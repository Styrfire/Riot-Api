package com.riot.api;

import com.riot.dto.ChampionMastery.ChampionMastery;
import com.riot.dto.Match.Match;
import com.riot.dto.Match.MatchList;
import com.riot.dto.Match.MatchTimeline;
import com.riot.dto.StaticData.Champion;
import com.riot.dto.StaticData.ChampionList;
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

	public MatchList getMatchListByAccountId(String encryptedAccountId) throws RiotApiException
	{
		return matchApi.getMatchListByAccountId(queryManager, encryptedAccountId);
	}

	public MatchList getMatchListByAccountId(String encryptedAccountId, Integer[] championIds, Integer[] rankedQueues, Integer[] seasons, Long beginTime, Long endTime, Integer beginIndex, Integer endIndex) throws RiotApiException
	{
		return matchApi.getMatchListByAccountId(queryManager, encryptedAccountId, championIds, rankedQueues, seasons, beginTime, endTime, beginIndex, endIndex);
	}

	public List<ChampionMastery> getChampionMasteriesBySummonerId(long summonerId) throws RiotApiException
	{
		return championMasteryApi.getChampionMasteriesBySummonerId(queryManager, summonerId);
	}

	public String getStaticLastPatchVersion() throws RiotApiException
	{
		return staticDataApi.getStaticLastPatchVersion(queryManager);
	}

	public ChampionList getStaticChampionInfo(String patchVersion) throws RiotApiException
	{
		return staticDataApi.getStaticChampionInfo(queryManager, patchVersion);
	}
}
