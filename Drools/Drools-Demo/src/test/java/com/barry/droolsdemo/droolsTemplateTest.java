package com.barry.droolsdemo;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class droolsTemplateTest {

    static KnowledgeBuilder builder;
    KieBase kieBase;

    public static void beforeClass() {
        builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    }

    @Before
    public void setUp() {
        builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        /**
         * 1. 从本地模板DRT文件得到输入流
         * 2. 创建一个 ArrayDataProvider , 二维数组中元素按顺序与DRT文件中定义的变量一一对应
         * 3. 创建一个 DataProviderCompiler 对象用compile()方法渲染, 将二维数组中的元素一一填充到DRT模板中, 得到DRL(规则)字符串
         * 4. 加载DRL(规则)字符串
         */
        String pathToDrt = "/Users/barry.cao/Desktop/drools/drl/template.drt";
        try (InputStream stream = new FileInputStream(pathToDrt)) {
            DataProvider dataProvider = new ArrayDataProvider(new String[][]{
                    new String[]{"String(this == \"规则条件\")", "\"规则动作\""}
            });
            DataProviderCompiler compiler = new DataProviderCompiler();
            String DRL = compiler.compile(dataProvider, stream);
            System.out.println("-----模板DRT渲染后的DRL-----");
            System.out.println(DRL);
            System.out.println("-----模板DRT渲染后的DRL-----");
            byte[] drlBytes = DRL.getBytes("UTF-8");
            Resource resourceTemplate = ResourceFactory.newByteArrayResource(drlBytes);
            builder.add(resourceTemplate, ResourceType.DRL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //builder.add(resourceNative, ResourceType.DRL);
//        builder.add(resourceRemote, ResourceType.DRL);
        kieBase = builder.newKieBase();
    }

    @Test
    public void testTemplate() {
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert("规则条件");
        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
