package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Dtos.CheckupPackpageDTO;
import com.example.Booking_Care_Web.Models.Entities.CheckupPackpage;
import com.example.Booking_Care_Web.Repositories.CheckupPackpageRepository;
import com.example.Booking_Care_Web.Services.CheckupPackpageServiceImpl;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;

@SpringBootTest
@SpringJUnitWebConfig

public class CheckupPackpageServiceTest {

    @Autowired
    private CheckupPackpageServiceImpl checkupPackpageServiceImp;

//    @Override
//    public List<CheckupPackpageDTO> findAll() {
//
//    }

    @Test
    public void testCreatePackpage(){

        CheckupPackpage checkupPackpageDTO = new CheckupPackpage();
        checkupPackpageDTO.setPackageId("0000026");
        checkupPackpageDTO.setName("Goi kham");
        checkupPackpageDTO.setDescription("xet nghiem j do");
        checkupPackpageDTO.setAmount(Double.valueOf("223"));

        System.out.println(checkupPackpageDTO);
        CheckupPackpage savedCP = checkupPackpageServiceImp.saveCheckupPackpage(checkupPackpageDTO);
        System.out.println(savedCP);
    }

    @Test
    public void testFindById(){
        String id = "tq10000";
        CheckupPackpage checkupPackpage = checkupPackpageServiceImp.findById(id);
        System.out.println(checkupPackpage.toString());
    }

    @Test
    public void testUpdateCP(){
        CheckupPackpage checkupPackpageDTO = new CheckupPackpage();
        checkupPackpageDTO.setPackageId("tq10000");
        checkupPackpageDTO.setName("Xuong khop");
        checkupPackpageDTO.setDescription("hahahihi");
        checkupPackpageDTO.setAmount(Double.valueOf("100"));

        CheckupPackpage update = checkupPackpageServiceImp.updateCP("tq10000", checkupPackpageDTO);
    }



}
