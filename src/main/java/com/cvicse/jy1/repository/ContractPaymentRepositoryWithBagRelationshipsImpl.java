package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ContractPayment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class ContractPaymentRepositoryWithBagRelationshipsImpl implements ContractPaymentRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String CONTRACTPAYMENTS_PARAMETER = "contractPayments";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ContractPayment> fetchBagRelationships(Optional<ContractPayment> contractPayment) {
        return contractPayment.map(this::fetchPaymentCostLists).map(this::fetchFundSourceLists);
    }

    @Override
    public Page<ContractPayment> fetchBagRelationships(Page<ContractPayment> contractPayments) {
        return new PageImpl<>(
            fetchBagRelationships(contractPayments.getContent()),
            contractPayments.getPageable(),
            contractPayments.getTotalElements()
        );
    }

    @Override
    public List<ContractPayment> fetchBagRelationships(List<ContractPayment> contractPayments) {
        return Optional.of(contractPayments)
            .map(this::fetchPaymentCostLists)
            .map(this::fetchFundSourceLists)
            .orElse(Collections.emptyList());
    }

    ContractPayment fetchPaymentCostLists(ContractPayment result) {
        return entityManager
            .createQuery(
                "select contractPayment from ContractPayment contractPayment left join fetch contractPayment.paymentCostLists where contractPayment.id = :id",
                ContractPayment.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<ContractPayment> fetchPaymentCostLists(List<ContractPayment> contractPayments) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, contractPayments.size()).forEach(index -> order.put(contractPayments.get(index).getId(), index));
        List<ContractPayment> result = entityManager
            .createQuery(
                "select contractPayment from ContractPayment contractPayment left join fetch contractPayment.paymentCostLists where contractPayment in :contractPayments",
                ContractPayment.class
            )
            .setParameter(CONTRACTPAYMENTS_PARAMETER, contractPayments)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    ContractPayment fetchFundSourceLists(ContractPayment result) {
        return entityManager
            .createQuery(
                "select contractPayment from ContractPayment contractPayment left join fetch contractPayment.fundSourceLists where contractPayment.id = :id",
                ContractPayment.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<ContractPayment> fetchFundSourceLists(List<ContractPayment> contractPayments) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, contractPayments.size()).forEach(index -> order.put(contractPayments.get(index).getId(), index));
        List<ContractPayment> result = entityManager
            .createQuery(
                "select contractPayment from ContractPayment contractPayment left join fetch contractPayment.fundSourceLists where contractPayment in :contractPayments",
                ContractPayment.class
            )
            .setParameter(CONTRACTPAYMENTS_PARAMETER, contractPayments)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
