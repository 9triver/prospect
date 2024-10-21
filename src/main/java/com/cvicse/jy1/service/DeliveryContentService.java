package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.DeliveryContent;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.DeliveryContent}.
 */
public interface DeliveryContentService {
    /**
     * Save a deliveryContent.
     *
     * @param deliveryContent the entity to save.
     * @return the persisted entity.
     */
    DeliveryContent save(DeliveryContent deliveryContent);

    /**
     * Updates a deliveryContent.
     *
     * @param deliveryContent the entity to update.
     * @return the persisted entity.
     */
    DeliveryContent update(DeliveryContent deliveryContent);

    /**
     * Partially updates a deliveryContent.
     *
     * @param deliveryContent the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DeliveryContent> partialUpdate(DeliveryContent deliveryContent);

    /**
     * Get all the deliveryContents.
     *
     * @return the list of entities.
     */
    List<DeliveryContent> findAll();

    /**
     * Get the "id" deliveryContent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeliveryContent> findOne(Integer id);

    /**
     * Delete the "id" deliveryContent.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
