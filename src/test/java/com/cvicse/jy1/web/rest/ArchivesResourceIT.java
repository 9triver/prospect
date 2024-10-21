package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ArchivesAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Archives;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.ArchivesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link ArchivesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ArchivesResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PAGE = 1;
    private static final Integer UPDATED_PAGE = 2;

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.PUBLIC;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.INTERNAL;

    private static final LocalDate DEFAULT_CONFIDENTIALITYPERIOD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CONFIDENTIALITYPERIOD = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CONFIDENTIALNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONFIDENTIALNUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STORAGEPERIOD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STORAGEPERIOD = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PLANNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PLANNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVINGNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVINGNUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/archives";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ArchivesRepository archivesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restArchivesMockMvc;

    private Archives archives;

    private Archives insertedArchives;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Archives createEntity(EntityManager em) {
        Archives archives = new Archives()
            .title(DEFAULT_TITLE)
            .content(DEFAULT_CONTENT)
            .date(DEFAULT_DATE)
            .page(DEFAULT_PAGE)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .confidentialityperiod(DEFAULT_CONFIDENTIALITYPERIOD)
            .confidentialnumber(DEFAULT_CONFIDENTIALNUMBER)
            .storageperiod(DEFAULT_STORAGEPERIOD)
            .plannumber(DEFAULT_PLANNUMBER)
            .remarks(DEFAULT_REMARKS)
            .receivingnumber(DEFAULT_RECEIVINGNUMBER);
        return archives;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Archives createUpdatedEntity(EntityManager em) {
        Archives archives = new Archives()
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .date(UPDATED_DATE)
            .page(UPDATED_PAGE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .confidentialityperiod(UPDATED_CONFIDENTIALITYPERIOD)
            .confidentialnumber(UPDATED_CONFIDENTIALNUMBER)
            .storageperiod(UPDATED_STORAGEPERIOD)
            .plannumber(UPDATED_PLANNUMBER)
            .remarks(UPDATED_REMARKS)
            .receivingnumber(UPDATED_RECEIVINGNUMBER);
        return archives;
    }

    @BeforeEach
    public void initTest() {
        archives = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedArchives != null) {
            archivesRepository.delete(insertedArchives);
            insertedArchives = null;
        }
    }

    @Test
    @Transactional
    void createArchives() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Archives
        var returnedArchives = om.readValue(
            restArchivesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(archives)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Archives.class
        );

        // Validate the Archives in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertArchivesUpdatableFieldsEquals(returnedArchives, getPersistedArchives(returnedArchives));

        insertedArchives = returnedArchives;
    }

    @Test
    @Transactional
    void createArchivesWithExistingId() throws Exception {
        // Create the Archives with an existing ID
        archives.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restArchivesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(archives)))
            .andExpect(status().isBadRequest());

        // Validate the Archives in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllArchives() throws Exception {
        // Initialize the database
        insertedArchives = archivesRepository.saveAndFlush(archives);

        // Get all the archivesList
        restArchivesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(archives.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].page").value(hasItem(DEFAULT_PAGE)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].confidentialityperiod").value(hasItem(DEFAULT_CONFIDENTIALITYPERIOD.toString())))
            .andExpect(jsonPath("$.[*].confidentialnumber").value(hasItem(DEFAULT_CONFIDENTIALNUMBER)))
            .andExpect(jsonPath("$.[*].storageperiod").value(hasItem(DEFAULT_STORAGEPERIOD.toString())))
            .andExpect(jsonPath("$.[*].plannumber").value(hasItem(DEFAULT_PLANNUMBER)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].receivingnumber").value(hasItem(DEFAULT_RECEIVINGNUMBER)));
    }

    @Test
    @Transactional
    void getArchives() throws Exception {
        // Initialize the database
        insertedArchives = archivesRepository.saveAndFlush(archives);

        // Get the archives
        restArchivesMockMvc
            .perform(get(ENTITY_API_URL_ID, archives.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(archives.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.page").value(DEFAULT_PAGE))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.confidentialityperiod").value(DEFAULT_CONFIDENTIALITYPERIOD.toString()))
            .andExpect(jsonPath("$.confidentialnumber").value(DEFAULT_CONFIDENTIALNUMBER))
            .andExpect(jsonPath("$.storageperiod").value(DEFAULT_STORAGEPERIOD.toString()))
            .andExpect(jsonPath("$.plannumber").value(DEFAULT_PLANNUMBER))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.receivingnumber").value(DEFAULT_RECEIVINGNUMBER));
    }

    @Test
    @Transactional
    void getNonExistingArchives() throws Exception {
        // Get the archives
        restArchivesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingArchives() throws Exception {
        // Initialize the database
        insertedArchives = archivesRepository.saveAndFlush(archives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the archives
        Archives updatedArchives = archivesRepository.findById(archives.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedArchives are not directly saved in db
        em.detach(updatedArchives);
        updatedArchives
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .date(UPDATED_DATE)
            .page(UPDATED_PAGE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .confidentialityperiod(UPDATED_CONFIDENTIALITYPERIOD)
            .confidentialnumber(UPDATED_CONFIDENTIALNUMBER)
            .storageperiod(UPDATED_STORAGEPERIOD)
            .plannumber(UPDATED_PLANNUMBER)
            .remarks(UPDATED_REMARKS)
            .receivingnumber(UPDATED_RECEIVINGNUMBER);

        restArchivesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedArchives.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedArchives))
            )
            .andExpect(status().isOk());

        // Validate the Archives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedArchivesToMatchAllProperties(updatedArchives);
    }

    @Test
    @Transactional
    void putNonExistingArchives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        archives.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArchivesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, archives.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(archives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Archives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchArchives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        archives.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restArchivesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(archives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Archives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamArchives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        archives.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restArchivesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(archives)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Archives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateArchivesWithPatch() throws Exception {
        // Initialize the database
        insertedArchives = archivesRepository.saveAndFlush(archives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the archives using partial update
        Archives partialUpdatedArchives = new Archives();
        partialUpdatedArchives.setId(archives.getId());

        partialUpdatedArchives
            .date(UPDATED_DATE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .confidentialnumber(UPDATED_CONFIDENTIALNUMBER)
            .storageperiod(UPDATED_STORAGEPERIOD)
            .plannumber(UPDATED_PLANNUMBER)
            .receivingnumber(UPDATED_RECEIVINGNUMBER);

        restArchivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedArchives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedArchives))
            )
            .andExpect(status().isOk());

        // Validate the Archives in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertArchivesUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedArchives, archives), getPersistedArchives(archives));
    }

    @Test
    @Transactional
    void fullUpdateArchivesWithPatch() throws Exception {
        // Initialize the database
        insertedArchives = archivesRepository.saveAndFlush(archives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the archives using partial update
        Archives partialUpdatedArchives = new Archives();
        partialUpdatedArchives.setId(archives.getId());

        partialUpdatedArchives
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .date(UPDATED_DATE)
            .page(UPDATED_PAGE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .confidentialityperiod(UPDATED_CONFIDENTIALITYPERIOD)
            .confidentialnumber(UPDATED_CONFIDENTIALNUMBER)
            .storageperiod(UPDATED_STORAGEPERIOD)
            .plannumber(UPDATED_PLANNUMBER)
            .remarks(UPDATED_REMARKS)
            .receivingnumber(UPDATED_RECEIVINGNUMBER);

        restArchivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedArchives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedArchives))
            )
            .andExpect(status().isOk());

        // Validate the Archives in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertArchivesUpdatableFieldsEquals(partialUpdatedArchives, getPersistedArchives(partialUpdatedArchives));
    }

    @Test
    @Transactional
    void patchNonExistingArchives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        archives.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArchivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, archives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(archives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Archives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchArchives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        archives.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restArchivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(archives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Archives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamArchives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        archives.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restArchivesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(archives)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Archives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteArchives() throws Exception {
        // Initialize the database
        insertedArchives = archivesRepository.saveAndFlush(archives);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the archives
        restArchivesMockMvc
            .perform(delete(ENTITY_API_URL_ID, archives.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return archivesRepository.count();
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

    protected Archives getPersistedArchives(Archives archives) {
        return archivesRepository.findById(archives.getId()).orElseThrow();
    }

    protected void assertPersistedArchivesToMatchAllProperties(Archives expectedArchives) {
        assertArchivesAllPropertiesEquals(expectedArchives, getPersistedArchives(expectedArchives));
    }

    protected void assertPersistedArchivesToMatchUpdatableProperties(Archives expectedArchives) {
        assertArchivesAllUpdatablePropertiesEquals(expectedArchives, getPersistedArchives(expectedArchives));
    }
}
