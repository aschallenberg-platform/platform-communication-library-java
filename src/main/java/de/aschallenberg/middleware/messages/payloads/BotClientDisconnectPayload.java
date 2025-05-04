package de.aschallenberg.middleware.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.middleware.dto.BotData;
import de.aschallenberg.middleware.messages.Payload;
import lombok.Data;
import lombok.NonNull;

@Data
public class BotClientDisconnectPayload implements Payload {
	private final BotData disconnectedBot;

	@JsonCreator
	public BotClientDisconnectPayload(@NonNull @JsonProperty("disconnectedBot") BotData disconnectedBot) {
		this.disconnectedBot = disconnectedBot;
	}
}
