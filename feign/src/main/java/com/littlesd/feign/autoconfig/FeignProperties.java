package com.littlesd.feign.autoconfig;

import feign.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(prefix = FeignProperties.FEIGN_PREFIX)
public class FeignProperties {

    public static final  String FEIGN_PREFIX = "feign";

    private String baseUrl;

    private Logger.Level level = Logger.Level.NONE;

    private Map<String, String> requestHeaders = new LinkedHashMap<>();



}
