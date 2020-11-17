package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.dto.CusotmerDTO;

@Mapper
public interface CustomerMapper {
	
	public CusotmerDTO toCustomerDTO(Customer customer);
	
	public Customer toCustomer(CusotmerDTO customerDTO);
	
	//trae una lista de customers y me devuelve una lista de customersDTO
	public List<CusotmerDTO> toCustomersDTO(List<Customer> customers);
	
	//trae una lista de customersDTO y me devuelve una lista de customers
	public List<Customer> toCustomers(List<CusotmerDTO> customersDTO);

}
