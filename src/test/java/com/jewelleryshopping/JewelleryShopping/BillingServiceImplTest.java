package com.jewelleryshopping.JewelleryShopping;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.jewelleryshopping.dtos.BillingDTO;
import com.jewelleryshopping.entities.Billing;
import com.jewelleryshopping.entities.User;
import com.jewelleryshopping.repositories.BillingRepository;
import com.jewelleryshopping.services.BillingServiceImpl;

public class BillingServiceImplTest {

    @Mock
    private BillingRepository billingRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BillingServiceImpl billingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllBillings_Valid() {
        // Given
        List<Billing> billings = new ArrayList<>();
        billings.add(new Billing());
        when(billingRepository.findAll()).thenReturn(billings);
        when(modelMapper.map(any(Billing.class), eq(BillingDTO.class))).thenReturn(new BillingDTO());

        // When
        List<BillingDTO> billingDTOs = billingService.getAllBillings();

        // Then
        assertEquals(billings.size(), billingDTOs.size());
    }

    @Test
    void testGetBillingById_Valid() {
        // Given
        Long id = 1L;
        Billing billing = new Billing();
        billing.setBillingId(id);
        when(billingRepository.findById(id)).thenReturn(Optional.of(billing));
        when(modelMapper.map(any(Billing.class), eq(BillingDTO.class))).thenReturn(new BillingDTO());

        // When
        BillingDTO billingDTO = billingService.getBillingById(id);

        // Then
        assertNotNull(billingDTO, "Billing DTO should not be null");
        assertEquals(id, billing.getBillingId(), "Billing ID should match");

    }


    @Test
    void testGetBillingById_Invalid() {
        // Given
        Long id = 2L;
        when(billingRepository.findById(id)).thenReturn(Optional.empty());

        // When
        BillingDTO billingDTO = billingService.getBillingById(id);

        // Then
        assertNull(billingDTO);
    }



    @Test
    void testCreateBilling_Valid() {
        // Given
        BillingDTO billingDTO = new BillingDTO(100.0, "12345", new User());
        Billing billing = new Billing();
        when(modelMapper.map(any(BillingDTO.class), eq(Billing.class))).thenReturn(billing);
        when(billingRepository.save(billing)).thenReturn(billing);
        when(modelMapper.map(any(Billing.class), eq(BillingDTO.class))).thenReturn(billingDTO);

        // When
        BillingDTO createdBillingDTO = billingService.createBilling(billingDTO);

        // Then
        assertNotNull(createdBillingDTO);
        assertEquals(billingDTO.getBillNo(), createdBillingDTO.getBillNo());
        assertEquals(billingDTO.getTotalCost(), createdBillingDTO.getTotalCost());
    }


    @Test
    void testUpdateBilling_Invalid() {
        // Given
        Long id = 2L;
        BillingDTO billingDTO = new BillingDTO(200.0, "67890", new User());
        when(billingRepository.existsById(id)).thenReturn(false);

        // When
        BillingDTO updatedBillingDTO = billingService.updateBilling(id, billingDTO);

        // Then
        assertNull(updatedBillingDTO);
    }

    @Test
    void testDeleteBilling_Valid() {
        // Given
        Long id = 1L;

        // When
        billingService.deleteBilling(id);

        // Then
        verify(billingRepository, times(1)).deleteById(id);
    }

    @Test
    void testIsBillingNoExists_Valid() {
        // Given
        String billNo = "12345";
        when(billingRepository.existsByBillNo(billNo)).thenReturn(true);

        // When
        boolean exists = billingService.isBillingNoExists(billNo);

        // Then
        assertTrue(exists);
    }

    @Test
    void testIsBillingNoExists_Invalid() {
        // Given
        String billNo = "12345";
        when(billingRepository.existsByBillNo(billNo)).thenReturn(false);

        // When
        boolean exists = billingService.isBillingNoExists(billNo);

        // Then
        assertFalse(exists);
    }
}
