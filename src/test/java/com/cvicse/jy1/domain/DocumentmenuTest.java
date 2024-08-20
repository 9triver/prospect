package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DocumentmenuTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentmenuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Documentmenu.class);
        Documentmenu documentmenu1 = getDocumentmenuSample1();
        Documentmenu documentmenu2 = new Documentmenu();
        assertThat(documentmenu1).isNotEqualTo(documentmenu2);

        documentmenu2.setId(documentmenu1.getId());
        assertThat(documentmenu1).isEqualTo(documentmenu2);

        documentmenu2 = getDocumentmenuSample2();
        assertThat(documentmenu1).isNotEqualTo(documentmenu2);
    }
}
