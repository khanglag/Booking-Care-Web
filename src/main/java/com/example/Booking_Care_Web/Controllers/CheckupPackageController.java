package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Dtos.CheckupPackageDTO;
import com.example.Booking_Care_Web.Models.Entities.CheckupPackage;
import com.example.Booking_Care_Web.Services.CheckupPackageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CheckupPackageController {

    @Autowired
    private CheckupPackageServiceImpl checkupPackageServiceImpl;

    @GetMapping("/checkuppackages")
    public List<CheckupPackageDTO> findAll() {return checkupPackageServiceImpl.findAll();}

    @GetMapping("/id")
    public RepsponseEntity<CheckupPackageDTO> getCPById(@RequestParam String id) {
        try {
            CheckupPackage cp = checkupPackageServiceImpl.findById(id);
            CheckupPackageDTO cpDTO = new CheckupPackageDTO(
                    cp.getId(),
                    cp.getName(),
                    cp.getDescription(),
                    cp.getAmount()
            ); return ResponseEntity.ok(cpDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<CheckupPackage> createCP(@RequestBody CheckupPackage cp){
        CheckupPackage savedCP = checkupPackageServiceImpl.saveCP(cp);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCP);
    }
}