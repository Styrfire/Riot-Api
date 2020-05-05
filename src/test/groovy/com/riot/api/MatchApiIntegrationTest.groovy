package com.riot.api

import com.riot.dto.Match.Match
import com.riot.dto.Match.MatchList
import com.riot.dto.Match.MatchTimeline
import com.riot.enums.METHOD
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Ignore
import spock.lang.Specification

class MatchApiIntegrationTest extends Specification
{
	private String apiKey

	private static Logger logger = LoggerFactory.getLogger(MatchApiIntegrationTest.class)

	@Ignore
	def "test getMatchByMatchId"()
	{
		given:
			RiotApi api = new RiotApi("API_KEY")
		when:
			Match match = api.getMatchByMatchId(1111111111)
		then:
			match != null
	}

	@Ignore
	def "test getMatchTimelineByMatchId"()
	{
		given:
			RiotApi api = new RiotApi("")
		when:
			MatchTimeline matchTimeline = api.getMatchTimelineByMatchId(1111111111)
		then:
			matchTimeline != null
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

	@Ignore
	def "test getMatchListByAccountId with parameters"()
	{
		given:
			apiKey = System.getProperty("api.key")
			RiotApi api = new RiotApi(apiKey)
			QueryManager queryManager = new QueryManager(apiKey)
		when:
			// with Chadwîck using My Worst Enemy key
			MatchList matchList = new MatchApi().getMatchListByAccountId(queryManager, "7H-6fWTCIIiD6ct-FlxfgxL3Eq7lW8qIdkL-CibUWlutOhE", null, null, null, null, null, null, null)
		then:
			matchList != null
	}
}