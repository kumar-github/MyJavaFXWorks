package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "external_trade_source", catalog = "QA_30_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ExternalTradeSource.findAll", query = "SELECT e FROM ExternalTradeSource e"),
	@NamedQuery(name = "ExternalTradeSource.findByOid", query = "SELECT e FROM ExternalTradeSource e WHERE e.oid = :oid"),
	@NamedQuery(name = "ExternalTradeSource.findByExternalTradeSrcName", query = "SELECT e FROM ExternalTradeSource e WHERE e.externalTradeSrcName = :externalTradeSrcName"),
	@NamedQuery(name = "ExternalTradeSource.findByTransId", query = "SELECT e FROM ExternalTradeSource e WHERE e.transId = :transId")})

/*@NamedNativeQueries({
		@NamedNativeQuery(name = "ExternalTradeSource.findAllExternalTradeSource",
				query = "select etsource.* from external_trade_source etsource join external_trade_system etsystem on etsource.external_trade_system_oid = etsystem.oid and etsource.external_trade_src_name <> 'NonDefined' order by etsource.external_trade_src_name",
				resultClass = ExternalTradeSource.class)
})*/

public class ExternalTradeSource implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "oid")
	private Integer oid;
	@Basic(optional = false)
	@Column(name = "external_trade_src_name")
	private String externalTradeSrcName;
	@Basic(optional = false)
	@Column(name = "trans_id")
	private int transId;

	/*
    @JoinColumn(name = "alias_source_code", referencedColumnName = "alias_source_code")
    @ManyToOne
    private AliasSource aliasSourceCode;
	 */


	//modified the above variable's type from AliasSource to String to avoid dependency
	@Column(name="alias_source_code", columnDefinition="CHAR")
	private String aliasSourceCode;

	@JoinColumn(name = "external_trade_system_oid", referencedColumnName = "oid")
	@ManyToOne(optional = false)
	private ExternalTradeSystem externalTradeSystemOid;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeSourceOid")
	private Collection<ExternalTrade> externalTradeCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeSourceOid")
	private Collection<ExternalTradeType> externalTradeTypeCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeSourceOid")
	private Collection<ExternalMapping> externalMappingCollection;

	public ExternalTradeSource() {
	}

	public ExternalTradeSource(Integer oid) {
		this.oid = oid;
	}

	public ExternalTradeSource(Integer oid, String externalTradeSrcName, int transId) {
		this.oid = oid;
		this.externalTradeSrcName = externalTradeSrcName;
		this.transId = transId;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getExternalTradeSrcName()
	{
		return externalTradeSrcName;
	}

	public void setExternalTradeSrcName(String externalTradeSrcName) {
		this.externalTradeSrcName = externalTradeSrcName;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	/*
    public AliasSource getAliasSourceCode() {
        return aliasSourceCode;
    }

    public void setAliasSourceCode(AliasSource aliasSourceCode) {
        this.aliasSourceCode = aliasSourceCode;
    }
	 */
	public String getAliasSourceCode()
	{
		return aliasSourceCode;
	}

	public void setAliasSourceCode(String aliasSourceCode)
	{
		this.aliasSourceCode = aliasSourceCode;
	}


	public ExternalTradeSystem getExternalTradeSystemOid() {
		return externalTradeSystemOid;
	}

	public void setExternalTradeSystemOid(ExternalTradeSystem externalTradeSystemOid) {
		this.externalTradeSystemOid = externalTradeSystemOid;
	}

	@XmlTransient
	public Collection<ExternalTrade> getExternalTradeCollection() {
		return externalTradeCollection;
	}

	public void setExternalTradeCollection(Collection<ExternalTrade> externalTradeCollection) {
		this.externalTradeCollection = externalTradeCollection;
	}

	@XmlTransient
	public Collection<ExternalTradeType> getExternalTradeTypeCollection() {
		return externalTradeTypeCollection;
	}

	public void setExternalTradeTypeCollection(Collection<ExternalTradeType> externalTradeTypeCollection) {
		this.externalTradeTypeCollection = externalTradeTypeCollection;
	}

	@XmlTransient
	public Collection<ExternalMapping> getExternalMappingCollection() {
		return externalMappingCollection;
	}

	public void setExternalMappingCollection(Collection<ExternalMapping> externalMappingCollection) {
		this.externalMappingCollection = externalMappingCollection;
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
		if (!(object instanceof ExternalTradeSource)) {
			return false;
		}
		ExternalTradeSource other = (ExternalTradeSource) object;
		if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
        return getExternalTradeSrcName();
	}    
}