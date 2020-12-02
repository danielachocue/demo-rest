package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.ShoppingProduct;




@SpringBootTest
@Rollback(false)
class ShoppingProductServiceTest {
	private final static Logger log = LoggerFactory.getLogger(ShoppingProductServiceTest.class);

	@Autowired
	ShoppingProductService shoppingProductService;

	@Test
	void totalCarrito() {
		//Arrange
		Long total=0l;
		Integer carId=21;
		//Act
		total=shoppingProductService.totalShoppingProductByShoppingCart(carId);
		log.info(total+"");
		//Assert
				
		assertTrue(total>0);
	}
	@Test
	void totalItemsCarrito() {
		//Arrange
		Integer totalItems=0;
		Integer carId=21;
		//Act
		totalItems=shoppingProductService.totalItems(carId);
		log.info("Items totales: "+totalItems);
		//Assert
				
		assertTrue(totalItems>0);
	}
	
	@Test
	void removeAllItems() {
		//Arrange
			List<Integer> lista = null;
			Integer carId=19;
			//Act
				lista=shoppingProductService.selectShprIdByCardId(carId);
				lista.forEach(shpro->{
					try {
						shoppingProductService.deleteById(shpro);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				//Assert
						
				assertTrue(lista.size()>0,"La lista esta vacia");
	}
	@Test
	void findCarId() {
		//Arrange
		List<ShoppingProduct> lista = null;
		Integer carId=21;
		//Act
		lista=shoppingProductService.findByCarId(carId);
		lista.forEach(shpro->{
			log.info(shpro.getShprId()+" id");
			log.info(shpro.getTotal()+" total");

		});
		//Assert
				
		assertTrue(lista.size()>0,"El shoppingCart esta vacio");

	}

}