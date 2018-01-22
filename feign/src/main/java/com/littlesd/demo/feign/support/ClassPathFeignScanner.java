package com.littlesd.demo.feign.support;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Set;

public class ClassPathFeignScanner  extends ClassPathBeanDefinitionScanner{

    public ClassPathFeignScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public  void registerDefaultFilters(){
        super.addIncludeFilter(new InterfaceTypeFilter());
    }

    public void doScan(String name, String... basePackages){
        Set<BeanDefinitionHolder>  beanDefinitionHolders = super.doScan(basePackages);
        processBeanDefinitions(name,beanDefinitionHolders);
    }

    private void processBeanDefinitions(String name, Set<BeanDefinitionHolder> beanDefinitionHolders) {
        GenericBeanDefinition beanDefinition = null;
        for(BeanDefinitionHolder beanDefinitionHolder:beanDefinitionHolders){
            beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
            beanDefinition.setBeanClass(FeignFactoryBean.class);
            beanDefinition.getPropertyValues().add("feignClientName",name);
            beanDefinition.getPropertyValues().add("feignConfig",new RuntimeBeanReference("feignConfig"));
        }
    }

    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    private static class InterfaceTypeFilter implements TypeFilter{

        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            return metadataReader.getClassMetadata().isInterface();
        }
    }

}
