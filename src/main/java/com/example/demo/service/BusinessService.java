package com.example.demo.service;

import com.example.demo.controller.business.request.BusinessRequest;
import com.example.demo.controller.business.response.BusinessResponse;
import com.example.demo.entity.Business;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.BusinessMapper;
import com.example.demo.repository.BusinessRepository; // Asegúrate de tener este repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private BusinessMapper businessMapper;


    // Método para crear un nuevo negocio
    /*public Business createBusiness(BusinessRequest businessRequest) {

        Business business = new Business();
        business.setBusinessName(businessRequest.businessName());
        business.setRuc(businessRequest.ruc());
        business.setAddress(businessRequest.address());
        business.setReasonSocial(businessRequest.reasonSocial());
        business.setComercialName(businessRequest.comercialName());
        business.setCategory(businessRequest.category());
        business.setUser(userService.getUserById(businessRequest.userId()).get());
        return businessRepository.save(business);
    }*/

    public Business createBusinesswUser(UserEntity user) {
        Business business = new Business();
        business.setUser(user);
        return businessRepository.save(business);
    }

    public BusinessResponse getBusinessResponse(Long id) {
        Business business = getBusinessByUser(id).get();
        return businessMapper.toBusinessResponse(business);
    }

    public Optional<Business> getBusinessByUser(Long id) {
        return businessRepository.findByUser_Id(id);
    }

    // Método para obtener todos los negocios
    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    // Método para obtener un negocio por su ID
    public Optional<Business> getBusinessById(Long id) {
        return businessRepository.findById(id);
    }

    public Business updateBusiness(BusinessRequest businessRequest) {
        Business oldBusiness = businessRepository.findById(businessRequest.businessId()).orElseThrow(null);
        oldBusiness.setBusinessName(businessRequest.businessName());
        oldBusiness.setRuc(businessRequest.ruc());
        oldBusiness.setAddress(businessRequest.address());
        oldBusiness.setReasonSocial(businessRequest.reasonSocial());
        oldBusiness.setComercialName(businessRequest.comercialName());
        oldBusiness.setCategory(businessRequest.category());
        return businessRepository.save(oldBusiness);
    }
}
