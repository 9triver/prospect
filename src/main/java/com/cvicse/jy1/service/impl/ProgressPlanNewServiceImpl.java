package com.cvicse.jy1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvicse.jy1.domain.ProgressPlanNew;
import com.cvicse.jy1.repository.ProgressPlanNewRepository;



@Service
public class ProgressPlanNewServiceImpl {

    @Autowired
    private ProgressPlanNewRepository repository;

    public List<ProgressPlanNew> findAllPlans() {
        return repository.findAll();
    }

    public ProgressPlanNew savePlan(ProgressPlanNew progressPlan) {
        return repository.save(progressPlan);
    }

    // 可以添加更多的业务逻辑方法
}