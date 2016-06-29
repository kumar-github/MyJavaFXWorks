/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.controller;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smurugabushanam
 */
@Entity
@Table(name = "icts_product", catalog = "QA_30_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IctsProduct.findAll", query = "SELECT i FROM IctsProduct i"),
    @NamedQuery(name = "IctsProduct.findByProductId", query = "SELECT i FROM IctsProduct i WHERE i.productId = :productId"),
    @NamedQuery(name = "IctsProduct.findByProductName", query = "SELECT i FROM IctsProduct i WHERE i.productName = :productName"),
    @NamedQuery(name = "IctsProduct.findByOrderType", query = "SELECT i FROM IctsProduct i WHERE i.orderType = :orderType"),
    @NamedQuery(name = "IctsProduct.findByTransId", query = "SELECT i FROM IctsProduct i WHERE i.transId = :transId")})
public class IctsProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "product_id", nullable = false)
    private Integer productId;
    @Basic(optional = false)
    @Column(name = "product_name", nullable = false, length = 200)
    private String productName;
    @Basic(optional = false)
    @Column(name = "order_type", nullable = false, length = 200)
    private String orderType;
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    @OneToMany(mappedBy = "productId", fetch = FetchType.EAGER)
    private Collection<Trade> tradeCollection;

    public IctsProduct() {
    }

    public IctsProduct(Integer productId) {
        this.productId = productId;
    }

    public IctsProduct(Integer productId, String productName, String orderType, int transId) {
        this.productId = productId;
        this.productName = productName;
        this.orderType = orderType;
        this.transId = transId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<Trade> getTradeCollection() {
        return tradeCollection;
    }

    public void setTradeCollection(Collection<Trade> tradeCollection) {
        this.tradeCollection = tradeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IctsProduct)) {
            return false;
        }
        IctsProduct other = (IctsProduct) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated6.IctsProduct[ productId=" + productId + " ]";
    }
    
}
