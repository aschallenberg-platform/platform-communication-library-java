package de.aschallenberg.communication.messages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.aschallenberg.communication.dto.BotData;
import de.aschallenberg.communication.messages.Payload;
import lombok.Data;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

@Data
public class GameFinishedPayload implements Payload {
	private final Map<Integer, BotData> bots;
	private final Map<Integer, Integer> scores;

	public GameFinishedPayload(@NonNull Map<BotData, Integer> scores) {
		this.bots = HashMap.newHashMap(scores.size());
		this.scores = HashMap.newHashMap(scores.size());

		scores.forEach((bot, score) -> {
			int hashCode = bot.hashCode();

			this.bots.put(hashCode, bot);
			this.scores.put(hashCode, score);
		});
	}

	@JsonCreator
	public GameFinishedPayload(
			@NonNull @JsonProperty("bots") Map<Integer, BotData> bots,
			@NonNull @JsonProperty("scores") Map<Integer, Integer> scores
	) {
		this.bots = bots;
		this.scores = scores;
	}

	@JsonIgnore
	public Map<BotData, Integer> getScoresByBots() {
		Map<BotData, Integer> map = HashMap.newHashMap(scores.size());
		scores.forEach((botHash, score) -> map.put(bots.get(botHash), score));

		return map;
	}
}
