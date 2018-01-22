package com.littlesd.demo.feign.autoconfig;

import feign.Client;
import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = FeignProperties.FEIGN_PREFIX)
public class FeignProperties {

    public static final  String FEIGN_PREFIX = "feign";

    private String baseUrl;

    private Logger.Level logLevel = Logger.Level.NONE;

    private Map<String, String> requestHeaders = new LinkedHashMap<>();

    private Class<? extends Contract> contractClass =  JAXRSContract.class;

    private Class<? extends Client> clientClass = ApacheHttpClient.class;

    private Class<? extends Encoder> encoderClass = JacksonEncoder.class;

    private Class<? extends Decoder> decoderClass = JacksonDecoder.class;

    private Map<String,FeignClientProperties> clients = new LinkedHashMap<>();

    public static String getFeignPrefix() {
        return FEIGN_PREFIX;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Logger.Level getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Logger.Level logLevel) {
        this.logLevel = logLevel;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public Class<? extends Contract> getContractClass() {
        return contractClass;
    }

    public void setContractClass(Class<? extends Contract> contractClass) {
        this.contractClass = contractClass;
    }

    public Class<? extends Client> getClientClass() {
        return clientClass;
    }

    public void setClientClass(Class<? extends Client> clientClass) {
        this.clientClass = clientClass;
    }

    public Class<? extends Encoder> getEncoderClass() {
        return encoderClass;
    }

    public void setEncoderClass(Class<? extends Encoder> encoderClass) {
        this.encoderClass = encoderClass;
    }

    public Class<? extends Decoder> getDecoderClass() {
        return decoderClass;
    }

    public void setDecoderClass(Class<? extends Decoder> decoderClass) {
        this.decoderClass = decoderClass;
    }

    public Map<String, FeignClientProperties> getClients() {
        return clients;
    }

    public void setClients(Map<String, FeignClientProperties> clients) {
        this.clients = clients;
    }

    public static class FeignClientProperties {

        private String baseUrl;

        private Logger.Level logLevel;

        private Map<String,String> requestHeader;

        private Class<? extends Contract> contractClass =  JAXRSContract.class;

        private Class<? extends Client> clientClass = ApacheHttpClient.class;

        private Class<? extends Encoder> encoderClass = JacksonEncoder.class;

        private Class<? extends Decoder> decoderClass = JacksonDecoder.class;

        private List<Class<? extends RequestInterceptor>> requestInterceptorClasses;

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public Logger.Level getLogLevel() {
            return logLevel;
        }

        public void setLogLevel(Logger.Level logLevel) {
            this.logLevel = logLevel;
        }

        public Map<String, String> getRequestHeader() {
            return requestHeader;
        }

        public void setRequestHeader(Map<String, String> requestHeader) {
            this.requestHeader = requestHeader;
        }

        public Class<? extends Contract> getContractClass() {
            return contractClass;
        }

        public void setContractClass(Class<? extends Contract> contractClass) {
            this.contractClass = contractClass;
        }

        public Class<? extends Client> getClientClass() {
            return clientClass;
        }

        public void setClientClass(Class<? extends Client> clientClass) {
            this.clientClass = clientClass;
        }

        public Class<? extends Encoder> getEncoderClass() {
            return encoderClass;
        }

        public void setEncoderClass(Class<? extends Encoder> encoderClass) {
            this.encoderClass = encoderClass;
        }

        public Class<? extends Decoder> getDecoderClass() {
            return decoderClass;
        }

        public void setDecoderClass(Class<? extends Decoder> decoderClass) {
            this.decoderClass = decoderClass;
        }

        public List<Class<? extends RequestInterceptor>> getRequestInterceptorClasses() {
            return requestInterceptorClasses;
        }

        public void setRequestInterceptorClasses(List<Class<? extends RequestInterceptor>> requestInterceptorClasses) {
            this.requestInterceptorClasses = requestInterceptorClasses;
        }
    }


}
