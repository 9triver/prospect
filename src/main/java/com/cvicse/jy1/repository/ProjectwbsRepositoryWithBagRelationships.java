package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Projectwbs;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ProjectwbsRepositoryWithBagRelationships {
    Optional<Projectwbs> fetchBagRelationships(Optional<Projectwbs> projectwbs);

    List<Projectwbs> fetchBagRelationships(List<Projectwbs> projectwbs);

    Page<Projectwbs> fetchBagRelationships(Page<Projectwbs> projectwbs);
}
