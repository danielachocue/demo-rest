package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@SpringBootTest
@Rollback(false)
class CartServiceTest {
	
	@Autowired
	CartService cartService;

	@Test
	void debeCrearUnShoppingCart()throws Exception {
		//Arrange
		String email="abaglowbn@furl.net";
		ShoppingCart shoppingCart=null;
		
		//Act
		shoppingCart=cartService.createCart(email);
		
		//Assert
		assertNotNull(shoppingCart);
	}
	
	@Test
	void noDebeCrearUnShoppingCartPorCustomerDisable()throws Exception {
		//Arrange
		String email="abeamondqq@harvard.edu";
		
		//Act
		assertThrows(Exception.class, ()->cartService.createCart(email));
		
	}
	
	@Test
	void noDebeCrearUnShoppingCartPorCustomerNull()throws Exception {
		//Arrange
		String email=null;
		
		//Act
		assertThrows(Exception.class, ()->cartService.createCart(email));
		
	}
	
	@Test
	void noDebeCrearUnShoppingCartPorCustomerNoExiste()throws Exception {
		//Arrange
		String email="jperez@vvv.com";
		
		//Act
		assertThrows(Exception.class, ()->cartService.createCart(email));
		
	}
	
	@Test
	void debeDeAgregarProductShoppingCart()throws Exception {
		//Arrange
		Integer carId=2;
		String proId= "APPL45";
		Integer quantity=10;
		Integer items=2;
		ShoppingProduct shoppingProduct=null;
		
		//Act
		shoppingProduct=cartService.addProduct(carId, proId, quantity,items);
		
		//Assert
		assertNotNull(shoppingProduct, "El shoppingProduct es nulo");
		
	}
	
	@Test
	void removerProduct() throws Exception{
		//Arrange
		Integer carId=2;
		String proId= "APPL45";
		
		//Act
		cartService.removeProduct(carId, proId);
		
		//Assert
	}
	
	@Test
	void LimpiarCart() throws Exception {
		
		//Arrange
		Integer carId=2;
		
		//Act
		
		cartService.clearCart(carId);
		
		//Assert
		//assertNotNull(shoppingCart, "el shoppingCart es nulo");
	}
	
	@Test
	void ListaProducts() throws Exception{
		
		//Arrange
		
		Integer carId=1;
		
		//Assert
		
		cartService.findShoppingProductByShoppingCart(carId);
	}

}
