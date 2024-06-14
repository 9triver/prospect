package com.cvicse.web.rest;

import static com.cvicse.domain.CycleplanAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Cycleplan;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Cycleplanstatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.CycleplanRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CycleplanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CycleplanResourceIT {

    private static final Long DEFAULT_CYCLEPLANID = 1L;
    private static final Long UPDATED_CYCLEPLANID = 2L;

    private static final String DEFAULT_CYCLEPLANNAME = "AAAAAAAAAA";
    private static final String UPDATED_CYCLEPLANNAME = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACTUALSTARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUALSTARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACTUALENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUALENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESPONSIBLENAME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSIBLENAME = "BBBBBBBBBB";

    private static final Cycleplanstatus DEFAULT_STATUS = Cycleplanstatus.IN_DEADLINE;
    private static final Cycleplanstatus UPDATED_STATUS = Cycleplanstatus.OVERDUE;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/cycleplans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CycleplanRepository cycleplanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCycleplanMockMvc;

    private Cycleplan cycleplan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cycleplan createEntity(EntityManager em) {
        Cycleplan cycleplan = new Cycleplan()
            .cycleplanid(DEFAULT_CYCLEPLANID)
            .cycleplanname(DEFAULT_CYCLEPLANNAME)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .actualstarttime(DEFAULT_ACTUALSTARTTIME)
            .actualendtime(DEFAULT_ACTUALENDTIME)
            .responsiblename(DEFAULT_RESPONSIBLENAME)
            .status(DEFAULT_STATUS)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return cycleplan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cycleplan createUpdatedEntity(EntityManager em) {
        Cycleplan cycleplan = new Cycleplan()
            .cycleplanid(UPDATED_CYCLEPLANID)
            .cycleplanname(UPDATED_CYCLEPLANNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return cycleplan;
    }

    @BeforeEach
    public void initTest() {
        cycleplan = createEntity(em);
    }

    @Test
    @Transactional
    void createCycleplan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Cycleplan
        var returnedCycleplan = om.readValue(
            restCycleplanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cycleplan)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Cycleplan.class
        );

        // Validate the Cycleplan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCycleplanUpdatableFieldsEquals(returnedCycleplan, getPersistedCycleplan(returnedCycleplan));
    }

    @Test
    @Transactional
    void createCycleplanWithExistingId() throws Exception {
        // Create the Cycleplan with an existing ID
        cycleplan.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCycleplanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cycleplan)))
            .andExpect(status().isBadRequest());

        // Validate the Cycleplan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCycleplans() throws Exception {
        // Initialize the database
        cycleplanRepository.saveAndFlush(cycleplan);

        // Get all the cycleplanList
        restCycleplanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cycleplan.getId().intValue())))
            .andExpect(jsonPath("$.[*].cycleplanid").value(hasItem(DEFAULT_CYCLEPLANID.intValue())))
            .andExpect(jsonPath("$.[*].cycleplanname").value(hasItem(DEFAULT_CYCLEPLANNAME)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].actualstarttime").value(hasItem(DEFAULT_ACTUALSTARTTIME.toString())))
            .andExpect(jsonPath("$.[*].actualendtime").value(hasItem(DEFAULT_ACTUALENDTIME.toString())))
            .andExpect(jsonPath("$.[*].responsiblename").value(hasItem(DEFAULT_RESPONSIBLENAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getCycleplan() throws Exception {
        // Initialize the database
        cycleplanRepository.saveAndFlush(cycleplan);

        // Get the cycleplan
        restCycleplanMockMvc
            .perform(get(ENTITY_API_URL_ID, cycleplan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cycleplan.getId().intValue()))
            .andExpect(jsonPath("$.cycleplanid").value(DEFAULT_CYCLEPLANID.intValue()))
            .andExpect(jsonPath("$.cycleplanname").value(DEFAULT_CYCLEPLANNAME))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.actualstarttime").value(DEFAULT_ACTUALSTARTTIME.toString()))
            .andExpect(jsonPath("$.actualendtime").value(DEFAULT_ACTUALENDTIME.toString()))
            .andExpect(jsonPath("$.responsiblename").value(DEFAULT_RESPONSIBLENAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCycleplan() throws Exception {
        // Get the cycleplan
        restCycleplanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCycleplan() throws Exception {
        // Initialize the database
        cycleplanRepository.saveAndFlush(cycleplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cycleplan
        Cycleplan updatedCycleplan = cycleplanRepository.findById(cycleplan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCycleplan are not directly saved in db
        em.detach(updatedCycleplan);
        updatedCycleplan
            .cycleplanid(UPDATED_CYCLEPLANID)
            .cycleplanname(UPDATED_CYCLEPLANNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restCycleplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCycleplan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCycleplan))
            )
            .andExpect(status().isOk());

        // Validate the Cycleplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCycleplanToMatchAllProperties(updatedCycleplan);
    }

    @Test
    @Transactional
    void putNonExistingCycleplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cycleplan.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCycleplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cycleplan.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cycleplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cycleplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCycleplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cycleplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCycleplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cycleplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cycleplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCycleplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cycleplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCycleplanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cycleplan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cycleplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCycleplanWithPatch() throws Exception {
        // Initialize the database
        cycleplanRepository.saveAndFlush(cycleplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cycleplan using partial update
        Cycleplan partialUpdatedCycleplan = new Cycleplan();
        partialUpdatedCycleplan.setId(cycleplan.getId());

        partialUpdatedCycleplan
            .cycleplanid(UPDATED_CYCLEPLANID)
            .secretlevel(UPDATED_SECRETLEVEL)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .actualendtime(UPDATED_ACTUALENDTIME);

        restCycleplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCycleplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCycleplan))
            )
            .andExpect(status().isOk());

        // Validate the Cycleplan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCycleplanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCycleplan, cycleplan),
            getPersistedCycleplan(cycleplan)
        );
    }

    @Test
    @Transactional
    void fullUpdateCycleplanWithPatch() throws Exception {
        // Initialize the database
        cycleplanRepository.saveAndFlush(cycleplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cycleplan using partial update
        Cycleplan partialUpdatedCycleplan = new Cycleplan();
        partialUpdatedCycleplan.setId(cycleplan.getId());

        partialUpdatedCycleplan
            .cycleplanid(UPDATED_CYCLEPLANID)
            .cycleplanname(UPDATED_CYCLEPLANNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restCycleplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCycleplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCycleplan))
            )
            .andExpect(status().isOk());

        // Validate the Cycleplan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCycleplanUpdatableFieldsEquals(partialUpdatedCycleplan, getPersistedCycleplan(partialUpdatedCycleplan));
    }

    @Test
    @Transactional
    void patchNonExistingCycleplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cycleplan.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCycleplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cycleplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cycleplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cycleplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCycleplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cycleplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCycleplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cycleplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cycleplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCycleplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cycleplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCycleplanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cycleplan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cycleplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCycleplan() throws Exception {
        // Initialize the database
        cycleplanRepository.saveAndFlush(cycleplan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cycleplan
        restCycleplanMockMvc
            .perform(delete(ENTITY_API_URL_ID, cycleplan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cycleplanRepository.count();
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

    protected Cycleplan getPersistedCycleplan(Cycleplan cycleplan) {
        return cycleplanRepository.findById(cycleplan.getId()).orElseThrow();
    }

    protected void assertPersistedCycleplanToMatchAllProperties(Cycleplan expectedCycleplan) {
        assertCycleplanAllPropertiesEquals(expectedCycleplan, getPersistedCycleplan(expectedCycleplan));
    }

    protected void assertPersistedCycleplanToMatchUpdatableProperties(Cycleplan expectedCycleplan) {
        assertCycleplanAllUpdatablePropertiesEquals(expectedCycleplan, getPersistedCycleplan(expectedCycleplan));
    }
}
