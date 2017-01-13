package com.icc.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringToDateConverter implements Converter<String, Date> {
    private static final Logger logger        = LoggerFactory
                                                      .getLogger(StringToDateConverter.class);
    private static final String DATE_FORMAT   = "yyyy-MM-dd";
    private static final String SIMPLE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Date convert(String source) {
        if (StringUtils.isEmpty(source))
            return null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat(SIMPLE_FORMAT);
            return sf.parse(source);
        } catch (Exception e) {
            logger.error("SIMPLE_FORMAT parse error source:" + source);
        }
        try {
            SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
            return sf.parse(source);
        } catch (Exception e) {
            logger.error("DATE_FORMAT parse error source:" + source);
        }
        return null;
    }
}
