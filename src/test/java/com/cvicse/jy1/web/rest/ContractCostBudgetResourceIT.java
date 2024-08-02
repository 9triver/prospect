package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ContractCostBudgetAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.ContractCostBudget;
import com.cvicse.jy1.domain.enumeration.ContractSubject;
import com.cvicse.jy1.repository.ContractCostBudgetRepository;
import com.cvicse.jy1.service.ContractCostBudgetService;
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
 * Integration tests for the {@link ContractCostBudgetResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ContractCostBudgetResourceIT {

    private static final ContractSubject DEFAULT_SUBJECT = ContractSubject.Materialfee;
    private static final ContractSubject UPDATED_SUBJECT = ContractSubject.Specialfee;

    private static final String DEFAULT_AUXILIARYITEM = "AAAAAAAAAA";
    private static final String UPDATED_AUXILIARYITEM = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_UNITPRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNITPRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTALPRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTALPRICE = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/contract-cost-budgets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ContractCostBudgetRepository contractCostBudgetRepository;

    @Mock
    private ContractCostBudgetRepository contractCostBudgetRepositoryMock;

    @Mock
    private ContractCostBudgetService contractCostBudgetServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractCostBudgetMockMvc;

    private ContractCostBudget contractCostBudget;

    private ContractCostBudget insertedContractCostBudget;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractCostBudget createEntity(EntityManager em) {
        ContractCostBudget contractCostBudget = new ContractCostBudget()
            .subject(DEFAULT_SUBJECT)
            .auxiliaryitem(DEFAULT_AUXILIARYITEM)
            .unit(DEFAULT_UNIT)
            .number(DEFAULT_NUMBER)
            .unitprice(DEFAULT_UNITPRICE)
            .totalprice(DEFAULT_TOTALPRICE);
        return contractCostBudget;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractCostBudget createUpdatedEntity(EntityManager em) {
        ContractCostBudget contractCostBudget = new ContractCostBudget()
            .subject(UPDATED_SUBJECT)
            .auxiliaryitem(UPDATED_AUXILIARYITEM)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .totalprice(UPDATED_TOTALPRICE);
        return contractCostBudget;
    }

    @BeforeEach
    public void initTest() {
        contractCostBudget = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedContractCostBudget != null) {
            contractCostBudgetRepository.delete(insertedContractCostBudget);
            insertedContractCostBudget = null;
        }
    }

    @Test
    @Transactional
    void createContractCostBudget() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ContractCostBudget
        var returnedContractCostBudget = om.readValue(
            restContractCostBudgetMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contractCostBudget)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ContractCostBudget.class
        );

        // Validate the ContractCostBudget in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertContractCostBudgetUpdatableFieldsEquals(
            returnedContractCostBudget,
            getPersistedContractCostBudget(returnedContractCostBudget)
        );

        insertedContractCostBudget = returnedContractCostBudget;
    }

    @Test
    @Transactional
    void createContractCostBudgetWithExistingId() throws Exception {
        // Create the ContractCostBudget with an existing ID
        contractCostBudget.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractCostBudgetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contractCostBudget)))
            .andExpect(status().isBadRequest());

        // Validate the ContractCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllContractCostBudgets() throws Exception {
        // Initialize the database
        insertedContractCostBudget = contractCostBudgetRepository.saveAndFlush(contractCostBudget);

        // Get all the contractCostBudgetList
        restContractCostBudgetMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractCostBudget.getId())))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT.toString())))
            .andExpect(jsonPath("$.[*].auxiliaryitem").value(hasItem(DEFAULT_AUXILIARYITEM)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].unitprice").value(hasItem(sameNumber(DEFAULT_UNITPRICE))))
            .andExpect(jsonPath("$.[*].totalprice").value(hasItem(sameNumber(DEFAULT_TOTALPRICE))));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllContractCostBudgetsWithEagerRelationshipsIsEnabled() throws Exception {
        when(contractCostBudgetServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restContractCostBudgetMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(contractCostBudgetServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllContractCostBudgetsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(contractCostBudgetServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restContractCostBudgetMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(contractCostBudgetRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getContractCostBudget() throws Exception {
        // Initialize the database
        insertedContractCostBudget = contractCostBudgetRepository.saveAndFlush(contractCostBudget);

        // Get the contractCostBudget
        restContractCostBudgetMockMvc
            .perform(get(ENTITY_API_URL_ID, contractCostBudget.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contractCostBudget.getId()))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT.toString()))
            .andExpect(jsonPath("$.auxiliaryitem").value(DEFAULT_AUXILIARYITEM))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.unitprice").value(sameNumber(DEFAULT_UNITPRICE)))
            .andExpect(jsonPath("$.totalprice").value(sameNumber(DEFAULT_TOTALPRICE)));
    }

    @Test
    @Transactional
    void getNonExistingContractCostBudget() throws Exception {
        // Get the contractCostBudget
        restContractCostBudgetMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingContractCostBudget() throws Exception {
        // Initialize the database
        insertedContractCostBudget = contractCostBudgetRepository.saveAndFlush(contractCostBudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contractCostBudget
        ContractCostBudget updatedContractCostBudget = contractCostBudgetRepository.findById(contractCostBudget.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedContractCostBudget are not directly saved in db
        em.detach(updatedContractCostBudget);
        updatedContractCostBudget
            .subject(UPDATED_SUBJECT)
            .auxiliaryitem(UPDATED_AUXILIARYITEM)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .totalprice(UPDATED_TOTALPRICE);

        restContractCostBudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedContractCostBudget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedContractCostBudget))
            )
            .andExpect(status().isOk());

        // Validate the ContractCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedContractCostBudgetToMatchAllProperties(updatedContractCostBudget);
    }

    @Test
    @Transactional
    void putNonExistingContractCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractCostBudget.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractCostBudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractCostBudget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(contractCostBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContractCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractCostBudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractCostBudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(contractCostBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContractCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractCostBudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractCostBudgetMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contractCostBudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractCostBudgetWithPatch() throws Exception {
        // Initialize the database
        insertedContractCostBudget = contractCostBudgetRepository.saveAndFlush(contractCostBudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contractCostBudget using partial update
        ContractCostBudget partialUpdatedContractCostBudget = new ContractCostBudget();
        partialUpdatedContractCostBudget.setId(contractCostBudget.getId());

        partialUpdatedContractCostBudget
            .subject(UPDATED_SUBJECT)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .totalprice(UPDATED_TOTALPRICE);

        restContractCostBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractCostBudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedContractCostBudget))
            )
            .andExpect(status().isOk());

        // Validate the ContractCostBudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertContractCostBudgetUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedContractCostBudget, contractCostBudget),
            getPersistedContractCostBudget(contractCostBudget)
        );
    }

    @Test
    @Transactional
    void fullUpdateContractCostBudgetWithPatch() throws Exception {
        // Initialize the database
        insertedContractCostBudget = contractCostBudgetRepository.saveAndFlush(contractCostBudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contractCostBudget using partial update
        ContractCostBudget partialUpdatedContractCostBudget = new ContractCostBudget();
        partialUpdatedContractCostBudget.setId(contractCostBudget.getId());

        partialUpdatedContractCostBudget
            .subject(UPDATED_SUBJECT)
            .auxiliaryitem(UPDATED_AUXILIARYITEM)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .totalprice(UPDATED_TOTALPRICE);

        restContractCostBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractCostBudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedContractCostBudget))
            )
            .andExpect(status().isOk());

        // Validate the ContractCostBudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertContractCostBudgetUpdatableFieldsEquals(
            partialUpdatedContractCostBudget,
            getPersistedContractCostBudget(partialUpdatedContractCostBudget)
        );
    }

    @Test
    @Transactional
    void patchNonExistingContractCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractCostBudget.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractCostBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractCostBudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(contractCostBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContractCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractCostBudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractCostBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(contractCostBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContractCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractCostBudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractCostBudgetMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(contractCostBudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContractCostBudget() throws Exception {
        // Initialize the database
        insertedContractCostBudget = contractCostBudgetRepository.saveAndFlush(contractCostBudget);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the contractCostBudget
        restContractCostBudgetMockMvc
            .perform(delete(ENTITY_API_URL_ID, contractCostBudget.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return contractCostBudgetRepository.count();
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

    protected ContractCostBudget getPersistedContractCostBudget(ContractCostBudget contractCostBudget) {
        return contractCostBudgetRepository.findById(contractCostBudget.getId()).orElseThrow();
    }

    protected void assertPersistedContractCostBudgetToMatchAllProperties(ContractCostBudget expectedContractCostBudget) {
        assertContractCostBudgetAllPropertiesEquals(expectedContractCostBudget, getPersistedContractCostBudget(expectedContractCostBudget));
    }

    protected void assertPersistedContractCostBudgetToMatchUpdatableProperties(ContractCostBudget expectedContractCostBudget) {
        assertContractCostBudgetAllUpdatablePropertiesEquals(
            expectedContractCostBudget,
            getPersistedContractCostBudget(expectedContractCostBudget)
        );
    }
}
