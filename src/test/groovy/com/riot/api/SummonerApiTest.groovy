package com.riot.api

import com.riot.dto.Summoner.Summoner
import spock.lang.Specification

class SummonerApiTest extends Specification
{
	def "test getSummonerByName"()
	{
		given:
			RiotApi api = new RiotApi()
		when:
			Summoner summoner = api.getSummonerByName("Zann Starfire")
		then:
			summoner != null
			summoner.getId() == 44199889
	}
}
