package com.riot.api

import com.riot.dto.Match.Match
import com.riot.dto.Match.MatchList
import com.riot.dto.Match.MatchTimeline
import spock.lang.Specification

class MatchApiTest extends Specification
{
	def "test getMatchByMatchId"()
	{
		given:
			RiotApi api = new RiotApi()
		when:
			Match match = api.getMatchByMatchId(2448574892)
		then:
			true
	}

	def "test getMatchTimelineByMatchId"()
	{
		given:
			RiotApi api = new RiotApi()
		when:
			MatchTimeline matchTimeline = api.getMatchTimelineByMatchId(2448574892)
		then:
			true
	}

	def "test getMatchListByAccountId"()
	{
		given:
			RiotApi api = new RiotApi()
		when:
			MatchList matchList = api.getMatchListByAccountId(206871870)
		then:
			true
	}
}
