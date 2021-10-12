package com.barry.drools.service;

import com.barry.drools.config.KieUtils;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReloadRuleService {

    @Autowired
    KieFileSystem kieFileSystem;
    public KieServices getKieServices(){
        KieServices kieServices = KieServices.Factory.get();
        return  kieServices;
    }
    public  void  reload(){
        KieServices kieServices = getKieServices();
        kieFileSystem.write("src/main/resources/rule/"+ UUID.randomUUID().toString()+".drl",loadRule());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)){
            System.out.println(results.getMessages());
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieUtils.setKieContainer(kieContainer);
    }


    public  void  reload1(){
        KieServices kieServices = getKieServices();
        KieHelper kieHelper=new KieHelper();
        kieHelper.addContent(loadRule(), ResourceType.DRL);
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.ERROR)){
            System.out.println(results.getMessages());
        }
        KieContainer kieContainer = kieHelper.getKieContainer();

        KieUtils.setKieContainer(kieContainer);

    }




    private static final String RULES = "package com.rules\n" +
        "\n" +
        "rule \"chapter3333333\"\n" +
        "\n" +
        "when\n" +
        "\n" +
        "then\n" +
        "\n" +
        "System.out.println(\"分享牛 Fire the default rules for dynamic!33333333\");\n" +
        "end";
    private  String loadRule(){
            return  RULES;
    }
}
