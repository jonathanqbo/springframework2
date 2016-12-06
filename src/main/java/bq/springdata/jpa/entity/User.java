package bq.springdata.jpa.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String email;
	
	private String cellPhone;
	
	private String homePhone;
	
	private Date birthday;
	
	private String password;
	
	// don't need do persist manually by setting entity Cascade Type to PERSIST
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "pk_homeaddress")
	private Address homeAddress;
	
	// don't need do persist manually by setting entity Cascade Type to PERSIST
	@OneToMany(mappedBy="user", cascade=CascadeType.PERSIST)
	private List<Address> historyAddress;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public List<Address> getHistoryAddress() {
		return historyAddress;
	}

	public void setHistoryAddress(List<Address> historyAddress) {
		this.historyAddress = historyAddress;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", email=" + email + ", cellPhone=" + cellPhone + ", homePhone=" + homePhone + ", birthday="
				+ birthday + ", password=" + password + ", homeAddress=" + homeAddress + ", historyAddress="
				+ historyAddress + "]";
	}
	
}
