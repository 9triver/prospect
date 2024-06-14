package com.cvicse.web.rest;

import static com.cvicse.domain.MonthplanAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Monthplan;
import com.cvicse.domain.enumeration.Annualplanstatus;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.MonthplanRepository;
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
 * Integration tests for the {@link MonthplanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MonthplanResourceIT {

    private static final Long DEFAULT_MONTHPLANID = 1L;
    private static final Long UPDATED_MONTHPLANID = 2L;

    private static final String DEFAULT_MONTHPLANNAME = "AAAAAAAAAA";
    private static final String UPDATED_MONTHPLANNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MONTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MONTH = LocalDate.now(ZoneId.systemDefault());

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final String DEFAULT_CREATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATORNAME = "BBBBBBBBBB";

    private static final Annualplanstatus DEFAULT_STATUS = Annualplanstatus.IN_PIPELINE;
    private static final Annualplanstatus UPDATED_STATUS = Annualplanstatus.NOTIFIED;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/monthplans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MonthplanRepository monthplanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMonthplanMockMvc;

    private Monthplan monthplan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Monthplan createEntity(EntityManager em) {
        Monthplan monthplan = new Monthplan()
            .monthplanid(DEFAULT_MONTHPLANID)
            .monthplanname(DEFAULT_MONTHPLANNAME)
            .month(DEFAULT_MONTH)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .creatorname(DEFAULT_CREATORNAME)
            .status(DEFAULT_STATUS)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return monthplan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Monthplan createUpdatedEntity(EntityManager em) {
        Monthplan monthplan = new Monthplan()
            .monthplanid(UPDATED_MONTHPLANID)
            .monthplanname(UPDATED_MONTHPLANNAME)
            .month(UPDATED_MONTH)
            .secretlevel(UPDATED_SECRETLEVEL)
            .creatorname(UPDATED_CREATORNAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return monthplan;
    }

    @BeforeEach
    public void initTest() {
        monthplan = createEntity(em);
    }

    @Test
    @Transactional
    void createMonthplan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Monthplan
        var returnedMonthplan = om.readValue(
            restMonthplanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(monthplan)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Monthplan.class
        );

        // Validate the Monthplan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMonthplanUpdatableFieldsEquals(returnedMonthplan, getPersistedMonthplan(returnedMonthplan));
    }

    @Test
    @Transactional
    void createMonthplanWithExistingId() throws Exception {
        // Create the Monthplan with an existing ID
        monthplan.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMonthplanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(monthplan)))
            .andExpect(status().isBadRequest());

        // Validate the Monthplan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMonthplans() throws Exception {
        // Initialize the database
        monthplanRepository.saveAndFlush(monthplan);

        // Get all the monthplanList
        restMonthplanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(monthplan.getId().intValue())))
            .andExpect(jsonPath("$.[*].monthplanid").value(hasItem(DEFAULT_MONTHPLANID.intValue())))
            .andExpect(jsonPath("$.[*].monthplanname").value(hasItem(DEFAULT_MONTHPLANNAME)))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH.toString())))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getMonthplan() throws Exception {
        // Initialize the database
        monthplanRepository.saveAndFlush(monthplan);

        // Get the monthplan
        restMonthplanMockMvc
            .perform(get(ENTITY_API_URL_ID, monthplan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(monthplan.getId().intValue()))
            .andExpect(jsonPath("$.monthplanid").value(DEFAULT_MONTHPLANID.intValue()))
            .andExpect(jsonPath("$.monthplanname").value(DEFAULT_MONTHPLANNAME))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH.toString()))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingMonthplan() throws Exception {
        // Get the monthplan
        restMonthplanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMonthplan() throws Exception {
        // Initialize the database
        monthplanRepository.saveAndFlush(monthplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the monthplan
        Monthplan updatedMonthplan = monthplanRepository.findById(monthplan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMonthplan are not directly saved in db
        em.detach(updatedMonthplan);
        updatedMonthplan
            .monthplanid(UPDATED_MONTHPLANID)
            .monthplanname(UPDATED_MONTHPLANNAME)
            .month(UPDATED_MONTH)
            .secretlevel(UPDATED_SECRETLEVEL)
            .creatorname(UPDATED_CREATORNAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restMonthplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMonthplan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedMonthplan))
            )
            .andExpect(status().isOk());

        // Validate the Monthplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMonthplanToMatchAllProperties(updatedMonthplan);
    }

    @Test
    @Transactional
    void putNonExistingMonthplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        monthplan.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMonthplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, monthplan.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(monthplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Monthplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMonthplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        monthplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMonthplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(monthplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Monthplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMonthplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        monthplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMonthplanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(monthplan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Monthplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMonthplanWithPatch() throws Exception {
        // Initialize the database
        monthplanRepository.saveAndFlush(monthplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the monthplan using partial update
        Monthplan partialUpdatedMonthplan = new Monthplan();
        partialUpdatedMonthplan.setId(monthplan.getId());

        partialUpdatedMonthplan.monthplanname(UPDATED_MONTHPLANNAME).creatorname(UPDATED_CREATORNAME).auditStatus(UPDATED_AUDIT_STATUS);

        restMonthplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMonthplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMonthplan))
            )
            .andExpect(status().isOk());

        // Validate the Monthplan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMonthplanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedMonthplan, monthplan),
            getPersistedMonthplan(monthplan)
        );
    }

    @Test
    @Transactional
    void fullUpdateMonthplanWithPatch() throws Exception {
        // Initialize the database
        monthplanRepository.saveAndFlush(monthplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the monthplan using partial update
        Monthplan partialUpdatedMonthplan = new Monthplan();
        partialUpdatedMonthplan.setId(monthplan.getId());

        partialUpdatedMonthplan
            .monthplanid(UPDATED_MONTHPLANID)
            .monthplanname(UPDATED_MONTHPLANNAME)
            .month(UPDATED_MONTH)
            .secretlevel(UPDATED_SECRETLEVEL)
            .creatorname(UPDATED_CREATORNAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restMonthplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMonthplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMonthplan))
            )
            .andExpect(status().isOk());

        // Validate the Monthplan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMonthplanUpdatableFieldsEquals(partialUpdatedMonthplan, getPersistedMonthplan(partialUpdatedMonthplan));
    }

    @Test
    @Transactional
    void patchNonExistingMonthplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        monthplan.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMonthplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, monthplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(monthplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Monthplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMonthplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        monthplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMonthplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(monthplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Monthplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMonthplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        monthplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMonthplanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(monthplan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Monthplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMonthplan() throws Exception {
        // Initialize the database
        monthplanRepository.saveAndFlush(monthplan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the monthplan
        restMonthplanMockMvc
            .perform(delete(ENTITY_API_URL_ID, monthplan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return monthplanRepository.count();
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

    protected Monthplan getPersistedMonthplan(Monthplan monthplan) {
        return monthplanRepository.findById(monthplan.getId()).orElseThrow();
    }

    protected void assertPersistedMonthplanToMatchAllProperties(Monthplan expectedMonthplan) {
        assertMonthplanAllPropertiesEquals(expectedMonthplan, getPersistedMonthplan(expectedMonthplan));
    }

    protected void assertPersistedMonthplanToMatchUpdatableProperties(Monthplan expectedMonthplan) {
        assertMonthplanAllUpdatablePropertiesEquals(expectedMonthplan, getPersistedMonthplan(expectedMonthplan));
    }
}
