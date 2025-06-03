package de.aschallenberg.communication.dto.messages;

import de.aschallenberg.communication.dto.BotData;
import de.aschallenberg.communication.messages.MessageFactory;
import de.aschallenberg.communication.messages.payloads.ErrorPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MessageFactoryTest {

	ErrorPayload errorPayload;

	@BeforeEach
	void setUp() {
		errorPayload = new ErrorPayload("Something went wrong");
	}

	@Test
	void testCreateMessage_PayloadNull() {
		assertThrows(NullPointerException.class, () -> MessageFactory.createMessage(null));
	}

	@Test
	void testCreateMessage_PayloadNotNull() {
		assertDoesNotThrow(() -> MessageFactory.createMessage(errorPayload));
	}

	@Test
	void testCreateMessage_SenderNull() {
		BotData sender = null;
		assertThrows(NullPointerException.class, () -> MessageFactory.createMessage(errorPayload, sender));
	}

	@Test
	void testCreateMessage_SenderNotNull() {
		assertDoesNotThrow(() -> MessageFactory.createMessage(new ErrorPayload(""), new BotData("Bot", "Easy")));
	}

	@Test
	void testCreateMessage_RecipientsNull() {
		List<BotData> recipients = null;
		assertThrows(NullPointerException.class, () -> MessageFactory.createMessage(errorPayload, recipients));
	}

	@Test
	void testCreateMessage_RecipientsEmpty() {
		List<BotData> recipients = List.of();
		assertDoesNotThrow(() -> MessageFactory.createMessage(errorPayload, recipients));
	}

	@Test
	void testCreateMessage_RecipientsNotEmpty() {
		List<BotData> recipients = List.of(new BotData("Bot1", "Easy"), new BotData("Bot2", "Medium"));
		assertDoesNotThrow(() -> MessageFactory.createMessage(errorPayload, recipients));
	}
}
