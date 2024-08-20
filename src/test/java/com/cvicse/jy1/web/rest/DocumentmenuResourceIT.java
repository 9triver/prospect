package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.DocumentmenuAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Documentmenu;
import com.cvicse.jy1.repository.DocumentmenuRepository;
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
 * Integration tests for the {@link DocumentmenuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DocumentmenuResourceIT {

    private static final String DEFAULT_MENUID = "AAAAAAAAAA";
    private static final String UPDATED_MENUID = "BBBBBBBBBB";

    private static final String DEFAULT_BELONGTYPE = "AAAAAAAAAA";
    private static final String UPDATED_BELONGTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MENUNAME = "AAAAAAAAAA";
    private static final String UPDATED_MENUNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARENTMENUID = "AAAAAAAAAA";
    private static final String UPDATED_PARENTMENUID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATORID = "AAAAAAAAAA";
    private static final String UPDATED_CREATORID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATORNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final Integer DEFAULT_FILENUM = 1;
    private static final Integer UPDATED_FILENUM = 2;

    private static final String DEFAULT_DEPARTMENTID = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENTID = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENTNAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILEURL = "AAAAAAAAAA";
    private static final String UPDATED_FILEURL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SPARE_1 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SPARE_1 = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_SPARE_2 = 1;
    private static final Integer UPDATED_SPARE_2 = 2;

    private static final String DEFAULT_SPARE_3 = "AAAAAAAAAA";
    private static final String UPDATED_SPARE_3 = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/documentmenus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DocumentmenuRepository documentmenuRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDocumentmenuMockMvc;

    private Documentmenu documentmenu;

    private Documentmenu insertedDocumentmenu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Documentmenu createEntity(EntityManager em) {
        Documentmenu documentmenu = new Documentmenu()
            .menuid(DEFAULT_MENUID)
            .belongtype(DEFAULT_BELONGTYPE)
            .menuname(DEFAULT_MENUNAME)
            .parentmenuid(DEFAULT_PARENTMENUID)
            .createtime(DEFAULT_CREATETIME)
            .creatorid(DEFAULT_CREATORID)
            .creatorname(DEFAULT_CREATORNAME)
            .type(DEFAULT_TYPE)
            .filenum(DEFAULT_FILENUM)
            .departmentid(DEFAULT_DEPARTMENTID)
            .departmentname(DEFAULT_DEPARTMENTNAME)
            .fileurl(DEFAULT_FILEURL)
            .spare1(DEFAULT_SPARE_1)
            .spare2(DEFAULT_SPARE_2)
            .spare3(DEFAULT_SPARE_3);
        return documentmenu;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Documentmenu createUpdatedEntity(EntityManager em) {
        Documentmenu documentmenu = new Documentmenu()
            .menuid(UPDATED_MENUID)
            .belongtype(UPDATED_BELONGTYPE)
            .menuname(UPDATED_MENUNAME)
            .parentmenuid(UPDATED_PARENTMENUID)
            .createtime(UPDATED_CREATETIME)
            .creatorid(UPDATED_CREATORID)
            .creatorname(UPDATED_CREATORNAME)
            .type(UPDATED_TYPE)
            .filenum(UPDATED_FILENUM)
            .departmentid(UPDATED_DEPARTMENTID)
            .departmentname(UPDATED_DEPARTMENTNAME)
            .spare1(UPDATED_SPARE_1)
            .spare2(UPDATED_SPARE_2)
            .spare3(UPDATED_SPARE_3);
        return documentmenu;
    }

    @BeforeEach
    public void initTest() {
        documentmenu = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDocumentmenu != null) {
            documentmenuRepository.delete(insertedDocumentmenu);
            insertedDocumentmenu = null;
        }
    }

    @Test
    @Transactional
    void createDocumentmenu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Documentmenu
        var returnedDocumentmenu = om.readValue(
            restDocumentmenuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(documentmenu)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Documentmenu.class
        );

        // Validate the Documentmenu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertDocumentmenuUpdatableFieldsEquals(returnedDocumentmenu, getPersistedDocumentmenu(returnedDocumentmenu));

        insertedDocumentmenu = returnedDocumentmenu;
    }

    @Test
    @Transactional
    void createDocumentmenuWithExistingId() throws Exception {
        // Create the Documentmenu with an existing ID
        documentmenu.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentmenuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(documentmenu)))
            .andExpect(status().isBadRequest());

        // Validate the Documentmenu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMenuidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        documentmenu.setMenuid(null);

        // Create the Documentmenu, which fails.

        restDocumentmenuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(documentmenu)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDocumentmenus() throws Exception {
        // Initialize the database
        insertedDocumentmenu = documentmenuRepository.saveAndFlush(documentmenu);

        // Get all the documentmenuList
        restDocumentmenuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documentmenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuid").value(hasItem(DEFAULT_MENUID)))
            .andExpect(jsonPath("$.[*].belongtype").value(hasItem(DEFAULT_BELONGTYPE)))
            .andExpect(jsonPath("$.[*].menuname").value(hasItem(DEFAULT_MENUNAME)))
            .andExpect(jsonPath("$.[*].parentmenuid").value(hasItem(DEFAULT_PARENTMENUID)))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].creatorid").value(hasItem(DEFAULT_CREATORID)))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].filenum").value(hasItem(DEFAULT_FILENUM)))
            .andExpect(jsonPath("$.[*].departmentid").value(hasItem(DEFAULT_DEPARTMENTID)))
            .andExpect(jsonPath("$.[*].departmentname").value(hasItem(DEFAULT_DEPARTMENTNAME)))
            .andExpect(jsonPath("$.[*].fileurl").value(hasItem(DEFAULT_FILEURL)))
            .andExpect(jsonPath("$.[*].spare1").value(hasItem(DEFAULT_SPARE_1.toString())))
            .andExpect(jsonPath("$.[*].spare2").value(hasItem(DEFAULT_SPARE_2)))
            .andExpect(jsonPath("$.[*].spare3").value(hasItem(DEFAULT_SPARE_3)));
    }

    @Test
    @Transactional
    void getDocumentmenu() throws Exception {
        // Initialize the database
        insertedDocumentmenu = documentmenuRepository.saveAndFlush(documentmenu);

        // Get the documentmenu
        restDocumentmenuMockMvc
            .perform(get(ENTITY_API_URL_ID, documentmenu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(documentmenu.getId().intValue()))
            .andExpect(jsonPath("$.menuid").value(DEFAULT_MENUID))
            .andExpect(jsonPath("$.belongtype").value(DEFAULT_BELONGTYPE))
            .andExpect(jsonPath("$.menuname").value(DEFAULT_MENUNAME))
            .andExpect(jsonPath("$.parentmenuid").value(DEFAULT_PARENTMENUID))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.creatorid").value(DEFAULT_CREATORID))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.filenum").value(DEFAULT_FILENUM))
            .andExpect(jsonPath("$.departmentid").value(DEFAULT_DEPARTMENTID))
            .andExpect(jsonPath("$.departmentname").value(DEFAULT_DEPARTMENTNAME))
            .andExpect(jsonPath("$.fileurl").value(DEFAULT_FILEURL))
            .andExpect(jsonPath("$.spare1").value(DEFAULT_SPARE_1.toString()))
            .andExpect(jsonPath("$.spare2").value(DEFAULT_SPARE_2))
            .andExpect(jsonPath("$.spare3").value(DEFAULT_SPARE_3));
    }

    @Test
    @Transactional
    void getNonExistingDocumentmenu() throws Exception {
        // Get the documentmenu
        restDocumentmenuMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDocumentmenu() throws Exception {
        // Initialize the database
        insertedDocumentmenu = documentmenuRepository.saveAndFlush(documentmenu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the documentmenu
        Documentmenu updatedDocumentmenu = documentmenuRepository.findById(documentmenu.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDocumentmenu are not directly saved in db
        em.detach(updatedDocumentmenu);
        updatedDocumentmenu
            .menuid(UPDATED_MENUID)
            .belongtype(UPDATED_BELONGTYPE)
            .menuname(UPDATED_MENUNAME)
            .parentmenuid(UPDATED_PARENTMENUID)
            .createtime(UPDATED_CREATETIME)
            .creatorid(UPDATED_CREATORID)
            .creatorname(UPDATED_CREATORNAME)
            .type(UPDATED_TYPE)
            .filenum(UPDATED_FILENUM)
            .departmentid(UPDATED_DEPARTMENTID)
            .departmentname(UPDATED_DEPARTMENTNAME)
            .fileurl(UPDATED_FILEURL)
            .spare1(UPDATED_SPARE_1)
            .spare2(UPDATED_SPARE_2)
            .spare3(UPDATED_SPARE_3);

        restDocumentmenuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDocumentmenu.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedDocumentmenu))
            )
            .andExpect(status().isOk());

        // Validate the Documentmenu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDocumentmenuToMatchAllProperties(updatedDocumentmenu);
    }

    @Test
    @Transactional
    void putNonExistingDocumentmenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentmenu.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentmenuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, documentmenu.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(documentmenu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documentmenu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDocumentmenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentmenu.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentmenuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(documentmenu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documentmenu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDocumentmenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentmenu.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentmenuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(documentmenu)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Documentmenu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDocumentmenuWithPatch() throws Exception {
        // Initialize the database
        insertedDocumentmenu = documentmenuRepository.saveAndFlush(documentmenu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the documentmenu using partial update
        Documentmenu partialUpdatedDocumentmenu = new Documentmenu();
        partialUpdatedDocumentmenu.setId(documentmenu.getId());

        partialUpdatedDocumentmenu
            .menuid(UPDATED_MENUID)
            .belongtype(UPDATED_BELONGTYPE)
            .menuname(UPDATED_MENUNAME)
            .parentmenuid(UPDATED_PARENTMENUID)
            .creatorname(UPDATED_CREATORNAME)
            .spare1(UPDATED_SPARE_1)
            .spare2(UPDATED_SPARE_2)
            .spare3(UPDATED_SPARE_3);

        restDocumentmenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentmenu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDocumentmenu))
            )
            .andExpect(status().isOk());

        // Validate the Documentmenu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDocumentmenuUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDocumentmenu, documentmenu),
            getPersistedDocumentmenu(documentmenu)
        );
    }

    @Test
    @Transactional
    void fullUpdateDocumentmenuWithPatch() throws Exception {
        // Initialize the database
        insertedDocumentmenu = documentmenuRepository.saveAndFlush(documentmenu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the documentmenu using partial update
        Documentmenu partialUpdatedDocumentmenu = new Documentmenu();
        partialUpdatedDocumentmenu.setId(documentmenu.getId());

        partialUpdatedDocumentmenu
            .menuid(UPDATED_MENUID)
            .belongtype(UPDATED_BELONGTYPE)
            .menuname(UPDATED_MENUNAME)
            .parentmenuid(UPDATED_PARENTMENUID)
            .createtime(UPDATED_CREATETIME)
            .creatorid(UPDATED_CREATORID)
            .creatorname(UPDATED_CREATORNAME)
            .type(UPDATED_TYPE)
            .filenum(UPDATED_FILENUM)
            .departmentid(UPDATED_DEPARTMENTID)
            .departmentname(UPDATED_DEPARTMENTNAME)
            .fileurl(UPDATED_FILEURL)
            .spare1(UPDATED_SPARE_1)
            .spare2(UPDATED_SPARE_2)
            .spare3(UPDATED_SPARE_3);

        restDocumentmenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentmenu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDocumentmenu))
            )
            .andExpect(status().isOk());

        // Validate the Documentmenu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDocumentmenuUpdatableFieldsEquals(partialUpdatedDocumentmenu, getPersistedDocumentmenu(partialUpdatedDocumentmenu));
    }

    @Test
    @Transactional
    void patchNonExistingDocumentmenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentmenu.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentmenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, documentmenu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(documentmenu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documentmenu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDocumentmenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentmenu.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentmenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(documentmenu))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documentmenu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDocumentmenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentmenu.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentmenuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(documentmenu)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Documentmenu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDocumentmenu() throws Exception {
        // Initialize the database
        insertedDocumentmenu = documentmenuRepository.saveAndFlush(documentmenu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the documentmenu
        restDocumentmenuMockMvc
            .perform(delete(ENTITY_API_URL_ID, documentmenu.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return documentmenuRepository.count();
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

    protected Documentmenu getPersistedDocumentmenu(Documentmenu documentmenu) {
        return documentmenuRepository.findById(documentmenu.getId()).orElseThrow();
    }

    protected void assertPersistedDocumentmenuToMatchAllProperties(Documentmenu expectedDocumentmenu) {
        assertDocumentmenuAllPropertiesEquals(expectedDocumentmenu, getPersistedDocumentmenu(expectedDocumentmenu));
    }

    protected void assertPersistedDocumentmenuToMatchUpdatableProperties(Documentmenu expectedDocumentmenu) {
        assertDocumentmenuAllUpdatablePropertiesEquals(expectedDocumentmenu, getPersistedDocumentmenu(expectedDocumentmenu));
    }
}
