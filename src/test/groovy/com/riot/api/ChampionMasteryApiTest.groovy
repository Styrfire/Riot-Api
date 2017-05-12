package com.riot.api

import com.riot.dto.ChampionMastery.ChampionMastery
import com.riot.dto.Summoner.Summoner
import spock.lang.Specification

class ChampionMasteryApiTest extends Specification
{
	def "test getChampionMasteries"()
	{
		given:
			RiotApi api = new RiotApi()
			Summoner summoner = api.getSummonerByName("Zann Starfire")
		when:
			List<ChampionMastery> championMastery = api.getChampionMasteries(summoner.getId())
		then:
			true
	}
}
