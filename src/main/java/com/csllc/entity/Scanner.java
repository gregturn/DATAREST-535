package com.csllc.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;


@Entity
@Table(name = "CS_FW_SCANNER")
@EntityListeners(AuditingEntityListener.class)
public class Scanner implements Serializable, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="SCANNER_ID_SEQ", sequenceName = "SCANNER_ID_SEQ", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCANNER_ID_SEQ") 
    @Basic(optional = false)
    @Column(name = "SCANNER_ID", nullable = false, precision = 22, scale = 0)
    private Long scannerId;
    
    @Column(name = "ENABLED_FLAG")
    private Character enabledFlag;
    
    @CreatedDate
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
    
    @Basic(optional = false)
    @Column(name = "LOOKUP_VALUE", nullable = false, length = 255)
    private String lookupValue;
    
    @Basic(optional = false)
    @Column(name = "PRODUCER_NAME", nullable = false, length = 24)
    private String producerName;
    
    // TODO 4 we had to set this to eager to retrieve as a sub-resource, would prefer to set lazy 
    @JoinColumn(name = "SCANNER_DEF_ID", referencedColumnName = "SCANNER_DEF_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScannerDef scannerDef;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CS_FW_TAG_SCANNER", joinColumns = @JoinColumn(name = "SCANNER_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scanner", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<ScannerParameter> scannerParameterList;

    
    @Transient
    private ListManager listManager = new ListManager();
    
    @Transient
    private Map metadata = new HashMap<>();

    public Map getMetadata() {
        return metadata;
    }
    
    public Scanner() {
    }

    public Scanner(Long scannerId) {
        this.scannerId = scannerId;
    }

    public Scanner(Long scannerId, String lookupValue, String producerName, String createdBy, Date updated, String updatedBy) {
        this.scannerId = scannerId;
        this.lookupValue = lookupValue;
        this.producerName = producerName;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
    }
    
    @Override
    public Long getId() {
        return scannerId;
    }

    public Long getScannerId() {
        return scannerId;
    }

    public void setScannerId(Long scannerId) {
        this.scannerId = scannerId;
    }

    public String getLookupValue() {
        return lookupValue;
    }

    public void setLookupValue(String lookupValue) {
        this.lookupValue = lookupValue;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public Character getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Character enabledFlag) {
        this.enabledFlag = enabledFlag;
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

    public ScannerDef getScannerDef() {
        return scannerDef;
    }

    public void setScannerDef(ScannerDef scannerDefId) {
        this.scannerDef = scannerDefId;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> newTags) {
//        tags = listManager.updateList(tags, newTags);
        tags = newTags;
    }

    public Set<ScannerParameter> getScannerParameterList() {
        return scannerParameterList;
    }

    public void setScannerParameterList(Set<ScannerParameter> incomingScannerParameters) {
        scannerParameterList = listManager.updateList(incomingScannerParameters, scannerParameterList);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scannerId != null ? scannerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scanner)) {
            return false;
        }
        Scanner other = (Scanner) object;
        if ((this.scannerId == null && other.scannerId != null) || (this.scannerId != null && !this.scannerId.equals(other.scannerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csllc.dbentities.Scanner[ scannerId=" + scannerId + " ]";
    }

}
