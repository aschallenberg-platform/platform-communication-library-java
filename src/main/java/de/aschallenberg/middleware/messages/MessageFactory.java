package de.aschallenberg.middleware.messages;

import de.aschallenberg.middleware.dto.BotData;
import de.aschallenberg.middleware.messages.payloads.*;
import de.aschallenberg.middleware.messages.payloads.empty.*;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MessageFactory {

	public static Message createMessage(@NonNull Payload payload) {
		return createMessageWithMeta(payload, null);
	}

	public static Message createMessage(@NonNull Payload payload, @NonNull BotData sender) {
		Meta meta = new Meta();
		meta.setSender(sender);
		return createMessageWithMeta(payload, meta);
	}

	public static Message createMessage(@NonNull Payload payload, @NonNull List<BotData> recipients) {
		Meta meta = new Meta();
		meta.setRecipients(recipients);
		return createMessageWithMeta(payload, meta);
	}

	private static Message createMessageWithMeta(Payload payload, Meta meta) {
		Message message = new Message();
		message.setMeta(meta);
		message.setPayload(payload);

		switch (payload) {
			case ErrorPayload pl -> message.setPayloadType(MessageTypes.ERROR);
			case BotClientDisconnectPayload pl -> message.setPayloadType(MessageTypes.BOT_CLIENT_DISCONNECTED);
			case RegisterRequestPayload pl -> message.setPayloadType(MessageTypes.REGISTER_REQUEST);
			case RegisterResponsePayload pl -> message.setPayloadType(MessageTypes.REGISTER_RESPONSE);
			case LogPayload pl -> message.setPayloadType(MessageTypes.LOG);
			case LobbyJoinPayload pl -> message.setPayloadType(MessageTypes.LOBBY_JOIN);
			case LobbyStartPayload pl -> message.setPayloadType(MessageTypes.LOBBY_START);
			case LobbyInterruptPayload pl -> message.setPayloadType(MessageTypes.LOBBY_INTERRUPT);
			case LobbyFinishedPayload pl -> message.setPayloadType(MessageTypes.LOBBY_FINISHED);
			case GameStartForBotsPayload pl -> message.setPayloadType(MessageTypes.GAME_START_FOR_BOTS);
			case GameStartPayload pl -> message.setPayloadType(MessageTypes.GAME_START);
			case GameInterruptPayload pl -> message.setPayloadType(MessageTypes.GAME_INTERRUPT);
			case GameFinishedPayload pl -> message.setPayloadType(MessageTypes.GAME_FINISHED);
			case StageStartPayload pl -> message.setPayloadType(MessageTypes.STAGE_START);
			case StageFinishedPayload pl -> message.setPayloadType(MessageTypes.STAGE_FINISHED);
			case GameUpdatePayload<?> pl -> message.setPayloadType(MessageTypes.GAME_UPDATE);
			case MovePayload<?> pl -> message.setPayloadType(MessageTypes.MOVE);
			case DisqualifyPayload pl -> message.setPayloadType(MessageTypes.DISQUALIFY);
			default -> throw new IllegalStateException("Unexpected payload type: " + payload);
		}

		return message;
	}
}
