package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.OutsourcingContract;
import com.cvicse.jy1.repository.OutsourcingContractRepository;
import com.cvicse.jy1.service.OutsourcingContractService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.OutsourcingContract}.
 */
@Service
@Transactional
public class OutsourcingContractServiceImpl implements OutsourcingContractService {

    private static final Logger log = LoggerFactory.getLogger(OutsourcingContractServiceImpl.class);

    private final OutsourcingContractRepository outsourcingContractRepository;

    public OutsourcingContractServiceImpl(OutsourcingContractRepository outsourcingContractRepository) {
        this.outsourcingContractRepository = outsourcingContractRepository;
    }

    @Override
    public OutsourcingContract save(OutsourcingContract outsourcingContract) {
        log.debug("Request to save OutsourcingContract : {}", outsourcingContract);
        return outsourcingContractRepository.save(outsourcingContract);
    }

    @Override
    public OutsourcingContract update(OutsourcingContract outsourcingContract) {
        log.debug("Request to update OutsourcingContract : {}", outsourcingContract);
        return outsourcingContractRepository.save(outsourcingContract);
    }

    @Override
    public Optional<OutsourcingContract> partialUpdate(OutsourcingContract outsourcingContract) {
        log.debug("Request to partially update OutsourcingContract : {}", outsourcingContract);

        return outsourcingContractRepository
            .findById(outsourcingContract.getId())
            .map(existingOutsourcingContract -> {
                if (outsourcingContract.getContractid() != null) {
                    existingOutsourcingContract.setContractid(outsourcingContract.getContractid());
                }
                if (outsourcingContract.getContractcode() != null) {
                    existingOutsourcingContract.setContractcode(outsourcingContract.getContractcode());
                }
                if (outsourcingContract.getContractname() != null) {
                    existingOutsourcingContract.setContractname(outsourcingContract.getContractname());
                }
                if (outsourcingContract.getContractqualityid() != null) {
                    existingOutsourcingContract.setContractqualityid(outsourcingContract.getContractqualityid());
                }
                if (outsourcingContract.getContractcostid() != null) {
                    existingOutsourcingContract.setContractcostid(outsourcingContract.getContractcostid());
                }
                if (outsourcingContract.getContractfinanceid() != null) {
                    existingOutsourcingContract.setContractfinanceid(outsourcingContract.getContractfinanceid());
                }
                if (outsourcingContract.getProjectid() != null) {
                    existingOutsourcingContract.setProjectid(outsourcingContract.getProjectid());
                }
                if (outsourcingContract.getProjectsecretlevel() != null) {
                    existingOutsourcingContract.setProjectsecretlevel(outsourcingContract.getProjectsecretlevel());
                }
                if (outsourcingContract.getCounterpartyunit() != null) {
                    existingOutsourcingContract.setCounterpartyunit(outsourcingContract.getCounterpartyunit());
                }
                if (outsourcingContract.getNegotiationdate() != null) {
                    existingOutsourcingContract.setNegotiationdate(outsourcingContract.getNegotiationdate());
                }
                if (outsourcingContract.getNegotiationlocation() != null) {
                    existingOutsourcingContract.setNegotiationlocation(outsourcingContract.getNegotiationlocation());
                }
                if (outsourcingContract.getNegotiator() != null) {
                    existingOutsourcingContract.setNegotiator(outsourcingContract.getNegotiator());
                }
                if (outsourcingContract.getBudgetamount() != null) {
                    existingOutsourcingContract.setBudgetamount(outsourcingContract.getBudgetamount());
                }
                if (outsourcingContract.getContractamount() != null) {
                    existingOutsourcingContract.setContractamount(outsourcingContract.getContractamount());
                }
                if (outsourcingContract.getApprover() != null) {
                    existingOutsourcingContract.setApprover(outsourcingContract.getApprover());
                }
                if (outsourcingContract.getApprovaldate() != null) {
                    existingOutsourcingContract.setApprovaldate(outsourcingContract.getApprovaldate());
                }
                if (outsourcingContract.getContractsecretlevel() != null) {
                    existingOutsourcingContract.setContractsecretlevel(outsourcingContract.getContractsecretlevel());
                }
                if (outsourcingContract.getDeliverycontent() != null) {
                    existingOutsourcingContract.setDeliverycontent(outsourcingContract.getDeliverycontent());
                }
                if (outsourcingContract.getWarrantyrequirement() != null) {
                    existingOutsourcingContract.setWarrantyrequirement(outsourcingContract.getWarrantyrequirement());
                }
                if (outsourcingContract.getPurchaseplanno() != null) {
                    existingOutsourcingContract.setPurchaseplanno(outsourcingContract.getPurchaseplanno());
                }
                if (outsourcingContract.getPurchaseplandate() != null) {
                    existingOutsourcingContract.setPurchaseplandate(outsourcingContract.getPurchaseplandate());
                }
                if (outsourcingContract.getPurchaseplanamount() != null) {
                    existingOutsourcingContract.setPurchaseplanamount(outsourcingContract.getPurchaseplanamount());
                }
                if (outsourcingContract.getPurchasemethod() != null) {
                    existingOutsourcingContract.setPurchasemethod(outsourcingContract.getPurchasemethod());
                }
                if (outsourcingContract.getPurchasesecretlevel() != null) {
                    existingOutsourcingContract.setPurchasesecretlevel(outsourcingContract.getPurchasesecretlevel());
                }
                if (outsourcingContract.getReviewmethod() != null) {
                    existingOutsourcingContract.setReviewmethod(outsourcingContract.getReviewmethod());
                }
                if (outsourcingContract.getRequirementdepartment() != null) {
                    existingOutsourcingContract.setRequirementdepartment(outsourcingContract.getRequirementdepartment());
                }
                if (outsourcingContract.getRequirementperson() != null) {
                    existingOutsourcingContract.setRequirementperson(outsourcingContract.getRequirementperson());
                }
                if (outsourcingContract.getUndertaker() != null) {
                    existingOutsourcingContract.setUndertaker(outsourcingContract.getUndertaker());
                }
                if (outsourcingContract.getUndertakingdepartment() != null) {
                    existingOutsourcingContract.setUndertakingdepartment(outsourcingContract.getUndertakingdepartment());
                }
                if (outsourcingContract.getWorkbagid() != null) {
                    existingOutsourcingContract.setWorkbagid(outsourcingContract.getWorkbagid());
                }
                if (outsourcingContract.getProjectmanager() != null) {
                    existingOutsourcingContract.setProjectmanager(outsourcingContract.getProjectmanager());
                }
                if (outsourcingContract.getFundsource() != null) {
                    existingOutsourcingContract.setFundsource(outsourcingContract.getFundsource());
                }
                if (outsourcingContract.getThesisname() != null) {
                    existingOutsourcingContract.setThesisname(outsourcingContract.getThesisname());
                }
                if (outsourcingContract.getContractauxiliaryno() != null) {
                    existingOutsourcingContract.setContractauxiliaryno(outsourcingContract.getContractauxiliaryno());
                }
                if (outsourcingContract.getReasonfornosuppliers() != null) {
                    existingOutsourcingContract.setReasonfornosuppliers(outsourcingContract.getReasonfornosuppliers());
                }
                if (outsourcingContract.getReasonforchange() != null) {
                    existingOutsourcingContract.setReasonforchange(outsourcingContract.getReasonforchange());
                }
                if (outsourcingContract.getNegotiationfiletime() != null) {
                    existingOutsourcingContract.setNegotiationfiletime(outsourcingContract.getNegotiationfiletime());
                }
                if (outsourcingContract.getBidopeningtime() != null) {
                    existingOutsourcingContract.setBidopeningtime(outsourcingContract.getBidopeningtime());
                }
                if (outsourcingContract.getJudges() != null) {
                    existingOutsourcingContract.setJudges(outsourcingContract.getJudges());
                }
                if (outsourcingContract.getResponsevendorname() != null) {
                    existingOutsourcingContract.setResponsevendorname(outsourcingContract.getResponsevendorname());
                }
                if (outsourcingContract.getFinalquoteandscore() != null) {
                    existingOutsourcingContract.setFinalquoteandscore(outsourcingContract.getFinalquoteandscore());
                }
                if (outsourcingContract.getNoticeofcompletiontime() != null) {
                    existingOutsourcingContract.setNoticeofcompletiontime(outsourcingContract.getNoticeofcompletiontime());
                }
                if (outsourcingContract.getSigningdate() != null) {
                    existingOutsourcingContract.setSigningdate(outsourcingContract.getSigningdate());
                }
                if (outsourcingContract.getContractenddate() != null) {
                    existingOutsourcingContract.setContractenddate(outsourcingContract.getContractenddate());
                }
                if (outsourcingContract.getActualcompletiontime() != null) {
                    existingOutsourcingContract.setActualcompletiontime(outsourcingContract.getActualcompletiontime());
                }
                if (outsourcingContract.getIssubmitsecrecyagreement() != null) {
                    existingOutsourcingContract.setIssubmitsecrecyagreement(outsourcingContract.getIssubmitsecrecyagreement());
                }
                if (outsourcingContract.getIssubmitsecurityagreement() != null) {
                    existingOutsourcingContract.setIssubmitsecurityagreement(outsourcingContract.getIssubmitsecurityagreement());
                }
                if (outsourcingContract.getRemark() != null) {
                    existingOutsourcingContract.setRemark(outsourcingContract.getRemark());
                }

                return existingOutsourcingContract;
            })
            .map(outsourcingContractRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OutsourcingContract> findAll() {
        log.debug("Request to get all OutsourcingContracts");
        return outsourcingContractRepository.findAll();
    }

    public Page<OutsourcingContract> findAllWithEagerRelationships(Pageable pageable) {
        return outsourcingContractRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OutsourcingContract> findOne(Integer id) {
        log.debug("Request to get OutsourcingContract : {}", id);
        return outsourcingContractRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete OutsourcingContract : {}", id);
        outsourcingContractRepository.deleteById(id);
    }
}
