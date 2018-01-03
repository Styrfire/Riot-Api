package com.riot.api

import com.riot.dto.StaticData.Champion
import com.riot.staticInfo.RiotEnum
import org.junit.Ignore
import spock.lang.Specification

@Ignore
class StaticDataApiTest extends Specification
{
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
