package de.aschallenberg.middleware.messages;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.aschallenberg.middleware.messages.payloads.*;
import de.aschallenberg.middleware.messages.payloads.empty.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(using = MessageSerializer.class)
public class Message {
	private Meta meta;

	private String payloadType;

	@JsonTypeInfo(
			use = JsonTypeInfo.Id.NAME,
			include = JsonTypeInfo.As.EXTERNAL_PROPERTY,  // wichtig: nimmt Typ aus dem "type"-Feld
			property = "payloadType"
	)
	@JsonSubTypes({
			@JsonSubTypes.Type(value = ErrorPayload.class, name = MessageTypes.ERROR),
			@JsonSubTypes.Type(value = BotClientDisconnectPayload.class, name = MessageTypes.BOT_CLIENT_DISCONNECTED),
			@JsonSubTypes.Type(value = RegisterRequestPayload.class, name = MessageTypes.REGISTER_REQUEST),
			@JsonSubTypes.Type(value = RegisterResponsePayload.class, name = MessageTypes.REGISTER_RESPONSE),
			@JsonSubTypes.Type(value = LogPayload.class, name = MessageTypes.LOG),
			@JsonSubTypes.Type(value = LobbyJoinPayload.class, name = MessageTypes.LOBBY_JOIN),
			@JsonSubTypes.Type(value = LobbyStartPayload.class, name = MessageTypes.LOBBY_START),
			@JsonSubTypes.Type(value = LobbyInterruptPayload.class, name = MessageTypes.LOBBY_INTERRUPT),
			@JsonSubTypes.Type(value = LobbyFinishedPayload.class, name = MessageTypes.LOBBY_FINISHED),
			@JsonSubTypes.Type(value = GameStartPayload.class, name = MessageTypes.GAME_START),
			@JsonSubTypes.Type(value = GameInterruptPayload.class, name = MessageTypes.GAME_INTERRUPT),
			@JsonSubTypes.Type(value = GameFinishedPayload.class, name = MessageTypes.GAME_FINISHED),
			@JsonSubTypes.Type(value = StageStartPayload.class, name = MessageTypes.STAGE_START),
			@JsonSubTypes.Type(value = StageFinishedPayload.class, name = MessageTypes.STAGE_FINISHED),
			@JsonSubTypes.Type(value = GameUpdatePayload.class, name = MessageTypes.GAME_UPDATE),
			@JsonSubTypes.Type(value = MovePayload.class, name = MessageTypes.MOVE),
			@JsonSubTypes.Type(value = DisqualifyPayload.class, name = MessageTypes.DISQUALIFY)

	})
	private Payload payload;

	@JsonCreator
	public Message(
			@JsonProperty("meta") Meta meta,
			@JsonProperty("payloadType") String payloadType,
			@JsonProperty("payload") Payload payload
	) {
		this.meta = meta;
		this.payloadType = payloadType;
		this.payload = payload;

		try {
			validate();
		} catch (JsonParseException e) {
			throw new IllegalArgumentException("Could not map message", e);
		}
	}

	public void validate() throws JsonParseException {
		if (payloadType == null) {
			throw new JsonParseException("payloadType must not be null");
		} else if(payload == null) {
			throw new JsonParseException("payload must not be null");
		}
	}
}
