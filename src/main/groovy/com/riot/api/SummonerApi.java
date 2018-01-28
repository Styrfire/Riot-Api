package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.Summoner.Summoner;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.apache.log4j.Logger;

class SummonerApi
{
	private static Logger logger = Logger.getLogger(SummonerApi.class);

	Summoner getSummonerByName(QueryManager queryManager, String summonerName) throws RiotApiException
	{
		logger.debug("summonerName = " + summonerName);
		summonerName = summonerName.replace(" ", "%20");
		String queryString = "/lol/summoner/v3/summoners/by-name/" + summonerName + "?";

		String response = queryManager.query(queryString, METHOD.SUMMONER_BY_NAME);

		return new Gson().fromJson(response, Summoner.class);
	}
}
