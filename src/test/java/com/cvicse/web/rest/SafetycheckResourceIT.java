package com.cvicse.web.rest;

import static com.cvicse.domain.SafetycheckAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Safetycheck;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Risklevel;
import com.cvicse.repository.SafetycheckRepository;
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
 * Integration tests for the {@link SafetycheckResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SafetycheckResourceIT {

    private static final Long DEFAULT_SAFETYCHECKID = 1L;
    private static final Long UPDATED_SAFETYCHECKID = 2L;

    private static final String DEFAULT_SAFETYCHECKNAME = "AAAAAAAAAA";
    private static final String UPDATED_SAFETYCHECKNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHECKSOURCE = "AAAAAAAAAA";
    private static final String UPDATED_CHECKSOURCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CHECKTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHECKTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EFFECTIVETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECTIVETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OPERATINGLOCATION = "AAAAAAAAAA";
    private static final String UPDATED_OPERATINGLOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_DEPROTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPROTMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_PHONENUMBER = 1L;
    private static final Long UPDATED_PHONENUMBER = 2L;

    private static final Risklevel DEFAULT_RISKLEVEL = Risklevel.One;
    private static final Risklevel UPDATED_RISKLEVEL = Risklevel.Two;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/safetychecks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SafetycheckRepository safetycheckRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSafetycheckMockMvc;

    private Safetycheck safetycheck;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Safetycheck createEntity(EntityManager em) {
        Safetycheck safetycheck = new Safetycheck()
            .safetycheckid(DEFAULT_SAFETYCHECKID)
            .safetycheckname(DEFAULT_SAFETYCHECKNAME)
            .checksource(DEFAULT_CHECKSOURCE)
            .checktime(DEFAULT_CHECKTIME)
            .effectivetime(DEFAULT_EFFECTIVETIME)
            .operatinglocation(DEFAULT_OPERATINGLOCATION)
            .deprotment(DEFAULT_DEPROTMENT)
            .phonenumber(DEFAULT_PHONENUMBER)
            .risklevel(DEFAULT_RISKLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return safetycheck;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Safetycheck createUpdatedEntity(EntityManager em) {
        Safetycheck safetycheck = new Safetycheck()
            .safetycheckid(UPDATED_SAFETYCHECKID)
            .safetycheckname(UPDATED_SAFETYCHECKNAME)
            .checksource(UPDATED_CHECKSOURCE)
            .checktime(UPDATED_CHECKTIME)
            .effectivetime(UPDATED_EFFECTIVETIME)
            .operatinglocation(UPDATED_OPERATINGLOCATION)
            .deprotment(UPDATED_DEPROTMENT)
            .phonenumber(UPDATED_PHONENUMBER)
            .risklevel(UPDATED_RISKLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return safetycheck;
    }

    @BeforeEach
    public void initTest() {
        safetycheck = createEntity(em);
    }

    @Test
    @Transactional
    void createSafetycheck() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Safetycheck
        var returnedSafetycheck = om.readValue(
            restSafetycheckMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(safetycheck)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Safetycheck.class
        );

        // Validate the Safetycheck in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSafetycheckUpdatableFieldsEquals(returnedSafetycheck, getPersistedSafetycheck(returnedSafetycheck));
    }

    @Test
    @Transactional
    void createSafetycheckWithExistingId() throws Exception {
        // Create the Safetycheck with an existing ID
        safetycheck.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSafetycheckMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(safetycheck)))
            .andExpect(status().isBadRequest());

        // Validate the Safetycheck in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSafetychecks() throws Exception {
        // Initialize the database
        safetycheckRepository.saveAndFlush(safetycheck);

        // Get all the safetycheckList
        restSafetycheckMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(safetycheck.getId().intValue())))
            .andExpect(jsonPath("$.[*].safetycheckid").value(hasItem(DEFAULT_SAFETYCHECKID.intValue())))
            .andExpect(jsonPath("$.[*].safetycheckname").value(hasItem(DEFAULT_SAFETYCHECKNAME)))
            .andExpect(jsonPath("$.[*].checksource").value(hasItem(DEFAULT_CHECKSOURCE)))
            .andExpect(jsonPath("$.[*].checktime").value(hasItem(DEFAULT_CHECKTIME.toString())))
            .andExpect(jsonPath("$.[*].effectivetime").value(hasItem(DEFAULT_EFFECTIVETIME.toString())))
            .andExpect(jsonPath("$.[*].operatinglocation").value(hasItem(DEFAULT_OPERATINGLOCATION)))
            .andExpect(jsonPath("$.[*].deprotment").value(hasItem(DEFAULT_DEPROTMENT)))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER.intValue())))
            .andExpect(jsonPath("$.[*].risklevel").value(hasItem(DEFAULT_RISKLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getSafetycheck() throws Exception {
        // Initialize the database
        safetycheckRepository.saveAndFlush(safetycheck);

        // Get the safetycheck
        restSafetycheckMockMvc
            .perform(get(ENTITY_API_URL_ID, safetycheck.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(safetycheck.getId().intValue()))
            .andExpect(jsonPath("$.safetycheckid").value(DEFAULT_SAFETYCHECKID.intValue()))
            .andExpect(jsonPath("$.safetycheckname").value(DEFAULT_SAFETYCHECKNAME))
            .andExpect(jsonPath("$.checksource").value(DEFAULT_CHECKSOURCE))
            .andExpect(jsonPath("$.checktime").value(DEFAULT_CHECKTIME.toString()))
            .andExpect(jsonPath("$.effectivetime").value(DEFAULT_EFFECTIVETIME.toString()))
            .andExpect(jsonPath("$.operatinglocation").value(DEFAULT_OPERATINGLOCATION))
            .andExpect(jsonPath("$.deprotment").value(DEFAULT_DEPROTMENT))
            .andExpect(jsonPath("$.phonenumber").value(DEFAULT_PHONENUMBER.intValue()))
            .andExpect(jsonPath("$.risklevel").value(DEFAULT_RISKLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSafetycheck() throws Exception {
        // Get the safetycheck
        restSafetycheckMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSafetycheck() throws Exception {
        // Initialize the database
        safetycheckRepository.saveAndFlush(safetycheck);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the safetycheck
        Safetycheck updatedSafetycheck = safetycheckRepository.findById(safetycheck.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSafetycheck are not directly saved in db
        em.detach(updatedSafetycheck);
        updatedSafetycheck
            .safetycheckid(UPDATED_SAFETYCHECKID)
            .safetycheckname(UPDATED_SAFETYCHECKNAME)
            .checksource(UPDATED_CHECKSOURCE)
            .checktime(UPDATED_CHECKTIME)
            .effectivetime(UPDATED_EFFECTIVETIME)
            .operatinglocation(UPDATED_OPERATINGLOCATION)
            .deprotment(UPDATED_DEPROTMENT)
            .phonenumber(UPDATED_PHONENUMBER)
            .risklevel(UPDATED_RISKLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restSafetycheckMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSafetycheck.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSafetycheck))
            )
            .andExpect(status().isOk());

        // Validate the Safetycheck in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSafetycheckToMatchAllProperties(updatedSafetycheck);
    }

    @Test
    @Transactional
    void putNonExistingSafetycheck() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        safetycheck.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSafetycheckMockMvc
            .perform(
                put(ENTITY_API_URL_ID, safetycheck.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(safetycheck))
            )
            .andExpect(status().isBadRequest());

        // Validate the Safetycheck in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSafetycheck() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        safetycheck.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSafetycheckMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(safetycheck))
            )
            .andExpect(status().isBadRequest());

        // Validate the Safetycheck in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSafetycheck() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        safetycheck.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSafetycheckMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(safetycheck)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Safetycheck in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSafetycheckWithPatch() throws Exception {
        // Initialize the database
        safetycheckRepository.saveAndFlush(safetycheck);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the safetycheck using partial update
        Safetycheck partialUpdatedSafetycheck = new Safetycheck();
        partialUpdatedSafetycheck.setId(safetycheck.getId());

        partialUpdatedSafetycheck.checksource(UPDATED_CHECKSOURCE).effectivetime(UPDATED_EFFECTIVETIME).deprotment(UPDATED_DEPROTMENT);

        restSafetycheckMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSafetycheck.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSafetycheck))
            )
            .andExpect(status().isOk());

        // Validate the Safetycheck in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSafetycheckUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSafetycheck, safetycheck),
            getPersistedSafetycheck(safetycheck)
        );
    }

    @Test
    @Transactional
    void fullUpdateSafetycheckWithPatch() throws Exception {
        // Initialize the database
        safetycheckRepository.saveAndFlush(safetycheck);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the safetycheck using partial update
        Safetycheck partialUpdatedSafetycheck = new Safetycheck();
        partialUpdatedSafetycheck.setId(safetycheck.getId());

        partialUpdatedSafetycheck
            .safetycheckid(UPDATED_SAFETYCHECKID)
            .safetycheckname(UPDATED_SAFETYCHECKNAME)
            .checksource(UPDATED_CHECKSOURCE)
            .checktime(UPDATED_CHECKTIME)
            .effectivetime(UPDATED_EFFECTIVETIME)
            .operatinglocation(UPDATED_OPERATINGLOCATION)
            .deprotment(UPDATED_DEPROTMENT)
            .phonenumber(UPDATED_PHONENUMBER)
            .risklevel(UPDATED_RISKLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restSafetycheckMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSafetycheck.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSafetycheck))
            )
            .andExpect(status().isOk());

        // Validate the Safetycheck in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSafetycheckUpdatableFieldsEquals(partialUpdatedSafetycheck, getPersistedSafetycheck(partialUpdatedSafetycheck));
    }

    @Test
    @Transactional
    void patchNonExistingSafetycheck() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        safetycheck.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSafetycheckMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, safetycheck.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(safetycheck))
            )
            .andExpect(status().isBadRequest());

        // Validate the Safetycheck in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSafetycheck() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        safetycheck.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSafetycheckMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(safetycheck))
            )
            .andExpect(status().isBadRequest());

        // Validate the Safetycheck in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSafetycheck() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        safetycheck.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSafetycheckMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(safetycheck)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Safetycheck in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSafetycheck() throws Exception {
        // Initialize the database
        safetycheckRepository.saveAndFlush(safetycheck);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the safetycheck
        restSafetycheckMockMvc
            .perform(delete(ENTITY_API_URL_ID, safetycheck.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return safetycheckRepository.count();
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

    protected Safetycheck getPersistedSafetycheck(Safetycheck safetycheck) {
        return safetycheckRepository.findById(safetycheck.getId()).orElseThrow();
    }

    protected void assertPersistedSafetycheckToMatchAllProperties(Safetycheck expectedSafetycheck) {
        assertSafetycheckAllPropertiesEquals(expectedSafetycheck, getPersistedSafetycheck(expectedSafetycheck));
    }

    protected void assertPersistedSafetycheckToMatchUpdatableProperties(Safetycheck expectedSafetycheck) {
        assertSafetycheckAllUpdatablePropertiesEquals(expectedSafetycheck, getPersistedSafetycheck(expectedSafetycheck));
    }
}
