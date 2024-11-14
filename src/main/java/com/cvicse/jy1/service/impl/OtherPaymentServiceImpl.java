package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.OtherPayment;
import com.cvicse.jy1.repository.OtherPaymentRepository;
import com.cvicse.jy1.service.OtherPaymentService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.OtherPayment}.
 */
@Service
@Transactional
public class OtherPaymentServiceImpl implements OtherPaymentService {

    private static final Logger log = LoggerFactory.getLogger(OtherPaymentServiceImpl.class);

    private final OtherPaymentRepository otherPaymentRepository;

    public OtherPaymentServiceImpl(OtherPaymentRepository otherPaymentRepository) {
        this.otherPaymentRepository = otherPaymentRepository;
    }

    @Override
    public OtherPayment save(OtherPayment otherPayment) {
        log.debug("Request to save OtherPayment : {}", otherPayment);
        return otherPaymentRepository.save(otherPayment);
    }

    @Override
    public OtherPayment update(OtherPayment otherPayment) {
        log.debug("Request to update OtherPayment : {}", otherPayment);
        return otherPaymentRepository.save(otherPayment);
    }

    @Override
    public Optional<OtherPayment> partialUpdate(OtherPayment otherPayment) {
        log.debug("Request to partially update OtherPayment : {}", otherPayment);

        return otherPaymentRepository
            .findById(otherPayment.getId())
            .map(existingOtherPayment -> {
                if (otherPayment.getName() != null) {
                    existingOtherPayment.setName(otherPayment.getName());
                }
                if (otherPayment.getType() != null) {
                    existingOtherPayment.setType(otherPayment.getType());
                }
                if (otherPayment.getRegistertime() != null) {
                    existingOtherPayment.setRegistertime(otherPayment.getRegistertime());
                }
                if (otherPayment.getSubjectid() != null) {
                    existingOtherPayment.setSubjectid(otherPayment.getSubjectid());
                }
                if (otherPayment.getSubjectname() != null) {
                    existingOtherPayment.setSubjectname(otherPayment.getSubjectname());
                }
                if (otherPayment.getPaymentamount() != null) {
                    existingOtherPayment.setPaymentamount(otherPayment.getPaymentamount());
                }
                if (otherPayment.getContractcode() != null) {
                    existingOtherPayment.setContractcode(otherPayment.getContractcode());
                }
                if (otherPayment.getContractname() != null) {
                    existingOtherPayment.setContractname(otherPayment.getContractname());
                }
                if (otherPayment.getWbsid() != null) {
                    existingOtherPayment.setWbsid(otherPayment.getWbsid());
                }
                if (otherPayment.getWbsname() != null) {
                    existingOtherPayment.setWbsname(otherPayment.getWbsname());
                }

                return existingOtherPayment;
            })
            .map(otherPaymentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OtherPayment> findAll() {
        log.debug("Request to get all OtherPayments");
        return otherPaymentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OtherPayment> findOne(Integer id) {
        log.debug("Request to get OtherPayment : {}", id);
        return otherPaymentRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete OtherPayment : {}", id);
        otherPaymentRepository.deleteById(id);
    }
}
