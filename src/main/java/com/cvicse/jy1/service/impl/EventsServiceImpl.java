package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Events;
import com.cvicse.jy1.repository.EventsRepository;
import com.cvicse.jy1.service.EventsService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Events}.
 */
@Service
@Transactional
public class EventsServiceImpl implements EventsService {

    private static final Logger log = LoggerFactory.getLogger(EventsServiceImpl.class);

    private final EventsRepository eventsRepository;

    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public Events save(Events events) {
        log.debug("Request to save Events : {}", events);
        return eventsRepository.save(events);
    }

    @Override
    public Events update(Events events) {
        log.debug("Request to update Events : {}", events);
        return eventsRepository.save(events);
    }

    @Override
    public Optional<Events> partialUpdate(Events events) {
        log.debug("Request to partially update Events : {}", events);

        return eventsRepository
            .findById(events.getId())
            .map(existingEvents -> {
                if (events.getTitle() != null) {
                    existingEvents.setTitle(events.getTitle());
                }
                if (events.getContent() != null) {
                    existingEvents.setContent(events.getContent());
                }
                if (events.getTime() != null) {
                    existingEvents.setTime(events.getTime());
                }
                if (events.getPlace() != null) {
                    existingEvents.setPlace(events.getPlace());
                }
                if (events.getParticipants() != null) {
                    existingEvents.setParticipants(events.getParticipants());
                }
                if (events.getPicture() != null) {
                    existingEvents.setPicture(events.getPicture());
                }
                if (events.getDescription() != null) {
                    existingEvents.setDescription(events.getDescription());
                }
                if (events.getSecretlevel() != null) {
                    existingEvents.setSecretlevel(events.getSecretlevel());
                }
                if (events.getAttachment() != null) {
                    existingEvents.setAttachment(events.getAttachment());
                }

                return existingEvents;
            })
            .map(eventsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Events> findAll() {
        log.debug("Request to get all Events");
        return eventsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Events> findOne(String id) {
        log.debug("Request to get Events : {}", id);
        return eventsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Events : {}", id);
        eventsRepository.deleteById(id);
    }
}
