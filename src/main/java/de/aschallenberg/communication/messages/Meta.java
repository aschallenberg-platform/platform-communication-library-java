package de.aschallenberg.communication.messages;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.aschallenberg.communication.dto.BotData;
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
