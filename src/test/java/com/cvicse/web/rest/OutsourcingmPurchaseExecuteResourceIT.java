package com.cvicse.web.rest;

import static com.cvicse.domain.OutsourcingmPurchaseExecuteAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.OutsourcingmPurchaseExecute;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.OutsourcingmPurchaseExecuteRepository;
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
 * Integration tests for the {@link OutsourcingmPurchaseExecuteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OutsourcingmPurchaseExecuteResourceIT {

    private static final Long DEFAULT_OUTSOURCINGEXECUTEID = 1L;
    private static final Long UPDATED_OUTSOURCINGEXECUTEID = 2L;

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

    private static final String ENTITY_API_URL = "/api/outsourcingm-purchase-executes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OutsourcingmPurchaseExecuteRepository outsourcingmPurchaseExecuteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOutsourcingmPurchaseExecuteMockMvc;

    private OutsourcingmPurchaseExecute outsourcingmPurchaseExecute;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingmPurchaseExecute createEntity(EntityManager em) {
        OutsourcingmPurchaseExecute outsourcingmPurchaseExecute = new OutsourcingmPurchaseExecute()
            .outsourcingexecuteid(DEFAULT_OUTSOURCINGEXECUTEID)
            .matarialname(DEFAULT_MATARIALNAME)
            .purchasingmethod(DEFAULT_PURCHASINGMETHOD)
            .budgit(DEFAULT_BUDGIT)
            .needtime(DEFAULT_NEEDTIME)
            .planusetime(DEFAULT_PLANUSETIME)
            .supplierid(DEFAULT_SUPPLIERID)
            .price(DEFAULT_PRICE)
            .secretlevel(DEFAULT_SECRETLEVEL);
        return outsourcingmPurchaseExecute;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingmPurchaseExecute createUpdatedEntity(EntityManager em) {
        OutsourcingmPurchaseExecute outsourcingmPurchaseExecute = new OutsourcingmPurchaseExecute()
            .outsourcingexecuteid(UPDATED_OUTSOURCINGEXECUTEID)
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL);
        return outsourcingmPurchaseExecute;
    }

    @BeforeEach
    public void initTest() {
        outsourcingmPurchaseExecute = createEntity(em);
    }

    @Test
    @Transactional
    void createOutsourcingmPurchaseExecute() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OutsourcingmPurchaseExecute
        var returnedOutsourcingmPurchaseExecute = om.readValue(
            restOutsourcingmPurchaseExecuteMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmPurchaseExecute))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OutsourcingmPurchaseExecute.class
        );

        // Validate the OutsourcingmPurchaseExecute in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOutsourcingmPurchaseExecuteUpdatableFieldsEquals(
            returnedOutsourcingmPurchaseExecute,
            getPersistedOutsourcingmPurchaseExecute(returnedOutsourcingmPurchaseExecute)
        );
    }

    @Test
    @Transactional
    void createOutsourcingmPurchaseExecuteWithExistingId() throws Exception {
        // Create the OutsourcingmPurchaseExecute with an existing ID
        outsourcingmPurchaseExecute.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOutsourcingmPurchaseExecuteMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmPurchaseExecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOutsourcingmPurchaseExecutes() throws Exception {
        // Initialize the database
        outsourcingmPurchaseExecuteRepository.saveAndFlush(outsourcingmPurchaseExecute);

        // Get all the outsourcingmPurchaseExecuteList
        restOutsourcingmPurchaseExecuteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(outsourcingmPurchaseExecute.getId().intValue())))
            .andExpect(jsonPath("$.[*].outsourcingexecuteid").value(hasItem(DEFAULT_OUTSOURCINGEXECUTEID.intValue())))
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
    void getOutsourcingmPurchaseExecute() throws Exception {
        // Initialize the database
        outsourcingmPurchaseExecuteRepository.saveAndFlush(outsourcingmPurchaseExecute);

        // Get the outsourcingmPurchaseExecute
        restOutsourcingmPurchaseExecuteMockMvc
            .perform(get(ENTITY_API_URL_ID, outsourcingmPurchaseExecute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(outsourcingmPurchaseExecute.getId().intValue()))
            .andExpect(jsonPath("$.outsourcingexecuteid").value(DEFAULT_OUTSOURCINGEXECUTEID.intValue()))
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
    void getNonExistingOutsourcingmPurchaseExecute() throws Exception {
        // Get the outsourcingmPurchaseExecute
        restOutsourcingmPurchaseExecuteMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOutsourcingmPurchaseExecute() throws Exception {
        // Initialize the database
        outsourcingmPurchaseExecuteRepository.saveAndFlush(outsourcingmPurchaseExecute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmPurchaseExecute
        OutsourcingmPurchaseExecute updatedOutsourcingmPurchaseExecute = outsourcingmPurchaseExecuteRepository
            .findById(outsourcingmPurchaseExecute.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOutsourcingmPurchaseExecute are not directly saved in db
        em.detach(updatedOutsourcingmPurchaseExecute);
        updatedOutsourcingmPurchaseExecute
            .outsourcingexecuteid(UPDATED_OUTSOURCINGEXECUTEID)
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL);

        restOutsourcingmPurchaseExecuteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOutsourcingmPurchaseExecute.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOutsourcingmPurchaseExecute))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingmPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOutsourcingmPurchaseExecuteToMatchAllProperties(updatedOutsourcingmPurchaseExecute);
    }

    @Test
    @Transactional
    void putNonExistingOutsourcingmPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchaseExecute.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingmPurchaseExecuteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, outsourcingmPurchaseExecute.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingmPurchaseExecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOutsourcingmPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchaseExecute.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmPurchaseExecuteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingmPurchaseExecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOutsourcingmPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchaseExecute.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmPurchaseExecuteMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmPurchaseExecute)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingmPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOutsourcingmPurchaseExecuteWithPatch() throws Exception {
        // Initialize the database
        outsourcingmPurchaseExecuteRepository.saveAndFlush(outsourcingmPurchaseExecute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmPurchaseExecute using partial update
        OutsourcingmPurchaseExecute partialUpdatedOutsourcingmPurchaseExecute = new OutsourcingmPurchaseExecute();
        partialUpdatedOutsourcingmPurchaseExecute.setId(outsourcingmPurchaseExecute.getId());

        partialUpdatedOutsourcingmPurchaseExecute
            .outsourcingexecuteid(UPDATED_OUTSOURCINGEXECUTEID)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL);

        restOutsourcingmPurchaseExecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingmPurchaseExecute.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingmPurchaseExecute))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingmPurchaseExecute in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingmPurchaseExecuteUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOutsourcingmPurchaseExecute, outsourcingmPurchaseExecute),
            getPersistedOutsourcingmPurchaseExecute(outsourcingmPurchaseExecute)
        );
    }

    @Test
    @Transactional
    void fullUpdateOutsourcingmPurchaseExecuteWithPatch() throws Exception {
        // Initialize the database
        outsourcingmPurchaseExecuteRepository.saveAndFlush(outsourcingmPurchaseExecute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmPurchaseExecute using partial update
        OutsourcingmPurchaseExecute partialUpdatedOutsourcingmPurchaseExecute = new OutsourcingmPurchaseExecute();
        partialUpdatedOutsourcingmPurchaseExecute.setId(outsourcingmPurchaseExecute.getId());

        partialUpdatedOutsourcingmPurchaseExecute
            .outsourcingexecuteid(UPDATED_OUTSOURCINGEXECUTEID)
            .matarialname(UPDATED_MATARIALNAME)
            .purchasingmethod(UPDATED_PURCHASINGMETHOD)
            .budgit(UPDATED_BUDGIT)
            .needtime(UPDATED_NEEDTIME)
            .planusetime(UPDATED_PLANUSETIME)
            .supplierid(UPDATED_SUPPLIERID)
            .price(UPDATED_PRICE)
            .secretlevel(UPDATED_SECRETLEVEL);

        restOutsourcingmPurchaseExecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingmPurchaseExecute.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingmPurchaseExecute))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingmPurchaseExecute in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingmPurchaseExecuteUpdatableFieldsEquals(
            partialUpdatedOutsourcingmPurchaseExecute,
            getPersistedOutsourcingmPurchaseExecute(partialUpdatedOutsourcingmPurchaseExecute)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOutsourcingmPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchaseExecute.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingmPurchaseExecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, outsourcingmPurchaseExecute.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingmPurchaseExecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOutsourcingmPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchaseExecute.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmPurchaseExecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingmPurchaseExecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOutsourcingmPurchaseExecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmPurchaseExecute.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmPurchaseExecuteMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(outsourcingmPurchaseExecute))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingmPurchaseExecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOutsourcingmPurchaseExecute() throws Exception {
        // Initialize the database
        outsourcingmPurchaseExecuteRepository.saveAndFlush(outsourcingmPurchaseExecute);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the outsourcingmPurchaseExecute
        restOutsourcingmPurchaseExecuteMockMvc
            .perform(delete(ENTITY_API_URL_ID, outsourcingmPurchaseExecute.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return outsourcingmPurchaseExecuteRepository.count();
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

    protected OutsourcingmPurchaseExecute getPersistedOutsourcingmPurchaseExecute(OutsourcingmPurchaseExecute outsourcingmPurchaseExecute) {
        return outsourcingmPurchaseExecuteRepository.findById(outsourcingmPurchaseExecute.getId()).orElseThrow();
    }

    protected void assertPersistedOutsourcingmPurchaseExecuteToMatchAllProperties(
        OutsourcingmPurchaseExecute expectedOutsourcingmPurchaseExecute
    ) {
        assertOutsourcingmPurchaseExecuteAllPropertiesEquals(
            expectedOutsourcingmPurchaseExecute,
            getPersistedOutsourcingmPurchaseExecute(expectedOutsourcingmPurchaseExecute)
        );
    }

    protected void assertPersistedOutsourcingmPurchaseExecuteToMatchUpdatableProperties(
        OutsourcingmPurchaseExecute expectedOutsourcingmPurchaseExecute
    ) {
        assertOutsourcingmPurchaseExecuteAllUpdatablePropertiesEquals(
            expectedOutsourcingmPurchaseExecute,
            getPersistedOutsourcingmPurchaseExecute(expectedOutsourcingmPurchaseExecute)
        );
    }
}
