package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.StaticData.ChampionList;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class StaticDataApi
{
	private static Logger logger = LoggerFactory.getLogger(StaticDataApi.class);

	ChampionList getStaticChampionInfo(QueryManager queryManager, String patchVersion) throws RiotApiException
	{
		String queryString = "http://ddragon.leagueoflegends.com/cdn/" + patchVersion + "/data/en_US/champion.json";

		String response = queryManager.query(queryString, METHOD.STATIC);

		return new Gson().fromJson(response, ChampionList.class);
	}
}
