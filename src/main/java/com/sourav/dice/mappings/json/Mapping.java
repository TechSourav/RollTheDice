package com.sourav.dice.mappings.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourav.dice.exception.FieldTransformationException;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Getter
@Component
public class Mapping {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @JsonProperty("fieldMappings")
    private Map<String, FieldMapping> fieldMappings;

    public static Mapping buildMapping(InputStream stream) {
        try {
            Mapping mappings = objectMapper.readValue(stream, Mapping.class);
            return mappings;

        } catch (IOException e) {
            throw new FieldTransformationException("Could not transform input message mappings.", e);
        }
    }
}
