package com.riot.api;

import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import com.riot.exception.RiotExceptionCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

class QueryManager
{
	private static Logger logger = LoggerFactory.getLogger(QueryManager.class);

	private final String apiKey;
	private final RiotRateLimiter rateLimiter;
	private final RestTemplate restTemplate;

	QueryManager(String apiKey)
	{
		this.apiKey = apiKey;
		this.rateLimiter = new RiotRateLimiter();
		this.restTemplate = new RestTemplate();
		// custom error handler for RestTemplate that throws exception when statusCode >= 300, not just 4xx and 5xx
		restTemplate.setErrorHandler(new ResponseErrorHandler()
		{
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException
			{
				return response.getStatusCode().value() >= 300;
			}

			@Override
			public void handleError(ClientHttpResponse response) throws IOException
			{
				MediaType contentType = response.getHeaders().getContentType();
				Charset charset = contentType != null ? contentType.getCharset() : null;
				byte[] body = FileCopyUtils.copyToByteArray(response.getBody());
				throw new HttpClientErrorException(response.getStatusCode(), response.getStatusText(), body, charset);
			}
		});
	}

	String query(String queryUrl, METHOD method) throws RiotApiException
	{
		try
		{
			// check to make sure the method isn't exceeding it's rate limit
			rateLimiter.preApiCallRateLimit(method);

			// make the api call
			String urlString = "https://na1.api.riotgames.com" + queryUrl + "api_key=" + apiKey;
			logger.info("urlString = " + urlString);
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlString, String.class);

			// rate limit headers
			Map<String, List<String>> headers = responseEntity.getHeaders();
			logger.debug("something", headers.get("X-App-Rate-Limit"));
			logger.debug("something", headers.get("X-App-Rate-Limit-Count"));
			logger.debug("something", headers.get("X-Method-Rate-Limit"));
			logger.debug("something", headers.get("X-Method-Rate-Limit-Count"));

			// updated method rate limit objects
			rateLimiter.postApiCallRateLimit(method, headers);

			// return the response
			return responseEntity.getBody();
		}
		catch (HttpClientErrorException e)
		{
			logger.error("Status Code: " + e.getStatusCode().value(), e);
			RiotExceptionCreator.throwException(e.getStatusCode().value());
			return null;
		}
		catch (Exception e)
		{
			logger.error("Oops... Something went wrong...", e);
			throw new RiotApiException("Oops... Something went wrong...");
		}
	}
}
