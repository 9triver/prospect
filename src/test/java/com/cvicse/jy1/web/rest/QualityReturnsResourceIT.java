package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.QualityReturnsAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.QualityReturns;
import com.cvicse.jy1.repository.QualityReturnsRepository;
import com.cvicse.jy1.service.QualityReturnsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
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
 * Integration tests for the {@link QualityReturnsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class QualityReturnsResourceIT {

    private static final String DEFAULT_QUALITYPLANID = "AAAAAAAAAA";
    private static final String UPDATED_QUALITYPLANID = "BBBBBBBBBB";

    private static final String DEFAULT_QUALITYOBJECTIVESID = "AAAAAAAAAA";
    private static final String UPDATED_QUALITYOBJECTIVESID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSIBLEID = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSIBLEID = "BBBBBBBBBB";

    private static final String DEFAULT_WBSID = "AAAAAAAAAA";
    private static final String UPDATED_WBSID = "BBBBBBBBBB";

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVESLEVEL = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVESLEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVES = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVES = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVESVALUE = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVESVALUE = "BBBBBBBBBB";

    private static final String DEFAULT_CALCULATIONMETHOD = "AAAAAAAAAA";
    private static final String UPDATED_CALCULATIONMETHOD = "BBBBBBBBBB";

    private static final String DEFAULT_FREQUENCY = "AAAAAAAAAA";
    private static final String UPDATED_FREQUENCY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISOBJECTIVESVALUE = false;
    private static final Boolean UPDATED_ISOBJECTIVESVALUE = true;

    private static final BigDecimal DEFAULT_PERCENTAGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERCENTAGE = new BigDecimal(2);

    private static final String DEFAULT_OBJECTIVESCOMPLETION = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVESCOMPLETION = "BBBBBBBBBB";

    private static final String DEFAULT_PROBLEM = "AAAAAAAAAA";
    private static final String UPDATED_PROBLEM = "BBBBBBBBBB";

    private static final String DEFAULT_TAKEACTION = "AAAAAAAAAA";
    private static final String UPDATED_TAKEACTION = "BBBBBBBBBB";

    private static final String DEFAULT_CONTINUOUSIMPROVEMENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTINUOUSIMPROVEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_WORKEVIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_WORKEVIDENCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RETURNTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RETURNTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/quality-returns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualityReturnsRepository qualityReturnsRepository;

    @Mock
    private QualityReturnsRepository qualityReturnsRepositoryMock;

    @Mock
    private QualityReturnsService qualityReturnsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualityReturnsMockMvc;

    private QualityReturns qualityReturns;

    private QualityReturns insertedQualityReturns;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualityReturns createEntity(EntityManager em) {
        QualityReturns qualityReturns = new QualityReturns()
            .qualityplanid(DEFAULT_QUALITYPLANID)
            .qualityobjectivesid(DEFAULT_QUALITYOBJECTIVESID)
            .name(DEFAULT_NAME)
            .department(DEFAULT_DEPARTMENT)
            .responsibleid(DEFAULT_RESPONSIBLEID)
            .wbsid(DEFAULT_WBSID)
            .workbagid(DEFAULT_WORKBAGID)
            .objectiveslevel(DEFAULT_OBJECTIVESLEVEL)
            .objectives(DEFAULT_OBJECTIVES)
            .objectivesvalue(DEFAULT_OBJECTIVESVALUE)
            .calculationmethod(DEFAULT_CALCULATIONMETHOD)
            .frequency(DEFAULT_FREQUENCY)
            .isobjectivesvalue(DEFAULT_ISOBJECTIVESVALUE)
            .percentage(DEFAULT_PERCENTAGE)
            .objectivescompletion(DEFAULT_OBJECTIVESCOMPLETION)
            .problem(DEFAULT_PROBLEM)
            .takeaction(DEFAULT_TAKEACTION)
            .continuousimprovement(DEFAULT_CONTINUOUSIMPROVEMENT)
            .workevidence(DEFAULT_WORKEVIDENCE)
            .returntime(DEFAULT_RETURNTIME)
            .status(DEFAULT_STATUS);
        return qualityReturns;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualityReturns createUpdatedEntity(EntityManager em) {
        QualityReturns qualityReturns = new QualityReturns()
            .qualityplanid(UPDATED_QUALITYPLANID)
            .qualityobjectivesid(UPDATED_QUALITYOBJECTIVESID)
            .name(UPDATED_NAME)
            .department(UPDATED_DEPARTMENT)
            .responsibleid(UPDATED_RESPONSIBLEID)
            .wbsid(UPDATED_WBSID)
            .workbagid(UPDATED_WORKBAGID)
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .objectives(UPDATED_OBJECTIVES)
            .objectivesvalue(UPDATED_OBJECTIVESVALUE)
            .calculationmethod(UPDATED_CALCULATIONMETHOD)
            .frequency(UPDATED_FREQUENCY)
            .isobjectivesvalue(UPDATED_ISOBJECTIVESVALUE)
            .percentage(UPDATED_PERCENTAGE)
            .objectivescompletion(UPDATED_OBJECTIVESCOMPLETION)
            .problem(UPDATED_PROBLEM)
            .takeaction(UPDATED_TAKEACTION)
            .continuousimprovement(UPDATED_CONTINUOUSIMPROVEMENT)
            .workevidence(UPDATED_WORKEVIDENCE)
            .returntime(UPDATED_RETURNTIME)
            .status(UPDATED_STATUS);
        return qualityReturns;
    }

    @BeforeEach
    public void initTest() {
        qualityReturns = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedQualityReturns != null) {
            qualityReturnsRepository.delete(insertedQualityReturns);
            insertedQualityReturns = null;
        }
    }

    @Test
    @Transactional
    void createQualityReturns() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the QualityReturns
        var returnedQualityReturns = om.readValue(
            restQualityReturnsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityReturns)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QualityReturns.class
        );

        // Validate the QualityReturns in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualityReturnsUpdatableFieldsEquals(returnedQualityReturns, getPersistedQualityReturns(returnedQualityReturns));

        insertedQualityReturns = returnedQualityReturns;
    }

    @Test
    @Transactional
    void createQualityReturnsWithExistingId() throws Exception {
        // Create the QualityReturns with an existing ID
        qualityReturns.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualityReturnsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityReturns)))
            .andExpect(status().isBadRequest());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualityReturns() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        // Get all the qualityReturnsList
        restQualityReturnsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualityReturns.getId().intValue())))
            .andExpect(jsonPath("$.[*].qualityplanid").value(hasItem(DEFAULT_QUALITYPLANID)))
            .andExpect(jsonPath("$.[*].qualityobjectivesid").value(hasItem(DEFAULT_QUALITYOBJECTIVESID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].responsibleid").value(hasItem(DEFAULT_RESPONSIBLEID)))
            .andExpect(jsonPath("$.[*].wbsid").value(hasItem(DEFAULT_WBSID)))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].objectiveslevel").value(hasItem(DEFAULT_OBJECTIVESLEVEL)))
            .andExpect(jsonPath("$.[*].objectives").value(hasItem(DEFAULT_OBJECTIVES)))
            .andExpect(jsonPath("$.[*].objectivesvalue").value(hasItem(DEFAULT_OBJECTIVESVALUE)))
            .andExpect(jsonPath("$.[*].calculationmethod").value(hasItem(DEFAULT_CALCULATIONMETHOD)))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY)))
            .andExpect(jsonPath("$.[*].isobjectivesvalue").value(hasItem(DEFAULT_ISOBJECTIVESVALUE.booleanValue())))
            .andExpect(jsonPath("$.[*].percentage").value(hasItem(sameNumber(DEFAULT_PERCENTAGE))))
            .andExpect(jsonPath("$.[*].objectivescompletion").value(hasItem(DEFAULT_OBJECTIVESCOMPLETION)))
            .andExpect(jsonPath("$.[*].problem").value(hasItem(DEFAULT_PROBLEM)))
            .andExpect(jsonPath("$.[*].takeaction").value(hasItem(DEFAULT_TAKEACTION)))
            .andExpect(jsonPath("$.[*].continuousimprovement").value(hasItem(DEFAULT_CONTINUOUSIMPROVEMENT)))
            .andExpect(jsonPath("$.[*].workevidence").value(hasItem(DEFAULT_WORKEVIDENCE)))
            .andExpect(jsonPath("$.[*].returntime").value(hasItem(DEFAULT_RETURNTIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllQualityReturnsWithEagerRelationshipsIsEnabled() throws Exception {
        when(qualityReturnsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restQualityReturnsMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(qualityReturnsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllQualityReturnsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(qualityReturnsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restQualityReturnsMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(qualityReturnsRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getQualityReturns() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        // Get the qualityReturns
        restQualityReturnsMockMvc
            .perform(get(ENTITY_API_URL_ID, qualityReturns.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualityReturns.getId().intValue()))
            .andExpect(jsonPath("$.qualityplanid").value(DEFAULT_QUALITYPLANID))
            .andExpect(jsonPath("$.qualityobjectivesid").value(DEFAULT_QUALITYOBJECTIVESID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.responsibleid").value(DEFAULT_RESPONSIBLEID))
            .andExpect(jsonPath("$.wbsid").value(DEFAULT_WBSID))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.objectiveslevel").value(DEFAULT_OBJECTIVESLEVEL))
            .andExpect(jsonPath("$.objectives").value(DEFAULT_OBJECTIVES))
            .andExpect(jsonPath("$.objectivesvalue").value(DEFAULT_OBJECTIVESVALUE))
            .andExpect(jsonPath("$.calculationmethod").value(DEFAULT_CALCULATIONMETHOD))
            .andExpect(jsonPath("$.frequency").value(DEFAULT_FREQUENCY))
            .andExpect(jsonPath("$.isobjectivesvalue").value(DEFAULT_ISOBJECTIVESVALUE.booleanValue()))
            .andExpect(jsonPath("$.percentage").value(sameNumber(DEFAULT_PERCENTAGE)))
            .andExpect(jsonPath("$.objectivescompletion").value(DEFAULT_OBJECTIVESCOMPLETION))
            .andExpect(jsonPath("$.problem").value(DEFAULT_PROBLEM))
            .andExpect(jsonPath("$.takeaction").value(DEFAULT_TAKEACTION))
            .andExpect(jsonPath("$.continuousimprovement").value(DEFAULT_CONTINUOUSIMPROVEMENT))
            .andExpect(jsonPath("$.workevidence").value(DEFAULT_WORKEVIDENCE))
            .andExpect(jsonPath("$.returntime").value(DEFAULT_RETURNTIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingQualityReturns() throws Exception {
        // Get the qualityReturns
        restQualityReturnsMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualityReturns() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityReturns
        QualityReturns updatedQualityReturns = qualityReturnsRepository.findById(qualityReturns.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQualityReturns are not directly saved in db
        em.detach(updatedQualityReturns);
        updatedQualityReturns
            .qualityplanid(UPDATED_QUALITYPLANID)
            .qualityobjectivesid(UPDATED_QUALITYOBJECTIVESID)
            .name(UPDATED_NAME)
            .department(UPDATED_DEPARTMENT)
            .responsibleid(UPDATED_RESPONSIBLEID)
            .wbsid(UPDATED_WBSID)
            .workbagid(UPDATED_WORKBAGID)
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .objectives(UPDATED_OBJECTIVES)
            .objectivesvalue(UPDATED_OBJECTIVESVALUE)
            .calculationmethod(UPDATED_CALCULATIONMETHOD)
            .frequency(UPDATED_FREQUENCY)
            .isobjectivesvalue(UPDATED_ISOBJECTIVESVALUE)
            .percentage(UPDATED_PERCENTAGE)
            .objectivescompletion(UPDATED_OBJECTIVESCOMPLETION)
            .problem(UPDATED_PROBLEM)
            .takeaction(UPDATED_TAKEACTION)
            .continuousimprovement(UPDATED_CONTINUOUSIMPROVEMENT)
            .workevidence(UPDATED_WORKEVIDENCE)
            .returntime(UPDATED_RETURNTIME)
            .status(UPDATED_STATUS);

        restQualityReturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualityReturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualityReturns))
            )
            .andExpect(status().isOk());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualityReturnsToMatchAllProperties(updatedQualityReturns);
    }

    @Test
    @Transactional
    void putNonExistingQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualityReturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityReturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualityReturnsWithPatch() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityReturns using partial update
        QualityReturns partialUpdatedQualityReturns = new QualityReturns();
        partialUpdatedQualityReturns.setId(qualityReturns.getId());

        partialUpdatedQualityReturns
            .qualityplanid(UPDATED_QUALITYPLANID)
            .qualityobjectivesid(UPDATED_QUALITYOBJECTIVESID)
            .department(UPDATED_DEPARTMENT)
            .wbsid(UPDATED_WBSID)
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .calculationmethod(UPDATED_CALCULATIONMETHOD)
            .frequency(UPDATED_FREQUENCY)
            .percentage(UPDATED_PERCENTAGE)
            .objectivescompletion(UPDATED_OBJECTIVESCOMPLETION)
            .problem(UPDATED_PROBLEM)
            .continuousimprovement(UPDATED_CONTINUOUSIMPROVEMENT)
            .returntime(UPDATED_RETURNTIME);

        restQualityReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityReturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityReturns))
            )
            .andExpect(status().isOk());

        // Validate the QualityReturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityReturnsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualityReturns, qualityReturns),
            getPersistedQualityReturns(qualityReturns)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualityReturnsWithPatch() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityReturns using partial update
        QualityReturns partialUpdatedQualityReturns = new QualityReturns();
        partialUpdatedQualityReturns.setId(qualityReturns.getId());

        partialUpdatedQualityReturns
            .qualityplanid(UPDATED_QUALITYPLANID)
            .qualityobjectivesid(UPDATED_QUALITYOBJECTIVESID)
            .name(UPDATED_NAME)
            .department(UPDATED_DEPARTMENT)
            .responsibleid(UPDATED_RESPONSIBLEID)
            .wbsid(UPDATED_WBSID)
            .workbagid(UPDATED_WORKBAGID)
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .objectives(UPDATED_OBJECTIVES)
            .objectivesvalue(UPDATED_OBJECTIVESVALUE)
            .calculationmethod(UPDATED_CALCULATIONMETHOD)
            .frequency(UPDATED_FREQUENCY)
            .isobjectivesvalue(UPDATED_ISOBJECTIVESVALUE)
            .percentage(UPDATED_PERCENTAGE)
            .objectivescompletion(UPDATED_OBJECTIVESCOMPLETION)
            .problem(UPDATED_PROBLEM)
            .takeaction(UPDATED_TAKEACTION)
            .continuousimprovement(UPDATED_CONTINUOUSIMPROVEMENT)
            .workevidence(UPDATED_WORKEVIDENCE)
            .returntime(UPDATED_RETURNTIME)
            .status(UPDATED_STATUS);

        restQualityReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityReturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityReturns))
            )
            .andExpect(status().isOk());

        // Validate the QualityReturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityReturnsUpdatableFieldsEquals(partialUpdatedQualityReturns, getPersistedQualityReturns(partialUpdatedQualityReturns));
    }

    @Test
    @Transactional
    void patchNonExistingQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualityReturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualityReturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualityReturns() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualityReturns
        restQualityReturnsMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualityReturns.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualityReturnsRepository.count();
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

    protected QualityReturns getPersistedQualityReturns(QualityReturns qualityReturns) {
        return qualityReturnsRepository.findById(qualityReturns.getId()).orElseThrow();
    }

    protected void assertPersistedQualityReturnsToMatchAllProperties(QualityReturns expectedQualityReturns) {
        assertQualityReturnsAllPropertiesEquals(expectedQualityReturns, getPersistedQualityReturns(expectedQualityReturns));
    }

    protected void assertPersistedQualityReturnsToMatchUpdatableProperties(QualityReturns expectedQualityReturns) {
        assertQualityReturnsAllUpdatablePropertiesEquals(expectedQualityReturns, getPersistedQualityReturns(expectedQualityReturns));
    }
}
