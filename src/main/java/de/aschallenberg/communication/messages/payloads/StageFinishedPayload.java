package de.aschallenberg.communication.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.communication.messages.Payload;
import lombok.Data;
import lombok.NonNull;

@Data
public class StageFinishedPayload implements Payload {
	private final boolean qualifiedForNextRound;

	@JsonCreator
	public StageFinishedPayload(@NonNull @JsonProperty("qualifiedForNextRound") boolean qualifiedForNextRound) {
		this.qualifiedForNextRound = qualifiedForNextRound;
	}
}
