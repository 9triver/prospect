package com.cvicse.domain;

import static com.cvicse.domain.ApprovalAgentTestSamples.*;
import static com.cvicse.domain.DepartmentTestSamples.*;
import static com.cvicse.domain.DocumentTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PlanexecuteTestSamples.*;
import static com.cvicse.domain.ProjectchargeTestSamples.*;
import static com.cvicse.domain.RoleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OfficersTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Officers.class);
        Officers officers1 = getOfficersSample1();
        Officers officers2 = new Officers();
        assertThat(officers1).isNotEqualTo(officers2);

        officers2.setId(officers1.getId());
        assertThat(officers1).isEqualTo(officers2);

        officers2 = getOfficersSample2();
        assertThat(officers1).isNotEqualTo(officers2);
    }

    @Test
    void roleTest() throws Exception {
        Officers officers = getOfficersRandomSampleGenerator();
        Role roleBack = getRoleRandomSampleGenerator();

        officers.setRole(roleBack);
        assertThat(officers.getRole()).isEqualTo(roleBack);

        officers.role(null);
        assertThat(officers.getRole()).isNull();
    }

    @Test
    void departmentTest() throws Exception {
        Officers officers = getOfficersRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        officers.addDepartment(departmentBack);
        assertThat(officers.getDepartments()).containsOnly(departmentBack);
        assertThat(departmentBack.getOfficers()).containsOnly(officers);

        officers.removeDepartment(departmentBack);
        assertThat(officers.getDepartments()).doesNotContain(departmentBack);
        assertThat(departmentBack.getOfficers()).doesNotContain(officers);

        officers.departments(new HashSet<>(Set.of(departmentBack)));
        assertThat(officers.getDepartments()).containsOnly(departmentBack);
        assertThat(departmentBack.getOfficers()).containsOnly(officers);

        officers.setDepartments(new HashSet<>());
        assertThat(officers.getDepartments()).doesNotContain(departmentBack);
        assertThat(departmentBack.getOfficers()).doesNotContain(officers);
    }

    @Test
    void documentTest() throws Exception {
        Officers officers = getOfficersRandomSampleGenerator();
        Document documentBack = getDocumentRandomSampleGenerator();

        officers.setDocument(documentBack);
        assertThat(officers.getDocument()).isEqualTo(documentBack);
        assertThat(documentBack.getCreatorid()).isEqualTo(officers);

        officers.document(null);
        assertThat(officers.getDocument()).isNull();
        assertThat(documentBack.getCreatorid()).isNull();
    }

    @Test
    void planexecuteTest() throws Exception {
        Officers officers = getOfficersRandomSampleGenerator();
        Planexecute planexecuteBack = getPlanexecuteRandomSampleGenerator();

        officers.setPlanexecute(planexecuteBack);
        assertThat(officers.getPlanexecute()).isEqualTo(planexecuteBack);
        assertThat(planexecuteBack.getResponsibleid()).isEqualTo(officers);

        officers.planexecute(null);
        assertThat(officers.getPlanexecute()).isNull();
        assertThat(planexecuteBack.getResponsibleid()).isNull();
    }

    @Test
    void projectchargeTest() throws Exception {
        Officers officers = getOfficersRandomSampleGenerator();
        Projectcharge projectchargeBack = getProjectchargeRandomSampleGenerator();

        officers.setProjectcharge(projectchargeBack);
        assertThat(officers.getProjectcharge()).isEqualTo(projectchargeBack);
        assertThat(projectchargeBack.getCreatorid()).isEqualTo(officers);

        officers.projectcharge(null);
        assertThat(officers.getProjectcharge()).isNull();
        assertThat(projectchargeBack.getCreatorid()).isNull();
    }

    @Test
    void approvalAgentTest() throws Exception {
        Officers officers = getOfficersRandomSampleGenerator();
        ApprovalAgent approvalAgentBack = getApprovalAgentRandomSampleGenerator();

        officers.setApprovalAgent(approvalAgentBack);
        assertThat(officers.getApprovalAgent()).isEqualTo(approvalAgentBack);
        assertThat(approvalAgentBack.getOriginalapprovalid()).isEqualTo(officers);

        officers.approvalAgent(null);
        assertThat(officers.getApprovalAgent()).isNull();
        assertThat(approvalAgentBack.getOriginalapprovalid()).isNull();
    }
}
