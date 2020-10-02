package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResourceService {
    Page<Office> getAllResources(Pageable pageable);
    Office getResourceById  (Long resourceId);
    
    //repositorymethods
    Page<Comment> findByOfficeId(Long officeId, Pageable pageable); // buscar por id oficina
    Page<Comment> findByNameAndOfficeId(String Name, Long officeId); // buscar implemento por nombre y id de oficina

    //crud?
    Office createResource(long officeId,Resource resource);
    Office updateResource(long officeId,Long resourceId,Resource resourceRequest);
    ResponseEntity<?> deleteResource(long officeId,Long resourceId);

}
