//package com.example.Booking_Care_Web.Controllers;
//
//import com.example.Booking_Care_Web.Models.Dtos.CheckupPackpageDTO;
//import com.example.Booking_Care_Web.Models.Entities.CheckupPackpage;
//import com.example.Booking_Care_Web.Services.CheckupPackpageServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//@RestController
//public class CheckupPackpageController {
//
//    @Autowired
//    private CheckupPackpageServiceImpl checkupPackpageServiceImpl;
//
//    @GetMapping("/checkuppackpages")
//    public List<CheckupPackpageDTO> findAll() {return checkupPackpageServiceImpl.findAll();}
//
//    @GetMapping("/package_id")
//    public ResponseEntity<CheckupPackpageDTO> getCPById(@RequestParam String id) {
//        try {
//            CheckupPackpage cp = checkupPackpageServiceImpl.findById(id);
//            CheckupPackpageDTO cpDTO = new CheckupPackpageDTO(
//                    cp.getPackageId(),
//                    cp.getName(),
//                    cp.getDescription(),
//                    cp.getAmount()
//            ); return ResponseEntity.ok(cpDTO);
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @PostMapping("/create")
//    public ResponseEntity<CheckupPackpage> createCP(@RequestBody CheckupPackpage cp){
//        CheckupPackpage savedCP = checkupPackpageServiceImpl.saveCP(cp);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedCP);
//    }
//}