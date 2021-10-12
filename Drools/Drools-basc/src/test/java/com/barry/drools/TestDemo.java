package com.barry.drools;

import com.barry.drools.bean.Car;
import com.barry.drools.bean.Member;
import com.barry.drools.bean.Person;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDemo {

    @Test
    public void test01() {
        KieServices kieServices=KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();

        KieSession kieSession = kieClasspathContainer.newKieSession("test-member");


        Member member=new Member();
        member.setGrade(12);
        member.setAccessTime(6);
        member.setConAmount(90.0);

        kieSession.insert(member);

        int i = kieSession.fireAllRules();
        System.out.println("触发的规则:"+i);
        kieSession.dispose();

    }
}
