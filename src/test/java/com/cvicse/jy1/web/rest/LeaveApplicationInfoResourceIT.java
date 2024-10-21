package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.LeaveApplicationInfoAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.LeaveApplicationInfo;
import com.cvicse.jy1.repository.LeaveApplicationInfoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link LeaveApplicationInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LeaveApplicationInfoResourceIT {

    private static final String DEFAULT_START_DATE = "AAAAAAAAAA";
    private static final String UPDATED_START_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_END_DATE = "AAAAAAAAAA";
    private static final String UPDATED_END_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_LEAVE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_LEAVE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/leave-application-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LeaveApplicationInfoRepository leaveApplicationInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLeaveApplicationInfoMockMvc;

    private LeaveApplicationInfo leaveApplicationInfo;

    private LeaveApplicationInfo insertedLeaveApplicationInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeaveApplicationInfo createEntity(EntityManager em) {
        LeaveApplicationInfo leaveApplicationInfo = new LeaveApplicationInfo()
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .leaveType(DEFAULT_LEAVE_TYPE)
            .reason(DEFAULT_REASON)
            .status(DEFAULT_STATUS);
        return leaveApplicationInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeaveApplicationInfo createUpdatedEntity(EntityManager em) {
        LeaveApplicationInfo leaveApplicationInfo = new LeaveApplicationInfo()
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .leaveType(UPDATED_LEAVE_TYPE)
            .reason(UPDATED_REASON)
            .status(UPDATED_STATUS);
        return leaveApplicationInfo;
    }

    @BeforeEach
    public void initTest() {
        leaveApplicationInfo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedLeaveApplicationInfo != null) {
            leaveApplicationInfoRepository.delete(insertedLeaveApplicationInfo);
            insertedLeaveApplicationInfo = null;
        }
    }

    @Test
    @Transactional
    void createLeaveApplicationInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LeaveApplicationInfo
        var returnedLeaveApplicationInfo = om.readValue(
            restLeaveApplicationInfoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(leaveApplicationInfo)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LeaveApplicationInfo.class
        );

        // Validate the LeaveApplicationInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertLeaveApplicationInfoUpdatableFieldsEquals(
            returnedLeaveApplicationInfo,
            getPersistedLeaveApplicationInfo(returnedLeaveApplicationInfo)
        );

        insertedLeaveApplicationInfo = returnedLeaveApplicationInfo;
    }

    @Test
    @Transactional
    void createLeaveApplicationInfoWithExistingId() throws Exception {
        // Create the LeaveApplicationInfo with an existing ID
        leaveApplicationInfo.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeaveApplicationInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(leaveApplicationInfo)))
            .andExpect(status().isBadRequest());

        // Validate the LeaveApplicationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLeaveApplicationInfos() throws Exception {
        // Initialize the database
        insertedLeaveApplicationInfo = leaveApplicationInfoRepository.saveAndFlush(leaveApplicationInfo);

        // Get all the leaveApplicationInfoList
        restLeaveApplicationInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leaveApplicationInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE)))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE)))
            .andExpect(jsonPath("$.[*].leaveType").value(hasItem(DEFAULT_LEAVE_TYPE)))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    void getLeaveApplicationInfo() throws Exception {
        // Initialize the database
        insertedLeaveApplicationInfo = leaveApplicationInfoRepository.saveAndFlush(leaveApplicationInfo);

        // Get the leaveApplicationInfo
        restLeaveApplicationInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, leaveApplicationInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(leaveApplicationInfo.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE))
            .andExpect(jsonPath("$.leaveType").value(DEFAULT_LEAVE_TYPE))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingLeaveApplicationInfo() throws Exception {
        // Get the leaveApplicationInfo
        restLeaveApplicationInfoMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLeaveApplicationInfo() throws Exception {
        // Initialize the database
        insertedLeaveApplicationInfo = leaveApplicationInfoRepository.saveAndFlush(leaveApplicationInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the leaveApplicationInfo
        LeaveApplicationInfo updatedLeaveApplicationInfo = leaveApplicationInfoRepository
            .findById(leaveApplicationInfo.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedLeaveApplicationInfo are not directly saved in db
        em.detach(updatedLeaveApplicationInfo);
        updatedLeaveApplicationInfo
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .leaveType(UPDATED_LEAVE_TYPE)
            .reason(UPDATED_REASON)
            .status(UPDATED_STATUS);

        restLeaveApplicationInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLeaveApplicationInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedLeaveApplicationInfo))
            )
            .andExpect(status().isOk());

        // Validate the LeaveApplicationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLeaveApplicationInfoToMatchAllProperties(updatedLeaveApplicationInfo);
    }

    @Test
    @Transactional
    void putNonExistingLeaveApplicationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leaveApplicationInfo.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeaveApplicationInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, leaveApplicationInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(leaveApplicationInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaveApplicationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLeaveApplicationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leaveApplicationInfo.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaveApplicationInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(leaveApplicationInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaveApplicationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLeaveApplicationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leaveApplicationInfo.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaveApplicationInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(leaveApplicationInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LeaveApplicationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLeaveApplicationInfoWithPatch() throws Exception {
        // Initialize the database
        insertedLeaveApplicationInfo = leaveApplicationInfoRepository.saveAndFlush(leaveApplicationInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the leaveApplicationInfo using partial update
        LeaveApplicationInfo partialUpdatedLeaveApplicationInfo = new LeaveApplicationInfo();
        partialUpdatedLeaveApplicationInfo.setId(leaveApplicationInfo.getId());

        partialUpdatedLeaveApplicationInfo.startDate(UPDATED_START_DATE);

        restLeaveApplicationInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeaveApplicationInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLeaveApplicationInfo))
            )
            .andExpect(status().isOk());

        // Validate the LeaveApplicationInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLeaveApplicationInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLeaveApplicationInfo, leaveApplicationInfo),
            getPersistedLeaveApplicationInfo(leaveApplicationInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateLeaveApplicationInfoWithPatch() throws Exception {
        // Initialize the database
        insertedLeaveApplicationInfo = leaveApplicationInfoRepository.saveAndFlush(leaveApplicationInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the leaveApplicationInfo using partial update
        LeaveApplicationInfo partialUpdatedLeaveApplicationInfo = new LeaveApplicationInfo();
        partialUpdatedLeaveApplicationInfo.setId(leaveApplicationInfo.getId());

        partialUpdatedLeaveApplicationInfo
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .leaveType(UPDATED_LEAVE_TYPE)
            .reason(UPDATED_REASON)
            .status(UPDATED_STATUS);

        restLeaveApplicationInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeaveApplicationInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLeaveApplicationInfo))
            )
            .andExpect(status().isOk());

        // Validate the LeaveApplicationInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLeaveApplicationInfoUpdatableFieldsEquals(
            partialUpdatedLeaveApplicationInfo,
            getPersistedLeaveApplicationInfo(partialUpdatedLeaveApplicationInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingLeaveApplicationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leaveApplicationInfo.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeaveApplicationInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, leaveApplicationInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(leaveApplicationInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaveApplicationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLeaveApplicationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leaveApplicationInfo.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaveApplicationInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(leaveApplicationInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeaveApplicationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLeaveApplicationInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leaveApplicationInfo.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeaveApplicationInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(leaveApplicationInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LeaveApplicationInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLeaveApplicationInfo() throws Exception {
        // Initialize the database
        insertedLeaveApplicationInfo = leaveApplicationInfoRepository.saveAndFlush(leaveApplicationInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the leaveApplicationInfo
        restLeaveApplicationInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, leaveApplicationInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return leaveApplicationInfoRepository.count();
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

    protected LeaveApplicationInfo getPersistedLeaveApplicationInfo(LeaveApplicationInfo leaveApplicationInfo) {
        return leaveApplicationInfoRepository.findById(leaveApplicationInfo.getId()).orElseThrow();
    }

    protected void assertPersistedLeaveApplicationInfoToMatchAllProperties(LeaveApplicationInfo expectedLeaveApplicationInfo) {
        assertLeaveApplicationInfoAllPropertiesEquals(
            expectedLeaveApplicationInfo,
            getPersistedLeaveApplicationInfo(expectedLeaveApplicationInfo)
        );
    }

    protected void assertPersistedLeaveApplicationInfoToMatchUpdatableProperties(LeaveApplicationInfo expectedLeaveApplicationInfo) {
        assertLeaveApplicationInfoAllUpdatablePropertiesEquals(
            expectedLeaveApplicationInfo,
            getPersistedLeaveApplicationInfo(expectedLeaveApplicationInfo)
        );
    }
}
