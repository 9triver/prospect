package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.CommunicationDictionaryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommunicationDictionaryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommunicationDictionary.class);
        CommunicationDictionary communicationDictionary1 = getCommunicationDictionarySample1();
        CommunicationDictionary communicationDictionary2 = new CommunicationDictionary();
        assertThat(communicationDictionary1).isNotEqualTo(communicationDictionary2);

        communicationDictionary2.setId(communicationDictionary1.getId());
        assertThat(communicationDictionary1).isEqualTo(communicationDictionary2);

        communicationDictionary2 = getCommunicationDictionarySample2();
        assertThat(communicationDictionary1).isNotEqualTo(communicationDictionary2);
    }
}
