package com.csllc.repository;

import com.csllc.entity.ScannerParameterDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScannerParameterDefRepository extends JpaRepository<ScannerParameterDef, Long> {


}
