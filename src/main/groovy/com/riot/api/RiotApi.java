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

	public Map<String, Summoner> getSummonersByName(String... summonerName)
	{
		return summonerApi.getSummonersByName(queryManager, summonerName);
	}

	public Summoner getSummonerByName(String summonerName)
	{
		return summonerApi.getSummonerByName(queryManager, summonerName);
	}

/*	public Map<String, Summoner> getSummonersByName(String... summonerNames)
	{
		System.out.println("summonerNames.toString() = " + summonerNames.length);
		for (int i = 0; i < summonerNames.length; i++)
			summonerNames[i] = summonerNames[i].toLowerCase().replace(" ", "");

		String queryString = "/api/lol/na/v1.4/summoner/by-name/" + summonerNames[0];
		for (int i = 1; i < summonerNames.length; i++)
			queryString += "," + summonerNames[i];

		System.out.println("queryString = " + queryString);
		String response = queryManager.query(queryString);
		Type mapType = new TypeToken<HashMap<String,Summoner>>(){}.getType();

		return new Gson().fromJson(response, mapType);
	}

	public Summoner getSummonerByName(String summonerName)
	{
		summonerName = summonerName.toLowerCase().replace(" ", "");
		return getSummonersByName(summonerName).get(summonerName);
	}*/

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
