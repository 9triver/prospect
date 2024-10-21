package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.PaymentCostListAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.PaymentCostList;
import com.cvicse.jy1.repository.PaymentCostListRepository;
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
 * Integration tests for the {@link PaymentCostListResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PaymentCostListResourceIT {

    private static final String DEFAULT_WBSID = "AAAAAAAAAA";
    private static final String UPDATED_WBSID = "BBBBBBBBBB";

    private static final String DEFAULT_WBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_WBSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARENTWBSID = "AAAAAAAAAA";
    private static final String UPDATED_PARENTWBSID = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_UNITPRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNITPRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUMBER = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUMBER = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INVOICEPAYMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_INVOICEPAYMENTAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BORROWINGPAYMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BORROWINGPAYMENTAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ACCOUNTINGAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ACCOUNTINGAMOUNT = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/payment-cost-lists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PaymentCostListRepository paymentCostListRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaymentCostListMockMvc;

    private PaymentCostList paymentCostList;

    private PaymentCostList insertedPaymentCostList;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentCostList createEntity(EntityManager em) {
        PaymentCostList paymentCostList = new PaymentCostList()
            .wbsid(DEFAULT_WBSID)
            .wbsname(DEFAULT_WBSNAME)
            .parentwbsid(DEFAULT_PARENTWBSID)
            .unit(DEFAULT_UNIT)
            .unitprice(DEFAULT_UNITPRICE)
            .number(DEFAULT_NUMBER)
            .invoicepaymentamount(DEFAULT_INVOICEPAYMENTAMOUNT)
            .borrowingpaymentamount(DEFAULT_BORROWINGPAYMENTAMOUNT)
            .accountingamount(DEFAULT_ACCOUNTINGAMOUNT);
        return paymentCostList;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentCostList createUpdatedEntity(EntityManager em) {
        PaymentCostList paymentCostList = new PaymentCostList()
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .parentwbsid(UPDATED_PARENTWBSID)
            .unit(UPDATED_UNIT)
            .unitprice(UPDATED_UNITPRICE)
            .number(UPDATED_NUMBER)
            .invoicepaymentamount(UPDATED_INVOICEPAYMENTAMOUNT)
            .borrowingpaymentamount(UPDATED_BORROWINGPAYMENTAMOUNT)
            .accountingamount(UPDATED_ACCOUNTINGAMOUNT);
        return paymentCostList;
    }

    @BeforeEach
    public void initTest() {
        paymentCostList = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedPaymentCostList != null) {
            paymentCostListRepository.delete(insertedPaymentCostList);
            insertedPaymentCostList = null;
        }
    }

    @Test
    @Transactional
    void createPaymentCostList() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PaymentCostList
        var returnedPaymentCostList = om.readValue(
            restPaymentCostListMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentCostList)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PaymentCostList.class
        );

        // Validate the PaymentCostList in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPaymentCostListUpdatableFieldsEquals(returnedPaymentCostList, getPersistedPaymentCostList(returnedPaymentCostList));

        insertedPaymentCostList = returnedPaymentCostList;
    }

    @Test
    @Transactional
    void createPaymentCostListWithExistingId() throws Exception {
        // Create the PaymentCostList with an existing ID
        paymentCostList.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentCostListMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentCostList)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentCostList in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPaymentCostLists() throws Exception {
        // Initialize the database
        insertedPaymentCostList = paymentCostListRepository.saveAndFlush(paymentCostList);

        // Get all the paymentCostListList
        restPaymentCostListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentCostList.getId().intValue())))
            .andExpect(jsonPath("$.[*].wbsid").value(hasItem(DEFAULT_WBSID)))
            .andExpect(jsonPath("$.[*].wbsname").value(hasItem(DEFAULT_WBSNAME)))
            .andExpect(jsonPath("$.[*].parentwbsid").value(hasItem(DEFAULT_PARENTWBSID)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].unitprice").value(hasItem(sameNumber(DEFAULT_UNITPRICE))))
            .andExpect(jsonPath("$.[*].number").value(hasItem(sameNumber(DEFAULT_NUMBER))))
            .andExpect(jsonPath("$.[*].invoicepaymentamount").value(hasItem(sameNumber(DEFAULT_INVOICEPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].borrowingpaymentamount").value(hasItem(sameNumber(DEFAULT_BORROWINGPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].accountingamount").value(hasItem(sameNumber(DEFAULT_ACCOUNTINGAMOUNT))));
    }

    @Test
    @Transactional
    void getPaymentCostList() throws Exception {
        // Initialize the database
        insertedPaymentCostList = paymentCostListRepository.saveAndFlush(paymentCostList);

        // Get the paymentCostList
        restPaymentCostListMockMvc
            .perform(get(ENTITY_API_URL_ID, paymentCostList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentCostList.getId().intValue()))
            .andExpect(jsonPath("$.wbsid").value(DEFAULT_WBSID))
            .andExpect(jsonPath("$.wbsname").value(DEFAULT_WBSNAME))
            .andExpect(jsonPath("$.parentwbsid").value(DEFAULT_PARENTWBSID))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.unitprice").value(sameNumber(DEFAULT_UNITPRICE)))
            .andExpect(jsonPath("$.number").value(sameNumber(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.invoicepaymentamount").value(sameNumber(DEFAULT_INVOICEPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.borrowingpaymentamount").value(sameNumber(DEFAULT_BORROWINGPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.accountingamount").value(sameNumber(DEFAULT_ACCOUNTINGAMOUNT)));
    }

    @Test
    @Transactional
    void getNonExistingPaymentCostList() throws Exception {
        // Get the paymentCostList
        restPaymentCostListMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPaymentCostList() throws Exception {
        // Initialize the database
        insertedPaymentCostList = paymentCostListRepository.saveAndFlush(paymentCostList);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentCostList
        PaymentCostList updatedPaymentCostList = paymentCostListRepository.findById(paymentCostList.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPaymentCostList are not directly saved in db
        em.detach(updatedPaymentCostList);
        updatedPaymentCostList
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .parentwbsid(UPDATED_PARENTWBSID)
            .unit(UPDATED_UNIT)
            .unitprice(UPDATED_UNITPRICE)
            .number(UPDATED_NUMBER)
            .invoicepaymentamount(UPDATED_INVOICEPAYMENTAMOUNT)
            .borrowingpaymentamount(UPDATED_BORROWINGPAYMENTAMOUNT)
            .accountingamount(UPDATED_ACCOUNTINGAMOUNT);

        restPaymentCostListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPaymentCostList.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPaymentCostList))
            )
            .andExpect(status().isOk());

        // Validate the PaymentCostList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPaymentCostListToMatchAllProperties(updatedPaymentCostList);
    }

    @Test
    @Transactional
    void putNonExistingPaymentCostList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCostList.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentCostListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentCostList.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentCostList))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCostList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPaymentCostList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCostList.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentCostListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentCostList))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCostList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPaymentCostList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCostList.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentCostListMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentCostList)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentCostList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePaymentCostListWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentCostList = paymentCostListRepository.saveAndFlush(paymentCostList);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentCostList using partial update
        PaymentCostList partialUpdatedPaymentCostList = new PaymentCostList();
        partialUpdatedPaymentCostList.setId(paymentCostList.getId());

        partialUpdatedPaymentCostList
            .wbsid(UPDATED_WBSID)
            .parentwbsid(UPDATED_PARENTWBSID)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .invoicepaymentamount(UPDATED_INVOICEPAYMENTAMOUNT)
            .borrowingpaymentamount(UPDATED_BORROWINGPAYMENTAMOUNT);

        restPaymentCostListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentCostList.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentCostList))
            )
            .andExpect(status().isOk());

        // Validate the PaymentCostList in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentCostListUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPaymentCostList, paymentCostList),
            getPersistedPaymentCostList(paymentCostList)
        );
    }

    @Test
    @Transactional
    void fullUpdatePaymentCostListWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentCostList = paymentCostListRepository.saveAndFlush(paymentCostList);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentCostList using partial update
        PaymentCostList partialUpdatedPaymentCostList = new PaymentCostList();
        partialUpdatedPaymentCostList.setId(paymentCostList.getId());

        partialUpdatedPaymentCostList
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .parentwbsid(UPDATED_PARENTWBSID)
            .unit(UPDATED_UNIT)
            .unitprice(UPDATED_UNITPRICE)
            .number(UPDATED_NUMBER)
            .invoicepaymentamount(UPDATED_INVOICEPAYMENTAMOUNT)
            .borrowingpaymentamount(UPDATED_BORROWINGPAYMENTAMOUNT)
            .accountingamount(UPDATED_ACCOUNTINGAMOUNT);

        restPaymentCostListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentCostList.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentCostList))
            )
            .andExpect(status().isOk());

        // Validate the PaymentCostList in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentCostListUpdatableFieldsEquals(
            partialUpdatedPaymentCostList,
            getPersistedPaymentCostList(partialUpdatedPaymentCostList)
        );
    }

    @Test
    @Transactional
    void patchNonExistingPaymentCostList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCostList.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentCostListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, paymentCostList.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentCostList))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCostList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPaymentCostList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCostList.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentCostListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentCostList))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCostList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPaymentCostList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCostList.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentCostListMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(paymentCostList)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentCostList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePaymentCostList() throws Exception {
        // Initialize the database
        insertedPaymentCostList = paymentCostListRepository.saveAndFlush(paymentCostList);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the paymentCostList
        restPaymentCostListMockMvc
            .perform(delete(ENTITY_API_URL_ID, paymentCostList.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return paymentCostListRepository.count();
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

    protected PaymentCostList getPersistedPaymentCostList(PaymentCostList paymentCostList) {
        return paymentCostListRepository.findById(paymentCostList.getId()).orElseThrow();
    }

    protected void assertPersistedPaymentCostListToMatchAllProperties(PaymentCostList expectedPaymentCostList) {
        assertPaymentCostListAllPropertiesEquals(expectedPaymentCostList, getPersistedPaymentCostList(expectedPaymentCostList));
    }

    protected void assertPersistedPaymentCostListToMatchUpdatableProperties(PaymentCostList expectedPaymentCostList) {
        assertPaymentCostListAllUpdatablePropertiesEquals(expectedPaymentCostList, getPersistedPaymentCostList(expectedPaymentCostList));
    }
}
