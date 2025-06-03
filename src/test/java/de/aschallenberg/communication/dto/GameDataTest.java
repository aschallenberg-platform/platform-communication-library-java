package de.aschallenberg.communication.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameDataTest {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Test
	void testToJson_withValidData_shouldSerializeCorrectly() throws JsonProcessingException {
		GameData data = GameData.builder()
				.name("TestName")
				.module("TestModule")
				.version("1.0")
				.settings(Map.of("key", "value"))
				.bots(List.of(new BotData("Bot1", "Easy")))
				.build();

		String json = MAPPER.writeValueAsString(data);
		assertNotNull(json);
		assertTrue(json.contains("TestName"));
		assertTrue(json.contains("key"));
		assertTrue(json.contains("Bot1"));
	}

	@Test
	void testFromJson_withValidJson_shouldDeserializeCorrectly() throws JsonProcessingException {
		String json = """
		{
		  "name": "TestGame",
		  "module": "TestMod",
		  "version": "1.2",
		  "settings": {
		    "difficulty": "hard"
		  },
		  "bots": [
		    {
		      "name": "BotA",
		      "ownerName": "OwnerA"
		    }
		  ]
		}
		""";

		GameData data = MAPPER.readValue(json, GameData.class);
		assertEquals("TestGame", data.getName());
		assertEquals("TestMod", data.getModule());
		assertEquals("1.2", data.getVersion());
		assertEquals("hard", data.getSettings().get("difficulty"));
	}

	@Test
	void testToJson_withNullValues_shouldNotThrow() throws JsonProcessingException {
		GameData data = GameData.builder()
				.name(null)
				.module(null)
				.version(null)
				.settings(Map.of("test", "123"))
				.bots(List.of())
				.build();

		assertDoesNotThrow(() -> MAPPER.writeValueAsString(data));
		String json = MAPPER.writeValueAsString(data);
		assertTrue(json.contains("\"name\":null"));
		assertTrue(json.contains("\"module\":null"));
		assertTrue(json.contains("\"version\":null"));
	}

	@Test
	void testFromJson_withNullFields_shouldNotThrow() throws JsonProcessingException {
		String json = """
		{
		  "name": null,
		  "module": null,
		  "version": null,
		  "settings": {},
		  "bots": []
		}
	""";

		GameData data = MAPPER.readValue(json, GameData.class);
		assertNull(data.getName());
		assertNull(data.getModule());
		assertNull(data.getVersion());
		assertTrue(data.getSettings().isEmpty());
		assertTrue(data.getBots().isEmpty());
	}

	@Test
	void testToJson_withEmptyCollections_shouldSerializeCorrectly() throws JsonProcessingException {
		GameData data = GameData.builder()
				.name("Test")
				.module("TestMod")
				.version("1.0")
				.settings(new HashMap<>())
				.bots(new ArrayList<>())
				.build();

		String json = MAPPER.writeValueAsString(data);
		assertTrue(json.contains("\"settings\":{}"));
		assertTrue(json.contains("\"bots\":[]"));
	}

	@Test
	void testRoundTripSerialization_shouldPreserveData() throws JsonProcessingException {
		Map<String, String> settings = new HashMap<>();
		settings.put("speed", "fast");

		List<BotData> bots = List.of(new BotData("BotX", "Medium"));

		GameData original = GameData.builder()
				.name("RoundTrip")
				.module("RoundMod")
				.version("2.0")
				.settings(settings)
				.bots(bots)
				.build();

		String json = MAPPER.writeValueAsString(original);
		GameData deserialized = MAPPER.readValue(json, GameData.class);

		assertEquals(original.getName(), deserialized.getName());
		assertEquals(original.getModule(), deserialized.getModule());
		assertEquals(original.getVersion(), deserialized.getVersion());
		assertEquals(original.getSettings(), deserialized.getSettings());
		assertEquals(original.getSettings().size(), deserialized.getSettings().size());
		assertEquals(original.getSettings().get("speed"), deserialized.getSettings().get("speed"));
		assertEquals(original.getBots().size(), deserialized.getBots().size());
		assertEquals(original.getBots().getFirst().getName(), deserialized.getBots().getFirst().getName());
	}
}
