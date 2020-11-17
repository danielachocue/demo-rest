package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ShoppingProductRepositoryTest {
	
	private final static Logger log=LoggerFactory.getLogger(ShoppingProductRepositoryTest.class);
	
	private static Integer shprId=null;
	
	private static String proId="APPL90";
	
	private static Integer carId=1;
	
	//Inyeccion de Dependencia
	@Autowired
	ShoppingProductRepository shoppingProductRepository;	
	@Autowired
	ProductRepository productRepository;	
	@Autowired
	ShoppingCartRepository shoppingCartRepositoty;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		
		log.info("save");
		
		ShoppingProduct shoppingProduct= new ShoppingProduct();
		shoppingProduct.setShprId(null);
		shoppingProduct.setQuantity(12);
		shoppingProduct.setTotal(123000L);
		
		//se confirma el producto ya existente
		Optional<Product> productOptional= productRepository.findById(proId);
		assertTrue(productOptional.isPresent(),"El Product "+proId+" no existe");
		
		//se agrerga el producto al carro
		Product product=productOptional.get();
		shoppingProduct.setProduct(product);
		
		//se confirma el shoppingcart ya existente
		Optional<ShoppingCart> shoppingCartOptional= shoppingCartRepositoty.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(),"El shoppingCart "+carId+" no existe");
		
		//se agrega el ShoppingCart
		ShoppingCart shoppingCart= shoppingCartOptional.get();
		shoppingProduct.setShoppingCart(shoppingCart);
		
		//se guarda el shoppingProduct
		shoppingProduct=shoppingProductRepository.save(shoppingProduct);
		
		//se trae el id para verificar que no es nulo
		shprId=shoppingProduct.getShprId();
		assertNotNull(shprId, "El shprId es nulo");
		
		//se imprime el id
		log.info("shprId: "+shprId);
	}
	
	@Test
	@Transactional
	@Order(2)
	void finById() {
		
		log.info("finById");
		
		Optional<ShoppingProduct> shoppingProductOptional= shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El shoppingProduct con shprId "+shprId+" no existe");
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		log.info("update");
		
		Optional<ShoppingProduct> shoppingProductOptional= shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El shoppingProduct con shprId "+shprId+" no existe");
		
		ShoppingProduct shoppingProduct= shoppingProductOptional.get();
		shoppingProduct.setQuantity(6);
		
		shoppingProductRepository.save(shoppingProduct);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		log.info("delete");
		
		Optional<ShoppingProduct> shoppingProductOptional= shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El shoppingProduct con shprId "+shprId+" no existe");
		
		ShoppingProduct shoppingProduct=shoppingProductOptional.get();
		
		shoppingProductRepository.delete(shoppingProduct);
	}
}
