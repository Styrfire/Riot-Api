package com.riot.api;

import com.google.common.util.concurrent.RateLimiter;
import com.riot.dto.RateLimiter.RateLimiterListData;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import com.riot.exception.RiotExceptionCreator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class QueryManager
{
	private final String apiKey;

	QueryManager(String apiKey)
	{
		this.apiKey = apiKey;
	}

	String query(String queryUrl, METHOD method) throws RiotApiException
	{
		Map<METHOD, RateLimiterListData> rateLimiterList = new HashMap<>();
 		try
		{
			boolean methodInList = false;
			// is method on rateLimiterList
			if (rateLimiterList.containsKey(method))
			{
				methodInList = true;
			}

			// if method is on rateLimiterList
			if (methodInList)
			{
				// if method is outside time window, remove it from list because the time window has reset
				if (rateLimiterList.get(method).getRateLimiter().tryAcquire())
				{
					rateLimiterList.remove(method);
					methodInList = false;
				}
				// if method is still inside time window
				else
				{
					// if method doesn't have anymore calls left within time window, wait, then remove from the list
					// because time window has reset
					if (rateLimiterList.get(method).getNumOfCalls() >= rateLimiterList.get(method).getMaxNumOfCalls())
					{
						rateLimiterList.get(method).getRateLimiter().acquire();
						rateLimiterList.remove(method);
						methodInList = false;
					}
				}
			}

			boolean appShortInList = false;
			// is app on rateLimiterList
			if (rateLimiterList.containsKey(METHOD.API_SHORT))
			{
				appShortInList = true;
			}

			// if app is on rateLimiterList
			if (appShortInList)
			{
				// if app is outside time window, remove it from list because the time window has reset
				if (rateLimiterList.get(METHOD.API_SHORT).getRateLimiter().tryAcquire())
				{
					rateLimiterList.remove(METHOD.API_SHORT);
					appShortInList = false;
				}
				// if method is still inside time window
				else
				{
					// if method doesn't have anymore calls left within time window, wait, then remove from the list
					// because time window has reset
					if (rateLimiterList.get(METHOD.API_SHORT).getNumOfCalls() >= rateLimiterList.get(METHOD.API_SHORT).getMaxNumOfCalls())
					{
						rateLimiterList.get(METHOD.API_SHORT).getRateLimiter().acquire();
						rateLimiterList.remove(METHOD.API_SHORT);
						appShortInList = false;
					}
				}
			}

			boolean appLongInList = false;
			// is app on rateLimiterList
			if (rateLimiterList.containsKey(METHOD.API_LONG))
			{
				appLongInList = true;
			}

			// if app is on rateLimiterList
			if (appLongInList)
			{
				// if app is outside time window, remove it from list because the time window has reset
				if (rateLimiterList.get(METHOD.API_LONG).getRateLimiter().tryAcquire())
				{
					rateLimiterList.remove(METHOD.API_LONG);
					appLongInList = false;
				}
				// if method is still inside time window
				else
				{
					// if method doesn't have anymore calls left within time window, wait, then remove from the list
					// because time window has reset
					if (rateLimiterList.get(METHOD.API_LONG).getNumOfCalls() >= rateLimiterList.get(METHOD.API_LONG).getMaxNumOfCalls())
					{
						rateLimiterList.get(METHOD.API_LONG).getRateLimiter().acquire();
						rateLimiterList.remove(METHOD.API_LONG);
						appLongInList = false;
					}
				}
			}

			//respect rate limit
//			if (!shortRateLimiter.tryAcquire())
//				System.out.println("ShortRateLimiterFailed");
//			if (!longRateLimiter.tryAcquire())
//				System.out.println("LongRateLimiterFailed");
//
//			shortRateLimiter.acquire();
//			longRateLimiter.acquire();

			// make the api call
			String urlString = "https://na1.api.riotgames.com" + queryUrl + "?api_key=" + apiKey;
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

			// if method wasn't on rateLimiterList, add it
			if (!methodInList)
			{
				Double timeWindow = Double.parseDouble(Arrays.asList(headers.get("X-Method-Rate-Limit").get(0).split(":")).get(1));

				RateLimiterListData rateLimiterListData = new RateLimiterListData();
				rateLimiterListData.setRateLimiter(RateLimiter.create(1/timeWindow));
				rateLimiterListData.setNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-Method-Rate-Limit-Count").get(0).split(":")).get(0)));
				rateLimiterListData.setMaxNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-Method-Rate-Limit").get(0).split(":")).get(0)));
				rateLimiterListData.setTimeWindowInSeconds(Integer.parseInt(Arrays.asList(headers.get("X-Method-Rate-Limit-Count").get(0).split(":")).get(1)));

				System.out.println("methodTimeWindow = " + timeWindow +
						"\nmethodNumOfCalls = " + rateLimiterListData.getNumOfCalls() +
						"\nmethodMaxNumOfCalls = " + rateLimiterListData.getMaxNumOfCalls() +
						"\nmethodTimeWindowInSeconds = " + rateLimiterListData.getTimeWindowInSeconds());

				rateLimiterList.put(method, rateLimiterListData);
			}
			// if method was on list, update the number of calls it's made within the time window
			else
			{
				rateLimiterList.get(method).setNumOfCalls(rateLimiterList.get(method).getNumOfCalls() + 1);
			}

			// if api short wasn't on rateLimiterList, add it
			if (!appShortInList && (method != METHOD.STATIC))
			{
				Double timeWindow = Double.parseDouble(Arrays.asList(headers.get("X-App-Rate-Limit").get(0).split("[:,]")).get(1));

				RateLimiterListData rateLimiterListData = new RateLimiterListData();
				rateLimiterListData.setRateLimiter(RateLimiter.create(1/timeWindow));
				rateLimiterListData.setNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit-Count").get(0).split("[:,]")).get(0)));
				rateLimiterListData.setMaxNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit").get(0).split("[:,]")).get(0)));
				rateLimiterListData.setTimeWindowInSeconds(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit-Count").get(0).split("[:,]")).get(1)));

				System.out.println("appShortTimeWindow = " + timeWindow +
				"\nappShortNumOfCalls = " + rateLimiterListData.getNumOfCalls() +
				"\nappShortMaxNumOfCalls = " + rateLimiterListData.getMaxNumOfCalls() +
				"\nappShortTimeWindowInSeconds = " + rateLimiterListData.getTimeWindowInSeconds());

				rateLimiterList.put(METHOD.API_SHORT, rateLimiterListData);
			}
			// if api short was on list, update the number of calls it's made within the time window
			else
			{
				rateLimiterList.get(METHOD.API_SHORT).setNumOfCalls(rateLimiterList.get(METHOD.API_SHORT).getNumOfCalls() + 1);
			}

			// if api long wasn't on rateLimiterList, add it
			if (!appLongInList && (method != METHOD.STATIC))
			{
				Double timeWindow = Double.parseDouble(Arrays.asList(headers.get("X-App-Rate-Limit").get(0).split("[:,]")).get(3));

				RateLimiterListData rateLimiterListData = new RateLimiterListData();
				rateLimiterListData.setRateLimiter(RateLimiter.create(1/timeWindow));
				rateLimiterListData.setNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit-Count").get(0).split("[:,]")).get(2)));
				rateLimiterListData.setMaxNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit").get(0).split("[:,]")).get(2)));
				rateLimiterListData.setTimeWindowInSeconds(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit-Count").get(0).split("[:,]")).get(3)));

				System.out.println("appLongTimeWindow = " + timeWindow +
				"\nappLongNumOfCalls = " + rateLimiterListData.getNumOfCalls() +
				"\nappLongMaxNumOfCalls = " + rateLimiterListData.getMaxNumOfCalls() +
				"\nappLongTimeWindowInSeconds = " + rateLimiterListData.getTimeWindowInSeconds());

				rateLimiterList.put(METHOD.API_LONG, rateLimiterListData);
			}
			// if api long was on list, update the number of calls it's made within the time window
			else
			{
				rateLimiterList.get(METHOD.API_LONG).setNumOfCalls(rateLimiterList.get(METHOD.API_LONG).getNumOfCalls() + 1);
			}

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
