package com.littlesd.demo.feign.autoconfig;

import com.littlesd.demo.feign.support.FeignClientConfig;
import com.littlesd.demo.feign.support.FeignConfig;
import feign.Feign;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

@Configuration
@ConditionalOnClass(Feign.class)
@EnableConfigurationProperties(FeignProperties.class)
@ConditionalOnProperty(
        prefix = "feign",
        value = "enabled",
        matchIfMissing = true
)
public class FeignAutoConfiguration {

    @Autowired
    private FeignProperties feignProperties;

    @Bean(value = "feignConfig")
    public FeignConfig feignConfig(){
        FeignConfig feignConfig = new FeignConfig();

        feignConfig.setLogLevel(feignProperties.getLogLevel());
        feignConfig.setRequestHeaders(feignProperties.getRequestHeaders());
        feignConfig.setClient(BeanUtils.instantiate(feignProperties.getClientClass()));
        feignConfig.setContract(BeanUtils.instantiate(feignProperties.getContractClass()));
        feignConfig.setEncoder(BeanUtils.instantiate(feignProperties.getEncoderClass()));
        feignConfig.setDecoder(BeanUtils.instantiate(feignProperties.getDecoderClass()));

        feignProperties.getClients().forEach((name,feignClientProperties) ->{
            Assert.hasText(feignClientProperties.getBaseUrl(),"client Property baseUrl can not be empty");

            FeignClientConfig feignClientConfig = new FeignClientConfig();
            feignClientConfig.setBaseUrl(feignClientProperties.getBaseUrl());
            feignClientConfig.setRequestHeaders(feignClientConfig.getRequestHeaders());
            feignClientConfig.setClient(BeanUtils.instantiate(feignClientProperties.getClientClass()));
            feignClientConfig.setContract(BeanUtils.instantiate(feignClientProperties.getContractClass()));
            feignClientConfig.setEncoder(BeanUtils.instantiate(feignClientProperties.getEncoderClass()));
            feignClientConfig.setDecoder(BeanUtils.instantiate(feignClientProperties.getDecoderClass()));

            feignConfig.addFeignClientConfig(name,feignClientConfig);
        });

        return feignConfig;
    }

}
