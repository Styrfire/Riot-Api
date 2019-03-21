package com.riot.api

import com.riot.dto.StaticData.Champion
import spock.lang.Ignore
import spock.lang.Specification

class StaticDataApiTest extends Specification
{
	@Ignore
	def "test getStaticChampionInfoById"()
	{
		given:
			RiotApi api = new RiotApi(RiotEnum.apiKey)
		when:
			Champion champion = api.getStaticChampionInfoById(1)
		then:
			champion != null
	}
}
