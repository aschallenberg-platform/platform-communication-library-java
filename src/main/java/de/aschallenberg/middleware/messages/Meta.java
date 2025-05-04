package de.aschallenberg.middleware.messages;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.aschallenberg.middleware.dto.BotData;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meta {
	private BotData sender;
	private List<BotData> recipients;
}
