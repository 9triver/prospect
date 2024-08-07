package com.cvicse.jy1.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The ProjectStatus enumeration.
 */
public enum ProjectStatus {
    /**
     * 项目状态：未开始、进行中、已完成、已取消、已超期
     */
    NOTSTART,
    IN_PROGRESS,
    COMPLETED,
    CANCELED,
    EXPIRED;

    @JsonCreator
    public static ProjectStatus fromString(String key) {
        for (ProjectStatus status : ProjectStatus.values()) {
            if (status.name().equalsIgnoreCase(key)) {
                return status;
            }
        }
        return null; // 或者可以抛出异常
    }
}
