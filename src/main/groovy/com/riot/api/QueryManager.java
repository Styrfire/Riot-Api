package com.riot.api;

import com.google.common.util.concurrent.RateLimiter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class QueryManager
{
	private static final double DEFAULT_SHORT_RATE_LIMIT = 20/1.0;
	private static final double DEFAULT_LONG_RATE_LIMIT = 100/120.0;

	private final String apiKey;

	private final RateLimiter shortRateLimiter;
	private final RateLimiter longRateLimiter;

	QueryManager(String apiKey)
	{
		this.apiKey = apiKey;

		longRateLimiter = RateLimiter.create(DEFAULT_LONG_RATE_LIMIT);
		shortRateLimiter = RateLimiter.create(DEFAULT_SHORT_RATE_LIMIT);
	}

	String query(String queryUrl)
	{
		try
		{
			//respect rate limit
			if (!shortRateLimiter.tryAcquire())
				System.out.println("ShortRateLimiterFailed");
			if (!longRateLimiter.tryAcquire())
				System.out.println("LongRateLimiterFailed");

			shortRateLimiter.acquire();
			longRateLimiter.acquire();

			URL url = new URL("https://na1.api.riotgames.com" + queryUrl + "?api_key=" + apiKey);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);

			// handle exceptions
			if (connection.getResponseCode() >= 300)
				throw new Exception();

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
		catch (Exception e)
		{
			e.getMessage();
			return null;
		}
	}
}
