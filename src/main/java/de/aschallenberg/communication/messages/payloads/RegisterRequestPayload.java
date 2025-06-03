package de.aschallenberg.communication.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.communication.messages.Payload;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class RegisterRequestPayload implements Payload {
	private final UUID token;

	@JsonCreator
	public RegisterRequestPayload(@NonNull @JsonProperty("token") UUID token) {
		this.token = token;
	}
}
