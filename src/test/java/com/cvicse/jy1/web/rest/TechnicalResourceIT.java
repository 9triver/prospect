package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.TechnicalAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Technical;
import com.cvicse.jy1.repository.TechnicalRepository;
import com.cvicse.jy1.service.TechnicalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TechnicalResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class TechnicalResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/technicals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TechnicalRepository technicalRepository;

    @Mock
    private TechnicalRepository technicalRepositoryMock;

    @Mock
    private TechnicalService technicalServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTechnicalMockMvc;

    private Technical technical;

    private Technical insertedTechnical;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Technical createEntity(EntityManager em) {
        Technical technical = new Technical()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME);
        return technical;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Technical createUpdatedEntity(EntityManager em) {
        Technical technical = new Technical()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);
        return technical;
    }

    @BeforeEach
    public void initTest() {
        technical = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTechnical != null) {
            technicalRepository.delete(insertedTechnical);
            insertedTechnical = null;
        }
    }

    @Test
    @Transactional
    void createTechnical() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Technical
        var returnedTechnical = om.readValue(
            restTechnicalMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technical)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Technical.class
        );

        // Validate the Technical in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTechnicalUpdatableFieldsEquals(returnedTechnical, getPersistedTechnical(returnedTechnical));

        insertedTechnical = returnedTechnical;
    }

    @Test
    @Transactional
    void createTechnicalWithExistingId() throws Exception {
        // Create the Technical with an existing ID
        technical.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTechnicalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technical)))
            .andExpect(status().isBadRequest());

        // Validate the Technical in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTechnicals() throws Exception {
        // Initialize the database
        insertedTechnical = technicalRepository.saveAndFlush(technical);

        // Get all the technicalList
        restTechnicalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(technical.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTechnicalsWithEagerRelationshipsIsEnabled() throws Exception {
        when(technicalServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTechnicalMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(technicalServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTechnicalsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(technicalServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTechnicalMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(technicalRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getTechnical() throws Exception {
        // Initialize the database
        insertedTechnical = technicalRepository.saveAndFlush(technical);

        // Get the technical
        restTechnicalMockMvc
            .perform(get(ENTITY_API_URL_ID, technical.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(technical.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTechnical() throws Exception {
        // Get the technical
        restTechnicalMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTechnical() throws Exception {
        // Initialize the database
        insertedTechnical = technicalRepository.saveAndFlush(technical);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technical
        Technical updatedTechnical = technicalRepository.findById(technical.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTechnical are not directly saved in db
        em.detach(updatedTechnical);
        updatedTechnical.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

        restTechnicalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTechnical.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTechnical))
            )
            .andExpect(status().isOk());

        // Validate the Technical in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTechnicalToMatchAllProperties(updatedTechnical);
    }

    @Test
    @Transactional
    void putNonExistingTechnical() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technical.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTechnicalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, technical.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technical))
            )
            .andExpect(status().isBadRequest());

        // Validate the Technical in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTechnical() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technical.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(technical))
            )
            .andExpect(status().isBadRequest());

        // Validate the Technical in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTechnical() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technical.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technical)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Technical in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTechnicalWithPatch() throws Exception {
        // Initialize the database
        insertedTechnical = technicalRepository.saveAndFlush(technical);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technical using partial update
        Technical partialUpdatedTechnical = new Technical();
        partialUpdatedTechnical.setId(technical.getId());

        partialUpdatedTechnical.name(UPDATED_NAME);

        restTechnicalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTechnical.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTechnical))
            )
            .andExpect(status().isOk());

        // Validate the Technical in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTechnicalUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTechnical, technical),
            getPersistedTechnical(technical)
        );
    }

    @Test
    @Transactional
    void fullUpdateTechnicalWithPatch() throws Exception {
        // Initialize the database
        insertedTechnical = technicalRepository.saveAndFlush(technical);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technical using partial update
        Technical partialUpdatedTechnical = new Technical();
        partialUpdatedTechnical.setId(technical.getId());

        partialUpdatedTechnical.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

        restTechnicalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTechnical.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTechnical))
            )
            .andExpect(status().isOk());

        // Validate the Technical in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTechnicalUpdatableFieldsEquals(partialUpdatedTechnical, getPersistedTechnical(partialUpdatedTechnical));
    }

    @Test
    @Transactional
    void patchNonExistingTechnical() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technical.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTechnicalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, technical.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(technical))
            )
            .andExpect(status().isBadRequest());

        // Validate the Technical in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTechnical() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technical.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(technical))
            )
            .andExpect(status().isBadRequest());

        // Validate the Technical in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTechnical() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technical.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(technical)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Technical in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTechnical() throws Exception {
        // Initialize the database
        insertedTechnical = technicalRepository.saveAndFlush(technical);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the technical
        restTechnicalMockMvc
            .perform(delete(ENTITY_API_URL_ID, technical.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return technicalRepository.count();
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

    protected Technical getPersistedTechnical(Technical technical) {
        return technicalRepository.findById(technical.getId()).orElseThrow();
    }

    protected void assertPersistedTechnicalToMatchAllProperties(Technical expectedTechnical) {
        assertTechnicalAllPropertiesEquals(expectedTechnical, getPersistedTechnical(expectedTechnical));
    }

    protected void assertPersistedTechnicalToMatchUpdatableProperties(Technical expectedTechnical) {
        assertTechnicalAllUpdatablePropertiesEquals(expectedTechnical, getPersistedTechnical(expectedTechnical));
    }
}
