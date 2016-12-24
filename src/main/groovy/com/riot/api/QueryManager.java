package com.riot.api;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QueryManager
{
	private static final double DEFAULT_SHORT_RATE_LIMIT = 10/10.0;
	private static final double DEFAULT_LONG_RATE_LIMIT = 600/500.0;

	private final RateLimiter shortRateLimiter;
	private final RateLimiter longRateLimiter;

	public QueryManager()
	{
		longRateLimiter = RateLimiter.create(DEFAULT_LONG_RATE_LIMIT);
		shortRateLimiter = RateLimiter.create(DEFAULT_SHORT_RATE_LIMIT);
	}

	public String query(String queryUrl)
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

			URL url = new URL("https://na.api.pvp.net" + queryUrl + "?api_key=e76cf560-ec14-4aae-946c-35967340214d");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);

			// handle exceptions
			if (connection.getResponseCode() >= 300)
				throw new Exception();

			// Get the response
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line, response = "";
			while ((line = bufferedReader.readLine()) != null)
			{
				response += line;
			}
			bufferedReader.close();
//			response = connection.getResponseMessage();
			System.out.println("ResponseCode = " + connection.getResponseCode());
//			System.out.println("Response = " + response);

			return response;
		}
		catch (Exception e)
		{
			e.getMessage();
			return null;
		}
	}
}
