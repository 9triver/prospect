package com.cvicse.web.rest;

import static com.cvicse.domain.ContractualfundsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Contractualfunds;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.ContractualfundsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link ContractualfundsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractualfundsResourceIT {

    private static final Long DEFAULT_CONTRACTUALID = 1L;
    private static final Long UPDATED_CONTRACTUALID = 2L;

    private static final Long DEFAULT_DEPARTMENT = 1L;
    private static final Long UPDATED_DEPARTMENT = 2L;

    private static final Long DEFAULT_YEAR = 1L;
    private static final Long UPDATED_YEAR = 2L;

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final BigDecimal DEFAULT_FOREIGNCURRENCY = new BigDecimal(1);
    private static final BigDecimal UPDATED_FOREIGNCURRENCY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTALBUDGET = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTALBUDGET = new BigDecimal(2);

    private static final BigDecimal DEFAULT_FUNDSINPLACE = new BigDecimal(1);
    private static final BigDecimal UPDATED_FUNDSINPLACE = new BigDecimal(2);

    private static final String DEFAULT_RESPONSIBLEUNITNAME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSIBLEUNITNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AUDITTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AUDITTIME = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ACCOUNTBANK = 1L;
    private static final Long UPDATED_ACCOUNTBANK = 2L;

    private static final String ENTITY_API_URL = "/api/contractualfunds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ContractualfundsRepository contractualfundsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractualfundsMockMvc;

    private Contractualfunds contractualfunds;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contractualfunds createEntity(EntityManager em) {
        Contractualfunds contractualfunds = new Contractualfunds()
            .contractualid(DEFAULT_CONTRACTUALID)
            .department(DEFAULT_DEPARTMENT)
            .year(DEFAULT_YEAR)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .status(DEFAULT_STATUS)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .foreigncurrency(DEFAULT_FOREIGNCURRENCY)
            .totalbudget(DEFAULT_TOTALBUDGET)
            .fundsinplace(DEFAULT_FUNDSINPLACE)
            .responsibleunitname(DEFAULT_RESPONSIBLEUNITNAME)
            .audittime(DEFAULT_AUDITTIME)
            .accountbank(DEFAULT_ACCOUNTBANK);
        return contractualfunds;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contractualfunds createUpdatedEntity(EntityManager em) {
        Contractualfunds contractualfunds = new Contractualfunds()
            .contractualid(UPDATED_CONTRACTUALID)
            .department(UPDATED_DEPARTMENT)
            .year(UPDATED_YEAR)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .status(UPDATED_STATUS)
            .secretlevel(UPDATED_SECRETLEVEL)
            .foreigncurrency(UPDATED_FOREIGNCURRENCY)
            .totalbudget(UPDATED_TOTALBUDGET)
            .fundsinplace(UPDATED_FUNDSINPLACE)
            .responsibleunitname(UPDATED_RESPONSIBLEUNITNAME)
            .audittime(UPDATED_AUDITTIME)
            .accountbank(UPDATED_ACCOUNTBANK);
        return contractualfunds;
    }

    @BeforeEach
    public void initTest() {
        contractualfunds = createEntity(em);
    }

    @Test
    @Transactional
    void createContractualfunds() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Contractualfunds
        var returnedContractualfunds = om.readValue(
            restContractualfundsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contractualfunds)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Contractualfunds.class
        );

        // Validate the Contractualfunds in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertContractualfundsUpdatableFieldsEquals(returnedContractualfunds, getPersistedContractualfunds(returnedContractualfunds));
    }

    @Test
    @Transactional
    void createContractualfundsWithExistingId() throws Exception {
        // Create the Contractualfunds with an existing ID
        contractualfunds.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractualfundsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contractualfunds)))
            .andExpect(status().isBadRequest());

        // Validate the Contractualfunds in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllContractualfunds() throws Exception {
        // Initialize the database
        contractualfundsRepository.saveAndFlush(contractualfunds);

        // Get all the contractualfundsList
        restContractualfundsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractualfunds.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractualid").value(hasItem(DEFAULT_CONTRACTUALID.intValue())))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT.intValue())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].foreigncurrency").value(hasItem(sameNumber(DEFAULT_FOREIGNCURRENCY))))
            .andExpect(jsonPath("$.[*].totalbudget").value(hasItem(sameNumber(DEFAULT_TOTALBUDGET))))
            .andExpect(jsonPath("$.[*].fundsinplace").value(hasItem(sameNumber(DEFAULT_FUNDSINPLACE))))
            .andExpect(jsonPath("$.[*].responsibleunitname").value(hasItem(DEFAULT_RESPONSIBLEUNITNAME)))
            .andExpect(jsonPath("$.[*].audittime").value(hasItem(DEFAULT_AUDITTIME.toString())))
            .andExpect(jsonPath("$.[*].accountbank").value(hasItem(DEFAULT_ACCOUNTBANK.intValue())));
    }

    @Test
    @Transactional
    void getContractualfunds() throws Exception {
        // Initialize the database
        contractualfundsRepository.saveAndFlush(contractualfunds);

        // Get the contractualfunds
        restContractualfundsMockMvc
            .perform(get(ENTITY_API_URL_ID, contractualfunds.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contractualfunds.getId().intValue()))
            .andExpect(jsonPath("$.contractualid").value(DEFAULT_CONTRACTUALID.intValue()))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT.intValue()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.foreigncurrency").value(sameNumber(DEFAULT_FOREIGNCURRENCY)))
            .andExpect(jsonPath("$.totalbudget").value(sameNumber(DEFAULT_TOTALBUDGET)))
            .andExpect(jsonPath("$.fundsinplace").value(sameNumber(DEFAULT_FUNDSINPLACE)))
            .andExpect(jsonPath("$.responsibleunitname").value(DEFAULT_RESPONSIBLEUNITNAME))
            .andExpect(jsonPath("$.audittime").value(DEFAULT_AUDITTIME.toString()))
            .andExpect(jsonPath("$.accountbank").value(DEFAULT_ACCOUNTBANK.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingContractualfunds() throws Exception {
        // Get the contractualfunds
        restContractualfundsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingContractualfunds() throws Exception {
        // Initialize the database
        contractualfundsRepository.saveAndFlush(contractualfunds);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contractualfunds
        Contractualfunds updatedContractualfunds = contractualfundsRepository.findById(contractualfunds.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedContractualfunds are not directly saved in db
        em.detach(updatedContractualfunds);
        updatedContractualfunds
            .contractualid(UPDATED_CONTRACTUALID)
            .department(UPDATED_DEPARTMENT)
            .year(UPDATED_YEAR)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .status(UPDATED_STATUS)
            .secretlevel(UPDATED_SECRETLEVEL)
            .foreigncurrency(UPDATED_FOREIGNCURRENCY)
            .totalbudget(UPDATED_TOTALBUDGET)
            .fundsinplace(UPDATED_FUNDSINPLACE)
            .responsibleunitname(UPDATED_RESPONSIBLEUNITNAME)
            .audittime(UPDATED_AUDITTIME)
            .accountbank(UPDATED_ACCOUNTBANK);

        restContractualfundsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedContractualfunds.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedContractualfunds))
            )
            .andExpect(status().isOk());

        // Validate the Contractualfunds in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedContractualfundsToMatchAllProperties(updatedContractualfunds);
    }

    @Test
    @Transactional
    void putNonExistingContractualfunds() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractualfunds.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractualfundsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractualfunds.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(contractualfunds))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contractualfunds in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContractualfunds() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractualfunds.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractualfundsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(contractualfunds))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contractualfunds in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContractualfunds() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractualfunds.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractualfundsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contractualfunds)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Contractualfunds in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractualfundsWithPatch() throws Exception {
        // Initialize the database
        contractualfundsRepository.saveAndFlush(contractualfunds);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contractualfunds using partial update
        Contractualfunds partialUpdatedContractualfunds = new Contractualfunds();
        partialUpdatedContractualfunds.setId(contractualfunds.getId());

        partialUpdatedContractualfunds
            .year(UPDATED_YEAR)
            .starttime(UPDATED_STARTTIME)
            .status(UPDATED_STATUS)
            .totalbudget(UPDATED_TOTALBUDGET)
            .fundsinplace(UPDATED_FUNDSINPLACE)
            .responsibleunitname(UPDATED_RESPONSIBLEUNITNAME);

        restContractualfundsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractualfunds.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedContractualfunds))
            )
            .andExpect(status().isOk());

        // Validate the Contractualfunds in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertContractualfundsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedContractualfunds, contractualfunds),
            getPersistedContractualfunds(contractualfunds)
        );
    }

    @Test
    @Transactional
    void fullUpdateContractualfundsWithPatch() throws Exception {
        // Initialize the database
        contractualfundsRepository.saveAndFlush(contractualfunds);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contractualfunds using partial update
        Contractualfunds partialUpdatedContractualfunds = new Contractualfunds();
        partialUpdatedContractualfunds.setId(contractualfunds.getId());

        partialUpdatedContractualfunds
            .contractualid(UPDATED_CONTRACTUALID)
            .department(UPDATED_DEPARTMENT)
            .year(UPDATED_YEAR)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .status(UPDATED_STATUS)
            .secretlevel(UPDATED_SECRETLEVEL)
            .foreigncurrency(UPDATED_FOREIGNCURRENCY)
            .totalbudget(UPDATED_TOTALBUDGET)
            .fundsinplace(UPDATED_FUNDSINPLACE)
            .responsibleunitname(UPDATED_RESPONSIBLEUNITNAME)
            .audittime(UPDATED_AUDITTIME)
            .accountbank(UPDATED_ACCOUNTBANK);

        restContractualfundsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractualfunds.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedContractualfunds))
            )
            .andExpect(status().isOk());

        // Validate the Contractualfunds in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertContractualfundsUpdatableFieldsEquals(
            partialUpdatedContractualfunds,
            getPersistedContractualfunds(partialUpdatedContractualfunds)
        );
    }

    @Test
    @Transactional
    void patchNonExistingContractualfunds() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractualfunds.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractualfundsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractualfunds.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(contractualfunds))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contractualfunds in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContractualfunds() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractualfunds.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractualfundsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(contractualfunds))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contractualfunds in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContractualfunds() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractualfunds.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractualfundsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(contractualfunds)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Contractualfunds in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContractualfunds() throws Exception {
        // Initialize the database
        contractualfundsRepository.saveAndFlush(contractualfunds);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the contractualfunds
        restContractualfundsMockMvc
            .perform(delete(ENTITY_API_URL_ID, contractualfunds.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return contractualfundsRepository.count();
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

    protected Contractualfunds getPersistedContractualfunds(Contractualfunds contractualfunds) {
        return contractualfundsRepository.findById(contractualfunds.getId()).orElseThrow();
    }

    protected void assertPersistedContractualfundsToMatchAllProperties(Contractualfunds expectedContractualfunds) {
        assertContractualfundsAllPropertiesEquals(expectedContractualfunds, getPersistedContractualfunds(expectedContractualfunds));
    }

    protected void assertPersistedContractualfundsToMatchUpdatableProperties(Contractualfunds expectedContractualfunds) {
        assertContractualfundsAllUpdatablePropertiesEquals(
            expectedContractualfunds,
            getPersistedContractualfunds(expectedContractualfunds)
        );
    }
}
