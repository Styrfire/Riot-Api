package com.riot.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.riot.dto.ChampionMastery.ChampionMastery;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChampionMasteryApi
{
	List<ChampionMastery> getChampionMasteriesBySummonerId(QueryManager queryManager, long summonerId)
	{
		System.out.println("summonerId = " + String.valueOf(summonerId));

		String queryString = "/lol/champion-mastery/v3/champion-masteries/by-summoner/" + String.valueOf(summonerId);

		System.out.println("queryString = " + queryString);
		String response = queryManager.query(queryString);

		return new Gson().fromJson(response, new TypeToken<ArrayList<ChampionMastery>>(){}.getType());
	}
}
