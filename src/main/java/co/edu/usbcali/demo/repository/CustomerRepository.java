package co.edu.usbcali.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	
	public List<Customer> findByEnableAndEmail(String enable, String email);
	
	@Query("SELECT cus FROM Customer cus WHERE cus.name LIKE 'Mar%'")
	public List<Customer> findCustomerLikemar();
	
	//Tarea
	
	//selecciona el customer donde el nombre sea igual al parametro
	public List<Customer> findByNameEquals(String name);
	
	//selecciona los customer con enebale igual a Y, los ordena segun el Email y enordene desendente segun el nombre
	@Query("SELECT cus FROM Customer cus WHERE cus.enable= 'Y' "
			+ "ORDER BY cus.email, cus.name DESC ")
	public List<Customer> findByNameOrderBy();
	
	//selecciona el customer donde el telefono contenga 566
	@Query("SELECT cus FROM Customer cus WHERE cus.phone LIKE '%566%'")
	public List<Customer> findByPhoneLike();
	
	//Selecciona los customer con el nombre del parametro
	@Query("SELECT cus FROM Customer cus WHERE cus.name IN('Ibby Fyall','Boothe Bridgwater')")
	public List<Customer> findByNameIn();
	
	//Selecciona el nombre del parametro o el email, cualquiera de los dos que exista
	public List<Customer> findByNameOrEmail(String name, String email);
	
	//modo automatico (por nombre del metodo)
	public Optional<Customer> findByEmailAndToken(String email,String token);

}
