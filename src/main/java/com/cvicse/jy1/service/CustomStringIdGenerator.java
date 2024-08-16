package com.cvicse.jy1.service;

import java.io.Serializable;
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
        try {
            String sequenceName = "OFFICERS_SEQ";
            Long nextVal = getNextSequenceValue(sequenceName);
            if (nextVal == null) {
                throw new HibernateException("Failed to retrieve sequence value.");
            }
            String idstr = String.format("%05d", nextVal);
            // Long id = Long.parseLong(idstr);
            System.out.println("---------|||||||||||主键id="+idstr+"||||||||||||--------");
            // String ProjectpbsId = String.format("%05d", nextVal);
            // String ProjectpbsId = String.valueOf(nextVal);
            return idstr;
            // 在这里编写生成主键的逻辑，例如使用 UUID 生成唯一的字符串
            // return "CUSTOM_PREFIX_" + UUID.randomUUID().toString();
        } catch (Exception e) {
            throw new HibernateException("Failed to generate ID", e);
        }
    }

    private Long getNextSequenceValue(String sequenceName) {
        String sql = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL"; // Assuming Oracle syntax
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
