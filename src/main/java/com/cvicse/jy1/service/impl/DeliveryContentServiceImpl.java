package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.DeliveryContent;
import com.cvicse.jy1.repository.DeliveryContentRepository;
import com.cvicse.jy1.service.DeliveryContentService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.DeliveryContent}.
 */
@Service
@Transactional
public class DeliveryContentServiceImpl implements DeliveryContentService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryContentServiceImpl.class);

    private final DeliveryContentRepository deliveryContentRepository;

    public DeliveryContentServiceImpl(DeliveryContentRepository deliveryContentRepository) {
        this.deliveryContentRepository = deliveryContentRepository;
    }

    @Override
    public DeliveryContent save(DeliveryContent deliveryContent) {
        log.debug("Request to save DeliveryContent : {}", deliveryContent);
        return deliveryContentRepository.save(deliveryContent);
    }

    @Override
    public DeliveryContent update(DeliveryContent deliveryContent) {
        log.debug("Request to update DeliveryContent : {}", deliveryContent);
        return deliveryContentRepository.save(deliveryContent);
    }

    @Override
    public Optional<DeliveryContent> partialUpdate(DeliveryContent deliveryContent) {
        log.debug("Request to partially update DeliveryContent : {}", deliveryContent);

        return deliveryContentRepository
            .findById(deliveryContent.getId())
            .map(existingDeliveryContent -> {
                if (deliveryContent.getWarrantyrequirement() != null) {
                    existingDeliveryContent.setWarrantyrequirement(deliveryContent.getWarrantyrequirement());
                }
                if (deliveryContent.getPurchaseplanno() != null) {
                    existingDeliveryContent.setPurchaseplanno(deliveryContent.getPurchaseplanno());
                }
                if (deliveryContent.getPurchaseplandate() != null) {
                    existingDeliveryContent.setPurchaseplandate(deliveryContent.getPurchaseplandate());
                }
                if (deliveryContent.getPurchaseplanamount() != null) {
                    existingDeliveryContent.setPurchaseplanamount(deliveryContent.getPurchaseplanamount());
                }
                if (deliveryContent.getPurchasemethod() != null) {
                    existingDeliveryContent.setPurchasemethod(deliveryContent.getPurchasemethod());
                }
                if (deliveryContent.getPurchasesecretlevel() != null) {
                    existingDeliveryContent.setPurchasesecretlevel(deliveryContent.getPurchasesecretlevel());
                }
                if (deliveryContent.getReviewmethod() != null) {
                    existingDeliveryContent.setReviewmethod(deliveryContent.getReviewmethod());
                }
                if (deliveryContent.getRequirementdepartment() != null) {
                    existingDeliveryContent.setRequirementdepartment(deliveryContent.getRequirementdepartment());
                }
                if (deliveryContent.getRequirementperson() != null) {
                    existingDeliveryContent.setRequirementperson(deliveryContent.getRequirementperson());
                }
                if (deliveryContent.getUndertaker() != null) {
                    existingDeliveryContent.setUndertaker(deliveryContent.getUndertaker());
                }
                if (deliveryContent.getUndertakingdepartment() != null) {
                    existingDeliveryContent.setUndertakingdepartment(deliveryContent.getUndertakingdepartment());
                }
                if (deliveryContent.getWorkbagid() != null) {
                    existingDeliveryContent.setWorkbagid(deliveryContent.getWorkbagid());
                }
                if (deliveryContent.getProjectmanager() != null) {
                    existingDeliveryContent.setProjectmanager(deliveryContent.getProjectmanager());
                }
                if (deliveryContent.getFundsource() != null) {
                    existingDeliveryContent.setFundsource(deliveryContent.getFundsource());
                }
                if (deliveryContent.getThesisname() != null) {
                    existingDeliveryContent.setThesisname(deliveryContent.getThesisname());
                }
                if (deliveryContent.getContractauxiliaryno() != null) {
                    existingDeliveryContent.setContractauxiliaryno(deliveryContent.getContractauxiliaryno());
                }
                if (deliveryContent.getReasonfornosuppliers() != null) {
                    existingDeliveryContent.setReasonfornosuppliers(deliveryContent.getReasonfornosuppliers());
                }
                if (deliveryContent.getReasonforchange() != null) {
                    existingDeliveryContent.setReasonforchange(deliveryContent.getReasonforchange());
                }
                if (deliveryContent.getNegotiationfiletime() != null) {
                    existingDeliveryContent.setNegotiationfiletime(deliveryContent.getNegotiationfiletime());
                }
                if (deliveryContent.getBidopeningtime() != null) {
                    existingDeliveryContent.setBidopeningtime(deliveryContent.getBidopeningtime());
                }
                if (deliveryContent.getJudges() != null) {
                    existingDeliveryContent.setJudges(deliveryContent.getJudges());
                }
                if (deliveryContent.getResponsevendorname() != null) {
                    existingDeliveryContent.setResponsevendorname(deliveryContent.getResponsevendorname());
                }
                if (deliveryContent.getFinalquoteandscore() != null) {
                    existingDeliveryContent.setFinalquoteandscore(deliveryContent.getFinalquoteandscore());
                }
                if (deliveryContent.getNoticeofcompletiontime() != null) {
                    existingDeliveryContent.setNoticeofcompletiontime(deliveryContent.getNoticeofcompletiontime());
                }
                if (deliveryContent.getSigningdate() != null) {
                    existingDeliveryContent.setSigningdate(deliveryContent.getSigningdate());
                }
                if (deliveryContent.getContractenddate() != null) {
                    existingDeliveryContent.setContractenddate(deliveryContent.getContractenddate());
                }
                if (deliveryContent.getActualcompletiontime() != null) {
                    existingDeliveryContent.setActualcompletiontime(deliveryContent.getActualcompletiontime());
                }
                if (deliveryContent.getIssubmitsecrecyagreement() != null) {
                    existingDeliveryContent.setIssubmitsecrecyagreement(deliveryContent.getIssubmitsecrecyagreement());
                }
                if (deliveryContent.getIssubmitsecurityagreement() != null) {
                    existingDeliveryContent.setIssubmitsecurityagreement(deliveryContent.getIssubmitsecurityagreement());
                }
                if (deliveryContent.getRemark() != null) {
                    existingDeliveryContent.setRemark(deliveryContent.getRemark());
                }

                return existingDeliveryContent;
            })
            .map(deliveryContentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeliveryContent> findAll() {
        log.debug("Request to get all DeliveryContents");
        return deliveryContentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeliveryContent> findOne(Integer id) {
        log.debug("Request to get DeliveryContent : {}", id);
        return deliveryContentRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete DeliveryContent : {}", id);
        deliveryContentRepository.deleteById(id);
    }
}
