package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.Match.Match;
import com.riot.dto.Match.MatchList;
import com.riot.dto.Match.MatchTimeline;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MatchApi
{
	private static Logger logger = LoggerFactory.getLogger(MatchApi.class);

	Match getMatchByMatchId(QueryManager queryManager, Long matchId) throws RiotApiException
	{
		logger.debug("matchId = " + matchId);
		String queryString = "/lol/match/v4/matches/" + matchId.toString() + "?";

		String response = queryManager.query(queryString, METHOD.MATCH_BY_MATCH_ID);

		return new Gson().fromJson(response, Match.class);
	}

	MatchTimeline getMatchTimelineByMatchId(QueryManager queryManager, Long matchId) throws RiotApiException
	{
		logger.debug("matchId = " + matchId);
		String queryString = "/lol/match/v4/timelines/by-match/" + matchId.toString() + "?";

		String response = queryManager.query(queryString, METHOD.MATCH_TIMELINE_BY_MATCH_ID);

		return new Gson().fromJson(response, MatchTimeline.class);
	}

	MatchList getMatchListByAccountId(QueryManager queryManager, String encryptedAccountId) throws RiotApiException
	{
		logger.debug("encryptedAccountId = " + encryptedAccountId);
		String queryString = "/lol/match/v4/matchlists/by-account/" + encryptedAccountId + "?";

		String response = queryManager.query(queryString, METHOD.MATCH_LIST_BY_ACCOUNT_ID);

		return new Gson().fromJson(response, MatchList.class);
	}

	MatchList getMatchListByAccountId(QueryManager queryManager, String encryptedAccountId, Integer[] championIds, Integer[] rankedQueues, Integer[] seasons, Long beginTime, Long endTime, Integer beginIndex, Integer endIndex) throws RiotApiException
	{
		logger.debug("encryptedAccountId = " + encryptedAccountId);

		if (championIds != null)
		{
			for (int i = 0; i < championIds.length; i++)
				logger.debug("championIds[" + i + "] = " + championIds[i]);
		}
		else
			logger.debug("championIds[] = null");

		if (rankedQueues != null)
		{
			for (int i = 0; i < rankedQueues.length; i++)
				logger.debug("rankedQueues[" + i + "] = " + rankedQueues[i]);
		}
		else
			logger.debug("rankedQueues[] = null");

		if (seasons != null)
		{
			for (int i = 0; i < seasons.length; i++)
				logger.debug("seasons[" + i + "] = " + seasons[i]);
		}
		else
			logger.debug("seasons[] = null");

		logger.debug("beginTime = " + beginTime);
		logger.debug("endTime = " + endTime);
		logger.debug("beginIndex = " + beginIndex);
		logger.debug("endIndex = " + endIndex);

		StringBuilder queryString = new StringBuilder("/lol/match/v4/matchlists/by-account/" + encryptedAccountId + "?");

		if (championIds != null)
		{
			queryString.append("championIds=").append(championIds[0]);
			for (int i = 1; i < championIds.length; i++)
				queryString.append(",").append(championIds[i]);

			queryString.append("&");
		}

		if (rankedQueues != null)
		{
			queryString.append("rankedQueues=").append(rankedQueues[0]);
			for (int i = 1; i < rankedQueues.length; i++)
				queryString.append(",").append(rankedQueues[i] + "&");

			queryString.append("&");
		}

		if (seasons != null)
		{
			queryString.append("seasons=").append(seasons[0]);
			for (int i = 1; i < seasons.length; i++)
				queryString.append(",").append(seasons[i]);

			queryString.append("&");
		}

		if (beginTime != null)
		{
			queryString.append("beginTime=" + beginTime).append("&");
		}

		if (endTime != null)
		{
			queryString.append("endTime=" + endTime).append("&");
		}

		if (beginIndex != null)
		{
			queryString.append("beginIndex=" + beginIndex).append("&");
		}

		if (endIndex != null)
		{
			queryString.append("endIndex=" + endIndex).append("&");
		}

		String response = queryManager.query(queryString.toString(), METHOD.MATCH_LIST_BY_ACCOUNT_ID);

		return new Gson().fromJson(response, MatchList.class);
	}
}
