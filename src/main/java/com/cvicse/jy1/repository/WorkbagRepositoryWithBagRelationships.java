package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Workbag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface WorkbagRepositoryWithBagRelationships {
    Optional<Workbag> fetchBagRelationships(Optional<Workbag> workbag);

    List<Workbag> fetchBagRelationships(List<Workbag> workbags);

    Page<Workbag> fetchBagRelationships(Page<Workbag> workbags);
}
