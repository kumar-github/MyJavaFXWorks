/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.controller;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "contract_status", catalog = "QA_30_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContractStatus.findAll", query = "SELECT c FROM ContractStatus c"),
    @NamedQuery(name = "ContractStatus.findByContrStatusCode", query = "SELECT c FROM ContractStatus c WHERE c.contrStatusCode = :contrStatusCode"),
    @NamedQuery(name = "ContractStatus.findByContrStatusDesc", query = "SELECT c FROM ContractStatus c WHERE c.contrStatusDesc = :contrStatusDesc"),
    @NamedQuery(name = "ContractStatus.findByTransId", query = "SELECT c FROM ContractStatus c WHERE c.transId = :transId")})
public class ContractStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "contr_status_code", columnDefinition="CHAR")
    private String contrStatusCode;
    
    @Column(name = "contr_status_desc")
    private String contrStatusDesc;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;

    public ContractStatus() {
    }

    public ContractStatus(String contrStatusCode) {
        this.contrStatusCode = contrStatusCode;
    }

    public ContractStatus(String contrStatusCode, int transId) {
        this.contrStatusCode = contrStatusCode;
        this.transId = transId;
    }

    public String getContrStatusCode() {
        return contrStatusCode;
    }

    public void setContrStatusCode(String contrStatusCode) {
        this.contrStatusCode = contrStatusCode;
    }

    public String getContrStatusDesc() {
        return contrStatusDesc;
    }

    public void setContrStatusDesc(String contrStatusDesc) {
        this.contrStatusDesc = contrStatusDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contrStatusCode != null ? contrStatusCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContractStatus)) {
            return false;
        }
        ContractStatus other = (ContractStatus) object;
        if ((this.contrStatusCode == null && other.contrStatusCode != null) || (this.contrStatusCode != null && !this.contrStatusCode.equals(other.contrStatusCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContractStatus[ contrStatusCode=" + contrStatusCode + " ]";
    }
    
}
