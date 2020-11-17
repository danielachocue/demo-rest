package co.edu.usbcali.demo.jpql;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.usbcali.demo.domain.Customer;


@SpringBootTest
public class TestCustomerJPQL {
	
	private final static Logger log=LoggerFactory.getLogger(TestCustomerJPQL.class);

	//Inyeccion de dependencia	
	@Autowired
	EntityManager entitymanager;
	
	//se ejecuta antes de que se ejecuta cada que se ejecuta una prueba
	@BeforeEach
	public void beforEach() {
		log.info("beforEach");
		assertNotNull(entitymanager,"El entitymanager es nulo");
	}
	//Where pasando por parametros
	@Test
	public void selectWherePara() {
		log.info("selectWherePara");
		//Realizo el Select donde where pasando parametros de la tabla
		String jpql="SELECT cus FROM Customer cus WHERE cus.enable=:enable AND cus.email=:email";
		//guaarda en una lista todos los customer
		List<Customer> customers= entitymanager.
				createQuery(jpql, Customer.class).
				setParameter("enable","Y").
				setParameter("email", "bropsdf@imgur.com").
				getResultList();
		
		//for funcional, que por cada registro ejecute esa funcion
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});
	}
	
	
	//un Where filtrando uno de los campos de la tabla
	@Test
	public void selectWhereEnable() {
		log.info("selectWhereEnable");
		//Realizo el Select donde el enable sea igual a Y
		String jpql="SELECT cus FROM Customer cus WHERE cus.enable='Y' ORDER BY cus.email";
		//guaarda en una lista todos los customer
		List<Customer> customers= entitymanager.createQuery(jpql, Customer.class).getResultList();
		
		//for funcional, que por cada registro ejecute esa funcion
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});
	}
	
	@Test
	// Select donde los nombre empiece con Mar
	public void selectLike() {
		log.info("selectLike");
		//Realizo el Select donde el nombre tenga empiece por Mar
		String jpql="SELECT cus FROM Customer cus WHERE cus.name LIKE 'Mar%'";
		//guaarda en una lista todos los customer
		List<Customer> customers= entitymanager.createQuery(jpql, Customer.class).getResultList();
		
		//for funcional, que por cada registro ejecute esa funcion
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});
	}

	@Test
	public void selectAll() {
		log.info("SelectAll");
		//Realizo el Select all de la tabla de customers
		String jpql="SELECT cus FROM Customer cus";
		//guaarda en una lista todos los customer
		List<Customer> customers= entitymanager.createQuery(jpql, Customer.class).getResultList();
		
		//recorrer la lista con for each
//		for (Customer customer : customers) {
//			log.info(customer.getEmail());
//			log.info(customer.getName());
			
//		}
		
		//for funcional, que por cada registro ejecute esa funcion
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});
	}

}
