package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.Summoner.Summoner;

public class SummonerApi
{
	Summoner getSummonerByName(QueryManager queryManager, String summonerName)
	{
		System.out.println("summonerName = " + summonerName);
		summonerName = summonerName.replace(" ", "%20");

		String queryString = "/lol/summoner/v3/summoners/by-name/" + summonerName;

		System.out.println("queryString = " + queryString);
		String response = queryManager.query(queryString);

		return new Gson().fromJson(response, Summoner.class);
	}
}
