package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ArchivesTestSamples.*;
import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ArchivesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Archives.class);
        Archives archives1 = getArchivesSample1();
        Archives archives2 = new Archives();
        assertThat(archives1).isNotEqualTo(archives2);

        archives2.setId(archives1.getId());
        assertThat(archives1).isEqualTo(archives2);

        archives2 = getArchivesSample2();
        assertThat(archives1).isNotEqualTo(archives2);
    }

    @Test
    void responsibleidTest() {
        Archives archives = getArchivesRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        archives.setResponsibleid(hrManagementBack);
        assertThat(archives.getResponsibleid()).isEqualTo(hrManagementBack);

        archives.responsibleid(null);
        assertThat(archives.getResponsibleid()).isNull();
    }
}
