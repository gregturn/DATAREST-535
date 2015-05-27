package com.csllc.repository;

import com.csllc.entity.ScannerParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScannerParameterRepository extends JpaRepository<ScannerParameter, Long> {


}
