package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.WorkTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class WorkTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Work.class);
        Work work1 = getWorkSample1();
        Work work2 = new Work();
        assertThat(work1).isNotEqualTo(work2);

        work2.setId(work1.getId());
        assertThat(work1).isEqualTo(work2);

        work2 = getWorkSample2();
        assertThat(work1).isNotEqualTo(work2);
    }

    @Test
    void workbagTest() {
        Work work = getWorkRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        work.addWorkbag(workbagBack);
        assertThat(work.getWorkbags()).containsOnly(workbagBack);
        assertThat(workbagBack.getWorks()).containsOnly(work);

        work.removeWorkbag(workbagBack);
        assertThat(work.getWorkbags()).doesNotContain(workbagBack);
        assertThat(workbagBack.getWorks()).doesNotContain(work);

        work.workbags(new HashSet<>(Set.of(workbagBack)));
        assertThat(work.getWorkbags()).containsOnly(workbagBack);
        assertThat(workbagBack.getWorks()).containsOnly(work);

        work.setWorkbags(new HashSet<>());
        assertThat(work.getWorkbags()).doesNotContain(workbagBack);
        assertThat(workbagBack.getWorks()).doesNotContain(work);
    }
}
