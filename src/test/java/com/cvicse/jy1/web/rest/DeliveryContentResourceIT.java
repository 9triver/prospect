package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.DeliveryContentAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.DeliveryContent;
import com.cvicse.jy1.repository.DeliveryContentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link DeliveryContentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DeliveryContentResourceIT {

    private static final String DEFAULT_WARRANTYREQUIREMENT = "AAAAAAAAAA";
    private static final String UPDATED_WARRANTYREQUIREMENT = "BBBBBBBBBB";

    private static final String DEFAULT_PURCHASEPLANNO = "AAAAAAAAAA";
    private static final String UPDATED_PURCHASEPLANNO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PURCHASEPLANDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PURCHASEPLANDATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_PURCHASEPLANAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PURCHASEPLANAMOUNT = new BigDecimal(2);

    private static final String DEFAULT_PURCHASEMETHOD = "AAAAAAAAAA";
    private static final String UPDATED_PURCHASEMETHOD = "BBBBBBBBBB";

    private static final String DEFAULT_PURCHASESECRETLEVEL = "AAAAAAAAAA";
    private static final String UPDATED_PURCHASESECRETLEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_REVIEWMETHOD = "AAAAAAAAAA";
    private static final String UPDATED_REVIEWMETHOD = "BBBBBBBBBB";

    private static final String DEFAULT_REQUIREMENTDEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_REQUIREMENTDEPARTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_REQUIREMENTPERSON = "AAAAAAAAAA";
    private static final String UPDATED_REQUIREMENTPERSON = "BBBBBBBBBB";

    private static final String DEFAULT_UNDERTAKER = "AAAAAAAAAA";
    private static final String UPDATED_UNDERTAKER = "BBBBBBBBBB";

    private static final String DEFAULT_UNDERTAKINGDEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_UNDERTAKINGDEPARTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTMANAGER = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTMANAGER = "BBBBBBBBBB";

    private static final String DEFAULT_FUNDSOURCE = "AAAAAAAAAA";
    private static final String UPDATED_FUNDSOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_THESISNAME = "AAAAAAAAAA";
    private static final String UPDATED_THESISNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTAUXILIARYNO = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTAUXILIARYNO = "BBBBBBBBBB";

    private static final String DEFAULT_REASONFORNOSUPPLIERS = "AAAAAAAAAA";
    private static final String UPDATED_REASONFORNOSUPPLIERS = "BBBBBBBBBB";

    private static final String DEFAULT_REASONFORCHANGE = "AAAAAAAAAA";
    private static final String UPDATED_REASONFORCHANGE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NEGOTIATIONFILETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NEGOTIATIONFILETIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_BIDOPENINGTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIDOPENINGTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_JUDGES = "AAAAAAAAAA";
    private static final String UPDATED_JUDGES = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSEVENDORNAME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSEVENDORNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FINALQUOTEANDSCORE = "AAAAAAAAAA";
    private static final String UPDATED_FINALQUOTEANDSCORE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NOTICEOFCOMPLETIONTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NOTICEOFCOMPLETIONTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SIGNINGDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SIGNINGDATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CONTRACTENDDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CONTRACTENDDATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACTUALCOMPLETIONTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUALCOMPLETIONTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ISSUBMITSECRECYAGREEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ISSUBMITSECRECYAGREEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_ISSUBMITSECURITYAGREEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ISSUBMITSECURITYAGREEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/delivery-contents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DeliveryContentRepository deliveryContentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeliveryContentMockMvc;

    private DeliveryContent deliveryContent;

    private DeliveryContent insertedDeliveryContent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryContent createEntity(EntityManager em) {
        DeliveryContent deliveryContent = new DeliveryContent()
            .warrantyrequirement(DEFAULT_WARRANTYREQUIREMENT)
            .purchaseplanno(DEFAULT_PURCHASEPLANNO)
            .purchaseplandate(DEFAULT_PURCHASEPLANDATE)
            .purchaseplanamount(DEFAULT_PURCHASEPLANAMOUNT)
            .purchasemethod(DEFAULT_PURCHASEMETHOD)
            .purchasesecretlevel(DEFAULT_PURCHASESECRETLEVEL)
            .reviewmethod(DEFAULT_REVIEWMETHOD)
            .requirementdepartment(DEFAULT_REQUIREMENTDEPARTMENT)
            .requirementperson(DEFAULT_REQUIREMENTPERSON)
            .undertaker(DEFAULT_UNDERTAKER)
            .undertakingdepartment(DEFAULT_UNDERTAKINGDEPARTMENT)
            .workbagid(DEFAULT_WORKBAGID)
            .projectmanager(DEFAULT_PROJECTMANAGER)
            .fundsource(DEFAULT_FUNDSOURCE)
            .thesisname(DEFAULT_THESISNAME)
            .contractauxiliaryno(DEFAULT_CONTRACTAUXILIARYNO)
            .reasonfornosuppliers(DEFAULT_REASONFORNOSUPPLIERS)
            .reasonforchange(DEFAULT_REASONFORCHANGE)
            .negotiationfiletime(DEFAULT_NEGOTIATIONFILETIME)
            .bidopeningtime(DEFAULT_BIDOPENINGTIME)
            .judges(DEFAULT_JUDGES)
            .responsevendorname(DEFAULT_RESPONSEVENDORNAME)
            .finalquoteandscore(DEFAULT_FINALQUOTEANDSCORE)
            .noticeofcompletiontime(DEFAULT_NOTICEOFCOMPLETIONTIME)
            .signingdate(DEFAULT_SIGNINGDATE)
            .contractenddate(DEFAULT_CONTRACTENDDATE)
            .actualcompletiontime(DEFAULT_ACTUALCOMPLETIONTIME)
            .issubmitsecrecyagreement(DEFAULT_ISSUBMITSECRECYAGREEMENT)
            .issubmitsecurityagreement(DEFAULT_ISSUBMITSECURITYAGREEMENT)
            .remark(DEFAULT_REMARK);
        return deliveryContent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryContent createUpdatedEntity(EntityManager em) {
        DeliveryContent deliveryContent = new DeliveryContent()
            .warrantyrequirement(UPDATED_WARRANTYREQUIREMENT)
            .purchaseplanno(UPDATED_PURCHASEPLANNO)
            .purchaseplandate(UPDATED_PURCHASEPLANDATE)
            .purchaseplanamount(UPDATED_PURCHASEPLANAMOUNT)
            .purchasemethod(UPDATED_PURCHASEMETHOD)
            .purchasesecretlevel(UPDATED_PURCHASESECRETLEVEL)
            .reviewmethod(UPDATED_REVIEWMETHOD)
            .requirementdepartment(UPDATED_REQUIREMENTDEPARTMENT)
            .requirementperson(UPDATED_REQUIREMENTPERSON)
            .undertaker(UPDATED_UNDERTAKER)
            .undertakingdepartment(UPDATED_UNDERTAKINGDEPARTMENT)
            .workbagid(UPDATED_WORKBAGID)
            .projectmanager(UPDATED_PROJECTMANAGER)
            .fundsource(UPDATED_FUNDSOURCE)
            .thesisname(UPDATED_THESISNAME)
            .contractauxiliaryno(UPDATED_CONTRACTAUXILIARYNO)
            .reasonfornosuppliers(UPDATED_REASONFORNOSUPPLIERS)
            .reasonforchange(UPDATED_REASONFORCHANGE)
            .negotiationfiletime(UPDATED_NEGOTIATIONFILETIME)
            .bidopeningtime(UPDATED_BIDOPENINGTIME)
            .judges(UPDATED_JUDGES)
            .responsevendorname(UPDATED_RESPONSEVENDORNAME)
            .finalquoteandscore(UPDATED_FINALQUOTEANDSCORE)
            .noticeofcompletiontime(UPDATED_NOTICEOFCOMPLETIONTIME)
            .signingdate(UPDATED_SIGNINGDATE)
            .contractenddate(UPDATED_CONTRACTENDDATE)
            .actualcompletiontime(UPDATED_ACTUALCOMPLETIONTIME)
            .issubmitsecrecyagreement(UPDATED_ISSUBMITSECRECYAGREEMENT)
            .issubmitsecurityagreement(UPDATED_ISSUBMITSECURITYAGREEMENT)
            .remark(UPDATED_REMARK);
        return deliveryContent;
    }

    @BeforeEach
    public void initTest() {
        deliveryContent = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDeliveryContent != null) {
            deliveryContentRepository.delete(insertedDeliveryContent);
            insertedDeliveryContent = null;
        }
    }

    @Test
    @Transactional
    void createDeliveryContent() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DeliveryContent
        var returnedDeliveryContent = om.readValue(
            restDeliveryContentMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(deliveryContent)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DeliveryContent.class
        );

        // Validate the DeliveryContent in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertDeliveryContentUpdatableFieldsEquals(returnedDeliveryContent, getPersistedDeliveryContent(returnedDeliveryContent));

        insertedDeliveryContent = returnedDeliveryContent;
    }

    @Test
    @Transactional
    void createDeliveryContentWithExistingId() throws Exception {
        // Create the DeliveryContent with an existing ID
        deliveryContent.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryContentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(deliveryContent)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryContent in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDeliveryContents() throws Exception {
        // Initialize the database
        insertedDeliveryContent = deliveryContentRepository.saveAndFlush(deliveryContent);

        // Get all the deliveryContentList
        restDeliveryContentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliveryContent.getId().intValue())))
            .andExpect(jsonPath("$.[*].warrantyrequirement").value(hasItem(DEFAULT_WARRANTYREQUIREMENT)))
            .andExpect(jsonPath("$.[*].purchaseplanno").value(hasItem(DEFAULT_PURCHASEPLANNO)))
            .andExpect(jsonPath("$.[*].purchaseplandate").value(hasItem(DEFAULT_PURCHASEPLANDATE.toString())))
            .andExpect(jsonPath("$.[*].purchaseplanamount").value(hasItem(sameNumber(DEFAULT_PURCHASEPLANAMOUNT))))
            .andExpect(jsonPath("$.[*].purchasemethod").value(hasItem(DEFAULT_PURCHASEMETHOD)))
            .andExpect(jsonPath("$.[*].purchasesecretlevel").value(hasItem(DEFAULT_PURCHASESECRETLEVEL)))
            .andExpect(jsonPath("$.[*].reviewmethod").value(hasItem(DEFAULT_REVIEWMETHOD)))
            .andExpect(jsonPath("$.[*].requirementdepartment").value(hasItem(DEFAULT_REQUIREMENTDEPARTMENT)))
            .andExpect(jsonPath("$.[*].requirementperson").value(hasItem(DEFAULT_REQUIREMENTPERSON)))
            .andExpect(jsonPath("$.[*].undertaker").value(hasItem(DEFAULT_UNDERTAKER)))
            .andExpect(jsonPath("$.[*].undertakingdepartment").value(hasItem(DEFAULT_UNDERTAKINGDEPARTMENT)))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].projectmanager").value(hasItem(DEFAULT_PROJECTMANAGER)))
            .andExpect(jsonPath("$.[*].fundsource").value(hasItem(DEFAULT_FUNDSOURCE)))
            .andExpect(jsonPath("$.[*].thesisname").value(hasItem(DEFAULT_THESISNAME)))
            .andExpect(jsonPath("$.[*].contractauxiliaryno").value(hasItem(DEFAULT_CONTRACTAUXILIARYNO)))
            .andExpect(jsonPath("$.[*].reasonfornosuppliers").value(hasItem(DEFAULT_REASONFORNOSUPPLIERS)))
            .andExpect(jsonPath("$.[*].reasonforchange").value(hasItem(DEFAULT_REASONFORCHANGE)))
            .andExpect(jsonPath("$.[*].negotiationfiletime").value(hasItem(DEFAULT_NEGOTIATIONFILETIME.toString())))
            .andExpect(jsonPath("$.[*].bidopeningtime").value(hasItem(DEFAULT_BIDOPENINGTIME.toString())))
            .andExpect(jsonPath("$.[*].judges").value(hasItem(DEFAULT_JUDGES)))
            .andExpect(jsonPath("$.[*].responsevendorname").value(hasItem(DEFAULT_RESPONSEVENDORNAME)))
            .andExpect(jsonPath("$.[*].finalquoteandscore").value(hasItem(DEFAULT_FINALQUOTEANDSCORE)))
            .andExpect(jsonPath("$.[*].noticeofcompletiontime").value(hasItem(DEFAULT_NOTICEOFCOMPLETIONTIME.toString())))
            .andExpect(jsonPath("$.[*].signingdate").value(hasItem(DEFAULT_SIGNINGDATE.toString())))
            .andExpect(jsonPath("$.[*].contractenddate").value(hasItem(DEFAULT_CONTRACTENDDATE.toString())))
            .andExpect(jsonPath("$.[*].actualcompletiontime").value(hasItem(DEFAULT_ACTUALCOMPLETIONTIME.toString())))
            .andExpect(jsonPath("$.[*].issubmitsecrecyagreement").value(hasItem(DEFAULT_ISSUBMITSECRECYAGREEMENT)))
            .andExpect(jsonPath("$.[*].issubmitsecurityagreement").value(hasItem(DEFAULT_ISSUBMITSECURITYAGREEMENT)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)));
    }

    @Test
    @Transactional
    void getDeliveryContent() throws Exception {
        // Initialize the database
        insertedDeliveryContent = deliveryContentRepository.saveAndFlush(deliveryContent);

        // Get the deliveryContent
        restDeliveryContentMockMvc
            .perform(get(ENTITY_API_URL_ID, deliveryContent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deliveryContent.getId().intValue()))
            .andExpect(jsonPath("$.warrantyrequirement").value(DEFAULT_WARRANTYREQUIREMENT))
            .andExpect(jsonPath("$.purchaseplanno").value(DEFAULT_PURCHASEPLANNO))
            .andExpect(jsonPath("$.purchaseplandate").value(DEFAULT_PURCHASEPLANDATE.toString()))
            .andExpect(jsonPath("$.purchaseplanamount").value(sameNumber(DEFAULT_PURCHASEPLANAMOUNT)))
            .andExpect(jsonPath("$.purchasemethod").value(DEFAULT_PURCHASEMETHOD))
            .andExpect(jsonPath("$.purchasesecretlevel").value(DEFAULT_PURCHASESECRETLEVEL))
            .andExpect(jsonPath("$.reviewmethod").value(DEFAULT_REVIEWMETHOD))
            .andExpect(jsonPath("$.requirementdepartment").value(DEFAULT_REQUIREMENTDEPARTMENT))
            .andExpect(jsonPath("$.requirementperson").value(DEFAULT_REQUIREMENTPERSON))
            .andExpect(jsonPath("$.undertaker").value(DEFAULT_UNDERTAKER))
            .andExpect(jsonPath("$.undertakingdepartment").value(DEFAULT_UNDERTAKINGDEPARTMENT))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.projectmanager").value(DEFAULT_PROJECTMANAGER))
            .andExpect(jsonPath("$.fundsource").value(DEFAULT_FUNDSOURCE))
            .andExpect(jsonPath("$.thesisname").value(DEFAULT_THESISNAME))
            .andExpect(jsonPath("$.contractauxiliaryno").value(DEFAULT_CONTRACTAUXILIARYNO))
            .andExpect(jsonPath("$.reasonfornosuppliers").value(DEFAULT_REASONFORNOSUPPLIERS))
            .andExpect(jsonPath("$.reasonforchange").value(DEFAULT_REASONFORCHANGE))
            .andExpect(jsonPath("$.negotiationfiletime").value(DEFAULT_NEGOTIATIONFILETIME.toString()))
            .andExpect(jsonPath("$.bidopeningtime").value(DEFAULT_BIDOPENINGTIME.toString()))
            .andExpect(jsonPath("$.judges").value(DEFAULT_JUDGES))
            .andExpect(jsonPath("$.responsevendorname").value(DEFAULT_RESPONSEVENDORNAME))
            .andExpect(jsonPath("$.finalquoteandscore").value(DEFAULT_FINALQUOTEANDSCORE))
            .andExpect(jsonPath("$.noticeofcompletiontime").value(DEFAULT_NOTICEOFCOMPLETIONTIME.toString()))
            .andExpect(jsonPath("$.signingdate").value(DEFAULT_SIGNINGDATE.toString()))
            .andExpect(jsonPath("$.contractenddate").value(DEFAULT_CONTRACTENDDATE.toString()))
            .andExpect(jsonPath("$.actualcompletiontime").value(DEFAULT_ACTUALCOMPLETIONTIME.toString()))
            .andExpect(jsonPath("$.issubmitsecrecyagreement").value(DEFAULT_ISSUBMITSECRECYAGREEMENT))
            .andExpect(jsonPath("$.issubmitsecurityagreement").value(DEFAULT_ISSUBMITSECURITYAGREEMENT))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK));
    }

    @Test
    @Transactional
    void getNonExistingDeliveryContent() throws Exception {
        // Get the deliveryContent
        restDeliveryContentMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDeliveryContent() throws Exception {
        // Initialize the database
        insertedDeliveryContent = deliveryContentRepository.saveAndFlush(deliveryContent);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the deliveryContent
        DeliveryContent updatedDeliveryContent = deliveryContentRepository.findById(deliveryContent.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDeliveryContent are not directly saved in db
        em.detach(updatedDeliveryContent);
        updatedDeliveryContent
            .warrantyrequirement(UPDATED_WARRANTYREQUIREMENT)
            .purchaseplanno(UPDATED_PURCHASEPLANNO)
            .purchaseplandate(UPDATED_PURCHASEPLANDATE)
            .purchaseplanamount(UPDATED_PURCHASEPLANAMOUNT)
            .purchasemethod(UPDATED_PURCHASEMETHOD)
            .purchasesecretlevel(UPDATED_PURCHASESECRETLEVEL)
            .reviewmethod(UPDATED_REVIEWMETHOD)
            .requirementdepartment(UPDATED_REQUIREMENTDEPARTMENT)
            .requirementperson(UPDATED_REQUIREMENTPERSON)
            .undertaker(UPDATED_UNDERTAKER)
            .undertakingdepartment(UPDATED_UNDERTAKINGDEPARTMENT)
            .workbagid(UPDATED_WORKBAGID)
            .projectmanager(UPDATED_PROJECTMANAGER)
            .fundsource(UPDATED_FUNDSOURCE)
            .thesisname(UPDATED_THESISNAME)
            .contractauxiliaryno(UPDATED_CONTRACTAUXILIARYNO)
            .reasonfornosuppliers(UPDATED_REASONFORNOSUPPLIERS)
            .reasonforchange(UPDATED_REASONFORCHANGE)
            .negotiationfiletime(UPDATED_NEGOTIATIONFILETIME)
            .bidopeningtime(UPDATED_BIDOPENINGTIME)
            .judges(UPDATED_JUDGES)
            .responsevendorname(UPDATED_RESPONSEVENDORNAME)
            .finalquoteandscore(UPDATED_FINALQUOTEANDSCORE)
            .noticeofcompletiontime(UPDATED_NOTICEOFCOMPLETIONTIME)
            .signingdate(UPDATED_SIGNINGDATE)
            .contractenddate(UPDATED_CONTRACTENDDATE)
            .actualcompletiontime(UPDATED_ACTUALCOMPLETIONTIME)
            .issubmitsecrecyagreement(UPDATED_ISSUBMITSECRECYAGREEMENT)
            .issubmitsecurityagreement(UPDATED_ISSUBMITSECURITYAGREEMENT)
            .remark(UPDATED_REMARK);

        restDeliveryContentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDeliveryContent.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedDeliveryContent))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryContent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDeliveryContentToMatchAllProperties(updatedDeliveryContent);
    }

    @Test
    @Transactional
    void putNonExistingDeliveryContent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliveryContent.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryContentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryContent.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(deliveryContent))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryContent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDeliveryContent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliveryContent.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryContentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(deliveryContent))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryContent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDeliveryContent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliveryContent.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryContentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(deliveryContent)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryContent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDeliveryContentWithPatch() throws Exception {
        // Initialize the database
        insertedDeliveryContent = deliveryContentRepository.saveAndFlush(deliveryContent);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the deliveryContent using partial update
        DeliveryContent partialUpdatedDeliveryContent = new DeliveryContent();
        partialUpdatedDeliveryContent.setId(deliveryContent.getId());

        partialUpdatedDeliveryContent
            .purchaseplanamount(UPDATED_PURCHASEPLANAMOUNT)
            .reviewmethod(UPDATED_REVIEWMETHOD)
            .undertaker(UPDATED_UNDERTAKER)
            .projectmanager(UPDATED_PROJECTMANAGER)
            .thesisname(UPDATED_THESISNAME)
            .contractauxiliaryno(UPDATED_CONTRACTAUXILIARYNO)
            .reasonforchange(UPDATED_REASONFORCHANGE)
            .negotiationfiletime(UPDATED_NEGOTIATIONFILETIME)
            .responsevendorname(UPDATED_RESPONSEVENDORNAME)
            .finalquoteandscore(UPDATED_FINALQUOTEANDSCORE)
            .issubmitsecrecyagreement(UPDATED_ISSUBMITSECRECYAGREEMENT)
            .issubmitsecurityagreement(UPDATED_ISSUBMITSECURITYAGREEMENT)
            .remark(UPDATED_REMARK);

        restDeliveryContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryContent.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDeliveryContent))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryContent in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDeliveryContentUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDeliveryContent, deliveryContent),
            getPersistedDeliveryContent(deliveryContent)
        );
    }

    @Test
    @Transactional
    void fullUpdateDeliveryContentWithPatch() throws Exception {
        // Initialize the database
        insertedDeliveryContent = deliveryContentRepository.saveAndFlush(deliveryContent);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the deliveryContent using partial update
        DeliveryContent partialUpdatedDeliveryContent = new DeliveryContent();
        partialUpdatedDeliveryContent.setId(deliveryContent.getId());

        partialUpdatedDeliveryContent
            .warrantyrequirement(UPDATED_WARRANTYREQUIREMENT)
            .purchaseplanno(UPDATED_PURCHASEPLANNO)
            .purchaseplandate(UPDATED_PURCHASEPLANDATE)
            .purchaseplanamount(UPDATED_PURCHASEPLANAMOUNT)
            .purchasemethod(UPDATED_PURCHASEMETHOD)
            .purchasesecretlevel(UPDATED_PURCHASESECRETLEVEL)
            .reviewmethod(UPDATED_REVIEWMETHOD)
            .requirementdepartment(UPDATED_REQUIREMENTDEPARTMENT)
            .requirementperson(UPDATED_REQUIREMENTPERSON)
            .undertaker(UPDATED_UNDERTAKER)
            .undertakingdepartment(UPDATED_UNDERTAKINGDEPARTMENT)
            .workbagid(UPDATED_WORKBAGID)
            .projectmanager(UPDATED_PROJECTMANAGER)
            .fundsource(UPDATED_FUNDSOURCE)
            .thesisname(UPDATED_THESISNAME)
            .contractauxiliaryno(UPDATED_CONTRACTAUXILIARYNO)
            .reasonfornosuppliers(UPDATED_REASONFORNOSUPPLIERS)
            .reasonforchange(UPDATED_REASONFORCHANGE)
            .negotiationfiletime(UPDATED_NEGOTIATIONFILETIME)
            .bidopeningtime(UPDATED_BIDOPENINGTIME)
            .judges(UPDATED_JUDGES)
            .responsevendorname(UPDATED_RESPONSEVENDORNAME)
            .finalquoteandscore(UPDATED_FINALQUOTEANDSCORE)
            .noticeofcompletiontime(UPDATED_NOTICEOFCOMPLETIONTIME)
            .signingdate(UPDATED_SIGNINGDATE)
            .contractenddate(UPDATED_CONTRACTENDDATE)
            .actualcompletiontime(UPDATED_ACTUALCOMPLETIONTIME)
            .issubmitsecrecyagreement(UPDATED_ISSUBMITSECRECYAGREEMENT)
            .issubmitsecurityagreement(UPDATED_ISSUBMITSECURITYAGREEMENT)
            .remark(UPDATED_REMARK);

        restDeliveryContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryContent.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDeliveryContent))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryContent in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDeliveryContentUpdatableFieldsEquals(
            partialUpdatedDeliveryContent,
            getPersistedDeliveryContent(partialUpdatedDeliveryContent)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDeliveryContent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliveryContent.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, deliveryContent.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(deliveryContent))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryContent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDeliveryContent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliveryContent.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryContentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(deliveryContent))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryContent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDeliveryContent() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliveryContent.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryContentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(deliveryContent)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryContent in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDeliveryContent() throws Exception {
        // Initialize the database
        insertedDeliveryContent = deliveryContentRepository.saveAndFlush(deliveryContent);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the deliveryContent
        restDeliveryContentMockMvc
            .perform(delete(ENTITY_API_URL_ID, deliveryContent.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return deliveryContentRepository.count();
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

    protected DeliveryContent getPersistedDeliveryContent(DeliveryContent deliveryContent) {
        return deliveryContentRepository.findById(deliveryContent.getId()).orElseThrow();
    }

    protected void assertPersistedDeliveryContentToMatchAllProperties(DeliveryContent expectedDeliveryContent) {
        assertDeliveryContentAllPropertiesEquals(expectedDeliveryContent, getPersistedDeliveryContent(expectedDeliveryContent));
    }

    protected void assertPersistedDeliveryContentToMatchUpdatableProperties(DeliveryContent expectedDeliveryContent) {
        assertDeliveryContentAllUpdatablePropertiesEquals(expectedDeliveryContent, getPersistedDeliveryContent(expectedDeliveryContent));
    }
}
