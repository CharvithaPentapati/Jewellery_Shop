package com.jewelleryshopping.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jewelleryshopping.dtos.BillingDTO;
import com.jewelleryshopping.services.BillingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/billings")
@Validated
public class BillingController {
	@Autowired
    private BillingService billingService;
 
    @GetMapping
    public ResponseEntity<List<BillingDTO>> getAllBillings() {
        List<BillingDTO> billings = billingService.getAllBillings();
        return new ResponseEntity<>(billings, HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<BillingDTO> getBillingById(@PathVariable Long id) {
        BillingDTO billing = billingService.getBillingById(id);
        return billing != null ? ResponseEntity.ok(billing) : ResponseEntity.notFound().build();
    }
 
    @GetMapping("/billNo/{billNo}")
    public ResponseEntity<BillingDTO> getBillingByBillNo(@PathVariable String billNo) {
        BillingDTO billing = billingService.getBillingByBillNo(billNo);
        return billing != null ? ResponseEntity.ok(billing) : ResponseEntity.notFound().build();
    }
 
    @PostMapping
    public ResponseEntity<?> createBilling(@Valid @RequestBody BillingDTO billingDTO) {
        if (billingService.isBillingNoExists(billingDTO.getBillNo())) {
            Map<String, String> error = new HashMap<>();
            error.put("Billing Exists", "Billing exists with the same bill number");
            return ResponseEntity.badRequest().body(error);
        }
        BillingDTO createdBilling = billingService.createBilling(billingDTO);
        return new ResponseEntity<>(createdBilling, HttpStatus.CREATED);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<BillingDTO> updateBilling(@PathVariable Long id, @Valid @RequestBody BillingDTO billingDTO) {
        BillingDTO updatedBilling = billingService.updateBilling(id, billingDTO);
        return updatedBilling != null ? ResponseEntity.ok(updatedBilling) : ResponseEntity.notFound().build();
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilling(@PathVariable Long id) {
        billingService.deleteBilling(id);
        return ResponseEntity.noContent().build();
    }
}
