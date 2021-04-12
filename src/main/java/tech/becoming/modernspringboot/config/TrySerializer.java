package tech.becoming.modernspringboot.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.vavr.control.Try;
import lombok.SneakyThrows;

import java.io.IOException;

public class TrySerializer extends StdSerializer<Try> {

    public TrySerializer(JavaType type) {
        super(type);
    }

    @SneakyThrows
    @Override
    public void serialize(Try value,
                          JsonGenerator jgen,
                          SerializerProvider provider) {
        var object = value.getOrElseThrow(() -> new IOException(value.getCause()));
        jgen.writeObject(object);
    }

}