package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.WorkbagAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Workbag;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.WorkbagRepository;
import com.cvicse.jy1.service.WorkbagService;
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
 * Integration tests for the {@link WorkbagResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class WorkbagResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PBSID = "AAAAAAAAAA";
    private static final String UPDATED_PBSID = "BBBBBBBBBB";

    private static final Integer DEFAULT_WORKBAGTYPE = 1;
    private static final Integer UPDATED_WORKBAGTYPE = 2;

    private static final String DEFAULT_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER = "BBBBBBBBBB";

    private static final Integer DEFAULT_ISKEYIMPORTANT = 1;
    private static final Integer UPDATED_ISKEYIMPORTANT = 2;

    private static final String DEFAULT_KEYPBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_KEYPBSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMPORTANTPBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_IMPORTANTPBSNAME = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.PUBLIC;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.INTERNAL;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ESTIMATEDPURCHASINGTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ESTIMATEDPURCHASINGTIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PROGRESS = 1;
    private static final Integer UPDATED_PROGRESS = 2;

    private static final Integer DEFAULT_ISSAFETYWORK = 1;
    private static final Integer UPDATED_ISSAFETYWORK = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.NOT_AUDITED;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.IN_AUDIT;

    private static final String ENTITY_API_URL = "/api/workbags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private WorkbagRepository workbagRepository;

    @Mock
    private WorkbagRepository workbagRepositoryMock;

    @Mock
    private WorkbagService workbagServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkbagMockMvc;

    private Workbag workbag;

    private Workbag insertedWorkbag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Workbag createEntity(EntityManager em) {
        Workbag workbag = new Workbag()
            .name(DEFAULT_NAME)
            .pbsid(DEFAULT_PBSID)
            .workbagtype(DEFAULT_WORKBAGTYPE)
            .supplier(DEFAULT_SUPPLIER)
            .iskeyimportant(DEFAULT_ISKEYIMPORTANT)
            .keypbsname(DEFAULT_KEYPBSNAME)
            .importantpbsname(DEFAULT_IMPORTANTPBSNAME)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .estimatedpurchasingtime(DEFAULT_ESTIMATEDPURCHASINGTIME)
            .progress(DEFAULT_PROGRESS)
            .issafetywork(DEFAULT_ISSAFETYWORK)
            .remark(DEFAULT_REMARK)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return workbag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Workbag createUpdatedEntity(EntityManager em) {
        Workbag workbag = new Workbag()
            .name(UPDATED_NAME)
            .pbsid(UPDATED_PBSID)
            .workbagtype(UPDATED_WORKBAGTYPE)
            .supplier(UPDATED_SUPPLIER)
            .iskeyimportant(UPDATED_ISKEYIMPORTANT)
            .keypbsname(UPDATED_KEYPBSNAME)
            .importantpbsname(UPDATED_IMPORTANTPBSNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .estimatedpurchasingtime(UPDATED_ESTIMATEDPURCHASINGTIME)
            .progress(UPDATED_PROGRESS)
            .issafetywork(UPDATED_ISSAFETYWORK)
            .remark(UPDATED_REMARK)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return workbag;
    }

    @BeforeEach
    public void initTest() {
        workbag = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedWorkbag != null) {
            workbagRepository.delete(insertedWorkbag);
            insertedWorkbag = null;
        }
    }

    @Test
    @Transactional
    void createWorkbag() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Workbag
        var returnedWorkbag = om.readValue(
            restWorkbagMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(workbag)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Workbag.class
        );

        // Validate the Workbag in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertWorkbagUpdatableFieldsEquals(returnedWorkbag, getPersistedWorkbag(returnedWorkbag));

        insertedWorkbag = returnedWorkbag;
    }

    @Test
    @Transactional
    void createWorkbagWithExistingId() throws Exception {
        // Create the Workbag with an existing ID
        workbag.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkbagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(workbag)))
            .andExpect(status().isBadRequest());

        // Validate the Workbag in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWorkbags() throws Exception {
        // Initialize the database
        insertedWorkbag = workbagRepository.saveAndFlush(workbag);

        // Get all the workbagList
        restWorkbagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workbag.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].pbsid").value(hasItem(DEFAULT_PBSID)))
            .andExpect(jsonPath("$.[*].workbagtype").value(hasItem(DEFAULT_WORKBAGTYPE)))
            .andExpect(jsonPath("$.[*].supplier").value(hasItem(DEFAULT_SUPPLIER)))
            .andExpect(jsonPath("$.[*].iskeyimportant").value(hasItem(DEFAULT_ISKEYIMPORTANT)))
            .andExpect(jsonPath("$.[*].keypbsname").value(hasItem(DEFAULT_KEYPBSNAME)))
            .andExpect(jsonPath("$.[*].importantpbsname").value(hasItem(DEFAULT_IMPORTANTPBSNAME)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].estimatedpurchasingtime").value(hasItem(DEFAULT_ESTIMATEDPURCHASINGTIME.toString())))
            .andExpect(jsonPath("$.[*].progress").value(hasItem(DEFAULT_PROGRESS)))
            .andExpect(jsonPath("$.[*].issafetywork").value(hasItem(DEFAULT_ISSAFETYWORK)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllWorkbagsWithEagerRelationshipsIsEnabled() throws Exception {
        when(workbagServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restWorkbagMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(workbagServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllWorkbagsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(workbagServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restWorkbagMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(workbagRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getWorkbag() throws Exception {
        // Initialize the database
        insertedWorkbag = workbagRepository.saveAndFlush(workbag);

        // Get the workbag
        restWorkbagMockMvc
            .perform(get(ENTITY_API_URL_ID, workbag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workbag.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.pbsid").value(DEFAULT_PBSID))
            .andExpect(jsonPath("$.workbagtype").value(DEFAULT_WORKBAGTYPE))
            .andExpect(jsonPath("$.supplier").value(DEFAULT_SUPPLIER))
            .andExpect(jsonPath("$.iskeyimportant").value(DEFAULT_ISKEYIMPORTANT))
            .andExpect(jsonPath("$.keypbsname").value(DEFAULT_KEYPBSNAME))
            .andExpect(jsonPath("$.importantpbsname").value(DEFAULT_IMPORTANTPBSNAME))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.estimatedpurchasingtime").value(DEFAULT_ESTIMATEDPURCHASINGTIME.toString()))
            .andExpect(jsonPath("$.progress").value(DEFAULT_PROGRESS))
            .andExpect(jsonPath("$.issafetywork").value(DEFAULT_ISSAFETYWORK))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingWorkbag() throws Exception {
        // Get the workbag
        restWorkbagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingWorkbag() throws Exception {
        // Initialize the database
        insertedWorkbag = workbagRepository.saveAndFlush(workbag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the workbag
        Workbag updatedWorkbag = workbagRepository.findById(workbag.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedWorkbag are not directly saved in db
        em.detach(updatedWorkbag);
        updatedWorkbag
            .name(UPDATED_NAME)
            .pbsid(UPDATED_PBSID)
            .workbagtype(UPDATED_WORKBAGTYPE)
            .supplier(UPDATED_SUPPLIER)
            .iskeyimportant(UPDATED_ISKEYIMPORTANT)
            .keypbsname(UPDATED_KEYPBSNAME)
            .importantpbsname(UPDATED_IMPORTANTPBSNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .estimatedpurchasingtime(UPDATED_ESTIMATEDPURCHASINGTIME)
            .progress(UPDATED_PROGRESS)
            .issafetywork(UPDATED_ISSAFETYWORK)
            .remark(UPDATED_REMARK)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restWorkbagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedWorkbag.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedWorkbag))
            )
            .andExpect(status().isOk());

        // Validate the Workbag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedWorkbagToMatchAllProperties(updatedWorkbag);
    }

    @Test
    @Transactional
    void putNonExistingWorkbag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workbag.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkbagMockMvc
            .perform(put(ENTITY_API_URL_ID, workbag.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(workbag)))
            .andExpect(status().isBadRequest());

        // Validate the Workbag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWorkbag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workbag.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkbagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(workbag))
            )
            .andExpect(status().isBadRequest());

        // Validate the Workbag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWorkbag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workbag.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkbagMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(workbag)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Workbag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWorkbagWithPatch() throws Exception {
        // Initialize the database
        insertedWorkbag = workbagRepository.saveAndFlush(workbag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the workbag using partial update
        Workbag partialUpdatedWorkbag = new Workbag();
        partialUpdatedWorkbag.setId(workbag.getId());

        partialUpdatedWorkbag
            .name(UPDATED_NAME)
            .pbsid(UPDATED_PBSID)
            .keypbsname(UPDATED_KEYPBSNAME)
            .importantpbsname(UPDATED_IMPORTANTPBSNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .description(UPDATED_DESCRIPTION)
            .endtime(UPDATED_ENDTIME)
            .estimatedpurchasingtime(UPDATED_ESTIMATEDPURCHASINGTIME);

        restWorkbagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkbag.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWorkbag))
            )
            .andExpect(status().isOk());

        // Validate the Workbag in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWorkbagUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedWorkbag, workbag), getPersistedWorkbag(workbag));
    }

    @Test
    @Transactional
    void fullUpdateWorkbagWithPatch() throws Exception {
        // Initialize the database
        insertedWorkbag = workbagRepository.saveAndFlush(workbag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the workbag using partial update
        Workbag partialUpdatedWorkbag = new Workbag();
        partialUpdatedWorkbag.setId(workbag.getId());

        partialUpdatedWorkbag
            .name(UPDATED_NAME)
            .pbsid(UPDATED_PBSID)
            .workbagtype(UPDATED_WORKBAGTYPE)
            .supplier(UPDATED_SUPPLIER)
            .iskeyimportant(UPDATED_ISKEYIMPORTANT)
            .keypbsname(UPDATED_KEYPBSNAME)
            .importantpbsname(UPDATED_IMPORTANTPBSNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .estimatedpurchasingtime(UPDATED_ESTIMATEDPURCHASINGTIME)
            .progress(UPDATED_PROGRESS)
            .issafetywork(UPDATED_ISSAFETYWORK)
            .remark(UPDATED_REMARK)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restWorkbagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkbag.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWorkbag))
            )
            .andExpect(status().isOk());

        // Validate the Workbag in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWorkbagUpdatableFieldsEquals(partialUpdatedWorkbag, getPersistedWorkbag(partialUpdatedWorkbag));
    }

    @Test
    @Transactional
    void patchNonExistingWorkbag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workbag.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkbagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, workbag.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(workbag))
            )
            .andExpect(status().isBadRequest());

        // Validate the Workbag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWorkbag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workbag.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkbagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(workbag))
            )
            .andExpect(status().isBadRequest());

        // Validate the Workbag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWorkbag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workbag.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkbagMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(workbag)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Workbag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWorkbag() throws Exception {
        // Initialize the database
        insertedWorkbag = workbagRepository.saveAndFlush(workbag);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the workbag
        restWorkbagMockMvc
            .perform(delete(ENTITY_API_URL_ID, workbag.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return workbagRepository.count();
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

    protected Workbag getPersistedWorkbag(Workbag workbag) {
        return workbagRepository.findById(workbag.getId()).orElseThrow();
    }

    protected void assertPersistedWorkbagToMatchAllProperties(Workbag expectedWorkbag) {
        assertWorkbagAllPropertiesEquals(expectedWorkbag, getPersistedWorkbag(expectedWorkbag));
    }

    protected void assertPersistedWorkbagToMatchUpdatableProperties(Workbag expectedWorkbag) {
        assertWorkbagAllUpdatablePropertiesEquals(expectedWorkbag, getPersistedWorkbag(expectedWorkbag));
    }
}
