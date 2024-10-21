package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.FundSourceListAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.FundSourceList;
import com.cvicse.jy1.repository.FundSourceListRepository;
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
 * Integration tests for the {@link FundSourceListResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FundSourceListResourceIT {

    private static final String DEFAULT_PAYMENTID = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENTID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTCODE = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTNAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTNAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/fund-source-lists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FundSourceListRepository fundSourceListRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFundSourceListMockMvc;

    private FundSourceList fundSourceList;

    private FundSourceList insertedFundSourceList;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundSourceList createEntity(EntityManager em) {
        FundSourceList fundSourceList = new FundSourceList()
            .paymentid(DEFAULT_PAYMENTID)
            .contractcode(DEFAULT_CONTRACTCODE)
            .contractname(DEFAULT_CONTRACTNAME)
            .amount(DEFAULT_AMOUNT);
        return fundSourceList;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundSourceList createUpdatedEntity(EntityManager em) {
        FundSourceList fundSourceList = new FundSourceList()
            .paymentid(UPDATED_PAYMENTID)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .amount(UPDATED_AMOUNT);
        return fundSourceList;
    }

    @BeforeEach
    public void initTest() {
        fundSourceList = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedFundSourceList != null) {
            fundSourceListRepository.delete(insertedFundSourceList);
            insertedFundSourceList = null;
        }
    }

    @Test
    @Transactional
    void createFundSourceList() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FundSourceList
        var returnedFundSourceList = om.readValue(
            restFundSourceListMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundSourceList)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FundSourceList.class
        );

        // Validate the FundSourceList in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertFundSourceListUpdatableFieldsEquals(returnedFundSourceList, getPersistedFundSourceList(returnedFundSourceList));

        insertedFundSourceList = returnedFundSourceList;
    }

    @Test
    @Transactional
    void createFundSourceListWithExistingId() throws Exception {
        // Create the FundSourceList with an existing ID
        fundSourceList.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundSourceListMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundSourceList)))
            .andExpect(status().isBadRequest());

        // Validate the FundSourceList in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFundSourceLists() throws Exception {
        // Initialize the database
        insertedFundSourceList = fundSourceListRepository.saveAndFlush(fundSourceList);

        // Get all the fundSourceListList
        restFundSourceListMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundSourceList.getId().intValue())))
            .andExpect(jsonPath("$.[*].paymentid").value(hasItem(DEFAULT_PAYMENTID)))
            .andExpect(jsonPath("$.[*].contractcode").value(hasItem(DEFAULT_CONTRACTCODE)))
            .andExpect(jsonPath("$.[*].contractname").value(hasItem(DEFAULT_CONTRACTNAME)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(sameNumber(DEFAULT_AMOUNT))));
    }

    @Test
    @Transactional
    void getFundSourceList() throws Exception {
        // Initialize the database
        insertedFundSourceList = fundSourceListRepository.saveAndFlush(fundSourceList);

        // Get the fundSourceList
        restFundSourceListMockMvc
            .perform(get(ENTITY_API_URL_ID, fundSourceList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fundSourceList.getId().intValue()))
            .andExpect(jsonPath("$.paymentid").value(DEFAULT_PAYMENTID))
            .andExpect(jsonPath("$.contractcode").value(DEFAULT_CONTRACTCODE))
            .andExpect(jsonPath("$.contractname").value(DEFAULT_CONTRACTNAME))
            .andExpect(jsonPath("$.amount").value(sameNumber(DEFAULT_AMOUNT)));
    }

    @Test
    @Transactional
    void getNonExistingFundSourceList() throws Exception {
        // Get the fundSourceList
        restFundSourceListMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFundSourceList() throws Exception {
        // Initialize the database
        insertedFundSourceList = fundSourceListRepository.saveAndFlush(fundSourceList);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundSourceList
        FundSourceList updatedFundSourceList = fundSourceListRepository.findById(fundSourceList.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFundSourceList are not directly saved in db
        em.detach(updatedFundSourceList);
        updatedFundSourceList
            .paymentid(UPDATED_PAYMENTID)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .amount(UPDATED_AMOUNT);

        restFundSourceListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFundSourceList.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedFundSourceList))
            )
            .andExpect(status().isOk());

        // Validate the FundSourceList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFundSourceListToMatchAllProperties(updatedFundSourceList);
    }

    @Test
    @Transactional
    void putNonExistingFundSourceList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundSourceList.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundSourceListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fundSourceList.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundSourceList))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundSourceList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFundSourceList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundSourceList.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundSourceListMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundSourceList))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundSourceList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFundSourceList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundSourceList.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundSourceListMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundSourceList)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FundSourceList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFundSourceListWithPatch() throws Exception {
        // Initialize the database
        insertedFundSourceList = fundSourceListRepository.saveAndFlush(fundSourceList);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundSourceList using partial update
        FundSourceList partialUpdatedFundSourceList = new FundSourceList();
        partialUpdatedFundSourceList.setId(fundSourceList.getId());

        partialUpdatedFundSourceList.contractcode(UPDATED_CONTRACTCODE).amount(UPDATED_AMOUNT);

        restFundSourceListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundSourceList.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundSourceList))
            )
            .andExpect(status().isOk());

        // Validate the FundSourceList in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundSourceListUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFundSourceList, fundSourceList),
            getPersistedFundSourceList(fundSourceList)
        );
    }

    @Test
    @Transactional
    void fullUpdateFundSourceListWithPatch() throws Exception {
        // Initialize the database
        insertedFundSourceList = fundSourceListRepository.saveAndFlush(fundSourceList);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundSourceList using partial update
        FundSourceList partialUpdatedFundSourceList = new FundSourceList();
        partialUpdatedFundSourceList.setId(fundSourceList.getId());

        partialUpdatedFundSourceList
            .paymentid(UPDATED_PAYMENTID)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .amount(UPDATED_AMOUNT);

        restFundSourceListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundSourceList.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundSourceList))
            )
            .andExpect(status().isOk());

        // Validate the FundSourceList in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundSourceListUpdatableFieldsEquals(partialUpdatedFundSourceList, getPersistedFundSourceList(partialUpdatedFundSourceList));
    }

    @Test
    @Transactional
    void patchNonExistingFundSourceList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundSourceList.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundSourceListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fundSourceList.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundSourceList))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundSourceList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFundSourceList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundSourceList.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundSourceListMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundSourceList))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundSourceList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFundSourceList() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundSourceList.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundSourceListMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fundSourceList)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FundSourceList in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFundSourceList() throws Exception {
        // Initialize the database
        insertedFundSourceList = fundSourceListRepository.saveAndFlush(fundSourceList);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fundSourceList
        restFundSourceListMockMvc
            .perform(delete(ENTITY_API_URL_ID, fundSourceList.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fundSourceListRepository.count();
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

    protected FundSourceList getPersistedFundSourceList(FundSourceList fundSourceList) {
        return fundSourceListRepository.findById(fundSourceList.getId()).orElseThrow();
    }

    protected void assertPersistedFundSourceListToMatchAllProperties(FundSourceList expectedFundSourceList) {
        assertFundSourceListAllPropertiesEquals(expectedFundSourceList, getPersistedFundSourceList(expectedFundSourceList));
    }

    protected void assertPersistedFundSourceListToMatchUpdatableProperties(FundSourceList expectedFundSourceList) {
        assertFundSourceListAllUpdatablePropertiesEquals(expectedFundSourceList, getPersistedFundSourceList(expectedFundSourceList));
    }
}
