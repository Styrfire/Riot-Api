package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.StaticData.Champion;
import com.riot.exception.RiotApiException;

class StaticDataApi
{
	Champion getStaticChampionInfo(QueryManager queryManager, Integer champId) throws RiotApiException
	{
		System.out.println("summonerName = " + champId);
		String queryString = "/lol/static-data/v3/champions/" + champId;
		System.out.println("queryString = " + queryString);

		String response = queryManager.query(queryString, true);

		return new Gson().fromJson(response, Champion.class);
	}
}
