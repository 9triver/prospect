package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.EventsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EventsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Events.class);
        Events events1 = getEventsSample1();
        Events events2 = new Events();
        assertThat(events1).isNotEqualTo(events2);

        events2.setId(events1.getId());
        assertThat(events1).isEqualTo(events2);

        events2 = getEventsSample2();
        assertThat(events1).isNotEqualTo(events2);
    }
}
