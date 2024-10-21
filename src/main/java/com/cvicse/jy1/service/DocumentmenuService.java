package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Documentmenu;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Documentmenu}.
 */
public interface DocumentmenuService {
    /**
     * Save a documentmenu.
     *
     * @param documentmenu the entity to save.
     * @return the persisted entity.
     */
    Documentmenu save(Documentmenu documentmenu);

    /**
     * Updates a documentmenu.
     *
     * @param documentmenu the entity to update.
     * @return the persisted entity.
     */
    Documentmenu update(Documentmenu documentmenu);

    /**
     * Partially updates a documentmenu.
     *
     * @param documentmenu the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Documentmenu> partialUpdate(Documentmenu documentmenu);

    /**
     * Get all the documentmenus.
     *
     * @return the list of entities.
     */
    List<Documentmenu> findAll();

    /**
     * Get the "id" documentmenu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Documentmenu> findOne(Integer id);

    /**
     * Delete the "id" documentmenu.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
