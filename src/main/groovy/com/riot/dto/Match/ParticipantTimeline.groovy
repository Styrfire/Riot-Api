package com.riot.dto.Match

class ParticipantTimeline
{
	String lane
	int participantId
	Map<String, Double> csDiffPerMinDeltas
	Map<String, Double> goldPerMinDeltas
	Map<String, Double> xpDiffPerMinDeltas
	Map<String, Double> creepsPerMinDeltas
	Map<String, Double> xpPerMinDeltas
	String role
	Map<String, Double> damageTakenDiffPerMinDeltas
	Map<String, Double> damageTakenPerMinDeltas
}
