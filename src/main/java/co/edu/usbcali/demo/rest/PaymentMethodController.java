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

import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.dto.PaymentMethodDTO;
import co.edu.usbcali.demo.mapper.PaymentMethodMapper;
import co.edu.usbcali.demo.service.PaymentMethodService;

@RestController
@RequestMapping("/api/paymentMethod")
@CrossOrigin("*")
public class PaymentMethodController {
	
	public static Logger log= LoggerFactory.getLogger(PaymentMethodController.class);
	
	//Inyeccion de dependencia
	@Autowired
	PaymentMethodService paymentMethodService;
	
	@Autowired
	PaymentMethodMapper paymentMethodMapper;
	
	//Se crea un PaymentMethod y se guarda
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody PaymentMethodDTO paymentMethodDTO) throws Exception {
			
			//Se crea una lista de PaymentMethods con todos los PaymentMethods
			PaymentMethod paymentMethod= paymentMethodMapper.toPaymenMethod(paymentMethodDTO);
			paymentMethodService.save(paymentMethod);
			paymentMethodDTO=paymentMethodMapper.toPaymenMethodDTO(paymentMethod);
			
			return ResponseEntity.ok().body(paymentMethodDTO);
	}
	
	//Metodo update
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody PaymentMethodDTO paymentMethodDTO) throws Exception {
			
			//Se crea una lista de PaymentMethods con todos los PaymentMethods
			PaymentMethod payMethod= paymentMethodMapper.toPaymenMethod(paymentMethodDTO);
			paymentMethodService.update(payMethod);
			paymentMethodDTO=paymentMethodMapper.toPaymenMethodDTO(payMethod);
			
			return ResponseEntity.ok().body(paymentMethodDTO);
	}
	
	//Metodo delete
	@DeleteMapping("/delete/{payId}")
	public ResponseEntity<?> delete(@PathVariable("payId") Integer payId) throws Exception {
			
			Optional<PaymentMethod> paymentMethodOptional=paymentMethodService.findById(payId);
			if(paymentMethodOptional.isPresent()==false) {
				return ResponseEntity.ok().body("PaymentMethod Not Found");
			}
			
			//se Trae los PaymentMethods
			PaymentMethod paymentMethod=paymentMethodOptional.get();
			paymentMethodService.delete(paymentMethod);
			return ResponseEntity.ok().build();
	}
	
	//Busca todos los PaymentMethod
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception{
		
		//se crea la lista de los PaymentMethods con todos los PaymentMethods
		List<PaymentMethod> paymenMethods= paymentMethodService.findAll();
		
		//se crea una lista de PaymentMethodsDTO
		List<PaymentMethodDTO> paymentMethodDTOs=paymentMethodMapper.toPaymenMethodsDTO(paymenMethods);
		
		//se retorna la lista de los DTOs
		return ResponseEntity.ok().body(paymentMethodDTOs);
	}
	
	//se crea el metodo de tipo DTO verificando que ya existe el paymentMethod
	@GetMapping("/findById/{payId}")
	public ResponseEntity<?> finById(@PathVariable("payId") Integer payId) throws Exception{
		
		Optional<PaymentMethod> paymentMethodOptional=paymentMethodService.findById(payId);
		if(paymentMethodOptional.isPresent()==false) {
			return ResponseEntity.ok().body("PaymentMethod Not Found");
		}
		
		//se Trae los PaymentMethods
		PaymentMethod paymentMethod=paymentMethodOptional.get();
		
		//se pasa la informacion del entity al DTO
		PaymentMethodDTO paymentMethodDTO=paymentMethodMapper.toPaymenMethodDTO(paymentMethod);
		
		return ResponseEntity.ok().body(paymentMethodDTO);
	}


}
