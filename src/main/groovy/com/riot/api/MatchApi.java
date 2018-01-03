package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.Match.Match;
import com.riot.dto.Match.MatchList;
import com.riot.dto.Match.MatchTimeline;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;

class MatchApi
{
	Match getMatchByMatchId(QueryManager queryManager, Long matchId) throws RiotApiException
	{
		String queryString = "/lol/match/v3/matches/" + matchId.toString();

		String response = queryManager.query(queryString, METHOD.MATCH_BY_MATCH_ID);

		return new Gson().fromJson(response, Match.class);
	}

	MatchTimeline getMatchTimelineByMatchId(QueryManager queryManager, Long matchId) throws RiotApiException
	{
		String queryString = "/lol/match/v3/timelines/by-match/" + matchId.toString();

		String response = queryManager.query(queryString, METHOD.MATCH_TIMELINE_BY_MATCH_ID);

		return new Gson().fromJson(response, MatchTimeline.class);
	}

	MatchList getMatchListByAccountId(QueryManager queryManager, Long accountId) throws RiotApiException
	{
		String queryString = "/lol/match/v3/matchlists/by-account/" + accountId.toString();

		String response = queryManager.query(queryString, METHOD.MATCH_LIST_BY_ACCOUNT_ID);

		return new Gson().fromJson(response, MatchList.class);
	}

/*	MatchList getMatchListByAccountId(QueryManager queryManager, Integer accountId, Integer[] championIds, String[] rankedQueues, String[] seasons, Integer beginIndex, Integer endIndex) throws Exception
	{
		StringBuilder queryString = new StringBuilder("/lol/match/v3/matchlists/by-account/" + accountId.toString() + "?");
		if (championIds != null)
		{
			queryString.append("championIds=").append(championIds[0]);
			for (int i = 1; i < championIds.length; i++)
				queryString.append(",").append(championIds[i]);
		}

		if (rankedQueues != null)
		{
			if (championIds != null)
				queryString.append("&");

			queryString.append("rankedQueues=").append(rankedQueues[0]);
			for (int i = 1; i < rankedQueues.length; i++)
				queryString.append(",").append(rankedQueues[i]);
		}

		if (seasons != null)
		{
			if (championIds != null || rankedQueues != null)
				queryString.append("&");

			queryString.append("seasons=").append(seasons[0]);
			for (int i = 1; i < seasons.length; i++)
				queryString.append(",").append(seasons[i]);
		}

		if (beginIndex != null)
		{
			if (championIds != null || rankedQueues != null || seasons != null)
				queryString.append("&");

			queryString.append(beginIndex);
		}

		if (beginIndex != null)
		{
			if (championIds != null || rankedQueues != null || seasons != null || beginIndex != null)
				queryString.append("&");

			queryString.append(endIndex);
		}

		String response = null;
		try
		{
			response = queryManager.query(queryString.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		return new Gson().fromJson(response, MatchList.class);
	}*/
}
