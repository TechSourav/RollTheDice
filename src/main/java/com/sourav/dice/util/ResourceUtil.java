package com.sourav.dice.util;

import org.apache.commons.validator.GenericValidator;
import org.springframework.util.Assert;

import java.io.InputStream;

import org.apache.log4j.Logger;

public interface ResourceUtil {

    Logger log = Logger.getLogger(ResourceUtil.class);

    static InputStream getResourceAsStream(String path) {
        Assert.isTrue(!GenericValidator.isBlankOrNull(path), "Resource Path is null or empty");

        log.info(String.format("Obtaining Resource Stream for Path: %s", path));
        InputStream resourceStream = ResourceUtil.class.getResourceAsStream(path);
        Assert.notNull(resourceStream, String.format("Could not get Resource Stream for %s", path));

        return ResourceUtil.class.getResourceAsStream(path);
    }
}
