package co.edu.usbcali.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CusotmerDTO {
	

	@NotNull
	@Email
	@Size(min=3,max=255)
	private String email;
	
	
	@NotNull
	@NotEmpty
	@Size(min=3,max=255)
	private String address;
	

	@NotNull
	@NotEmpty
	@Size(min=1,max=1)
	private String enable;
	

	@NotNull
	@NotEmpty
	@Size(min=4,max=255)
	private String name;
	

	@NotNull
	@NotEmpty
	@Size(min=6,max=255)
	private String phone;
	

	@NotNull
	@NotEmpty
	@Size(max=255)
	private String token;
	
	
	public CusotmerDTO() {
		super();
	}

	public CusotmerDTO(String email, String address, String enable, String name, String phone, String token) {
		super();
		this.email = email;
		this.address = address;
		this.enable = enable;
		this.name = name;
		this.phone = phone;
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
