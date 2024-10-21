package com.cvicse.jy1.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class UnQualityAuditAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertUnQualityAuditAllPropertiesEquals(UnQualityAudit expected, UnQualityAudit actual) {
        assertUnQualityAuditAutoGeneratedPropertiesEquals(expected, actual);
        assertUnQualityAuditAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertUnQualityAuditAllUpdatablePropertiesEquals(UnQualityAudit expected, UnQualityAudit actual) {
        assertUnQualityAuditUpdatableFieldsEquals(expected, actual);
        assertUnQualityAuditUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertUnQualityAuditAutoGeneratedPropertiesEquals(UnQualityAudit expected, UnQualityAudit actual) {
        assertThat(expected)
            .as("Verify UnQualityAudit auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertUnQualityAuditUpdatableFieldsEquals(UnQualityAudit expected, UnQualityAudit actual) {
        assertThat(expected)
            .as("Verify UnQualityAudit relevant properties")
            .satisfies(e -> assertThat(e.getWorkbagid()).as("check workbagid").isEqualTo(actual.getWorkbagid()))
            .satisfies(e -> assertThat(e.getBelongwbsid()).as("check belongwbsid").isEqualTo(actual.getBelongwbsid()))
            .satisfies(
                e -> assertThat(e.getOutsourcingcontractid()).as("check outsourcingcontractid").isEqualTo(actual.getOutsourcingcontractid())
            )
            .satisfies(e -> assertThat(e.getUnqualityid()).as("check unqualityid").isEqualTo(actual.getUnqualityid()))
            .satisfies(e -> assertThat(e.getUnqualityname()).as("check unqualityname").isEqualTo(actual.getUnqualityname()))
            .satisfies(e -> assertThat(e.getUnqualityunit()).as("check unqualityunit").isEqualTo(actual.getUnqualityunit()))
            .satisfies(
                e -> assertThat(e.getUnqualitytrialgroup()).as("check unqualitytrialgroup").isEqualTo(actual.getUnqualitytrialgroup())
            )
            .satisfies(e -> assertThat(e.getInspector()).as("check inspector").isEqualTo(actual.getInspector()))
            .satisfies(e -> assertThat(e.getUnqualitystage()).as("check unqualitystage").isEqualTo(actual.getUnqualitystage()))
            .satisfies(e -> assertThat(e.getUnqualitynumber()).as("check unqualitynumber").isEqualTo(actual.getUnqualitynumber()))
            .satisfies(
                e -> assertThat(e.getUnqualityintroduction()).as("check unqualityintroduction").isEqualTo(actual.getUnqualityintroduction())
            )
            .satisfies(e -> assertThat(e.getUnqualitycategory()).as("check unqualitycategory").isEqualTo(actual.getUnqualitycategory()))
            .satisfies(e -> assertThat(e.getHandlingopinion()).as("check handlingopinion").isEqualTo(actual.getHandlingopinion()))
            .satisfies(e -> assertThat(e.getApplicant()).as("check applicant").isEqualTo(actual.getApplicant()))
            .satisfies(e -> assertThat(e.getApplicationdate()).as("check applicationdate").isEqualTo(actual.getApplicationdate()))
            .satisfies(e -> assertThat(e.getAuditStatus()).as("check auditStatus").isEqualTo(actual.getAuditStatus()))
            .satisfies(e -> assertThat(e.getAttachment()).as("check attachment").isEqualTo(actual.getAttachment()))
            .satisfies(e -> assertThat(e.getDisposalmethod()).as("check disposalmethod").isEqualTo(actual.getDisposalmethod()))
            .satisfies(e -> assertThat(e.getCauseanalysis()).as("check causeanalysis").isEqualTo(actual.getCauseanalysis()))
            .satisfies(e -> assertThat(e.getCorrectivemeasures()).as("check correctivemeasures").isEqualTo(actual.getCorrectivemeasures()))
            .satisfies(e -> assertThat(e.getRemarks()).as("check remarks").isEqualTo(actual.getRemarks()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertUnQualityAuditUpdatableRelationshipsEquals(UnQualityAudit expected, UnQualityAudit actual) {
        assertThat(expected)
            .as("Verify UnQualityAudit relationships")
            .satisfies(e -> assertThat(e.getWorkbag()).as("check workbag").isEqualTo(actual.getWorkbag()));
    }
}
