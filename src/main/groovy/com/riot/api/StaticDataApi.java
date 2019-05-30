package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.StaticData.Champion;
import com.riot.dto.StaticData.ChampionList;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class StaticDataApi
{
	private static Logger logger = LoggerFactory.getLogger(StaticDataApi.class);

	ChampionList getStaticChampionInfo(QueryManager queryManager) throws RiotApiException
	{
		String queryString = "/lol/static-data/v3/champions?locale=en_US&dataById=true&";

		String response = queryManager.query(queryString, METHOD.STATIC);

		return new Gson().fromJson(response, ChampionList.class);
	}

	Champion getStaticChampionInfoById(QueryManager queryManager, Integer champId) throws RiotApiException
	{
		logger.debug("summonerName = " + champId);
		String queryString = "/lol/static-data/v3/champions/" + champId + "?locale=en_US&";

		String response = queryManager.query(queryString, METHOD.STATIC);

		return new Gson().fromJson(response, Champion.class);
	}
}
