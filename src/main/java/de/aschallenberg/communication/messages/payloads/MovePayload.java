package de.aschallenberg.communication.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.communication.messages.Payload;
import lombok.Data;
import lombok.NonNull;

@Data
public class MovePayload<T> implements Payload {
	private final T value;

	@JsonCreator
	public MovePayload(@NonNull @JsonProperty("value") T value) {
		this.value = value;
	}
}
