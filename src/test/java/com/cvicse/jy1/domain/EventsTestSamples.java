package com.cvicse.jy1.domain;

import java.util.UUID;

public class EventsTestSamples {

    public static Events getEventsSample1() {
        return new Events()
            .id("id1")
            .title("title1")
            .content("content1")
            .place("place1")
            .participants("participants1")
            .picture("picture1")
            .description("description1")
            .attachment("attachment1");
    }

    public static Events getEventsSample2() {
        return new Events()
            .id("id2")
            .title("title2")
            .content("content2")
            .place("place2")
            .participants("participants2")
            .picture("picture2")
            .description("description2")
            .attachment("attachment2");
    }

    public static Events getEventsRandomSampleGenerator() {
        return new Events()
            .id(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .content(UUID.randomUUID().toString())
            .place(UUID.randomUUID().toString())
            .participants(UUID.randomUUID().toString())
            .picture(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .attachment(UUID.randomUUID().toString());
    }
}
