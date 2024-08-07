package com.cvicse.jy1.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The AuditStatus enumeration.
 */
public enum AuditStatus {
    /**
     * 审核状态：未审核、审核中、已通过
     */
    Not_Audited,
    In_Audit,
    Approved;

    @JsonCreator
    public static AuditStatus fromString(String key) {
        for (AuditStatus auditstatus : AuditStatus.values()) {
            if (auditstatus.name().equalsIgnoreCase(key)) {
                return auditstatus;
            }
        }
        return null; // 或者可以抛出异常
    }
}
