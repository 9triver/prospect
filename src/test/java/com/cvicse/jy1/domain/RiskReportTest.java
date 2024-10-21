package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.RiskReportTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RiskReportTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskReport.class);
        RiskReport riskReport1 = getRiskReportSample1();
        RiskReport riskReport2 = new RiskReport();
        assertThat(riskReport1).isNotEqualTo(riskReport2);

        riskReport2.setId(riskReport1.getId());
        assertThat(riskReport1).isEqualTo(riskReport2);

        riskReport2 = getRiskReportSample2();
        assertThat(riskReport1).isNotEqualTo(riskReport2);
    }

    @Test
    void creatoridTest() {
        RiskReport riskReport = getRiskReportRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        riskReport.setCreatorid(hrManagementBack);
        assertThat(riskReport.getCreatorid()).isEqualTo(hrManagementBack);

        riskReport.creatorid(null);
        assertThat(riskReport.getCreatorid()).isNull();
    }

    @Test
    void wbsidTest() {
        RiskReport riskReport = getRiskReportRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        riskReport.setWbsid(projectwbsBack);
        assertThat(riskReport.getWbsid()).isEqualTo(projectwbsBack);

        riskReport.wbsid(null);
        assertThat(riskReport.getWbsid()).isNull();
    }

    @Test
    void workbagTest() {
        RiskReport riskReport = getRiskReportRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        riskReport.setWorkbag(workbagBack);
        assertThat(riskReport.getWorkbag()).isEqualTo(workbagBack);

        riskReport.workbag(null);
        assertThat(riskReport.getWorkbag()).isNull();
    }
}
