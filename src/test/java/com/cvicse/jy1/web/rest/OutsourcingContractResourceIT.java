package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.OutsourcingContractAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.OutsourcingContract;
import com.cvicse.jy1.repository.OutsourcingContractRepository;
import com.cvicse.jy1.service.OutsourcingContractService;
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
 * Integration tests for the {@link OutsourcingContractResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class OutsourcingContractResourceIT {

    private static final String DEFAULT_CONTRACTID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTCODE = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTNAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTQUALITYID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTQUALITYID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTCOSTID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTCOSTID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTFINANCEID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTFINANCEID = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTID = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTID = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTSECRETLEVEL = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTSECRETLEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTERPARTYUNIT = "AAAAAAAAAA";
    private static final String UPDATED_COUNTERPARTYUNIT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NEGOTIATIONDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NEGOTIATIONDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NEGOTIATIONLOCATION = "AAAAAAAAAA";
    private static final String UPDATED_NEGOTIATIONLOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_NEGOTIATOR = "AAAAAAAAAA";
    private static final String UPDATED_NEGOTIATOR = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BUDGETAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUDGETAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONTRACTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONTRACTAMOUNT = new BigDecimal(2);

    private static final String DEFAULT_APPROVER = "AAAAAAAAAA";
    private static final String UPDATED_APPROVER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_APPROVALDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPROVALDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CONTRACTSECRETLEVEL = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTSECRETLEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERYCONTENT = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERYCONTENT = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/outsourcing-contracts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OutsourcingContractRepository outsourcingContractRepository;

    @Mock
    private OutsourcingContractRepository outsourcingContractRepositoryMock;

    @Mock
    private OutsourcingContractService outsourcingContractServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOutsourcingContractMockMvc;

    private OutsourcingContract outsourcingContract;

    private OutsourcingContract insertedOutsourcingContract;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingContract createEntity(EntityManager em) {
        OutsourcingContract outsourcingContract = new OutsourcingContract()
            .contractid(DEFAULT_CONTRACTID)
            .contractcode(DEFAULT_CONTRACTCODE)
            .contractname(DEFAULT_CONTRACTNAME)
            .contractqualityid(DEFAULT_CONTRACTQUALITYID)
            .contractcostid(DEFAULT_CONTRACTCOSTID)
            .contractfinanceid(DEFAULT_CONTRACTFINANCEID)
            .projectid(DEFAULT_PROJECTID)
            .projectsecretlevel(DEFAULT_PROJECTSECRETLEVEL)
            .counterpartyunit(DEFAULT_COUNTERPARTYUNIT)
            .negotiationdate(DEFAULT_NEGOTIATIONDATE)
            .negotiationlocation(DEFAULT_NEGOTIATIONLOCATION)
            .negotiator(DEFAULT_NEGOTIATOR)
            .budgetamount(DEFAULT_BUDGETAMOUNT)
            .contractamount(DEFAULT_CONTRACTAMOUNT)
            .approver(DEFAULT_APPROVER)
            .approvaldate(DEFAULT_APPROVALDATE)
            .contractsecretlevel(DEFAULT_CONTRACTSECRETLEVEL)
            .deliverycontent(DEFAULT_DELIVERYCONTENT)
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
        return outsourcingContract;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingContract createUpdatedEntity(EntityManager em) {
        OutsourcingContract outsourcingContract = new OutsourcingContract()
            .contractid(UPDATED_CONTRACTID)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .contractqualityid(UPDATED_CONTRACTQUALITYID)
            .contractcostid(UPDATED_CONTRACTCOSTID)
            .contractfinanceid(UPDATED_CONTRACTFINANCEID)
            .projectid(UPDATED_PROJECTID)
            .projectsecretlevel(UPDATED_PROJECTSECRETLEVEL)
            .counterpartyunit(UPDATED_COUNTERPARTYUNIT)
            .negotiationdate(UPDATED_NEGOTIATIONDATE)
            .negotiationlocation(UPDATED_NEGOTIATIONLOCATION)
            .negotiator(UPDATED_NEGOTIATOR)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .contractamount(UPDATED_CONTRACTAMOUNT)
            .approver(UPDATED_APPROVER)
            .approvaldate(UPDATED_APPROVALDATE)
            .contractsecretlevel(UPDATED_CONTRACTSECRETLEVEL)
            .deliverycontent(UPDATED_DELIVERYCONTENT)
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
        return outsourcingContract;
    }

    @BeforeEach
    public void initTest() {
        outsourcingContract = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedOutsourcingContract != null) {
            outsourcingContractRepository.delete(insertedOutsourcingContract);
            insertedOutsourcingContract = null;
        }
    }

    @Test
    @Transactional
    void createOutsourcingContract() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OutsourcingContract
        var returnedOutsourcingContract = om.readValue(
            restOutsourcingContractMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingContract)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OutsourcingContract.class
        );

        // Validate the OutsourcingContract in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOutsourcingContractUpdatableFieldsEquals(
            returnedOutsourcingContract,
            getPersistedOutsourcingContract(returnedOutsourcingContract)
        );

        insertedOutsourcingContract = returnedOutsourcingContract;
    }

    @Test
    @Transactional
    void createOutsourcingContractWithExistingId() throws Exception {
        // Create the OutsourcingContract with an existing ID
        outsourcingContract.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOutsourcingContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingContract)))
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOutsourcingContracts() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        // Get all the outsourcingContractList
        restOutsourcingContractMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(outsourcingContract.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractid").value(hasItem(DEFAULT_CONTRACTID)))
            .andExpect(jsonPath("$.[*].contractcode").value(hasItem(DEFAULT_CONTRACTCODE)))
            .andExpect(jsonPath("$.[*].contractname").value(hasItem(DEFAULT_CONTRACTNAME)))
            .andExpect(jsonPath("$.[*].contractqualityid").value(hasItem(DEFAULT_CONTRACTQUALITYID)))
            .andExpect(jsonPath("$.[*].contractcostid").value(hasItem(DEFAULT_CONTRACTCOSTID)))
            .andExpect(jsonPath("$.[*].contractfinanceid").value(hasItem(DEFAULT_CONTRACTFINANCEID)))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID)))
            .andExpect(jsonPath("$.[*].projectsecretlevel").value(hasItem(DEFAULT_PROJECTSECRETLEVEL)))
            .andExpect(jsonPath("$.[*].counterpartyunit").value(hasItem(DEFAULT_COUNTERPARTYUNIT)))
            .andExpect(jsonPath("$.[*].negotiationdate").value(hasItem(DEFAULT_NEGOTIATIONDATE.toString())))
            .andExpect(jsonPath("$.[*].negotiationlocation").value(hasItem(DEFAULT_NEGOTIATIONLOCATION)))
            .andExpect(jsonPath("$.[*].negotiator").value(hasItem(DEFAULT_NEGOTIATOR)))
            .andExpect(jsonPath("$.[*].budgetamount").value(hasItem(sameNumber(DEFAULT_BUDGETAMOUNT))))
            .andExpect(jsonPath("$.[*].contractamount").value(hasItem(sameNumber(DEFAULT_CONTRACTAMOUNT))))
            .andExpect(jsonPath("$.[*].approver").value(hasItem(DEFAULT_APPROVER)))
            .andExpect(jsonPath("$.[*].approvaldate").value(hasItem(DEFAULT_APPROVALDATE.toString())))
            .andExpect(jsonPath("$.[*].contractsecretlevel").value(hasItem(DEFAULT_CONTRACTSECRETLEVEL)))
            .andExpect(jsonPath("$.[*].deliverycontent").value(hasItem(DEFAULT_DELIVERYCONTENT)))
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

    @SuppressWarnings({ "unchecked" })
    void getAllOutsourcingContractsWithEagerRelationshipsIsEnabled() throws Exception {
        when(outsourcingContractServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOutsourcingContractMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(outsourcingContractServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllOutsourcingContractsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(outsourcingContractServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOutsourcingContractMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(outsourcingContractRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getOutsourcingContract() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        // Get the outsourcingContract
        restOutsourcingContractMockMvc
            .perform(get(ENTITY_API_URL_ID, outsourcingContract.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(outsourcingContract.getId().intValue()))
            .andExpect(jsonPath("$.contractid").value(DEFAULT_CONTRACTID))
            .andExpect(jsonPath("$.contractcode").value(DEFAULT_CONTRACTCODE))
            .andExpect(jsonPath("$.contractname").value(DEFAULT_CONTRACTNAME))
            .andExpect(jsonPath("$.contractqualityid").value(DEFAULT_CONTRACTQUALITYID))
            .andExpect(jsonPath("$.contractcostid").value(DEFAULT_CONTRACTCOSTID))
            .andExpect(jsonPath("$.contractfinanceid").value(DEFAULT_CONTRACTFINANCEID))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID))
            .andExpect(jsonPath("$.projectsecretlevel").value(DEFAULT_PROJECTSECRETLEVEL))
            .andExpect(jsonPath("$.counterpartyunit").value(DEFAULT_COUNTERPARTYUNIT))
            .andExpect(jsonPath("$.negotiationdate").value(DEFAULT_NEGOTIATIONDATE.toString()))
            .andExpect(jsonPath("$.negotiationlocation").value(DEFAULT_NEGOTIATIONLOCATION))
            .andExpect(jsonPath("$.negotiator").value(DEFAULT_NEGOTIATOR))
            .andExpect(jsonPath("$.budgetamount").value(sameNumber(DEFAULT_BUDGETAMOUNT)))
            .andExpect(jsonPath("$.contractamount").value(sameNumber(DEFAULT_CONTRACTAMOUNT)))
            .andExpect(jsonPath("$.approver").value(DEFAULT_APPROVER))
            .andExpect(jsonPath("$.approvaldate").value(DEFAULT_APPROVALDATE.toString()))
            .andExpect(jsonPath("$.contractsecretlevel").value(DEFAULT_CONTRACTSECRETLEVEL))
            .andExpect(jsonPath("$.deliverycontent").value(DEFAULT_DELIVERYCONTENT))
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
    void getNonExistingOutsourcingContract() throws Exception {
        // Get the outsourcingContract
        restOutsourcingContractMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOutsourcingContract() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingContract
        OutsourcingContract updatedOutsourcingContract = outsourcingContractRepository.findById(outsourcingContract.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOutsourcingContract are not directly saved in db
        em.detach(updatedOutsourcingContract);
        updatedOutsourcingContract
            .contractid(UPDATED_CONTRACTID)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .contractqualityid(UPDATED_CONTRACTQUALITYID)
            .contractcostid(UPDATED_CONTRACTCOSTID)
            .contractfinanceid(UPDATED_CONTRACTFINANCEID)
            .projectid(UPDATED_PROJECTID)
            .projectsecretlevel(UPDATED_PROJECTSECRETLEVEL)
            .counterpartyunit(UPDATED_COUNTERPARTYUNIT)
            .negotiationdate(UPDATED_NEGOTIATIONDATE)
            .negotiationlocation(UPDATED_NEGOTIATIONLOCATION)
            .negotiator(UPDATED_NEGOTIATOR)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .contractamount(UPDATED_CONTRACTAMOUNT)
            .approver(UPDATED_APPROVER)
            .approvaldate(UPDATED_APPROVALDATE)
            .contractsecretlevel(UPDATED_CONTRACTSECRETLEVEL)
            .deliverycontent(UPDATED_DELIVERYCONTENT)
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

        restOutsourcingContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOutsourcingContract.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOutsourcingContract))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOutsourcingContractToMatchAllProperties(updatedOutsourcingContract);
    }

    @Test
    @Transactional
    void putNonExistingOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, outsourcingContract.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingContract))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingContract))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingContract)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOutsourcingContractWithPatch() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingContract using partial update
        OutsourcingContract partialUpdatedOutsourcingContract = new OutsourcingContract();
        partialUpdatedOutsourcingContract.setId(outsourcingContract.getId());

        partialUpdatedOutsourcingContract
            .contractcostid(UPDATED_CONTRACTCOSTID)
            .contractfinanceid(UPDATED_CONTRACTFINANCEID)
            .projectid(UPDATED_PROJECTID)
            .negotiationdate(UPDATED_NEGOTIATIONDATE)
            .negotiationlocation(UPDATED_NEGOTIATIONLOCATION)
            .contractamount(UPDATED_CONTRACTAMOUNT)
            .approvaldate(UPDATED_APPROVALDATE)
            .deliverycontent(UPDATED_DELIVERYCONTENT)
            .warrantyrequirement(UPDATED_WARRANTYREQUIREMENT)
            .purchaseplanamount(UPDATED_PURCHASEPLANAMOUNT)
            .requirementperson(UPDATED_REQUIREMENTPERSON)
            .undertaker(UPDATED_UNDERTAKER)
            .projectmanager(UPDATED_PROJECTMANAGER)
            .reasonfornosuppliers(UPDATED_REASONFORNOSUPPLIERS)
            .judges(UPDATED_JUDGES)
            .finalquoteandscore(UPDATED_FINALQUOTEANDSCORE)
            .noticeofcompletiontime(UPDATED_NOTICEOFCOMPLETIONTIME)
            .contractenddate(UPDATED_CONTRACTENDDATE)
            .issubmitsecrecyagreement(UPDATED_ISSUBMITSECRECYAGREEMENT)
            .issubmitsecurityagreement(UPDATED_ISSUBMITSECURITYAGREEMENT);

        restOutsourcingContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingContract))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingContract in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingContractUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOutsourcingContract, outsourcingContract),
            getPersistedOutsourcingContract(outsourcingContract)
        );
    }

    @Test
    @Transactional
    void fullUpdateOutsourcingContractWithPatch() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingContract using partial update
        OutsourcingContract partialUpdatedOutsourcingContract = new OutsourcingContract();
        partialUpdatedOutsourcingContract.setId(outsourcingContract.getId());

        partialUpdatedOutsourcingContract
            .contractid(UPDATED_CONTRACTID)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .contractqualityid(UPDATED_CONTRACTQUALITYID)
            .contractcostid(UPDATED_CONTRACTCOSTID)
            .contractfinanceid(UPDATED_CONTRACTFINANCEID)
            .projectid(UPDATED_PROJECTID)
            .projectsecretlevel(UPDATED_PROJECTSECRETLEVEL)
            .counterpartyunit(UPDATED_COUNTERPARTYUNIT)
            .negotiationdate(UPDATED_NEGOTIATIONDATE)
            .negotiationlocation(UPDATED_NEGOTIATIONLOCATION)
            .negotiator(UPDATED_NEGOTIATOR)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .contractamount(UPDATED_CONTRACTAMOUNT)
            .approver(UPDATED_APPROVER)
            .approvaldate(UPDATED_APPROVALDATE)
            .contractsecretlevel(UPDATED_CONTRACTSECRETLEVEL)
            .deliverycontent(UPDATED_DELIVERYCONTENT)
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

        restOutsourcingContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingContract))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingContract in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingContractUpdatableFieldsEquals(
            partialUpdatedOutsourcingContract,
            getPersistedOutsourcingContract(partialUpdatedOutsourcingContract)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, outsourcingContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingContract))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingContract))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOutsourcingContract() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingContract.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingContractMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(outsourcingContract)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingContract in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOutsourcingContract() throws Exception {
        // Initialize the database
        insertedOutsourcingContract = outsourcingContractRepository.saveAndFlush(outsourcingContract);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the outsourcingContract
        restOutsourcingContractMockMvc
            .perform(delete(ENTITY_API_URL_ID, outsourcingContract.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return outsourcingContractRepository.count();
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

    protected OutsourcingContract getPersistedOutsourcingContract(OutsourcingContract outsourcingContract) {
        return outsourcingContractRepository.findById(outsourcingContract.getId()).orElseThrow();
    }

    protected void assertPersistedOutsourcingContractToMatchAllProperties(OutsourcingContract expectedOutsourcingContract) {
        assertOutsourcingContractAllPropertiesEquals(
            expectedOutsourcingContract,
            getPersistedOutsourcingContract(expectedOutsourcingContract)
        );
    }

    protected void assertPersistedOutsourcingContractToMatchUpdatableProperties(OutsourcingContract expectedOutsourcingContract) {
        assertOutsourcingContractAllUpdatablePropertiesEquals(
            expectedOutsourcingContract,
            getPersistedOutsourcingContract(expectedOutsourcingContract)
        );
    }
}
