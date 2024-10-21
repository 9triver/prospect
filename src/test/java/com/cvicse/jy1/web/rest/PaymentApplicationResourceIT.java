package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.PaymentApplicationAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.PaymentApplication;
import com.cvicse.jy1.repository.PaymentApplicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link PaymentApplicationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PaymentApplicationResourceIT {

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTCODE = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_PLANPAYMENTNODE = "AAAAAAAAAA";
    private static final String UPDATED_PLANPAYMENTNODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PLANPAYMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PLANPAYMENTAMOUNT = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/payment-applications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PaymentApplicationRepository paymentApplicationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaymentApplicationMockMvc;

    private PaymentApplication paymentApplication;

    private PaymentApplication insertedPaymentApplication;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentApplication createEntity(EntityManager em) {
        PaymentApplication paymentApplication = new PaymentApplication()
            .workbagid(DEFAULT_WORKBAGID)
            .contractcode(DEFAULT_CONTRACTCODE)
            .planpaymentnode(DEFAULT_PLANPAYMENTNODE)
            .planpaymentamount(DEFAULT_PLANPAYMENTAMOUNT);
        return paymentApplication;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentApplication createUpdatedEntity(EntityManager em) {
        PaymentApplication paymentApplication = new PaymentApplication()
            .workbagid(UPDATED_WORKBAGID)
            .contractcode(UPDATED_CONTRACTCODE)
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT);
        return paymentApplication;
    }

    @BeforeEach
    public void initTest() {
        paymentApplication = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedPaymentApplication != null) {
            paymentApplicationRepository.delete(insertedPaymentApplication);
            insertedPaymentApplication = null;
        }
    }

    @Test
    @Transactional
    void createPaymentApplication() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PaymentApplication
        var returnedPaymentApplication = om.readValue(
            restPaymentApplicationMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentApplication)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PaymentApplication.class
        );

        // Validate the PaymentApplication in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPaymentApplicationUpdatableFieldsEquals(
            returnedPaymentApplication,
            getPersistedPaymentApplication(returnedPaymentApplication)
        );

        insertedPaymentApplication = returnedPaymentApplication;
    }

    @Test
    @Transactional
    void createPaymentApplicationWithExistingId() throws Exception {
        // Create the PaymentApplication with an existing ID
        paymentApplication.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentApplicationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentApplication)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPaymentApplications() throws Exception {
        // Initialize the database
        insertedPaymentApplication = paymentApplicationRepository.saveAndFlush(paymentApplication);

        // Get all the paymentApplicationList
        restPaymentApplicationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentApplication.getId().intValue())))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].contractcode").value(hasItem(DEFAULT_CONTRACTCODE)))
            .andExpect(jsonPath("$.[*].planpaymentnode").value(hasItem(DEFAULT_PLANPAYMENTNODE)))
            .andExpect(jsonPath("$.[*].planpaymentamount").value(hasItem(sameNumber(DEFAULT_PLANPAYMENTAMOUNT))));
    }

    @Test
    @Transactional
    void getPaymentApplication() throws Exception {
        // Initialize the database
        insertedPaymentApplication = paymentApplicationRepository.saveAndFlush(paymentApplication);

        // Get the paymentApplication
        restPaymentApplicationMockMvc
            .perform(get(ENTITY_API_URL_ID, paymentApplication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentApplication.getId().intValue()))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.contractcode").value(DEFAULT_CONTRACTCODE))
            .andExpect(jsonPath("$.planpaymentnode").value(DEFAULT_PLANPAYMENTNODE))
            .andExpect(jsonPath("$.planpaymentamount").value(sameNumber(DEFAULT_PLANPAYMENTAMOUNT)));
    }

    @Test
    @Transactional
    void getNonExistingPaymentApplication() throws Exception {
        // Get the paymentApplication
        restPaymentApplicationMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPaymentApplication() throws Exception {
        // Initialize the database
        insertedPaymentApplication = paymentApplicationRepository.saveAndFlush(paymentApplication);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentApplication
        PaymentApplication updatedPaymentApplication = paymentApplicationRepository.findById(paymentApplication.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPaymentApplication are not directly saved in db
        em.detach(updatedPaymentApplication);
        updatedPaymentApplication
            .workbagid(UPDATED_WORKBAGID)
            .contractcode(UPDATED_CONTRACTCODE)
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT);

        restPaymentApplicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPaymentApplication.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPaymentApplication))
            )
            .andExpect(status().isOk());

        // Validate the PaymentApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPaymentApplicationToMatchAllProperties(updatedPaymentApplication);
    }

    @Test
    @Transactional
    void putNonExistingPaymentApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentApplication.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentApplicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentApplication.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPaymentApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentApplication.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentApplicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPaymentApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentApplication.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentApplicationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentApplication)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePaymentApplicationWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentApplication = paymentApplicationRepository.saveAndFlush(paymentApplication);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentApplication using partial update
        PaymentApplication partialUpdatedPaymentApplication = new PaymentApplication();
        partialUpdatedPaymentApplication.setId(paymentApplication.getId());

        partialUpdatedPaymentApplication
            .workbagid(UPDATED_WORKBAGID)
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT);

        restPaymentApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentApplication.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentApplication))
            )
            .andExpect(status().isOk());

        // Validate the PaymentApplication in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentApplicationUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPaymentApplication, paymentApplication),
            getPersistedPaymentApplication(paymentApplication)
        );
    }

    @Test
    @Transactional
    void fullUpdatePaymentApplicationWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentApplication = paymentApplicationRepository.saveAndFlush(paymentApplication);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentApplication using partial update
        PaymentApplication partialUpdatedPaymentApplication = new PaymentApplication();
        partialUpdatedPaymentApplication.setId(paymentApplication.getId());

        partialUpdatedPaymentApplication
            .workbagid(UPDATED_WORKBAGID)
            .contractcode(UPDATED_CONTRACTCODE)
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT);

        restPaymentApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentApplication.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentApplication))
            )
            .andExpect(status().isOk());

        // Validate the PaymentApplication in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentApplicationUpdatableFieldsEquals(
            partialUpdatedPaymentApplication,
            getPersistedPaymentApplication(partialUpdatedPaymentApplication)
        );
    }

    @Test
    @Transactional
    void patchNonExistingPaymentApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentApplication.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, paymentApplication.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPaymentApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentApplication.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPaymentApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentApplication.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentApplicationMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(paymentApplication)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePaymentApplication() throws Exception {
        // Initialize the database
        insertedPaymentApplication = paymentApplicationRepository.saveAndFlush(paymentApplication);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the paymentApplication
        restPaymentApplicationMockMvc
            .perform(delete(ENTITY_API_URL_ID, paymentApplication.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return paymentApplicationRepository.count();
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

    protected PaymentApplication getPersistedPaymentApplication(PaymentApplication paymentApplication) {
        return paymentApplicationRepository.findById(paymentApplication.getId()).orElseThrow();
    }

    protected void assertPersistedPaymentApplicationToMatchAllProperties(PaymentApplication expectedPaymentApplication) {
        assertPaymentApplicationAllPropertiesEquals(expectedPaymentApplication, getPersistedPaymentApplication(expectedPaymentApplication));
    }

    protected void assertPersistedPaymentApplicationToMatchUpdatableProperties(PaymentApplication expectedPaymentApplication) {
        assertPaymentApplicationAllUpdatablePropertiesEquals(
            expectedPaymentApplication,
            getPersistedPaymentApplication(expectedPaymentApplication)
        );
    }
}
