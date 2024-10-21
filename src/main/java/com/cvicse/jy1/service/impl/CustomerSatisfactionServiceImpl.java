package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.CustomerSatisfaction;
import com.cvicse.jy1.repository.CustomerSatisfactionRepository;
import com.cvicse.jy1.service.CustomerSatisfactionService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.CustomerSatisfaction}.
 */
@Service
@Transactional
public class CustomerSatisfactionServiceImpl implements CustomerSatisfactionService {

    private static final Logger log = LoggerFactory.getLogger(CustomerSatisfactionServiceImpl.class);

    private final CustomerSatisfactionRepository customerSatisfactionRepository;

    public CustomerSatisfactionServiceImpl(CustomerSatisfactionRepository customerSatisfactionRepository) {
        this.customerSatisfactionRepository = customerSatisfactionRepository;
    }

    @Override
    public CustomerSatisfaction save(CustomerSatisfaction customerSatisfaction) {
        log.debug("Request to save CustomerSatisfaction : {}", customerSatisfaction);
        return customerSatisfactionRepository.save(customerSatisfaction);
    }

    @Override
    public CustomerSatisfaction update(CustomerSatisfaction customerSatisfaction) {
        log.debug("Request to update CustomerSatisfaction : {}", customerSatisfaction);
        return customerSatisfactionRepository.save(customerSatisfaction);
    }

    @Override
    public Optional<CustomerSatisfaction> partialUpdate(CustomerSatisfaction customerSatisfaction) {
        log.debug("Request to partially update CustomerSatisfaction : {}", customerSatisfaction);

        return customerSatisfactionRepository
            .findById(customerSatisfaction.getId())
            .map(existingCustomerSatisfaction -> {
                if (customerSatisfaction.getYear() != null) {
                    existingCustomerSatisfaction.setYear(customerSatisfaction.getYear());
                }
                if (customerSatisfaction.getSatisfactionitem() != null) {
                    existingCustomerSatisfaction.setSatisfactionitem(customerSatisfaction.getSatisfactionitem());
                }
                if (customerSatisfaction.getScore() != null) {
                    existingCustomerSatisfaction.setScore(customerSatisfaction.getScore());
                }
                if (customerSatisfaction.getOpinion() != null) {
                    existingCustomerSatisfaction.setOpinion(customerSatisfaction.getOpinion());
                }
                if (customerSatisfaction.getTotalscore() != null) {
                    existingCustomerSatisfaction.setTotalscore(customerSatisfaction.getTotalscore());
                }
                if (customerSatisfaction.getSurveytime() != null) {
                    existingCustomerSatisfaction.setSurveytime(customerSatisfaction.getSurveytime());
                }
                if (customerSatisfaction.getCustomer() != null) {
                    existingCustomerSatisfaction.setCustomer(customerSatisfaction.getCustomer());
                }
                if (customerSatisfaction.getPlonenumber() != null) {
                    existingCustomerSatisfaction.setPlonenumber(customerSatisfaction.getPlonenumber());
                }
                if (customerSatisfaction.getFilename() != null) {
                    existingCustomerSatisfaction.setFilename(customerSatisfaction.getFilename());
                }

                return existingCustomerSatisfaction;
            })
            .map(customerSatisfactionRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerSatisfaction> findAll() {
        log.debug("Request to get all CustomerSatisfactions");
        return customerSatisfactionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerSatisfaction> findOne(Integer id) {
        log.debug("Request to get CustomerSatisfaction : {}", id);
        return customerSatisfactionRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete CustomerSatisfaction : {}", id);
        customerSatisfactionRepository.deleteById(id);
    }
}
