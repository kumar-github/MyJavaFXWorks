/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.controller;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "portfolio_alias", catalog = "QA_30_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortfolioAlias.findAll", query = "SELECT p FROM PortfolioAlias p"),
    @NamedQuery(name = "PortfolioAlias.findByPortNum", query = "SELECT p FROM PortfolioAlias p WHERE p.portfolioAliasPK.portNum = :portNum"),
    @NamedQuery(name = "PortfolioAlias.findByAliasSourceCode", query = "SELECT p FROM PortfolioAlias p WHERE p.portfolioAliasPK.aliasSourceCode = :aliasSourceCode"),
    @NamedQuery(name = "PortfolioAlias.findByPortAliasName", query = "SELECT p FROM PortfolioAlias p WHERE p.portAliasName = :portAliasName"),
    @NamedQuery(name = "PortfolioAlias.findByPortAliasDesc", query = "SELECT p FROM PortfolioAlias p WHERE p.portAliasDesc = :portAliasDesc"),
    @NamedQuery(name = "PortfolioAlias.findByTransId", query = "SELECT p FROM PortfolioAlias p WHERE p.transId = :transId")})
public class PortfolioAlias implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PortfolioAliasPK portfolioAliasPK;
    @Basic(optional = false)
    @Column(name = "port_alias_name")
    private String portAliasName;
    @Column(name = "port_alias_desc")
    private String portAliasDesc;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "alias_source_code", referencedColumnName = "alias_source_code", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AliasSource aliasSource;

    public PortfolioAlias() {
    }

    public PortfolioAlias(PortfolioAliasPK portfolioAliasPK) {
        this.portfolioAliasPK = portfolioAliasPK;
    }

    public PortfolioAlias(PortfolioAliasPK portfolioAliasPK, String portAliasName, int transId) {
        this.portfolioAliasPK = portfolioAliasPK;
        this.portAliasName = portAliasName;
        this.transId = transId;
    }

    public PortfolioAlias(int portNum, String aliasSourceCode) {
        this.portfolioAliasPK = new PortfolioAliasPK(portNum, aliasSourceCode);
    }

    public PortfolioAliasPK getPortfolioAliasPK() {
        return portfolioAliasPK;
    }

    public void setPortfolioAliasPK(PortfolioAliasPK portfolioAliasPK) {
        this.portfolioAliasPK = portfolioAliasPK;
    }

    public String getPortAliasName() {
        return portAliasName;
    }

    public void setPortAliasName(String portAliasName) {
        this.portAliasName = portAliasName;
    }

    public String getPortAliasDesc() {
        return portAliasDesc;
    }

    public void setPortAliasDesc(String portAliasDesc) {
        this.portAliasDesc = portAliasDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public AliasSource getAliasSource() {
        return aliasSource;
    }

    public void setAliasSource(AliasSource aliasSource) {
        this.aliasSource = aliasSource;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (portfolioAliasPK != null ? portfolioAliasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortfolioAlias)) {
            return false;
        }
        PortfolioAlias other = (PortfolioAlias) object;
        if ((this.portfolioAliasPK == null && other.portfolioAliasPK != null) || (this.portfolioAliasPK != null && !this.portfolioAliasPK.equals(other.portfolioAliasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PortfolioAlias[ portfolioAliasPK=" + portfolioAliasPK + " ]";
    }
    
}
