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
@RequestMapping("businesses") // Base path para los negocios
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    // Endpoint para crear un nuevo negocio
    @PostMapping
    public ResponseEntity<Business> createBusiness(@RequestBody BusinessRequest businessRequest) {
        Business createdBusiness = businessService.createBusiness(businessRequest);
        return ResponseEntity.ok(createdBusiness);
    }

    // Endpoint para obtener todos los negocios
    @GetMapping
    public ResponseEntity<List<Business>> getAllBusinesses() {
        List<Business> businesses = businessService.getAllBusinesses();
        return ResponseEntity.ok(businesses);
    }

    // Endpoint para obtener un negocio por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Business> getBusinessById(@PathVariable Long id) {
        Optional<Business> business = businessService.getBusinessById(id);
        return business.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
