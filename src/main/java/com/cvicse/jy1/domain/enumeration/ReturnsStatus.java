package com.cvicse.jy1.domain.enumeration;

/**
 * The ReturnsStatus enumeration.
 */
public enum ReturnsStatus {
    /**
     * 回报状态：未启动，提前完成，正常进行未完成,正常进行未完成,
	正常进行已完成,延期未完成,延期已完成,催办未完成,催办已完成
     */
    NOT_STARTED,
    COMPLETE_AHEAD_OF_SCHEDULE,
    NORMAL_PROGRESS_INCOMPLETE,
    NORMAL_PROGRESS_COMPLETED,
    DELAY_NOT_COMPLETED,
    DELAY_COMPLETED,
    REMINDER_NOT_COMPLETED,
    REMINDER_HAS_COMPLETED,
}
