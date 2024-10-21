package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.RegularInspectionAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.RegularInspection;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.RegularInspectionRepository;
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
 * Integration tests for the {@link RegularInspectionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RegularInspectionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final String DEFAULT_WORKBAGNAME = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGNAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.PUBLIC;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.INTERNAL;

    private static final String DEFAULT_STANDARD = "AAAAAAAAAA";
    private static final String UPDATED_STANDARD = "BBBBBBBBBB";

    private static final String DEFAULT_MEASUREMENTMETHOD = "AAAAAAAAAA";
    private static final String UPDATED_MEASUREMENTMETHOD = "BBBBBBBBBB";

    private static final String DEFAULT_CHECKRESULT = "AAAAAAAAAA";
    private static final String UPDATED_CHECKRESULT = "BBBBBBBBBB";

    private static final String DEFAULT_CHECKTARGET = "AAAAAAAAAA";
    private static final String UPDATED_CHECKTARGET = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CHECKTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHECKTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CHECKCOMPLETION = "AAAAAAAAAA";
    private static final String UPDATED_CHECKCOMPLETION = "BBBBBBBBBB";

    private static final String DEFAULT_CHECKSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_CHECKSTATUS = "BBBBBBBBBB";

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.NOT_AUDITED;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.IN_AUDIT;

    private static final String ENTITY_API_URL = "/api/regular-inspections";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RegularInspectionRepository regularInspectionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRegularInspectionMockMvc;

    private RegularInspection regularInspection;

    private RegularInspection insertedRegularInspection;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegularInspection createEntity(EntityManager em) {
        RegularInspection regularInspection = new RegularInspection()
            .name(DEFAULT_NAME)
            .workbagid(DEFAULT_WORKBAGID)
            .workbagname(DEFAULT_WORKBAGNAME)
            .type(DEFAULT_TYPE)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .standard(DEFAULT_STANDARD)
            .measurementmethod(DEFAULT_MEASUREMENTMETHOD)
            .checkresult(DEFAULT_CHECKRESULT)
            .checktarget(DEFAULT_CHECKTARGET)
            .checktime(DEFAULT_CHECKTIME)
            .checkcompletion(DEFAULT_CHECKCOMPLETION)
            .checkstatus(DEFAULT_CHECKSTATUS)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return regularInspection;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegularInspection createUpdatedEntity(EntityManager em) {
        RegularInspection regularInspection = new RegularInspection()
            .name(UPDATED_NAME)
            .workbagid(UPDATED_WORKBAGID)
            .workbagname(UPDATED_WORKBAGNAME)
            .type(UPDATED_TYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .standard(UPDATED_STANDARD)
            .measurementmethod(UPDATED_MEASUREMENTMETHOD)
            .checkresult(UPDATED_CHECKRESULT)
            .checktarget(UPDATED_CHECKTARGET)
            .checktime(UPDATED_CHECKTIME)
            .checkcompletion(UPDATED_CHECKCOMPLETION)
            .checkstatus(UPDATED_CHECKSTATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return regularInspection;
    }

    @BeforeEach
    public void initTest() {
        regularInspection = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedRegularInspection != null) {
            regularInspectionRepository.delete(insertedRegularInspection);
            insertedRegularInspection = null;
        }
    }

    @Test
    @Transactional
    void createRegularInspection() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RegularInspection
        var returnedRegularInspection = om.readValue(
            restRegularInspectionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(regularInspection)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RegularInspection.class
        );

        // Validate the RegularInspection in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRegularInspectionUpdatableFieldsEquals(returnedRegularInspection, getPersistedRegularInspection(returnedRegularInspection));

        insertedRegularInspection = returnedRegularInspection;
    }

    @Test
    @Transactional
    void createRegularInspectionWithExistingId() throws Exception {
        // Create the RegularInspection with an existing ID
        regularInspection.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegularInspectionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(regularInspection)))
            .andExpect(status().isBadRequest());

        // Validate the RegularInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRegularInspections() throws Exception {
        // Initialize the database
        insertedRegularInspection = regularInspectionRepository.saveAndFlush(regularInspection);

        // Get all the regularInspectionList
        restRegularInspectionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(regularInspection.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].workbagname").value(hasItem(DEFAULT_WORKBAGNAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].standard").value(hasItem(DEFAULT_STANDARD)))
            .andExpect(jsonPath("$.[*].measurementmethod").value(hasItem(DEFAULT_MEASUREMENTMETHOD)))
            .andExpect(jsonPath("$.[*].checkresult").value(hasItem(DEFAULT_CHECKRESULT)))
            .andExpect(jsonPath("$.[*].checktarget").value(hasItem(DEFAULT_CHECKTARGET)))
            .andExpect(jsonPath("$.[*].checktime").value(hasItem(DEFAULT_CHECKTIME.toString())))
            .andExpect(jsonPath("$.[*].checkcompletion").value(hasItem(DEFAULT_CHECKCOMPLETION)))
            .andExpect(jsonPath("$.[*].checkstatus").value(hasItem(DEFAULT_CHECKSTATUS)))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getRegularInspection() throws Exception {
        // Initialize the database
        insertedRegularInspection = regularInspectionRepository.saveAndFlush(regularInspection);

        // Get the regularInspection
        restRegularInspectionMockMvc
            .perform(get(ENTITY_API_URL_ID, regularInspection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(regularInspection.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.workbagname").value(DEFAULT_WORKBAGNAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.standard").value(DEFAULT_STANDARD))
            .andExpect(jsonPath("$.measurementmethod").value(DEFAULT_MEASUREMENTMETHOD))
            .andExpect(jsonPath("$.checkresult").value(DEFAULT_CHECKRESULT))
            .andExpect(jsonPath("$.checktarget").value(DEFAULT_CHECKTARGET))
            .andExpect(jsonPath("$.checktime").value(DEFAULT_CHECKTIME.toString()))
            .andExpect(jsonPath("$.checkcompletion").value(DEFAULT_CHECKCOMPLETION))
            .andExpect(jsonPath("$.checkstatus").value(DEFAULT_CHECKSTATUS))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRegularInspection() throws Exception {
        // Get the regularInspection
        restRegularInspectionMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRegularInspection() throws Exception {
        // Initialize the database
        insertedRegularInspection = regularInspectionRepository.saveAndFlush(regularInspection);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the regularInspection
        RegularInspection updatedRegularInspection = regularInspectionRepository.findById(regularInspection.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRegularInspection are not directly saved in db
        em.detach(updatedRegularInspection);
        updatedRegularInspection
            .name(UPDATED_NAME)
            .workbagid(UPDATED_WORKBAGID)
            .workbagname(UPDATED_WORKBAGNAME)
            .type(UPDATED_TYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .standard(UPDATED_STANDARD)
            .measurementmethod(UPDATED_MEASUREMENTMETHOD)
            .checkresult(UPDATED_CHECKRESULT)
            .checktarget(UPDATED_CHECKTARGET)
            .checktime(UPDATED_CHECKTIME)
            .checkcompletion(UPDATED_CHECKCOMPLETION)
            .checkstatus(UPDATED_CHECKSTATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restRegularInspectionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRegularInspection.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRegularInspection))
            )
            .andExpect(status().isOk());

        // Validate the RegularInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRegularInspectionToMatchAllProperties(updatedRegularInspection);
    }

    @Test
    @Transactional
    void putNonExistingRegularInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        regularInspection.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegularInspectionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, regularInspection.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(regularInspection))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegularInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRegularInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        regularInspection.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegularInspectionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(regularInspection))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegularInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRegularInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        regularInspection.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegularInspectionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(regularInspection)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RegularInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRegularInspectionWithPatch() throws Exception {
        // Initialize the database
        insertedRegularInspection = regularInspectionRepository.saveAndFlush(regularInspection);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the regularInspection using partial update
        RegularInspection partialUpdatedRegularInspection = new RegularInspection();
        partialUpdatedRegularInspection.setId(regularInspection.getId());

        partialUpdatedRegularInspection
            .name(UPDATED_NAME)
            .workbagid(UPDATED_WORKBAGID)
            .workbagname(UPDATED_WORKBAGNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .standard(UPDATED_STANDARD)
            .checkresult(UPDATED_CHECKRESULT)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restRegularInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRegularInspection.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRegularInspection))
            )
            .andExpect(status().isOk());

        // Validate the RegularInspection in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRegularInspectionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRegularInspection, regularInspection),
            getPersistedRegularInspection(regularInspection)
        );
    }

    @Test
    @Transactional
    void fullUpdateRegularInspectionWithPatch() throws Exception {
        // Initialize the database
        insertedRegularInspection = regularInspectionRepository.saveAndFlush(regularInspection);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the regularInspection using partial update
        RegularInspection partialUpdatedRegularInspection = new RegularInspection();
        partialUpdatedRegularInspection.setId(regularInspection.getId());

        partialUpdatedRegularInspection
            .name(UPDATED_NAME)
            .workbagid(UPDATED_WORKBAGID)
            .workbagname(UPDATED_WORKBAGNAME)
            .type(UPDATED_TYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .standard(UPDATED_STANDARD)
            .measurementmethod(UPDATED_MEASUREMENTMETHOD)
            .checkresult(UPDATED_CHECKRESULT)
            .checktarget(UPDATED_CHECKTARGET)
            .checktime(UPDATED_CHECKTIME)
            .checkcompletion(UPDATED_CHECKCOMPLETION)
            .checkstatus(UPDATED_CHECKSTATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restRegularInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRegularInspection.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRegularInspection))
            )
            .andExpect(status().isOk());

        // Validate the RegularInspection in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRegularInspectionUpdatableFieldsEquals(
            partialUpdatedRegularInspection,
            getPersistedRegularInspection(partialUpdatedRegularInspection)
        );
    }

    @Test
    @Transactional
    void patchNonExistingRegularInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        regularInspection.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegularInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, regularInspection.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(regularInspection))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegularInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRegularInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        regularInspection.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegularInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(regularInspection))
            )
            .andExpect(status().isBadRequest());

        // Validate the RegularInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRegularInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        regularInspection.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRegularInspectionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(regularInspection)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RegularInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRegularInspection() throws Exception {
        // Initialize the database
        insertedRegularInspection = regularInspectionRepository.saveAndFlush(regularInspection);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the regularInspection
        restRegularInspectionMockMvc
            .perform(delete(ENTITY_API_URL_ID, regularInspection.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return regularInspectionRepository.count();
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

    protected RegularInspection getPersistedRegularInspection(RegularInspection regularInspection) {
        return regularInspectionRepository.findById(regularInspection.getId()).orElseThrow();
    }

    protected void assertPersistedRegularInspectionToMatchAllProperties(RegularInspection expectedRegularInspection) {
        assertRegularInspectionAllPropertiesEquals(expectedRegularInspection, getPersistedRegularInspection(expectedRegularInspection));
    }

    protected void assertPersistedRegularInspectionToMatchUpdatableProperties(RegularInspection expectedRegularInspection) {
        assertRegularInspectionAllUpdatablePropertiesEquals(
            expectedRegularInspection,
            getPersistedRegularInspection(expectedRegularInspection)
        );
    }
}
