package com.acme.offirent;


import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.repository.DistrictRepository;
import com.acme.offirent.domain.repository.OfficeRepository;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.exception.LockedActionException;
import com.acme.offirent.exception.ResourceNotFoundException;
import com.acme.offirent.service.OfficeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OfficeServiceTest {
    @MockBean
    private OfficeRepository officeRepository;

    @MockBean
    private DistrictRepository districtRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private OfficeService officeService;


    @TestConfiguration
    static class OfficeServiceTestConfiguration {

        @Bean
        public OfficeService officeService() {
            return new OfficeServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetOfficeById With Valid Id Then Returns Office")
    public void whenGetOfficeByIdWithValidIdThenReturnsOffice() {

        //Arrange
        Long id = 1L;
        Office office = new Office();
        office.setId(id);

        when(officeRepository.findById(office.getId()))
                .thenReturn(Optional.of(office));


        //Act
        Office searchedOffice = officeService.getOfficeById(id);

        //Assert
        assertThat(searchedOffice.getId()).isEqualTo(id);
    }


    @Test
    @DisplayName("When GetOfficeById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetOfficeByIdWithInvalidIdThenReturnsResourceNotFoundException() {

        //Arrange
        Long invalidId = 1L;
        String template = "Resource %s not found for %s with value %s"; //metodo comun
        String expectedMessage = String.format(template, "Office", "Id", invalidId);   //metodo comun

        when(officeRepository.findById(invalidId))
                .thenReturn(Optional.empty());

        //Act
        Throwable exception = catchThrowable(() -> {
            Office searchedOffice = officeService.getOfficeById(invalidId);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When GetAllOfficesByAccountId With Invalid AccountId Then Returns Resource Resource Not Found Exception")
    public void whenGetAllOfficesByDistrictIdWithInvalidDistrictIdThenReturnsResourceResourceNotFoundException() {

        //Arrange
        Long invalidId = 1L;
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        String template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template, "Account", "Id", invalidId);

        when(accountRepository.findById(invalidId))
                .thenReturn(Optional.empty());

        //Act
        Throwable exception = catchThrowable(() -> {
            Page<Office> offices = officeService.getAllOfficesByAccountId(invalidId, pageable);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

//    @Test
//    @DisplayName("When CreateOffice with Not Premium Account having more than 15 Offices returns LockedActionException")
//    public void whenCreateOfficeWithNotPremiumAccountHavingMoreThan15OfficesReturnsLockedActionException() {
//        //Arrange
//        Long id = 1L;
//        Office office = new Office();
//        office.setId(id);
//
//        String expectedMessage  = "Cant create an Office due to user is not premium and cant have more than 15 offices"; //metodo comun
//        //Act
//        Throwable exception = catchThrowable(() -> {
//            Office office = officeService.createOffice();
//        });
//        //Assert
//        assertThat(exception)
//                .isInstanceOf(LockedActionException.class)
//                .hasMessage(expectedMessage);
//    }
}