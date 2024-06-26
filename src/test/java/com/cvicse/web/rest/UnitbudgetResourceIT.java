package com.cvicse.web.rest;

import static com.cvicse.domain.UnitbudgetAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Unitbudget;
import com.cvicse.repository.UnitbudgetRepository;
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
 * Integration tests for the {@link UnitbudgetResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UnitbudgetResourceIT {

    private static final String DEFAULT_SUBPROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBPROJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_UNITBUDGERNAME = "AAAAAAAAAA";
    private static final String UPDATED_UNITBUDGERNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_BILLINGUNIT = 1;
    private static final Integer UPDATED_BILLINGUNIT = 2;

    private static final BigDecimal DEFAULT_NUMBER = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUMBER = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTALBUDGET = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTALBUDGET = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAINTAINERBUDGET = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAINTAINERBUDGET = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OUTSOURCINGBUDGET = new BigDecimal(1);
    private static final BigDecimal UPDATED_OUTSOURCINGBUDGET = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EARMARKEDBUDGET = new BigDecimal(1);
    private static final BigDecimal UPDATED_EARMARKEDBUDGET = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TESTBUDGET = new BigDecimal(1);
    private static final BigDecimal UPDATED_TESTBUDGET = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/unitbudgets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UnitbudgetRepository unitbudgetRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUnitbudgetMockMvc;

    private Unitbudget unitbudget;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Unitbudget createEntity(EntityManager em) {
        Unitbudget unitbudget = new Unitbudget()
            .subprojectname(DEFAULT_SUBPROJECTNAME)
            .unitbudgername(DEFAULT_UNITBUDGERNAME)
            .billingunit(DEFAULT_BILLINGUNIT)
            .number(DEFAULT_NUMBER)
            .totalbudget(DEFAULT_TOTALBUDGET)
            .maintainerbudget(DEFAULT_MAINTAINERBUDGET)
            .outsourcingbudget(DEFAULT_OUTSOURCINGBUDGET)
            .earmarkedbudget(DEFAULT_EARMARKEDBUDGET)
            .testbudget(DEFAULT_TESTBUDGET);
        return unitbudget;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Unitbudget createUpdatedEntity(EntityManager em) {
        Unitbudget unitbudget = new Unitbudget()
            .subprojectname(UPDATED_SUBPROJECTNAME)
            .unitbudgername(UPDATED_UNITBUDGERNAME)
            .billingunit(UPDATED_BILLINGUNIT)
            .number(UPDATED_NUMBER)
            .totalbudget(UPDATED_TOTALBUDGET)
            .maintainerbudget(UPDATED_MAINTAINERBUDGET)
            .outsourcingbudget(UPDATED_OUTSOURCINGBUDGET)
            .earmarkedbudget(UPDATED_EARMARKEDBUDGET)
            .testbudget(UPDATED_TESTBUDGET);
        return unitbudget;
    }

    @BeforeEach
    public void initTest() {
        unitbudget = createEntity(em);
    }

    @Test
    @Transactional
    void createUnitbudget() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Unitbudget
        var returnedUnitbudget = om.readValue(
            restUnitbudgetMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(unitbudget)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Unitbudget.class
        );

        // Validate the Unitbudget in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertUnitbudgetUpdatableFieldsEquals(returnedUnitbudget, getPersistedUnitbudget(returnedUnitbudget));
    }

    @Test
    @Transactional
    void createUnitbudgetWithExistingId() throws Exception {
        // Create the Unitbudget with an existing ID
        unitbudget.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnitbudgetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(unitbudget)))
            .andExpect(status().isBadRequest());

        // Validate the Unitbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUnitbudgets() throws Exception {
        // Initialize the database
        unitbudgetRepository.saveAndFlush(unitbudget);

        // Get all the unitbudgetList
        restUnitbudgetMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unitbudget.getId())))
            .andExpect(jsonPath("$.[*].subprojectname").value(hasItem(DEFAULT_SUBPROJECTNAME)))
            .andExpect(jsonPath("$.[*].unitbudgername").value(hasItem(DEFAULT_UNITBUDGERNAME)))
            .andExpect(jsonPath("$.[*].billingunit").value(hasItem(DEFAULT_BILLINGUNIT)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(sameNumber(DEFAULT_NUMBER))))
            .andExpect(jsonPath("$.[*].totalbudget").value(hasItem(sameNumber(DEFAULT_TOTALBUDGET))))
            .andExpect(jsonPath("$.[*].maintainerbudget").value(hasItem(sameNumber(DEFAULT_MAINTAINERBUDGET))))
            .andExpect(jsonPath("$.[*].outsourcingbudget").value(hasItem(sameNumber(DEFAULT_OUTSOURCINGBUDGET))))
            .andExpect(jsonPath("$.[*].earmarkedbudget").value(hasItem(sameNumber(DEFAULT_EARMARKEDBUDGET))))
            .andExpect(jsonPath("$.[*].testbudget").value(hasItem(sameNumber(DEFAULT_TESTBUDGET))));
    }

    @Test
    @Transactional
    void getUnitbudget() throws Exception {
        // Initialize the database
        unitbudgetRepository.saveAndFlush(unitbudget);

        // Get the unitbudget
        restUnitbudgetMockMvc
            .perform(get(ENTITY_API_URL_ID, unitbudget.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unitbudget.getId()))
            .andExpect(jsonPath("$.subprojectname").value(DEFAULT_SUBPROJECTNAME))
            .andExpect(jsonPath("$.unitbudgername").value(DEFAULT_UNITBUDGERNAME))
            .andExpect(jsonPath("$.billingunit").value(DEFAULT_BILLINGUNIT))
            .andExpect(jsonPath("$.number").value(sameNumber(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.totalbudget").value(sameNumber(DEFAULT_TOTALBUDGET)))
            .andExpect(jsonPath("$.maintainerbudget").value(sameNumber(DEFAULT_MAINTAINERBUDGET)))
            .andExpect(jsonPath("$.outsourcingbudget").value(sameNumber(DEFAULT_OUTSOURCINGBUDGET)))
            .andExpect(jsonPath("$.earmarkedbudget").value(sameNumber(DEFAULT_EARMARKEDBUDGET)))
            .andExpect(jsonPath("$.testbudget").value(sameNumber(DEFAULT_TESTBUDGET)));
    }

    @Test
    @Transactional
    void getNonExistingUnitbudget() throws Exception {
        // Get the unitbudget
        restUnitbudgetMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUnitbudget() throws Exception {
        // Initialize the database
        unitbudgetRepository.saveAndFlush(unitbudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the unitbudget
        Unitbudget updatedUnitbudget = unitbudgetRepository.findById(unitbudget.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedUnitbudget are not directly saved in db
        em.detach(updatedUnitbudget);
        updatedUnitbudget
            .subprojectname(UPDATED_SUBPROJECTNAME)
            .unitbudgername(UPDATED_UNITBUDGERNAME)
            .billingunit(UPDATED_BILLINGUNIT)
            .number(UPDATED_NUMBER)
            .totalbudget(UPDATED_TOTALBUDGET)
            .maintainerbudget(UPDATED_MAINTAINERBUDGET)
            .outsourcingbudget(UPDATED_OUTSOURCINGBUDGET)
            .earmarkedbudget(UPDATED_EARMARKEDBUDGET)
            .testbudget(UPDATED_TESTBUDGET);

        restUnitbudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUnitbudget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedUnitbudget))
            )
            .andExpect(status().isOk());

        // Validate the Unitbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedUnitbudgetToMatchAllProperties(updatedUnitbudget);
    }

    @Test
    @Transactional
    void putNonExistingUnitbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unitbudget.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnitbudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, unitbudget.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(unitbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unitbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUnitbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unitbudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnitbudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(unitbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unitbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUnitbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unitbudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnitbudgetMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(unitbudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Unitbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUnitbudgetWithPatch() throws Exception {
        // Initialize the database
        unitbudgetRepository.saveAndFlush(unitbudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the unitbudget using partial update
        Unitbudget partialUpdatedUnitbudget = new Unitbudget();
        partialUpdatedUnitbudget.setId(unitbudget.getId());

        partialUpdatedUnitbudget
            .subprojectname(UPDATED_SUBPROJECTNAME)
            .unitbudgername(UPDATED_UNITBUDGERNAME)
            .number(UPDATED_NUMBER)
            .earmarkedbudget(UPDATED_EARMARKEDBUDGET)
            .testbudget(UPDATED_TESTBUDGET);

        restUnitbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUnitbudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUnitbudget))
            )
            .andExpect(status().isOk());

        // Validate the Unitbudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUnitbudgetUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedUnitbudget, unitbudget),
            getPersistedUnitbudget(unitbudget)
        );
    }

    @Test
    @Transactional
    void fullUpdateUnitbudgetWithPatch() throws Exception {
        // Initialize the database
        unitbudgetRepository.saveAndFlush(unitbudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the unitbudget using partial update
        Unitbudget partialUpdatedUnitbudget = new Unitbudget();
        partialUpdatedUnitbudget.setId(unitbudget.getId());

        partialUpdatedUnitbudget
            .subprojectname(UPDATED_SUBPROJECTNAME)
            .unitbudgername(UPDATED_UNITBUDGERNAME)
            .billingunit(UPDATED_BILLINGUNIT)
            .number(UPDATED_NUMBER)
            .totalbudget(UPDATED_TOTALBUDGET)
            .maintainerbudget(UPDATED_MAINTAINERBUDGET)
            .outsourcingbudget(UPDATED_OUTSOURCINGBUDGET)
            .earmarkedbudget(UPDATED_EARMARKEDBUDGET)
            .testbudget(UPDATED_TESTBUDGET);

        restUnitbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUnitbudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUnitbudget))
            )
            .andExpect(status().isOk());

        // Validate the Unitbudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUnitbudgetUpdatableFieldsEquals(partialUpdatedUnitbudget, getPersistedUnitbudget(partialUpdatedUnitbudget));
    }

    @Test
    @Transactional
    void patchNonExistingUnitbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unitbudget.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnitbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, unitbudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(unitbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unitbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUnitbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unitbudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnitbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(unitbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unitbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUnitbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unitbudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnitbudgetMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(unitbudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Unitbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUnitbudget() throws Exception {
        // Initialize the database
        unitbudgetRepository.saveAndFlush(unitbudget);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the unitbudget
        restUnitbudgetMockMvc
            .perform(delete(ENTITY_API_URL_ID, unitbudget.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return unitbudgetRepository.count();
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

    protected Unitbudget getPersistedUnitbudget(Unitbudget unitbudget) {
        return unitbudgetRepository.findById(unitbudget.getId()).orElseThrow();
    }

    protected void assertPersistedUnitbudgetToMatchAllProperties(Unitbudget expectedUnitbudget) {
        assertUnitbudgetAllPropertiesEquals(expectedUnitbudget, getPersistedUnitbudget(expectedUnitbudget));
    }

    protected void assertPersistedUnitbudgetToMatchUpdatableProperties(Unitbudget expectedUnitbudget) {
        assertUnitbudgetAllUpdatablePropertiesEquals(expectedUnitbudget, getPersistedUnitbudget(expectedUnitbudget));
    }
}
