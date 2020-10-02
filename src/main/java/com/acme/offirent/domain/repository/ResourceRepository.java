package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, String> {
    Page<Comment> findByOfficeId(Long officeId, Pageable pageable); // buscar por id oficina
    Optional<Page<Comment>> findByNameAndOfficeId(String Name, Long officeId); // buscar implemento por nombre y id de oficina

}