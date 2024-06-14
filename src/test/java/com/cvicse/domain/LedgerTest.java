package com.cvicse.domain;

import static com.cvicse.domain.LedgerTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LedgerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ledger.class);
        Ledger ledger1 = getLedgerSample1();
        Ledger ledger2 = new Ledger();
        assertThat(ledger1).isNotEqualTo(ledger2);

        ledger2.setId(ledger1.getId());
        assertThat(ledger1).isEqualTo(ledger2);

        ledger2 = getLedgerSample2();
        assertThat(ledger1).isNotEqualTo(ledger2);
    }

    @Test
    void officersTest() throws Exception {
        Ledger ledger = getLedgerRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        ledger.setOfficers(officersBack);
        assertThat(ledger.getOfficers()).isEqualTo(officersBack);

        ledger.officers(null);
        assertThat(ledger.getOfficers()).isNull();
    }
}
