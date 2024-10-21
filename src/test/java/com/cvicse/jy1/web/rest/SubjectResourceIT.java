package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.SubjectAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Subject;
import com.cvicse.jy1.repository.SubjectRepository;
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
 * Integration tests for the {@link SubjectResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SubjectResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PARENTID = "AAAAAAAAAA";
    private static final String UPDATED_PARENTID = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/subjects";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSubjectMockMvc;

    private Subject subject;

    private Subject insertedSubject;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Subject createEntity(EntityManager em) {
        Subject subject = new Subject().name(DEFAULT_NAME).type(DEFAULT_TYPE).parentid(DEFAULT_PARENTID).remark(DEFAULT_REMARK);
        return subject;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Subject createUpdatedEntity(EntityManager em) {
        Subject subject = new Subject().name(UPDATED_NAME).type(UPDATED_TYPE).parentid(UPDATED_PARENTID).remark(UPDATED_REMARK);
        return subject;
    }

    @BeforeEach
    public void initTest() {
        subject = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedSubject != null) {
            subjectRepository.delete(insertedSubject);
            insertedSubject = null;
        }
    }

    @Test
    @Transactional
    void createSubject() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Subject
        var returnedSubject = om.readValue(
            restSubjectMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subject)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Subject.class
        );

        // Validate the Subject in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSubjectUpdatableFieldsEquals(returnedSubject, getPersistedSubject(returnedSubject));

        insertedSubject = returnedSubject;
    }

    @Test
    @Transactional
    void createSubjectWithExistingId() throws Exception {
        // Create the Subject with an existing ID
        subject.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubjectMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subject)))
            .andExpect(status().isBadRequest());

        // Validate the Subject in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSubjects() throws Exception {
        // Initialize the database
        insertedSubject = subjectRepository.saveAndFlush(subject);

        // Get all the subjectList
        restSubjectMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subject.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].parentid").value(hasItem(DEFAULT_PARENTID)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)));
    }

    @Test
    @Transactional
    void getSubject() throws Exception {
        // Initialize the database
        insertedSubject = subjectRepository.saveAndFlush(subject);

        // Get the subject
        restSubjectMockMvc
            .perform(get(ENTITY_API_URL_ID, subject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subject.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.parentid").value(DEFAULT_PARENTID))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK));
    }

    @Test
    @Transactional
    void getNonExistingSubject() throws Exception {
        // Get the subject
        restSubjectMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSubject() throws Exception {
        // Initialize the database
        insertedSubject = subjectRepository.saveAndFlush(subject);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the subject
        Subject updatedSubject = subjectRepository.findById(subject.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSubject are not directly saved in db
        em.detach(updatedSubject);
        updatedSubject.name(UPDATED_NAME).type(UPDATED_TYPE).parentid(UPDATED_PARENTID).remark(UPDATED_REMARK);

        restSubjectMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSubject.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSubject))
            )
            .andExpect(status().isOk());

        // Validate the Subject in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSubjectToMatchAllProperties(updatedSubject);
    }

    @Test
    @Transactional
    void putNonExistingSubject() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subject.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubjectMockMvc
            .perform(put(ENTITY_API_URL_ID, subject.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subject)))
            .andExpect(status().isBadRequest());

        // Validate the Subject in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSubject() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subject.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubjectMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(subject))
            )
            .andExpect(status().isBadRequest());

        // Validate the Subject in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSubject() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subject.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubjectMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subject)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Subject in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSubjectWithPatch() throws Exception {
        // Initialize the database
        insertedSubject = subjectRepository.saveAndFlush(subject);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the subject using partial update
        Subject partialUpdatedSubject = new Subject();
        partialUpdatedSubject.setId(subject.getId());

        partialUpdatedSubject.parentid(UPDATED_PARENTID).remark(UPDATED_REMARK);

        restSubjectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSubject.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSubject))
            )
            .andExpect(status().isOk());

        // Validate the Subject in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSubjectUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedSubject, subject), getPersistedSubject(subject));
    }

    @Test
    @Transactional
    void fullUpdateSubjectWithPatch() throws Exception {
        // Initialize the database
        insertedSubject = subjectRepository.saveAndFlush(subject);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the subject using partial update
        Subject partialUpdatedSubject = new Subject();
        partialUpdatedSubject.setId(subject.getId());

        partialUpdatedSubject.name(UPDATED_NAME).type(UPDATED_TYPE).parentid(UPDATED_PARENTID).remark(UPDATED_REMARK);

        restSubjectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSubject.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSubject))
            )
            .andExpect(status().isOk());

        // Validate the Subject in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSubjectUpdatableFieldsEquals(partialUpdatedSubject, getPersistedSubject(partialUpdatedSubject));
    }

    @Test
    @Transactional
    void patchNonExistingSubject() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subject.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubjectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, subject.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(subject))
            )
            .andExpect(status().isBadRequest());

        // Validate the Subject in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSubject() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subject.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubjectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(subject))
            )
            .andExpect(status().isBadRequest());

        // Validate the Subject in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSubject() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subject.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubjectMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(subject)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Subject in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSubject() throws Exception {
        // Initialize the database
        insertedSubject = subjectRepository.saveAndFlush(subject);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the subject
        restSubjectMockMvc
            .perform(delete(ENTITY_API_URL_ID, subject.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return subjectRepository.count();
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

    protected Subject getPersistedSubject(Subject subject) {
        return subjectRepository.findById(subject.getId()).orElseThrow();
    }

    protected void assertPersistedSubjectToMatchAllProperties(Subject expectedSubject) {
        assertSubjectAllPropertiesEquals(expectedSubject, getPersistedSubject(expectedSubject));
    }

    protected void assertPersistedSubjectToMatchUpdatableProperties(Subject expectedSubject) {
        assertSubjectAllUpdatablePropertiesEquals(expectedSubject, getPersistedSubject(expectedSubject));
    }
}
