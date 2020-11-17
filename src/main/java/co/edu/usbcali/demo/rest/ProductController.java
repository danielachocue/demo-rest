package co.edu.usbcali.demo.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.dto.ProductDTO;
import co.edu.usbcali.demo.mapper.ProductMapper;
import co.edu.usbcali.demo.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	public static Logger log= LoggerFactory.getLogger(ProductController.class);
	
	//Inyeccion de dependencia
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductMapper productMapper;
	
	//Crea y Guarda un nuevo producto
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ProductDTO productDTO) throws Exception{
			
			//se crea una lista de productos son todos los productos
			Product product=productMapper.toProduct(productDTO);
			productService.save(product);
			productDTO=productMapper.toProductDTO(product);
			
			return ResponseEntity.ok().body(productDTO);
			
	}
	
	//Metodo Update
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody ProductDTO productDTO) throws Exception{
			
			//se crea una lista de productos son todos los productos
			Product product=productMapper.toProduct(productDTO);
			productService.update(product);
			productDTO=productMapper.toProductDTO(product);
			
			return ResponseEntity.ok().body(productDTO);
			
	}
	
	//Se crea el metodo de tipo DTO verificando que exista el product
	@DeleteMapping("/delete/{proId}")
	public ResponseEntity<?> delete(@PathVariable("proId") String proId) throws Exception{
	
			Optional<Product> productOptional=productService.findById(proId);
			if(productOptional.isPresent()==false) {
				return ResponseEntity.ok().body("Porduct Not Found");
			}
			//se trae los productos
			Product product=productOptional.get();
			
			productService.delete(product);
			
			return ResponseEntity.ok().body("Producto eliminado");
		
	}
	
	//Busca todos los Products
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception{
		
			//se crea la lista de productos con todos los productos
			List<Product> products= productService.findAll();
			//se crea una lista de customerDTO
			List<ProductDTO> productDTOs= productMapper.toProductsDTO(products);
			
			//se retorna la lista de DTOs
			return ResponseEntity.ok().body(productDTOs);
	}
	
	
	//Se crea el metodo de tipo DTO verificando que exista el product
	@GetMapping("/findById/{proId}")
	public ResponseEntity<?> findById(@PathVariable("proId") String proId) throws Exception{
	
			Optional<Product> productOptional=productService.findById(proId);
			if(productOptional.isPresent()==false) {
				return ResponseEntity.ok().body("Porduct Not Found");
			}
			//se trae los productos
			Product product=productOptional.get();
			//se pasa la informacion del entity a el DTO
			ProductDTO productDTO=productMapper.toProductDTO(product);
			
			return ResponseEntity.ok().body(productDTO);
		
	}
}
