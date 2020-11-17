package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class ProductTest {
	private final static String proid= "App1540";
	private final static Logger log= LoggerFactory.getLogger(ProductTest.class);
	
	//Patron de inyeccion de dependencia
	@Autowired
	EntityManager entityManager;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		
		//Siga si no es nulo
		assertNotNull(entityManager,"El entityManager es Null");
		Product product= entityManager.find(Product.class, proid);
		
		//siga si es nulo
		assertNull(product,"el producto con Id "+proid+" ya existe");
		
		//Se llena el objeto
		product= new Product();
		product.setName("phone");
		product.setDetail("Color Negro");
		product.setProId(proid);
		product.setEnable("X");
		product.setPrice(1236);
		product.setImage("wasw1456ad");
		
		entityManager.persist(product);
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		//siga si no es nulo
		assertNotNull(entityManager,"el entityManager es nulo");
		Product product= entityManager.find(Product.class, proid);
		
		//siga si no es nulo
		assertNotNull(product,"el producto con id "+proid+" no existe");
		
		log.info(product.getName());
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		//siga si no es nulo
		assertNotNull(entityManager,"el entityManager es nulo");
		
		Product product= entityManager.find(Product.class, proid);
		
		//siga si no es nulo osea existe
		assertNotNull(product,"el producto con Id "+proid+" no existe");
		
		product.setEnable("Y");
		entityManager.merge(product);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Product product= entityManager.find(Product.class, proid);
		
		assertNotNull(product, "el producto con id "+proid+" no existe");
		
		entityManager.remove(product);
	}
}
