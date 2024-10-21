package com.cvicse.jy1.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ProjectRiskTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ProjectRisk getProjectRiskSample1() {
        return new ProjectRisk()
            .id(1)
            .year(1L)
            .name("name1")
            .riskcontent("riskcontent1")
            .riskreason("riskreason1")
            .importantrange("importantrange1")
            .measuresandtimelimit("measuresandtimelimit1")
            .conditions("conditions1")
            .closedloopindicator("closedloopindicator1");
    }

    public static ProjectRisk getProjectRiskSample2() {
        return new ProjectRisk()
            .id(2)
            .year(2L)
            .name("name2")
            .riskcontent("riskcontent2")
            .riskreason("riskreason2")
            .importantrange("importantrange2")
            .measuresandtimelimit("measuresandtimelimit2")
            .conditions("conditions2")
            .closedloopindicator("closedloopindicator2");
    }

    public static ProjectRisk getProjectRiskRandomSampleGenerator() {
        return new ProjectRisk()
            .id(intCount.incrementAndGet())
            .year(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .riskcontent(UUID.randomUUID().toString())
            .riskreason(UUID.randomUUID().toString())
            .importantrange(UUID.randomUUID().toString())
            .measuresandtimelimit(UUID.randomUUID().toString())
            .conditions(UUID.randomUUID().toString())
            .closedloopindicator(UUID.randomUUID().toString());
    }
}
