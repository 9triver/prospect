package com.cvicse.web.rest;

import static com.cvicse.domain.ApprovalAgentAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.ApprovalAgent;
import com.cvicse.repository.ApprovalAgentRepository;
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
 * Integration tests for the {@link ApprovalAgentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ApprovalAgentResourceIT {

    private static final Long DEFAULT_AGENTID = 1L;
    private static final Long UPDATED_AGENTID = 2L;

    private static final String DEFAULT_AGENTNAME = "AAAAAAAAAA";
    private static final String UPDATED_AGENTNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AGENTSTARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AGENTSTARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_AUTOCANCELTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AUTOCANCELTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AGENTDEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_AGENTDEPARTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINALAPPROVALNAME = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINALAPPROVALNAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINALDEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINALDEPARTMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_SECRECYLEVEL = 1;
    private static final Integer UPDATED_SECRECYLEVEL = 2;

    private static final String ENTITY_API_URL = "/api/approval-agents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ApprovalAgentRepository approvalAgentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApprovalAgentMockMvc;

    private ApprovalAgent approvalAgent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApprovalAgent createEntity(EntityManager em) {
        ApprovalAgent approvalAgent = new ApprovalAgent()
            .agentid(DEFAULT_AGENTID)
            .agentname(DEFAULT_AGENTNAME)
            .agentstarttime(DEFAULT_AGENTSTARTTIME)
            .autocanceltime(DEFAULT_AUTOCANCELTIME)
            .agentdepartment(DEFAULT_AGENTDEPARTMENT)
            .originalapprovalname(DEFAULT_ORIGINALAPPROVALNAME)
            .originaldepartment(DEFAULT_ORIGINALDEPARTMENT)
            .secrecylevel(DEFAULT_SECRECYLEVEL);
        return approvalAgent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApprovalAgent createUpdatedEntity(EntityManager em) {
        ApprovalAgent approvalAgent = new ApprovalAgent()
            .agentid(UPDATED_AGENTID)
            .agentname(UPDATED_AGENTNAME)
            .agentstarttime(UPDATED_AGENTSTARTTIME)
            .autocanceltime(UPDATED_AUTOCANCELTIME)
            .agentdepartment(UPDATED_AGENTDEPARTMENT)
            .originalapprovalname(UPDATED_ORIGINALAPPROVALNAME)
            .originaldepartment(UPDATED_ORIGINALDEPARTMENT)
            .secrecylevel(UPDATED_SECRECYLEVEL);
        return approvalAgent;
    }

    @BeforeEach
    public void initTest() {
        approvalAgent = createEntity(em);
    }

    @Test
    @Transactional
    void createApprovalAgent() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ApprovalAgent
        var returnedApprovalAgent = om.readValue(
            restApprovalAgentMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(approvalAgent)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ApprovalAgent.class
        );

        // Validate the ApprovalAgent in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertApprovalAgentUpdatableFieldsEquals(returnedApprovalAgent, getPersistedApprovalAgent(returnedApprovalAgent));
    }

    @Test
    @Transactional
    void createApprovalAgentWithExistingId() throws Exception {
        // Create the ApprovalAgent with an existing ID
        approvalAgent.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restApprovalAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(approvalAgent)))
            .andExpect(status().isBadRequest());

        // Validate the ApprovalAgent in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllApprovalAgents() throws Exception {
        // Initialize the database
        approvalAgentRepository.saveAndFlush(approvalAgent);

        // Get all the approvalAgentList
        restApprovalAgentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(approvalAgent.getId().intValue())))
            .andExpect(jsonPath("$.[*].agentid").value(hasItem(DEFAULT_AGENTID.intValue())))
            .andExpect(jsonPath("$.[*].agentname").value(hasItem(DEFAULT_AGENTNAME)))
            .andExpect(jsonPath("$.[*].agentstarttime").value(hasItem(DEFAULT_AGENTSTARTTIME.toString())))
            .andExpect(jsonPath("$.[*].autocanceltime").value(hasItem(DEFAULT_AUTOCANCELTIME.toString())))
            .andExpect(jsonPath("$.[*].agentdepartment").value(hasItem(DEFAULT_AGENTDEPARTMENT)))
            .andExpect(jsonPath("$.[*].originalapprovalname").value(hasItem(DEFAULT_ORIGINALAPPROVALNAME)))
            .andExpect(jsonPath("$.[*].originaldepartment").value(hasItem(DEFAULT_ORIGINALDEPARTMENT)))
            .andExpect(jsonPath("$.[*].secrecylevel").value(hasItem(DEFAULT_SECRECYLEVEL)));
    }

    @Test
    @Transactional
    void getApprovalAgent() throws Exception {
        // Initialize the database
        approvalAgentRepository.saveAndFlush(approvalAgent);

        // Get the approvalAgent
        restApprovalAgentMockMvc
            .perform(get(ENTITY_API_URL_ID, approvalAgent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(approvalAgent.getId().intValue()))
            .andExpect(jsonPath("$.agentid").value(DEFAULT_AGENTID.intValue()))
            .andExpect(jsonPath("$.agentname").value(DEFAULT_AGENTNAME))
            .andExpect(jsonPath("$.agentstarttime").value(DEFAULT_AGENTSTARTTIME.toString()))
            .andExpect(jsonPath("$.autocanceltime").value(DEFAULT_AUTOCANCELTIME.toString()))
            .andExpect(jsonPath("$.agentdepartment").value(DEFAULT_AGENTDEPARTMENT))
            .andExpect(jsonPath("$.originalapprovalname").value(DEFAULT_ORIGINALAPPROVALNAME))
            .andExpect(jsonPath("$.originaldepartment").value(DEFAULT_ORIGINALDEPARTMENT))
            .andExpect(jsonPath("$.secrecylevel").value(DEFAULT_SECRECYLEVEL));
    }

    @Test
    @Transactional
    void getNonExistingApprovalAgent() throws Exception {
        // Get the approvalAgent
        restApprovalAgentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingApprovalAgent() throws Exception {
        // Initialize the database
        approvalAgentRepository.saveAndFlush(approvalAgent);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the approvalAgent
        ApprovalAgent updatedApprovalAgent = approvalAgentRepository.findById(approvalAgent.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedApprovalAgent are not directly saved in db
        em.detach(updatedApprovalAgent);
        updatedApprovalAgent
            .agentid(UPDATED_AGENTID)
            .agentname(UPDATED_AGENTNAME)
            .agentstarttime(UPDATED_AGENTSTARTTIME)
            .autocanceltime(UPDATED_AUTOCANCELTIME)
            .agentdepartment(UPDATED_AGENTDEPARTMENT)
            .originalapprovalname(UPDATED_ORIGINALAPPROVALNAME)
            .originaldepartment(UPDATED_ORIGINALDEPARTMENT)
            .secrecylevel(UPDATED_SECRECYLEVEL);

        restApprovalAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedApprovalAgent.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedApprovalAgent))
            )
            .andExpect(status().isOk());

        // Validate the ApprovalAgent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedApprovalAgentToMatchAllProperties(updatedApprovalAgent);
    }

    @Test
    @Transactional
    void putNonExistingApprovalAgent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        approvalAgent.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApprovalAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, approvalAgent.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(approvalAgent))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApprovalAgent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchApprovalAgent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        approvalAgent.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApprovalAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(approvalAgent))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApprovalAgent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamApprovalAgent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        approvalAgent.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApprovalAgentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(approvalAgent)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ApprovalAgent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateApprovalAgentWithPatch() throws Exception {
        // Initialize the database
        approvalAgentRepository.saveAndFlush(approvalAgent);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the approvalAgent using partial update
        ApprovalAgent partialUpdatedApprovalAgent = new ApprovalAgent();
        partialUpdatedApprovalAgent.setId(approvalAgent.getId());

        partialUpdatedApprovalAgent
            .agentname(UPDATED_AGENTNAME)
            .autocanceltime(UPDATED_AUTOCANCELTIME)
            .agentdepartment(UPDATED_AGENTDEPARTMENT)
            .originaldepartment(UPDATED_ORIGINALDEPARTMENT);

        restApprovalAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedApprovalAgent.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedApprovalAgent))
            )
            .andExpect(status().isOk());

        // Validate the ApprovalAgent in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertApprovalAgentUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedApprovalAgent, approvalAgent),
            getPersistedApprovalAgent(approvalAgent)
        );
    }

    @Test
    @Transactional
    void fullUpdateApprovalAgentWithPatch() throws Exception {
        // Initialize the database
        approvalAgentRepository.saveAndFlush(approvalAgent);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the approvalAgent using partial update
        ApprovalAgent partialUpdatedApprovalAgent = new ApprovalAgent();
        partialUpdatedApprovalAgent.setId(approvalAgent.getId());

        partialUpdatedApprovalAgent
            .agentid(UPDATED_AGENTID)
            .agentname(UPDATED_AGENTNAME)
            .agentstarttime(UPDATED_AGENTSTARTTIME)
            .autocanceltime(UPDATED_AUTOCANCELTIME)
            .agentdepartment(UPDATED_AGENTDEPARTMENT)
            .originalapprovalname(UPDATED_ORIGINALAPPROVALNAME)
            .originaldepartment(UPDATED_ORIGINALDEPARTMENT)
            .secrecylevel(UPDATED_SECRECYLEVEL);

        restApprovalAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedApprovalAgent.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedApprovalAgent))
            )
            .andExpect(status().isOk());

        // Validate the ApprovalAgent in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertApprovalAgentUpdatableFieldsEquals(partialUpdatedApprovalAgent, getPersistedApprovalAgent(partialUpdatedApprovalAgent));
    }

    @Test
    @Transactional
    void patchNonExistingApprovalAgent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        approvalAgent.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApprovalAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, approvalAgent.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(approvalAgent))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApprovalAgent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchApprovalAgent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        approvalAgent.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApprovalAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(approvalAgent))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApprovalAgent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamApprovalAgent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        approvalAgent.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApprovalAgentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(approvalAgent)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ApprovalAgent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteApprovalAgent() throws Exception {
        // Initialize the database
        approvalAgentRepository.saveAndFlush(approvalAgent);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the approvalAgent
        restApprovalAgentMockMvc
            .perform(delete(ENTITY_API_URL_ID, approvalAgent.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return approvalAgentRepository.count();
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

    protected ApprovalAgent getPersistedApprovalAgent(ApprovalAgent approvalAgent) {
        return approvalAgentRepository.findById(approvalAgent.getId()).orElseThrow();
    }

    protected void assertPersistedApprovalAgentToMatchAllProperties(ApprovalAgent expectedApprovalAgent) {
        assertApprovalAgentAllPropertiesEquals(expectedApprovalAgent, getPersistedApprovalAgent(expectedApprovalAgent));
    }

    protected void assertPersistedApprovalAgentToMatchUpdatableProperties(ApprovalAgent expectedApprovalAgent) {
        assertApprovalAgentAllUpdatablePropertiesEquals(expectedApprovalAgent, getPersistedApprovalAgent(expectedApprovalAgent));
    }
}
