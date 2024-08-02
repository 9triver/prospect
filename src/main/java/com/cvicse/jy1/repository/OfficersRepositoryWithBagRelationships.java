package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Officers;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface OfficersRepositoryWithBagRelationships {
    Optional<Officers> fetchBagRelationships(Optional<Officers> officers);

    List<Officers> fetchBagRelationships(List<Officers> officers);

    Page<Officers> fetchBagRelationships(Page<Officers> officers);
}
