package de.aschallenberg.middleware.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameData implements Serializable {
	private String name;
	private String module;
	private String version;
	private Map<String, String> settings;
	private List<BotData> bots;
}
