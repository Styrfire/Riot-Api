package com.riot;

import com.riot.api.RiotApi;
import com.riot.dto.Summoner;

public class TestApi
{
	public static void main(String[] args)
	{
		RiotApi api = new RiotApi();
		Summoner me = api.getSummonerByName("Zann Starfire");
		System.out.println(me.getName() + "'s id = " + me.getId());
	}
}
