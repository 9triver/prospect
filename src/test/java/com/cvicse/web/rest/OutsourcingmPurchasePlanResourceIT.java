package com.cvicse.web.rest;

import static com.cvicse.domain.OutsourcingmPurchasePlanAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.OutsourcingmPurchasePlan;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.OutsourcingmPurchasePlanRepository;
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
 * Integration tests for the {@link OutsourcingmPurchasePlanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OutsourcingmPurchasePlanResourceIT {

    private static final Long DEFAULT_OUTSOURCINGPLANID = 1L;
    private static final Long UPDATED_OUTSOURCINGPLANID = 2L;

    private static final String DEFAULT_MATARIALNAME = "AAAAAAAAAA";
    private static final String UPDATED_MATARIALNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PURCHASINGMETHOD = 1;
    private static final Integer UPDATED_PURCHASINGMETHOD = 2;

    private static final BigDecimal DEFAULT_BUDGIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUDGIT = new BigDecimal(2);

    private static final LocalDate DEFAULT_NEEDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NEEDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PLANUSETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PLANUSETIME = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_SUPPLIERID = 1L;
    private static final Long UPDATED_SUPPLIERID = 2L;

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/outsourcingm-purchase-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OutsourcingmPurchasePlanRepository outsourcingmPurchasePlanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOutsourcingmPurchasePlanMockMvc;

    private OutsourcingmPurchasePlan outsourcingmPurchasePlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingmPurchasePlan createEntity(EntityManager em) {
        OutsourcingmPurchasePlan outsourcingmPurchasePlan = new OutsourcingmPurchasePlan()
            .outsourcingplanid(DEFAULT_OUTSOURCINGPLANID)
            .matarialname(DEFAULT_MATARIALNAME)
            .purchasingmethod(DEFAULT_PURCHASINGMETHOD)
            .budgit(DEFAULT_BUDGIT)
            .needtime(DEFAULT_NEEDTIME)
            .planusetime(DEFAULT_PLANUSETIME)
            .supplierid(DEFAULT_SUPPLIERID)
            .price(DEFAULT_PRICE)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return outsourcingmPurchasePlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingmPurchasePlan createUpdatedEntity(EntityManager em) {
        OutsourcingmPurchasePlan outsourcingmPurchasePlan = new OutsourcingmPurchasePlan()
            .outsourcingplanid(UPDATED_OUTSOURCINGPLANID)
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return outsourcingmPurchasePlan;
    }

    @BeforeEach
    public void initTest() {
        outsourcingmPurchasePlan = createEntity(em);
    }

    @Test
    @Transactional
    void createOutsourcingmPurchasePlan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OutsourcingmPurchasePlan
        var returnedOutsourcingmPurchasePlan = om.readValue(
            restOutsourcingmPurchasePlanMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmPurchasePlan))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OutsourcingmPurchasePlan.class
        );

        // Validate the OutsourcingmPurchasePlan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOutsourcingmPurchasePlanUpdatableFieldsEquals(
            returnedOutsourcingmPurchasePlan,
            getPersistedOutsourcingmPurchasePlan(returnedOutsourcingmPurchasePlan)
        );
    }

    @Test
    @Transactional
    void createOutsourcingmPurchasePlanWithExistingId() throws Exception {
        // Create the OutsourcingmPurchasePlan with an existing ID
        outsourcingmPurchasePlan.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOutsourcingmPurchasePlanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmPurchasePlan)))
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOutsourcingmPurchasePlans() throws Exception {
        // Initialize the database
        outsourcingmPurchasePlanRepository.saveAndFlush(outsourcingmPurchasePlan);

        // Get all the outsourcingmPurchasePlanList
        restOutsourcingmPurchasePlanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(outsourcingmPurchasePlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].outsourcingplanid").value(hasItem(DEFAULT_OUTSOURCINGPLANID.intValue())))
            .andExpect(jsonPath("$.[*].matarialname").value(hasItem(DEFAULT_MATARIALNAME)))
            .andExpect(jsonPath("$.[*].purchasingmethod").value(hasItem(DEFAULT_PURCHASINGMETHOD)))
            .andExpect(jsonPath("$.[*].budgit").value(hasItem(sameNumber(DEFAULT_BUDGIT))))
            .andExpect(jsonPath("$.[*].needtime").value(hasItem(DEFAULT_NEEDTIME.toString())))
            .andExpect(jsonPath("$.[*].planusetime").value(hasItem(DEFAULT_PLANUSETIME.toString())))
            .andExpect(jsonPath("$.[*].supplierid").value(hasItem(DEFAULT_SUPPLIERID.intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(sameNumber(DEFAULT_PRICE))))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getOutsourcingmPurchasePlan() throws Exception {
        // Initialize the database
        outsourcingmPurchasePlanRepository.saveAndFlush(outsourcingmPurchasePlan);

        // Get the outsourcingmPurchasePlan
        restOutsourcingmPurchasePlanMockMvc
            .perform(get(ENTITY_API_URL_ID, outsourcingmPurchasePlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(outsourcingmPurchasePlan.getId().intValue()))
            .andExpect(jsonPath("$.outsourcingplanid").value(DEFAULT_OUTSOURCINGPLANID.intValue()))
            .andExpect(jsonPath("$.matarialname").value(DEFAULT_MATARIALNAME))
            .andExpect(jsonPath("$.purchasingmethod").value(DEFAULT_PURCHASINGMETHOD))
            .andExpect(jsonPath("$.budgit").value(sameNumber(DEFAULT_BUDGIT)))
            .andExpect(jsonPath("$.needtime").value(DEFAULT_NEEDTIME.toString()))
            .andExpect(jsonPath("$.planusetime").value(DEFAULT_PLANUSETIME.toString()))
            .andExpect(jsonPath("$.supplierid").value(DEFAULT_SUPPLIERID.intValue()))
            .andExpect(jsonPath("$.price").value(sameNumber(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingOutsourcingmPurchasePlan() throws Exception {
        // Get the outsourcingmPurchasePlan
        restOutsourcingmPurchasePlanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOutsourcingmPurchasePlan() throws Exception {
        // Initialize the database
        outsourcingmPurchasePlanRepository.saveAndFlush(outsourcingmPurchasePlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmPurchasePlan
        OutsourcingmPurchasePlan updatedOutsourcingmPurchasePlan = outsourcingmPurchasePlanRepository
            .findById(outsourcingmPurchasePlan.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOutsourcingmPurchasePlan are not directly saved in db
        em.detach(updatedOutsourcingmPurchasePlan);
        updatedOutsourcingmPurchasePlan
            .outsourcingplanid(UPDATED_OUTSOURCINGPLANID)
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restOutsourcingmPurchasePlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOutsourcingmPurchasePlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOutsourcingmPurchasePlan))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingmPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOutsourcingmPurchasePlanToMatchAllProperties(updatedOutsourcingmPurchasePlan);
    }

    @Test
    @Transactional
    void putNonExistingOutsourcingmPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchasePlan.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingmPurchasePlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, outsourcingmPurchasePlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingmPurchasePlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOutsourcingmPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchasePlan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmPurchasePlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingmPurchasePlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOutsourcingmPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchasePlan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmPurchasePlanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmPurchasePlan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingmPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOutsourcingmPurchasePlanWithPatch() throws Exception {
        // Initialize the database
        outsourcingmPurchasePlanRepository.saveAndFlush(outsourcingmPurchasePlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmPurchasePlan using partial update
        OutsourcingmPurchasePlan partialUpdatedOutsourcingmPurchasePlan = new OutsourcingmPurchasePlan();
        partialUpdatedOutsourcingmPurchasePlan.setId(outsourcingmPurchasePlan.getId());

        partialUpdatedOutsourcingmPurchasePlan.budgit(UPDATED_BUDGIT).needtime(UPDATED_NEEDTIME).supplierid(UPDATED_SUPPLIERID);

        restOutsourcingmPurchasePlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingmPurchasePlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingmPurchasePlan))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingmPurchasePlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingmPurchasePlanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOutsourcingmPurchasePlan, outsourcingmPurchasePlan),
            getPersistedOutsourcingmPurchasePlan(outsourcingmPurchasePlan)
        );
    }

    @Test
    @Transactional
    void fullUpdateOutsourcingmPurchasePlanWithPatch() throws Exception {
        // Initialize the database
        outsourcingmPurchasePlanRepository.saveAndFlush(outsourcingmPurchasePlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmPurchasePlan using partial update
        OutsourcingmPurchasePlan partialUpdatedOutsourcingmPurchasePlan = new OutsourcingmPurchasePlan();
        partialUpdatedOutsourcingmPurchasePlan.setId(outsourcingmPurchasePlan.getId());

        partialUpdatedOutsourcingmPurchasePlan
            .outsourcingplanid(UPDATED_OUTSOURCINGPLANID)
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restOutsourcingmPurchasePlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingmPurchasePlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingmPurchasePlan))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingmPurchasePlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingmPurchasePlanUpdatableFieldsEquals(
            partialUpdatedOutsourcingmPurchasePlan,
            getPersistedOutsourcingmPurchasePlan(partialUpdatedOutsourcingmPurchasePlan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOutsourcingmPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchasePlan.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingmPurchasePlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, outsourcingmPurchasePlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingmPurchasePlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOutsourcingmPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchasePlan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmPurchasePlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingmPurchasePlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOutsourcingmPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchasePlan.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmPurchasePlanMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(outsourcingmPurchasePlan))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingmPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOutsourcingmPurchasePlan() throws Exception {
        // Initialize the database
        outsourcingmPurchasePlanRepository.saveAndFlush(outsourcingmPurchasePlan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the outsourcingmPurchasePlan
        restOutsourcingmPurchasePlanMockMvc
            .perform(delete(ENTITY_API_URL_ID, outsourcingmPurchasePlan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return outsourcingmPurchasePlanRepository.count();
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

    protected OutsourcingmPurchasePlan getPersistedOutsourcingmPurchasePlan(OutsourcingmPurchasePlan outsourcingmPurchasePlan) {
        return outsourcingmPurchasePlanRepository.findById(outsourcingmPurchasePlan.getId()).orElseThrow();
    }

    protected void assertPersistedOutsourcingmPurchasePlanToMatchAllProperties(OutsourcingmPurchasePlan expectedOutsourcingmPurchasePlan) {
        assertOutsourcingmPurchasePlanAllPropertiesEquals(
            expectedOutsourcingmPurchasePlan,
            getPersistedOutsourcingmPurchasePlan(expectedOutsourcingmPurchasePlan)
        );
    }

    protected void assertPersistedOutsourcingmPurchasePlanToMatchUpdatableProperties(
        OutsourcingmPurchasePlan expectedOutsourcingmPurchasePlan
    ) {
        assertOutsourcingmPurchasePlanAllUpdatablePropertiesEquals(
            expectedOutsourcingmPurchasePlan,
            getPersistedOutsourcingmPurchasePlan(expectedOutsourcingmPurchasePlan)
        );
    }
}
