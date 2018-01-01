package com.riot.api;

import com.riot.dto.ChampionMastery.ChampionMastery;
import com.riot.dto.Match.Match;
import com.riot.dto.Match.MatchList;
import com.riot.dto.Match.MatchTimeline;
import com.riot.dto.StaticData.Champion;
import com.riot.dto.Summoner.Summoner;
import com.riot.exception.RiotApiException;

import java.util.List;

public class RiotApi
{
	private QueryManager queryManager;

	private ChampionMasteryApi championMasteryApi;
	private MatchApi matchApi;
	private StaticDataApi staticDataApi;
	private SummonerApi summonerApi;

	public RiotApi(String apiKey)
	{
		this.queryManager = new QueryManager(apiKey);

		this.championMasteryApi = new ChampionMasteryApi();
		this.matchApi = new MatchApi();
		this.staticDataApi = new StaticDataApi();
		this.summonerApi = new SummonerApi();
	}

	public Summoner getSummonerByName(String summonerName) throws RiotApiException
	{
		return summonerApi.getSummonerByName(queryManager, summonerName);
	}

	public Match getMatchByMatchId(Long matchId) throws RiotApiException
	{
		return matchApi.getMatchByMatchId(queryManager, matchId);
	}

	public MatchTimeline getMatchTimelineByMatchId(Long matchId) throws RiotApiException
	{
		return matchApi.getMatchTimelineByMatchId(queryManager, matchId);
	}

	public MatchList getMatchListByAccountId(Long accountId) throws RiotApiException
	{
		return matchApi.getMatchListByAccountId(queryManager, accountId);
	}

	public List<ChampionMastery> getChampionMasteriesBySummonerId(long summonerId) throws RiotApiException
	{
		return championMasteryApi.getChampionMasteriesBySummonerId(queryManager, summonerId);
	}

	public Champion getStaticChampionInfoById(int championId) throws RiotApiException
	{
		return staticDataApi.getStaticChampionInfo(queryManager, championId);
	}

	/*	public MatchList getMatchlistByAccountId(Integer accountId, Integer[] championIds, String[] rankedQueues, String[] seasons, Integer beginIndex, Integer endIndex)
	{
		return matchApi.getMatchListByAccountId(queryManager, accountId, championIds, rankedQueues, seasons, beginIndex, endIndex);
	}*/
}
