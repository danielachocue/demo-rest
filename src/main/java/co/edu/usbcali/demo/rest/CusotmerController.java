package co.edu.usbcali.demo.rest;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.dto.CusotmerDTO;
import co.edu.usbcali.demo.mapper.CustomerMapper;
import co.edu.usbcali.demo.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CusotmerController {
	
	public static Logger log =LoggerFactory.getLogger(CusotmerController.class);
	
	//Inyeccion de dependencia
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerMapper customerMapper;
	
	//Crea y Guarda un nuevo customer
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody CusotmerDTO customerDTO) throws Exception{
		
			Customer customer=customerMapper.toCustomer(customerDTO);
			customer=customerService.save(customer);
			
			return ResponseEntity.ok().body(customerDTO);
		
	}
	
	//Crea y Guarda un nuevo customer
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody CusotmerDTO customerDTO) throws Exception{
		
			Customer customer=customerMapper.toCustomer(customerDTO);
			customer=customerService.update(customer);
			
			return ResponseEntity.ok().body(customerDTO);
		
	}
	
	//Se crea el metodo de tipo DTO verificando que exista el customer
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<?> delete(@PathVariable("email") String email) throws Exception {
		
			customerService.deleteById(email);
			
			return ResponseEntity.ok().build();

		
	}
	
	//Busca todos los customers
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception{
		
			//se crea una lista de customers con tods los customers
			List<Customer> customers=customerService.findAll();
			//se crea una lista de customersDTO
			List<CusotmerDTO> customerDTOs= customerMapper.toCustomersDTO(customers);
			
			//se retorna la listade DTOs
			return ResponseEntity.ok().body(customerDTOs);
			
	}
	
	//Se crea el metodo de tipo DTO verificando que exista el customer
	@GetMapping("/findById/{email}")
	public ResponseEntity<?> findById(@PathVariable("email") String email) throws Exception {
		
			Optional<Customer> customerOptional=customerService.findById(email);
			if(customerOptional.isPresent()==false) {
				return ResponseEntity.ok().body("Customer Not Found");				
			}		
			
			//traigo el objeto
			Customer customer=customerOptional.get();
			//Paso la informacion del Entity a el DTO
			CusotmerDTO customerDTO=customerMapper.toCustomerDTO(customer);
			
			return ResponseEntity.ok().body(customerDTO);

		
	}
}
