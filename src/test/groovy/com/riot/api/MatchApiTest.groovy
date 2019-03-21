package com.riot.api

import com.riot.dto.Match.Match
import com.riot.dto.Match.MatchList
import com.riot.dto.Match.MatchTimeline
import com.riot.enums.METHOD
import org.apache.log4j.Logger
import spock.lang.Ignore
import spock.lang.Specification

class MatchApiTest extends Specification
{
	private static Logger logger = Logger.getLogger(MatchApiTest.class)

	@Ignore
	def "test getMatchByMatchId"()
	{
		given:
			RiotApi api = new RiotApi("")
		when:
			Match match = api.getMatchByMatchId(2448574892)
		then:
			match != null
			match.getGameId() == 2448574892
	}

	@Ignore
	def "test getMatchTimelineByMatchId"()
	{
		given:
			RiotApi api = new RiotApi("")
		when:
			MatchTimeline matchTimeline = api.getMatchTimelineByMatchId(2448574892)
		then:
			matchTimeline != null
			matchTimeline.getFrameInterval() == 60000
	}

	def "test getMatchListByAccountId"()
	{
		given:
			QueryManager queryManagerMock = Mock(QueryManager)
			ClassLoader classLoader = getClass().getClassLoader()
			File file = new File(classLoader.getResource("apiResponses/match/matchListByAccountId.json").getFile())
			String responseJson = new String(file.readBytes())
			logger.info("responseJson = " + responseJson)
			queryManagerMock.query(_ as String, _ as METHOD) >> responseJson
		when:
			MatchList matchList = new MatchApi().getMatchListByAccountId(queryManagerMock, "0aPpp5KW_Q3Ro748Fj2U5P07PuNVA-ReP5RoQTk0vBR_2YQ")
		then:
			matchList != null
			matchList.getMatches() != null
			matchList.getTotalGames() == 153
			matchList.getStartIndex() == 0
			matchList.getEndIndex() == 100
	}
}
