package com.cvicse.jy1.service;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class ActivitiDemo {

    //1、创建ProcessEngine
    @Autowired
    private ProcessEngine processEngine;

    @PostConstruct
    public void activities(){
        //2、获取ReposityService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3、使用service进行流程的部署，定义一个流程的名字，把bpmn和png部署到数据中
        Deployment deploy = repositoryService.createDeployment()
                .name("审批流程")
                .addClasspathResource("bpmn/1.bpmn")
                .addClasspathResource("bpmn/1.png")
                .deploy();
        //4、输出部署信息
        System.out.println("流程id="+deploy.getId());
        System.out.println("流程名字="+deploy.getName());
    }
}
