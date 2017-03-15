/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.tradecapture.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "external_comment", catalog = "DEV_31_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExternalComment.findAll", query = "SELECT e FROM ExternalComment e"),
    @NamedQuery(name = "ExternalComment.findByOid", query = "SELECT e FROM ExternalComment e WHERE e.oid = :oid"),
    @NamedQuery(name = "ExternalComment.findByCommentText", query = "SELECT e FROM ExternalComment e WHERE e.commentText = :commentText"),
    @NamedQuery(name = "ExternalComment.findByTransId", query = "SELECT e FROM ExternalComment e WHERE e.transId = :transId")})
public class ExternalComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Column(name = "comment_text")
    private String commentText;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(mappedBy = "externalCommentOid")
    private Collection<ExchToolsTrade> exchToolsTradeCollection;

    public ExternalComment() {
    }

    public ExternalComment(Integer oid) {
        this.oid = oid;
    }

    public ExternalComment(Integer oid, int transId) {
        this.oid = oid;
        this.transId = transId;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<ExchToolsTrade> getExchToolsTradeCollection() {
        return exchToolsTradeCollection;
    }

    public void setExchToolsTradeCollection(Collection<ExchToolsTrade> exchToolsTradeCollection) {
        this.exchToolsTradeCollection = exchToolsTradeCollection;
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
        if (!(object instanceof ExternalComment)) {
            return false;
        }
        ExternalComment other = (ExternalComment) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExternalComment[ oid=" + oid + " ]";
    }
    
}
