package com.barry.drools.controller;

import com.barry.drools.bean.Person;
import com.barry.drools.config.KieUtils;
import com.barry.drools.service.ReloadRuleService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@RequestMapping("")
@Controller
public class TestResource {
    @Autowired
    private ReloadRuleService reloadRuleService;
    @ResponseBody
    @RequestMapping("/reload")
    public String reload() throws IOException {

        reloadRuleService.reload();
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/reload1")
    public String reload1() throws IOException {

        reloadRuleService.reload1();
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/test")
    public String test() throws IOException {

        KieContainer kieContainer = KieUtils.getKieContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Person person=new Person();
        person.setName("分享牛");
        person.setAge(18);
        kieSession.insert(person);
        int i = kieSession.fireAllRules();
        kieSession.dispose();
        return "触发的规则数量，"+i;
    }
}
