package com.sourav.dice.mappings.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldMapping {

    @JsonProperty("description")
    private String description;

    @JsonProperty("startDelimiter")
    private String startDelimiter;

    @JsonProperty("endDelimiter")
    private String endDelimiter;

}
