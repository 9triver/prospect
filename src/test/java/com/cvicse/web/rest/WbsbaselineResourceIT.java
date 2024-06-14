package com.cvicse.web.rest;

import static com.cvicse.domain.WbsbaselineAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Wbsbaseline;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.WbsbaselineRepository;
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
 * Integration tests for the {@link WbsbaselineResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WbsbaselineResourceIT {

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

    private static final String ENTITY_API_URL = "/api/wbsbaselines";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private WbsbaselineRepository wbsbaselineRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWbsbaselineMockMvc;

    private Wbsbaseline wbsbaseline;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wbsbaseline createEntity(EntityManager em) {
        Wbsbaseline wbsbaseline = new Wbsbaseline()
            .formid(DEFAULT_FORMID)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .requestdeportment(DEFAULT_REQUESTDEPORTMENT)
            .chargetype(DEFAULT_CHARGETYPE)
            .chargecontent(DEFAULT_CHARGECONTENT)
            .status(DEFAULT_STATUS)
            .version(DEFAULT_VERSION)
            .remark(DEFAULT_REMARK);
        return wbsbaseline;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wbsbaseline createUpdatedEntity(EntityManager em) {
        Wbsbaseline wbsbaseline = new Wbsbaseline()
            .formid(UPDATED_FORMID)
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT)
            .status(UPDATED_STATUS)
            .version(UPDATED_VERSION)
            .remark(UPDATED_REMARK);
        return wbsbaseline;
    }

    @BeforeEach
    public void initTest() {
        wbsbaseline = createEntity(em);
    }

    @Test
    @Transactional
    void createWbsbaseline() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Wbsbaseline
        var returnedWbsbaseline = om.readValue(
            restWbsbaselineMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wbsbaseline)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Wbsbaseline.class
        );

        // Validate the Wbsbaseline in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertWbsbaselineUpdatableFieldsEquals(returnedWbsbaseline, getPersistedWbsbaseline(returnedWbsbaseline));
    }

    @Test
    @Transactional
    void createWbsbaselineWithExistingId() throws Exception {
        // Create the Wbsbaseline with an existing ID
        wbsbaseline.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWbsbaselineMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wbsbaseline)))
            .andExpect(status().isBadRequest());

        // Validate the Wbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWbsbaselines() throws Exception {
        // Initialize the database
        wbsbaselineRepository.saveAndFlush(wbsbaseline);

        // Get all the wbsbaselineList
        restWbsbaselineMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wbsbaseline.getId().intValue())))
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
    void getWbsbaseline() throws Exception {
        // Initialize the database
        wbsbaselineRepository.saveAndFlush(wbsbaseline);

        // Get the wbsbaseline
        restWbsbaselineMockMvc
            .perform(get(ENTITY_API_URL_ID, wbsbaseline.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(wbsbaseline.getId().intValue()))
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
    void getNonExistingWbsbaseline() throws Exception {
        // Get the wbsbaseline
        restWbsbaselineMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingWbsbaseline() throws Exception {
        // Initialize the database
        wbsbaselineRepository.saveAndFlush(wbsbaseline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wbsbaseline
        Wbsbaseline updatedWbsbaseline = wbsbaselineRepository.findById(wbsbaseline.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedWbsbaseline are not directly saved in db
        em.detach(updatedWbsbaseline);
        updatedWbsbaseline
            .formid(UPDATED_FORMID)
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT)
            .status(UPDATED_STATUS)
            .version(UPDATED_VERSION)
            .remark(UPDATED_REMARK);

        restWbsbaselineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedWbsbaseline.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedWbsbaseline))
            )
            .andExpect(status().isOk());

        // Validate the Wbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedWbsbaselineToMatchAllProperties(updatedWbsbaseline);
    }

    @Test
    @Transactional
    void putNonExistingWbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsbaseline.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWbsbaselineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wbsbaseline.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(wbsbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsbaseline.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbsbaselineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(wbsbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsbaseline.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbsbaselineMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wbsbaseline)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Wbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWbsbaselineWithPatch() throws Exception {
        // Initialize the database
        wbsbaselineRepository.saveAndFlush(wbsbaseline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wbsbaseline using partial update
        Wbsbaseline partialUpdatedWbsbaseline = new Wbsbaseline();
        partialUpdatedWbsbaseline.setId(wbsbaseline.getId());

        partialUpdatedWbsbaseline.secretlevel(UPDATED_SECRETLEVEL).status(UPDATED_STATUS).remark(UPDATED_REMARK);

        restWbsbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWbsbaseline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWbsbaseline))
            )
            .andExpect(status().isOk());

        // Validate the Wbsbaseline in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWbsbaselineUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedWbsbaseline, wbsbaseline),
            getPersistedWbsbaseline(wbsbaseline)
        );
    }

    @Test
    @Transactional
    void fullUpdateWbsbaselineWithPatch() throws Exception {
        // Initialize the database
        wbsbaselineRepository.saveAndFlush(wbsbaseline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wbsbaseline using partial update
        Wbsbaseline partialUpdatedWbsbaseline = new Wbsbaseline();
        partialUpdatedWbsbaseline.setId(wbsbaseline.getId());

        partialUpdatedWbsbaseline
            .formid(UPDATED_FORMID)
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT)
            .status(UPDATED_STATUS)
            .version(UPDATED_VERSION)
            .remark(UPDATED_REMARK);

        restWbsbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWbsbaseline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWbsbaseline))
            )
            .andExpect(status().isOk());

        // Validate the Wbsbaseline in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWbsbaselineUpdatableFieldsEquals(partialUpdatedWbsbaseline, getPersistedWbsbaseline(partialUpdatedWbsbaseline));
    }

    @Test
    @Transactional
    void patchNonExistingWbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsbaseline.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWbsbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, wbsbaseline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(wbsbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsbaseline.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbsbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(wbsbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWbsbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbsbaseline.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbsbaselineMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(wbsbaseline)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Wbsbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWbsbaseline() throws Exception {
        // Initialize the database
        wbsbaselineRepository.saveAndFlush(wbsbaseline);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the wbsbaseline
        restWbsbaselineMockMvc
            .perform(delete(ENTITY_API_URL_ID, wbsbaseline.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return wbsbaselineRepository.count();
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

    protected Wbsbaseline getPersistedWbsbaseline(Wbsbaseline wbsbaseline) {
        return wbsbaselineRepository.findById(wbsbaseline.getId()).orElseThrow();
    }

    protected void assertPersistedWbsbaselineToMatchAllProperties(Wbsbaseline expectedWbsbaseline) {
        assertWbsbaselineAllPropertiesEquals(expectedWbsbaseline, getPersistedWbsbaseline(expectedWbsbaseline));
    }

    protected void assertPersistedWbsbaselineToMatchUpdatableProperties(Wbsbaseline expectedWbsbaseline) {
        assertWbsbaselineAllUpdatablePropertiesEquals(expectedWbsbaseline, getPersistedWbsbaseline(expectedWbsbaseline));
    }
}
