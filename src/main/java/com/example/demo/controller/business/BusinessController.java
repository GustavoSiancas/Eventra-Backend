package com.example.demo.controller.business;

import com.example.demo.controller.business.request.BusinessRequest;
import com.example.demo.entity.Business;
import com.example.demo.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/businesses") // Base path para los negocios
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /*
    @PostMapping
    public ResponseEntity<Business> createBusiness(@RequestBody BusinessRequest businessRequest) {
        Business createdBusiness = businessService.createBusiness(businessRequest);
        return ResponseEntity.ok(createdBusiness);
    }*/

    @GetMapping
    public ResponseEntity<List<Business>> getAllBusinesses() {
        List<Business> businesses = businessService.getAllBusinesses();
        return ResponseEntity.ok(businesses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Business> getBusinessById(@PathVariable Long id) {
        Optional<Business> business = businessService.getBusinessById(id);
        return business.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Business> updateBusiness(@RequestBody BusinessRequest businessRequest) {
        return ResponseEntity.ok(businessService.updateBusiness(businessRequest));
    }
}
