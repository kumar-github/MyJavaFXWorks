/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.tradecapture.controller;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "external_mapping", catalog = "DEV_31_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExternalMapping.findAll", query = "SELECT e FROM ExternalMapping e"),
    @NamedQuery(name = "ExternalMapping.findByOid", query = "SELECT e FROM ExternalMapping e WHERE e.oid = :oid"),
    @NamedQuery(name = "ExternalMapping.findByMappingType", query = "SELECT e FROM ExternalMapping e WHERE e.mappingType = :mappingType"),
    @NamedQuery(name = "ExternalMapping.findByExternalValue1", query = "SELECT e FROM ExternalMapping e WHERE e.externalValue1 = :externalValue1"),
    @NamedQuery(name = "ExternalMapping.findByExternalValue2", query = "SELECT e FROM ExternalMapping e WHERE e.externalValue2 = :externalValue2"),
    @NamedQuery(name = "ExternalMapping.findByExternalValue3", query = "SELECT e FROM ExternalMapping e WHERE e.externalValue3 = :externalValue3"),
    @NamedQuery(name = "ExternalMapping.findByExternalValue4", query = "SELECT e FROM ExternalMapping e WHERE e.externalValue4 = :externalValue4"),
    @NamedQuery(name = "ExternalMapping.findByAliasValue", query = "SELECT e FROM ExternalMapping e WHERE e.aliasValue = :aliasValue"),
    @NamedQuery(name = "ExternalMapping.findByTransId", query = "SELECT e FROM ExternalMapping e WHERE e.transId = :transId")})
public class ExternalMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Basic(optional = false)
    @Column(name = "mapping_type")
    private Character mappingType;
    @Column(name = "external_value1")
    private String externalValue1;
    @Column(name = "external_value2")
    private String externalValue2;
    @Column(name = "external_value3")
    private String externalValue3;
    @Column(name = "external_value4")
    private String externalValue4;
    @Basic(optional = false)
    @Column(name = "alias_value")
    private String aliasValue;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "external_trade_source_oid", referencedColumnName = "oid")
    @ManyToOne(optional = false)
    private ExternalTradeSource externalTradeSourceOid;

    public ExternalMapping() {
    }

    public ExternalMapping(Integer oid) {
        this.oid = oid;
    }

    public ExternalMapping(Integer oid, Character mappingType, String aliasValue, int transId) {
        this.oid = oid;
        this.mappingType = mappingType;
        this.aliasValue = aliasValue;
        this.transId = transId;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Character getMappingType() {
        return mappingType;
    }

    public void setMappingType(Character mappingType) {
        this.mappingType = mappingType;
    }

    public String getExternalValue1() {
        return externalValue1;
    }

    public void setExternalValue1(String externalValue1) {
        this.externalValue1 = externalValue1;
    }

    public String getExternalValue2() {
        return externalValue2;
    }

    public void setExternalValue2(String externalValue2) {
        this.externalValue2 = externalValue2;
    }

    public String getExternalValue3() {
        return externalValue3;
    }

    public void setExternalValue3(String externalValue3) {
        this.externalValue3 = externalValue3;
    }

    public String getExternalValue4() {
        return externalValue4;
    }

    public void setExternalValue4(String externalValue4) {
        this.externalValue4 = externalValue4;
    }

    public String getAliasValue() {
        return aliasValue;
    }

    public void setAliasValue(String aliasValue) {
        this.aliasValue = aliasValue;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public ExternalTradeSource getExternalTradeSourceOid() {
        return externalTradeSourceOid;
    }

    public void setExternalTradeSourceOid(ExternalTradeSource externalTradeSourceOid) {
        this.externalTradeSourceOid = externalTradeSourceOid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExternalMapping)) {
            return false;
        }
        ExternalMapping other = (ExternalMapping) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExternalMapping[ oid=" + oid + " ]";
    }
    
}
