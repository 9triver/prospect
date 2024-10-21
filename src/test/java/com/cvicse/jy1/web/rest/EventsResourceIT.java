package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.EventsAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Events;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.EventsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
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
 * Integration tests for the {@link EventsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EventsResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_PARTICIPANTS = "AAAAAAAAAA";
    private static final String UPDATED_PARTICIPANTS = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.PUBLIC;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.INTERNAL;

    private static final String DEFAULT_ATTACHMENT = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEventsMockMvc;

    private Events events;

    private Events insertedEvents;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Events createEntity(EntityManager em) {
        Events events = new Events()
            .title(DEFAULT_TITLE)
            .content(DEFAULT_CONTENT)
            .time(DEFAULT_TIME)
            .place(DEFAULT_PLACE)
            .participants(DEFAULT_PARTICIPANTS)
            .picture(DEFAULT_PICTURE)
            .description(DEFAULT_DESCRIPTION)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .attachment(DEFAULT_ATTACHMENT);
        return events;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Events createUpdatedEntity(EntityManager em) {
        Events events = new Events()
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .time(UPDATED_TIME)
            .place(UPDATED_PLACE)
            .participants(UPDATED_PARTICIPANTS)
            .picture(UPDATED_PICTURE)
            .description(UPDATED_DESCRIPTION)
            .secretlevel(UPDATED_SECRETLEVEL)
            .attachment(UPDATED_ATTACHMENT);
        return events;
    }

    @BeforeEach
    public void initTest() {
        events = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedEvents != null) {
            eventsRepository.delete(insertedEvents);
            insertedEvents = null;
        }
    }

    @Test
    @Transactional
    void createEvents() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Events
        var returnedEvents = om.readValue(
            restEventsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(events)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Events.class
        );

        // Validate the Events in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEventsUpdatableFieldsEquals(returnedEvents, getPersistedEvents(returnedEvents));

        insertedEvents = returnedEvents;
    }

    @Test
    @Transactional
    void createEventsWithExistingId() throws Exception {
        // Create the Events with an existing ID
        events.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(events)))
            .andExpect(status().isBadRequest());

        // Validate the Events in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEvents() throws Exception {
        // Initialize the database
        insertedEvents = eventsRepository.saveAndFlush(events);

        // Get all the eventsList
        restEventsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(events.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].place").value(hasItem(DEFAULT_PLACE)))
            .andExpect(jsonPath("$.[*].participants").value(hasItem(DEFAULT_PARTICIPANTS)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].attachment").value(hasItem(DEFAULT_ATTACHMENT)));
    }

    @Test
    @Transactional
    void getEvents() throws Exception {
        // Initialize the database
        insertedEvents = eventsRepository.saveAndFlush(events);

        // Get the events
        restEventsMockMvc
            .perform(get(ENTITY_API_URL_ID, events.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(events.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.place").value(DEFAULT_PLACE))
            .andExpect(jsonPath("$.participants").value(DEFAULT_PARTICIPANTS))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.attachment").value(DEFAULT_ATTACHMENT));
    }

    @Test
    @Transactional
    void getNonExistingEvents() throws Exception {
        // Get the events
        restEventsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEvents() throws Exception {
        // Initialize the database
        insertedEvents = eventsRepository.saveAndFlush(events);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the events
        Events updatedEvents = eventsRepository.findById(events.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEvents are not directly saved in db
        em.detach(updatedEvents);
        updatedEvents
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .time(UPDATED_TIME)
            .place(UPDATED_PLACE)
            .participants(UPDATED_PARTICIPANTS)
            .picture(UPDATED_PICTURE)
            .description(UPDATED_DESCRIPTION)
            .secretlevel(UPDATED_SECRETLEVEL)
            .attachment(UPDATED_ATTACHMENT);

        restEventsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEvents.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEvents))
            )
            .andExpect(status().isOk());

        // Validate the Events in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEventsToMatchAllProperties(updatedEvents);
    }

    @Test
    @Transactional
    void putNonExistingEvents() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        events.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventsMockMvc
            .perform(put(ENTITY_API_URL_ID, events.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(events)))
            .andExpect(status().isBadRequest());

        // Validate the Events in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEvents() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        events.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEventsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(events))
            )
            .andExpect(status().isBadRequest());

        // Validate the Events in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEvents() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        events.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEventsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(events)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Events in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEventsWithPatch() throws Exception {
        // Initialize the database
        insertedEvents = eventsRepository.saveAndFlush(events);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the events using partial update
        Events partialUpdatedEvents = new Events();
        partialUpdatedEvents.setId(events.getId());

        partialUpdatedEvents
            .content(UPDATED_CONTENT)
            .time(UPDATED_TIME)
            .participants(UPDATED_PARTICIPANTS)
            .secretlevel(UPDATED_SECRETLEVEL)
            .attachment(UPDATED_ATTACHMENT);

        restEventsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEvents.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEvents))
            )
            .andExpect(status().isOk());

        // Validate the Events in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEventsUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedEvents, events), getPersistedEvents(events));
    }

    @Test
    @Transactional
    void fullUpdateEventsWithPatch() throws Exception {
        // Initialize the database
        insertedEvents = eventsRepository.saveAndFlush(events);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the events using partial update
        Events partialUpdatedEvents = new Events();
        partialUpdatedEvents.setId(events.getId());

        partialUpdatedEvents
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .time(UPDATED_TIME)
            .place(UPDATED_PLACE)
            .participants(UPDATED_PARTICIPANTS)
            .picture(UPDATED_PICTURE)
            .description(UPDATED_DESCRIPTION)
            .secretlevel(UPDATED_SECRETLEVEL)
            .attachment(UPDATED_ATTACHMENT);

        restEventsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEvents.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEvents))
            )
            .andExpect(status().isOk());

        // Validate the Events in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEventsUpdatableFieldsEquals(partialUpdatedEvents, getPersistedEvents(partialUpdatedEvents));
    }

    @Test
    @Transactional
    void patchNonExistingEvents() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        events.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, events.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(events))
            )
            .andExpect(status().isBadRequest());

        // Validate the Events in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEvents() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        events.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEventsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(events))
            )
            .andExpect(status().isBadRequest());

        // Validate the Events in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEvents() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        events.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEventsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(events)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Events in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEvents() throws Exception {
        // Initialize the database
        insertedEvents = eventsRepository.saveAndFlush(events);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the events
        restEventsMockMvc
            .perform(delete(ENTITY_API_URL_ID, events.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return eventsRepository.count();
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

    protected Events getPersistedEvents(Events events) {
        return eventsRepository.findById(events.getId()).orElseThrow();
    }

    protected void assertPersistedEventsToMatchAllProperties(Events expectedEvents) {
        assertEventsAllPropertiesEquals(expectedEvents, getPersistedEvents(expectedEvents));
    }

    protected void assertPersistedEventsToMatchUpdatableProperties(Events expectedEvents) {
        assertEventsAllUpdatablePropertiesEquals(expectedEvents, getPersistedEvents(expectedEvents));
    }
}
