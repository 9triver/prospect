package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.RiskReport;
import com.cvicse.jy1.repository.RiskReportRepository;
import com.cvicse.jy1.service.RiskReportService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.RiskReport}.
 */
@Service
@Transactional
public class RiskReportServiceImpl implements RiskReportService {

    private static final Logger log = LoggerFactory.getLogger(RiskReportServiceImpl.class);

    private final RiskReportRepository riskReportRepository;

    public RiskReportServiceImpl(RiskReportRepository riskReportRepository) {
        this.riskReportRepository = riskReportRepository;
    }

    @Override
    public RiskReport save(RiskReport riskReport) {
        log.debug("Request to save RiskReport : {}", riskReport);
        return riskReportRepository.save(riskReport);
    }

    @Override
    public RiskReport update(RiskReport riskReport) {
        log.debug("Request to update RiskReport : {}", riskReport);
        return riskReportRepository.save(riskReport);
    }

    @Override
    public Optional<RiskReport> partialUpdate(RiskReport riskReport) {
        log.debug("Request to partially update RiskReport : {}", riskReport);

        return riskReportRepository
            .findById(riskReport.getId())
            .map(existingRiskReport -> {
                if (riskReport.getType() != null) {
                    existingRiskReport.setType(riskReport.getType());
                }
                if (riskReport.getYear() != null) {
                    existingRiskReport.setYear(riskReport.getYear());
                }
                if (riskReport.getRiskreportname() != null) {
                    existingRiskReport.setRiskreportname(riskReport.getRiskreportname());
                }
                if (riskReport.getReporttime() != null) {
                    existingRiskReport.setReporttime(riskReport.getReporttime());
                }
                if (riskReport.getAuditStatus() != null) {
                    existingRiskReport.setAuditStatus(riskReport.getAuditStatus());
                }

                return existingRiskReport;
            })
            .map(riskReportRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiskReport> findAll() {
        log.debug("Request to get all RiskReports");
        return riskReportRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RiskReport> findOne(Integer id) {
        log.debug("Request to get RiskReport : {}", id);
        return riskReportRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RiskReport : {}", id);
        riskReportRepository.deleteById(id);
    }
}
