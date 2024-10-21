package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.RegularInspectionTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RegularInspectionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegularInspection.class);
        RegularInspection regularInspection1 = getRegularInspectionSample1();
        RegularInspection regularInspection2 = new RegularInspection();
        assertThat(regularInspection1).isNotEqualTo(regularInspection2);

        regularInspection2.setId(regularInspection1.getId());
        assertThat(regularInspection1).isEqualTo(regularInspection2);

        regularInspection2 = getRegularInspectionSample2();
        assertThat(regularInspection1).isNotEqualTo(regularInspection2);
    }

    @Test
    void responsiblepersonTest() {
        RegularInspection regularInspection = getRegularInspectionRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        regularInspection.setResponsibleperson(hrManagementBack);
        assertThat(regularInspection.getResponsibleperson()).isEqualTo(hrManagementBack);

        regularInspection.responsibleperson(null);
        assertThat(regularInspection.getResponsibleperson()).isNull();
    }

    @Test
    void designerTest() {
        RegularInspection regularInspection = getRegularInspectionRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        regularInspection.setDesigner(hrManagementBack);
        assertThat(regularInspection.getDesigner()).isEqualTo(hrManagementBack);

        regularInspection.designer(null);
        assertThat(regularInspection.getDesigner()).isNull();
    }

    @Test
    void checkpersonTest() {
        RegularInspection regularInspection = getRegularInspectionRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        regularInspection.setCheckperson(hrManagementBack);
        assertThat(regularInspection.getCheckperson()).isEqualTo(hrManagementBack);

        regularInspection.checkperson(null);
        assertThat(regularInspection.getCheckperson()).isNull();
    }

    @Test
    void workbagTest() {
        RegularInspection regularInspection = getRegularInspectionRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        regularInspection.setWorkbag(workbagBack);
        assertThat(regularInspection.getWorkbag()).isEqualTo(workbagBack);

        regularInspection.workbag(null);
        assertThat(regularInspection.getWorkbag()).isNull();
    }
}
