package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.CustomerSatisfactionAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.CustomerSatisfaction;
import com.cvicse.jy1.repository.CustomerSatisfactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CustomerSatisfactionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CustomerSatisfactionResourceIT {

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    private static final String DEFAULT_SATISFACTIONITEM = "AAAAAAAAAA";
    private static final String UPDATED_SATISFACTIONITEM = "BBBBBBBBBB";

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final String DEFAULT_OPINION = "AAAAAAAAAA";
    private static final String UPDATED_OPINION = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTALSCORE = 1;
    private static final Integer UPDATED_TOTALSCORE = 2;

    private static final LocalDate DEFAULT_SURVEYTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SURVEYTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CUSTOMER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER = "BBBBBBBBBB";

    private static final String DEFAULT_PLONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLONENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FILENAME = "AAAAAAAAAA";
    private static final String UPDATED_FILENAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/customer-satisfactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CustomerSatisfactionRepository customerSatisfactionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerSatisfactionMockMvc;

    private CustomerSatisfaction customerSatisfaction;

    private CustomerSatisfaction insertedCustomerSatisfaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerSatisfaction createEntity(EntityManager em) {
        CustomerSatisfaction customerSatisfaction = new CustomerSatisfaction()
            .year(DEFAULT_YEAR)
            .satisfactionitem(DEFAULT_SATISFACTIONITEM)
            .score(DEFAULT_SCORE)
            .opinion(DEFAULT_OPINION)
            .totalscore(DEFAULT_TOTALSCORE)
            .surveytime(DEFAULT_SURVEYTIME)
            .customer(DEFAULT_CUSTOMER)
            .plonenumber(DEFAULT_PLONENUMBER)
            .filename(DEFAULT_FILENAME);
        return customerSatisfaction;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerSatisfaction createUpdatedEntity(EntityManager em) {
        CustomerSatisfaction customerSatisfaction = new CustomerSatisfaction()
            .year(UPDATED_YEAR)
            .satisfactionitem(UPDATED_SATISFACTIONITEM)
            .score(UPDATED_SCORE)
            .opinion(UPDATED_OPINION)
            .totalscore(UPDATED_TOTALSCORE)
            .surveytime(UPDATED_SURVEYTIME)
            .customer(UPDATED_CUSTOMER)
            .plonenumber(UPDATED_PLONENUMBER)
            .filename(UPDATED_FILENAME);
        return customerSatisfaction;
    }

    @BeforeEach
    public void initTest() {
        customerSatisfaction = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCustomerSatisfaction != null) {
            customerSatisfactionRepository.delete(insertedCustomerSatisfaction);
            insertedCustomerSatisfaction = null;
        }
    }

    @Test
    @Transactional
    void createCustomerSatisfaction() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CustomerSatisfaction
        var returnedCustomerSatisfaction = om.readValue(
            restCustomerSatisfactionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customerSatisfaction)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CustomerSatisfaction.class
        );

        // Validate the CustomerSatisfaction in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCustomerSatisfactionUpdatableFieldsEquals(
            returnedCustomerSatisfaction,
            getPersistedCustomerSatisfaction(returnedCustomerSatisfaction)
        );

        insertedCustomerSatisfaction = returnedCustomerSatisfaction;
    }

    @Test
    @Transactional
    void createCustomerSatisfactionWithExistingId() throws Exception {
        // Create the CustomerSatisfaction with an existing ID
        customerSatisfaction.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerSatisfactionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customerSatisfaction)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerSatisfaction in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCustomerSatisfactions() throws Exception {
        // Initialize the database
        insertedCustomerSatisfaction = customerSatisfactionRepository.saveAndFlush(customerSatisfaction);

        // Get all the customerSatisfactionList
        restCustomerSatisfactionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerSatisfaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].satisfactionitem").value(hasItem(DEFAULT_SATISFACTIONITEM)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].opinion").value(hasItem(DEFAULT_OPINION)))
            .andExpect(jsonPath("$.[*].totalscore").value(hasItem(DEFAULT_TOTALSCORE)))
            .andExpect(jsonPath("$.[*].surveytime").value(hasItem(DEFAULT_SURVEYTIME.toString())))
            .andExpect(jsonPath("$.[*].customer").value(hasItem(DEFAULT_CUSTOMER)))
            .andExpect(jsonPath("$.[*].plonenumber").value(hasItem(DEFAULT_PLONENUMBER)))
            .andExpect(jsonPath("$.[*].filename").value(hasItem(DEFAULT_FILENAME)));
    }

    @Test
    @Transactional
    void getCustomerSatisfaction() throws Exception {
        // Initialize the database
        insertedCustomerSatisfaction = customerSatisfactionRepository.saveAndFlush(customerSatisfaction);

        // Get the customerSatisfaction
        restCustomerSatisfactionMockMvc
            .perform(get(ENTITY_API_URL_ID, customerSatisfaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customerSatisfaction.getId().intValue()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.satisfactionitem").value(DEFAULT_SATISFACTIONITEM))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.opinion").value(DEFAULT_OPINION))
            .andExpect(jsonPath("$.totalscore").value(DEFAULT_TOTALSCORE))
            .andExpect(jsonPath("$.surveytime").value(DEFAULT_SURVEYTIME.toString()))
            .andExpect(jsonPath("$.customer").value(DEFAULT_CUSTOMER))
            .andExpect(jsonPath("$.plonenumber").value(DEFAULT_PLONENUMBER))
            .andExpect(jsonPath("$.filename").value(DEFAULT_FILENAME));
    }

    @Test
    @Transactional
    void getNonExistingCustomerSatisfaction() throws Exception {
        // Get the customerSatisfaction
        restCustomerSatisfactionMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustomerSatisfaction() throws Exception {
        // Initialize the database
        insertedCustomerSatisfaction = customerSatisfactionRepository.saveAndFlush(customerSatisfaction);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customerSatisfaction
        CustomerSatisfaction updatedCustomerSatisfaction = customerSatisfactionRepository
            .findById(customerSatisfaction.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedCustomerSatisfaction are not directly saved in db
        em.detach(updatedCustomerSatisfaction);
        updatedCustomerSatisfaction
            .year(UPDATED_YEAR)
            .satisfactionitem(UPDATED_SATISFACTIONITEM)
            .score(UPDATED_SCORE)
            .opinion(UPDATED_OPINION)
            .totalscore(UPDATED_TOTALSCORE)
            .surveytime(UPDATED_SURVEYTIME)
            .customer(UPDATED_CUSTOMER)
            .plonenumber(UPDATED_PLONENUMBER)
            .filename(UPDATED_FILENAME);

        restCustomerSatisfactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCustomerSatisfaction.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCustomerSatisfaction))
            )
            .andExpect(status().isOk());

        // Validate the CustomerSatisfaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCustomerSatisfactionToMatchAllProperties(updatedCustomerSatisfaction);
    }

    @Test
    @Transactional
    void putNonExistingCustomerSatisfaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerSatisfaction.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerSatisfactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customerSatisfaction.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customerSatisfaction))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomerSatisfaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustomerSatisfaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerSatisfaction.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerSatisfactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customerSatisfaction))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomerSatisfaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustomerSatisfaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerSatisfaction.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerSatisfactionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customerSatisfaction)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomerSatisfaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomerSatisfactionWithPatch() throws Exception {
        // Initialize the database
        insertedCustomerSatisfaction = customerSatisfactionRepository.saveAndFlush(customerSatisfaction);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customerSatisfaction using partial update
        CustomerSatisfaction partialUpdatedCustomerSatisfaction = new CustomerSatisfaction();
        partialUpdatedCustomerSatisfaction.setId(customerSatisfaction.getId());

        partialUpdatedCustomerSatisfaction
            .opinion(UPDATED_OPINION)
            .surveytime(UPDATED_SURVEYTIME)
            .plonenumber(UPDATED_PLONENUMBER)
            .filename(UPDATED_FILENAME);

        restCustomerSatisfactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomerSatisfaction.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomerSatisfaction))
            )
            .andExpect(status().isOk());

        // Validate the CustomerSatisfaction in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomerSatisfactionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCustomerSatisfaction, customerSatisfaction),
            getPersistedCustomerSatisfaction(customerSatisfaction)
        );
    }

    @Test
    @Transactional
    void fullUpdateCustomerSatisfactionWithPatch() throws Exception {
        // Initialize the database
        insertedCustomerSatisfaction = customerSatisfactionRepository.saveAndFlush(customerSatisfaction);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customerSatisfaction using partial update
        CustomerSatisfaction partialUpdatedCustomerSatisfaction = new CustomerSatisfaction();
        partialUpdatedCustomerSatisfaction.setId(customerSatisfaction.getId());

        partialUpdatedCustomerSatisfaction
            .year(UPDATED_YEAR)
            .satisfactionitem(UPDATED_SATISFACTIONITEM)
            .score(UPDATED_SCORE)
            .opinion(UPDATED_OPINION)
            .totalscore(UPDATED_TOTALSCORE)
            .surveytime(UPDATED_SURVEYTIME)
            .customer(UPDATED_CUSTOMER)
            .plonenumber(UPDATED_PLONENUMBER)
            .filename(UPDATED_FILENAME);

        restCustomerSatisfactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomerSatisfaction.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomerSatisfaction))
            )
            .andExpect(status().isOk());

        // Validate the CustomerSatisfaction in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomerSatisfactionUpdatableFieldsEquals(
            partialUpdatedCustomerSatisfaction,
            getPersistedCustomerSatisfaction(partialUpdatedCustomerSatisfaction)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCustomerSatisfaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerSatisfaction.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerSatisfactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customerSatisfaction.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customerSatisfaction))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomerSatisfaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustomerSatisfaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerSatisfaction.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerSatisfactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customerSatisfaction))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomerSatisfaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustomerSatisfaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customerSatisfaction.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerSatisfactionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(customerSatisfaction)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomerSatisfaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustomerSatisfaction() throws Exception {
        // Initialize the database
        insertedCustomerSatisfaction = customerSatisfactionRepository.saveAndFlush(customerSatisfaction);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the customerSatisfaction
        restCustomerSatisfactionMockMvc
            .perform(delete(ENTITY_API_URL_ID, customerSatisfaction.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return customerSatisfactionRepository.count();
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

    protected CustomerSatisfaction getPersistedCustomerSatisfaction(CustomerSatisfaction customerSatisfaction) {
        return customerSatisfactionRepository.findById(customerSatisfaction.getId()).orElseThrow();
    }

    protected void assertPersistedCustomerSatisfactionToMatchAllProperties(CustomerSatisfaction expectedCustomerSatisfaction) {
        assertCustomerSatisfactionAllPropertiesEquals(
            expectedCustomerSatisfaction,
            getPersistedCustomerSatisfaction(expectedCustomerSatisfaction)
        );
    }

    protected void assertPersistedCustomerSatisfactionToMatchUpdatableProperties(CustomerSatisfaction expectedCustomerSatisfaction) {
        assertCustomerSatisfactionAllUpdatablePropertiesEquals(
            expectedCustomerSatisfaction,
            getPersistedCustomerSatisfaction(expectedCustomerSatisfaction)
        );
    }
}
