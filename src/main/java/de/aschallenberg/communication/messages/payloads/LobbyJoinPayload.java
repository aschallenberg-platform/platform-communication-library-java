package de.aschallenberg.communication.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.communication.messages.Payload;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class LobbyJoinPayload implements Payload {
	private final UUID lobbyCode;

	@JsonCreator
	public LobbyJoinPayload(@NonNull @JsonProperty("lobbyCode") UUID lobbyCode) {
		this.lobbyCode = lobbyCode;
	}
}
