package com.riot.api

import com.riot.dto.Summoner.Summoner
import com.riot.enums.METHOD
import org.apache.log4j.Logger
import spock.lang.Specification

class SummonerApiTest extends Specification
{
	private static Logger logger = Logger.getLogger(SummonerApiTest.class)

	def "test getSummonerByName"()
	{
		given:
			QueryManager queryManagerMock = Mock(QueryManager)
			ClassLoader classLoader = getClass().getClassLoader()
			File file = new File(classLoader.getResource("apiResponses/summoner/summonerByName.json").getFile())
			String responseJson = new String(file.readBytes())
			logger.info("responseJson = " + responseJson)
			queryManagerMock.query(_ as String, _ as METHOD) >> responseJson
		when:
			Summoner summoner = new SummonerApi().getSummonerByName(queryManagerMock, "Zann Starfire")
		then:
			summoner != null
			summoner.getProfileIconId() == 3081
			summoner.getName() == "Zann Starfire"
			summoner.getSummonerLevel() == 59
			summoner.getAccountId() == 206871870
			summoner.getId() == 44199889
			summoner.getRevisionDate() == 1520548102000
	}
}
