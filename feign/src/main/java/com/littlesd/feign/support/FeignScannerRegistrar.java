package com.littlesd.feign.support;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

public class FeignScannerRegistrar implements ImportBeanDefinitionRegistrar,ResourceLoaderAware{

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes[] annotationAttributes = null;
        if(importingClassMetadata.hasAnnotation(FeignClient.class.getName())){
            AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(FeignClient.class.getName
                    ()));
            annotationAttributes = new AnnotationAttributes[]{annoAttrs};
        }else if(importingClassMetadata.hasAnnotation(FeignClients.class.getName())){
            AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(FeignClients.class.getName
                    ()));
            annotationAttributes = annoAttrs.getAnnotationArray("value");
        }
        for(AnnotationAttributes annoArrrs:annotationAttributes){
            ClassPathFeignScanner scanner = new ClassPathFeignScanner(registry);
            scanner.registerDefaultFilters();
            if(resourceLoader != null){
                scanner.setResourceLoader(resourceLoader);
            }
            String name = annoArrrs.getString("name");
            String[] basePackages = annoArrrs.getStringArray("scanPackages");
            scanner.doScan(name,basePackages);
        }

    }
}
