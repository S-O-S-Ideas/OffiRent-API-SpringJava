package com.acme.offirent.controller;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.resource.AccountResource;
import com.acme.offirent.resource.OfficeResource;
import com.acme.offirent.resource.SaveAccountResource;
import com.acme.offirent.resource.SaveOfficeResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OfficesController {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get all offices",description = "Get all offices",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all offices",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/offices")
    public Page<OfficeResource> getAllOffices(Pageable pageable){

        Page<Office> resourcePage = officeService.getAllOffices(pageable);
        List<OfficeResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary = "Get all offices by account",description = "Get all Offices by given Account Id",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all Offices by given Account Id",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/accounts/{accountId}/offices")
    public Page<OfficeResource> getAllOfficesByAccountId(@PathVariable(name = "accountId") Long accountId,Pageable pageable){

        Page<Office> resourcePage = officeService.getAllOfficesByAccountId(accountId,pageable);
        List<OfficeResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary = "Get Office by Id", description = "Get Office for given Id", tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Office returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/offices/{officeId}")
    public OfficeResource getOfficeById(@PathVariable(name = "officeId") Long officeId){
        return convertToResource(officeService.getOfficeById(officeId));
    }

    @Operation(summary = "Create Office ",description = "Enter a new Office at register",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enter a new office for given information",content =@Content(mediaType = "application/json") )
    })
    @PostMapping("/accounts/{accountId}/offices")
    public OfficeResource createOffice(@PathVariable(name = "accountId") Long accountId,@Valid @RequestBody SaveOfficeResource resource){
        return convertToResource(
                officeService.createOffice(convertToEntity(resource),accountId));
    }

    @Operation(summary = "Get all the offices that have the same price",description = "Get all Offices by given price",tags = {"DistrictOffices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all Offices by given price",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/offices/<={price}")
    public Page<OfficeResource> getAllOfficesByPriceLessThanEqual(@PathVariable(name = "price") Float price, Pageable pageable){
        Page<Office> resourcePage = officeService.getAllOfficesByPriceLessThanEqual(price, pageable);
        List<OfficeResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }

    @GetMapping("/offices/=>{price1}/<={price2}")
    public Page<OfficeResource> getAllOfficesByPriceLessThanEqualAndPriceGreaterThanEqual(@PathVariable(name = "price1") Float price1,@PathVariable(name = "price2") Float price2,  Pageable pageable){
        Page<Office> resourcePage = officeService.getAllOfficesByPriceLessThanEqualAndPriceGreaterThanEqual(price2, price1, pageable);
        List<OfficeResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }


    private Office convertToEntity(SaveOfficeResource resource){return  mapper.map(resource, Office.class);}

    private OfficeResource convertToResource(Office entity){return  mapper.map(entity,OfficeResource.class);}
}
