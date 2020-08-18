package com.riot.dto.Match

class Match
{
	long gameId
	List<ParticipantIdentity> participantIdentities
	int queueId
	String gameType
	long gameDuration
	List<TeamStats> teams
	String platformId
	long gameCreation
	int seasonId
	String gameVersion
	int mapId
	String gameMode
	List<Participant> participants
}
