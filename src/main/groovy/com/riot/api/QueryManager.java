package com.riot.api;

import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import com.riot.exception.RiotExceptionCreator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

class QueryManager
{
	private final String apiKey;
	private final RiotRateLimiter rateLimiter;

	QueryManager(String apiKey)
	{
		this.apiKey = apiKey;
		this.rateLimiter = new RiotRateLimiter();
	}

	String query(String queryUrl, METHOD method) throws RiotApiException
	{
 		try
		{
			// check to make sure the method isn't exceeding it's rate limit
			rateLimiter.preApiCallRateLimit(method);

			// make the api call
			String urlString = "https://na1.api.riotgames.com" + queryUrl + "api_key=" + apiKey;
			System.out.println("urlString = " + urlString);
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);

			// handle exceptions
			if (connection.getResponseCode() >= 300)
				RiotExceptionCreator.throwException(connection.getResponseCode());

			// rate limit headers
			Map<String, List<String>> headers = connection.getHeaderFields();
			System.out.println(headers.get("X-App-Rate-Limit"));
			System.out.println(headers.get("X-App-Rate-Limit-Count"));
			System.out.println(headers.get("X-Method-Rate-Limit"));
			System.out.println(headers.get("X-Method-Rate-Limit-Count"));

			// updated method rate limit objects
			rateLimiter.postApiCallRateLimit(method, headers);

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
