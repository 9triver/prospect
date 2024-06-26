package com.cvicse.web.rest;

import static com.cvicse.domain.ComprehensiveledgerAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Comprehensiveledger;
import com.cvicse.repository.ComprehensiveledgerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ComprehensiveledgerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ComprehensiveledgerResourceIT {

    private static final String DEFAULT_FUNDSNAME = "AAAAAAAAAA";
    private static final String UPDATED_FUNDSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_WBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_WBSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_UNITNAME = "AAAAAAAAAA";
    private static final String UPDATED_UNITNAME = "BBBBBBBBBB";

    private static final String DEFAULT_BUDGETSECTION = "AAAAAAAAAA";
    private static final String UPDATED_BUDGETSECTION = "BBBBBBBBBB";

    private static final String DEFAULT_PURPOSE = "AAAAAAAAAA";
    private static final String UPDATED_PURPOSE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final BigDecimal DEFAULT_UNITPRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNITPRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_FOREIGNEXCHANGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_FOREIGNEXCHANGE = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/comprehensiveledgers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ComprehensiveledgerRepository comprehensiveledgerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restComprehensiveledgerMockMvc;

    private Comprehensiveledger comprehensiveledger;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comprehensiveledger createEntity(EntityManager em) {
        Comprehensiveledger comprehensiveledger = new Comprehensiveledger()
            .fundsname(DEFAULT_FUNDSNAME)
            .wbsname(DEFAULT_WBSNAME)
            .unitname(DEFAULT_UNITNAME)
            .budgetsection(DEFAULT_BUDGETSECTION)
            .purpose(DEFAULT_PURPOSE)
            .unit(DEFAULT_UNIT)
            .number(DEFAULT_NUMBER)
            .unitprice(DEFAULT_UNITPRICE)
            .foreignexchange(DEFAULT_FOREIGNEXCHANGE);
        return comprehensiveledger;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comprehensiveledger createUpdatedEntity(EntityManager em) {
        Comprehensiveledger comprehensiveledger = new Comprehensiveledger()
            .fundsname(UPDATED_FUNDSNAME)
            .wbsname(UPDATED_WBSNAME)
            .unitname(UPDATED_UNITNAME)
            .budgetsection(UPDATED_BUDGETSECTION)
            .purpose(UPDATED_PURPOSE)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .foreignexchange(UPDATED_FOREIGNEXCHANGE);
        return comprehensiveledger;
    }

    @BeforeEach
    public void initTest() {
        comprehensiveledger = createEntity(em);
    }

    @Test
    @Transactional
    void createComprehensiveledger() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Comprehensiveledger
        var returnedComprehensiveledger = om.readValue(
            restComprehensiveledgerMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(comprehensiveledger)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Comprehensiveledger.class
        );

        // Validate the Comprehensiveledger in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertComprehensiveledgerUpdatableFieldsEquals(
            returnedComprehensiveledger,
            getPersistedComprehensiveledger(returnedComprehensiveledger)
        );
    }

    @Test
    @Transactional
    void createComprehensiveledgerWithExistingId() throws Exception {
        // Create the Comprehensiveledger with an existing ID
        comprehensiveledger.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restComprehensiveledgerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(comprehensiveledger)))
            .andExpect(status().isBadRequest());

        // Validate the Comprehensiveledger in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllComprehensiveledgers() throws Exception {
        // Initialize the database
        comprehensiveledgerRepository.saveAndFlush(comprehensiveledger);

        // Get all the comprehensiveledgerList
        restComprehensiveledgerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comprehensiveledger.getId())))
            .andExpect(jsonPath("$.[*].fundsname").value(hasItem(DEFAULT_FUNDSNAME)))
            .andExpect(jsonPath("$.[*].wbsname").value(hasItem(DEFAULT_WBSNAME)))
            .andExpect(jsonPath("$.[*].unitname").value(hasItem(DEFAULT_UNITNAME)))
            .andExpect(jsonPath("$.[*].budgetsection").value(hasItem(DEFAULT_BUDGETSECTION)))
            .andExpect(jsonPath("$.[*].purpose").value(hasItem(DEFAULT_PURPOSE)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].unitprice").value(hasItem(sameNumber(DEFAULT_UNITPRICE))))
            .andExpect(jsonPath("$.[*].foreignexchange").value(hasItem(sameNumber(DEFAULT_FOREIGNEXCHANGE))));
    }

    @Test
    @Transactional
    void getComprehensiveledger() throws Exception {
        // Initialize the database
        comprehensiveledgerRepository.saveAndFlush(comprehensiveledger);

        // Get the comprehensiveledger
        restComprehensiveledgerMockMvc
            .perform(get(ENTITY_API_URL_ID, comprehensiveledger.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(comprehensiveledger.getId()))
            .andExpect(jsonPath("$.fundsname").value(DEFAULT_FUNDSNAME))
            .andExpect(jsonPath("$.wbsname").value(DEFAULT_WBSNAME))
            .andExpect(jsonPath("$.unitname").value(DEFAULT_UNITNAME))
            .andExpect(jsonPath("$.budgetsection").value(DEFAULT_BUDGETSECTION))
            .andExpect(jsonPath("$.purpose").value(DEFAULT_PURPOSE))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.unitprice").value(sameNumber(DEFAULT_UNITPRICE)))
            .andExpect(jsonPath("$.foreignexchange").value(sameNumber(DEFAULT_FOREIGNEXCHANGE)));
    }

    @Test
    @Transactional
    void getNonExistingComprehensiveledger() throws Exception {
        // Get the comprehensiveledger
        restComprehensiveledgerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingComprehensiveledger() throws Exception {
        // Initialize the database
        comprehensiveledgerRepository.saveAndFlush(comprehensiveledger);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the comprehensiveledger
        Comprehensiveledger updatedComprehensiveledger = comprehensiveledgerRepository.findById(comprehensiveledger.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedComprehensiveledger are not directly saved in db
        em.detach(updatedComprehensiveledger);
        updatedComprehensiveledger
            .fundsname(UPDATED_FUNDSNAME)
            .wbsname(UPDATED_WBSNAME)
            .unitname(UPDATED_UNITNAME)
            .budgetsection(UPDATED_BUDGETSECTION)
            .purpose(UPDATED_PURPOSE)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .foreignexchange(UPDATED_FOREIGNEXCHANGE);

        restComprehensiveledgerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedComprehensiveledger.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedComprehensiveledger))
            )
            .andExpect(status().isOk());

        // Validate the Comprehensiveledger in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedComprehensiveledgerToMatchAllProperties(updatedComprehensiveledger);
    }

    @Test
    @Transactional
    void putNonExistingComprehensiveledger() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensiveledger.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComprehensiveledgerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, comprehensiveledger.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(comprehensiveledger))
            )
            .andExpect(status().isBadRequest());

        // Validate the Comprehensiveledger in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchComprehensiveledger() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensiveledger.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComprehensiveledgerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(comprehensiveledger))
            )
            .andExpect(status().isBadRequest());

        // Validate the Comprehensiveledger in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamComprehensiveledger() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensiveledger.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComprehensiveledgerMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(comprehensiveledger)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Comprehensiveledger in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateComprehensiveledgerWithPatch() throws Exception {
        // Initialize the database
        comprehensiveledgerRepository.saveAndFlush(comprehensiveledger);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the comprehensiveledger using partial update
        Comprehensiveledger partialUpdatedComprehensiveledger = new Comprehensiveledger();
        partialUpdatedComprehensiveledger.setId(comprehensiveledger.getId());

        partialUpdatedComprehensiveledger.budgetsection(UPDATED_BUDGETSECTION).purpose(UPDATED_PURPOSE).unit(UPDATED_UNIT);

        restComprehensiveledgerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedComprehensiveledger.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedComprehensiveledger))
            )
            .andExpect(status().isOk());

        // Validate the Comprehensiveledger in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertComprehensiveledgerUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedComprehensiveledger, comprehensiveledger),
            getPersistedComprehensiveledger(comprehensiveledger)
        );
    }

    @Test
    @Transactional
    void fullUpdateComprehensiveledgerWithPatch() throws Exception {
        // Initialize the database
        comprehensiveledgerRepository.saveAndFlush(comprehensiveledger);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the comprehensiveledger using partial update
        Comprehensiveledger partialUpdatedComprehensiveledger = new Comprehensiveledger();
        partialUpdatedComprehensiveledger.setId(comprehensiveledger.getId());

        partialUpdatedComprehensiveledger
            .fundsname(UPDATED_FUNDSNAME)
            .wbsname(UPDATED_WBSNAME)
            .unitname(UPDATED_UNITNAME)
            .budgetsection(UPDATED_BUDGETSECTION)
            .purpose(UPDATED_PURPOSE)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .foreignexchange(UPDATED_FOREIGNEXCHANGE);

        restComprehensiveledgerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedComprehensiveledger.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedComprehensiveledger))
            )
            .andExpect(status().isOk());

        // Validate the Comprehensiveledger in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertComprehensiveledgerUpdatableFieldsEquals(
            partialUpdatedComprehensiveledger,
            getPersistedComprehensiveledger(partialUpdatedComprehensiveledger)
        );
    }

    @Test
    @Transactional
    void patchNonExistingComprehensiveledger() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensiveledger.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComprehensiveledgerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, comprehensiveledger.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(comprehensiveledger))
            )
            .andExpect(status().isBadRequest());

        // Validate the Comprehensiveledger in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchComprehensiveledger() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensiveledger.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComprehensiveledgerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(comprehensiveledger))
            )
            .andExpect(status().isBadRequest());

        // Validate the Comprehensiveledger in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamComprehensiveledger() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensiveledger.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComprehensiveledgerMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(comprehensiveledger)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Comprehensiveledger in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteComprehensiveledger() throws Exception {
        // Initialize the database
        comprehensiveledgerRepository.saveAndFlush(comprehensiveledger);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the comprehensiveledger
        restComprehensiveledgerMockMvc
            .perform(delete(ENTITY_API_URL_ID, comprehensiveledger.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return comprehensiveledgerRepository.count();
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

    protected Comprehensiveledger getPersistedComprehensiveledger(Comprehensiveledger comprehensiveledger) {
        return comprehensiveledgerRepository.findById(comprehensiveledger.getId()).orElseThrow();
    }

    protected void assertPersistedComprehensiveledgerToMatchAllProperties(Comprehensiveledger expectedComprehensiveledger) {
        assertComprehensiveledgerAllPropertiesEquals(
            expectedComprehensiveledger,
            getPersistedComprehensiveledger(expectedComprehensiveledger)
        );
    }

    protected void assertPersistedComprehensiveledgerToMatchUpdatableProperties(Comprehensiveledger expectedComprehensiveledger) {
        assertComprehensiveledgerAllUpdatablePropertiesEquals(
            expectedComprehensiveledger,
            getPersistedComprehensiveledger(expectedComprehensiveledger)
        );
    }
}
