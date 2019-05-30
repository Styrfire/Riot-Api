package com.riot.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.riot.dto.ChampionMastery.ChampionMastery;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class ChampionMasteryApi
{
	private static Logger logger = LoggerFactory.getLogger(ChampionMasteryApi.class);

	List<ChampionMastery> getChampionMasteriesBySummonerId(QueryManager queryManager, long summonerId) throws RiotApiException
	{
		logger.debug("summonerId = " + summonerId);
		String queryString = "/lol/champion-mastery/v3/champion-masteries/by-summoner/" + String.valueOf(summonerId) + "?";

		String response = queryManager.query(queryString, METHOD.CHAMPION_MASTERIES_BY_SUMMONER_ID);

		return new Gson().fromJson(response, new TypeToken<ArrayList<ChampionMastery>>(){}.getType());
	}
}
