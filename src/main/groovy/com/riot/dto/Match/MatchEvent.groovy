package com.riot.dto.Match

class MatchEvent
{
	String eventType
	String towerType
	int teamId
	String ascendedType
	int killerId
	String levelUpType
	String pointCaptured
	List<Integer> assistingParticipantIds
	String wardType
	String monsterType
	String type //Legal values: CHAMPION_KILL, WARD_PLACED, WARD_KILL, BUILDING_KILL, ELITE_MONSTER_KILL, ITEM_PURCHASED, ITEM_SOLD, ITEM_DESTROYED, ITEM_UNDO, SKILL_LEVEL_UP, ASCENDED_EVENT, CAPTURE_POINT, PORO_KING_SUMMON
	int skillSlot
	int victimId
	long timestamp
	int afterId
	String monsterSubType
	String laneType
	int itemId
	int participantId
	String buildingType
	int creatorId
	MatchPosition position
	int beforeId
}
