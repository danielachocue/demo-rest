package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

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
class ProductRepositoryTest {
	
	private final static Logger log= LoggerFactory.getLogger(ProductRepositoryTest.class);
	
	private final static String proId= "App1540";
	
	//Inyeccion dependencia
	@Autowired
	ProductRepository productRepository;
	

	@Test
	@Transactional
	@Order(1)
	void save() {
		
		//Me retorna un Optional
		Optional<Product> productOptional=productRepository.findById(proId);
		
		//Siga si False, valida si me esta devolviendo un opcional
		assertFalse(productOptional.isPresent(),"El product ya no exite");
		
		//Se le asigna memoria
		Product product= new Product();
		
		//Se llena el objeto
		product= new Product();
		product.setName("phone");
		product.setDetail("Color Negro");
		product.setProId(proId);
		product.setEnable("X");
		product.setPrice(1236);
		product.setImage("wasw1456ad");
		
		//Grabar
		productRepository.save(product);
		
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		
		log.info("findById");
		
		//Me retorna un Optional
		Optional<Product> productOptional=productRepository.findById(proId);
		
		//Siga si True, quiere decir que existe
		assertTrue(productOptional.isPresent(),"El producto ya no exite");

	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		log.info("updDate");
		
		//Me retorna un Optional
		Optional<Product> productOptional=productRepository.findById(proId);
		
		//Siga si True, quiere decir que existe
		assertTrue(productOptional.isPresent(),"El product no exite");
		
		Product product= productOptional.get();
		
		product.setEnable("N");
		
		productRepository.save(product);

	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		log.info("delete");
		
		//Me retorna un Optional
		Optional<Product> productOptional=productRepository.findById(proId);
		
		//Siga si True, quiere decir que existe
		assertTrue(productOptional.isPresent(),"El product no exite");
		
		Product product= productOptional.get();
		
		productRepository.delete(product);

	}
	
	@Test
	@Transactional
	@Order(5)
	void findAll() {
		
		log.info("findAll");
		
		//funcional
		productRepository.findAll().forEach(product->{
			log.info("Name: "+product.getName());
			log.info("Detail: "+product.getDetail());
		});
	}
	
	@Test
	@Transactional
	@Order(6)
	void count() {
		
		log.info("Count: "+productRepository.count());
		
	}
	
	@Test
	@Transactional
	@Order(7)
	void findByEnableAndName() {
		
		List<Product> products= productRepository.findByEnableAndName("Y", "motorolaX");
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name: "+product.getName());
			log.info("Detail: "+product.getDetail());
		});
		
	}
	
	@Test
	@Transactional
	@Order(8)
	void findProductLikeiPh() {
		
		List<Product> products= productRepository.findProductLikeiPh();
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name: "+product.getName());
			log.info("Detail: "+product.getDetail());
		});
		
	}
	
	@Test
	@Transactional
	@Order(9)
	void findBynameEqual() {
		
		List<Product> products= productRepository.findByNameEquals("motorolaX");
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name: "+product.getName());
			log.info("Detail: "+product.getDetail());
		});
		
	}
	
	@Test
	@Transactional
	@Order(10)
	void findByPriceorderBy() {
		
		List<Product> products= productRepository.findPriceOrderBy();
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name: "+product.getName());
			log.info("Detail: "+product.getDetail());
			log.info("Price: "+product.getPrice());
		});
		
	}
	
	@Test
	@Transactional
	@Order(11)
	void findBynameLike() {
		
		List<Product> products= productRepository.findNameLikeContent();
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name: "+product.getName());
			log.info("Price: "+product.getPrice());
		});
		
	}
	
	@Test
	@Transactional
	@Order(12)
	void findByprodcutIn() {
		
		List<Product> products= productRepository.findProdcutIn();
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name: "+product.getName());
			log.info("ProId: "+product.getProId());
		});
		
	}
	
	@Test
	@Transactional
	@Order(13)
	void findByproIdOrPrice() {
		
		List<Product> products= productRepository.findByProIdOrPrice("APPL45",4440000);
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name: "+product.getName());
			log.info("ProId: "+product.getProId());
			log.info("Price: "+product.getPrice());
		});
		
	}
}
