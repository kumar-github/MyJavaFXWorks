/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.controller;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Saravana Kumar M
 */
@Embeddable
public class PortfolioAliasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "port_num")
    private int portNum;
    
    @Basic(optional = false)
    @Column(name = "alias_source_code", columnDefinition="CHAR")
    private String aliasSourceCode;

    public PortfolioAliasPK() {
    }

    public PortfolioAliasPK(int portNum, String aliasSourceCode) {
        this.portNum = portNum;
        this.aliasSourceCode = aliasSourceCode;
    }

    public int getPortNum() {
        return portNum;
    }

    public void setPortNum(int portNum) {
        this.portNum = portNum;
    }

    public String getAliasSourceCode() {
        return aliasSourceCode;
    }

    public void setAliasSourceCode(String aliasSourceCode) {
        this.aliasSourceCode = aliasSourceCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) portNum;
        hash += (aliasSourceCode != null ? aliasSourceCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortfolioAliasPK)) {
            return false;
        }
        PortfolioAliasPK other = (PortfolioAliasPK) object;
        if (this.portNum != other.portNum) {
            return false;
        }
        if ((this.aliasSourceCode == null && other.aliasSourceCode != null) || (this.aliasSourceCode != null && !this.aliasSourceCode.equals(other.aliasSourceCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PortfolioAliasPK[ portNum=" + portNum + ", aliasSourceCode=" + aliasSourceCode + " ]";
    }
    
}
