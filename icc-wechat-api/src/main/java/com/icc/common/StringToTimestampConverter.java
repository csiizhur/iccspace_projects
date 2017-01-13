package com.icc.common;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringToTimestampConverter implements Converter<String, Date> {
    private static final Logger logger        = LoggerFactory
                                                      .getLogger(StringToTimestampConverter.class);

    @Override
    public Timestamp convert(String source) {
        if (StringUtils.isEmpty(source))
            return null;
        try {
           return Timestamp.valueOf(source);
        } catch (Exception e) {
            logger.error("SIMPLE_FORMAT parse error source:" + source);
        }
        return null;
    }
}
