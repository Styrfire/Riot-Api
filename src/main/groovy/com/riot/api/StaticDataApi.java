package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.StaticData.Champion;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.apache.log4j.Logger;

class StaticDataApi
{
	private static Logger logger = Logger.getLogger(StaticDataApi.class);

	Champion getStaticChampionInfo(QueryManager queryManager, Integer champId) throws RiotApiException
	{
		logger.debug("summonerName = " + champId);
		String queryString = "/lol/static-data/v3/champions/" + champId + "?locale=en_US&";

		String response = queryManager.query(queryString, METHOD.STATIC);

		return new Gson().fromJson(response, Champion.class);
	}
}
