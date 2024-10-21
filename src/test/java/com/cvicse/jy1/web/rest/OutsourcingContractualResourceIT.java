package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.OutsourcingContractualAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.OutsourcingContractual;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.OutsourcingContractualRepository;
import com.cvicse.jy1.service.OutsourcingContractualService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link OutsourcingContractualResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class OutsourcingContractualResourceIT {

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_YEAR = 1L;
    private static final Long UPDATED_YEAR = 2L;

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.INTERNAL;

    private static final BigDecimal DEFAULT_FOREIGNCURRENCY = new BigDecimal(1);
    private static final BigDecimal UPDATED_FOREIGNCURRENCY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTALBUDGET = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTALBUDGET = new BigDecimal(2);

    private static final BigDecimal DEFAULT_FUNDSINPLACE = new BigDecimal(1);
    private static final BigDecimal UPDATED_FUNDSINPLACE = new BigDecimal(2);

    private static final String DEFAULT_RESPONSIBLEUNITNAME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSIBLEUNITNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AUDITTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AUDITTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ACCOUNTBANK = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTBANK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/outsourcing-contractuals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OutsourcingContractualRepository outsourcingContractualRepository;

    @Mock
    private OutsourcingContractualRepository outsourcingContractualRepositoryMock;

    @Mock
    private OutsourcingContractualService outsourcingContractualServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOutsourcingContractualMockMvc;

    private OutsourcingContractual outsourcingContractual;

    private OutsourcingContractual insertedOutsourcingContractual;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingContractual createEntity(EntityManager em) {
        OutsourcingContractual outsourcingContractual = new OutsourcingContractual()
            .department(DEFAULT_DEPARTMENT)
            .year(DEFAULT_YEAR)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .status(DEFAULT_STATUS)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .foreigncurrency(DEFAULT_FOREIGNCURRENCY)
            .totalbudget(DEFAULT_TOTALBUDGET)
            .fundsinplace(DEFAULT_FUNDSINPLACE)
            .responsibleunitname(DEFAULT_RESPONSIBLEUNITNAME)
            .audittime(DEFAULT_AUDITTIME)
            .accountbank(DEFAULT_ACCOUNTBANK);
        return outsourcingContractual;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingContractual createUpdatedEntity(EntityManager em) {
        OutsourcingContractual outsourcingContractual = new OutsourcingContractual()
            .department(UPDATED_DEPARTMENT)
            .year(UPDATED_YEAR)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .status(UPDATED_STATUS)
            .secretlevel(UPDATED_SECRETLEVEL)
            .foreigncurrency(UPDATED_FOREIGNCURRENCY)
            .totalbudget(UPDATED_TOTALBUDGET)
            .fundsinplace(UPDATED_FUNDSINPLACE)
            .responsibleunitname(UPDATED_RESPONSIBLEUNITNAME)
            .audittime(UPDATED_AUDITTIME)
            .accountbank(UPDATED_ACCOUNTBANK);
        return outsourcingContractual;
    }

    @BeforeEach
    public void initTest() {
        outsourcingContractual = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedOutsourcingContractual != null) {
            outsourcingContractualRepository.delete(insertedOutsourcingContractual);
            insertedOutsourcingContractual = null;
        }
    }

    @Test
    @Transactional
    void createOutsourcingContractual() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OutsourcingContractual
        var returnedOutsourcingContractual = om.readValue(
            restOutsourcingContractualMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingContractual)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OutsourcingContractual.class
        );

        // Validate the OutsourcingContractual in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOutsourcingContractualUpdatableFieldsEquals(
            returnedOutsourcingContractual,
            getPersistedOutsourcingContractual(returnedOutsourcingContractual)
        );

        insertedOutsourcingContractual = returnedOutsourcingContractual;
    }

    @Test
    @Transactional
    void createOutsourcingContractualWithExistingId() throws Exception {
        // Create the OutsourcingContractual with an existing ID
        outsourcingContractual.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOutsourcingContractualMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingContractual)))
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContractual in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOutsourcingContractuals() throws Exception {
        // Initialize the database
        insertedOutsourcingContractual = outsourcingContractualRepository.saveAndFlush(outsourcingContractual);

        // Get all the outsourcingContractualList
        restOutsourcingContractualMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(outsourcingContractual.getId())))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].foreigncurrency").value(hasItem(sameNumber(DEFAULT_FOREIGNCURRENCY))))
            .andExpect(jsonPath("$.[*].totalbudget").value(hasItem(sameNumber(DEFAULT_TOTALBUDGET))))
            .andExpect(jsonPath("$.[*].fundsinplace").value(hasItem(sameNumber(DEFAULT_FUNDSINPLACE))))
            .andExpect(jsonPath("$.[*].responsibleunitname").value(hasItem(DEFAULT_RESPONSIBLEUNITNAME)))
            .andExpect(jsonPath("$.[*].audittime").value(hasItem(DEFAULT_AUDITTIME.toString())))
            .andExpect(jsonPath("$.[*].accountbank").value(hasItem(DEFAULT_ACCOUNTBANK)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllOutsourcingContractualsWithEagerRelationshipsIsEnabled() throws Exception {
        when(outsourcingContractualServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOutsourcingContractualMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(outsourcingContractualServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllOutsourcingContractualsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(outsourcingContractualServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOutsourcingContractualMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(outsourcingContractualRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getOutsourcingContractual() throws Exception {
        // Initialize the database
        insertedOutsourcingContractual = outsourcingContractualRepository.saveAndFlush(outsourcingContractual);

        // Get the outsourcingContractual
        restOutsourcingContractualMockMvc
            .perform(get(ENTITY_API_URL_ID, outsourcingContractual.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(outsourcingContractual.getId()))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.foreigncurrency").value(sameNumber(DEFAULT_FOREIGNCURRENCY)))
            .andExpect(jsonPath("$.totalbudget").value(sameNumber(DEFAULT_TOTALBUDGET)))
            .andExpect(jsonPath("$.fundsinplace").value(sameNumber(DEFAULT_FUNDSINPLACE)))
            .andExpect(jsonPath("$.responsibleunitname").value(DEFAULT_RESPONSIBLEUNITNAME))
            .andExpect(jsonPath("$.audittime").value(DEFAULT_AUDITTIME.toString()))
            .andExpect(jsonPath("$.accountbank").value(DEFAULT_ACCOUNTBANK));
    }

    @Test
    @Transactional
    void getNonExistingOutsourcingContractual() throws Exception {
        // Get the outsourcingContractual
        restOutsourcingContractualMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOutsourcingContractual() throws Exception {
        // Initialize the database
        insertedOutsourcingContractual = outsourcingContractualRepository.saveAndFlush(outsourcingContractual);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingContractual
        OutsourcingContractual updatedOutsourcingContractual = outsourcingContractualRepository
            .findById(outsourcingContractual.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOutsourcingContractual are not directly saved in db
        em.detach(updatedOutsourcingContractual);
        updatedOutsourcingContractual
            .department(UPDATED_DEPARTMENT)
            .year(UPDATED_YEAR)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .status(UPDATED_STATUS)
            .secretlevel(UPDATED_SECRETLEVEL)
            .foreigncurrency(UPDATED_FOREIGNCURRENCY)
            .totalbudget(UPDATED_TOTALBUDGET)
            .fundsinplace(UPDATED_FUNDSINPLACE)
            .responsibleunitname(UPDATED_RESPONSIBLEUNITNAME)
            .audittime(UPDATED_AUDITTIME)
            .accountbank(UPDATED_ACCOUNTBANK);

        restOutsourcingContractualMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOutsourcingContractual.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOutsourcingContractual))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingContractual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOutsourcingContractualToMatchAllProperties(updatedOutsourcingContractual);
    }

    @Test
    @Transactional
    void putNonExistingOutsourcingContractual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContractual.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingContractualMockMvc
            .perform(
                put(ENTITY_API_URL_ID, outsourcingContractual.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingContractual))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContractual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOutsourcingContractual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContractual.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractualMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingContractual))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContractual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOutsourcingContractual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContractual.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractualMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingContractual)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingContractual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOutsourcingContractualWithPatch() throws Exception {
        // Initialize the database
        insertedOutsourcingContractual = outsourcingContractualRepository.saveAndFlush(outsourcingContractual);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingContractual using partial update
        OutsourcingContractual partialUpdatedOutsourcingContractual = new OutsourcingContractual();
        partialUpdatedOutsourcingContractual.setId(outsourcingContractual.getId());

        partialUpdatedOutsourcingContractual
            .starttime(UPDATED_STARTTIME)
            .foreigncurrency(UPDATED_FOREIGNCURRENCY)
            .fundsinplace(UPDATED_FUNDSINPLACE);

        restOutsourcingContractualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingContractual.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingContractual))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingContractual in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingContractualUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOutsourcingContractual, outsourcingContractual),
            getPersistedOutsourcingContractual(outsourcingContractual)
        );
    }

    @Test
    @Transactional
    void fullUpdateOutsourcingContractualWithPatch() throws Exception {
        // Initialize the database
        insertedOutsourcingContractual = outsourcingContractualRepository.saveAndFlush(outsourcingContractual);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingContractual using partial update
        OutsourcingContractual partialUpdatedOutsourcingContractual = new OutsourcingContractual();
        partialUpdatedOutsourcingContractual.setId(outsourcingContractual.getId());

        partialUpdatedOutsourcingContractual
            .department(UPDATED_DEPARTMENT)
            .year(UPDATED_YEAR)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .status(UPDATED_STATUS)
            .secretlevel(UPDATED_SECRETLEVEL)
            .foreigncurrency(UPDATED_FOREIGNCURRENCY)
            .totalbudget(UPDATED_TOTALBUDGET)
            .fundsinplace(UPDATED_FUNDSINPLACE)
            .responsibleunitname(UPDATED_RESPONSIBLEUNITNAME)
            .audittime(UPDATED_AUDITTIME)
            .accountbank(UPDATED_ACCOUNTBANK);

        restOutsourcingContractualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingContractual.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingContractual))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingContractual in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingContractualUpdatableFieldsEquals(
            partialUpdatedOutsourcingContractual,
            getPersistedOutsourcingContractual(partialUpdatedOutsourcingContractual)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOutsourcingContractual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContractual.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingContractualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, outsourcingContractual.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingContractual))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContractual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOutsourcingContractual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContractual.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractualMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingContractual))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContractual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOutsourcingContractual() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContractual.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractualMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(outsourcingContractual))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingContractual in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOutsourcingContractual() throws Exception {
        // Initialize the database
        insertedOutsourcingContractual = outsourcingContractualRepository.saveAndFlush(outsourcingContractual);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the outsourcingContractual
        restOutsourcingContractualMockMvc
            .perform(delete(ENTITY_API_URL_ID, outsourcingContractual.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return outsourcingContractualRepository.count();
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

    protected OutsourcingContractual getPersistedOutsourcingContractual(OutsourcingContractual outsourcingContractual) {
        return outsourcingContractualRepository.findById(outsourcingContractual.getId()).orElseThrow();
    }

    protected void assertPersistedOutsourcingContractualToMatchAllProperties(OutsourcingContractual expectedOutsourcingContractual) {
        assertOutsourcingContractualAllPropertiesEquals(
            expectedOutsourcingContractual,
            getPersistedOutsourcingContractual(expectedOutsourcingContractual)
        );
    }

    protected void assertPersistedOutsourcingContractualToMatchUpdatableProperties(OutsourcingContractual expectedOutsourcingContractual) {
        assertOutsourcingContractualAllUpdatablePropertiesEquals(
            expectedOutsourcingContractual,
            getPersistedOutsourcingContractual(expectedOutsourcingContractual)
        );
    }
}
