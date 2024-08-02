package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.FundsEstimationAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.FundsEstimation;
import com.cvicse.jy1.repository.FundsEstimationRepository;
import com.cvicse.jy1.service.FundsEstimationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link FundsEstimationResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class FundsEstimationResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_UNITPRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNITPRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MATERIALFEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MATERIALFEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SPECIALFEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SPECIALFEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OUTSOURCINGFEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_OUTSOURCINGFEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTALBUDGETPRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTALBUDGETPRICE = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/funds-estimations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FundsEstimationRepository fundsEstimationRepository;

    @Mock
    private FundsEstimationRepository fundsEstimationRepositoryMock;

    @Mock
    private FundsEstimationService fundsEstimationServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFundsEstimationMockMvc;

    private FundsEstimation fundsEstimation;

    private FundsEstimation insertedFundsEstimation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundsEstimation createEntity(EntityManager em) {
        FundsEstimation fundsEstimation = new FundsEstimation()
            .name(DEFAULT_NAME)
            .source(DEFAULT_SOURCE)
            .unit(DEFAULT_UNIT)
            .number(DEFAULT_NUMBER)
            .unitprice(DEFAULT_UNITPRICE)
            .materialfee(DEFAULT_MATERIALFEE)
            .specialfee(DEFAULT_SPECIALFEE)
            .outsourcingfee(DEFAULT_OUTSOURCINGFEE)
            .totalbudgetprice(DEFAULT_TOTALBUDGETPRICE);
        return fundsEstimation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundsEstimation createUpdatedEntity(EntityManager em) {
        FundsEstimation fundsEstimation = new FundsEstimation()
            .name(UPDATED_NAME)
            .source(UPDATED_SOURCE)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .materialfee(UPDATED_MATERIALFEE)
            .specialfee(UPDATED_SPECIALFEE)
            .outsourcingfee(UPDATED_OUTSOURCINGFEE)
            .totalbudgetprice(UPDATED_TOTALBUDGETPRICE);
        return fundsEstimation;
    }

    @BeforeEach
    public void initTest() {
        fundsEstimation = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedFundsEstimation != null) {
            fundsEstimationRepository.delete(insertedFundsEstimation);
            insertedFundsEstimation = null;
        }
    }

    @Test
    @Transactional
    void createFundsEstimation() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FundsEstimation
        var returnedFundsEstimation = om.readValue(
            restFundsEstimationMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsEstimation)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FundsEstimation.class
        );

        // Validate the FundsEstimation in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertFundsEstimationUpdatableFieldsEquals(returnedFundsEstimation, getPersistedFundsEstimation(returnedFundsEstimation));

        insertedFundsEstimation = returnedFundsEstimation;
    }

    @Test
    @Transactional
    void createFundsEstimationWithExistingId() throws Exception {
        // Create the FundsEstimation with an existing ID
        fundsEstimation.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundsEstimationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsEstimation)))
            .andExpect(status().isBadRequest());

        // Validate the FundsEstimation in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFundsEstimations() throws Exception {
        // Initialize the database
        insertedFundsEstimation = fundsEstimationRepository.saveAndFlush(fundsEstimation);

        // Get all the fundsEstimationList
        restFundsEstimationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundsEstimation.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].unitprice").value(hasItem(sameNumber(DEFAULT_UNITPRICE))))
            .andExpect(jsonPath("$.[*].materialfee").value(hasItem(sameNumber(DEFAULT_MATERIALFEE))))
            .andExpect(jsonPath("$.[*].specialfee").value(hasItem(sameNumber(DEFAULT_SPECIALFEE))))
            .andExpect(jsonPath("$.[*].outsourcingfee").value(hasItem(sameNumber(DEFAULT_OUTSOURCINGFEE))))
            .andExpect(jsonPath("$.[*].totalbudgetprice").value(hasItem(sameNumber(DEFAULT_TOTALBUDGETPRICE))));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllFundsEstimationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(fundsEstimationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restFundsEstimationMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(fundsEstimationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllFundsEstimationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(fundsEstimationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restFundsEstimationMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(fundsEstimationRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getFundsEstimation() throws Exception {
        // Initialize the database
        insertedFundsEstimation = fundsEstimationRepository.saveAndFlush(fundsEstimation);

        // Get the fundsEstimation
        restFundsEstimationMockMvc
            .perform(get(ENTITY_API_URL_ID, fundsEstimation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fundsEstimation.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.unitprice").value(sameNumber(DEFAULT_UNITPRICE)))
            .andExpect(jsonPath("$.materialfee").value(sameNumber(DEFAULT_MATERIALFEE)))
            .andExpect(jsonPath("$.specialfee").value(sameNumber(DEFAULT_SPECIALFEE)))
            .andExpect(jsonPath("$.outsourcingfee").value(sameNumber(DEFAULT_OUTSOURCINGFEE)))
            .andExpect(jsonPath("$.totalbudgetprice").value(sameNumber(DEFAULT_TOTALBUDGETPRICE)));
    }

    @Test
    @Transactional
    void getNonExistingFundsEstimation() throws Exception {
        // Get the fundsEstimation
        restFundsEstimationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFundsEstimation() throws Exception {
        // Initialize the database
        insertedFundsEstimation = fundsEstimationRepository.saveAndFlush(fundsEstimation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsEstimation
        FundsEstimation updatedFundsEstimation = fundsEstimationRepository.findById(fundsEstimation.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFundsEstimation are not directly saved in db
        em.detach(updatedFundsEstimation);
        updatedFundsEstimation
            .name(UPDATED_NAME)
            .source(UPDATED_SOURCE)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .materialfee(UPDATED_MATERIALFEE)
            .specialfee(UPDATED_SPECIALFEE)
            .outsourcingfee(UPDATED_OUTSOURCINGFEE)
            .totalbudgetprice(UPDATED_TOTALBUDGETPRICE);

        restFundsEstimationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFundsEstimation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedFundsEstimation))
            )
            .andExpect(status().isOk());

        // Validate the FundsEstimation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFundsEstimationToMatchAllProperties(updatedFundsEstimation);
    }

    @Test
    @Transactional
    void putNonExistingFundsEstimation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsEstimation.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundsEstimationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fundsEstimation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundsEstimation))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundsEstimation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFundsEstimation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsEstimation.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsEstimationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundsEstimation))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundsEstimation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFundsEstimation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsEstimation.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsEstimationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsEstimation)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FundsEstimation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFundsEstimationWithPatch() throws Exception {
        // Initialize the database
        insertedFundsEstimation = fundsEstimationRepository.saveAndFlush(fundsEstimation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsEstimation using partial update
        FundsEstimation partialUpdatedFundsEstimation = new FundsEstimation();
        partialUpdatedFundsEstimation.setId(fundsEstimation.getId());

        partialUpdatedFundsEstimation
            .unitprice(UPDATED_UNITPRICE)
            .specialfee(UPDATED_SPECIALFEE)
            .outsourcingfee(UPDATED_OUTSOURCINGFEE)
            .totalbudgetprice(UPDATED_TOTALBUDGETPRICE);

        restFundsEstimationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundsEstimation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundsEstimation))
            )
            .andExpect(status().isOk());

        // Validate the FundsEstimation in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundsEstimationUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFundsEstimation, fundsEstimation),
            getPersistedFundsEstimation(fundsEstimation)
        );
    }

    @Test
    @Transactional
    void fullUpdateFundsEstimationWithPatch() throws Exception {
        // Initialize the database
        insertedFundsEstimation = fundsEstimationRepository.saveAndFlush(fundsEstimation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsEstimation using partial update
        FundsEstimation partialUpdatedFundsEstimation = new FundsEstimation();
        partialUpdatedFundsEstimation.setId(fundsEstimation.getId());

        partialUpdatedFundsEstimation
            .name(UPDATED_NAME)
            .source(UPDATED_SOURCE)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .materialfee(UPDATED_MATERIALFEE)
            .specialfee(UPDATED_SPECIALFEE)
            .outsourcingfee(UPDATED_OUTSOURCINGFEE)
            .totalbudgetprice(UPDATED_TOTALBUDGETPRICE);

        restFundsEstimationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundsEstimation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundsEstimation))
            )
            .andExpect(status().isOk());

        // Validate the FundsEstimation in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundsEstimationUpdatableFieldsEquals(
            partialUpdatedFundsEstimation,
            getPersistedFundsEstimation(partialUpdatedFundsEstimation)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFundsEstimation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsEstimation.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundsEstimationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fundsEstimation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundsEstimation))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundsEstimation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFundsEstimation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsEstimation.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsEstimationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundsEstimation))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundsEstimation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFundsEstimation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsEstimation.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsEstimationMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fundsEstimation)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FundsEstimation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFundsEstimation() throws Exception {
        // Initialize the database
        insertedFundsEstimation = fundsEstimationRepository.saveAndFlush(fundsEstimation);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fundsEstimation
        restFundsEstimationMockMvc
            .perform(delete(ENTITY_API_URL_ID, fundsEstimation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fundsEstimationRepository.count();
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

    protected FundsEstimation getPersistedFundsEstimation(FundsEstimation fundsEstimation) {
        return fundsEstimationRepository.findById(fundsEstimation.getId()).orElseThrow();
    }

    protected void assertPersistedFundsEstimationToMatchAllProperties(FundsEstimation expectedFundsEstimation) {
        assertFundsEstimationAllPropertiesEquals(expectedFundsEstimation, getPersistedFundsEstimation(expectedFundsEstimation));
    }

    protected void assertPersistedFundsEstimationToMatchUpdatableProperties(FundsEstimation expectedFundsEstimation) {
        assertFundsEstimationAllUpdatablePropertiesEquals(expectedFundsEstimation, getPersistedFundsEstimation(expectedFundsEstimation));
    }
}
