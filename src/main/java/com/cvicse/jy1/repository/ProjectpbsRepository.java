package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Projectpbs;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Projectpbs entity.
 *
 * When extending this class, extend ProjectpbsRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface ProjectpbsRepository extends ProjectpbsRepositoryWithBagRelationships, JpaRepository<Projectpbs, String> {
    default Optional<Projectpbs> findOneWithEagerRelationships(String id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Projectpbs> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Projectpbs> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    @Query("select p from Projectpbs p where p.pbsname like %:name%")
    List<Projectpbs> findAllWithToOneRelationship(@Param("name") String name);
}
