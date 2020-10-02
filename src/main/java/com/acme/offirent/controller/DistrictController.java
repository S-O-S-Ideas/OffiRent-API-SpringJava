package com.acme.offirent.controller;

import com.acme.offirent.domain.model.District;
import com.acme.offirent.domain.service.DistrictService;
import com.acme.offirent.resource.DistrictResource;
import com.acme.offirent.resource.SaveDistrictResource;
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
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/districts/{id}")
    public DistrictResource getDistrictById(@PathVariable(name = "id") Long districtId){
        return convertToResource(districtService.getDistrictById(districtId));
    }

    @GetMapping("/departaments/{id}/districts")
    public Page<DistrictResource> getAllDistrictByDepartamentId(@PathVariable(name = "id") Long departamentId, Pageable pageable){
        List<DistrictResource> districts=districtService.getAllDistrictByDepartamentId(departamentId, pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());

        int districtsCount= districts.size();
        return new PageImpl<>(districts,pageable,districtsCount);
    }

    @PostMapping("/districts")
    public DistrictResource createDistrict(@Valid @RequestBody SaveDistrictResource resource){
        return convertToResource(
                districtService.createDistrict(convertToEntity(resource)));
    }

    @DeleteMapping("/districts/{id}")
    public ResponseEntity<?> deleteDistrict(@PathVariable(name = "id") Long districtId){
        return districtService.deleteDistrict(districtId);
    }



    private District convertToEntity(SaveDistrictResource resource){return  mapper.map(resource, District.class);}

    private DistrictResource convertToResource(District entity){return  mapper.map(entity,DistrictResource.class);}
}
