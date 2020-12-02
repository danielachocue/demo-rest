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

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.dto.AddShoppingProductDTO;
import co.edu.usbcali.demo.dto.CloseShoppingCartDTO;
import co.edu.usbcali.demo.dto.EmailDTO;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;
import co.edu.usbcali.demo.mapper.ShoppingCartMapper;
import co.edu.usbcali.demo.mapper.ShoppingProductMapper;
import co.edu.usbcali.demo.service.CartService;
import co.edu.usbcali.demo.service.ShoppingCartService;
import co.edu.usbcali.demo.service.ShoppingProductService;

@RestController
@RequestMapping("/api/shoppingCart")
@CrossOrigin("*")
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartMapper shoppingCartMapper;
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ShoppingProductMapper shoppingProductMapper;
	@Autowired
	CartService cartService;
	@Autowired
	ShoppingProductService shoppingProductService;
	
	private final static Logger log = LoggerFactory.getLogger(CusotmerController.class);
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ShoppingCartDTO shoppingCartDTO)throws Exception{
	
			ShoppingCart shoppingCart =shoppingCartMapper.toShoppingCart(shoppingCartDTO);
			shoppingCart=shoppingCartService.save(shoppingCart);
			shoppingCartDTO= shoppingCartMapper.toShoppingCartDTO(shoppingCart);
			
			return ResponseEntity.ok().body(shoppingCartDTO);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody ShoppingCartDTO shoppingCartDTO)throws Exception{
	
		ShoppingCart shoppingCart = shoppingCartMapper.toShoppingCart(shoppingCartDTO);
		shoppingCart=shoppingCartService.update(shoppingCart);
		shoppingCartDTO= shoppingCartMapper.toShoppingCartDTO(shoppingCart);
			
			return ResponseEntity.ok().body(shoppingCartDTO);
		
	}
	@DeleteMapping("/delete/{carId}")
	public ResponseEntity<?> delete(@PathVariable("carId") Integer carId) throws Exception{
		shoppingCartService.deleteById(carId);
			return ResponseEntity.ok().build();
			
			
		
	}
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception{
		
			//Lista de Customer
			List<ShoppingCart> shoppingCart=shoppingCartService.findAll();
			//Declaro arreglo de DTOS
			List<ShoppingCartDTO> shoppingCartDTO = shoppingCartMapper.toListShoppingCartDTO(shoppingCart);
			
			return ResponseEntity.ok().body(shoppingCartDTO);
			
		
	}
	@GetMapping("/findById/{carId}")
	public ResponseEntity<?> findById(@PathVariable("carId") Integer carId) throws Exception{
			Optional<ShoppingCart>ShoppingCartOptional=shoppingCartService.findById(carId);
			if(ShoppingCartOptional.isPresent()==false) {
				return ResponseEntity.ok().body("ShoppingCart not found");
			}
			ShoppingCart shoppingCart=ShoppingCartOptional.get();
			ShoppingCartDTO shoppingCartDTO=shoppingCartMapper.toShoppingCartDTO(shoppingCart);
			return ResponseEntity.ok().body(shoppingCartDTO);
			
			
		
	}
	@PostMapping("/createCart")
	public ResponseEntity<?> createCart(@Valid @RequestBody EmailDTO emailDTO)throws Exception{
		ShoppingCart shoppingCart = cartService.createCart(emailDTO.getEmail());
		ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toShoppingCartDTO(shoppingCart);
		return ResponseEntity.ok().body(shoppingCartDTO);			
		
	}
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@Valid @RequestBody AddShoppingProductDTO addShpoppingProductDTO)throws Exception{
		ShoppingProduct shoppingProduct = cartService.addProduct(addShpoppingProductDTO.getCarId(), addShpoppingProductDTO.getProId(),addShpoppingProductDTO.getQuantity());
		ShoppingProductDTO shoppingProductDTO = shoppingProductMapper.toShoppingProductDTO(shoppingProduct);
		return ResponseEntity.ok().body(shoppingProductDTO);
		
	}
	@DeleteMapping("/removeProduct/{carId}/{proId}")
	public ResponseEntity<?> removeProduct(@PathVariable("carId") Integer carId, @PathVariable("proId") String proId) throws Exception{
			cartService.removeProduct(carId, proId);
			return ResponseEntity.ok().build();
			
			
		
	}
	@DeleteMapping("/clearCart/{carId}")
	public ResponseEntity<?> clearCart(@PathVariable("carId") Integer carId) throws Exception{
			cartService.clearCart(carId);
			return ResponseEntity.ok().build();
			
			
		
	}
	@GetMapping("/findShoppingProductByShoppingCart/{carId}")
	public ResponseEntity<?> findShoppingProductByShoppingCart(@PathVariable("carId") Integer carId) throws Exception{
		List<ShoppingProduct> shoppingProducts = cartService.findShoppingProductByShoppingCart(carId);
		if (shoppingProducts.isEmpty() == true || shoppingProducts == null) {
			return ResponseEntity.ok().body(null);
		}
		List<ShoppingProductDTO> shoppingProductDTOs = shoppingProductMapper.toListShoppingProductDTO(shoppingProducts);

		return ResponseEntity.ok().body(shoppingProductDTOs);

			
		
	}
	@GetMapping("/findCarIdShoppingCartsByEmail/{email}")
	public ResponseEntity<?> findCarIdShoppingCartsByEmail(@PathVariable("email") String email) throws Exception{
		List<ShoppingCart> shoppingCarts = cartService.findCarIdShoppingCartsByEmail(email);
		if (shoppingCarts.isEmpty() == true || shoppingCarts == null) {
			return ResponseEntity.ok().body(null);
		}
		List<ShoppingCartDTO> shoppingCartDTOs = shoppingCartMapper.toListShoppingCartDTO(shoppingCarts);
			
			return ResponseEntity.ok().body(shoppingCartDTOs);
			
		
	}
	@PutMapping("/closeShoppingCart")
	public ResponseEntity<?> closeShoppingCart(@Valid @RequestBody CloseShoppingCartDTO closeShoppingCartDTO) throws Exception{
		ShoppingCart shoppingCart = cartService.closeShoppingCart(closeShoppingCartDTO.getCarId(),closeShoppingCartDTO.getPayId());
			ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toShoppingCartDTO(shoppingCart);
			return ResponseEntity.ok().body(shoppingCartDTO);
			
		
	}
	@GetMapping("/selectPurchase/{email}")
	public ResponseEntity<?> selectPurchase(@PathVariable("email") String email) throws Exception{
		List<ShoppingProductDTO> shoppingProductsDTO = shoppingProductMapper.toListShoppingProductDTO(cartService.selectPurchase(email));

		return ResponseEntity.ok().body(shoppingProductsDTO);
	


			
		
	}
}
