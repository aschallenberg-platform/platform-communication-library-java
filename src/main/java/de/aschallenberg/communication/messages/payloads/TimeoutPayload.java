package de.aschallenberg.communication.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.communication.dto.BotData;
import de.aschallenberg.communication.messages.Payload;
import lombok.Data;
import lombok.NonNull;

@Data
public class TimeoutPayload implements Payload {
	private final BotData timedOutBot;

	@JsonCreator
	public TimeoutPayload(@NonNull @JsonProperty("timedOutBot") BotData timedOutBot) {
		this.timedOutBot = timedOutBot;
	}
}
