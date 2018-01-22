package com.littlesd.demo.feign.support;

import feign.Client;
import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FeignConfig {

    private Logger.Level logLevel;

    private Contract contract;

    private Client client;

    private Encoder encoder;

    private Decoder decoder;

    private Map<String,String> requestHeaders = new LinkedHashMap<>();

    private List<RequestInterceptor> requestInterceptors = new ArrayList<>();

    private Map<String, FeignClientConfig> feignClientConfigs = new LinkedHashMap<>();

    public Logger.Level getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Logger.Level logLevel) {
        this.logLevel = logLevel;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    public Decoder getDecoder() {
        return decoder;
    }

    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public List<RequestInterceptor> getRequestInterceptors() {
        return requestInterceptors;
    }

    public void setRequestInterceptors(List<RequestInterceptor> requestInterceptors) {
        this.requestInterceptors = requestInterceptors;
    }

    public Map<String, FeignClientConfig> getFeignClientConfigs() {
        return feignClientConfigs;
    }

    public void setFeignClientConfigs(Map<String, FeignClientConfig> feignClientConfigs) {
        this.feignClientConfigs = feignClientConfigs;
    }

    public void addFeignClientConfig(String name,FeignClientConfig feignClientConfig){
        if(feignClientConfig.getLogLevel()==null && logLevel!=null) {
            feignClientConfig.setLogLevel(logLevel);
        }
        if(feignClientConfig.getClient()==null && client!=null) {
            feignClientConfig.setClient(client);
        }
        if(feignClientConfig.getContract()==null && contract!=null) {
            feignClientConfig.setContract(contract);
        }
        if(feignClientConfig.getEncoder()==null && encoder!=null) {
            feignClientConfig.setEncoder(encoder);
        }
        if(feignClientConfig.getDecoder()==null && decoder!=null) {
            feignClientConfig.setDecoder(decoder);
        }
        if(feignClientConfig.getRequestHeaders()==null && requestHeaders!=null) {
            feignClientConfig.setRequestHeaders(requestHeaders);
        }
        if(feignClientConfig.getRequestInterceptors()==null && requestInterceptors!=null) {
            feignClientConfig.setRequestInterceptors(requestInterceptors);
        }
        this.feignClientConfigs.put(name, feignClientConfig);
    }

    public FeignClientConfig getFeignClientConfig(String name){
        return this.feignClientConfigs.get(name);
    }

}
