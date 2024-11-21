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

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    public void testFindById() {
        // Arrange (Chuẩn bị dữ liệu giả lập)
        String id = "0000000";
        CheckupPackpage expectedCheckupPackpage = new CheckupPackpage(); // Khởi tạo đối tượng mong đợi
        when(checkupPackageRepository.findById(id)).thenReturn(Optional.of(expectedCheckupPackpage)); // Giả lập repository

        // Act (Gọi phương thức cần kiểm tra)
        CheckupPackpage checkupPackpage = checkupPackpageServiceImp.findById(id);

        // Assert (Kiểm tra kết quả)
        assertNotNull(checkupPackpage);
        assertEquals(expectedCheckupPackpage, checkupPackpage);
    }
}