package com.cvicse.domain;

import java.util.UUID;

public class ProjectHumanresourcesplanTestSamples {

    public static ProjectHumanresourcesplan getProjectHumanresourcesplanSample1() {
        return new ProjectHumanresourcesplan().id("id1").projectname("projectname1").clientname("clientname1");
    }

    public static ProjectHumanresourcesplan getProjectHumanresourcesplanSample2() {
        return new ProjectHumanresourcesplan().id("id2").projectname("projectname2").clientname("clientname2");
    }

    public static ProjectHumanresourcesplan getProjectHumanresourcesplanRandomSampleGenerator() {
        return new ProjectHumanresourcesplan()
            .id(UUID.randomUUID().toString())
            .projectname(UUID.randomUUID().toString())
            .clientname(UUID.randomUUID().toString());
    }
}
