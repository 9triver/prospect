package com.cvicse.web.rest;

import static com.cvicse.domain.QualityreturnsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Qualityreturns;
import com.cvicse.domain.enumeration.ReturnsStatus;
import com.cvicse.repository.QualityreturnsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link QualityreturnsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QualityreturnsResourceIT {

    private static final Long DEFAULT_QUALITYRETURNSID = 1L;
    private static final Long UPDATED_QUALITYRETURNSID = 2L;

    private static final String DEFAULT_QUALITYRETURNSNAME = "AAAAAAAAAA";
    private static final String UPDATED_QUALITYRETURNSNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_QUALITYTYPE = 1;
    private static final Integer UPDATED_QUALITYTYPE = 2;

    private static final LocalDate DEFAULT_RETURNSTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RETURNSTIME = LocalDate.now(ZoneId.systemDefault());

    private static final ReturnsStatus DEFAULT_RETURNSSTATUS = ReturnsStatus.Not_Comlated;
    private static final ReturnsStatus UPDATED_RETURNSSTATUS = ReturnsStatus.Completed;

    private static final String ENTITY_API_URL = "/api/qualityreturns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualityreturnsRepository qualityreturnsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualityreturnsMockMvc;

    private Qualityreturns qualityreturns;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualityreturns createEntity(EntityManager em) {
        Qualityreturns qualityreturns = new Qualityreturns()
            .qualityreturnsid(DEFAULT_QUALITYRETURNSID)
            .qualityreturnsname(DEFAULT_QUALITYRETURNSNAME)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .qualitytype(DEFAULT_QUALITYTYPE)
            .returnstime(DEFAULT_RETURNSTIME)
            .returnsstatus(DEFAULT_RETURNSSTATUS);
        return qualityreturns;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualityreturns createUpdatedEntity(EntityManager em) {
        Qualityreturns qualityreturns = new Qualityreturns()
            .qualityreturnsid(UPDATED_QUALITYRETURNSID)
            .qualityreturnsname(UPDATED_QUALITYRETURNSNAME)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .qualitytype(UPDATED_QUALITYTYPE)
            .returnstime(UPDATED_RETURNSTIME)
            .returnsstatus(UPDATED_RETURNSSTATUS);
        return qualityreturns;
    }

    @BeforeEach
    public void initTest() {
        qualityreturns = createEntity(em);
    }

    @Test
    @Transactional
    void createQualityreturns() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Qualityreturns
        var returnedQualityreturns = om.readValue(
            restQualityreturnsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityreturns)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Qualityreturns.class
        );

        // Validate the Qualityreturns in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualityreturnsUpdatableFieldsEquals(returnedQualityreturns, getPersistedQualityreturns(returnedQualityreturns));
    }

    @Test
    @Transactional
    void createQualityreturnsWithExistingId() throws Exception {
        // Create the Qualityreturns with an existing ID
        qualityreturns.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualityreturnsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityreturns)))
            .andExpect(status().isBadRequest());

        // Validate the Qualityreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualityreturns() throws Exception {
        // Initialize the database
        qualityreturnsRepository.saveAndFlush(qualityreturns);

        // Get all the qualityreturnsList
        restQualityreturnsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualityreturns.getId().intValue())))
            .andExpect(jsonPath("$.[*].qualityreturnsid").value(hasItem(DEFAULT_QUALITYRETURNSID.intValue())))
            .andExpect(jsonPath("$.[*].qualityreturnsname").value(hasItem(DEFAULT_QUALITYRETURNSNAME)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].qualitytype").value(hasItem(DEFAULT_QUALITYTYPE)))
            .andExpect(jsonPath("$.[*].returnstime").value(hasItem(DEFAULT_RETURNSTIME.toString())))
            .andExpect(jsonPath("$.[*].returnsstatus").value(hasItem(DEFAULT_RETURNSSTATUS.toString())));
    }

    @Test
    @Transactional
    void getQualityreturns() throws Exception {
        // Initialize the database
        qualityreturnsRepository.saveAndFlush(qualityreturns);

        // Get the qualityreturns
        restQualityreturnsMockMvc
            .perform(get(ENTITY_API_URL_ID, qualityreturns.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualityreturns.getId().intValue()))
            .andExpect(jsonPath("$.qualityreturnsid").value(DEFAULT_QUALITYRETURNSID.intValue()))
            .andExpect(jsonPath("$.qualityreturnsname").value(DEFAULT_QUALITYRETURNSNAME))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.qualitytype").value(DEFAULT_QUALITYTYPE))
            .andExpect(jsonPath("$.returnstime").value(DEFAULT_RETURNSTIME.toString()))
            .andExpect(jsonPath("$.returnsstatus").value(DEFAULT_RETURNSSTATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQualityreturns() throws Exception {
        // Get the qualityreturns
        restQualityreturnsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualityreturns() throws Exception {
        // Initialize the database
        qualityreturnsRepository.saveAndFlush(qualityreturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityreturns
        Qualityreturns updatedQualityreturns = qualityreturnsRepository.findById(qualityreturns.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQualityreturns are not directly saved in db
        em.detach(updatedQualityreturns);
        updatedQualityreturns
            .qualityreturnsid(UPDATED_QUALITYRETURNSID)
            .qualityreturnsname(UPDATED_QUALITYRETURNSNAME)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .qualitytype(UPDATED_QUALITYTYPE)
            .returnstime(UPDATED_RETURNSTIME)
            .returnsstatus(UPDATED_RETURNSSTATUS);

        restQualityreturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualityreturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualityreturns))
            )
            .andExpect(status().isOk());

        // Validate the Qualityreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualityreturnsToMatchAllProperties(updatedQualityreturns);
    }

    @Test
    @Transactional
    void putNonExistingQualityreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityreturns.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityreturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualityreturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualityreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualityreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityreturns.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityreturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualityreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualityreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityreturns.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityreturnsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityreturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualityreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualityreturnsWithPatch() throws Exception {
        // Initialize the database
        qualityreturnsRepository.saveAndFlush(qualityreturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityreturns using partial update
        Qualityreturns partialUpdatedQualityreturns = new Qualityreturns();
        partialUpdatedQualityreturns.setId(qualityreturns.getId());

        partialUpdatedQualityreturns
            .qualityreturnsid(UPDATED_QUALITYRETURNSID)
            .qualitytype(UPDATED_QUALITYTYPE)
            .returnstime(UPDATED_RETURNSTIME);

        restQualityreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityreturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityreturns))
            )
            .andExpect(status().isOk());

        // Validate the Qualityreturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityreturnsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualityreturns, qualityreturns),
            getPersistedQualityreturns(qualityreturns)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualityreturnsWithPatch() throws Exception {
        // Initialize the database
        qualityreturnsRepository.saveAndFlush(qualityreturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityreturns using partial update
        Qualityreturns partialUpdatedQualityreturns = new Qualityreturns();
        partialUpdatedQualityreturns.setId(qualityreturns.getId());

        partialUpdatedQualityreturns
            .qualityreturnsid(UPDATED_QUALITYRETURNSID)
            .qualityreturnsname(UPDATED_QUALITYRETURNSNAME)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .qualitytype(UPDATED_QUALITYTYPE)
            .returnstime(UPDATED_RETURNSTIME)
            .returnsstatus(UPDATED_RETURNSSTATUS);

        restQualityreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityreturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityreturns))
            )
            .andExpect(status().isOk());

        // Validate the Qualityreturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityreturnsUpdatableFieldsEquals(partialUpdatedQualityreturns, getPersistedQualityreturns(partialUpdatedQualityreturns));
    }

    @Test
    @Transactional
    void patchNonExistingQualityreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityreturns.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualityreturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualityreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualityreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityreturns.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualityreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualityreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityreturns.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityreturnsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualityreturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualityreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualityreturns() throws Exception {
        // Initialize the database
        qualityreturnsRepository.saveAndFlush(qualityreturns);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualityreturns
        restQualityreturnsMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualityreturns.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualityreturnsRepository.count();
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

    protected Qualityreturns getPersistedQualityreturns(Qualityreturns qualityreturns) {
        return qualityreturnsRepository.findById(qualityreturns.getId()).orElseThrow();
    }

    protected void assertPersistedQualityreturnsToMatchAllProperties(Qualityreturns expectedQualityreturns) {
        assertQualityreturnsAllPropertiesEquals(expectedQualityreturns, getPersistedQualityreturns(expectedQualityreturns));
    }

    protected void assertPersistedQualityreturnsToMatchUpdatableProperties(Qualityreturns expectedQualityreturns) {
        assertQualityreturnsAllUpdatablePropertiesEquals(expectedQualityreturns, getPersistedQualityreturns(expectedQualityreturns));
    }
}
