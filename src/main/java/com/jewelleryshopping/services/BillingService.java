package com.jewelleryshopping.services;

import java.util.List;

import com.jewelleryshopping.dtos.BillingDTO;


public interface BillingService {
	List<BillingDTO> getAllBillings();
    BillingDTO getBillingById(Long id);
    BillingDTO getBillingByBillNo(String billNo);
    BillingDTO createBilling(BillingDTO billingDTO);
    BillingDTO updateBilling(Long id, BillingDTO billingDTO);
    void deleteBilling(Long id);
    boolean isBillingNoExists(String billNo);
   }
