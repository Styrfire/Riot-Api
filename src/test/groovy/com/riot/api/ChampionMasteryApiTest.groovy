package com.riot.api

import com.riot.dto.ChampionMastery.ChampionMastery
import com.riot.dto.Summoner.Summoner
import spock.lang.Specification

class ChampionMasteryApiTest extends Specification
{
	def "test getChampionMasteriesBySummonerId"()
	{
		given:
			RiotApi api = new RiotApi()
			Summoner summoner = api.getSummonerByName("Zann Starfire")
		when:
			List<ChampionMastery> championMastery = api.getChampionMasteriesBySummonerId(summoner.getId())
		then:
			true
	}
}
