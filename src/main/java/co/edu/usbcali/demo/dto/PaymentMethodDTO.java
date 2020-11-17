package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class PaymentMethodDTO {
	
	@Range(min=1, max=255)
	private Integer payId;
	
	@NotNull
	@NotEmpty
	@Size(min=1,max=255)
	private String enable;
	
	@NotNull
	@NotEmpty
	@Size(min=4,max=255)
	private String name;

	public PaymentMethodDTO() {
		super();
	}

	public PaymentMethodDTO(Integer payId, String enable,String name) {
		super();
		this.payId = payId;
		this.enable = enable;
		this.name = name;
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
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

}
