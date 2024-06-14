package com.cvicse.web.rest;

import static com.cvicse.domain.WbsmanageAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Wbsmanage;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.WbsmanageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link WbsmanageResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WbsmanageResourceIT {

    private static final String DEFAULT_WBSID = "AAAAAAAAAA";
    private static final String UPDATED_WBSID = "BBBBBBBBBB";

    private static final String DEFAULT_WBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_WBSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_ADMINISTRATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_ADMINISTRATORNAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSIBLENAME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSIBLENAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/wbsmanages";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private WbsmanageRepository wbsmanageRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWbsmanageMockMvc;

    private Wbsmanage wbsmanage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wbsmanage createEntity(EntityManager em) {
        Wbsmanage wbsmanage = new Wbsmanage()
            .wbsid(DEFAULT_WBSID)
            .wbsname(DEFAULT_WBSNAME)
            .description(DEFAULT_DESCRIPTION)
            .result(DEFAULT_RESULT)
            .administratorname(DEFAULT_ADMINISTRATORNAME)
            .responsiblename(DEFAULT_RESPONSIBLENAME)
            .department(DEFAULT_DEPARTMENT)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return wbsmanage;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wbsmanage createUpdatedEntity(EntityManager em) {
        Wbsmanage wbsmanage = new Wbsmanage()
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .description(UPDATED_DESCRIPTION)
            .result(UPDATED_RESULT)
            .administratorname(UPDATED_ADMINISTRATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .department(UPDATED_DEPARTMENT)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return wbsmanage;
    }

    @BeforeEach
    public void initTest() {
        wbsmanage = createEntity(em);
    }

    @Test
    @Transactional
    void createWbsmanage() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Wbsmanage
        var returnedWbsmanage = om.readValue(
            restWbsmanageMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wbsmanage)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Wbsmanage.class
        );

        // Validate the Wbsmanage in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertWbsmanageUpdatableFieldsEquals(returnedWbsmanage, getPersistedWbsmanage(returnedWbsmanage));
    }

    @Test
    @Transactional
    void createWbsmanageWithExistingId() throws Exception {
        // Create the Wbsmanage with an existing ID
        wbsmanage.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWbsmanageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wbsmanage)))
            .andExpect(status().isBadRequest());

        // Validate the Wbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWbsmanages() throws Exception {
        // Initialize the database
        wbsmanageRepository.saveAndFlush(wbsmanage);

        // Get all the wbsmanageList
        restWbsmanageMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wbsmanage.getId().intValue())))
            .andExpect(jsonPath("$.[*].wbsid").value(hasItem(DEFAULT_WBSID)))
            .andExpect(jsonPath("$.[*].wbsname").value(hasItem(DEFAULT_WBSNAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT)))
            .andExpect(jsonPath("$.[*].administratorname").value(hasItem(DEFAULT_ADMINISTRATORNAME)))
            .andExpect(jsonPath("$.[*].responsiblename").value(hasItem(DEFAULT_RESPONSIBLENAME)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getWbsmanage() throws Exception {
        // Initialize the database
        wbsmanageRepository.saveAndFlush(wbsmanage);

        // Get the wbsmanage
        restWbsmanageMockMvc
            .perform(get(ENTITY_API_URL_ID, wbsmanage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(wbsmanage.getId().intValue()))
            .andExpect(jsonPath("$.wbsid").value(DEFAULT_WBSID))
            .andExpect(jsonPath("$.wbsname").value(DEFAULT_WBSNAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT))
            .andExpect(jsonPath("$.administratorname").value(DEFAULT_ADMINISTRATORNAME))
            .andExpect(jsonPath("$.responsiblename").value(DEFAULT_RESPONSIBLENAME))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingWbsmanage() throws Exception {
        // Get the wbsmanage
        restWbsmanageMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingWbsmanage() throws Exception {
        // Initialize the database
        wbsmanageRepository.saveAndFlush(wbsmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wbsmanage
        Wbsmanage updatedWbsmanage = wbsmanageRepository.findById(wbsmanage.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedWbsmanage are not directly saved in db
        em.detach(updatedWbsmanage);
        updatedWbsmanage
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .description(UPDATED_DESCRIPTION)
            .result(UPDATED_RESULT)
            .administratorname(UPDATED_ADMINISTRATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .department(UPDATED_DEPARTMENT)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restWbsmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedWbsmanage.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedWbsmanage))
            )
            .andExpect(status().isOk());

        // Validate the Wbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedWbsmanageToMatchAllProperties(updatedWbsmanage);
    }

    @Test
    @Transactional
    void putNonExistingWbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsmanage.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWbsmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wbsmanage.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wbsmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbsmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(wbsmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbsmanageMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wbsmanage)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Wbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWbsmanageWithPatch() throws Exception {
        // Initialize the database
        wbsmanageRepository.saveAndFlush(wbsmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wbsmanage using partial update
        Wbsmanage partialUpdatedWbsmanage = new Wbsmanage();
        partialUpdatedWbsmanage.setId(wbsmanage.getId());

        partialUpdatedWbsmanage.description(UPDATED_DESCRIPTION).result(UPDATED_RESULT).responsiblename(UPDATED_RESPONSIBLENAME);

        restWbsmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWbsmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWbsmanage))
            )
            .andExpect(status().isOk());

        // Validate the Wbsmanage in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWbsmanageUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedWbsmanage, wbsmanage),
            getPersistedWbsmanage(wbsmanage)
        );
    }

    @Test
    @Transactional
    void fullUpdateWbsmanageWithPatch() throws Exception {
        // Initialize the database
        wbsmanageRepository.saveAndFlush(wbsmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wbsmanage using partial update
        Wbsmanage partialUpdatedWbsmanage = new Wbsmanage();
        partialUpdatedWbsmanage.setId(wbsmanage.getId());

        partialUpdatedWbsmanage
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .description(UPDATED_DESCRIPTION)
            .result(UPDATED_RESULT)
            .administratorname(UPDATED_ADMINISTRATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .department(UPDATED_DEPARTMENT)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restWbsmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWbsmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWbsmanage))
            )
            .andExpect(status().isOk());

        // Validate the Wbsmanage in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWbsmanageUpdatableFieldsEquals(partialUpdatedWbsmanage, getPersistedWbsmanage(partialUpdatedWbsmanage));
    }

    @Test
    @Transactional
    void patchNonExistingWbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsmanage.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWbsmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, wbsmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(wbsmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbsmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(wbsmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbsmanageMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(wbsmanage)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Wbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWbsmanage() throws Exception {
        // Initialize the database
        wbsmanageRepository.saveAndFlush(wbsmanage);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the wbsmanage
        restWbsmanageMockMvc
            .perform(delete(ENTITY_API_URL_ID, wbsmanage.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return wbsmanageRepository.count();
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

    protected Wbsmanage getPersistedWbsmanage(Wbsmanage wbsmanage) {
        return wbsmanageRepository.findById(wbsmanage.getId()).orElseThrow();
    }

    protected void assertPersistedWbsmanageToMatchAllProperties(Wbsmanage expectedWbsmanage) {
        assertWbsmanageAllPropertiesEquals(expectedWbsmanage, getPersistedWbsmanage(expectedWbsmanage));
    }

    protected void assertPersistedWbsmanageToMatchUpdatableProperties(Wbsmanage expectedWbsmanage) {
        assertWbsmanageAllUpdatablePropertiesEquals(expectedWbsmanage, getPersistedWbsmanage(expectedWbsmanage));
    }
}
