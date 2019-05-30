package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.Summoner.Summoner;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SummonerApi
{
	private static Logger logger = LoggerFactory.getLogger(SummonerApi.class);

	Summoner getSummonerByName(QueryManager queryManager, String summonerName) throws RiotApiException
	{
		logger.debug("summonerName = " + summonerName);
		String queryString = "/lol/summoner/v4/summoners/by-name/" + summonerName + "?";

		String response = queryManager.query(queryString, METHOD.SUMMONER_BY_NAME);

		return new Gson().fromJson(response, Summoner.class);
	}
}
