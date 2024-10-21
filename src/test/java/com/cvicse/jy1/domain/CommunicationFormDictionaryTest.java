package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.CommunicationFormDictionaryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommunicationFormDictionaryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommunicationFormDictionary.class);
        CommunicationFormDictionary communicationFormDictionary1 = getCommunicationFormDictionarySample1();
        CommunicationFormDictionary communicationFormDictionary2 = new CommunicationFormDictionary();
        assertThat(communicationFormDictionary1).isNotEqualTo(communicationFormDictionary2);

        communicationFormDictionary2.setId(communicationFormDictionary1.getId());
        assertThat(communicationFormDictionary1).isEqualTo(communicationFormDictionary2);

        communicationFormDictionary2 = getCommunicationFormDictionarySample2();
        assertThat(communicationFormDictionary1).isNotEqualTo(communicationFormDictionary2);
    }
}
