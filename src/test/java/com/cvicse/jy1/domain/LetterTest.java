package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.FrontlineTestSamples.*;
import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.LetterTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LetterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Letter.class);
        Letter letter1 = getLetterSample1();
        Letter letter2 = new Letter();
        assertThat(letter1).isNotEqualTo(letter2);

        letter2.setId(letter1.getId());
        assertThat(letter1).isEqualTo(letter2);

        letter2 = getLetterSample2();
        assertThat(letter1).isNotEqualTo(letter2);
    }

    @Test
    void wbsidTest() {
        Letter letter = getLetterRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        letter.setWbsid(projectwbsBack);
        assertThat(letter.getWbsid()).isEqualTo(projectwbsBack);

        letter.wbsid(null);
        assertThat(letter.getWbsid()).isNull();
    }

    @Test
    void workbagidTest() {
        Letter letter = getLetterRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        letter.setWorkbagid(workbagBack);
        assertThat(letter.getWorkbagid()).isEqualTo(workbagBack);

        letter.workbagid(null);
        assertThat(letter.getWorkbagid()).isNull();
    }

    @Test
    void frontlineidTest() {
        Letter letter = getLetterRandomSampleGenerator();
        Frontline frontlineBack = getFrontlineRandomSampleGenerator();

        letter.setFrontlineid(frontlineBack);
        assertThat(letter.getFrontlineid()).isEqualTo(frontlineBack);

        letter.frontlineid(null);
        assertThat(letter.getFrontlineid()).isNull();
    }

    @Test
    void receivingunitTest() {
        Letter letter = getLetterRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        letter.setReceivingunit(departmentBack);
        assertThat(letter.getReceivingunit()).isEqualTo(departmentBack);

        letter.receivingunit(null);
        assertThat(letter.getReceivingunit()).isNull();
    }

    @Test
    void sendingunitTest() {
        Letter letter = getLetterRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        letter.setSendingunit(departmentBack);
        assertThat(letter.getSendingunit()).isEqualTo(departmentBack);

        letter.sendingunit(null);
        assertThat(letter.getSendingunit()).isNull();
    }

    @Test
    void lettermakerTest() {
        Letter letter = getLetterRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        letter.setLettermaker(hrManagementBack);
        assertThat(letter.getLettermaker()).isEqualTo(hrManagementBack);

        letter.lettermaker(null);
        assertThat(letter.getLettermaker()).isNull();
    }

    @Test
    void letterreceiverTest() {
        Letter letter = getLetterRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        letter.setLetterreceiver(hrManagementBack);
        assertThat(letter.getLetterreceiver()).isEqualTo(hrManagementBack);

        letter.letterreceiver(null);
        assertThat(letter.getLetterreceiver()).isNull();
    }

    @Test
    void letterhandlerTest() {
        Letter letter = getLetterRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        letter.setLetterhandler(hrManagementBack);
        assertThat(letter.getLetterhandler()).isEqualTo(hrManagementBack);

        letter.letterhandler(null);
        assertThat(letter.getLetterhandler()).isNull();
    }
}
