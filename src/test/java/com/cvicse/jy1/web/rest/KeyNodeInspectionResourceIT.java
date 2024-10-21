package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.KeyNodeInspectionAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.KeyNodeInspection;
import com.cvicse.jy1.repository.KeyNodeInspectionRepository;
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
 * Integration tests for the {@link KeyNodeInspectionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class KeyNodeInspectionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final String DEFAULT_WORKBAGNAME = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGNAME = "BBBBBBBBBB";

    private static final String DEFAULT_BELONGWBSID = "AAAAAAAAAA";
    private static final String UPDATED_BELONGWBSID = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTLEVEL = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTLEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_ISKEY = "AAAAAAAAAA";
    private static final String UPDATED_ISKEY = "BBBBBBBBBB";

    private static final String DEFAULT_ISIMPLEMENTATIONPLAN = "AAAAAAAAAA";
    private static final String UPDATED_ISIMPLEMENTATIONPLAN = "BBBBBBBBBB";

    private static final String DEFAULT_ISQUALITYPLAN = "AAAAAAAAAA";
    private static final String UPDATED_ISQUALITYPLAN = "BBBBBBBBBB";

    private static final String DEFAULT_ISTECHNIQUEPLAN = "AAAAAAAAAA";
    private static final String UPDATED_ISTECHNIQUEPLAN = "BBBBBBBBBB";

    private static final String DEFAULT_IMPLEMENTATIONPLANSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_IMPLEMENTATIONPLANSTATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ISIMPLEMENTATIONPLANMATERIAL = "AAAAAAAAAA";
    private static final String UPDATED_ISIMPLEMENTATIONPLANMATERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNOLOGYPLANSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_TECHNOLOGYPLANSTATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ISTECHNOLOGYMATERIAL = "AAAAAAAAAA";
    private static final String UPDATED_ISTECHNOLOGYMATERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTCHECKSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTCHECKSTATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ISFIRSTCHECKMATERIAL = "AAAAAAAAAA";
    private static final String UPDATED_ISFIRSTCHECKMATERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCTIONCHECKSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCTIONCHECKSTATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ISPRODUCTIONCHECKMATERIAL = "AAAAAAAAAA";
    private static final String UPDATED_ISPRODUCTIONCHECKMATERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/key-node-inspections";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private KeyNodeInspectionRepository keyNodeInspectionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKeyNodeInspectionMockMvc;

    private KeyNodeInspection keyNodeInspection;

    private KeyNodeInspection insertedKeyNodeInspection;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KeyNodeInspection createEntity(EntityManager em) {
        KeyNodeInspection keyNodeInspection = new KeyNodeInspection()
            .name(DEFAULT_NAME)
            .workbagid(DEFAULT_WORKBAGID)
            .workbagname(DEFAULT_WORKBAGNAME)
            .belongwbsid(DEFAULT_BELONGWBSID)
            .projectlevel(DEFAULT_PROJECTLEVEL)
            .iskey(DEFAULT_ISKEY)
            .isimplementationplan(DEFAULT_ISIMPLEMENTATIONPLAN)
            .isqualityplan(DEFAULT_ISQUALITYPLAN)
            .istechniqueplan(DEFAULT_ISTECHNIQUEPLAN)
            .implementationplanstatus(DEFAULT_IMPLEMENTATIONPLANSTATUS)
            .isimplementationplanmaterial(DEFAULT_ISIMPLEMENTATIONPLANMATERIAL)
            .technologyplanstatus(DEFAULT_TECHNOLOGYPLANSTATUS)
            .istechnologymaterial(DEFAULT_ISTECHNOLOGYMATERIAL)
            .firstcheckstatus(DEFAULT_FIRSTCHECKSTATUS)
            .isfirstcheckmaterial(DEFAULT_ISFIRSTCHECKMATERIAL)
            .productioncheckstatus(DEFAULT_PRODUCTIONCHECKSTATUS)
            .isproductioncheckmaterial(DEFAULT_ISPRODUCTIONCHECKMATERIAL)
            .status(DEFAULT_STATUS);
        return keyNodeInspection;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KeyNodeInspection createUpdatedEntity(EntityManager em) {
        KeyNodeInspection keyNodeInspection = new KeyNodeInspection()
            .name(UPDATED_NAME)
            .workbagid(UPDATED_WORKBAGID)
            .workbagname(UPDATED_WORKBAGNAME)
            .belongwbsid(UPDATED_BELONGWBSID)
            .projectlevel(UPDATED_PROJECTLEVEL)
            .iskey(UPDATED_ISKEY)
            .isimplementationplan(UPDATED_ISIMPLEMENTATIONPLAN)
            .isqualityplan(UPDATED_ISQUALITYPLAN)
            .istechniqueplan(UPDATED_ISTECHNIQUEPLAN)
            .implementationplanstatus(UPDATED_IMPLEMENTATIONPLANSTATUS)
            .isimplementationplanmaterial(UPDATED_ISIMPLEMENTATIONPLANMATERIAL)
            .technologyplanstatus(UPDATED_TECHNOLOGYPLANSTATUS)
            .istechnologymaterial(UPDATED_ISTECHNOLOGYMATERIAL)
            .firstcheckstatus(UPDATED_FIRSTCHECKSTATUS)
            .isfirstcheckmaterial(UPDATED_ISFIRSTCHECKMATERIAL)
            .productioncheckstatus(UPDATED_PRODUCTIONCHECKSTATUS)
            .isproductioncheckmaterial(UPDATED_ISPRODUCTIONCHECKMATERIAL)
            .status(UPDATED_STATUS);
        return keyNodeInspection;
    }

    @BeforeEach
    public void initTest() {
        keyNodeInspection = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedKeyNodeInspection != null) {
            keyNodeInspectionRepository.delete(insertedKeyNodeInspection);
            insertedKeyNodeInspection = null;
        }
    }

    @Test
    @Transactional
    void createKeyNodeInspection() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the KeyNodeInspection
        var returnedKeyNodeInspection = om.readValue(
            restKeyNodeInspectionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(keyNodeInspection)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            KeyNodeInspection.class
        );

        // Validate the KeyNodeInspection in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertKeyNodeInspectionUpdatableFieldsEquals(returnedKeyNodeInspection, getPersistedKeyNodeInspection(returnedKeyNodeInspection));

        insertedKeyNodeInspection = returnedKeyNodeInspection;
    }

    @Test
    @Transactional
    void createKeyNodeInspectionWithExistingId() throws Exception {
        // Create the KeyNodeInspection with an existing ID
        keyNodeInspection.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restKeyNodeInspectionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(keyNodeInspection)))
            .andExpect(status().isBadRequest());

        // Validate the KeyNodeInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllKeyNodeInspections() throws Exception {
        // Initialize the database
        insertedKeyNodeInspection = keyNodeInspectionRepository.saveAndFlush(keyNodeInspection);

        // Get all the keyNodeInspectionList
        restKeyNodeInspectionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(keyNodeInspection.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].workbagname").value(hasItem(DEFAULT_WORKBAGNAME)))
            .andExpect(jsonPath("$.[*].belongwbsid").value(hasItem(DEFAULT_BELONGWBSID)))
            .andExpect(jsonPath("$.[*].projectlevel").value(hasItem(DEFAULT_PROJECTLEVEL)))
            .andExpect(jsonPath("$.[*].iskey").value(hasItem(DEFAULT_ISKEY)))
            .andExpect(jsonPath("$.[*].isimplementationplan").value(hasItem(DEFAULT_ISIMPLEMENTATIONPLAN)))
            .andExpect(jsonPath("$.[*].isqualityplan").value(hasItem(DEFAULT_ISQUALITYPLAN)))
            .andExpect(jsonPath("$.[*].istechniqueplan").value(hasItem(DEFAULT_ISTECHNIQUEPLAN)))
            .andExpect(jsonPath("$.[*].implementationplanstatus").value(hasItem(DEFAULT_IMPLEMENTATIONPLANSTATUS)))
            .andExpect(jsonPath("$.[*].isimplementationplanmaterial").value(hasItem(DEFAULT_ISIMPLEMENTATIONPLANMATERIAL)))
            .andExpect(jsonPath("$.[*].technologyplanstatus").value(hasItem(DEFAULT_TECHNOLOGYPLANSTATUS)))
            .andExpect(jsonPath("$.[*].istechnologymaterial").value(hasItem(DEFAULT_ISTECHNOLOGYMATERIAL)))
            .andExpect(jsonPath("$.[*].firstcheckstatus").value(hasItem(DEFAULT_FIRSTCHECKSTATUS)))
            .andExpect(jsonPath("$.[*].isfirstcheckmaterial").value(hasItem(DEFAULT_ISFIRSTCHECKMATERIAL)))
            .andExpect(jsonPath("$.[*].productioncheckstatus").value(hasItem(DEFAULT_PRODUCTIONCHECKSTATUS)))
            .andExpect(jsonPath("$.[*].isproductioncheckmaterial").value(hasItem(DEFAULT_ISPRODUCTIONCHECKMATERIAL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    void getKeyNodeInspection() throws Exception {
        // Initialize the database
        insertedKeyNodeInspection = keyNodeInspectionRepository.saveAndFlush(keyNodeInspection);

        // Get the keyNodeInspection
        restKeyNodeInspectionMockMvc
            .perform(get(ENTITY_API_URL_ID, keyNodeInspection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(keyNodeInspection.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.workbagname").value(DEFAULT_WORKBAGNAME))
            .andExpect(jsonPath("$.belongwbsid").value(DEFAULT_BELONGWBSID))
            .andExpect(jsonPath("$.projectlevel").value(DEFAULT_PROJECTLEVEL))
            .andExpect(jsonPath("$.iskey").value(DEFAULT_ISKEY))
            .andExpect(jsonPath("$.isimplementationplan").value(DEFAULT_ISIMPLEMENTATIONPLAN))
            .andExpect(jsonPath("$.isqualityplan").value(DEFAULT_ISQUALITYPLAN))
            .andExpect(jsonPath("$.istechniqueplan").value(DEFAULT_ISTECHNIQUEPLAN))
            .andExpect(jsonPath("$.implementationplanstatus").value(DEFAULT_IMPLEMENTATIONPLANSTATUS))
            .andExpect(jsonPath("$.isimplementationplanmaterial").value(DEFAULT_ISIMPLEMENTATIONPLANMATERIAL))
            .andExpect(jsonPath("$.technologyplanstatus").value(DEFAULT_TECHNOLOGYPLANSTATUS))
            .andExpect(jsonPath("$.istechnologymaterial").value(DEFAULT_ISTECHNOLOGYMATERIAL))
            .andExpect(jsonPath("$.firstcheckstatus").value(DEFAULT_FIRSTCHECKSTATUS))
            .andExpect(jsonPath("$.isfirstcheckmaterial").value(DEFAULT_ISFIRSTCHECKMATERIAL))
            .andExpect(jsonPath("$.productioncheckstatus").value(DEFAULT_PRODUCTIONCHECKSTATUS))
            .andExpect(jsonPath("$.isproductioncheckmaterial").value(DEFAULT_ISPRODUCTIONCHECKMATERIAL))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingKeyNodeInspection() throws Exception {
        // Get the keyNodeInspection
        restKeyNodeInspectionMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingKeyNodeInspection() throws Exception {
        // Initialize the database
        insertedKeyNodeInspection = keyNodeInspectionRepository.saveAndFlush(keyNodeInspection);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the keyNodeInspection
        KeyNodeInspection updatedKeyNodeInspection = keyNodeInspectionRepository.findById(keyNodeInspection.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedKeyNodeInspection are not directly saved in db
        em.detach(updatedKeyNodeInspection);
        updatedKeyNodeInspection
            .name(UPDATED_NAME)
            .workbagid(UPDATED_WORKBAGID)
            .workbagname(UPDATED_WORKBAGNAME)
            .belongwbsid(UPDATED_BELONGWBSID)
            .projectlevel(UPDATED_PROJECTLEVEL)
            .iskey(UPDATED_ISKEY)
            .isimplementationplan(UPDATED_ISIMPLEMENTATIONPLAN)
            .isqualityplan(UPDATED_ISQUALITYPLAN)
            .istechniqueplan(UPDATED_ISTECHNIQUEPLAN)
            .implementationplanstatus(UPDATED_IMPLEMENTATIONPLANSTATUS)
            .isimplementationplanmaterial(UPDATED_ISIMPLEMENTATIONPLANMATERIAL)
            .technologyplanstatus(UPDATED_TECHNOLOGYPLANSTATUS)
            .istechnologymaterial(UPDATED_ISTECHNOLOGYMATERIAL)
            .firstcheckstatus(UPDATED_FIRSTCHECKSTATUS)
            .isfirstcheckmaterial(UPDATED_ISFIRSTCHECKMATERIAL)
            .productioncheckstatus(UPDATED_PRODUCTIONCHECKSTATUS)
            .isproductioncheckmaterial(UPDATED_ISPRODUCTIONCHECKMATERIAL)
            .status(UPDATED_STATUS);

        restKeyNodeInspectionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedKeyNodeInspection.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedKeyNodeInspection))
            )
            .andExpect(status().isOk());

        // Validate the KeyNodeInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedKeyNodeInspectionToMatchAllProperties(updatedKeyNodeInspection);
    }

    @Test
    @Transactional
    void putNonExistingKeyNodeInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        keyNodeInspection.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKeyNodeInspectionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, keyNodeInspection.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(keyNodeInspection))
            )
            .andExpect(status().isBadRequest());

        // Validate the KeyNodeInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchKeyNodeInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        keyNodeInspection.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKeyNodeInspectionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(keyNodeInspection))
            )
            .andExpect(status().isBadRequest());

        // Validate the KeyNodeInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamKeyNodeInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        keyNodeInspection.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKeyNodeInspectionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(keyNodeInspection)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the KeyNodeInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateKeyNodeInspectionWithPatch() throws Exception {
        // Initialize the database
        insertedKeyNodeInspection = keyNodeInspectionRepository.saveAndFlush(keyNodeInspection);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the keyNodeInspection using partial update
        KeyNodeInspection partialUpdatedKeyNodeInspection = new KeyNodeInspection();
        partialUpdatedKeyNodeInspection.setId(keyNodeInspection.getId());

        partialUpdatedKeyNodeInspection
            .workbagname(UPDATED_WORKBAGNAME)
            .belongwbsid(UPDATED_BELONGWBSID)
            .iskey(UPDATED_ISKEY)
            .isqualityplan(UPDATED_ISQUALITYPLAN)
            .istechnologymaterial(UPDATED_ISTECHNOLOGYMATERIAL)
            .firstcheckstatus(UPDATED_FIRSTCHECKSTATUS)
            .isfirstcheckmaterial(UPDATED_ISFIRSTCHECKMATERIAL)
            .productioncheckstatus(UPDATED_PRODUCTIONCHECKSTATUS)
            .isproductioncheckmaterial(UPDATED_ISPRODUCTIONCHECKMATERIAL);

        restKeyNodeInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKeyNodeInspection.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedKeyNodeInspection))
            )
            .andExpect(status().isOk());

        // Validate the KeyNodeInspection in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertKeyNodeInspectionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedKeyNodeInspection, keyNodeInspection),
            getPersistedKeyNodeInspection(keyNodeInspection)
        );
    }

    @Test
    @Transactional
    void fullUpdateKeyNodeInspectionWithPatch() throws Exception {
        // Initialize the database
        insertedKeyNodeInspection = keyNodeInspectionRepository.saveAndFlush(keyNodeInspection);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the keyNodeInspection using partial update
        KeyNodeInspection partialUpdatedKeyNodeInspection = new KeyNodeInspection();
        partialUpdatedKeyNodeInspection.setId(keyNodeInspection.getId());

        partialUpdatedKeyNodeInspection
            .name(UPDATED_NAME)
            .workbagid(UPDATED_WORKBAGID)
            .workbagname(UPDATED_WORKBAGNAME)
            .belongwbsid(UPDATED_BELONGWBSID)
            .projectlevel(UPDATED_PROJECTLEVEL)
            .iskey(UPDATED_ISKEY)
            .isimplementationplan(UPDATED_ISIMPLEMENTATIONPLAN)
            .isqualityplan(UPDATED_ISQUALITYPLAN)
            .istechniqueplan(UPDATED_ISTECHNIQUEPLAN)
            .implementationplanstatus(UPDATED_IMPLEMENTATIONPLANSTATUS)
            .isimplementationplanmaterial(UPDATED_ISIMPLEMENTATIONPLANMATERIAL)
            .technologyplanstatus(UPDATED_TECHNOLOGYPLANSTATUS)
            .istechnologymaterial(UPDATED_ISTECHNOLOGYMATERIAL)
            .firstcheckstatus(UPDATED_FIRSTCHECKSTATUS)
            .isfirstcheckmaterial(UPDATED_ISFIRSTCHECKMATERIAL)
            .productioncheckstatus(UPDATED_PRODUCTIONCHECKSTATUS)
            .isproductioncheckmaterial(UPDATED_ISPRODUCTIONCHECKMATERIAL)
            .status(UPDATED_STATUS);

        restKeyNodeInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKeyNodeInspection.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedKeyNodeInspection))
            )
            .andExpect(status().isOk());

        // Validate the KeyNodeInspection in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertKeyNodeInspectionUpdatableFieldsEquals(
            partialUpdatedKeyNodeInspection,
            getPersistedKeyNodeInspection(partialUpdatedKeyNodeInspection)
        );
    }

    @Test
    @Transactional
    void patchNonExistingKeyNodeInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        keyNodeInspection.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKeyNodeInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, keyNodeInspection.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(keyNodeInspection))
            )
            .andExpect(status().isBadRequest());

        // Validate the KeyNodeInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchKeyNodeInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        keyNodeInspection.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKeyNodeInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(keyNodeInspection))
            )
            .andExpect(status().isBadRequest());

        // Validate the KeyNodeInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamKeyNodeInspection() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        keyNodeInspection.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKeyNodeInspectionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(keyNodeInspection)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the KeyNodeInspection in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteKeyNodeInspection() throws Exception {
        // Initialize the database
        insertedKeyNodeInspection = keyNodeInspectionRepository.saveAndFlush(keyNodeInspection);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the keyNodeInspection
        restKeyNodeInspectionMockMvc
            .perform(delete(ENTITY_API_URL_ID, keyNodeInspection.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return keyNodeInspectionRepository.count();
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

    protected KeyNodeInspection getPersistedKeyNodeInspection(KeyNodeInspection keyNodeInspection) {
        return keyNodeInspectionRepository.findById(keyNodeInspection.getId()).orElseThrow();
    }

    protected void assertPersistedKeyNodeInspectionToMatchAllProperties(KeyNodeInspection expectedKeyNodeInspection) {
        assertKeyNodeInspectionAllPropertiesEquals(expectedKeyNodeInspection, getPersistedKeyNodeInspection(expectedKeyNodeInspection));
    }

    protected void assertPersistedKeyNodeInspectionToMatchUpdatableProperties(KeyNodeInspection expectedKeyNodeInspection) {
        assertKeyNodeInspectionAllUpdatablePropertiesEquals(
            expectedKeyNodeInspection,
            getPersistedKeyNodeInspection(expectedKeyNodeInspection)
        );
    }
}
