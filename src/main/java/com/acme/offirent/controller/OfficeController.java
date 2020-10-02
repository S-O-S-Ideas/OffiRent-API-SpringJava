package com.acme.offirent.controller;


import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.resource.OfficeResource;
import com.acme.offirent.resource.SaveOfficeResource;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OfficeController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OfficeService officeService;

    @GetMapping("/offices")
    public Page<OfficeResource> getAllOffices(Pageable pageable){

       List<OfficeResource> offices= officeService.getAllOffices(pageable)
               .getContent().stream().map(this::convertToResource)
               .collect(Collectors.toList());

       int officesCount= offices.size();
       return new PageImpl<>(offices,pageable,officesCount);
    }

    @GetMapping("/offices/{id}")
    public OfficeResource getOfficeById(@PathVariable(name = "id") Long officeId){
        return convertToResource(officeService.getOfficeById(officeId));
    }

    @GetMapping("/districts/{districtId}/offices")
    public Page<OfficeResource> getAllOfficesByDistrictId( @PathVariable(name="districtId")Long districtId, Pageable pageable ){

        List<OfficeResource> offices= officeService.getAllOfficesByDistrictId(districtId, pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());

        int officesCount=offices.size();
        return new PageImpl<>(offices, pageable, officesCount);
    }

    @GetMapping("/offices/{price}/offices")
    public Page<OfficeResource> getAllOfficesByPriceLessThanEqual(@PathVariable(name="price") Float price, Pageable pageable){

        List<OfficeResource> offices= officeService.getAllOfficesByPriceLessThanEqual(price ,pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());

        int officesCount=offices.size();
        return new PageImpl<>(offices,pageable,officesCount);
    }

    @PostMapping("/offices")
    public OfficeResource createOffice(@Valid @RequestBody SaveOfficeResource resource){
        return convertToResource(
                officeService.createOffice(convertToEntity(resource)));
    }

    @PutMapping("/offices/{id}")
    public OfficeResource updateOffice(@PathVariable(name = "id")   Long officeId,@Valid @RequestBody SaveOfficeResource resource){
        return convertToResource(officeService.updateOffice(officeId,convertToEntity(resource)));
    }

    @DeleteMapping("/offices/{id}")
    public ResponseEntity<?> deleteOffice(@PathVariable(name="id") Long officeId){
        return officeService.deleteOffice(officeId);
    }

    private Office convertToEntity(SaveOfficeResource resource){
        return mapper.map(resource, Office.class);
    }

    private OfficeResource convertToResource(Office entity){
        return mapper.map(entity, OfficeResource.class);
    }

}
