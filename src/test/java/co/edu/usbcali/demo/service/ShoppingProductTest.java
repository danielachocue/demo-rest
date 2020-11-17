package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(false)
class ShoppingProductTest {
	
	@Autowired
	ShoppingProductService shoppingProductService;

	@Test
	void suma() {
		
		//Arrange
		
		Long total=0L;
		Integer carId=1;
		
		//Act
		
		total=shoppingProductService.totalShoppingProductByShoppingCart(carId);
		
		//Assert
		
		assertTrue(total>0);
	}
}
