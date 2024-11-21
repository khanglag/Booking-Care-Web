package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Dtos.CheckupPackpageDTO;
import com.example.Booking_Care_Web.Models.Entities.CheckupPackpage;
import com.example.Booking_Care_Web.Repositories.CheckupPackpageRepository;
import com.example.Booking_Care_Web.Services.CheckupPackpageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CheckupPackpageServiceTest {

    @Mock
    private CheckupPackpageRepository checkupPackageRepository;

    @InjectMocks
    private CheckupPackpageServiceImpl checkupPackpageServiceImp;

    @BeforeEach
    public void setUp() { MockitoAnnotations.openMocks(this);}

    @Test
    public void testCreateCheckupPackage(){

        CheckupPackpage checkupPackpageDTO = new CheckupPackpage();
        checkupPackpageDTO.setPackageId("0000008");
        checkupPackpageDTO.setName("Nguyenngu");
        checkupPackpageDTO.setDescription("ngusi");
        checkupPackpageDTO.setAmount(Double.valueOf(1803));

        System.out.println(checkupPackpageDTO);
        CheckupPackpage savedCP = checkupPackpageServiceImp.saveCP(checkupPackpageDTO);
        System.out.println(savedCP);
    }

    @Test
    public void testFindById(){

//        String package_id ="tq30000";
//        Optional<CheckupPackpageDTO> checkupPackageDTOOptional = checkupPackpageServiceImp.findById(package_id);
//        System.out.println(checkupPackageDTOOptional.toString());
    }
}