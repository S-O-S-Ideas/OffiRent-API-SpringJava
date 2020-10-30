package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office, Long> {
       Optional<Page<Office>> findByDistrictIdOrderByAccount_PremiumDesc (Long districtId, Pageable pageable);   //al final no se llega a utilizar
       Optional<Page<Office>> findByPriceLessThanEqualOrderByAccount_PremiumDesc (Float price, Pageable pageable);
       @Query("select o from offices o join accounts a on o.account_id = a.id order by  a.premium")
       Page<Office> findOfficesByAccount_PremiumDesc ();
}
