package de.aschallenberg.communication.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.communication.dto.GameData;
import de.aschallenberg.communication.messages.Payload;
import lombok.Data;
import lombok.NonNull;

@Data
public class GameStartPayload implements Payload {
	private final GameData gameData;

	@JsonCreator
	public GameStartPayload(@NonNull @JsonProperty("gameData") GameData gameData) {
		this.gameData = gameData;
	}
}
