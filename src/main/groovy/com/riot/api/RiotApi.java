package com.riot.api;

import com.riot.dto.Summoner;

import java.util.Map;

public class RiotApi
{
	private QueryManager queryManager;
	private SummonerApi summonerApi;

	public RiotApi()
	{
		this.queryManager = new QueryManager();
		this.summonerApi = new SummonerApi();
	}

	public Summoner getSummonerByName(String summonerName)
	{
		return summonerApi.getSummonerByName(queryManager, summonerName);
	}

/*	public MatchList getMatchListBySummonerId(Integer summonerId, int[] championIds, String[] rankedQueues, String[] seasons)
	{
		String queryString = "" + summonerId;
		if (championIds != null)
			for (int i = 1; i < championIds.length; i++)
				queryString += "";

		if (rankedQueues != null)
		{
			queryString += "?rankedQueues=" + rankedQueues[0];
			for (int i = 1; i < rankedQueues.length; i++)
				queryString += "," + rankedQueues[i];
		}

		if (seasons != null)
		{
			queryString += "";
			for (int i = 1; i < seasons.length; i++)
				queryString += "";
		}

		String response = queryManager.query(queryString);

		return new Gson().fromJson(response, MatchList.class);
	}*/
}
