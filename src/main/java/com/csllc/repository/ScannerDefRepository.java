package com.csllc.repository;

import com.csllc.entity.ScannerDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface ScannerDefRepository extends JpaRepository<ScannerDef, Long> {

    // can load scanner def like so:
    // https://localhost:5199/api/scannerDefs/search/scannerDefName?name=FTP
    // https://localhost:5199/api/scannerDefs/search/scannerDefName?name=File%20System


    // https://localhost:5199/api/scannerDefs/search/name?name=FTP
    @RestResource(path = "name", rel = "name")
    public ScannerDef findOneByName(@Param("name") String scannerDefName);

}
