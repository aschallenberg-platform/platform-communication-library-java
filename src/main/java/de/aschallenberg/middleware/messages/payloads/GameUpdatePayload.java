package de.aschallenberg.middleware.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.middleware.messages.Payload;
import lombok.Data;
import lombok.NonNull;

@Data
public class GameUpdatePayload<T> implements Payload {
	private final T value;

	@JsonCreator
	public GameUpdatePayload(@NonNull @JsonProperty("value") T value) {
		this.value = value;
	}
}
