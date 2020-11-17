package co.edu.usbcali.demo.jpql;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.usbcali.demo.domain.Product;

@SpringBootTest
class TestProductJPQL {
	
	private final static Logger log= LoggerFactory.getLogger(TestProductJPQL.class);
	
	//Inyeccion de dependencia
	@Autowired
	EntityManager entityManager;
	
	//se ejecuta antes de que se ejecuta cada que se ejecuta una prueba
	@BeforeEach
	public void beforeEach() {
		log.info("beforeEach");
		assertNotNull(entityManager,"El entitymanager es nulo");
	}
	
	//Where pasando por parametros
	@Test
	public void selectWherePara() {
		log.info("selectWherePara");
		//Realizo el Select donde where pasando parametros de la tabla
		String jpql="SELECT pro FROM Product pro WHERE pro.enable=:enable";
		//guaarda en una lista todos los customer
		List<Product> products= entityManager.
				createQuery(jpql, Product.class).
				setParameter("enable","Y").
				//setParameter("proId ", "APPL90").
				getResultList();
		
		//for funcional, que por cada registro ejecute esa funcion
		products.forEach(product->{
			log.info(product.getName());
			log.info(product.getEnable());
			log.info(product.getDetail());
		});
	}
	
	//un Where donde se filtra uno de los campos de la tabla
	@Test
	void selectWhereId() {
		log.info("selectWhere");
		//Realiza el select donde el id sea igual a APPL90
		String jpql="SELECT pro FROM Product pro WHERE pro.proId= 'APPL90'";
		//guarda en una lista todos los productos
		List<Product> products= entityManager.createQuery(jpql, Product.class).getResultList();
		
		//for funcional para recorrer la lista de productos
		products.forEach(product->{
			log.info(product.getName());
			log.info(product.getEnable());
		});
	}
	
	//select donde el nomrbre empiece con iph
	@Test
	void selectLike() {
		log.info("selectLike");
		//Realiza el select donde el nomrbre empiece con iph
		String jpql="SELECT pro FROM Product pro WHERE pro.name LIKE 'iPh%'";
		//guarda en una lista todos los productos
		List<Product> products= entityManager.createQuery(jpql, Product.class).getResultList();
		
		//for funcional para recorrer la lista de productos
		products.forEach(product->{
			log.info(product.getName());
			log.info(product.getEnable());
		});
	}

	@Test
	void selectAll() {
		log.info("SleectAll");
		//Realiza el select all de la tabala product
		String jpql="SELECT pro FROM Product pro";
		//guarda en una lista todos los productos
		List<Product> products= entityManager.createQuery(jpql, Product.class).getResultList();
		
		//for funcional para recorrer la lista de productos
		products.forEach(product->{
			log.info(product.getName());
			log.info(product.getEnable());
		});
	}

}
