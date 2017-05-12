package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.Match.Match;
import com.riot.dto.Match.MatchList;
import com.riot.dto.Match.MatchTimeline;

class MatchApi
{
	Match getMatchByMatchId(QueryManager queryManager, Long matchId)
	{
		String queryString = "/lol/match/v3/matches/" + matchId.toString();
		String response = queryManager.query(queryString);

		return new Gson().fromJson(response, Match.class);
	}

	MatchTimeline getMatchTimelineByMatchId(QueryManager queryManager, Long matchId)
	{
		String queryString = "/lol/match/v3/timelines/by-match/" + matchId.toString();
		String response = queryManager.query(queryString);

		return new Gson().fromJson(response, MatchTimeline.class);
	}

	MatchList getMatchListByAccountId(QueryManager queryManager, Long accountId)
	{
		String queryString = "/lol/match/v3/matchlists/by-account/" + accountId.toString();
		String response = queryManager.query(queryString);

		return new Gson().fromJson(response, MatchList.class);
	}

/*	MatchList getMatchListByAccountId(QueryManager queryManager, Integer accountId, Integer[] championIds, String[] rankedQueues, String[] seasons, Integer beginIndex, Integer endIndex)
	{
		String queryString = "/lol/match/v3/matchlists/by-account/" + accountId.toString() + "?";
		if (championIds != null)
		{
			queryString += "championIds=" + championIds[0];
			for (int i = 1; i < championIds.length; i++)
				queryString += "," + championIds[i];
		}

		if (rankedQueues != null)
		{
			if (championIds != null)
				queryString += "&";

			queryString += "rankedQueues=" + rankedQueues[0];
			for (int i = 1; i < rankedQueues.length; i++)
				queryString += "," + rankedQueues[i];
		}

		if (seasons != null)
		{
			if (championIds != null || rankedQueues != null)
				queryString += "&";

			queryString += "seasons=" + seasons[0];
			for (int i = 1; i < seasons.length; i++)
				queryString += "," + seasons[i];
		}

		if (beginIndex != null)
		{
			if (championIds != null || rankedQueues != null || seasons != null)
				queryString += "&";

			queryString += beginIndex;
		}

		if (beginIndex != null)
		{
			if (championIds != null || rankedQueues != null || seasons != null || beginIndex != null)
				queryString += "&";

			queryString += endIndex;
		}

		String response = queryManager.query(queryString);

		return new Gson().fromJson(response, MatchList.class);
	}*/
}
