package com.cvicse.jy1.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The Secretlevel enumeration.
 */
public enum Secretlevel {
    /**
     * 密级：机密、非密_内部、公开
     */
    SECRET,
    NOSECTET_INTERNAL,
    PUBLIC;

    @JsonCreator
    public static Secretlevel fromString(String key) {
        for (Secretlevel level : Secretlevel.values()) {
            if (level.name().equalsIgnoreCase(key)) {
                return level;
            }
        }
        return null; // 或者可以抛出异常
    }
}
