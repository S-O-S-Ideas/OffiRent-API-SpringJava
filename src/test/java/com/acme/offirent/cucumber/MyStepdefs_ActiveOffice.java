package com.acme.offirent.cucumber;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.repository.DistrictRepository;
import com.acme.offirent.domain.repository.OfficeRepository;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.service.OfficeServiceImpl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class MyStepdefs_ActiveOffice {


    @MockBean
    private OfficeRepository officeRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private OfficeService officeService;

    @TestConfiguration
    static class OfficeServiceImplTestConfiguration{

        @Bean
        public OfficeService officeService(){
            return new OfficeServiceImpl();
        }
    }

    Account account;
    Office office;
    Long officeId = 1L;
    Long accountId = 2L;

    @Given("Offi-provider has premium Account")
    public void offiProviderHasPremium() {
        account.setPremium(true);
        assertThat(account.isPremium()).isTrue();
    }

    @And("Offi-provider is in the Deactivated Office window")
    public void offiProviderIsInTheDeactivatedOfficeWindow() {
        office.setStatus(false);


    }

    @When("Offi-provider clicks in Activate Product")
    public void offiProviderClicksInActivateProduct() {


    }

    @Then("the system change the office status to {boolean}")
    public void theSystemChangeTheOfficeStatusTo() {
    }

    @Given("Offi-provider has not premium Account")
    public void offiProviderHasNotPremium() {
        account.setPremium(false);
        assertThat(account.isPremium()).isFalse();
    }

    @Then("the system show the message Resource Office can not be changed with status with value activated")
    public void theSystemShowTheMessage() {
    }
}
