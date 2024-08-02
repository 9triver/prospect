package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Technical;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface TechnicalRepositoryWithBagRelationships {
    Optional<Technical> fetchBagRelationships(Optional<Technical> technical);

    List<Technical> fetchBagRelationships(List<Technical> technicals);

    Page<Technical> fetchBagRelationships(Page<Technical> technicals);
}
