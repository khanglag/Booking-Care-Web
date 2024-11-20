package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Entities.CheckupPackage;
import com.example.Booking_Care_Web.Repositories.CheckupPackageRepository;
import com.example.Booking_Care_Web.Services.CheckupPackageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CheckupPackageServiceTest {

    @Mock
    private CheckupPackageRepository checkupPackageRepository;

    @InjectMocks
    private CheckupPackageServiceImpl checkupPackageServiceImp;

    @BeforeEach
    public void setUp() { MockitoAnnotations.openMocks(this);}

    @Test
    public void testCreateCheckupPackage(){

        CheckupPackage checkupPackageDTO = new CheckupPackage();
        checkupPackageDTO.setPackageId("0000008");
        checkupPackageDTO.setName("Nguyenngu");
        checkupPackageDTO.setDescription("ngusi");
        checkupPackageDTO.setAmount("232");

        System.out.println(checkupPackageDTO);
        CheckupPackage savedCP = checkupPackageServiceImp.savedCP(checkupPackageDTO);
        System.out.println(savedCP);
    }

    @Test
    public void testFindById(){

        String id ="tq30000";
        Optional<CheckupPackageDTO> checkupPackageDTOOptional = checkupPackageService.findById(package_id);
        System.out.println(checkupPackageDTOOptional.toString());
    }
}