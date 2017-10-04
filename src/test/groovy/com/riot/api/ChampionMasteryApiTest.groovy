package com.riot.api

import com.riot.dto.ChampionMastery.ChampionMastery
import com.riot.staticInfo.RiotEnum
import org.junit.Ignore
import spock.lang.Specification

@Ignore
class ChampionMasteryApiTest extends Specification
{
	def "test getChampionMasteriesBySummonerId"()
	{
		given:
			RiotApi api = new RiotApi(RiotEnum.apiKey)
		when:
			List<ChampionMastery> championMastery = api.getChampionMasteriesBySummonerId(44199889)
		then:
			championMastery != null
			championMastery.get(0).getChampionId() == 154
	}
}
