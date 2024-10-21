package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.LetterAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Letter;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.LetterRepository;
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
 * Integration tests for the {@link LetterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LetterResourceIT {

    private static final String DEFAULT_LETTERNAME = "AAAAAAAAAA";
    private static final String UPDATED_LETTERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LETTERNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LETTERNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_LETTERTYPE = "AAAAAAAAAA";
    private static final String UPDATED_LETTERTYPE = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.PUBLIC;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.INTERNAL;

    private static final String DEFAULT_LETTERCONTENT = "AAAAAAAAAA";
    private static final String UPDATED_LETTERCONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_LETTERSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_LETTERSTATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LETTERTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LETTERTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PREVIOUSFILE = "AAAAAAAAAA";
    private static final String UPDATED_PREVIOUSFILE = "BBBBBBBBBB";

    private static final String DEFAULT_DATARECORDSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_DATARECORDSTATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/letters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLetterMockMvc;

    private Letter letter;

    private Letter insertedLetter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Letter createEntity(EntityManager em) {
        Letter letter = new Letter()
            .lettername(DEFAULT_LETTERNAME)
            .letternumber(DEFAULT_LETTERNUMBER)
            .lettertype(DEFAULT_LETTERTYPE)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .lettercontent(DEFAULT_LETTERCONTENT)
            .letterstatus(DEFAULT_LETTERSTATUS)
            .lettertime(DEFAULT_LETTERTIME)
            .previousfile(DEFAULT_PREVIOUSFILE)
            .datarecordstatus(DEFAULT_DATARECORDSTATUS);
        return letter;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Letter createUpdatedEntity(EntityManager em) {
        Letter letter = new Letter()
            .lettername(UPDATED_LETTERNAME)
            .letternumber(UPDATED_LETTERNUMBER)
            .lettertype(UPDATED_LETTERTYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .lettercontent(UPDATED_LETTERCONTENT)
            .letterstatus(UPDATED_LETTERSTATUS)
            .lettertime(UPDATED_LETTERTIME)
            .previousfile(UPDATED_PREVIOUSFILE)
            .datarecordstatus(UPDATED_DATARECORDSTATUS);
        return letter;
    }

    @BeforeEach
    public void initTest() {
        letter = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedLetter != null) {
            letterRepository.delete(insertedLetter);
            insertedLetter = null;
        }
    }

    @Test
    @Transactional
    void createLetter() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Letter
        var returnedLetter = om.readValue(
            restLetterMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(letter)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Letter.class
        );

        // Validate the Letter in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertLetterUpdatableFieldsEquals(returnedLetter, getPersistedLetter(returnedLetter));

        insertedLetter = returnedLetter;
    }

    @Test
    @Transactional
    void createLetterWithExistingId() throws Exception {
        // Create the Letter with an existing ID
        letter.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLetterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(letter)))
            .andExpect(status().isBadRequest());

        // Validate the Letter in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLetters() throws Exception {
        // Initialize the database
        insertedLetter = letterRepository.saveAndFlush(letter);

        // Get all the letterList
        restLetterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(letter.getId().intValue())))
            .andExpect(jsonPath("$.[*].lettername").value(hasItem(DEFAULT_LETTERNAME)))
            .andExpect(jsonPath("$.[*].letternumber").value(hasItem(DEFAULT_LETTERNUMBER)))
            .andExpect(jsonPath("$.[*].lettertype").value(hasItem(DEFAULT_LETTERTYPE)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].lettercontent").value(hasItem(DEFAULT_LETTERCONTENT)))
            .andExpect(jsonPath("$.[*].letterstatus").value(hasItem(DEFAULT_LETTERSTATUS)))
            .andExpect(jsonPath("$.[*].lettertime").value(hasItem(DEFAULT_LETTERTIME.toString())))
            .andExpect(jsonPath("$.[*].previousfile").value(hasItem(DEFAULT_PREVIOUSFILE)))
            .andExpect(jsonPath("$.[*].datarecordstatus").value(hasItem(DEFAULT_DATARECORDSTATUS)));
    }

    @Test
    @Transactional
    void getLetter() throws Exception {
        // Initialize the database
        insertedLetter = letterRepository.saveAndFlush(letter);

        // Get the letter
        restLetterMockMvc
            .perform(get(ENTITY_API_URL_ID, letter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(letter.getId().intValue()))
            .andExpect(jsonPath("$.lettername").value(DEFAULT_LETTERNAME))
            .andExpect(jsonPath("$.letternumber").value(DEFAULT_LETTERNUMBER))
            .andExpect(jsonPath("$.lettertype").value(DEFAULT_LETTERTYPE))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.lettercontent").value(DEFAULT_LETTERCONTENT))
            .andExpect(jsonPath("$.letterstatus").value(DEFAULT_LETTERSTATUS))
            .andExpect(jsonPath("$.lettertime").value(DEFAULT_LETTERTIME.toString()))
            .andExpect(jsonPath("$.previousfile").value(DEFAULT_PREVIOUSFILE))
            .andExpect(jsonPath("$.datarecordstatus").value(DEFAULT_DATARECORDSTATUS));
    }

    @Test
    @Transactional
    void getNonExistingLetter() throws Exception {
        // Get the letter
        restLetterMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLetter() throws Exception {
        // Initialize the database
        insertedLetter = letterRepository.saveAndFlush(letter);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the letter
        Letter updatedLetter = letterRepository.findById(letter.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLetter are not directly saved in db
        em.detach(updatedLetter);
        updatedLetter
            .lettername(UPDATED_LETTERNAME)
            .letternumber(UPDATED_LETTERNUMBER)
            .lettertype(UPDATED_LETTERTYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .lettercontent(UPDATED_LETTERCONTENT)
            .letterstatus(UPDATED_LETTERSTATUS)
            .lettertime(UPDATED_LETTERTIME)
            .previousfile(UPDATED_PREVIOUSFILE)
            .datarecordstatus(UPDATED_DATARECORDSTATUS);

        restLetterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLetter.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedLetter))
            )
            .andExpect(status().isOk());

        // Validate the Letter in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLetterToMatchAllProperties(updatedLetter);
    }

    @Test
    @Transactional
    void putNonExistingLetter() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        letter.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLetterMockMvc
            .perform(put(ENTITY_API_URL_ID, letter.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(letter)))
            .andExpect(status().isBadRequest());

        // Validate the Letter in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLetter() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        letter.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLetterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(letter))
            )
            .andExpect(status().isBadRequest());

        // Validate the Letter in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLetter() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        letter.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLetterMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(letter)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Letter in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLetterWithPatch() throws Exception {
        // Initialize the database
        insertedLetter = letterRepository.saveAndFlush(letter);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the letter using partial update
        Letter partialUpdatedLetter = new Letter();
        partialUpdatedLetter.setId(letter.getId());

        partialUpdatedLetter
            .lettername(UPDATED_LETTERNAME)
            .letternumber(UPDATED_LETTERNUMBER)
            .lettertype(UPDATED_LETTERTYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .lettercontent(UPDATED_LETTERCONTENT)
            .letterstatus(UPDATED_LETTERSTATUS)
            .lettertime(UPDATED_LETTERTIME);

        restLetterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLetter.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLetter))
            )
            .andExpect(status().isOk());

        // Validate the Letter in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLetterUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedLetter, letter), getPersistedLetter(letter));
    }

    @Test
    @Transactional
    void fullUpdateLetterWithPatch() throws Exception {
        // Initialize the database
        insertedLetter = letterRepository.saveAndFlush(letter);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the letter using partial update
        Letter partialUpdatedLetter = new Letter();
        partialUpdatedLetter.setId(letter.getId());

        partialUpdatedLetter
            .lettername(UPDATED_LETTERNAME)
            .letternumber(UPDATED_LETTERNUMBER)
            .lettertype(UPDATED_LETTERTYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .lettercontent(UPDATED_LETTERCONTENT)
            .letterstatus(UPDATED_LETTERSTATUS)
            .lettertime(UPDATED_LETTERTIME)
            .previousfile(UPDATED_PREVIOUSFILE)
            .datarecordstatus(UPDATED_DATARECORDSTATUS);

        restLetterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLetter.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLetter))
            )
            .andExpect(status().isOk());

        // Validate the Letter in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLetterUpdatableFieldsEquals(partialUpdatedLetter, getPersistedLetter(partialUpdatedLetter));
    }

    @Test
    @Transactional
    void patchNonExistingLetter() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        letter.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLetterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, letter.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(letter))
            )
            .andExpect(status().isBadRequest());

        // Validate the Letter in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLetter() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        letter.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLetterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(letter))
            )
            .andExpect(status().isBadRequest());

        // Validate the Letter in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLetter() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        letter.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLetterMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(letter)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Letter in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLetter() throws Exception {
        // Initialize the database
        insertedLetter = letterRepository.saveAndFlush(letter);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the letter
        restLetterMockMvc
            .perform(delete(ENTITY_API_URL_ID, letter.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return letterRepository.count();
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

    protected Letter getPersistedLetter(Letter letter) {
        return letterRepository.findById(letter.getId()).orElseThrow();
    }

    protected void assertPersistedLetterToMatchAllProperties(Letter expectedLetter) {
        assertLetterAllPropertiesEquals(expectedLetter, getPersistedLetter(expectedLetter));
    }

    protected void assertPersistedLetterToMatchUpdatableProperties(Letter expectedLetter) {
        assertLetterAllUpdatablePropertiesEquals(expectedLetter, getPersistedLetter(expectedLetter));
    }
}
