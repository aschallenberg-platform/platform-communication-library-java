package de.aschallenberg.communication.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.communication.dto.BotData;
import de.aschallenberg.communication.dto.GameData;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GameStartForBotsPayload extends GameStartPayload {
	private final BotData ownBotData;

	@JsonCreator
	public GameStartForBotsPayload(
			@NonNull @JsonProperty("gameData") GameData gameData,
			@NonNull @JsonProperty("ownBotData") BotData ownBotData
	) {
		super(gameData);
		this.ownBotData = ownBotData;
	}
}
