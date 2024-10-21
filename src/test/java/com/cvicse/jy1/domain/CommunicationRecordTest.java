package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.CommunicationDictionaryTestSamples.*;
import static com.cvicse.jy1.domain.CommunicationFormDictionaryTestSamples.*;
import static com.cvicse.jy1.domain.CommunicationRecordTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommunicationRecordTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommunicationRecord.class);
        CommunicationRecord communicationRecord1 = getCommunicationRecordSample1();
        CommunicationRecord communicationRecord2 = new CommunicationRecord();
        assertThat(communicationRecord1).isNotEqualTo(communicationRecord2);

        communicationRecord2.setId(communicationRecord1.getId());
        assertThat(communicationRecord1).isEqualTo(communicationRecord2);

        communicationRecord2 = getCommunicationRecordSample2();
        assertThat(communicationRecord1).isNotEqualTo(communicationRecord2);
    }

    @Test
    void projectwbsTest() {
        CommunicationRecord communicationRecord = getCommunicationRecordRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        communicationRecord.setProjectwbs(projectwbsBack);
        assertThat(communicationRecord.getProjectwbs()).isEqualTo(projectwbsBack);

        communicationRecord.projectwbs(null);
        assertThat(communicationRecord.getProjectwbs()).isNull();
    }

    @Test
    void workbagTest() {
        CommunicationRecord communicationRecord = getCommunicationRecordRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        communicationRecord.setWorkbag(workbagBack);
        assertThat(communicationRecord.getWorkbag()).isEqualTo(workbagBack);

        communicationRecord.workbag(null);
        assertThat(communicationRecord.getWorkbag()).isNull();
    }

    @Test
    void communicationTest() {
        CommunicationRecord communicationRecord = getCommunicationRecordRandomSampleGenerator();
        CommunicationDictionary communicationDictionaryBack = getCommunicationDictionaryRandomSampleGenerator();

        communicationRecord.setCommunication(communicationDictionaryBack);
        assertThat(communicationRecord.getCommunication()).isEqualTo(communicationDictionaryBack);

        communicationRecord.communication(null);
        assertThat(communicationRecord.getCommunication()).isNull();
    }

    @Test
    void workCommunicationFormTest() {
        CommunicationRecord communicationRecord = getCommunicationRecordRandomSampleGenerator();
        CommunicationFormDictionary communicationFormDictionaryBack = getCommunicationFormDictionaryRandomSampleGenerator();

        communicationRecord.setWorkCommunicationForm(communicationFormDictionaryBack);
        assertThat(communicationRecord.getWorkCommunicationForm()).isEqualTo(communicationFormDictionaryBack);

        communicationRecord.workCommunicationForm(null);
        assertThat(communicationRecord.getWorkCommunicationForm()).isNull();
    }
}
