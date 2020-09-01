package com.riot.api

import com.riot.dto.StaticData.ChampionList
import spock.lang.Ignore
import spock.lang.Specification

class StaticDataApiIntegrationTest extends Specification
{
	@Ignore
	def "test getStaticLastPatchVersion"()
	{
		given:
			RiotApi api = new RiotApi("API_KEY")
		when:
			String patchVersion = api.getStaticLastPatchVersion()
		then:
			patchVersion != null
	}

	@Ignore
	def "test getStaticChampionInfoById"()
	{
		given:
			RiotApi api = new RiotApi("API_KEY")
		when:
			ChampionList championList = api.getStaticChampionInfo("10.4.1")
		then:
			championList != null
	}
}
