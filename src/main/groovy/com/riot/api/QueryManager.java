package com.riot.api;

import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import com.riot.exception.RiotExceptionCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

class QueryManager
{
	private final String apiKey;
	private final RiotRateLimiter rateLimiter;
	private final RestTemplate restTemplate;

	QueryManager(String apiKey)
	{
		this.apiKey = apiKey;
		this.rateLimiter = new RiotRateLimiter();
		this.restTemplate = new RestTemplate();
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
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlString, String.class);

			// handle exceptions
			if (responseEntity.getStatusCode().value() >= 300)
				RiotExceptionCreator.throwException(responseEntity.getStatusCode().value());

			// rate limit headers
			Map<String, List<String>> headers = responseEntity.getHeaders();
			System.out.println(headers.get("X-App-Rate-Limit"));
			System.out.println(headers.get("X-App-Rate-Limit-Count"));
			System.out.println(headers.get("X-Method-Rate-Limit"));
			System.out.println(headers.get("X-Method-Rate-Limit-Count"));

			// updated method rate limit objects
			rateLimiter.postApiCallRateLimit(method, headers);

			// return the response
			return responseEntity.getBody();
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
