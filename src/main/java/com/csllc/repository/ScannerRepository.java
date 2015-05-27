package com.csllc.repository;

import com.csllc.entity.Scanner;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

    
@Repository
public interface ScannerRepository extends JpaRepository<Scanner, Long>, JpaSpecificationExecutor<Scanner> {

//    public static String PARTNER_TYPE = "partnertype";
    
    @Query("select s.producerName from Scanner s group by s.producerName order by s.producerName asc")
    public List<String> listProducers();

    @Query("select distinct s.scannerDef.name from Scanner s where s.producerName = :producerName order by s.scannerDef.name asc")
    public List<String> listScannerDefs(@Param("producerName") String producerName);
    
    @RestResource(path = "byProducerAndType", rel = "byProducerAndType")
    public Page<Scanner> findByProducerNameAndScannerDefName(@Param("producerName") String producerName, @Param("typeName") String scannerDefName, Pageable pageData);

    
    @RestResource(path = "byProducerAndTypeAndTag", rel = "byProducerAndTypeAndTag")
    public Page<Scanner> findByProducerNameAndScannerDefNameAndTagsName(@Param("producerName") String producerName, @Param("typeName") String scannerDefName, @Param("tagName") String tagName, Pageable pageData);
    
    // uncommenting this causes /scanners/search to produce two identical links for bypartnertype

    // https://localhost:5199/api/scanners/search/type?type=JDBC
//    @RestResource(path = "type", rel = "type")
//    public Page<Scanner> findByScannerDefName(@Param("type") String scannerDefName, Pageable pageData);


}
