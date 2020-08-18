package com.riot.dto.Match

class Participant
{
	int participantId
	int championId
	List<Rune> runes
	ParticipantStats stats
	int teamId
	ParticipantTimeline timeline
	int spell1Id
	int spell2Id
	String highestAchievedSeasonTier
	List<Mastery> masteries
}
