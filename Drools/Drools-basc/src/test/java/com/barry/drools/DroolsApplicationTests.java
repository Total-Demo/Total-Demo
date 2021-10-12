package com.barry.drools;

import com.barry.drools.bean.Car;
import com.barry.drools.bean.Person;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DroolsApplicationTests {

    @Test
    void contextLoads() {
        KieServices kieServices=KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();

        KieSession kieSession = kieClasspathContainer.newKieSession("costprice");

        Person person1=new Person("haha",65);
        Car car1=new Car("宝马",10000.0);
        car1.setPerson(person1);

        kieSession.insert(car1);

        int i = kieSession.fireAllRules(1);
        System.out.println("触发的规则:"+i);
        kieSession.dispose();


    }

}
