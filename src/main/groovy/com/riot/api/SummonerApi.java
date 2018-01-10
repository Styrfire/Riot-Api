package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.Summoner.Summoner;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;

class SummonerApi
{
	Summoner getSummonerByName(QueryManager queryManager, String summonerName) throws RiotApiException
	{
		System.out.println("summonerName = " + summonerName);
		summonerName = summonerName.replace(" ", "%20");

		String queryString = "/lol/summoner/v3/summoners/by-name/" + summonerName + "?";
		System.out.println("queryString = " + queryString);

		String response = queryManager.query(queryString, METHOD.GENERIC);

		return new Gson().fromJson(response, Summoner.class);
	}
}
