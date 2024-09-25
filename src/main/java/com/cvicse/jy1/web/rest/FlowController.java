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
import java.util.Optional;

import org.activiti.bpmn.model.Activity;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.ExtensionElement;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.FormProperty;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;
import java.util.HashSet;
import com.cvicse.jy1.security.SecurityUtils;

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
    public List<Map<String,String>> todo(@RequestBody String assignee) {
        // 根据流程key 和 任务负责人 查询任务
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(assignee)
                // .processDefinitionKey("myLeave")
                .list();
        List<Map<String,String>> list = new ArrayList<>();
        for (Task task : tasks) {
            Map<String,String> map = new HashMap<>();
            map.put("Task_Name_", task.getName());// 任务名称
            map.put("PROC_DEF_ID_", task.getProcessDefinitionId());// 流程定义id
            map.put("PROC_INST_ID_", task.getProcessInstanceId());// 流程实例id
            map.put("CREATE_TIME_", task.getCreateTime().toString());// 创建时间
            map.put("TASK_ID_", task.getId()); // 任务id，后续会通过任务id来完成任务
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                                .processDefinitionId(task.getProcessDefinitionId()).singleResult();
            map.put("PROC_NAME_", processDefinition.getName());
            map.put("PROC_ID_", processDefinition.getId());
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
    // * 发起流程
    // */
    // @GetMapping("/startproc")
    // public String startproc() {
    // ProcessInstance processInstance =
    // runtimeService.startProcessInstanceByKey("myLeave");
    // return "流程定义id：" + processInstance.getProcessDefinitionId() + "流程实例id：" +
    // processInstance.getId() + "当前活动id："
    // + processInstance.getActivityId();
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
    // public ResponseEntity<String> processDeployment(@RequestParam("xmlinfo")
    // String xmlinfo) {
    // try {
    // // 部署流程定义
    // repositoryService.createDeployment()
    // .addString(".bpmn20.xml", xmlinfo)
    // .deploy();
    // return new ResponseEntity<>("流程定义已成功部署", HttpStatus.CREATED);
    // } catch (Exception e) {
    // return new ResponseEntity<>("部署流程定义失败: " + e.getMessage(),
    // HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }
    @PostMapping("/processDeployment")
    public ResponseEntity<String> processDeployment(@RequestBody Map<String, String> requestBody) {
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
            map.put("version", String.valueOf(processDefinition.getVersion()));
            list.add(map);
        }
        return ResponseEntity.ok(list);
    }

    // 根据流程定义id获取流程建模对应的xml
    @PostMapping("/processPreview")
    public ResponseEntity getXmlById(@RequestBody Map<String, String> requestBody) {
        String id = requestBody.get("id");
        String xmlInfo = getXmlByProcessDefinitionId(id);
        if (!xmlInfo.equals("")) {
            return ResponseEntity.ok(xmlInfo);
        } else {
            return new ResponseEntity<>("获取流程建模数据失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // InputStream bpmnStream = repositoryService.getProcessModel(id);
        // if (bpmnStream != null) {
        // try {
        // // 将InputStream转换为String
        // BufferedReader reader = new BufferedReader(new InputStreamReader(bpmnStream,
        // StandardCharsets.UTF_8));
        // StringBuilder xmlContent = new StringBuilder();
        // String line;
        // while ((line = reader.readLine()) != null) {
        // xmlContent.append(line);
        // }
        // return new ResponseEntity<>(xmlContent.toString(), HttpStatus.OK);
        // } catch (IOException e) {
        // return new ResponseEntity<>("获取流程失败2: " + e.getMessage(),
        // HttpStatus.INTERNAL_SERVER_ERROR);
        // } finally {
        // try {
        // bpmnStream.close(); // 确保关闭流
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // } else {
        // return new ResponseEntity<>("获取流程失败1: ", HttpStatus.INTERNAL_SERVER_ERROR);
        // }
    }

    // 发起流程请求体对应的实体
    static class StartProcessRequest {
        private String key;
        private Map<String, Object> variables;
        private String userName;
    
        // Getters and Setters
        public String getKey() {
            return key;
        }
    
        public void setKey(String key) {
            this.key = key;
        }
    
        public Map<String, Object> getVariables() {
            return variables;
        }
    
        public void setVariables(Map<String, Object> variables) {
            this.variables = variables;
        }
    
        public String getUserName() {
            return userName;
        }
    
        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    /**
     * 根据流程标识发起流程
     */
    @PostMapping("/startproc")
    public ResponseEntity startprocByKey(@RequestBody StartProcessRequest startProcessRequest) {
        try {
            String key = startProcessRequest.getKey();
            String userName = startProcessRequest.getUserName();
            Map<String, Object> variables = startProcessRequest.getVariables();
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionKey(key).latestVersion().singleResult();
            ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),variables);
            String resStr = "流程定义id：" + processInstance.getProcessDefinitionId() + "流程实例id：" + processInstance.getId()
                    + "当前活动id："
                    + processInstance.getActivityId();
            System.out.print("发起流程成功" + resStr);
            Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
            String assignee = task.getAssignee();
            if (!assignee.equals(userName)) {
                return ResponseEntity.ok("流程创建成功，但该用户没有第一岗权限");
            }
            Map<String, String> map = new HashMap<>();
            map.put("processDefinitionId", processInstance.getProcessDefinitionId());
            map.put("processInstanceId", processInstance.getId());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("发起流程失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 通过流程定义id获取流程定义对应的建模(不作为接口使用)
    public String getXmlByProcessDefinitionId(String id) {
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
                return xmlContent.toString();
            } catch (IOException e) {
                System.out.print(e.getMessage());
                return "";
            } finally {
                try {
                    bpmnStream.close(); // 确保关闭流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.print("无效的流程定义id");
            return "";
        }
    }

    // 查询流程定义的历史版本
    @PostMapping("/queryProcessDefinitionVersion")
    public ResponseEntity queryProcessDefinitionVersion(@RequestBody Map<String, String> requestBody) {
        try {
            String key = requestBody.get("key");
            List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionKey(key).orderByProcessDefinitionVersion().desc().list();
            List<Map<String, String>> list = new ArrayList<>();
            for (ProcessDefinition processDefinition : processDefinitions) {
                Deployment deployment = repositoryService.createDeploymentQuery()
                        .deploymentId(processDefinition.getDeploymentId()).singleResult();
                Map<String, String> map = new HashMap<>();
                String xmlInfo = getXmlByProcessDefinitionId(processDefinition.getId());
                map.put("name", processDefinition.getName());
                map.put("deploymentTime", deployment.getDeploymentTime().toString());
                map.put("xmlInfo", xmlInfo);
                map.put("version", String.valueOf(processDefinition.getVersion()));
                map.put("id", processDefinition.getId());
                list.add(map);
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>("查询历史版本失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/test")
    public void test() {
        System.out.println("test");
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myLeave").latestVersion().singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        // System.out.println("周"+bpmnModel+bpmnModel.getMainProcess().getFlowElements());

        Task task = taskService.createTaskQuery().taskId("170015").singleResult();
        System.out.println("周" + task);
        // 获取用户任务
        UserTask userTask = (UserTask) bpmnModel.getMainProcess().getFlowElement(task.getTaskDefinitionKey());
        // 获取用户任务的扩展元素
        String formKey = userTask.getFormKey();
        List<FormProperty> formProperties = userTask.getFormProperties();
        System.out.println("周" + formKey);
        for (FormProperty formProperty : formProperties) {
            // formProperty.toString();
            System.out.println("周" + formProperty.getId() + formProperty.getName() + formProperty.getType());
        }

        // Object principal =
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // if (principal instanceof UserDetails) {
        // UserDetails userDetails = (UserDetails) principal;
        // System.out.println("周" +
        // userDetails.getUsername()+Optional.of(userDetails.getUsername()));
        // }
        // System.out.println("周" + "空"+SecurityUtils.getCurrentUserLogin().get());
    }

    @PostMapping("/getTaskInfoByTaskId")
    public ResponseEntity<?> getTaskInfoByTaskId(@RequestBody Map<String, String> map) {
        try {
            String taskId = map.get("taskId");
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            System.out.println("周" + task);
            // 获取用户任务
            UserTask userTask = (UserTask) repositoryService.getBpmnModel(task.getProcessDefinitionId())
                    .getMainProcess().getFlowElement(task.getTaskDefinitionKey());
            // 获取用户任务的扩展元素
            String formKey = userTask.getFormKey();
            List<FormProperty> formProperties = userTask.getFormProperties();
            // 保存业务单元列表
            List<Map<String,String>> bussinessUnits = new ArrayList();
            System.out.println("周" + formKey);
            for (FormProperty formProperty : formProperties) {
                // formProperty.toString();
                System.out.println("周" + formProperty.getId() + formProperty.getName() + formProperty.getType());
                // 业务单元每个元素都是一个对象
                Map<String,String> businessUnitMap = new HashMap<>();
                businessUnitMap.put("name",formProperty.getName());
                businessUnitMap.put("path",formProperty.getId());
                bussinessUnits.add(businessUnitMap);
            }
            return ResponseEntity.ok(bussinessUnits);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("初始化流程工作台失败");

        }
    }

    // 提交任务
    @PostMapping("/submitTask")
    public ResponseEntity<?> submitTask(@RequestBody Map<String, String> requestMap) {
        String taskId = requestMap.get("taskId");
        String procInstId = requestMap.get("procInstId");
        // Execution execution = runtimeService.createExecutionQuery().executionId(taskService.createTaskQuery().taskId(taskId).singleResult().getExecutionId()).singleResult();
        // String activityId = execution.getActivityId();
        // BpmnModel bpmnModel = repositoryService.getBpmnModel(runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult().getProcessDefinitionId());
        // // 获取当前节点
        // FlowNode currentNode = (FlowNode)bpmnModel.getMainProcess().getFlowElement(activityId);
        // List<SequenceFlow> outgoingFlows = currentNode.getOutgoingFlows();
        // for (SequenceFlow sequenceFlow : outgoingFlows) {
        //     System.out.println("周" + sequenceFlow.getId() + sequenceFlow.getName());
        // }
        taskService.complete(taskId);
        Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
        if(task == null){
            return ResponseEntity.ok("流程已结束");
        }
        Map<String,String> map = new HashMap();
        map.put("assignee",task.getAssignee());
        map.put("message","任务已提交");
        return ResponseEntity.ok(map);
    }

    // 利用流程变量的方式添加岗位意见，这种方式一个岗位就只能有一个意见，再次修改意见会被替换
    // 保存岗位意见
    @PostMapping("/savePositionOpinion")
    public ResponseEntity<?> savePositionOpinion(@RequestBody Map<String, String> requestMap) {
        String taskId = requestMap.get("taskId");
        String procInstId = requestMap.get("procInstId");
        String positionOpinion = requestMap.get("positionOpinion");
        taskService.setVariable(taskId,"positionOpinion",positionOpinion);
        Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
        Map<String,String> map = new HashMap();
        map.put("assignee",task.getAssignee());
        map.put("message","意见已保存");
        return ResponseEntity.ok(map);
    }
    
    // 获取岗位意见
    @PostMapping("/getPositionOpinion")
    public ResponseEntity<?> getPositionOpinion(@RequestBody Map<String,String> requestMap){
        String taskId = requestMap.get("taskId");
        String procInstId = requestMap.get("procInstId");
        Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
        String positionOpinion = (String) taskService.getVariable(taskId,"positionOpinion");
        Map<String,String> map = new HashMap();
        map.put("positionOpinion",positionOpinion);
        return ResponseEntity.ok(map);
    }

    // 考虑使用ACT_HI_COMMENT来实现岗位意见的存储
    // 保存岗位意见
    @PostMapping("/savePositionOpinion2")
    public ResponseEntity<?> savePositionOpinion2(@RequestBody Map<String, String> requestMap) {
        String taskId = requestMap.get("taskId");
        String procInstId = requestMap.get("procInstId");
        String positionOpinion = requestMap.get("positionOpinion");
        taskService.addComment(taskId,procInstId,positionOpinion);
        Map<String,String> map = new HashMap();
        map.put("message","意见已保存");
        return ResponseEntity.ok(map);
    }
    // 获取最后一次岗位意见
    @PostMapping("/getPositionOpinion2")
    public ResponseEntity<?> getPositionOpinion2(@RequestBody Map<String,String> requestMap){
        String procInstId = requestMap.get("procInstId");
        String taskId = requestMap.get("taskId");
        List<Comment> commentList = taskService.getTaskComments(taskId);
        List<Map<String,String>> list = new ArrayList<>();
        for(Comment comment : commentList){
            Map<String,String> map = new HashMap();     
            map.put("positionOpinion",comment.getFullMessage());
            list.add(map);
        }
        return ResponseEntity.ok(list.size()>0?list.get(0):new ArrayList<>());
    }

    // 根据流程实例所有岗位意见列表
    @PostMapping("/getAllOpinionList")
    public ResponseEntity<?> getAllOpinionList(@RequestBody Map<String,String> requestMap){
        String procInstId = requestMap.get("procInstId");
        String taskId = requestMap.get("taskId");
        List<Comment> commentList = taskService.getProcessInstanceComments(procInstId);
        List<Map<String,String>> list = new ArrayList<>();
        for(Comment comment : commentList){
            Map<String,String> map = new HashMap();     
            map.put("id", comment.getId());
            map.put("positionOpinion",comment.getFullMessage());
            map.put("time",comment.getTime().toString());
            map.put("taskId",comment.getTaskId());
            if(taskId.equals(comment.getTaskId())){
                Task task = taskService.createTaskQuery().taskId(comment.getTaskId()).singleResult();
                map.put("taskName",task.getName());
                map.put("assignee", task.getAssignee());
            }else{
                HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(comment.getTaskId()).singleResult();
                map.put("taskName",historicTaskInstance.getName());
                map.put("assignee", historicTaskInstance.getAssignee());
            }
            System.out.println("周111"+comment.getTaskId());
            list.add(map);
            
        }
        return ResponseEntity.ok(list);
    }

    // 根据流程实例id获取流程变量
    @PostMapping("/getProcessVariable")
    public ResponseEntity<?> getProcessVariable(@RequestBody Map<String,String> requestMap){
        String procInstId = requestMap.get("procInstId");
        Map<String,Object> map = runtimeService.getVariables(procInstId);
        return ResponseEntity.ok(map);
    }

    // 根据流程实例id获取实际流程图
    @PostMapping("/getRunningProcessDiagram")
    public ResponseEntity<?> getRunningProcessDiagram(@RequestBody Map<String,String> requestMap){
        String procInstId = requestMap.get("procInstId");
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
        // 流程实例对应的流程图
        String xmlInfo = getXmlByProcessDefinitionId(processInstance.getProcessDefinitionId());
        List<Execution> executions = runtimeService.createExecutionQuery()
                .processInstanceId(procInstId)
                .list();
        System.out.println("executions: " + executions.toString());
        Set<String> activityIds = new HashSet<>();
        Set<String> flowIds = new HashSet<>();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        for (Execution execution : executions) {
            System.out.println("execution11: " + execution.getActivityId());
            if (execution.getActivityId() != null) {
                activityIds.add(execution.getActivityId());
                Object flowElement = bpmnModel.getFlowElement(execution.getActivityId());
            
                if (flowElement instanceof Activity) { // 确认是 Activity 类型
                    Activity activity = (Activity) flowElement;
                    
                    // 获取活动节点的出站序列流
                    List<SequenceFlow> incomingFlows = activity.getIncomingFlows();
                    for (SequenceFlow flow : incomingFlows) {
                        flowIds.add(flow.getId());
                    }
                }
            }
        }
        System.out.println("activityIds: " + activityIds + "flowIds" + flowIds);
        // 活动节点
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(procInstId);
        
        System.out.println("activeActivityIds"+activeActivityIds);
        // 活动连线
        Map map = new HashMap();
        map.put("activityIds",activityIds);
        map.put("flowIds",flowIds);
        map.put("xmlInfo",xmlInfo);
        return ResponseEntity.ok(map);
    }
    // 根据流程实例id获取实际流程图
    @PostMapping("/getRunningProcessDiagram1")
    public ResponseEntity<?> getRunningProcessDiagram1(@RequestBody Map<String,String> requestMap){
        String procInstId = requestMap.get("procInstId");
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();;
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(procInstId);
        List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery().processInstanceId(procInstId).list();
        Set<String> flowIds = new HashSet<>();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        for(HistoricActivityInstance historicActivityInstance:historicActivityInstances){
            FlowElement flowElement = bpmnModel.getFlowElement(historicActivityInstance.getActivityId());
            System.out.println("周");
            System.out.println(flowElement instanceof Activity);
            System.out.println(flowElement.getName());
            if (flowElement instanceof Activity) { // 确认是 Activity 类型
                Activity activity = (Activity) flowElement;
                
                // 获取活动节点的出站序列流
                List<SequenceFlow> incomingFlows = activity.getIncomingFlows();
                for (SequenceFlow flow : incomingFlows) {
                    flowIds.add(flow.getId());
                }
            }
        }
        String xmlInfo = getXmlByProcessDefinitionId(processInstance.getProcessDefinitionId());
        Map<String,Map<String,String>> histroicActivityInfos = new HashMap<>();
        for(HistoricActivityInstance historicActivityInstance:historicActivityInstances){
            Map<String,String> histroicActivityInfo = new HashMap<>();
        
            histroicActivityInfo.put("id",historicActivityInstance.getActivityId());
            histroicActivityInfo.put("name",historicActivityInstance.getActivityName());
            histroicActivityInfo.put("assignee",historicActivityInstance.getAssignee());
            histroicActivityInfo.put("startTime",historicActivityInstance.getStartTime().toString());
            if(historicActivityInstance.getEndTime()!=null){
                histroicActivityInfo.put("endTime",historicActivityInstance.getEndTime().toString());
                histroicActivityInfo.put("durationInMillis",historicActivityInstance.getDurationInMillis().toString());
            }else{
                histroicActivityInfo.put("endTime",null);
                histroicActivityInfo.put("durationInMillis",null);
            }
            histroicActivityInfos.put(historicActivityInstance.getActivityId(), histroicActivityInfo);
        }
        Map map = new HashMap();
        map.put("activeActivityIds",activeActivityIds);
        map.put("xmlInfo",xmlInfo);
        map.put("flowIds",flowIds);
        map.put("histroicActivityInfos",histroicActivityInfos);
        return ResponseEntity.ok(map);
    }

    // 流程退回
    @PostMapping("/flow/rollback/{taskId}/{sid}")
    public ResponseEntity<?> rollback(@PathVariable String taskId, @PathVariable String sid) {
        Task t = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDefinitionId = runtimeService.createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).singleResult().getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        // 寻找流程实例当前任务的activeId
        Execution execution = runtimeService.createExecutionQuery().executionId(t.getExecutionId()).singleResult();
        String activityId = execution.getActivityId();
        String targetActivityId = null;
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId(t.getProcessInstanceId()).list();
        for (HistoricActivityInstance h : list) {
            if(h.getActivityType().equals("userTask")&&h.getTaskId().equals(sid)){
                targetActivityId = h.getActivityId();
                break;
            }
        }
        // 获取目标节点
        FlowNode currentNode = (FlowNode)bpmnModel.getMainProcess().getFlowElement(activityId);
        FlowNode targetNode = (FlowNode)bpmnModel.getMainProcess().getFlowElement(targetActivityId);
        // 创建连接线
        List<SequenceFlow> newSequenceFlowList = new ArrayList<SequenceFlow>();
        SequenceFlow newSequenceFlow = new SequenceFlow();
        newSequenceFlow.setId("newFlow");
        newSequenceFlow.setSourceFlowElement(currentNode);
        newSequenceFlow.setTargetFlowElement(targetNode);
        newSequenceFlowList.add(newSequenceFlow);
        // 备份原有方向
        List<SequenceFlow> dataflows = currentNode.getOutgoingFlows();
        List<SequenceFlow> oriSequenceFlows = new ArrayList<SequenceFlow>();
        oriSequenceFlows.addAll(dataflows);
        // 清空原有方向
        currentNode.getOutgoingFlows().clear();
        // 设置新方向
        currentNode.setOutgoingFlows(newSequenceFlowList);
        // 完成当前任务
        taskService.addComment(taskId, t.getProcessInstanceId(), "comment", "跳转节点");
        taskService.complete(taskId);
        // 恢复原有方向
        currentNode.setOutgoingFlows(oriSequenceFlows);
        return ResponseEntity.ok("流程退回成功");
    }
}