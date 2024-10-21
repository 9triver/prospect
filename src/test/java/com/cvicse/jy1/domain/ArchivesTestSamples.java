package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ArchivesTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Archives getArchivesSample1() {
        return new Archives()
            .id("id1")
            .title("title1")
            .content("content1")
            .page(1)
            .confidentialnumber("confidentialnumber1")
            .plannumber("plannumber1")
            .remarks("remarks1")
            .receivingnumber("receivingnumber1");
    }

    public static Archives getArchivesSample2() {
        return new Archives()
            .id("id2")
            .title("title2")
            .content("content2")
            .page(2)
            .confidentialnumber("confidentialnumber2")
            .plannumber("plannumber2")
            .remarks("remarks2")
            .receivingnumber("receivingnumber2");
    }

    public static Archives getArchivesRandomSampleGenerator() {
        return new Archives()
            .id(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .content(UUID.randomUUID().toString())
            .page(intCount.incrementAndGet())
            .confidentialnumber(UUID.randomUUID().toString())
            .plannumber(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString())
            .receivingnumber(UUID.randomUUID().toString());
    }
}
