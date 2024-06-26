package com.cvicse.web.rest;

import static com.cvicse.domain.EvaluationCriteriaAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.EvaluationCriteria;
import com.cvicse.repository.EvaluationCriteriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EvaluationCriteriaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EvaluationCriteriaResourceIT {

    private static final Integer DEFAULT_STANDARDTYPE = 1;
    private static final Integer UPDATED_STANDARDTYPE = 2;

    private static final String DEFAULT_STANDARDNAME = "AAAAAAAAAA";
    private static final String UPDATED_STANDARDNAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MARK = new BigDecimal(1);
    private static final BigDecimal UPDATED_MARK = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/evaluation-criteria";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EvaluationCriteriaRepository evaluationCriteriaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEvaluationCriteriaMockMvc;

    private EvaluationCriteria evaluationCriteria;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationCriteria createEntity(EntityManager em) {
        EvaluationCriteria evaluationCriteria = new EvaluationCriteria()
            .standardtype(DEFAULT_STANDARDTYPE)
            .standardname(DEFAULT_STANDARDNAME)
            .mark(DEFAULT_MARK);
        return evaluationCriteria;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EvaluationCriteria createUpdatedEntity(EntityManager em) {
        EvaluationCriteria evaluationCriteria = new EvaluationCriteria()
            .standardtype(UPDATED_STANDARDTYPE)
            .standardname(UPDATED_STANDARDNAME)
            .mark(UPDATED_MARK);
        return evaluationCriteria;
    }

    @BeforeEach
    public void initTest() {
        evaluationCriteria = createEntity(em);
    }

    @Test
    @Transactional
    void createEvaluationCriteria() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EvaluationCriteria
        var returnedEvaluationCriteria = om.readValue(
            restEvaluationCriteriaMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(evaluationCriteria)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EvaluationCriteria.class
        );

        // Validate the EvaluationCriteria in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEvaluationCriteriaUpdatableFieldsEquals(
            returnedEvaluationCriteria,
            getPersistedEvaluationCriteria(returnedEvaluationCriteria)
        );
    }

    @Test
    @Transactional
    void createEvaluationCriteriaWithExistingId() throws Exception {
        // Create the EvaluationCriteria with an existing ID
        evaluationCriteria.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEvaluationCriteriaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(evaluationCriteria)))
            .andExpect(status().isBadRequest());

        // Validate the EvaluationCriteria in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEvaluationCriteria() throws Exception {
        // Initialize the database
        evaluationCriteriaRepository.saveAndFlush(evaluationCriteria);

        // Get all the evaluationCriteriaList
        restEvaluationCriteriaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evaluationCriteria.getId())))
            .andExpect(jsonPath("$.[*].standardtype").value(hasItem(DEFAULT_STANDARDTYPE)))
            .andExpect(jsonPath("$.[*].standardname").value(hasItem(DEFAULT_STANDARDNAME)))
            .andExpect(jsonPath("$.[*].mark").value(hasItem(sameNumber(DEFAULT_MARK))));
    }

    @Test
    @Transactional
    void getEvaluationCriteria() throws Exception {
        // Initialize the database
        evaluationCriteriaRepository.saveAndFlush(evaluationCriteria);

        // Get the evaluationCriteria
        restEvaluationCriteriaMockMvc
            .perform(get(ENTITY_API_URL_ID, evaluationCriteria.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(evaluationCriteria.getId()))
            .andExpect(jsonPath("$.standardtype").value(DEFAULT_STANDARDTYPE))
            .andExpect(jsonPath("$.standardname").value(DEFAULT_STANDARDNAME))
            .andExpect(jsonPath("$.mark").value(sameNumber(DEFAULT_MARK)));
    }

    @Test
    @Transactional
    void getNonExistingEvaluationCriteria() throws Exception {
        // Get the evaluationCriteria
        restEvaluationCriteriaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEvaluationCriteria() throws Exception {
        // Initialize the database
        evaluationCriteriaRepository.saveAndFlush(evaluationCriteria);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the evaluationCriteria
        EvaluationCriteria updatedEvaluationCriteria = evaluationCriteriaRepository.findById(evaluationCriteria.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEvaluationCriteria are not directly saved in db
        em.detach(updatedEvaluationCriteria);
        updatedEvaluationCriteria.standardtype(UPDATED_STANDARDTYPE).standardname(UPDATED_STANDARDNAME).mark(UPDATED_MARK);

        restEvaluationCriteriaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEvaluationCriteria.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEvaluationCriteria))
            )
            .andExpect(status().isOk());

        // Validate the EvaluationCriteria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEvaluationCriteriaToMatchAllProperties(updatedEvaluationCriteria);
    }

    @Test
    @Transactional
    void putNonExistingEvaluationCriteria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        evaluationCriteria.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEvaluationCriteriaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, evaluationCriteria.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(evaluationCriteria))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationCriteria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEvaluationCriteria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        evaluationCriteria.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationCriteriaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(evaluationCriteria))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationCriteria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEvaluationCriteria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        evaluationCriteria.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationCriteriaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(evaluationCriteria)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EvaluationCriteria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEvaluationCriteriaWithPatch() throws Exception {
        // Initialize the database
        evaluationCriteriaRepository.saveAndFlush(evaluationCriteria);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the evaluationCriteria using partial update
        EvaluationCriteria partialUpdatedEvaluationCriteria = new EvaluationCriteria();
        partialUpdatedEvaluationCriteria.setId(evaluationCriteria.getId());

        partialUpdatedEvaluationCriteria.standardtype(UPDATED_STANDARDTYPE).mark(UPDATED_MARK);

        restEvaluationCriteriaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEvaluationCriteria.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEvaluationCriteria))
            )
            .andExpect(status().isOk());

        // Validate the EvaluationCriteria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEvaluationCriteriaUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEvaluationCriteria, evaluationCriteria),
            getPersistedEvaluationCriteria(evaluationCriteria)
        );
    }

    @Test
    @Transactional
    void fullUpdateEvaluationCriteriaWithPatch() throws Exception {
        // Initialize the database
        evaluationCriteriaRepository.saveAndFlush(evaluationCriteria);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the evaluationCriteria using partial update
        EvaluationCriteria partialUpdatedEvaluationCriteria = new EvaluationCriteria();
        partialUpdatedEvaluationCriteria.setId(evaluationCriteria.getId());

        partialUpdatedEvaluationCriteria.standardtype(UPDATED_STANDARDTYPE).standardname(UPDATED_STANDARDNAME).mark(UPDATED_MARK);

        restEvaluationCriteriaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEvaluationCriteria.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEvaluationCriteria))
            )
            .andExpect(status().isOk());

        // Validate the EvaluationCriteria in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEvaluationCriteriaUpdatableFieldsEquals(
            partialUpdatedEvaluationCriteria,
            getPersistedEvaluationCriteria(partialUpdatedEvaluationCriteria)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEvaluationCriteria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        evaluationCriteria.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEvaluationCriteriaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, evaluationCriteria.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(evaluationCriteria))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationCriteria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEvaluationCriteria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        evaluationCriteria.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationCriteriaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(evaluationCriteria))
            )
            .andExpect(status().isBadRequest());

        // Validate the EvaluationCriteria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEvaluationCriteria() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        evaluationCriteria.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEvaluationCriteriaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(evaluationCriteria)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EvaluationCriteria in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEvaluationCriteria() throws Exception {
        // Initialize the database
        evaluationCriteriaRepository.saveAndFlush(evaluationCriteria);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the evaluationCriteria
        restEvaluationCriteriaMockMvc
            .perform(delete(ENTITY_API_URL_ID, evaluationCriteria.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return evaluationCriteriaRepository.count();
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

    protected EvaluationCriteria getPersistedEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
        return evaluationCriteriaRepository.findById(evaluationCriteria.getId()).orElseThrow();
    }

    protected void assertPersistedEvaluationCriteriaToMatchAllProperties(EvaluationCriteria expectedEvaluationCriteria) {
        assertEvaluationCriteriaAllPropertiesEquals(expectedEvaluationCriteria, getPersistedEvaluationCriteria(expectedEvaluationCriteria));
    }

    protected void assertPersistedEvaluationCriteriaToMatchUpdatableProperties(EvaluationCriteria expectedEvaluationCriteria) {
        assertEvaluationCriteriaAllUpdatablePropertiesEquals(
            expectedEvaluationCriteria,
            getPersistedEvaluationCriteria(expectedEvaluationCriteria)
        );
    }
}
