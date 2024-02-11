package com.jewelleryshopping.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewelleryshopping.dtos.BillingDTO;
import com.jewelleryshopping.entities.Billing;
import com.jewelleryshopping.repositories.BillingRepository;

@Service
public class BillingServiceImpl implements BillingService {
	@Autowired
    private BillingRepository billingRepository;
 
    @Autowired
    private ModelMapper modelMapper;
 
    @Override
    public List<BillingDTO> getAllBillings() {
        List<Billing> billings = billingRepository.findAll();
        return billings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
 
    @Override
    public BillingDTO getBillingById(Long id) {
        Optional<Billing> billingOptional = billingRepository.findById(id);
        return billingOptional.map(this::convertToDto).orElse(null);
    }
 
    @Override
    public BillingDTO getBillingByBillNo(String billNo) {
        Billing billing = billingRepository.findByBillNo(billNo);
        return convertToDto(billing);
    }
 
    @Override
    public BillingDTO createBilling(BillingDTO billingDTO) {
        Billing billing = convertToEntity(billingDTO);
        Billing savedBilling = billingRepository.save(billing);
        return convertToDto(savedBilling);
    }
 
    @Override
    public BillingDTO updateBilling(Long id, BillingDTO billingDTO) {
        if (billingRepository.existsById(id)) {
            Billing billing = convertToEntity(billingDTO);
            billing.setBillingId(id);
            Billing updatedBilling = billingRepository.save(billing);
            return convertToDto(updatedBilling);
        }
        return null;
    }
 
    @Override
    public void deleteBilling(Long id) {
        billingRepository.deleteById(id);
    }
 
    @Override
    public boolean isBillingNoExists(String billNo) {
        return billingRepository.existsByBillNo(billNo);
    }
 
    private BillingDTO convertToDto(Billing billing) {
        return modelMapper.map(billing, BillingDTO.class);
    }
 
    private Billing convertToEntity(BillingDTO billingDTO) {
        return modelMapper.map(billingDTO, Billing.class);
    }
}
