package com.riot.dto.Match

class MatchFrame
{
	long timestamp
	Map<Integer, MatchParticipantFrame> participantFrames
	List<MatchEvent> events
}
