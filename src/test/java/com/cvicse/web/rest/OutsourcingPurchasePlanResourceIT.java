package com.cvicse.web.rest;

import static com.cvicse.domain.OutsourcingPurchasePlanAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.OutsourcingPurchasePlan;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.OutsourcingPurchasePlanRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link OutsourcingPurchasePlanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OutsourcingPurchasePlanResourceIT {

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

    private static final String ENTITY_API_URL = "/api/outsourcing-purchase-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OutsourcingPurchasePlanRepository outsourcingPurchasePlanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOutsourcingPurchasePlanMockMvc;

    private OutsourcingPurchasePlan outsourcingPurchasePlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingPurchasePlan createEntity(EntityManager em) {
        OutsourcingPurchasePlan outsourcingPurchasePlan = new OutsourcingPurchasePlan()
            .matarialname(DEFAULT_MATARIALNAME)
            .purchasingmethod(DEFAULT_PURCHASINGMETHOD)
            .budgit(DEFAULT_BUDGIT)
            .needtime(DEFAULT_NEEDTIME)
            .planusetime(DEFAULT_PLANUSETIME)
            .supplierid(DEFAULT_SUPPLIERID)
            .price(DEFAULT_PRICE)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return outsourcingPurchasePlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingPurchasePlan createUpdatedEntity(EntityManager em) {
        OutsourcingPurchasePlan outsourcingPurchasePlan = new OutsourcingPurchasePlan()
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return outsourcingPurchasePlan;
    }

    @BeforeEach
    public void initTest() {
        outsourcingPurchasePlan = createEntity(em);
    }

    @Test
    @Transactional
    void createOutsourcingPurchasePlan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OutsourcingPurchasePlan
        var returnedOutsourcingPurchasePlan = om.readValue(
            restOutsourcingPurchasePlanMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingPurchasePlan))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OutsourcingPurchasePlan.class
        );

        // Validate the OutsourcingPurchasePlan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOutsourcingPurchasePlanUpdatableFieldsEquals(
            returnedOutsourcingPurchasePlan,
            getPersistedOutsourcingPurchasePlan(returnedOutsourcingPurchasePlan)
        );
    }

    @Test
    @Transactional
    void createOutsourcingPurchasePlanWithExistingId() throws Exception {
        // Create the OutsourcingPurchasePlan with an existing ID
        outsourcingPurchasePlan.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOutsourcingPurchasePlanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingPurchasePlan)))
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOutsourcingPurchasePlans() throws Exception {
        // Initialize the database
        outsourcingPurchasePlanRepository.saveAndFlush(outsourcingPurchasePlan);

        // Get all the outsourcingPurchasePlanList
        restOutsourcingPurchasePlanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(outsourcingPurchasePlan.getId())))
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
    void getOutsourcingPurchasePlan() throws Exception {
        // Initialize the database
        outsourcingPurchasePlanRepository.saveAndFlush(outsourcingPurchasePlan);

        // Get the outsourcingPurchasePlan
        restOutsourcingPurchasePlanMockMvc
            .perform(get(ENTITY_API_URL_ID, outsourcingPurchasePlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(outsourcingPurchasePlan.getId()))
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
    void getNonExistingOutsourcingPurchasePlan() throws Exception {
        // Get the outsourcingPurchasePlan
        restOutsourcingPurchasePlanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOutsourcingPurchasePlan() throws Exception {
        // Initialize the database
        outsourcingPurchasePlanRepository.saveAndFlush(outsourcingPurchasePlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingPurchasePlan
        OutsourcingPurchasePlan updatedOutsourcingPurchasePlan = outsourcingPurchasePlanRepository
            .findById(outsourcingPurchasePlan.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOutsourcingPurchasePlan are not directly saved in db
        em.detach(updatedOutsourcingPurchasePlan);
        updatedOutsourcingPurchasePlan
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restOutsourcingPurchasePlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOutsourcingPurchasePlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOutsourcingPurchasePlan))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOutsourcingPurchasePlanToMatchAllProperties(updatedOutsourcingPurchasePlan);
    }

    @Test
    @Transactional
    void putNonExistingOutsourcingPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchasePlan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingPurchasePlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, outsourcingPurchasePlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingPurchasePlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOutsourcingPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchasePlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingPurchasePlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingPurchasePlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOutsourcingPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchasePlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingPurchasePlanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingPurchasePlan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOutsourcingPurchasePlanWithPatch() throws Exception {
        // Initialize the database
        outsourcingPurchasePlanRepository.saveAndFlush(outsourcingPurchasePlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingPurchasePlan using partial update
        OutsourcingPurchasePlan partialUpdatedOutsourcingPurchasePlan = new OutsourcingPurchasePlan();
        partialUpdatedOutsourcingPurchasePlan.setId(outsourcingPurchasePlan.getId());

        partialUpdatedOutsourcingPurchasePlan.purchasingmethod(UPDATED_PURCHASINGMETHOD).secretlevel(UPDATED_SECRETLEVEL);

        restOutsourcingPurchasePlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingPurchasePlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingPurchasePlan))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingPurchasePlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingPurchasePlanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOutsourcingPurchasePlan, outsourcingPurchasePlan),
            getPersistedOutsourcingPurchasePlan(outsourcingPurchasePlan)
        );
    }

    @Test
    @Transactional
    void fullUpdateOutsourcingPurchasePlanWithPatch() throws Exception {
        // Initialize the database
        outsourcingPurchasePlanRepository.saveAndFlush(outsourcingPurchasePlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingPurchasePlan using partial update
        OutsourcingPurchasePlan partialUpdatedOutsourcingPurchasePlan = new OutsourcingPurchasePlan();
        partialUpdatedOutsourcingPurchasePlan.setId(outsourcingPurchasePlan.getId());

        partialUpdatedOutsourcingPurchasePlan
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restOutsourcingPurchasePlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingPurchasePlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingPurchasePlan))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingPurchasePlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingPurchasePlanUpdatableFieldsEquals(
            partialUpdatedOutsourcingPurchasePlan,
            getPersistedOutsourcingPurchasePlan(partialUpdatedOutsourcingPurchasePlan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOutsourcingPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchasePlan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingPurchasePlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, outsourcingPurchasePlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingPurchasePlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOutsourcingPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchasePlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingPurchasePlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingPurchasePlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOutsourcingPurchasePlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchasePlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingPurchasePlanMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(outsourcingPurchasePlan))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingPurchasePlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOutsourcingPurchasePlan() throws Exception {
        // Initialize the database
        outsourcingPurchasePlanRepository.saveAndFlush(outsourcingPurchasePlan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the outsourcingPurchasePlan
        restOutsourcingPurchasePlanMockMvc
            .perform(delete(ENTITY_API_URL_ID, outsourcingPurchasePlan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return outsourcingPurchasePlanRepository.count();
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

    protected OutsourcingPurchasePlan getPersistedOutsourcingPurchasePlan(OutsourcingPurchasePlan outsourcingPurchasePlan) {
        return outsourcingPurchasePlanRepository.findById(outsourcingPurchasePlan.getId()).orElseThrow();
    }

    protected void assertPersistedOutsourcingPurchasePlanToMatchAllProperties(OutsourcingPurchasePlan expectedOutsourcingPurchasePlan) {
        assertOutsourcingPurchasePlanAllPropertiesEquals(
            expectedOutsourcingPurchasePlan,
            getPersistedOutsourcingPurchasePlan(expectedOutsourcingPurchasePlan)
        );
    }

    protected void assertPersistedOutsourcingPurchasePlanToMatchUpdatableProperties(
        OutsourcingPurchasePlan expectedOutsourcingPurchasePlan
    ) {
        assertOutsourcingPurchasePlanAllUpdatablePropertiesEquals(
            expectedOutsourcingPurchasePlan,
            getPersistedOutsourcingPurchasePlan(expectedOutsourcingPurchasePlan)
        );
    }
}
