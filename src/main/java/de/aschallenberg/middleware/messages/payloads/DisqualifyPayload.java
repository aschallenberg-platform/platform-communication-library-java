package de.aschallenberg.middleware.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.middleware.dto.BotData;
import de.aschallenberg.middleware.messages.Payload;
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
