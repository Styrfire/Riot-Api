package com.riot.dto.Match

class Match
{
	int seasonId
	int queueId
	long gameId
	List<ParticipantIdentity> participantIdentities
	String gameVersion
	String platformId
	String gameMode
	int mapId
	String gameType
	List<TeamStats> teams
	List<Participant> participants
	long gameDuration
	long gameCreation
}
