package com.cvicse.jy1;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ActivitiServiceTask implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(ActivitiServiceTask.class);

    @Override
    public void execute(DelegateExecution execution) {
        // Retrieve variables from the process instance if needed
        String variable = (String) execution.getVariable("myVariable");
        log.info("Processing request with variable: {}", variable);
        System.out.println("!!!!!!!!!!!!~~~~~!!!~~!!!!!~~!!~!~!@!!!!!~!@!!!~~!!!");
        // Implement your business logic here
        // For example, processing data, calling external services, etc.
        
        // Set variables or complete tasks as needed
        execution.setVariable("result", "processed");
    }
}
