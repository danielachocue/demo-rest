package co.edu.usbcali.demo.rest;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;
import co.edu.usbcali.demo.mapper.ShoppingCartMapper;
import co.edu.usbcali.demo.mapper.ShoppingProductMapper;
import co.edu.usbcali.demo.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")

public class CartController {

	private final static Logger log = LoggerFactory.getLogger(CartController.class);

	@Autowired
	CartService cartService;

	@Autowired
	ShoppingProductMapper shoppingProductMapper;

	@Autowired
	ShoppingCartMapper shoppingCartMapper;

	@PostMapping("/createCart/{email}")
	public ResponseEntity<?> createCart(@PathVariable("email") String email) throws Exception {

		ShoppingCart shoppingCart = cartService.createCart(email);
		ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toShoppingCartDTO(shoppingCart);
		log.info("createCart");
		return ResponseEntity.ok().body(shoppingCartDTO);
	}

	@PostMapping("/addProduct/{carId}/{proId}/{quantity}")
	public ResponseEntity<?> addProduct(@PathVariable("carId") Integer carId, @PathVariable("proId") String proId,
			@PathVariable("quantity") Integer quantity) throws Exception {

		ShoppingProduct shoppingProduct = cartService.addProduct(carId, proId, quantity);
		ShoppingProductDTO shoppingProductDTO = shoppingProductMapper.toShoppingProductDTO(shoppingProduct);
		log.info("addProduct");
		return ResponseEntity.ok().body(shoppingProductDTO);
	}

	@DeleteMapping("/removeProduct/{carId}/{proId}")
	public ResponseEntity<?> removeProduct(@PathVariable("carId") Integer carId, @PathVariable("proId") String proId)
			throws Exception {

		cartService.removeProduct(carId, proId);

		log.info("removeProduct");
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/clearCart/{carId}")
	public ResponseEntity<?> clearCart(@PathVariable("carId") Integer carId) throws Exception {

		cartService.clearCart(carId);
		;

		log.info("clearCart");
		return ResponseEntity.ok().build();
	}

	@GetMapping("/findShprByCarId/{carId}")
	public ResponseEntity<?> findShprByCarId(@PathVariable("carId") Integer carId) throws Exception {

		List<ShoppingProduct> shoppingProducts = cartService.findShoppingProductByShoppingCart(carId);
		if (shoppingProducts.isEmpty() == true || shoppingProducts == null) {
			return ResponseEntity.ok().body(null);
		}
		List<ShoppingProductDTO> shoppingProductDTOs = shoppingProductMapper.toListShoppingProductDTO(shoppingProducts);

		return ResponseEntity.ok().body(shoppingProductDTOs);

	}

	@GetMapping("/findShcaByEmail/{email}")
	public ResponseEntity<?> findShcaByEmail(@PathVariable("email") String email) throws Exception {

		List<ShoppingCart> shoppingCarts = cartService.findShoppingCartByEmail(email);
		if (shoppingCarts.isEmpty() == true || shoppingCarts == null) {
			return ResponseEntity.ok().body(null);
		}
		List<ShoppingCartDTO> shoppingCartDTOs = shoppingCartMapper.toListShoppingCartDTO(shoppingCarts);

		return ResponseEntity.ok().body(shoppingCartDTOs);

	}

	@PutMapping("/finalizarCompra/{carId}/{payId}")
	public ResponseEntity<?> finalizarCompra(@PathVariable("carId") Integer carId, @PathVariable("payId") Integer payId)
			throws Exception {

		ShoppingCart shoppingCart = cartService.finalizarCompra(carId, payId);
		ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toShoppingCartDTO(shoppingCart);
		log.info("finalizarCompra");
		return ResponseEntity.ok().body(shoppingCartDTO);
	}

}