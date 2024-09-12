package com.cvicse.jy1.web.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * FlowController
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FlowController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("Upload directory");
        System.out.println(repositoryService);
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        System.out.println("zhou" + list.get(0).getKey());
        return "hello activiti";
    }

    @GetMapping("/flowDeploy")
    public void startFlow() {
        // 3、使用service进行流程的部署，定义一个流程的名字，把bpmn和png部署到数据中
        Deployment deploy = repositoryService.createDeployment()
                .name("员工请假审批流程")
                .addClasspathResource("processes/leave.bpmn20.xml")
                .deploy();
        // 4、输出部署信息
        System.out.println("流程id=" + deploy.getId());
        System.out.println("流程名字=" + deploy.getName());
    }

    @GetMapping("/startProcess")
    public void startProcess() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myLeave");
        System.out.println("流程定义id：" + processInstance.getProcessDefinitionId() + "流程实例id：" + processInstance.getId()
                + "当前活动id：" + processInstance.getActivityId());
    }

    /**
     * 根据用户查询对应的待办任务
     * 
     * @param assignee
     * @return
     */
    @PostMapping("/todo")
    public List todo(@RequestBody String assignee) {
        // 根据流程key 和 任务负责人 查询任务
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(assignee)
                // .processDefinitionKey("myLeave")
                .list();
        List list = new ArrayList();
        for (Task task : tasks) {
            Map map = new HashMap();
            map.put("Task_Name_", task.getName());// 任务名称
            map.put("PROC_DEF_ID_", task.getProcessDefinitionId());// 流程定义id
            map.put("PROC_INST_ID_", task.getProcessInstanceId());// 流程实例id
            map.put("CREATE_TIME_", task.getCreateTime());// 创建时间
            map.put("TASK_ID_", task.getId()); // 任务id，后续会通过任务id来完成任务
            map.put("PROC_NAME_", repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(task.getProcessDefinitionId()).singleResult().getName());
            list.add(map);
        }
        return list;
    }

    /**
     * 根据用户查询对应的在办任务
     * 
     * @param assignee
     * @return
     */
    @PostMapping("/doing")
    public List doing(@RequestBody String assignee) {
        // 获取所有分配给对应人的历史任务
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(assignee)
                .list();
        List list = new ArrayList();
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            // 根据流程实例标识 获取运行中的流程实例 如果为null则表示流程实例标识对应的流程已结束
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(historicTaskInstance.getProcessInstanceId()).singleResult();
            // 需遍历每一个历史任务对应的流程是否都在运行中，且任务的执行人不能为自己，这时候对应的流程才是在办流程
            if (processInstance != null) {
                String runProcAssignee = taskService.createTaskQuery().processInstanceId(processInstance.getId())
                        .singleResult().getAssignee();
                if (!runProcAssignee.equals(assignee)) {
                    Map map = new HashMap();
                    // 根据流程实例id获取运行着的流程实例中但当前处理的任务 并且获取任务名称
                    map.put("Task_Name_", taskService.createTaskQuery()
                            .processInstanceId(historicTaskInstance.getProcessInstanceId()).singleResult().getName());// 任务名称
                    map.put("PROC_DEF_ID_", historicTaskInstance.getProcessDefinitionId());// 流程定义id
                    map.put("PROC_INST_ID_", historicTaskInstance.getProcessInstanceId());// 流程实例id
                    map.put("CREATE_TIME_", historicTaskInstance.getCreateTime());// 创建时间
                    map.put("TASK_ID_", historicTaskInstance.getId()); // 任务id，后续会通过任务id来完成任务
                    map.put("PROC_NAME_",
                            repositoryService.createProcessDefinitionQuery()
                                    .processDefinitionId(historicTaskInstance.getProcessDefinitionId()).singleResult()
                                    .getName());
                    list.add(map);
                }

            }

        }
        return list;
    }

    /**
     * 根据用户查询对应的结办任务
     * 
     * @param assignee
     * @return
     */
    @PostMapping("/done")
    public List done(@RequestBody String assignee) {
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime()
                .asc()
                .taskAssignee(assignee)
                .finished()
                .list();
        List list = new ArrayList();
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(historicTaskInstance.getProcessInstanceId()).singleResult();
            if (processInstance == null) {
                Map map = new HashMap();
                map.put("PROC_DEF_ID_", historicTaskInstance.getProcessDefinitionId());// 流程定义id
                map.put("PROC_INST_ID_", historicTaskInstance.getProcessInstanceId());// 流程实例id
                map.put("CREATE_TIME_", historicTaskInstance.getCreateTime());// 创建时间
                map.put("END_TIME_", historicTaskInstance.getEndTime());// 结束时间
                map.put("TASK_ID_", historicTaskInstance.getId()); // 任务id，后续会通过任务id来完成任务
                map.put("DURATION_", historicTaskInstance.getDurationInMillis()); // 持续时间
                map.put("PROC_NAME_", repositoryService.createProcessDefinitionQuery()
                        .processDefinitionId(historicTaskInstance.getProcessDefinitionId()).singleResult().getName()); // 流程名称
                list.add(map);
            }
        }
        System.out.println("/done" + historicTaskInstances + "list" + list);
        return list;
    }

    /**
     * 查询所有流程定义的信息
     * 
     * @return
     */
    @GetMapping("/queryprocdef")
    public List queryprocdef() {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
        List list = new ArrayList();
        for (ProcessDefinition processDefinition : processDefinitions) {
            Map map = new HashMap();
            map.put("defId", processDefinition.getKey());
            map.put("defKey", processDefinition.getKey());
            map.put("defName", processDefinition.getName());
            map.put("defVersion", processDefinition.getVersion());
            map.put("deplaymentId", processDefinition.getDeploymentId());
            list.add(map);
        }
        System.out.println("zhou" + list);
        return list;
    }

    // /**
    //  * 发起流程
    //  */
    // @GetMapping("/startproc")
    // public String startproc() {
    //     ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myLeave");
    //     return "流程定义id：" + processInstance.getProcessDefinitionId() + "流程实例id：" + processInstance.getId() + "当前活动id："
    //             + processInstance.getActivityId();
    // }

    /**
     * 处理任务
     */
    @PostMapping("/handletask")
    public void handletask(@RequestBody String taskId) {
        System.out.println("taskId" + taskId);
        taskService.complete(taskId);
    }

    /**
     * 查询流程历史信息
     */
    @PostMapping("/queryhistory")
    public List queryhistory(@RequestBody String procInstanceId) {
        System.out.println("接收到的流程实例id" + procInstanceId);
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(procInstanceId).orderByHistoricTaskInstanceStartTime().asc().list();
        System.out.println("根据流程实例id查询到的历史任务列表" + historicTaskInstances);
        List list = new ArrayList();
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            Map map = new HashMap();
            map.put("ID_", historicTaskInstance.getId());
            map.put("NAME_", historicTaskInstance.getName());
            map.put("ASSIGNEE_", historicTaskInstance.getAssignee());
            map.put("START_TIME_", historicTaskInstance.getStartTime());
            map.put("END_TIME_", historicTaskInstance.getEndTime());
            map.put("DURATION_", historicTaskInstance.getDurationInMillis());
            list.add(map);
        }
        return list;
    }

    @GetMapping("/queryProcessDeployment")
    public ResponseEntity<List<Map<String, String>>> queryProcessDeployment() {
        List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
        List<Map<String, String>> list = new ArrayList<>();
        for (Deployment deployment : deployments) {
            Map<String, String> map = new HashMap<>();
            map.put("id", deployment.getId());
            map.put("name", deployment.getName());
            map.put("deploymentTime", deployment.getDeploymentTime().toString());
            map.put("category", deployment.getCategory());
            map.put("key", deployment.getKey());
            map.put("tenantId", deployment.getTenantId());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }

    // 该接口无法传递很长的数据，优化为下面的接口
    // @PostMapping("/processDeployment")
    // public ResponseEntity<String> processDeployment(@RequestParam("xmlinfo") String xmlinfo) {
    //     try {
    //         // 部署流程定义
    //         repositoryService.createDeployment()
    //                 .addString(".bpmn20.xml", xmlinfo)
    //                 .deploy();
    //         return new ResponseEntity<>("流程定义已成功部署", HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>("部署流程定义失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    @PostMapping("/processDeployment")
    public ResponseEntity<String> processDeployment(@RequestBody Map<String,String> requestBody) {
        String xmlinfo = requestBody.get("xmlinfo");
        try {
            // 部署流程定义
            repositoryService.createDeployment()
                    .addString(".bpmn20.xml", xmlinfo)
                    .deploy();
            return new ResponseEntity<>("流程定义已成功部署", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("部署流程定义失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 查询流程定义的信息
    @PostMapping("/queryProcessDefinition")
    public ResponseEntity<List<Map<String, String>>> queryProcessDefinition(
            @RequestBody Map<String, String> requestMap) {
        String name = requestMap.get("name").trim();
        String key = requestMap.get("key").trim();
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKeyLike("%" + key + "%")
                .processDefinitionNameLike("%" + name + "%")
                .latestVersion()
                .list();
        List<Map<String, String>> list = new ArrayList<>();
        for (ProcessDefinition processDefinition : processDefinitions) {
            Map<String, String> map = new HashMap<>();
            Deployment deployment = repositoryService.createDeploymentQuery()
                    .deploymentId(processDefinition.getDeploymentId()).singleResult();
            map.put("id", processDefinition.getId());
            map.put("name", processDefinition.getName());
            map.put("deploymentTime", deployment.getDeploymentTime().toString());
            map.put("key", processDefinition.getKey());
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }

    // 根据流程定义id获取流程建模对应的xml
    @PostMapping("/processPreview")
    public ResponseEntity<String> getXmlById(@RequestBody Map<String, String> requestBody) {
        String id = requestBody.get("id");
        InputStream bpmnStream = repositoryService.getProcessModel(id);
        if (bpmnStream != null) {
            try {
                // 将InputStream转换为String
                BufferedReader reader = new BufferedReader(new InputStreamReader(bpmnStream, StandardCharsets.UTF_8));
                StringBuilder xmlContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    xmlContent.append(line);
                }
                return new ResponseEntity<>(xmlContent.toString(), HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>("获取流程失败2: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            } finally {
                try {
                    bpmnStream.close(); // 确保关闭流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return new ResponseEntity<>("获取流程失败1: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据流程标识发起流程
     */
    @PostMapping("/startproc")
    public ResponseEntity startprocByKey(@RequestBody Map<String,String> requestBody) {
        try{
            String key = requestBody.get("key");
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).latestVersion().singleResult();
            ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
            String resStr = "流程定义id：" + processInstance.getProcessDefinitionId() + "流程实例id：" + processInstance.getId() + "当前活动id："
                    + processInstance.getActivityId();
            return ResponseEntity.ok("发起流程成功"+resStr);
        }catch(Exception e){
            return new ResponseEntity<>("发起流程失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}