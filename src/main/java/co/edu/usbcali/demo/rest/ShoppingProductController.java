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

import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;
import co.edu.usbcali.demo.mapper.ShoppingProductMapper;
import co.edu.usbcali.demo.service.ShoppingProductService;

@RestController
@RequestMapping("/api/shoppingProduct")
@CrossOrigin("*")
public class ShoppingProductController {
	private final static Logger log = LoggerFactory.getLogger(CusotmerController.class);
	@Autowired
	ShoppingProductService shoppingProductService;
	@Autowired
	ShoppingProductMapper shoppingProductMapper;
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ShoppingProductDTO shoppingProductDTO)throws Exception{
	
			ShoppingProduct shoppingProduct =shoppingProductMapper.toShoppingProduct(shoppingProductDTO);
			shoppingProduct=shoppingProductService.save(shoppingProduct);
			shoppingProductDTO= shoppingProductMapper.toShoppingProductDTO(shoppingProduct);
			
			return ResponseEntity.ok().body(shoppingProductDTO);
		
	}
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody ShoppingProductDTO shoppingProductDTO)throws Exception{
	
		ShoppingProduct shoppingProduct = shoppingProductMapper.toShoppingProduct(shoppingProductDTO);
		shoppingProduct=shoppingProductService.update(shoppingProduct);
		shoppingProductDTO= shoppingProductMapper.toShoppingProductDTO(shoppingProduct);
			
			return ResponseEntity.ok().body(shoppingProductDTO);
		
	}
	@DeleteMapping("/delete/{sphrId}")
	public ResponseEntity<?> delete(@PathVariable("sphrId") Integer sphrId) throws Exception{
		shoppingProductService.deleteById(sphrId);
			return ResponseEntity.ok().build();
			
			
		
	}
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception{
		
			//Lista de Customer
			List<ShoppingProduct> shoppingProduct=shoppingProductService.findAll();
			//Declaro arreglo de DTOS
			List<ShoppingProductDTO> shoppingProductDTO = shoppingProductMapper.toListShoppingProductDTO(shoppingProduct);
			
			return ResponseEntity.ok().body(shoppingProductDTO);
			
		
	}
	@GetMapping("/findById/{sphrId}")
	public ResponseEntity<?> findById(@PathVariable("sphrId") Integer sphrId) throws Exception{
			Optional<ShoppingProduct>shoppingProductOptional=shoppingProductService.findById(sphrId);
			if(shoppingProductOptional.isPresent()==false) {
				return ResponseEntity.ok().body("ShoppingProduct not found");
			}
			ShoppingProduct shoppingProduct=shoppingProductOptional.get();
			ShoppingProductDTO shoppingProductDTO=shoppingProductMapper.toShoppingProductDTO(shoppingProduct);
			return ResponseEntity.ok().body(shoppingProductDTO);
			
			
		
	}
}

