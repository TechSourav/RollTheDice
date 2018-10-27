package com.sourav.dice.mappings.service;

import com.sourav.dice.mappings.json.Mapping;
import com.sourav.dice.util.ResourceUtil;
import org.springframework.stereotype.Component;

@Component
public class FieldMappingServiceImpl implements FieldMappingService {

    private Mapping mappings;

    @Override
    public Mapping getMappings() {

        if (mappings == null) {
            mappings = Mapping.buildMapping((ResourceUtil.getResourceAsStream("/mapping.json")));
        }
        return mappings;

    }
}
