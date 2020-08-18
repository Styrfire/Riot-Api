package com.riot.dto.Match

class ParticipantTimeline
{
	int participantId
	Map<String, Double> csDiffPerMinDeltas
	Map<String, Double> damageTakenPerMinDeltas
	String role
	Map<String, Double> damageTakenDiffPerMinDeltas
	Map<String, Double> xpPerMinDeltas
	Map<String, Double> xpDiffPerMinDeltas
	String lane
	Map<String, Double> creepsPerMinDeltas
	Map<String, Double> goldPerMinDeltas
}
