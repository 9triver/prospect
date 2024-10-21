package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.MilestoneNodeAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.MilestoneNode;
import com.cvicse.jy1.repository.MilestoneNodeRepository;
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
 * Integration tests for the {@link MilestoneNodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MilestoneNodeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PLANPAYMENTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PLANPAYMENTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_PLANPAYMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PLANPAYMENTAMOUNT = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/milestone-nodes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MilestoneNodeRepository milestoneNodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMilestoneNodeMockMvc;

    private MilestoneNode milestoneNode;

    private MilestoneNode insertedMilestoneNode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MilestoneNode createEntity(EntityManager em) {
        MilestoneNode milestoneNode = new MilestoneNode()
            .name(DEFAULT_NAME)
            .planpaymenttime(DEFAULT_PLANPAYMENTTIME)
            .planpaymentamount(DEFAULT_PLANPAYMENTAMOUNT);
        return milestoneNode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MilestoneNode createUpdatedEntity(EntityManager em) {
        MilestoneNode milestoneNode = new MilestoneNode()
            .name(UPDATED_NAME)
            .planpaymenttime(UPDATED_PLANPAYMENTTIME)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT);
        return milestoneNode;
    }

    @BeforeEach
    public void initTest() {
        milestoneNode = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedMilestoneNode != null) {
            milestoneNodeRepository.delete(insertedMilestoneNode);
            insertedMilestoneNode = null;
        }
    }

    @Test
    @Transactional
    void createMilestoneNode() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the MilestoneNode
        var returnedMilestoneNode = om.readValue(
            restMilestoneNodeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(milestoneNode)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MilestoneNode.class
        );

        // Validate the MilestoneNode in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMilestoneNodeUpdatableFieldsEquals(returnedMilestoneNode, getPersistedMilestoneNode(returnedMilestoneNode));

        insertedMilestoneNode = returnedMilestoneNode;
    }

    @Test
    @Transactional
    void createMilestoneNodeWithExistingId() throws Exception {
        // Create the MilestoneNode with an existing ID
        milestoneNode.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMilestoneNodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(milestoneNode)))
            .andExpect(status().isBadRequest());

        // Validate the MilestoneNode in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMilestoneNodes() throws Exception {
        // Initialize the database
        insertedMilestoneNode = milestoneNodeRepository.saveAndFlush(milestoneNode);

        // Get all the milestoneNodeList
        restMilestoneNodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(milestoneNode.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].planpaymenttime").value(hasItem(DEFAULT_PLANPAYMENTTIME.toString())))
            .andExpect(jsonPath("$.[*].planpaymentamount").value(hasItem(sameNumber(DEFAULT_PLANPAYMENTAMOUNT))));
    }

    @Test
    @Transactional
    void getMilestoneNode() throws Exception {
        // Initialize the database
        insertedMilestoneNode = milestoneNodeRepository.saveAndFlush(milestoneNode);

        // Get the milestoneNode
        restMilestoneNodeMockMvc
            .perform(get(ENTITY_API_URL_ID, milestoneNode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(milestoneNode.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.planpaymenttime").value(DEFAULT_PLANPAYMENTTIME.toString()))
            .andExpect(jsonPath("$.planpaymentamount").value(sameNumber(DEFAULT_PLANPAYMENTAMOUNT)));
    }

    @Test
    @Transactional
    void getNonExistingMilestoneNode() throws Exception {
        // Get the milestoneNode
        restMilestoneNodeMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMilestoneNode() throws Exception {
        // Initialize the database
        insertedMilestoneNode = milestoneNodeRepository.saveAndFlush(milestoneNode);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the milestoneNode
        MilestoneNode updatedMilestoneNode = milestoneNodeRepository.findById(milestoneNode.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMilestoneNode are not directly saved in db
        em.detach(updatedMilestoneNode);
        updatedMilestoneNode.name(UPDATED_NAME).planpaymenttime(UPDATED_PLANPAYMENTTIME).planpaymentamount(UPDATED_PLANPAYMENTAMOUNT);

        restMilestoneNodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMilestoneNode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedMilestoneNode))
            )
            .andExpect(status().isOk());

        // Validate the MilestoneNode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMilestoneNodeToMatchAllProperties(updatedMilestoneNode);
    }

    @Test
    @Transactional
    void putNonExistingMilestoneNode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        milestoneNode.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMilestoneNodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, milestoneNode.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(milestoneNode))
            )
            .andExpect(status().isBadRequest());

        // Validate the MilestoneNode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMilestoneNode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        milestoneNode.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMilestoneNodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(milestoneNode))
            )
            .andExpect(status().isBadRequest());

        // Validate the MilestoneNode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMilestoneNode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        milestoneNode.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMilestoneNodeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(milestoneNode)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MilestoneNode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMilestoneNodeWithPatch() throws Exception {
        // Initialize the database
        insertedMilestoneNode = milestoneNodeRepository.saveAndFlush(milestoneNode);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the milestoneNode using partial update
        MilestoneNode partialUpdatedMilestoneNode = new MilestoneNode();
        partialUpdatedMilestoneNode.setId(milestoneNode.getId());

        partialUpdatedMilestoneNode.name(UPDATED_NAME).planpaymentamount(UPDATED_PLANPAYMENTAMOUNT);

        restMilestoneNodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMilestoneNode.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMilestoneNode))
            )
            .andExpect(status().isOk());

        // Validate the MilestoneNode in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMilestoneNodeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedMilestoneNode, milestoneNode),
            getPersistedMilestoneNode(milestoneNode)
        );
    }

    @Test
    @Transactional
    void fullUpdateMilestoneNodeWithPatch() throws Exception {
        // Initialize the database
        insertedMilestoneNode = milestoneNodeRepository.saveAndFlush(milestoneNode);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the milestoneNode using partial update
        MilestoneNode partialUpdatedMilestoneNode = new MilestoneNode();
        partialUpdatedMilestoneNode.setId(milestoneNode.getId());

        partialUpdatedMilestoneNode
            .name(UPDATED_NAME)
            .planpaymenttime(UPDATED_PLANPAYMENTTIME)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT);

        restMilestoneNodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMilestoneNode.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMilestoneNode))
            )
            .andExpect(status().isOk());

        // Validate the MilestoneNode in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMilestoneNodeUpdatableFieldsEquals(partialUpdatedMilestoneNode, getPersistedMilestoneNode(partialUpdatedMilestoneNode));
    }

    @Test
    @Transactional
    void patchNonExistingMilestoneNode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        milestoneNode.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMilestoneNodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, milestoneNode.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(milestoneNode))
            )
            .andExpect(status().isBadRequest());

        // Validate the MilestoneNode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMilestoneNode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        milestoneNode.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMilestoneNodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(milestoneNode))
            )
            .andExpect(status().isBadRequest());

        // Validate the MilestoneNode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMilestoneNode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        milestoneNode.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMilestoneNodeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(milestoneNode)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MilestoneNode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMilestoneNode() throws Exception {
        // Initialize the database
        insertedMilestoneNode = milestoneNodeRepository.saveAndFlush(milestoneNode);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the milestoneNode
        restMilestoneNodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, milestoneNode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return milestoneNodeRepository.count();
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

    protected MilestoneNode getPersistedMilestoneNode(MilestoneNode milestoneNode) {
        return milestoneNodeRepository.findById(milestoneNode.getId()).orElseThrow();
    }

    protected void assertPersistedMilestoneNodeToMatchAllProperties(MilestoneNode expectedMilestoneNode) {
        assertMilestoneNodeAllPropertiesEquals(expectedMilestoneNode, getPersistedMilestoneNode(expectedMilestoneNode));
    }

    protected void assertPersistedMilestoneNodeToMatchUpdatableProperties(MilestoneNode expectedMilestoneNode) {
        assertMilestoneNodeAllUpdatablePropertiesEquals(expectedMilestoneNode, getPersistedMilestoneNode(expectedMilestoneNode));
    }
}
