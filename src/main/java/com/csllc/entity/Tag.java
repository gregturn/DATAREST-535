package com.csllc.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "CS_FW_TAG", uniqueConstraints = { @UniqueConstraint(columnNames = {"TAG_NAME"})})
@EntityListeners(AuditingEntityListener.class)
public class Tag implements Serializable, Identifiable<Long> {
    
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="TAG_ID_SEQ", sequenceName = "TAG_ID_SEQ", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAG_ID_SEQ") 
    @Basic(optional = false)
    @Column(name = "TAG_ID", nullable = false, precision = 22, scale = 0)
    private Long tagId;
    
    @Basic(optional = false)
    @Column(name = "TAG_NAME", nullable = false, length = 255)
    private String name;
    
    @Basic(optional = false)
    @Column(name = "TAG_MANAGER", nullable = false, length = 255)
    private String tagManager = "";
    
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
    private Long versionNumber = BigInteger.ONE.longValue();
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tagId", fetch = FetchType.LAZY)
//    private Set<Scanner> scannerList;
//    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tagId", fetch = FetchType.LAZY)
//    private Set<ReceivedDocument> receivedDocumentList;
//    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tagId", fetch = FetchType.LAZY)
//    private Set<MailboxProcessor> mailboxProcessorList;
//    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tagId", fetch = FetchType.LAZY)
//    private Set<NewDocument> newDocumentList;

    public Tag() {
    }

    public Tag(Long tagId) {
        this.tagId = tagId;
    }

    public Tag(Long tagId, String tagName, String tagManager, String createdBy, Date updated, String updatedBy) {
        this.tagId = tagId;
        this.name = tagName;
        this.tagManager = tagManager;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
    }

    @Override
    public Long getId() {
        return this.tagId;
    }
    
    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String tagName) {
        this.name = tagName;
    }

    public String getTagManager() {
        return tagManager;
    }

    public void setTagManager(String tagManager) {
        this.tagManager = tagManager;
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

    public Long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Long versionNumber) {
        this.versionNumber = versionNumber;
    }

//    public Set<Scanner> getScannerList() {
//        return scannerList;
//    }
//
//    public void setScannerList(Set<Scanner> scannerList) {
//        this.scannerList = scannerList;
//    }
//
//    public Set<ReceivedDocument> getReceivedDocumentList() {
//        return receivedDocumentList;
//    }
//
//    public void setReceivedDocumentList(Set<ReceivedDocument> receivedDocumentList) {
//        this.receivedDocumentList = receivedDocumentList;
//    }
//
//    public Set<MailboxProcessor> getMailboxProcessorList() {
//        return mailboxProcessorList;
//    }
//
//    public void setMailboxProcessorList(Set<MailboxProcessor> mailboxProcessorList) {
//        this.mailboxProcessorList = mailboxProcessorList;
//    }
//
//    public Set<NewDocument> getNewDocumentList() {
//        return newDocumentList;
//    }
//
//    public void setNewDocumentList(Set<NewDocument> newDocumentList) {
//        this.newDocumentList = newDocumentList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagId != null ? tagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.tagId == null && other.tagId != null) || (this.tagId != null && !this.tagId.equals(other.tagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csllc.dbentities.Tag[ tagId=" + tagId + " ]";
    }
    
}
