package com.csllc.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;

@Entity
@Table(name = "CS_FW_SCANNER_PARAMETER", uniqueConstraints = { @UniqueConstraint(columnNames = {"SCANNER_ID", "SCANNER_PARAMETER_DEF_ID"})})
@EntityListeners(AuditingEntityListener.class)
public class ScannerParameter implements Serializable, Identifiable<Long> {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="SCANNER_PARAMETER_ID_SEQ", sequenceName = "SCANNER_PARAMETER_ID_SEQ", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCANNER_PARAMETER_ID_SEQ") 
    @Basic(optional = false)
    @Column(name = "SCANNER_PARAMETER_ID", nullable = false, precision = 22, scale = 0)
    private Long scannerParameterId;
    
    @Column(length = 1020)
    private String value;
    
    @CreatedDate
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();
    
    @CreatedBy
    @Basic(optional = false)
    @Column(name = "CREATED_BY", nullable = false, length = 24)
    private String createdBy = "";
    
    @LastModifiedDate
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated = new Date();
    
    @LastModifiedBy
    @Basic(optional = false)
    @Column(name = "UPDATED_BY", nullable = false, length = 24)
    private String updatedBy = "";
    
    @Column(name = "VERSION_NUMBER")
    private BigInteger versionNumber;
    
    @JoinColumn(name = "SCANNER_ID", referencedColumnName = "SCANNER_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Scanner scanner;
    
    @JoinColumn(name = "SCANNER_PARAMETER_DEF_ID", referencedColumnName = "SCANNER_PARAMETER_DEF_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScannerParameterDef scannerParameterDefId;
    
    public ScannerParameter() {
    }

    public ScannerParameter(Long scannerParameterId) {
        this.scannerParameterId = scannerParameterId;
    }

    public ScannerParameter(Long scannerParameterId, String createdBy, Date updated, String updatedBy) {
        this.scannerParameterId = scannerParameterId;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
    }

    public Long getScannerParameterId() {
        return scannerParameterId;
    }

    public void setScannerParameterId(Long scannerParameterId) {
        this.scannerParameterId = scannerParameterId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public BigInteger getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(BigInteger versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public ScannerParameterDef getScannerParameterDefId() {
        return scannerParameterDefId;
    }

    public void setScannerParameterDefId(ScannerParameterDef scannerParameterDefId) {
        this.scannerParameterDefId = scannerParameterDefId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scannerParameterId != null ? scannerParameterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScannerParameter)) {
            return false;
        }
        ScannerParameter other = (ScannerParameter) object;
        if ((this.scannerParameterId == null && other.scannerParameterId != null) || (this.scannerParameterId != null && !this.scannerParameterId.equals(other.scannerParameterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csllc.dbentities.ScannerParameter[ scannerParameterId=" + scannerParameterId + " ]";
    }

    @Override
    public Long getId() {
        return this.scannerParameterId;
    }
    
}
