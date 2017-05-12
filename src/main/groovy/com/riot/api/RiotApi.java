package com.riot.api;

import com.riot.dto.ChampionMastery.ChampionMastery;
import com.riot.dto.Match.MatchList;
import com.riot.dto.Summoner.Summoner;

import java.util.List;

public class RiotApi
{
	private QueryManager queryManager;

	private ChampionMasteryApi championMasteryApi;
	private MatchApi matchApi;
	private SummonerApi summonerApi;

	public RiotApi()
	{
		this.queryManager = new QueryManager();

		this.championMasteryApi = new ChampionMasteryApi();
		this.matchApi = new MatchApi();
		this.summonerApi = new SummonerApi();
	}

	public Summoner getSummonerByName(String summonerName)
	{
		return summonerApi.getSummonerByName(queryManager, summonerName);
	}

	MatchList getMatchListByAccountId(Integer accountId)
	{
		return matchApi.getMatchListByAccountId(queryManager, accountId);
	}

	public List<ChampionMastery> getChampionMasteries(long summonerId)
	{
		return championMasteryApi.getChampionMasteries(queryManager, summonerId);
	}

/*	public Match getMatchlistBySummonerId(Integer summonerId, Integer[] championIds, String[] rankedQueues, String[] seasons, Integer beginIndex, Integer endIndex)
	{
		return matchApi.getMatchListBySummonerId(queryManager, summonerId, championIds, rankedQueues, seasons, beginIndex, endIndex);
	}*/
}
