package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.OutsourcingPurchaseExecuteAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.OutsourcingPurchaseExecute;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.OutsourcingPurchaseExecuteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
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
 * Integration tests for the {@link OutsourcingPurchaseExecuteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OutsourcingPurchaseExecuteResourceIT {

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

    private static final String ENTITY_API_URL = "/api/outsourcing-purchase-executes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OutsourcingPurchaseExecuteRepository outsourcingPurchaseExecuteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOutsourcingPurchaseExecuteMockMvc;

    private OutsourcingPurchaseExecute outsourcingPurchaseExecute;

    private OutsourcingPurchaseExecute insertedOutsourcingPurchaseExecute;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingPurchaseExecute createEntity(EntityManager em) {
        OutsourcingPurchaseExecute outsourcingPurchaseExecute = new OutsourcingPurchaseExecute()
            .matarialname(DEFAULT_MATARIALNAME)
            .purchasingmethod(DEFAULT_PURCHASINGMETHOD)
            .budgit(DEFAULT_BUDGIT)
            .needtime(DEFAULT_NEEDTIME)
            .planusetime(DEFAULT_PLANUSETIME)
            .supplierid(DEFAULT_SUPPLIERID)
            .price(DEFAULT_PRICE)
            .secretlevel(DEFAULT_SECRETLEVEL);
        return outsourcingPurchaseExecute;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingPurchaseExecute createUpdatedEntity(EntityManager em) {
        OutsourcingPurchaseExecute outsourcingPurchaseExecute = new OutsourcingPurchaseExecute()
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL);
        return outsourcingPurchaseExecute;
    }

    @BeforeEach
    public void initTest() {
        outsourcingPurchaseExecute = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedOutsourcingPurchaseExecute != null) {
            outsourcingPurchaseExecuteRepository.delete(insertedOutsourcingPurchaseExecute);
            insertedOutsourcingPurchaseExecute = null;
        }
    }

    @Test
    @Transactional
    void createOutsourcingPurchaseExecute() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OutsourcingPurchaseExecute
        var returnedOutsourcingPurchaseExecute = om.readValue(
            restOutsourcingPurchaseExecuteMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingPurchaseExecute))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OutsourcingPurchaseExecute.class
        );

        // Validate the OutsourcingPurchaseExecute in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOutsourcingPurchaseExecuteUpdatableFieldsEquals(
            returnedOutsourcingPurchaseExecute,
            getPersistedOutsourcingPurchaseExecute(returnedOutsourcingPurchaseExecute)
        );

        insertedOutsourcingPurchaseExecute = returnedOutsourcingPurchaseExecute;
    }

    @Test
    @Transactional
    void createOutsourcingPurchaseExecuteWithExistingId() throws Exception {
        // Create the OutsourcingPurchaseExecute with an existing ID
        outsourcingPurchaseExecute.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOutsourcingPurchaseExecuteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingPurchaseExecute)))
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOutsourcingPurchaseExecutes() throws Exception {
        // Initialize the database
        insertedOutsourcingPurchaseExecute = outsourcingPurchaseExecuteRepository.saveAndFlush(outsourcingPurchaseExecute);

        // Get all the outsourcingPurchaseExecuteList
        restOutsourcingPurchaseExecuteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(outsourcingPurchaseExecute.getId())))
            .andExpect(jsonPath("$.[*].matarialname").value(hasItem(DEFAULT_MATARIALNAME)))
            .andExpect(jsonPath("$.[*].purchasingmethod").value(hasItem(DEFAULT_PURCHASINGMETHOD)))
            .andExpect(jsonPath("$.[*].budgit").value(hasItem(sameNumber(DEFAULT_BUDGIT))))
            .andExpect(jsonPath("$.[*].needtime").value(hasItem(DEFAULT_NEEDTIME.toString())))
            .andExpect(jsonPath("$.[*].planusetime").value(hasItem(DEFAULT_PLANUSETIME.toString())))
            .andExpect(jsonPath("$.[*].supplierid").value(hasItem(DEFAULT_SUPPLIERID.intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(sameNumber(DEFAULT_PRICE))))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())));
    }

    @Test
    @Transactional
    void getOutsourcingPurchaseExecute() throws Exception {
        // Initialize the database
        insertedOutsourcingPurchaseExecute = outsourcingPurchaseExecuteRepository.saveAndFlush(outsourcingPurchaseExecute);

        // Get the outsourcingPurchaseExecute
        restOutsourcingPurchaseExecuteMockMvc
            .perform(get(ENTITY_API_URL_ID, outsourcingPurchaseExecute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(outsourcingPurchaseExecute.getId()))
            .andExpect(jsonPath("$.matarialname").value(DEFAULT_MATARIALNAME))
            .andExpect(jsonPath("$.purchasingmethod").value(DEFAULT_PURCHASINGMETHOD))
            .andExpect(jsonPath("$.budgit").value(sameNumber(DEFAULT_BUDGIT)))
            .andExpect(jsonPath("$.needtime").value(DEFAULT_NEEDTIME.toString()))
            .andExpect(jsonPath("$.planusetime").value(DEFAULT_PLANUSETIME.toString()))
            .andExpect(jsonPath("$.supplierid").value(DEFAULT_SUPPLIERID.intValue()))
            .andExpect(jsonPath("$.price").value(sameNumber(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()));
    }

    @Test
    @Transactional
    void getNonExistingOutsourcingPurchaseExecute() throws Exception {
        // Get the outsourcingPurchaseExecute
        restOutsourcingPurchaseExecuteMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOutsourcingPurchaseExecute() throws Exception {
        // Initialize the database
        insertedOutsourcingPurchaseExecute = outsourcingPurchaseExecuteRepository.saveAndFlush(outsourcingPurchaseExecute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingPurchaseExecute
        OutsourcingPurchaseExecute updatedOutsourcingPurchaseExecute = outsourcingPurchaseExecuteRepository
            .findById(outsourcingPurchaseExecute.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOutsourcingPurchaseExecute are not directly saved in db
        em.detach(updatedOutsourcingPurchaseExecute);
        updatedOutsourcingPurchaseExecute
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL);

        restOutsourcingPurchaseExecuteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOutsourcingPurchaseExecute.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOutsourcingPurchaseExecute))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOutsourcingPurchaseExecuteToMatchAllProperties(updatedOutsourcingPurchaseExecute);
    }

    @Test
    @Transactional
    void putNonExistingOutsourcingPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchaseExecute.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingPurchaseExecuteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, outsourcingPurchaseExecute.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingPurchaseExecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOutsourcingPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchaseExecute.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingPurchaseExecuteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingPurchaseExecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOutsourcingPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchaseExecute.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingPurchaseExecuteMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingPurchaseExecute)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOutsourcingPurchaseExecuteWithPatch() throws Exception {
        // Initialize the database
        insertedOutsourcingPurchaseExecute = outsourcingPurchaseExecuteRepository.saveAndFlush(outsourcingPurchaseExecute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingPurchaseExecute using partial update
        OutsourcingPurchaseExecute partialUpdatedOutsourcingPurchaseExecute = new OutsourcingPurchaseExecute();
        partialUpdatedOutsourcingPurchaseExecute.setId(outsourcingPurchaseExecute.getId());

        partialUpdatedOutsourcingPurchaseExecute
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .needtime(UPDATED_NEEDTIME)
            .supplierid(UPDATED_SUPPLIERID)
            .secretlevel(UPDATED_SECRETLEVEL);

        restOutsourcingPurchaseExecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingPurchaseExecute.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingPurchaseExecute))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingPurchaseExecute in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingPurchaseExecuteUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOutsourcingPurchaseExecute, outsourcingPurchaseExecute),
            getPersistedOutsourcingPurchaseExecute(outsourcingPurchaseExecute)
        );
    }

    @Test
    @Transactional
    void fullUpdateOutsourcingPurchaseExecuteWithPatch() throws Exception {
        // Initialize the database
        insertedOutsourcingPurchaseExecute = outsourcingPurchaseExecuteRepository.saveAndFlush(outsourcingPurchaseExecute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingPurchaseExecute using partial update
        OutsourcingPurchaseExecute partialUpdatedOutsourcingPurchaseExecute = new OutsourcingPurchaseExecute();
        partialUpdatedOutsourcingPurchaseExecute.setId(outsourcingPurchaseExecute.getId());

        partialUpdatedOutsourcingPurchaseExecute
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL);

        restOutsourcingPurchaseExecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingPurchaseExecute.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingPurchaseExecute))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingPurchaseExecute in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingPurchaseExecuteUpdatableFieldsEquals(
            partialUpdatedOutsourcingPurchaseExecute,
            getPersistedOutsourcingPurchaseExecute(partialUpdatedOutsourcingPurchaseExecute)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOutsourcingPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchaseExecute.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingPurchaseExecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, outsourcingPurchaseExecute.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingPurchaseExecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOutsourcingPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchaseExecute.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingPurchaseExecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingPurchaseExecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOutsourcingPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingPurchaseExecute.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingPurchaseExecuteMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(outsourcingPurchaseExecute))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOutsourcingPurchaseExecute() throws Exception {
        // Initialize the database
        insertedOutsourcingPurchaseExecute = outsourcingPurchaseExecuteRepository.saveAndFlush(outsourcingPurchaseExecute);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the outsourcingPurchaseExecute
        restOutsourcingPurchaseExecuteMockMvc
            .perform(delete(ENTITY_API_URL_ID, outsourcingPurchaseExecute.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return outsourcingPurchaseExecuteRepository.count();
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

    protected OutsourcingPurchaseExecute getPersistedOutsourcingPurchaseExecute(OutsourcingPurchaseExecute outsourcingPurchaseExecute) {
        return outsourcingPurchaseExecuteRepository.findById(outsourcingPurchaseExecute.getId()).orElseThrow();
    }

    protected void assertPersistedOutsourcingPurchaseExecuteToMatchAllProperties(
        OutsourcingPurchaseExecute expectedOutsourcingPurchaseExecute
    ) {
        assertOutsourcingPurchaseExecuteAllPropertiesEquals(
            expectedOutsourcingPurchaseExecute,
            getPersistedOutsourcingPurchaseExecute(expectedOutsourcingPurchaseExecute)
        );
    }

    protected void assertPersistedOutsourcingPurchaseExecuteToMatchUpdatableProperties(
        OutsourcingPurchaseExecute expectedOutsourcingPurchaseExecute
    ) {
        assertOutsourcingPurchaseExecuteAllUpdatablePropertiesEquals(
            expectedOutsourcingPurchaseExecute,
            getPersistedOutsourcingPurchaseExecute(expectedOutsourcingPurchaseExecute)
        );
    }
}
