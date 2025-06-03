package de.aschallenberg.communication.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.communication.dto.BotData;
import de.aschallenberg.communication.messages.Payload;
import lombok.Data;
import lombok.NonNull;

@Data
public class DisqualifyPayload implements Payload {
	private final BotData disqualifiedBot;

	@JsonCreator
	public DisqualifyPayload(@NonNull @JsonProperty("disqualifiedBot") BotData disqualifiedBot) {
		this.disqualifiedBot = disqualifiedBot;
	}
}
