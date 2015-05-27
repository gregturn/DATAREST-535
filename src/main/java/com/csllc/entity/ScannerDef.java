package com.csllc.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;


@Entity
@Table(name = "CS_FW_SCANNER_DEF", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"SCANNER_DEF_NAME"}),
    @UniqueConstraint(columnNames = {"DISPLAY_NAME"})})
@EntityListeners(AuditingEntityListener.class)
public class ScannerDef implements Serializable, Identifiable<Long> {
    
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="SCANNER_DEF_ID_SEQ", sequenceName = "SCANNER_DEF_ID_SEQ", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCANNER_DEF_ID_SEQ") 
    @Basic(optional = false)
    @Column(name = "SCANNER_DEF_ID", nullable = false, precision = 22, scale = 0)
    private Long scannerDefId;
    
    @Column(name = "SCANNER_DEF_NAME", length = 24)
    private String name;
    
    @Column(name = "DISPLAY_NAME", length = 255)
    private String displayName;
    
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @CreatedBy
    @Basic(optional = false)
    @Column(name = "CREATED_BY", nullable = false, length = 24)
    private String createdBy;

    @LastModifiedDate
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    
    @LastModifiedBy
    @Basic(optional = false)
    @Column(name = "UPDATED_BY", nullable = false, length = 24)
    private String updatedBy;
    
    @Column(name = "VERSION_NUMBER")
    private BigInteger versionNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scannerDefId", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<ScannerParameterDef> scannerParameterDefList;

    
    @Transient
    private Map metadata = new HashMap<>();

    public Map getMetadata() {
        return metadata;
    }
    
    public ScannerDef() {
    }

    public ScannerDef(Long scannerDefId) {
        this.scannerDefId = scannerDefId;
    }

    public ScannerDef(Long scannerDefId, String createdBy, Date updated, String updatedBy) {
        this.scannerDefId = scannerDefId;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
    }

    @Override
    public Long getId() {
        return this.scannerDefId;
    }
    
    public Long getScannerDefId() {
        return scannerDefId;
    }

    public void setScannerDefId(Long scannerDefId) {
        this.scannerDefId = scannerDefId;
    }

    public String getName() {
        return name;
    }

    public void setName(String scannerDefName) {
        this.name = scannerDefName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public Set<ScannerParameterDef> getScannerParameterDefList() {
        return scannerParameterDefList;
    }

    public void setScannerParameterDefList(Set<ScannerParameterDef> scannerParameterDefList) {
         this.scannerParameterDefList = scannerParameterDefList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scannerDefId != null ? scannerDefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScannerDef)) {
            return false;
        }
        ScannerDef other = (ScannerDef) object;
        if ((this.scannerDefId == null && other.scannerDefId != null) || (this.scannerDefId != null && !this.scannerDefId.equals(other.scannerDefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csllc.dbentities.ScannerDef[ scannerDefId=" + scannerDefId + " ]";
    }
    
}
