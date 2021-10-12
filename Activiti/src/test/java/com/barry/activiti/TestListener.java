package com.barry.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class TestListener {

    /**
     * 部署流程定义
     */
    @Test
    public void testDeployment() {
        //1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2、得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3、使用RepositoryService进行部署
        Deployment deployment = repositoryService.createDeployment()
                // 添加bpmn资源
                .addClasspathResource("bpmn/demo-listen.bpmn")
                .name("测试监听器")
                .deploy();
        //4、输出部署信息
        System.out.println("流程部署id:" + deployment.getId());
        System.out.println("流程部署名称:" + deployment.getName());

    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcess() {
        //1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2、获取RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //3、根据流程定义Id启动流程
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("testListener");
        //输出内容
        System.out.println("流程定义id:" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例id:" + processInstance.getId());
        System.out.println("当前活动Id:" + processInstance.getActivityId());
    }


}
