package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.LeaveApplicationInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LeaveApplicationInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaveApplicationInfo.class);
        LeaveApplicationInfo leaveApplicationInfo1 = getLeaveApplicationInfoSample1();
        LeaveApplicationInfo leaveApplicationInfo2 = new LeaveApplicationInfo();
        assertThat(leaveApplicationInfo1).isNotEqualTo(leaveApplicationInfo2);

        leaveApplicationInfo2.setId(leaveApplicationInfo1.getId());
        assertThat(leaveApplicationInfo1).isEqualTo(leaveApplicationInfo2);

        leaveApplicationInfo2 = getLeaveApplicationInfoSample2();
        assertThat(leaveApplicationInfo1).isNotEqualTo(leaveApplicationInfo2);
    }
}
