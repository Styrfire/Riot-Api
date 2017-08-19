package com.riot.api;

import com.riot.dto.ChampionMastery.ChampionMastery;
import com.riot.dto.Match.Match;
import com.riot.dto.Match.MatchList;
import com.riot.dto.Match.MatchTimeline;
import com.riot.dto.Summoner.Summoner;

import java.util.List;

public class RiotApi
{
	private QueryManager queryManager;

	private ChampionMasteryApi championMasteryApi;
	private MatchApi matchApi;
	private SummonerApi summonerApi;
	private static String apiKey = "RGAPI-a195fc65-a616-464c-946a-1faa4429295f";

	public RiotApi()
	{
		this(apiKey);
	}

	public RiotApi(String apiKey)
	{
		this.queryManager = new QueryManager(apiKey);

		this.championMasteryApi = new ChampionMasteryApi();
		this.matchApi = new MatchApi();
		this.summonerApi = new SummonerApi();
	}

	public Summoner getSummonerByName(String summonerName)
	{
		return summonerApi.getSummonerByName(queryManager, summonerName);
	}

	Match getMatchByMatchId(Long matchId)
	{
		return matchApi.getMatchByMatchId(queryManager, matchId);
	}

	MatchTimeline getMatchTimelineByMatchId(Long matchId)
	{
		return matchApi.getMatchTimelineByMatchId(queryManager, matchId);
	}

	MatchList getMatchListByAccountId(Long accountId)
	{
		return matchApi.getMatchListByAccountId(queryManager, accountId);
	}

	public List<ChampionMastery> getChampionMasteriesBySummonerId(long summonerId)
	{
		return championMasteryApi.getChampionMasteriesBySummonerId(queryManager, summonerId);
	}

/*	public Match getMatchlistBySummonerId(Integer summonerId, Integer[] championIds, String[] rankedQueues, String[] seasons, Integer beginIndex, Integer endIndex)
	{
		return matchApi.getMatchListBySummonerId(queryManager, summonerId, championIds, rankedQueues, seasons, beginIndex, endIndex);
	}*/
}
