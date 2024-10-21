package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ProjectBudgetAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.ProjectBudget;
import com.cvicse.jy1.repository.ProjectBudgetRepository;
import com.cvicse.jy1.service.ProjectBudgetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link ProjectBudgetResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ProjectBudgetResourceIT {

    private static final String DEFAULT_WBSID = "AAAAAAAAAA";
    private static final String UPDATED_WBSID = "BBBBBBBBBB";

    private static final String DEFAULT_WBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_WBSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARENTWBSID = "AAAAAAAAAA";
    private static final String UPDATED_PARENTWBSID = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUBJECTID = 1;
    private static final Integer UPDATED_SUBJECTID = 2;

    private static final String DEFAULT_SUBJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTNAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    private static final String DEFAULT_AUXILIARYITEM = "AAAAAAAAAA";
    private static final String UPDATED_AUXILIARYITEM = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_UNITPRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNITPRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BUDGETAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUDGETAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ESTIMATEDAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ESTIMATEDAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_IMPLEMENTEDAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_IMPLEMENTEDAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DIFFERENCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DIFFERENCE = new BigDecimal(2);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/project-budgets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProjectBudgetRepository projectBudgetRepository;

    @Mock
    private ProjectBudgetRepository projectBudgetRepositoryMock;

    @Mock
    private ProjectBudgetService projectBudgetServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectBudgetMockMvc;

    private ProjectBudget projectBudget;

    private ProjectBudget insertedProjectBudget;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectBudget createEntity(EntityManager em) {
        ProjectBudget projectBudget = new ProjectBudget()
            .wbsid(DEFAULT_WBSID)
            .wbsname(DEFAULT_WBSNAME)
            .parentwbsid(DEFAULT_PARENTWBSID)
            .subjectid(DEFAULT_SUBJECTID)
            .subjectname(DEFAULT_SUBJECTNAME)
            .contractid(DEFAULT_CONTRACTID)
            .contractname(DEFAULT_CONTRACTNAME)
            .year(DEFAULT_YEAR)
            .auxiliaryitem(DEFAULT_AUXILIARYITEM)
            .unit(DEFAULT_UNIT)
            .number(DEFAULT_NUMBER)
            .unitprice(DEFAULT_UNITPRICE)
            .budgetamount(DEFAULT_BUDGETAMOUNT)
            .estimatedamount(DEFAULT_ESTIMATEDAMOUNT)
            .implementedamount(DEFAULT_IMPLEMENTEDAMOUNT)
            .difference(DEFAULT_DIFFERENCE)
            .remark(DEFAULT_REMARK);
        return projectBudget;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectBudget createUpdatedEntity(EntityManager em) {
        ProjectBudget projectBudget = new ProjectBudget()
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .parentwbsid(UPDATED_PARENTWBSID)
            .subjectid(UPDATED_SUBJECTID)
            .subjectname(UPDATED_SUBJECTNAME)
            .contractid(UPDATED_CONTRACTID)
            .contractname(UPDATED_CONTRACTNAME)
            .year(UPDATED_YEAR)
            .auxiliaryitem(UPDATED_AUXILIARYITEM)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .estimatedamount(UPDATED_ESTIMATEDAMOUNT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .difference(UPDATED_DIFFERENCE)
            .remark(UPDATED_REMARK);
        return projectBudget;
    }

    @BeforeEach
    public void initTest() {
        projectBudget = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedProjectBudget != null) {
            projectBudgetRepository.delete(insertedProjectBudget);
            insertedProjectBudget = null;
        }
    }

    @Test
    @Transactional
    void createProjectBudget() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ProjectBudget
        var returnedProjectBudget = om.readValue(
            restProjectBudgetMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectBudget)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ProjectBudget.class
        );

        // Validate the ProjectBudget in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProjectBudgetUpdatableFieldsEquals(returnedProjectBudget, getPersistedProjectBudget(returnedProjectBudget));

        insertedProjectBudget = returnedProjectBudget;
    }

    @Test
    @Transactional
    void createProjectBudgetWithExistingId() throws Exception {
        // Create the ProjectBudget with an existing ID
        projectBudget.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectBudgetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectBudget)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWbsidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        projectBudget.setWbsid(null);

        // Create the ProjectBudget, which fails.

        restProjectBudgetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectBudget)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkContractidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        projectBudget.setContractid(null);

        // Create the ProjectBudget, which fails.

        restProjectBudgetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectBudget)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllProjectBudgets() throws Exception {
        // Initialize the database
        insertedProjectBudget = projectBudgetRepository.saveAndFlush(projectBudget);

        // Get all the projectBudgetList
        restProjectBudgetMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectBudget.getId().intValue())))
            .andExpect(jsonPath("$.[*].wbsid").value(hasItem(DEFAULT_WBSID)))
            .andExpect(jsonPath("$.[*].wbsname").value(hasItem(DEFAULT_WBSNAME)))
            .andExpect(jsonPath("$.[*].parentwbsid").value(hasItem(DEFAULT_PARENTWBSID)))
            .andExpect(jsonPath("$.[*].subjectid").value(hasItem(DEFAULT_SUBJECTID)))
            .andExpect(jsonPath("$.[*].subjectname").value(hasItem(DEFAULT_SUBJECTNAME)))
            .andExpect(jsonPath("$.[*].contractid").value(hasItem(DEFAULT_CONTRACTID)))
            .andExpect(jsonPath("$.[*].contractname").value(hasItem(DEFAULT_CONTRACTNAME)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].auxiliaryitem").value(hasItem(DEFAULT_AUXILIARYITEM)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].unitprice").value(hasItem(sameNumber(DEFAULT_UNITPRICE))))
            .andExpect(jsonPath("$.[*].budgetamount").value(hasItem(sameNumber(DEFAULT_BUDGETAMOUNT))))
            .andExpect(jsonPath("$.[*].estimatedamount").value(hasItem(sameNumber(DEFAULT_ESTIMATEDAMOUNT))))
            .andExpect(jsonPath("$.[*].implementedamount").value(hasItem(sameNumber(DEFAULT_IMPLEMENTEDAMOUNT))))
            .andExpect(jsonPath("$.[*].difference").value(hasItem(sameNumber(DEFAULT_DIFFERENCE))))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProjectBudgetsWithEagerRelationshipsIsEnabled() throws Exception {
        when(projectBudgetServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProjectBudgetMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(projectBudgetServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProjectBudgetsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(projectBudgetServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProjectBudgetMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(projectBudgetRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getProjectBudget() throws Exception {
        // Initialize the database
        insertedProjectBudget = projectBudgetRepository.saveAndFlush(projectBudget);

        // Get the projectBudget
        restProjectBudgetMockMvc
            .perform(get(ENTITY_API_URL_ID, projectBudget.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectBudget.getId().intValue()))
            .andExpect(jsonPath("$.wbsid").value(DEFAULT_WBSID))
            .andExpect(jsonPath("$.wbsname").value(DEFAULT_WBSNAME))
            .andExpect(jsonPath("$.parentwbsid").value(DEFAULT_PARENTWBSID))
            .andExpect(jsonPath("$.subjectid").value(DEFAULT_SUBJECTID))
            .andExpect(jsonPath("$.subjectname").value(DEFAULT_SUBJECTNAME))
            .andExpect(jsonPath("$.contractid").value(DEFAULT_CONTRACTID))
            .andExpect(jsonPath("$.contractname").value(DEFAULT_CONTRACTNAME))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.auxiliaryitem").value(DEFAULT_AUXILIARYITEM))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.unitprice").value(sameNumber(DEFAULT_UNITPRICE)))
            .andExpect(jsonPath("$.budgetamount").value(sameNumber(DEFAULT_BUDGETAMOUNT)))
            .andExpect(jsonPath("$.estimatedamount").value(sameNumber(DEFAULT_ESTIMATEDAMOUNT)))
            .andExpect(jsonPath("$.implementedamount").value(sameNumber(DEFAULT_IMPLEMENTEDAMOUNT)))
            .andExpect(jsonPath("$.difference").value(sameNumber(DEFAULT_DIFFERENCE)))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK));
    }

    @Test
    @Transactional
    void getNonExistingProjectBudget() throws Exception {
        // Get the projectBudget
        restProjectBudgetMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProjectBudget() throws Exception {
        // Initialize the database
        insertedProjectBudget = projectBudgetRepository.saveAndFlush(projectBudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectBudget
        ProjectBudget updatedProjectBudget = projectBudgetRepository.findById(projectBudget.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProjectBudget are not directly saved in db
        em.detach(updatedProjectBudget);
        updatedProjectBudget
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .parentwbsid(UPDATED_PARENTWBSID)
            .subjectid(UPDATED_SUBJECTID)
            .subjectname(UPDATED_SUBJECTNAME)
            .contractid(UPDATED_CONTRACTID)
            .contractname(UPDATED_CONTRACTNAME)
            .year(UPDATED_YEAR)
            .auxiliaryitem(UPDATED_AUXILIARYITEM)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .estimatedamount(UPDATED_ESTIMATEDAMOUNT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .difference(UPDATED_DIFFERENCE)
            .remark(UPDATED_REMARK);

        restProjectBudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProjectBudget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProjectBudget))
            )
            .andExpect(status().isOk());

        // Validate the ProjectBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProjectBudgetToMatchAllProperties(updatedProjectBudget);
    }

    @Test
    @Transactional
    void putNonExistingProjectBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectBudget.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectBudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectBudget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProjectBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectBudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectBudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProjectBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectBudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectBudgetMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectBudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProjectBudgetWithPatch() throws Exception {
        // Initialize the database
        insertedProjectBudget = projectBudgetRepository.saveAndFlush(projectBudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectBudget using partial update
        ProjectBudget partialUpdatedProjectBudget = new ProjectBudget();
        partialUpdatedProjectBudget.setId(projectBudget.getId());

        partialUpdatedProjectBudget
            .wbsname(UPDATED_WBSNAME)
            .parentwbsid(UPDATED_PARENTWBSID)
            .subjectname(UPDATED_SUBJECTNAME)
            .contractname(UPDATED_CONTRACTNAME)
            .auxiliaryitem(UPDATED_AUXILIARYITEM)
            .unitprice(UPDATED_UNITPRICE)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .estimatedamount(UPDATED_ESTIMATEDAMOUNT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .difference(UPDATED_DIFFERENCE);

        restProjectBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectBudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectBudget))
            )
            .andExpect(status().isOk());

        // Validate the ProjectBudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectBudgetUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProjectBudget, projectBudget),
            getPersistedProjectBudget(projectBudget)
        );
    }

    @Test
    @Transactional
    void fullUpdateProjectBudgetWithPatch() throws Exception {
        // Initialize the database
        insertedProjectBudget = projectBudgetRepository.saveAndFlush(projectBudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectBudget using partial update
        ProjectBudget partialUpdatedProjectBudget = new ProjectBudget();
        partialUpdatedProjectBudget.setId(projectBudget.getId());

        partialUpdatedProjectBudget
            .wbsid(UPDATED_WBSID)
            .wbsname(UPDATED_WBSNAME)
            .parentwbsid(UPDATED_PARENTWBSID)
            .subjectid(UPDATED_SUBJECTID)
            .subjectname(UPDATED_SUBJECTNAME)
            .contractid(UPDATED_CONTRACTID)
            .contractname(UPDATED_CONTRACTNAME)
            .year(UPDATED_YEAR)
            .auxiliaryitem(UPDATED_AUXILIARYITEM)
            .unit(UPDATED_UNIT)
            .number(UPDATED_NUMBER)
            .unitprice(UPDATED_UNITPRICE)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .estimatedamount(UPDATED_ESTIMATEDAMOUNT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .difference(UPDATED_DIFFERENCE)
            .remark(UPDATED_REMARK);

        restProjectBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectBudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectBudget))
            )
            .andExpect(status().isOk());

        // Validate the ProjectBudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectBudgetUpdatableFieldsEquals(partialUpdatedProjectBudget, getPersistedProjectBudget(partialUpdatedProjectBudget));
    }

    @Test
    @Transactional
    void patchNonExistingProjectBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectBudget.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, projectBudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProjectBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectBudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProjectBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectBudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectBudgetMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(projectBudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProjectBudget() throws Exception {
        // Initialize the database
        insertedProjectBudget = projectBudgetRepository.saveAndFlush(projectBudget);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the projectBudget
        restProjectBudgetMockMvc
            .perform(delete(ENTITY_API_URL_ID, projectBudget.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return projectBudgetRepository.count();
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

    protected ProjectBudget getPersistedProjectBudget(ProjectBudget projectBudget) {
        return projectBudgetRepository.findById(projectBudget.getId()).orElseThrow();
    }

    protected void assertPersistedProjectBudgetToMatchAllProperties(ProjectBudget expectedProjectBudget) {
        assertProjectBudgetAllPropertiesEquals(expectedProjectBudget, getPersistedProjectBudget(expectedProjectBudget));
    }

    protected void assertPersistedProjectBudgetToMatchUpdatableProperties(ProjectBudget expectedProjectBudget) {
        assertProjectBudgetAllUpdatablePropertiesEquals(expectedProjectBudget, getPersistedProjectBudget(expectedProjectBudget));
    }
}
