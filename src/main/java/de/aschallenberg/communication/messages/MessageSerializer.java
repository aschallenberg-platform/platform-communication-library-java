package de.aschallenberg.communication.messages;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MessageSerializer extends JsonSerializer<Message> {
	@Override
	public void serialize(final Message value, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		value.validate();

		gen.writeStartObject();

		gen.writeObjectField("meta", value.getMeta());
		gen.writeStringField("payloadType", value.getPayloadType());
		gen.writeObjectField("payload", value.getPayload());

		gen.writeEndObject();
	}
}
