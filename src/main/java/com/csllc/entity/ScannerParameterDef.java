package com.csllc.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;

@Entity
@Table(name = "CS_FW_SCANNER_PARAMETER_DEF")
@EntityListeners(AuditingEntityListener.class)
public class ScannerParameterDef implements Serializable, Identifiable<Long> {
    
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="SCANNER_PARAMETER_DEF_ID_SEQ", sequenceName = "SCANNER_PARAMETER_DEF_ID_SEQ", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCANNER_PARAMETER_DEF_ID_SEQ") 
    @Basic(optional = false)
    @Column(name = "SCANNER_PARAMETER_DEF_ID", nullable = false, precision = 22, scale = 0)
    private Long scannerParameterDefId;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "TYPE")
    private ParameterType type = ParameterType.TEXT;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String name;
    
    @Basic(optional = false)
    @Column(name = "DISPLAY_NAME", nullable = false, length = 255)
    private String displayName;
    
    @Column(length = 1020)
    private String value;
    
    @Column(length = 1020)
    private String description;
    
    @Column(name = "REQUIRED_FLAG")
    private Character requiredFlag;
    
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
    
    @JoinColumn(name = "SCANNER_DEF_ID", referencedColumnName = "SCANNER_DEF_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScannerDef scannerDefId;

    public ScannerParameterDef() {
    }

    public ScannerParameterDef(Long scannerParameterDefId) {
        this.scannerParameterDefId = scannerParameterDefId;
    }

    public ScannerParameterDef(Long scannerParameterDefId, String name, String displayName, String createdBy, Date updated, String updatedBy) {
        this.scannerParameterDefId = scannerParameterDefId;
        this.name = name;
        this.displayName = displayName;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
    }

    public Long getScannerParameterDefId() {
        return scannerParameterDefId;
    }

    public void setScannerParameterDefId(Long scannerParameterDefId) {
        this.scannerParameterDefId = scannerParameterDefId;
    }

    public ParameterType getType() {
        return type;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Character getRequiredFlag() {
        return requiredFlag;
    }

    public void setRequiredFlag(Character requiredFlag) {
        this.requiredFlag = requiredFlag;
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

    public ScannerDef getScannerDefId() {
        return scannerDefId;
    }

    public void setScannerDefId(ScannerDef scannerDefId) {
        this.scannerDefId = scannerDefId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scannerParameterDefId != null ? scannerParameterDefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScannerParameterDef)) {
            return false;
        }
        ScannerParameterDef other = (ScannerParameterDef) object;
        if ((this.scannerParameterDefId == null && other.scannerParameterDefId != null) || (this.scannerParameterDefId != null && !this.scannerParameterDefId.equals(other.scannerParameterDefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csllc.dbentities.ScannerParameterDef[ scannerParameterDefId=" + scannerParameterDefId + " ]";
    }

    @Override
    public Long getId() {
        return this.scannerParameterDefId;
    }
    
}
