package de.aschallenberg.middleware.dto.messages;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.aschallenberg.middleware.dto.BotData;
import de.aschallenberg.middleware.dto.GameData;
import de.aschallenberg.middleware.messages.Message;
import de.aschallenberg.middleware.messages.MessageFactory;
import de.aschallenberg.middleware.messages.Meta;
import de.aschallenberg.middleware.messages.Payload;
import de.aschallenberg.middleware.messages.payloads.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MessageSerializationTest {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@SneakyThrows
	public static void assertSerialization(Message original) {
		assertDoesNotThrow(() -> objectMapper.writeValueAsString(original));
		String json = objectMapper.writeValueAsString(original);

		System.out.println(json);

		assertDoesNotThrow(() -> objectMapper.readValue(json, Message.class));
		Message deserialized = objectMapper.readValue(json, Message.class);

		assertEquals(original.getPayloadType(), deserialized.getPayloadType());
	}

	BotData botData1, botData2;
	List<BotData> botDataList;
	Message messageSender, messageRecipients;
	ErrorPayload errorPayload;

	@BeforeEach
	void setUp() {
		botData1 = new BotData("Bot1", "Easy");
		botData2 = new BotData("Bot2", "Medium");
		botDataList = List.of(botData1, botData2);

		errorPayload = new ErrorPayload("Something went wrong");

		messageSender = MessageFactory.createMessage(errorPayload, botData1);
		messageRecipients = MessageFactory.createMessage(errorPayload, botDataList);
	}

	@Test
	void testSerializeMessage_MessageEmpty() {
		Message message = new Message();
		assertThrows(JsonParseException.class, () -> objectMapper.writeValueAsString(message));
	}

	@Test
	void testSerializeMessage_PayloadTypeNull() {
		messageSender.setPayloadType(null);
		assertThrows(JsonParseException.class, () -> objectMapper.writeValueAsString(messageSender));
	}

	@Test
	void testSerializeMessage_MetaNull() {
		messageSender.setMeta(null);
		assertSerialization(messageSender);
	}

	@Test
	void testSerializeMessage_MetaEmpty() {
		messageSender.setMeta(new Meta());
		assertSerialization(messageSender);
	}

	@Test
	void testSerializeMessage_MetaSender() {
		assertSerialization(messageSender);
	}

	@Test
	void testSerializeMessage_MetaRecipients() {
		assertSerialization(messageRecipients);
	}

	@Test
	void testSerializeMessage_PayloadNull() {
		messageSender.setPayload(null);
		assertThrows(JsonParseException.class, () -> objectMapper.writeValueAsString(messageSender));
	}

	@ParameterizedTest
	@MethodSource("validPayloadsProvider")
	void testSerializeMessage_Payloads(Payload payload) {
		Message message = MessageFactory.createMessage(payload);
		assertSerialization(message);
	}

	static Stream<Payload> validPayloadsProvider() {
		BotData botData = new BotData("Bot1", "Easy");
		GameData gameData = new GameData();
		UUID uuid = UUID.randomUUID();
		Map<BotData, Integer> scores = Map.of(botData, 42);

		return Stream.of(
				new ErrorPayload("Some error"),
				new BotClientDisconnectPayload(botData),
				new RegisterRequestPayload(uuid),
				new RegisterResponsePayload(),
				new LogPayload("This is a log message."),
				new LobbyJoinPayload(UUID.randomUUID()),
				new LobbyStartPayload(),
				new LobbyInterruptPayload(),
				new LobbyFinishedPayload(),
				new GameStartForBotsPayload(gameData, botData),
				new GameStartPayload(gameData),
				new GameInterruptPayload(),
				new GameFinishedPayload(scores),
				new StageStartPayload(),
				new StageFinishedPayload(true),
				new GameUpdatePayload<>("Some game update"),
				new MovePayload<>("Some move"),
				new DisqualifyPayload(botData),
				new TimeoutPayload(botData)
		);
	}
}
