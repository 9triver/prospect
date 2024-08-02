package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.OfficersAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Officers;
import com.cvicse.jy1.domain.enumeration.OfficersStatus;
import com.cvicse.jy1.repository.OfficersRepository;
import com.cvicse.jy1.service.OfficersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
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
 * Integration tests for the {@link OfficersResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class OfficersResourceIT {

    private static final LocalDate DEFAULT_HIREDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_HIREDATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_YEARS = 1;
    private static final Integer UPDATED_YEARS = 2;

    private static final OfficersStatus DEFAULT_STATUS = OfficersStatus.ON_JOB;
    private static final OfficersStatus UPDATED_STATUS = OfficersStatus.NO_JOB;

    private static final String ENTITY_API_URL = "/api/officers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OfficersRepository officersRepository;

    @Mock
    private OfficersRepository officersRepositoryMock;

    @Mock
    private OfficersService officersServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOfficersMockMvc;

    private Officers officers;

    private Officers insertedOfficers;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Officers createEntity(EntityManager em) {
        Officers officers = new Officers().hiredate(DEFAULT_HIREDATE).years(DEFAULT_YEARS).status(DEFAULT_STATUS);
        return officers;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Officers createUpdatedEntity(EntityManager em) {
        Officers officers = new Officers().hiredate(UPDATED_HIREDATE).years(UPDATED_YEARS).status(UPDATED_STATUS);
        return officers;
    }

    @BeforeEach
    public void initTest() {
        officers = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedOfficers != null) {
            officersRepository.delete(insertedOfficers);
            insertedOfficers = null;
        }
    }

    @Test
    @Transactional
    void createOfficers() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Officers
        var returnedOfficers = om.readValue(
            restOfficersMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(officers)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Officers.class
        );

        // Validate the Officers in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOfficersUpdatableFieldsEquals(returnedOfficers, getPersistedOfficers(returnedOfficers));

        insertedOfficers = returnedOfficers;
    }

    @Test
    @Transactional
    void createOfficersWithExistingId() throws Exception {
        // Create the Officers with an existing ID
        officers.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfficersMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(officers)))
            .andExpect(status().isBadRequest());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOfficers() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        // Get all the officersList
        restOfficersMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(officers.getId().intValue())))
            .andExpect(jsonPath("$.[*].hiredate").value(hasItem(DEFAULT_HIREDATE.toString())))
            .andExpect(jsonPath("$.[*].years").value(hasItem(DEFAULT_YEARS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllOfficersWithEagerRelationshipsIsEnabled() throws Exception {
        when(officersServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOfficersMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(officersServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllOfficersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(officersServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOfficersMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(officersRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getOfficers() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        // Get the officers
        restOfficersMockMvc
            .perform(get(ENTITY_API_URL_ID, officers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(officers.getId().intValue()))
            .andExpect(jsonPath("$.hiredate").value(DEFAULT_HIREDATE.toString()))
            .andExpect(jsonPath("$.years").value(DEFAULT_YEARS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingOfficers() throws Exception {
        // Get the officers
        restOfficersMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOfficers() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the officers
        Officers updatedOfficers = officersRepository.findById(officers.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOfficers are not directly saved in db
        em.detach(updatedOfficers);
        updatedOfficers.hiredate(UPDATED_HIREDATE).years(UPDATED_YEARS).status(UPDATED_STATUS);

        restOfficersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOfficers.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOfficers))
            )
            .andExpect(status().isOk());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOfficersToMatchAllProperties(updatedOfficers);
    }

    @Test
    @Transactional
    void putNonExistingOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, officers.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(officers))
            )
            .andExpect(status().isBadRequest());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(officers))
            )
            .andExpect(status().isBadRequest());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(officers)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOfficersWithPatch() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the officers using partial update
        Officers partialUpdatedOfficers = new Officers();
        partialUpdatedOfficers.setId(officers.getId());

        partialUpdatedOfficers.hiredate(UPDATED_HIREDATE).status(UPDATED_STATUS);

        restOfficersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOfficers.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOfficers))
            )
            .andExpect(status().isOk());

        // Validate the Officers in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOfficersUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedOfficers, officers), getPersistedOfficers(officers));
    }

    @Test
    @Transactional
    void fullUpdateOfficersWithPatch() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the officers using partial update
        Officers partialUpdatedOfficers = new Officers();
        partialUpdatedOfficers.setId(officers.getId());

        partialUpdatedOfficers.hiredate(UPDATED_HIREDATE).years(UPDATED_YEARS).status(UPDATED_STATUS);

        restOfficersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOfficers.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOfficers))
            )
            .andExpect(status().isOk());

        // Validate the Officers in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOfficersUpdatableFieldsEquals(partialUpdatedOfficers, getPersistedOfficers(partialUpdatedOfficers));
    }

    @Test
    @Transactional
    void patchNonExistingOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, officers.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(officers))
            )
            .andExpect(status().isBadRequest());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(officers))
            )
            .andExpect(status().isBadRequest());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(officers)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOfficers() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the officers
        restOfficersMockMvc
            .perform(delete(ENTITY_API_URL_ID, officers.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return officersRepository.count();
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

    protected Officers getPersistedOfficers(Officers officers) {
        return officersRepository.findById(officers.getId()).orElseThrow();
    }

    protected void assertPersistedOfficersToMatchAllProperties(Officers expectedOfficers) {
        assertOfficersAllPropertiesEquals(expectedOfficers, getPersistedOfficers(expectedOfficers));
    }

    protected void assertPersistedOfficersToMatchUpdatableProperties(Officers expectedOfficers) {
        assertOfficersAllUpdatablePropertiesEquals(expectedOfficers, getPersistedOfficers(expectedOfficers));
    }
}
