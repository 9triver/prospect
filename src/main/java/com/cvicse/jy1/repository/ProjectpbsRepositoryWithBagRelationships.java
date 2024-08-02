package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Projectpbs;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ProjectpbsRepositoryWithBagRelationships {
    Optional<Projectpbs> fetchBagRelationships(Optional<Projectpbs> projectpbs);

    List<Projectpbs> fetchBagRelationships(List<Projectpbs> projectpbs);

    Page<Projectpbs> fetchBagRelationships(Page<Projectpbs> projectpbs);
}
