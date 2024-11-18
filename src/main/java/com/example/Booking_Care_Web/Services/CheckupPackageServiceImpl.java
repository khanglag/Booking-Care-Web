package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.CheckupPackageDTO;
import com.example.Booking_Care_Web.Models.Entities.CheckupPackage;
import com.example.Booking_Care_Web.Repositories.CheckupPackageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckupPackageServiceImpl implements CheckupPackageService{

    @Autowired
    private CheckupPackageRepository checkupPackageRepository;

    @Override
    public CheckupPackage findById(String id) { return checkupPackageRepository.findById(id).get();}

    @Override
    public List<CheckupPackageDTO> findAll() {
        return checkupPackageRepository.findAll().stream()
                .map(cp -> new CheckupPackageDTO(
                        cp.getId(),
                        cp.getName(),
                        cp.getDescription(),
                        cp.getAmount()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CheckupPackage saveCP (CheckupPackage checkupPackage){

        checkupPackageRepository.insertId(checkupPackage.getId(), checkupPackage.getName(),
                checkupPackage.getDescription(),checkupPackage.getAmount());
        return checkupPackage;
    }
}