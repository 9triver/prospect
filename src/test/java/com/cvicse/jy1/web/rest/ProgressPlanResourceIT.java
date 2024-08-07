package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ProgressPlanAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.ProgressPlan;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.PlanLevel;
import com.cvicse.jy1.domain.enumeration.Planstatus;
import com.cvicse.jy1.domain.enumeration.Progressstatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.ProgressPlanRepository;
import com.cvicse.jy1.service.ProgressPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ProgressPlanResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ProgressPlanResourceIT {

    private static final String DEFAULT_PLANNAME = "AAAAAAAAAA";
    private static final String UPDATED_PLANNAME = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final Integer DEFAULT_PLANTYPE = 1;
    private static final Integer UPDATED_PLANTYPE = 2;

    private static final PlanLevel DEFAULT_PLANLEVEL = PlanLevel.CYCLE;
    private static final PlanLevel UPDATED_PLANLEVEL = PlanLevel.STAGE;

    private static final String DEFAULT_BELONGPLANID = "AAAAAAAAAA";
    private static final String UPDATED_BELONGPLANID = "BBBBBBBBBB";

    private static final String DEFAULT_PLANSTAGE = "AAAAAAAAAA";
    private static final String UPDATED_PLANSTAGE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_READYTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_READYTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERABLES = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERABLES = "BBBBBBBBBB";

    private static final String DEFAULT_PLANOBJECTIVES = "AAAAAAAAAA";
    private static final String UPDATED_PLANOBJECTIVES = "BBBBBBBBBB";

    private static final String DEFAULT_PREPLAN = "AAAAAAAAAA";
    private static final String UPDATED_PREPLAN = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACTUALSTARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUALSTARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACTUALENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUALENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PROGRESS = 1;
    private static final Integer UPDATED_PROGRESS = 2;

    private static final Progressstatus DEFAULT_PROGRESSTYPE = Progressstatus.Not_start;
    private static final Progressstatus UPDATED_PROGRESSTYPE = Progressstatus.Start;

    private static final Integer DEFAULT_ISKEY = 1;
    private static final Integer UPDATED_ISKEY = 2;

    private static final Planstatus DEFAULT_STATUS = Planstatus.IN_DEADLINE;
    private static final Planstatus UPDATED_STATUS = Planstatus.OVERDUE;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/progress-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProgressPlanRepository progressPlanRepository;

    @Mock
    private ProgressPlanRepository progressPlanRepositoryMock;

    @Mock
    private ProgressPlanService progressPlanServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProgressPlanMockMvc;

    private ProgressPlan progressPlan;

    private ProgressPlan insertedProgressPlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProgressPlan createEntity(EntityManager em) {
        ProgressPlan progressPlan = new ProgressPlan()
            .planname(DEFAULT_PLANNAME)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .plantype(DEFAULT_PLANTYPE)
            .planlevel(DEFAULT_PLANLEVEL)
            .belongplanid(DEFAULT_BELONGPLANID)
            .planstage(DEFAULT_PLANSTAGE)
            .readytime(DEFAULT_READYTIME)
            .description(DEFAULT_DESCRIPTION)
            .deliverables(DEFAULT_DELIVERABLES)
            .planobjectives(DEFAULT_PLANOBJECTIVES)
            .preplan(DEFAULT_PREPLAN)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .actualstarttime(DEFAULT_ACTUALSTARTTIME)
            .actualendtime(DEFAULT_ACTUALENDTIME)
            .progress(DEFAULT_PROGRESS)
            .progresstype(DEFAULT_PROGRESSTYPE)
            .iskey(DEFAULT_ISKEY)
            .status(DEFAULT_STATUS)
            .auditStatus(DEFAULT_AUDIT_STATUS)
            .remark(DEFAULT_REMARK);
        return progressPlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProgressPlan createUpdatedEntity(EntityManager em) {
        ProgressPlan progressPlan = new ProgressPlan()
            .planname(UPDATED_PLANNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .plantype(UPDATED_PLANTYPE)
            .planlevel(UPDATED_PLANLEVEL)
            .belongplanid(UPDATED_BELONGPLANID)
            .planstage(UPDATED_PLANSTAGE)
            .readytime(UPDATED_READYTIME)
            .description(UPDATED_DESCRIPTION)
            .deliverables(UPDATED_DELIVERABLES)
            .planobjectives(UPDATED_PLANOBJECTIVES)
            .preplan(UPDATED_PREPLAN)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .progress(UPDATED_PROGRESS)
            .progresstype(UPDATED_PROGRESSTYPE)
            .iskey(UPDATED_ISKEY)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .remark(UPDATED_REMARK);
        return progressPlan;
    }

    @BeforeEach
    public void initTest() {
        progressPlan = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedProgressPlan != null) {
            progressPlanRepository.delete(insertedProgressPlan);
            insertedProgressPlan = null;
        }
    }

    @Test
    @Transactional
    void createProgressPlan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ProgressPlan
        var returnedProgressPlan = om.readValue(
            restProgressPlanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressPlan)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ProgressPlan.class
        );

        // Validate the ProgressPlan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProgressPlanUpdatableFieldsEquals(returnedProgressPlan, getPersistedProgressPlan(returnedProgressPlan));

        insertedProgressPlan = returnedProgressPlan;
    }

    @Test
    @Transactional
    void createProgressPlanWithExistingId() throws Exception {
        // Create the ProgressPlan with an existing ID
        progressPlan.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgressPlanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressPlan)))
            .andExpect(status().isBadRequest());

        // Validate the ProgressPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProgressPlans() throws Exception {
        // Initialize the database
        insertedProgressPlan = progressPlanRepository.saveAndFlush(progressPlan);

        // Get all the progressPlanList
        restProgressPlanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(progressPlan.getId())))
            .andExpect(jsonPath("$.[*].planname").value(hasItem(DEFAULT_PLANNAME)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].plantype").value(hasItem(DEFAULT_PLANTYPE)))
            .andExpect(jsonPath("$.[*].planlevel").value(hasItem(DEFAULT_PLANLEVEL.toString())))
            .andExpect(jsonPath("$.[*].planstage").value(hasItem(DEFAULT_PLANSTAGE)))
            .andExpect(jsonPath("$.[*].readytime").value(hasItem(DEFAULT_READYTIME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].deliverables").value(hasItem(DEFAULT_DELIVERABLES)))
            .andExpect(jsonPath("$.[*].planobjectives").value(hasItem(DEFAULT_PLANOBJECTIVES)))
            .andExpect(jsonPath("$.[*].preplan").value(hasItem(DEFAULT_PREPLAN)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].actualstarttime").value(hasItem(DEFAULT_ACTUALSTARTTIME.toString())))
            .andExpect(jsonPath("$.[*].actualendtime").value(hasItem(DEFAULT_ACTUALENDTIME.toString())))
            .andExpect(jsonPath("$.[*].progress").value(hasItem(DEFAULT_PROGRESS)))
            .andExpect(jsonPath("$.[*].progresstype").value(hasItem(DEFAULT_PROGRESSTYPE.toString())))
            .andExpect(jsonPath("$.[*].iskey").value(hasItem(DEFAULT_ISKEY)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProgressPlansWithEagerRelationshipsIsEnabled() throws Exception {
        when(progressPlanServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProgressPlanMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(progressPlanServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProgressPlansWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(progressPlanServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProgressPlanMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(progressPlanRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getProgressPlan() throws Exception {
        // Initialize the database
        insertedProgressPlan = progressPlanRepository.saveAndFlush(progressPlan);

        // Get the progressPlan
        restProgressPlanMockMvc
            .perform(get(ENTITY_API_URL_ID, progressPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(progressPlan.getId()))
            .andExpect(jsonPath("$.planname").value(DEFAULT_PLANNAME))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.plantype").value(DEFAULT_PLANTYPE))
            .andExpect(jsonPath("$.planlevel").value(DEFAULT_PLANLEVEL.toString()))
            .andExpect(jsonPath("$.planstage").value(DEFAULT_PLANSTAGE))
            .andExpect(jsonPath("$.readytime").value(DEFAULT_READYTIME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.deliverables").value(DEFAULT_DELIVERABLES))
            .andExpect(jsonPath("$.planobjectives").value(DEFAULT_PLANOBJECTIVES))
            .andExpect(jsonPath("$.preplan").value(DEFAULT_PREPLAN))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.actualstarttime").value(DEFAULT_ACTUALSTARTTIME.toString()))
            .andExpect(jsonPath("$.actualendtime").value(DEFAULT_ACTUALENDTIME.toString()))
            .andExpect(jsonPath("$.progress").value(DEFAULT_PROGRESS))
            .andExpect(jsonPath("$.progresstype").value(DEFAULT_PROGRESSTYPE.toString()))
            .andExpect(jsonPath("$.iskey").value(DEFAULT_ISKEY))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK));
    }

    @Test
    @Transactional
    void getNonExistingProgressPlan() throws Exception {
        // Get the progressPlan
        restProgressPlanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProgressPlan() throws Exception {
        // Initialize the database
        insertedProgressPlan = progressPlanRepository.saveAndFlush(progressPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressPlan
        ProgressPlan updatedProgressPlan = progressPlanRepository.findById(progressPlan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProgressPlan are not directly saved in db
        em.detach(updatedProgressPlan);
        updatedProgressPlan
            .planname(UPDATED_PLANNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .plantype(UPDATED_PLANTYPE)
            .planlevel(UPDATED_PLANLEVEL)
            .planstage(UPDATED_PLANSTAGE)
            .readytime(UPDATED_READYTIME)
            .description(UPDATED_DESCRIPTION)
            .deliverables(UPDATED_DELIVERABLES)
            .planobjectives(UPDATED_PLANOBJECTIVES)
            .preplan(UPDATED_PREPLAN)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .progress(UPDATED_PROGRESS)
            .progresstype(UPDATED_PROGRESSTYPE)
            .iskey(UPDATED_ISKEY)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .remark(UPDATED_REMARK);

        restProgressPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProgressPlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProgressPlan))
            )
            .andExpect(status().isOk());

        // Validate the ProgressPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProgressPlanToMatchAllProperties(updatedProgressPlan);
    }

    @Test
    @Transactional
    void putNonExistingProgressPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressPlan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, progressPlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProgressPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProgressPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProgressPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProgressPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressPlanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressPlan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProgressPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProgressPlanWithPatch() throws Exception {
        // Initialize the database
        insertedProgressPlan = progressPlanRepository.saveAndFlush(progressPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressPlan using partial update
        ProgressPlan partialUpdatedProgressPlan = new ProgressPlan();
        partialUpdatedProgressPlan.setId(progressPlan.getId());

        partialUpdatedProgressPlan
            .planname(UPDATED_PLANNAME)
            .planstage(UPDATED_PLANSTAGE)
            .readytime(UPDATED_READYTIME)
            .preplan(UPDATED_PREPLAN)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .progresstype(UPDATED_PROGRESSTYPE)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProgressPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressPlan))
            )
            .andExpect(status().isOk());

        // Validate the ProgressPlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressPlanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProgressPlan, progressPlan),
            getPersistedProgressPlan(progressPlan)
        );
    }

    @Test
    @Transactional
    void fullUpdateProgressPlanWithPatch() throws Exception {
        // Initialize the database
        insertedProgressPlan = progressPlanRepository.saveAndFlush(progressPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressPlan using partial update
        ProgressPlan partialUpdatedProgressPlan = new ProgressPlan();
        partialUpdatedProgressPlan.setId(progressPlan.getId());

        partialUpdatedProgressPlan
            .planname(UPDATED_PLANNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .plantype(UPDATED_PLANTYPE)
            .planlevel(UPDATED_PLANLEVEL)
            .planstage(UPDATED_PLANSTAGE)
            .readytime(UPDATED_READYTIME)
            .description(UPDATED_DESCRIPTION)
            .deliverables(UPDATED_DELIVERABLES)
            .planobjectives(UPDATED_PLANOBJECTIVES)
            .preplan(UPDATED_PREPLAN)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .progress(UPDATED_PROGRESS)
            .progresstype(UPDATED_PROGRESSTYPE)
            .iskey(UPDATED_ISKEY)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .remark(UPDATED_REMARK);

        restProgressPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressPlan))
            )
            .andExpect(status().isOk());

        // Validate the ProgressPlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressPlanUpdatableFieldsEquals(partialUpdatedProgressPlan, getPersistedProgressPlan(partialUpdatedProgressPlan));
    }

    @Test
    @Transactional
    void patchNonExistingProgressPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressPlan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, progressPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProgressPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProgressPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProgressPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProgressPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressPlanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(progressPlan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProgressPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProgressPlan() throws Exception {
        // Initialize the database
        insertedProgressPlan = progressPlanRepository.saveAndFlush(progressPlan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the progressPlan
        restProgressPlanMockMvc
            .perform(delete(ENTITY_API_URL_ID, progressPlan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return progressPlanRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected ProgressPlan getPersistedProgressPlan(ProgressPlan progressPlan) {
        return progressPlanRepository.findById(progressPlan.getId()).orElseThrow();
    }

    protected void assertPersistedProgressPlanToMatchAllProperties(ProgressPlan expectedProgressPlan) {
        assertProgressPlanAllPropertiesEquals(expectedProgressPlan, getPersistedProgressPlan(expectedProgressPlan));
    }

    protected void assertPersistedProgressPlanToMatchUpdatableProperties(ProgressPlan expectedProgressPlan) {
        assertProgressPlanAllUpdatablePropertiesEquals(expectedProgressPlan, getPersistedProgressPlan(expectedProgressPlan));
    }
}
