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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "execution_type", catalog = "DEV_31_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExecutionType.findAll", query = "SELECT e FROM ExecutionType e"),
    @NamedQuery(name = "ExecutionType.findByExecTypeCode", query = "SELECT e FROM ExecutionType e WHERE e.execTypeCode = :execTypeCode"),
    @NamedQuery(name = "ExecutionType.findByExecTypeDesc", query = "SELECT e FROM ExecutionType e WHERE e.execTypeDesc = :execTypeDesc"),
    @NamedQuery(name = "ExecutionType.findByFifoPriority", query = "SELECT e FROM ExecutionType e WHERE e.fifoPriority = :fifoPriority"),
    @NamedQuery(name = "ExecutionType.findByTransId", query = "SELECT e FROM ExecutionType e WHERE e.transId = :transId")})
public class ExecutionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "exec_type_code", columnDefinition="CHAR")
    private String execTypeCode;
    
    @Basic(optional = false)
    @Column(name = "exec_type_desc")
    private String execTypeDesc;
    @Basic(optional = false)
    @Column(name = "fifo_priority")
    private short fifoPriority;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;

    public ExecutionType() {
    }

    public ExecutionType(String execTypeCode) {
        this.execTypeCode = execTypeCode;
    }

    public ExecutionType(String execTypeCode, String execTypeDesc, short fifoPriority, int transId) {
        this.execTypeCode = execTypeCode;
        this.execTypeDesc = execTypeDesc;
        this.fifoPriority = fifoPriority;
        this.transId = transId;
    }

    public String getExecTypeCode() {
        return execTypeCode;
    }

    public void setExecTypeCode(String execTypeCode) {
        this.execTypeCode = execTypeCode;
    }

    public String getExecTypeDesc() {
        return execTypeDesc;
    }

    public void setExecTypeDesc(String execTypeDesc) {
        this.execTypeDesc = execTypeDesc;
    }

    public short getFifoPriority() {
        return fifoPriority;
    }

    public void setFifoPriority(short fifoPriority) {
        this.fifoPriority = fifoPriority;
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
        hash += (execTypeCode != null ? execTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExecutionType)) {
            return false;
        }
        ExecutionType other = (ExecutionType) object;
        if ((this.execTypeCode == null && other.execTypeCode != null) || (this.execTypeCode != null && !this.execTypeCode.equals(other.execTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExecutionType[ execTypeCode=" + execTypeCode + " ]";
    }
    
}
