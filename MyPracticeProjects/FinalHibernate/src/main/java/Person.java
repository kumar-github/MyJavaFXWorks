import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="person_details")
public class Person  implements java.io.Serializable
{
	@Id
	@Column(name="ID", unique=true, nullable=false, precision=22, scale=0)
	private BigDecimal id;
	
	@Column(name="NAME", length=50)
	private String name;
	
	@Column(name="SURNAME", length=50)
	private String surname;
	
	@Temporal(TemporalType.DATE)
	@Column(name="BIRTHDATE", length=7)
	private Date birthdate;
	
	@Column(name="SEX", length=8)
	private String sex;

	public Person()
	{
	}
	
	public Person(BigDecimal id)
	{
		this.id = id;
	}
	
	public Person(BigDecimal id, String name, String surname, Date birthdate, String sex)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.sex = sex;
	}
	
	public BigDecimal getId()
	{
		return this.id;
	}
	
	public void setId(BigDecimal id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getSurname()
	{
		return this.surname;
	}
	
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	public Date getBirthdate()
	{
		return this.birthdate;
	}
	
	public void setBirthdate(Date birthdate)
	{
		this.birthdate = birthdate;
	}

	public String getSex()
	{
		return this.sex;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
}