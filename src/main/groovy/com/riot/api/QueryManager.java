package com.riot.api;

import com.google.common.util.concurrent.RateLimiter;
import com.riot.exception.RiotApiException;
import com.riot.exception.RiotExceptionCreator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class QueryManager
{
	private static final double DEFAULT_SHORT_RATE_LIMIT = 20/1.0;
	private static final double DEFAULT_LONG_RATE_LIMIT = 100/120.0;
	private static final double STATIC_DATA_RATE_LIMIT = 10/3600.0;

	private final String apiKey;

	private final RateLimiter shortRateLimiter;
	private final RateLimiter longRateLimiter;
	private final RateLimiter staticDataRateLimiter;

	QueryManager(String apiKey)
	{
		this.apiKey = apiKey;

		longRateLimiter = RateLimiter.create(DEFAULT_LONG_RATE_LIMIT);
		shortRateLimiter = RateLimiter.create(DEFAULT_SHORT_RATE_LIMIT);
		staticDataRateLimiter = RateLimiter.create(STATIC_DATA_RATE_LIMIT);
	}

	String query(String queryUrl, boolean isStaticData) throws RiotApiException
	{
		try
		{
			//respect rate limit
			if (isStaticData)
			{
				if (!staticDataRateLimiter.tryAcquire())
					System.out.println("StaticDataRateLimiterFailed");

				staticDataRateLimiter.acquire();
			}
			else
			{
				if (!shortRateLimiter.tryAcquire())
					System.out.println("ShortRateLimiterFailed");
				if (!longRateLimiter.tryAcquire())
					System.out.println("LongRateLimiterFailed");

				shortRateLimiter.acquire();
				longRateLimiter.acquire();
			}

			String urlString = "https://na1.api.riotgames.com" + queryUrl + "?api_key=" + apiKey;
			System.out.println("urlString = " + urlString);
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);

			// handle exceptions
			if (connection.getResponseCode() >= 300)
				RiotExceptionCreator.throwException(connection.getResponseCode());

			// Get the response
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null)
			{
				response.append(line);
			}
			bufferedReader.close();
			System.out.println("ResponseCode = " + connection.getResponseCode());

			return response.toString();
		}
		catch (RiotApiException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RiotApiException("Oops... Something went wrong...");
		}
	}
}
