package com.riot.dto.Match

class MatchEvent
{
	String laneType
	int skillSlot
	String ascendedType
	int creatorId
	int afterId
	String eventType
	String type //Legal values: CHAMPION_KILL, WARD_PLACED, WARD_KILL, BUILDING_KILL, ELITE_MONSTER_KILL, ITEM_PURCHASED, ITEM_SOLD, ITEM_DESTROYED, ITEM_UNDO, SKILL_LEVEL_UP, ASCENDED_EVENT, CAPTURE_POINT, PORO_KING_SUMMON
	String levelUpType
	String wardType
	int participantId
	String towerType
	int itemId
	int beforeId
	String pointCaptured
	String monsterType
	String monsterSubType
	int teamId
	MatchPosition position
	int killerId
	long timestamp
	List<Integer> assistingParticipantIds
	String buildingType
	int victimId
}
