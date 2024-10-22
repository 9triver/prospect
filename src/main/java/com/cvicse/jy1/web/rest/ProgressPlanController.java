package com.cvicse.jy1.web.rest;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.cvicse.jy1.domain.ProgressPlanNew;
import com.cvicse.jy1.repository.ProgressPlanNewRepository;


import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/progress-plan-new")
@Transactional
public class ProgressPlanController {

    @Autowired
    private ProgressPlanNewRepository progressPlanNewRepository;

    @GetMapping("/test1021/save")
    public String test(){
        ProgressPlanNew newPlan = new ProgressPlanNew();
        newPlan.setPlanname("项目A进度计划");
        newPlan.setWbsname("模块X");
        newPlan.setWbsid("X001");
        newPlan.setPlantype("里程碑");
        newPlan.setPlanlevel("1");
        newPlan.setTime(new Date()); // 当前时间
        newPlan.setPlanendtime(new Date(System.currentTimeMillis() + (30 * 24 * 60 * 60 * 1000))); // 30天后的时间
        newPlan.setResponsiblepersonname("张三");
        newPlan.setResponsiblepersonid("P001");
        newPlan.setResponsibledpartmentname("研发部");
        newPlan.setResponsibledpartmentid("D001");
        progressPlanNewRepository.save(newPlan);
        return "test";
    }
    @GetMapping("/test1021/query")
    public List<ProgressPlanNew> test2(){
        List<ProgressPlanNew> newPlan = progressPlanNewRepository.findAll();
        return newPlan;
    }

    // 查询所有数据
    @GetMapping("/findAll")
    public List<ProgressPlanNew> findAll(){
        return progressPlanNewRepository.findAll();
    }
    // 创建一个新的进度计划
    @PostMapping("/create")
    public ProgressPlanNew createProgressPlan(@RequestBody ProgressPlanNew progressPlan) {
        // System.out.println("sdsssss:" + progressPlan);
        // return progressPlan;
        return progressPlanNewRepository.save(progressPlan);
    }
}
