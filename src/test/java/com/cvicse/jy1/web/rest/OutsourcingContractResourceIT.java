package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.OutsourcingContractAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.OutsourcingContract;
import com.cvicse.jy1.repository.OutsourcingContractRepository;
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
 * Integration tests for the {@link OutsourcingContractResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OutsourcingContractResourceIT {

    private static final String DEFAULT_CONTRACTID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTCODE = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTNAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTQUALITYID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTQUALITYID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTCOSTID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTCOSTID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTFINANCEID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTFINANCEID = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTID = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTID = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTSECRETLEVEL = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTSECRETLEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTERPARTYUNIT = "AAAAAAAAAA";
    private static final String UPDATED_COUNTERPARTYUNIT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NEGOTIATIONDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NEGOTIATIONDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NEGOTIATIONLOCATION = "AAAAAAAAAA";
    private static final String UPDATED_NEGOTIATIONLOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_NEGOTIATOR = "AAAAAAAAAA";
    private static final String UPDATED_NEGOTIATOR = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BUDGETAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUDGETAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONTRACTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONTRACTAMOUNT = new BigDecimal(2);

    private static final String DEFAULT_APPROVER = "AAAAAAAAAA";
    private static final String UPDATED_APPROVER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_APPROVALDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPROVALDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CONTRACTSECRETLEVEL = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTSECRETLEVEL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/outsourcing-contracts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OutsourcingContractRepository outsourcingContractRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOutsourcingContractMockMvc;

    private OutsourcingContract outsourcingContract;

    private OutsourcingContract insertedOutsourcingContract;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingContract createEntity(EntityManager em) {
        OutsourcingContract outsourcingContract = new OutsourcingContract()
            .contractid(DEFAULT_CONTRACTID)
            .contractcode(DEFAULT_CONTRACTCODE)
            .contractname(DEFAULT_CONTRACTNAME)
            .contractqualityid(DEFAULT_CONTRACTQUALITYID)
            .contractcostid(DEFAULT_CONTRACTCOSTID)
            .contractfinanceid(DEFAULT_CONTRACTFINANCEID)
            .projectid(DEFAULT_PROJECTID)
            .projectsecretlevel(DEFAULT_PROJECTSECRETLEVEL)
            .counterpartyunit(DEFAULT_COUNTERPARTYUNIT)
            .negotiationdate(DEFAULT_NEGOTIATIONDATE)
            .negotiationlocation(DEFAULT_NEGOTIATIONLOCATION)
            .negotiator(DEFAULT_NEGOTIATOR)
            .budgetamount(DEFAULT_BUDGETAMOUNT)
            .contractamount(DEFAULT_CONTRACTAMOUNT)
            .approver(DEFAULT_APPROVER)
            .approvaldate(DEFAULT_APPROVALDATE)
            .contractsecretlevel(DEFAULT_CONTRACTSECRETLEVEL);
        return outsourcingContract;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingContract createUpdatedEntity(EntityManager em) {
        OutsourcingContract outsourcingContract = new OutsourcingContract()
            .contractid(UPDATED_CONTRACTID)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .contractqualityid(UPDATED_CONTRACTQUALITYID)
            .contractcostid(UPDATED_CONTRACTCOSTID)
            .contractfinanceid(UPDATED_CONTRACTFINANCEID)
            .projectid(UPDATED_PROJECTID)
            .projectsecretlevel(UPDATED_PROJECTSECRETLEVEL)
            .counterpartyunit(UPDATED_COUNTERPARTYUNIT)
            .negotiationdate(UPDATED_NEGOTIATIONDATE)
            .negotiationlocation(UPDATED_NEGOTIATIONLOCATION)
            .negotiator(UPDATED_NEGOTIATOR)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .contractamount(UPDATED_CONTRACTAMOUNT)
            .approver(UPDATED_APPROVER)
            .approvaldate(UPDATED_APPROVALDATE)
            .contractsecretlevel(UPDATED_CONTRACTSECRETLEVEL);
        return outsourcingContract;
    }

    @BeforeEach
    public void initTest() {
        outsourcingContract = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedOutsourcingContract != null) {
            outsourcingContractRepository.delete(insertedOutsourcingContract);
            insertedOutsourcingContract = null;
        }
    }

    @Test
    @Transactional
    void createOutsourcingContract() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OutsourcingContract
        var returnedOutsourcingContract = om.readValue(
            restOutsourcingContractMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingContract)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OutsourcingContract.class
        );

        // Validate the OutsourcingContract in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOutsourcingContractUpdatableFieldsEquals(
            returnedOutsourcingContract,
            getPersistedOutsourcingContract(returnedOutsourcingContract)
        );

        insertedOutsourcingContract = returnedOutsourcingContract;
    }

    @Test
    @Transactional
    void createOutsourcingContractWithExistingId() throws Exception {
        // Create the OutsourcingContract with an existing ID
        outsourcingContract.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOutsourcingContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingContract)))
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOutsourcingContracts() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        // Get all the outsourcingContractList
        restOutsourcingContractMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(outsourcingContract.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractid").value(hasItem(DEFAULT_CONTRACTID)))
            .andExpect(jsonPath("$.[*].contractcode").value(hasItem(DEFAULT_CONTRACTCODE)))
            .andExpect(jsonPath("$.[*].contractname").value(hasItem(DEFAULT_CONTRACTNAME)))
            .andExpect(jsonPath("$.[*].contractqualityid").value(hasItem(DEFAULT_CONTRACTQUALITYID)))
            .andExpect(jsonPath("$.[*].contractcostid").value(hasItem(DEFAULT_CONTRACTCOSTID)))
            .andExpect(jsonPath("$.[*].contractfinanceid").value(hasItem(DEFAULT_CONTRACTFINANCEID)))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID)))
            .andExpect(jsonPath("$.[*].projectsecretlevel").value(hasItem(DEFAULT_PROJECTSECRETLEVEL)))
            .andExpect(jsonPath("$.[*].counterpartyunit").value(hasItem(DEFAULT_COUNTERPARTYUNIT)))
            .andExpect(jsonPath("$.[*].negotiationdate").value(hasItem(DEFAULT_NEGOTIATIONDATE.toString())))
            .andExpect(jsonPath("$.[*].negotiationlocation").value(hasItem(DEFAULT_NEGOTIATIONLOCATION)))
            .andExpect(jsonPath("$.[*].negotiator").value(hasItem(DEFAULT_NEGOTIATOR)))
            .andExpect(jsonPath("$.[*].budgetamount").value(hasItem(sameNumber(DEFAULT_BUDGETAMOUNT))))
            .andExpect(jsonPath("$.[*].contractamount").value(hasItem(sameNumber(DEFAULT_CONTRACTAMOUNT))))
            .andExpect(jsonPath("$.[*].approver").value(hasItem(DEFAULT_APPROVER)))
            .andExpect(jsonPath("$.[*].approvaldate").value(hasItem(DEFAULT_APPROVALDATE.toString())))
            .andExpect(jsonPath("$.[*].contractsecretlevel").value(hasItem(DEFAULT_CONTRACTSECRETLEVEL)));
    }

    @Test
    @Transactional
    void getOutsourcingContract() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        // Get the outsourcingContract
        restOutsourcingContractMockMvc
            .perform(get(ENTITY_API_URL_ID, outsourcingContract.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(outsourcingContract.getId().intValue()))
            .andExpect(jsonPath("$.contractid").value(DEFAULT_CONTRACTID))
            .andExpect(jsonPath("$.contractcode").value(DEFAULT_CONTRACTCODE))
            .andExpect(jsonPath("$.contractname").value(DEFAULT_CONTRACTNAME))
            .andExpect(jsonPath("$.contractqualityid").value(DEFAULT_CONTRACTQUALITYID))
            .andExpect(jsonPath("$.contractcostid").value(DEFAULT_CONTRACTCOSTID))
            .andExpect(jsonPath("$.contractfinanceid").value(DEFAULT_CONTRACTFINANCEID))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID))
            .andExpect(jsonPath("$.projectsecretlevel").value(DEFAULT_PROJECTSECRETLEVEL))
            .andExpect(jsonPath("$.counterpartyunit").value(DEFAULT_COUNTERPARTYUNIT))
            .andExpect(jsonPath("$.negotiationdate").value(DEFAULT_NEGOTIATIONDATE.toString()))
            .andExpect(jsonPath("$.negotiationlocation").value(DEFAULT_NEGOTIATIONLOCATION))
            .andExpect(jsonPath("$.negotiator").value(DEFAULT_NEGOTIATOR))
            .andExpect(jsonPath("$.budgetamount").value(sameNumber(DEFAULT_BUDGETAMOUNT)))
            .andExpect(jsonPath("$.contractamount").value(sameNumber(DEFAULT_CONTRACTAMOUNT)))
            .andExpect(jsonPath("$.approver").value(DEFAULT_APPROVER))
            .andExpect(jsonPath("$.approvaldate").value(DEFAULT_APPROVALDATE.toString()))
            .andExpect(jsonPath("$.contractsecretlevel").value(DEFAULT_CONTRACTSECRETLEVEL));
    }

    @Test
    @Transactional
    void getNonExistingOutsourcingContract() throws Exception {
        // Get the outsourcingContract
        restOutsourcingContractMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOutsourcingContract() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingContract
        OutsourcingContract updatedOutsourcingContract = outsourcingContractRepository.findById(outsourcingContract.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOutsourcingContract are not directly saved in db
        em.detach(updatedOutsourcingContract);
        updatedOutsourcingContract
            .contractid(UPDATED_CONTRACTID)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .contractqualityid(UPDATED_CONTRACTQUALITYID)
            .contractcostid(UPDATED_CONTRACTCOSTID)
            .contractfinanceid(UPDATED_CONTRACTFINANCEID)
            .projectid(UPDATED_PROJECTID)
            .projectsecretlevel(UPDATED_PROJECTSECRETLEVEL)
            .counterpartyunit(UPDATED_COUNTERPARTYUNIT)
            .negotiationdate(UPDATED_NEGOTIATIONDATE)
            .negotiationlocation(UPDATED_NEGOTIATIONLOCATION)
            .negotiator(UPDATED_NEGOTIATOR)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .contractamount(UPDATED_CONTRACTAMOUNT)
            .approver(UPDATED_APPROVER)
            .approvaldate(UPDATED_APPROVALDATE)
            .contractsecretlevel(UPDATED_CONTRACTSECRETLEVEL);

        restOutsourcingContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOutsourcingContract.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOutsourcingContract))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOutsourcingContractToMatchAllProperties(updatedOutsourcingContract);
    }

    @Test
    @Transactional
    void putNonExistingOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, outsourcingContract.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingContract))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingContract))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingContract)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOutsourcingContractWithPatch() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingContract using partial update
        OutsourcingContract partialUpdatedOutsourcingContract = new OutsourcingContract();
        partialUpdatedOutsourcingContract.setId(outsourcingContract.getId());

        partialUpdatedOutsourcingContract
            .contractid(UPDATED_CONTRACTID)
            .contractname(UPDATED_CONTRACTNAME)
            .contractqualityid(UPDATED_CONTRACTQUALITYID)
            .contractcostid(UPDATED_CONTRACTCOSTID)
            .contractfinanceid(UPDATED_CONTRACTFINANCEID)
            .counterpartyunit(UPDATED_COUNTERPARTYUNIT)
            .negotiationlocation(UPDATED_NEGOTIATIONLOCATION)
            .negotiator(UPDATED_NEGOTIATOR)
            .contractamount(UPDATED_CONTRACTAMOUNT)
            .approver(UPDATED_APPROVER)
            .approvaldate(UPDATED_APPROVALDATE);

        restOutsourcingContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingContract))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingContract in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingContractUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOutsourcingContract, outsourcingContract),
            getPersistedOutsourcingContract(outsourcingContract)
        );
    }

    @Test
    @Transactional
    void fullUpdateOutsourcingContractWithPatch() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingContract using partial update
        OutsourcingContract partialUpdatedOutsourcingContract = new OutsourcingContract();
        partialUpdatedOutsourcingContract.setId(outsourcingContract.getId());

        partialUpdatedOutsourcingContract
            .contractid(UPDATED_CONTRACTID)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .contractqualityid(UPDATED_CONTRACTQUALITYID)
            .contractcostid(UPDATED_CONTRACTCOSTID)
            .contractfinanceid(UPDATED_CONTRACTFINANCEID)
            .projectid(UPDATED_PROJECTID)
            .projectsecretlevel(UPDATED_PROJECTSECRETLEVEL)
            .counterpartyunit(UPDATED_COUNTERPARTYUNIT)
            .negotiationdate(UPDATED_NEGOTIATIONDATE)
            .negotiationlocation(UPDATED_NEGOTIATIONLOCATION)
            .negotiator(UPDATED_NEGOTIATOR)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .contractamount(UPDATED_CONTRACTAMOUNT)
            .approver(UPDATED_APPROVER)
            .approvaldate(UPDATED_APPROVALDATE)
            .contractsecretlevel(UPDATED_CONTRACTSECRETLEVEL);

        restOutsourcingContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingContract))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingContract in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingContractUpdatableFieldsEquals(
            partialUpdatedOutsourcingContract,
            getPersistedOutsourcingContract(partialUpdatedOutsourcingContract)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, outsourcingContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingContract))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingContract))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(outsourcingContract)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOutsourcingContract() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the outsourcingContract
        restOutsourcingContractMockMvc
            .perform(delete(ENTITY_API_URL_ID, outsourcingContract.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return outsourcingContractRepository.count();
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

    protected OutsourcingContract getPersistedOutsourcingContract(OutsourcingContract outsourcingContract) {
        return outsourcingContractRepository.findById(outsourcingContract.getId()).orElseThrow();
    }

    protected void assertPersistedOutsourcingContractToMatchAllProperties(OutsourcingContract expectedOutsourcingContract) {
        assertOutsourcingContractAllPropertiesEquals(
            expectedOutsourcingContract,
            getPersistedOutsourcingContract(expectedOutsourcingContract)
        );
    }

    protected void assertPersistedOutsourcingContractToMatchUpdatableProperties(OutsourcingContract expectedOutsourcingContract) {
        assertOutsourcingContractAllUpdatablePropertiesEquals(
            expectedOutsourcingContract,
            getPersistedOutsourcingContract(expectedOutsourcingContract)
        );
    }
}
