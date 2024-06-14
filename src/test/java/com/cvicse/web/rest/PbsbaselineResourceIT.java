package com.cvicse.web.rest;

import static com.cvicse.domain.PbsbaselineAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Pbsbaseline;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.PbsbaselineRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link PbsbaselineResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PbsbaselineResourceIT {

    private static final String DEFAULT_FORMID = "AAAAAAAAAA";
    private static final String UPDATED_FORMID = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final String DEFAULT_REQUESTDEPORTMENT = "AAAAAAAAAA";
    private static final String UPDATED_REQUESTDEPORTMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHARGETYPE = 1;
    private static final Integer UPDATED_CHARGETYPE = 2;

    private static final String DEFAULT_CHARGECONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CHARGECONTENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pbsbaselines";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PbsbaselineRepository pbsbaselineRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPbsbaselineMockMvc;

    private Pbsbaseline pbsbaseline;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pbsbaseline createEntity(EntityManager em) {
        Pbsbaseline pbsbaseline = new Pbsbaseline()
            .formid(DEFAULT_FORMID)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .requestdeportment(DEFAULT_REQUESTDEPORTMENT)
            .chargetype(DEFAULT_CHARGETYPE)
            .chargecontent(DEFAULT_CHARGECONTENT)
            .status(DEFAULT_STATUS)
            .version(DEFAULT_VERSION)
            .remark(DEFAULT_REMARK);
        return pbsbaseline;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pbsbaseline createUpdatedEntity(EntityManager em) {
        Pbsbaseline pbsbaseline = new Pbsbaseline()
            .formid(UPDATED_FORMID)
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT)
            .status(UPDATED_STATUS)
            .version(UPDATED_VERSION)
            .remark(UPDATED_REMARK);
        return pbsbaseline;
    }

    @BeforeEach
    public void initTest() {
        pbsbaseline = createEntity(em);
    }

    @Test
    @Transactional
    void createPbsbaseline() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Pbsbaseline
        var returnedPbsbaseline = om.readValue(
            restPbsbaselineMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pbsbaseline)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Pbsbaseline.class
        );

        // Validate the Pbsbaseline in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPbsbaselineUpdatableFieldsEquals(returnedPbsbaseline, getPersistedPbsbaseline(returnedPbsbaseline));
    }

    @Test
    @Transactional
    void createPbsbaselineWithExistingId() throws Exception {
        // Create the Pbsbaseline with an existing ID
        pbsbaseline.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPbsbaselineMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pbsbaseline)))
            .andExpect(status().isBadRequest());

        // Validate the Pbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPbsbaselines() throws Exception {
        // Initialize the database
        pbsbaselineRepository.saveAndFlush(pbsbaseline);

        // Get all the pbsbaselineList
        restPbsbaselineMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pbsbaseline.getId().intValue())))
            .andExpect(jsonPath("$.[*].formid").value(hasItem(DEFAULT_FORMID)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].requestdeportment").value(hasItem(DEFAULT_REQUESTDEPORTMENT)))
            .andExpect(jsonPath("$.[*].chargetype").value(hasItem(DEFAULT_CHARGETYPE)))
            .andExpect(jsonPath("$.[*].chargecontent").value(hasItem(DEFAULT_CHARGECONTENT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)));
    }

    @Test
    @Transactional
    void getPbsbaseline() throws Exception {
        // Initialize the database
        pbsbaselineRepository.saveAndFlush(pbsbaseline);

        // Get the pbsbaseline
        restPbsbaselineMockMvc
            .perform(get(ENTITY_API_URL_ID, pbsbaseline.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pbsbaseline.getId().intValue()))
            .andExpect(jsonPath("$.formid").value(DEFAULT_FORMID))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.requestdeportment").value(DEFAULT_REQUESTDEPORTMENT))
            .andExpect(jsonPath("$.chargetype").value(DEFAULT_CHARGETYPE))
            .andExpect(jsonPath("$.chargecontent").value(DEFAULT_CHARGECONTENT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK));
    }

    @Test
    @Transactional
    void getNonExistingPbsbaseline() throws Exception {
        // Get the pbsbaseline
        restPbsbaselineMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPbsbaseline() throws Exception {
        // Initialize the database
        pbsbaselineRepository.saveAndFlush(pbsbaseline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pbsbaseline
        Pbsbaseline updatedPbsbaseline = pbsbaselineRepository.findById(pbsbaseline.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPbsbaseline are not directly saved in db
        em.detach(updatedPbsbaseline);
        updatedPbsbaseline
            .formid(UPDATED_FORMID)
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT)
            .status(UPDATED_STATUS)
            .version(UPDATED_VERSION)
            .remark(UPDATED_REMARK);

        restPbsbaselineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPbsbaseline.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPbsbaseline))
            )
            .andExpect(status().isOk());

        // Validate the Pbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPbsbaselineToMatchAllProperties(updatedPbsbaseline);
    }

    @Test
    @Transactional
    void putNonExistingPbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsbaseline.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPbsbaselineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pbsbaseline.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(pbsbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsbaseline.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbsbaselineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(pbsbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsbaseline.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbsbaselineMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pbsbaseline)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Pbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePbsbaselineWithPatch() throws Exception {
        // Initialize the database
        pbsbaselineRepository.saveAndFlush(pbsbaseline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pbsbaseline using partial update
        Pbsbaseline partialUpdatedPbsbaseline = new Pbsbaseline();
        partialUpdatedPbsbaseline.setId(pbsbaseline.getId());

        partialUpdatedPbsbaseline
            .formid(UPDATED_FORMID)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .status(UPDATED_STATUS)
            .version(UPDATED_VERSION)
            .remark(UPDATED_REMARK);

        restPbsbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPbsbaseline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPbsbaseline))
            )
            .andExpect(status().isOk());

        // Validate the Pbsbaseline in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPbsbaselineUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPbsbaseline, pbsbaseline),
            getPersistedPbsbaseline(pbsbaseline)
        );
    }

    @Test
    @Transactional
    void fullUpdatePbsbaselineWithPatch() throws Exception {
        // Initialize the database
        pbsbaselineRepository.saveAndFlush(pbsbaseline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pbsbaseline using partial update
        Pbsbaseline partialUpdatedPbsbaseline = new Pbsbaseline();
        partialUpdatedPbsbaseline.setId(pbsbaseline.getId());

        partialUpdatedPbsbaseline
            .formid(UPDATED_FORMID)
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT)
            .status(UPDATED_STATUS)
            .version(UPDATED_VERSION)
            .remark(UPDATED_REMARK);

        restPbsbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPbsbaseline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPbsbaseline))
            )
            .andExpect(status().isOk());

        // Validate the Pbsbaseline in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPbsbaselineUpdatableFieldsEquals(partialUpdatedPbsbaseline, getPersistedPbsbaseline(partialUpdatedPbsbaseline));
    }

    @Test
    @Transactional
    void patchNonExistingPbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsbaseline.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPbsbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pbsbaseline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(pbsbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsbaseline.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbsbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(pbsbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsbaseline.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbsbaselineMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(pbsbaseline)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Pbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePbsbaseline() throws Exception {
        // Initialize the database
        pbsbaselineRepository.saveAndFlush(pbsbaseline);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the pbsbaseline
        restPbsbaselineMockMvc
            .perform(delete(ENTITY_API_URL_ID, pbsbaseline.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return pbsbaselineRepository.count();
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

    protected Pbsbaseline getPersistedPbsbaseline(Pbsbaseline pbsbaseline) {
        return pbsbaselineRepository.findById(pbsbaseline.getId()).orElseThrow();
    }

    protected void assertPersistedPbsbaselineToMatchAllProperties(Pbsbaseline expectedPbsbaseline) {
        assertPbsbaselineAllPropertiesEquals(expectedPbsbaseline, getPersistedPbsbaseline(expectedPbsbaseline));
    }

    protected void assertPersistedPbsbaselineToMatchUpdatableProperties(Pbsbaseline expectedPbsbaseline) {
        assertPbsbaselineAllUpdatablePropertiesEquals(expectedPbsbaseline, getPersistedPbsbaseline(expectedPbsbaseline));
    }
}
