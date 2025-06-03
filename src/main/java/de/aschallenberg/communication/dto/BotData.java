package de.aschallenberg.communication.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class BotData implements Serializable {
	private String name;
	private String ownerName;
}
