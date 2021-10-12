package com.barry.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.junit.Test;

public class TestCreate {

    /**
     * 生成 activiti的数据库表
     */
    @Test
    public void testCreateDbTable() { //使用classpath下的activiti.cfg.xml中的配置创建processEngine
        //ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //System.out.println(processEngine);


        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println(processEngine);

    }
}
