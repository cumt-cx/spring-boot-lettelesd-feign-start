package com.littlesd.feign.support;

import feign.Feign;
import feign.Request;
import feign.RequestTemplate;
import feign.Target;
import org.springframework.beans.factory.FactoryBean;

public class FeignFactoryBean<T> implements FactoryBean<T> {

    private Class<T> feignInteface;

    private FeignConfig feignConfig;

    private String feignClientName;

    public FeignFactoryBean(){

    }

    public FeignFactoryBean(Class<T> feignInteface){this.feignInteface = feignInteface ;}

    public Class<T> getFeignInteface() {
        return feignInteface;
    }

    public void setFeignInteface(Class<T> feignInteface) {
        this.feignInteface = feignInteface;
    }

    public FeignConfig getFeignConfig() {
        return feignConfig;
    }

    public void setFeignConfig(FeignConfig feignConfig) {
        this.feignConfig = feignConfig;
    }

    public String getFeignClientName() {
        return feignClientName;
    }

    public void setFeignClientName(String feignClientName) {
        this.feignClientName = feignClientName;
    }

    @Override
    public T getObject() throws Exception {
        FeignClientConfig feignClientConfig = feignConfig.getFeignClientConfig(feignClientName);

        Feign.Builder builder = Feign.builder();
        if(feignClientConfig.getLogLevel()!=null) {
            builder.logLevel(feignClientConfig.getLogLevel());
        }
        if(feignClientConfig.getClient()!=null) {
            builder.client(feignClientConfig.getClient());
        }
        if(feignClientConfig.getContract()!=null) {
            builder.contract(feignClientConfig.getContract());
        }
        if(feignClientConfig.getEncoder()!=null) {
            builder.encoder(feignClientConfig.getEncoder());
        }
        if(feignClientConfig.getDecoder()!=null) {
            builder.decoder(feignClientConfig.getDecoder());
        }
        if(feignClientConfig.getRequestInterceptors()!=null) {
            builder.requestInterceptors(feignClientConfig.getRequestInterceptors());
        }

        return builder.target(new Target.HardCodedTarget<T>(feignInteface,feignClientConfig.getBaseUrl()){
            @Override
            public Request apply(RequestTemplate input) {
                feignClientConfig.getRequestHeaders().forEach((name,value) ->{
                    input.header(name,value);
                });
                return super.apply(input);
            }
        });

    }

    @Override
    public Class<?> getObjectType() {
        return feignInteface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
