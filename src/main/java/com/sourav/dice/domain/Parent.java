package com.sourav.dice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
public class Parent {

    private Integer rank;

    // modification tracking
    @JsonIgnore
    private Date createdAt;
}
