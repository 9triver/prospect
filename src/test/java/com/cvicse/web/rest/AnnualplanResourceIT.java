package com.cvicse.web.rest;

import static com.cvicse.domain.AnnualplanAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Annualplan;
import com.cvicse.domain.enumeration.Annualplanstatus;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.AnnualplanRepository;
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
 * Integration tests for the {@link AnnualplanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnnualplanResourceIT {

    private static final Long DEFAULT_ANNUALPLANID = 1L;
    private static final Long UPDATED_ANNUALPLANID = 2L;

    private static final String DEFAULT_ANNUALPLANNAME = "AAAAAAAAAA";
    private static final String UPDATED_ANNUALPLANNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_YEAR = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_YEAR = LocalDate.now(ZoneId.systemDefault());

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final String DEFAULT_CREATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATORNAME = "BBBBBBBBBB";

    private static final Annualplanstatus DEFAULT_STATUS = Annualplanstatus.IN_PIPELINE;
    private static final Annualplanstatus UPDATED_STATUS = Annualplanstatus.NOTIFIED;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/annualplans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AnnualplanRepository annualplanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnnualplanMockMvc;

    private Annualplan annualplan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Annualplan createEntity(EntityManager em) {
        Annualplan annualplan = new Annualplan()
            .annualplanid(DEFAULT_ANNUALPLANID)
            .annualplanname(DEFAULT_ANNUALPLANNAME)
            .year(DEFAULT_YEAR)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .creatorname(DEFAULT_CREATORNAME)
            .status(DEFAULT_STATUS)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return annualplan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Annualplan createUpdatedEntity(EntityManager em) {
        Annualplan annualplan = new Annualplan()
            .annualplanid(UPDATED_ANNUALPLANID)
            .annualplanname(UPDATED_ANNUALPLANNAME)
            .year(UPDATED_YEAR)
            .secretlevel(UPDATED_SECRETLEVEL)
            .creatorname(UPDATED_CREATORNAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return annualplan;
    }

    @BeforeEach
    public void initTest() {
        annualplan = createEntity(em);
    }

    @Test
    @Transactional
    void createAnnualplan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Annualplan
        var returnedAnnualplan = om.readValue(
            restAnnualplanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(annualplan)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Annualplan.class
        );

        // Validate the Annualplan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAnnualplanUpdatableFieldsEquals(returnedAnnualplan, getPersistedAnnualplan(returnedAnnualplan));
    }

    @Test
    @Transactional
    void createAnnualplanWithExistingId() throws Exception {
        // Create the Annualplan with an existing ID
        annualplan.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnnualplanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(annualplan)))
            .andExpect(status().isBadRequest());

        // Validate the Annualplan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAnnualplans() throws Exception {
        // Initialize the database
        annualplanRepository.saveAndFlush(annualplan);

        // Get all the annualplanList
        restAnnualplanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(annualplan.getId().intValue())))
            .andExpect(jsonPath("$.[*].annualplanid").value(hasItem(DEFAULT_ANNUALPLANID.intValue())))
            .andExpect(jsonPath("$.[*].annualplanname").value(hasItem(DEFAULT_ANNUALPLANNAME)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.toString())))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getAnnualplan() throws Exception {
        // Initialize the database
        annualplanRepository.saveAndFlush(annualplan);

        // Get the annualplan
        restAnnualplanMockMvc
            .perform(get(ENTITY_API_URL_ID, annualplan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(annualplan.getId().intValue()))
            .andExpect(jsonPath("$.annualplanid").value(DEFAULT_ANNUALPLANID.intValue()))
            .andExpect(jsonPath("$.annualplanname").value(DEFAULT_ANNUALPLANNAME))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.toString()))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAnnualplan() throws Exception {
        // Get the annualplan
        restAnnualplanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAnnualplan() throws Exception {
        // Initialize the database
        annualplanRepository.saveAndFlush(annualplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the annualplan
        Annualplan updatedAnnualplan = annualplanRepository.findById(annualplan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAnnualplan are not directly saved in db
        em.detach(updatedAnnualplan);
        updatedAnnualplan
            .annualplanid(UPDATED_ANNUALPLANID)
            .annualplanname(UPDATED_ANNUALPLANNAME)
            .year(UPDATED_YEAR)
            .secretlevel(UPDATED_SECRETLEVEL)
            .creatorname(UPDATED_CREATORNAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restAnnualplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAnnualplan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAnnualplan))
            )
            .andExpect(status().isOk());

        // Validate the Annualplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAnnualplanToMatchAllProperties(updatedAnnualplan);
    }

    @Test
    @Transactional
    void putNonExistingAnnualplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualplan.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnnualplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, annualplan.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(annualplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Annualplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAnnualplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(annualplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Annualplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAnnualplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualplanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(annualplan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Annualplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAnnualplanWithPatch() throws Exception {
        // Initialize the database
        annualplanRepository.saveAndFlush(annualplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the annualplan using partial update
        Annualplan partialUpdatedAnnualplan = new Annualplan();
        partialUpdatedAnnualplan.setId(annualplan.getId());

        partialUpdatedAnnualplan
            .annualplanname(UPDATED_ANNUALPLANNAME)
            .year(UPDATED_YEAR)
            .creatorname(UPDATED_CREATORNAME)
            .status(UPDATED_STATUS);

        restAnnualplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnnualplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAnnualplan))
            )
            .andExpect(status().isOk());

        // Validate the Annualplan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAnnualplanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAnnualplan, annualplan),
            getPersistedAnnualplan(annualplan)
        );
    }

    @Test
    @Transactional
    void fullUpdateAnnualplanWithPatch() throws Exception {
        // Initialize the database
        annualplanRepository.saveAndFlush(annualplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the annualplan using partial update
        Annualplan partialUpdatedAnnualplan = new Annualplan();
        partialUpdatedAnnualplan.setId(annualplan.getId());

        partialUpdatedAnnualplan
            .annualplanid(UPDATED_ANNUALPLANID)
            .annualplanname(UPDATED_ANNUALPLANNAME)
            .year(UPDATED_YEAR)
            .secretlevel(UPDATED_SECRETLEVEL)
            .creatorname(UPDATED_CREATORNAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restAnnualplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnnualplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAnnualplan))
            )
            .andExpect(status().isOk());

        // Validate the Annualplan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAnnualplanUpdatableFieldsEquals(partialUpdatedAnnualplan, getPersistedAnnualplan(partialUpdatedAnnualplan));
    }

    @Test
    @Transactional
    void patchNonExistingAnnualplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualplan.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnnualplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, annualplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(annualplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Annualplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAnnualplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(annualplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Annualplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAnnualplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualplan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualplanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(annualplan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Annualplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAnnualplan() throws Exception {
        // Initialize the database
        annualplanRepository.saveAndFlush(annualplan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the annualplan
        restAnnualplanMockMvc
            .perform(delete(ENTITY_API_URL_ID, annualplan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return annualplanRepository.count();
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

    protected Annualplan getPersistedAnnualplan(Annualplan annualplan) {
        return annualplanRepository.findById(annualplan.getId()).orElseThrow();
    }

    protected void assertPersistedAnnualplanToMatchAllProperties(Annualplan expectedAnnualplan) {
        assertAnnualplanAllPropertiesEquals(expectedAnnualplan, getPersistedAnnualplan(expectedAnnualplan));
    }

    protected void assertPersistedAnnualplanToMatchUpdatableProperties(Annualplan expectedAnnualplan) {
        assertAnnualplanAllUpdatablePropertiesEquals(expectedAnnualplan, getPersistedAnnualplan(expectedAnnualplan));
    }
}
