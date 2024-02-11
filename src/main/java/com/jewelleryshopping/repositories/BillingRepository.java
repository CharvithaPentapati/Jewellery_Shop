package com.jewelleryshopping.repositories;

import com.jewelleryshopping.entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
    Billing findByBillNo(String billNo);

	boolean existsByBillNo(String billNo);
}
