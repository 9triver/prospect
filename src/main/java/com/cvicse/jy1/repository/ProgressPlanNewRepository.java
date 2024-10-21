package com.cvicse.jy1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvicse.jy1.domain.ProgressPlanNew;

import java.util.List;

public interface ProgressPlanNewRepository extends JpaRepository<ProgressPlanNew, Long> {

    List<ProgressPlanNew> findByPlanname(String planname);
    
    // 可以添加更多的自定义查询方法
}