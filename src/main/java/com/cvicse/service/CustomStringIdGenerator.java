package com.cvicse.service;

import com.cvicse.repository.OfficersRepository;
import java.io.Serializable;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomStringIdGenerator implements IdentifierGenerator {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomStringIdGenerator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        //查询现在人员编号，累加序列
        String sequenceName = "OFFICERS_SEQ";
        Long nextVal = getNextSequenceValue(sequenceName);
        String formattedOfficerId = String.format("%05d", nextVal);
        return formattedOfficerId;
        // 在这里编写生成主键的逻辑，例如使用 UUID 生成唯一的字符串
        // return "CUSTOM_PREFIX_" + UUID.randomUUID().toString();
    }

    private Long getNextSequenceValue(String sequenceName) {
        String sql = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL"; // Assuming Oracle syntax
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
