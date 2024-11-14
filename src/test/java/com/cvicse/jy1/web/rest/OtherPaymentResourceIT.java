package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.OtherPaymentAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.OtherPayment;
import com.cvicse.jy1.domain.enumeration.OtherPaymenttype;
import com.cvicse.jy1.repository.OtherPaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link OtherPaymentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OtherPaymentResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final OtherPaymenttype DEFAULT_TYPE = OtherPaymenttype.SPORADICPURCHASE;
    private static final OtherPaymenttype UPDATED_TYPE = OtherPaymenttype.SHARE;

    private static final LocalDate DEFAULT_REGISTERTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REGISTERTIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_SUBJECTID = 1;
    private static final Integer UPDATED_SUBJECTID = 2;

    private static final String DEFAULT_SUBJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECTNAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PAYMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAYMENTAMOUNT = new BigDecimal(2);

    private static final String DEFAULT_CONTRACTCODE = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTNAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_WBSID = "AAAAAAAAAA";
    private static final String UPDATED_WBSID = "BBBBBBBBBB";

    private static final String DEFAULT_WBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_WBSNAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/other-payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OtherPaymentRepository otherPaymentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOtherPaymentMockMvc;

    private OtherPayment otherPayment;

    private OtherPayment insertedOtherPayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OtherPayment createEntity(EntityManager em) {
        OtherPayment otherPayment = new OtherPayment()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .registertime(DEFAULT_REGISTERTIME)
            .subjectid(DEFAULT_SUBJECTID)
            .subjectname(DEFAULT_SUBJECTNAME)
            .paymentamount(DEFAULT_PAYMENTAMOUNT)
            .contractcode(DEFAULT_CONTRACTCODE)
            .contractname(DEFAULT_CONTRACTNAME)
            .wbsid(DEFAULT_WBSID)
            .wbsname(DEFAULT_WBSNAME);
        return otherPayment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OtherPayment createUpdatedEntity(EntityManager em) {
        OtherPayment otherPayment = new OtherPayment()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .registertime(UPDATED_REGISTERTIME)
            .subjectid(UPDATED_SUBJECTID)
            .subjectname(UPDATED_SUBJECTNAME)
            .paymentamount(UPDATED_PAYMENTAMOUNT)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME);
        return otherPayment;
    }

    @BeforeEach
    public void initTest() {
        otherPayment = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedOtherPayment != null) {
            otherPaymentRepository.delete(insertedOtherPayment);
            insertedOtherPayment = null;
        }
    }

    @Test
    @Transactional
    void createOtherPayment() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OtherPayment
        var returnedOtherPayment = om.readValue(
            restOtherPaymentMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(otherPayment)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OtherPayment.class
        );

        // Validate the OtherPayment in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOtherPaymentUpdatableFieldsEquals(returnedOtherPayment, getPersistedOtherPayment(returnedOtherPayment));

        insertedOtherPayment = returnedOtherPayment;
    }

    @Test
    @Transactional
    void createOtherPaymentWithExistingId() throws Exception {
        // Create the OtherPayment with an existing ID
        otherPayment.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOtherPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(otherPayment)))
            .andExpect(status().isBadRequest());

        // Validate the OtherPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOtherPayments() throws Exception {
        // Initialize the database
        insertedOtherPayment = otherPaymentRepository.saveAndFlush(otherPayment);

        // Get all the otherPaymentList
        restOtherPaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(otherPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].registertime").value(hasItem(DEFAULT_REGISTERTIME.toString())))
            .andExpect(jsonPath("$.[*].subjectid").value(hasItem(DEFAULT_SUBJECTID)))
            .andExpect(jsonPath("$.[*].subjectname").value(hasItem(DEFAULT_SUBJECTNAME)))
            .andExpect(jsonPath("$.[*].paymentamount").value(hasItem(sameNumber(DEFAULT_PAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].contractcode").value(hasItem(DEFAULT_CONTRACTCODE)))
            .andExpect(jsonPath("$.[*].contractname").value(hasItem(DEFAULT_CONTRACTNAME)))
            .andExpect(jsonPath("$.[*].wbsid").value(hasItem(DEFAULT_WBSID)))
            .andExpect(jsonPath("$.[*].wbsname").value(hasItem(DEFAULT_WBSNAME)));
    }

    @Test
    @Transactional
    void getOtherPayment() throws Exception {
        // Initialize the database
        insertedOtherPayment = otherPaymentRepository.saveAndFlush(otherPayment);

        // Get the otherPayment
        restOtherPaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, otherPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(otherPayment.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.registertime").value(DEFAULT_REGISTERTIME.toString()))
            .andExpect(jsonPath("$.subjectid").value(DEFAULT_SUBJECTID))
            .andExpect(jsonPath("$.subjectname").value(DEFAULT_SUBJECTNAME))
            .andExpect(jsonPath("$.paymentamount").value(sameNumber(DEFAULT_PAYMENTAMOUNT)))
            .andExpect(jsonPath("$.contractcode").value(DEFAULT_CONTRACTCODE))
            .andExpect(jsonPath("$.contractname").value(DEFAULT_CONTRACTNAME))
            .andExpect(jsonPath("$.wbsid").value(DEFAULT_WBSID))
            .andExpect(jsonPath("$.wbsname").value(DEFAULT_WBSNAME));
    }

    @Test
    @Transactional
    void getNonExistingOtherPayment() throws Exception {
        // Get the otherPayment
        restOtherPaymentMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOtherPayment() throws Exception {
        // Initialize the database
        insertedOtherPayment = otherPaymentRepository.saveAndFlush(otherPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the otherPayment
        OtherPayment updatedOtherPayment = otherPaymentRepository.findById(otherPayment.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOtherPayment are not directly saved in db
        em.detach(updatedOtherPayment);
        updatedOtherPayment
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .registertime(UPDATED_REGISTERTIME)
            .subjectid(UPDATED_SUBJECTID)
            .subjectname(UPDATED_SUBJECTNAME)
            .paymentamount(UPDATED_PAYMENTAMOUNT)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME);

        restOtherPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOtherPayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOtherPayment))
            )
            .andExpect(status().isOk());

        // Validate the OtherPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOtherPaymentToMatchAllProperties(updatedOtherPayment);
    }

    @Test
    @Transactional
    void putNonExistingOtherPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        otherPayment.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOtherPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, otherPayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(otherPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the OtherPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOtherPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        otherPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOtherPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(otherPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the OtherPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOtherPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        otherPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOtherPaymentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(otherPayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OtherPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOtherPaymentWithPatch() throws Exception {
        // Initialize the database
        insertedOtherPayment = otherPaymentRepository.saveAndFlush(otherPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the otherPayment using partial update
        OtherPayment partialUpdatedOtherPayment = new OtherPayment();
        partialUpdatedOtherPayment.setId(otherPayment.getId());

        partialUpdatedOtherPayment
            .name(UPDATED_NAME)
            .paymentamount(UPDATED_PAYMENTAMOUNT)
            .contractcode(UPDATED_CONTRACTCODE)
            .wbsname(UPDATED_WBSNAME);

        restOtherPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOtherPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOtherPayment))
            )
            .andExpect(status().isOk());

        // Validate the OtherPayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOtherPaymentUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOtherPayment, otherPayment),
            getPersistedOtherPayment(otherPayment)
        );
    }

    @Test
    @Transactional
    void fullUpdateOtherPaymentWithPatch() throws Exception {
        // Initialize the database
        insertedOtherPayment = otherPaymentRepository.saveAndFlush(otherPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the otherPayment using partial update
        OtherPayment partialUpdatedOtherPayment = new OtherPayment();
        partialUpdatedOtherPayment.setId(otherPayment.getId());

        partialUpdatedOtherPayment
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .registertime(UPDATED_REGISTERTIME)
            .subjectid(UPDATED_SUBJECTID)
            .subjectname(UPDATED_SUBJECTNAME)
            .paymentamount(UPDATED_PAYMENTAMOUNT)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME);

        restOtherPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOtherPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOtherPayment))
            )
            .andExpect(status().isOk());

        // Validate the OtherPayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOtherPaymentUpdatableFieldsEquals(partialUpdatedOtherPayment, getPersistedOtherPayment(partialUpdatedOtherPayment));
    }

    @Test
    @Transactional
    void patchNonExistingOtherPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        otherPayment.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOtherPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, otherPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(otherPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the OtherPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOtherPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        otherPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOtherPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(otherPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the OtherPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOtherPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        otherPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOtherPaymentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(otherPayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OtherPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOtherPayment() throws Exception {
        // Initialize the database
        insertedOtherPayment = otherPaymentRepository.saveAndFlush(otherPayment);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the otherPayment
        restOtherPaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, otherPayment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return otherPaymentRepository.count();
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

    protected OtherPayment getPersistedOtherPayment(OtherPayment otherPayment) {
        return otherPaymentRepository.findById(otherPayment.getId()).orElseThrow();
    }

    protected void assertPersistedOtherPaymentToMatchAllProperties(OtherPayment expectedOtherPayment) {
        assertOtherPaymentAllPropertiesEquals(expectedOtherPayment, getPersistedOtherPayment(expectedOtherPayment));
    }

    protected void assertPersistedOtherPaymentToMatchUpdatableProperties(OtherPayment expectedOtherPayment) {
        assertOtherPaymentAllUpdatablePropertiesEquals(expectedOtherPayment, getPersistedOtherPayment(expectedOtherPayment));
    }
}
