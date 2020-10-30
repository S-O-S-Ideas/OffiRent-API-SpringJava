package com.acme.offirent.service;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.repository.DistrictRepository;
import com.acme.offirent.domain.repository.OfficeRepository;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.exception.ResourceConditionException;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<Office> getAllOffices(Pageable pageable) {
        return officeRepository.findAll(pageable);
    }

    @Override
    public Office getOfficeById(Long officeId) {
        return officeRepository.findById(officeId)

                .orElseThrow( ()->new ResourceNotFoundException("Office","Id",officeId) );
    }

    @Override
    public Page<Office> getAllOfficesByDistrictId(Long districtId, Pageable pageable) {
        return officeRepository.findByDistrictIdOrderByAccount_PremiumDesc(districtId, pageable)

                .orElseThrow( ()-> new ResourceNotFoundException("District","Id",districtId) );
    }

    @Override
    public Page<Office> getAllOfficesByPriceLessThanEqual(Float price, Pageable pageable) {
        return officeRepository.findByPriceLessThanEqualOrderByAccount_PremiumDesc(price, pageable)

                .orElseThrow( ()->new ResourceNotFoundException("Office","Price",price) );

    }

    @Override
    public Office activeOffice(Long accountId, Long officeId) {

        if(accountRepository.findById(accountId).isEmpty())
            throw new ResourceNotFoundException("Account","Id",accountId);


        if(!accountRepository.findById(accountId).get().isPremium())
            throw new ResourceConditionException("Office","status","activated");

        return officeRepository.findById(officeId).map(office -> {
            office.setStatus(true);
            return officeRepository.save(office);
        }).orElseThrow(()-> new ResourceNotFoundException("Office","Id",officeId));
    }

    @Override
    public Office createOffice(Office office) {
        return officeRepository.save(office);
    }

    @Override
    public Office updateOffice(Long officeId, Office officeRequest) {
        return officeRepository.findById(officeId).map(office->{
                office.setAddress(officeRequest.getAddress());
                office.setFloor(officeRequest.getFloor());
                office.setCapacity(officeRequest.getCapacity());
                office.setAllowResource(officeRequest.getAllowResource());
                office.setScore(officeRequest.getScore());
                office.setDescription(officeRequest.getDescription());
                office.setPrice(officeRequest.getPrice());
                office.setStatus(officeRequest.getStatus());
                office.setComment(officeRequest.getComment());
                return officeRepository.save(office);
                }).orElseThrow(()->new ResourceNotFoundException("Office","Id",officeId));
    }

    @Override
    public ResponseEntity<?> deleteOffice(Long officeId) {
        return officeRepository.findById(officeId).map(office -> {
            officeRepository.delete(office);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Office","Id",officeId));
    }
}
