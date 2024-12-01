package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.CheckupPackpageDTO;
import com.example.Booking_Care_Web.Models.Entities.CheckupPackpage;
import com.example.Booking_Care_Web.Repositories.CheckupPackpageRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckupPackpageServiceImpl implements CheckupPackpageService {

    @Autowired
    private CheckupPackpageRepository checkupPackageRepository;

    @Override
    public CheckupPackpage findById(String package_id) {
        return checkupPackageRepository.findById(package_id).orElseThrow(() -> new EntityNotFoundException("cp không tìm thấy với ID: " + package_id));
    }
    @Override
    public List<CheckupPackpageDTO> findAll() {
        return checkupPackageRepository.findAll().stream()
                .map(cp -> new CheckupPackpageDTO(
                        cp.getPackageId(),
                        cp.getName(),
                        cp.getDescription(),
                        cp.getAmount()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public CheckupPackpage saveCheckupPackpage(CheckupPackpage checkupPackpage){
        return checkupPackageRepository.save(checkupPackpage);
    }


    public CheckupPackpage updateCP (String id, CheckupPackpage updateCP){
        CheckupPackpage checkuExisting = checkupPackageRepository.findById(id).orElseThrow(() -> new RuntimeException("CheckupPackage không tồn tại ID" + id));

        if (updateCP.getName() != null) {
            checkuExisting.setName(updateCP.getName());
        }
        if (updateCP.getDescription() != null){
            checkuExisting.setDescription(updateCP.getDescription());
        }
        if (updateCP.getAmount() != null){
            checkuExisting.setDescription(String.valueOf(updateCP.getAmount()));
        }
        return checkupPackageRepository.save(checkuExisting);
    }
}