package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.CommunicationRecordAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.CommunicationRecord;
import com.cvicse.jy1.repository.CommunicationRecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link CommunicationRecordResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CommunicationRecordResourceIT {

    private static final String DEFAULT_WBSID = "AAAAAAAAAA";
    private static final String UPDATED_WBSID = "BBBBBBBBBB";

    private static final String DEFAULT_WBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_WBSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSOCIATIONMEETINGNAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSOCIATIONMEETINGNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_COMMUNICATIONTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMMUNICATIONTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMMUNICATIONLOCATION = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNICATIONLOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_COMMUNICATIONCONTENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNICATIONCONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_AUDITORID = "AAAAAAAAAA";
    private static final String UPDATED_AUDITORID = "BBBBBBBBBB";

    private static final String DEFAULT_AUDITORNAME = "AAAAAAAAAA";
    private static final String UPDATED_AUDITORNAME = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/communication-records";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CommunicationRecordRepository communicationRecordRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommunicationRecordMockMvc;

    private CommunicationRecord communicationRecord;

    private CommunicationRecord insertedCommunicationRecord;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommunicationRecord createEntity(EntityManager em) {
        CommunicationRecord communicationRecord = new CommunicationRecord()
            .wbsid(DEFAULT_WBSID)
            .wbsname(DEFAULT_WBSNAME)
            .workbagid(DEFAULT_WORKBAGID)
            .associationmeetingname(DEFAULT_ASSOCIATIONMEETINGNAME)
            .communicationtime(DEFAULT_COMMUNICATIONTIME)
            .communicationlocation(DEFAULT_COMMUNICATIONLOCATION)
            .communicationcontent(DEFAULT_COMMUNICATIONCONTENT)
            .auditorid(DEFAULT_AUDITORID)
            .auditorname(DEFAULT_AUDITORNAME)
            .remarks(DEFAULT_REMARKS);
        return communicationRecord;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommunicationRecord createUpdatedEntity(EntityManager em) {
        CommunicationRecord communicationRecord = new CommunicationRecord()
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .workbagid(UPDATED_WORKBAGID)
            .associationmeetingname(UPDATED_ASSOCIATIONMEETINGNAME)
            .communicationtime(UPDATED_COMMUNICATIONTIME)
            .communicationlocation(UPDATED_COMMUNICATIONLOCATION)
            .communicationcontent(UPDATED_COMMUNICATIONCONTENT)
            .auditorid(UPDATED_AUDITORID)
            .auditorname(UPDATED_AUDITORNAME)
            .remarks(UPDATED_REMARKS);
        return communicationRecord;
    }

    @BeforeEach
    public void initTest() {
        communicationRecord = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCommunicationRecord != null) {
            communicationRecordRepository.delete(insertedCommunicationRecord);
            insertedCommunicationRecord = null;
        }
    }

    @Test
    @Transactional
    void createCommunicationRecord() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CommunicationRecord
        var returnedCommunicationRecord = om.readValue(
            restCommunicationRecordMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationRecord)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CommunicationRecord.class
        );

        // Validate the CommunicationRecord in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCommunicationRecordUpdatableFieldsEquals(
            returnedCommunicationRecord,
            getPersistedCommunicationRecord(returnedCommunicationRecord)
        );

        insertedCommunicationRecord = returnedCommunicationRecord;
    }

    @Test
    @Transactional
    void createCommunicationRecordWithExistingId() throws Exception {
        // Create the CommunicationRecord with an existing ID
        communicationRecord.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommunicationRecordMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationRecord)))
            .andExpect(status().isBadRequest());

        // Validate the CommunicationRecord in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCommunicationRecords() throws Exception {
        // Initialize the database
        insertedCommunicationRecord = communicationRecordRepository.saveAndFlush(communicationRecord);

        // Get all the communicationRecordList
        restCommunicationRecordMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(communicationRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].wbsid").value(hasItem(DEFAULT_WBSID)))
            .andExpect(jsonPath("$.[*].wbsname").value(hasItem(DEFAULT_WBSNAME)))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].associationmeetingname").value(hasItem(DEFAULT_ASSOCIATIONMEETINGNAME)))
            .andExpect(jsonPath("$.[*].communicationtime").value(hasItem(DEFAULT_COMMUNICATIONTIME.toString())))
            .andExpect(jsonPath("$.[*].communicationlocation").value(hasItem(DEFAULT_COMMUNICATIONLOCATION)))
            .andExpect(jsonPath("$.[*].communicationcontent").value(hasItem(DEFAULT_COMMUNICATIONCONTENT)))
            .andExpect(jsonPath("$.[*].auditorid").value(hasItem(DEFAULT_AUDITORID)))
            .andExpect(jsonPath("$.[*].auditorname").value(hasItem(DEFAULT_AUDITORNAME)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));
    }

    @Test
    @Transactional
    void getCommunicationRecord() throws Exception {
        // Initialize the database
        insertedCommunicationRecord = communicationRecordRepository.saveAndFlush(communicationRecord);

        // Get the communicationRecord
        restCommunicationRecordMockMvc
            .perform(get(ENTITY_API_URL_ID, communicationRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(communicationRecord.getId().intValue()))
            .andExpect(jsonPath("$.wbsid").value(DEFAULT_WBSID))
            .andExpect(jsonPath("$.wbsname").value(DEFAULT_WBSNAME))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.associationmeetingname").value(DEFAULT_ASSOCIATIONMEETINGNAME))
            .andExpect(jsonPath("$.communicationtime").value(DEFAULT_COMMUNICATIONTIME.toString()))
            .andExpect(jsonPath("$.communicationlocation").value(DEFAULT_COMMUNICATIONLOCATION))
            .andExpect(jsonPath("$.communicationcontent").value(DEFAULT_COMMUNICATIONCONTENT))
            .andExpect(jsonPath("$.auditorid").value(DEFAULT_AUDITORID))
            .andExpect(jsonPath("$.auditorname").value(DEFAULT_AUDITORNAME))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS));
    }

    @Test
    @Transactional
    void getNonExistingCommunicationRecord() throws Exception {
        // Get the communicationRecord
        restCommunicationRecordMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCommunicationRecord() throws Exception {
        // Initialize the database
        insertedCommunicationRecord = communicationRecordRepository.saveAndFlush(communicationRecord);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationRecord
        CommunicationRecord updatedCommunicationRecord = communicationRecordRepository.findById(communicationRecord.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCommunicationRecord are not directly saved in db
        em.detach(updatedCommunicationRecord);
        updatedCommunicationRecord
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .workbagid(UPDATED_WORKBAGID)
            .associationmeetingname(UPDATED_ASSOCIATIONMEETINGNAME)
            .communicationtime(UPDATED_COMMUNICATIONTIME)
            .communicationlocation(UPDATED_COMMUNICATIONLOCATION)
            .communicationcontent(UPDATED_COMMUNICATIONCONTENT)
            .auditorid(UPDATED_AUDITORID)
            .auditorname(UPDATED_AUDITORNAME)
            .remarks(UPDATED_REMARKS);

        restCommunicationRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCommunicationRecord.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCommunicationRecord))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationRecord in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCommunicationRecordToMatchAllProperties(updatedCommunicationRecord);
    }

    @Test
    @Transactional
    void putNonExistingCommunicationRecord() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationRecord.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunicationRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, communicationRecord.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(communicationRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationRecord in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCommunicationRecord() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationRecord.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(communicationRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationRecord in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCommunicationRecord() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationRecord.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationRecordMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationRecord)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CommunicationRecord in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCommunicationRecordWithPatch() throws Exception {
        // Initialize the database
        insertedCommunicationRecord = communicationRecordRepository.saveAndFlush(communicationRecord);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationRecord using partial update
        CommunicationRecord partialUpdatedCommunicationRecord = new CommunicationRecord();
        partialUpdatedCommunicationRecord.setId(communicationRecord.getId());

        partialUpdatedCommunicationRecord
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .workbagid(UPDATED_WORKBAGID)
            .communicationlocation(UPDATED_COMMUNICATIONLOCATION)
            .auditorname(UPDATED_AUDITORNAME);

        restCommunicationRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommunicationRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCommunicationRecord))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationRecord in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCommunicationRecordUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCommunicationRecord, communicationRecord),
            getPersistedCommunicationRecord(communicationRecord)
        );
    }

    @Test
    @Transactional
    void fullUpdateCommunicationRecordWithPatch() throws Exception {
        // Initialize the database
        insertedCommunicationRecord = communicationRecordRepository.saveAndFlush(communicationRecord);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationRecord using partial update
        CommunicationRecord partialUpdatedCommunicationRecord = new CommunicationRecord();
        partialUpdatedCommunicationRecord.setId(communicationRecord.getId());

        partialUpdatedCommunicationRecord
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .workbagid(UPDATED_WORKBAGID)
            .associationmeetingname(UPDATED_ASSOCIATIONMEETINGNAME)
            .communicationtime(UPDATED_COMMUNICATIONTIME)
            .communicationlocation(UPDATED_COMMUNICATIONLOCATION)
            .communicationcontent(UPDATED_COMMUNICATIONCONTENT)
            .auditorid(UPDATED_AUDITORID)
            .auditorname(UPDATED_AUDITORNAME)
            .remarks(UPDATED_REMARKS);

        restCommunicationRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommunicationRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCommunicationRecord))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationRecord in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCommunicationRecordUpdatableFieldsEquals(
            partialUpdatedCommunicationRecord,
            getPersistedCommunicationRecord(partialUpdatedCommunicationRecord)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCommunicationRecord() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationRecord.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunicationRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, communicationRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(communicationRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationRecord in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCommunicationRecord() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationRecord.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(communicationRecord))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationRecord in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCommunicationRecord() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationRecord.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationRecordMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(communicationRecord)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CommunicationRecord in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCommunicationRecord() throws Exception {
        // Initialize the database
        insertedCommunicationRecord = communicationRecordRepository.saveAndFlush(communicationRecord);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the communicationRecord
        restCommunicationRecordMockMvc
            .perform(delete(ENTITY_API_URL_ID, communicationRecord.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return communicationRecordRepository.count();
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

    protected CommunicationRecord getPersistedCommunicationRecord(CommunicationRecord communicationRecord) {
        return communicationRecordRepository.findById(communicationRecord.getId()).orElseThrow();
    }

    protected void assertPersistedCommunicationRecordToMatchAllProperties(CommunicationRecord expectedCommunicationRecord) {
        assertCommunicationRecordAllPropertiesEquals(
            expectedCommunicationRecord,
            getPersistedCommunicationRecord(expectedCommunicationRecord)
        );
    }

    protected void assertPersistedCommunicationRecordToMatchUpdatableProperties(CommunicationRecord expectedCommunicationRecord) {
        assertCommunicationRecordAllUpdatablePropertiesEquals(
            expectedCommunicationRecord,
            getPersistedCommunicationRecord(expectedCommunicationRecord)
        );
    }
}
