package de.aschallenberg.communication.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.communication.messages.Payload;
import lombok.Data;
import lombok.NonNull;

@Data
public class ErrorPayload implements Payload {
	private final String errorMessage;

	@JsonCreator
	public ErrorPayload(@NonNull @JsonProperty("errorMessage") String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
