package com.barry.drools.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

@Configuration
public class DroolsAutoConfiguration {
    public  static  final String  RULE_PATH="rule/";
    public KieServices getKieServices(){
        KieServices kieServices = KieServices.Factory.get();
        return  kieServices;
    }

    @Bean
    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (Resource file:getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULE_PATH+file.getFilename(),"UTF-8"));
        }
        return  kieFileSystem;
    }
    @Bean
    @ConditionalOnMissingBean(KieContainer.class)
    public KieContainer kieContainer() throws IOException {
        KieServices kieServices = getKieServices();
        KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {

            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)){
            System.out.println(results.getMessages());

        }
        KieContainer kieContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
        KieUtils.setKieContainer(kieContainer);
        return kieContainer;
    }
    @Bean
    @ConditionalOnMissingBean(KieBase.class)
    public KieBase kieBase() throws IOException {
        KieBase kieBase = kieContainer().getKieBase();
        return kieBase;
    }
    @Bean
    @ConditionalOnMissingBean(KModuleBeanFactoryPostProcessor.class)
    public KModuleBeanFactoryPostProcessor kModuleBeanFactoryPostProcessor(){
        KModuleBeanFactoryPostProcessor kModuleBeanFactoryPostProcessor = new KModuleBeanFactoryPostProcessor();
        return  kModuleBeanFactoryPostProcessor;
    }


    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver
            =new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath*:" + RULE_PATH + "**/*.*");
        return  resources;
    }
}
