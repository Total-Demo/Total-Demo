package com.barry.droolsdemo;


import com.barry.droolsdemo.entity.PersonInfoEntity;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DroolsdemoApplicationTests {

    @Test
    public void contextLoads() throws Exception{

        String realPath = "/Users/barry.cao/Desktop/drools/drl/drools01.xls";//指定决策表xls文件的磁盘路径
        File file = new File(realPath);
        InputStream is = new FileInputStream(file);
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String drl = compiler.compile(is, InputType.XLS);

        System.out.println(drl);
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        KieSession session = kieHelper.build().newKieSession();

        PersonInfoEntity personInfoEntity = new PersonInfoEntity();
        personInfoEntity.setSex("男");
        personInfoEntity.setAge(35);
        personInfoEntity.setSalary(1000);

        List<String> list = new ArrayList<String>();
        session.setGlobal("listRules",list);

        session.insert(personInfoEntity);

        session.getAgenda().getAgendaGroup("sign").setFocus();

        session.fireAllRules();

        for (String s : list) {
            System.out.println(s);
        }
        session.dispose();

    }

}
