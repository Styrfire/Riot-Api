package com.riot.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.riot.dto.Summoner;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class SummonerAPI
{
	Map<String, Summoner> getSummonersByName(QueryManager queryManager, String... summonerNames)
	{
		System.out.println("summonerNames.toString() = " + summonerNames.length);
		for (int i = 0; i < summonerNames.length; i++)
			summonerNames[i] = summonerNames[i].toLowerCase().replace(" ", "");

		String queryString = "/api/lol/na/v1.4/summoner/by-name/" + summonerNames[0];
		for (int i = 1; i < summonerNames.length; i++)
			queryString += "," + summonerNames[i];

		System.out.println("queryString = " + queryString);
		String response = queryManager.query(queryString);
		Type mapType = new TypeToken<HashMap<String,Summoner>>(){}.getType();

		return new Gson().fromJson(response, mapType);
	}

	Summoner getSummonerByName(QueryManager queryManager, String summonerName)
	{
		summonerName = summonerName.toLowerCase().replace(" ", "");
		return getSummonersByName(queryManager, summonerName).get(summonerName);
	}

	public Map<String, Summoner> getSummonersById(QueryManager queryManager, String summonerName)
	{
		String response = queryManager.query("https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/" + summonerName);
		Type mapType = new TypeToken<HashMap<String,Summoner>>(){}.getType();
		Map<String, Summoner> summoner = summoner = new Gson().fromJson(response, mapType);

		return summoner;

	}
}
