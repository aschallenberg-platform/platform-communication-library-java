package de.aschallenberg.middleware.messages;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageTypes {
	public static final String ERROR = "ERROR";
	public static final String BOT_CLIENT_DISCONNECTED = "BOT_CLIENT_DISCONNECTED";
	public static final String REGISTER_REQUEST = "REGISTER_REQUEST";
	public static final String REGISTER_RESPONSE = "REGISTER_RESPONSE";
	public static final String LOG = "LOG";
	public static final String LOBBY_JOIN = "LOBBY_JOIN";
	public static final String LOBBY_START = "LOBBY_START";
	public static final String LOBBY_INTERRUPT = "LOBBY_INTERRUPT";
	public static final String LOBBY_FINISHED = "LOBBY_FINISHED";
	public static final String GAME_START = "GAME_START";
	public static final String GAME_START_FOR_BOTS = "GAME_START_FOR_BOTS";
	public static final String GAME_INTERRUPT = "GAME_INTERRUPT";
	public static final String GAME_FINISHED = "GAME_FINISHED";
	public static final String STAGE_START = "STAGE_START";
	public static final String STAGE_FINISHED = "STAGE_FINISHED";
	public static final String GAME_UPDATE = "GAME_UPDATE";
	public static final String MOVE = "MOVE";
	public static final String DISQUALIFY = "DISQUALIFY";
}
