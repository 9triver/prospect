package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.FundSourceList;
import com.cvicse.jy1.repository.FundSourceListRepository;
import com.cvicse.jy1.service.FundSourceListService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.FundSourceList}.
 */
@Service
@Transactional
public class FundSourceListServiceImpl implements FundSourceListService {

    private static final Logger log = LoggerFactory.getLogger(FundSourceListServiceImpl.class);

    private final FundSourceListRepository fundSourceListRepository;

    public FundSourceListServiceImpl(FundSourceListRepository fundSourceListRepository) {
        this.fundSourceListRepository = fundSourceListRepository;
    }

    @Override
    public FundSourceList save(FundSourceList fundSourceList) {
        log.debug("Request to save FundSourceList : {}", fundSourceList);
        return fundSourceListRepository.save(fundSourceList);
    }

    @Override
    public FundSourceList update(FundSourceList fundSourceList) {
        log.debug("Request to update FundSourceList : {}", fundSourceList);
        return fundSourceListRepository.save(fundSourceList);
    }

    @Override
    public Optional<FundSourceList> partialUpdate(FundSourceList fundSourceList) {
        log.debug("Request to partially update FundSourceList : {}", fundSourceList);

        return fundSourceListRepository
            .findById(fundSourceList.getId())
            .map(existingFundSourceList -> {
                if (fundSourceList.getPaymentid() != null) {
                    existingFundSourceList.setPaymentid(fundSourceList.getPaymentid());
                }
                if (fundSourceList.getContractcode() != null) {
                    existingFundSourceList.setContractcode(fundSourceList.getContractcode());
                }
                if (fundSourceList.getContractname() != null) {
                    existingFundSourceList.setContractname(fundSourceList.getContractname());
                }
                if (fundSourceList.getAmount() != null) {
                    existingFundSourceList.setAmount(fundSourceList.getAmount());
                }

                return existingFundSourceList;
            })
            .map(fundSourceListRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FundSourceList> findAll() {
        log.debug("Request to get all FundSourceLists");
        return fundSourceListRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FundSourceList> findOne(Integer id) {
        log.debug("Request to get FundSourceList : {}", id);
        return fundSourceListRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete FundSourceList : {}", id);
        fundSourceListRepository.deleteById(id);
    }
}
