package de.aschallenberg.communication.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.communication.messages.Payload;
import lombok.Data;
import lombok.NonNull;

@Data
public class LogPayload implements Payload {
	private final String message;

	@JsonCreator
	public LogPayload(@NonNull @JsonProperty("message") String message) {
		this.message = message;
	}
}
