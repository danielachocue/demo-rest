package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	
	public List<Product> findByEnableAndName(String enable, String name);
	
	@Query("SELECT pro FROM Product pro WHERE pro.name LIKE 'iPh%'")
	public List<Product> findProductLikeiPh();
	
	//Tarea
	
	//selecciona el producto donde el nombre es igual al parametro
	public List<Product> findByNameEquals(String name);
	
	//Selecciona los priductos con enable igual a Y y los ordena segun el precio
	@Query("SELECT pro FROM Product pro WHERE pro.enable='Y'"
			+ "ORDER BY pro.price, pro.name DESC")
	public List<Product> findPriceOrderBy();
	
	//Selecciona el nombre del producto que contenga X
	@Query("SELECT pro FROM Product pro WHERE pro.name LIKE '%X%'")
	public List<Product> findNameLikeContent();
	
	//Selecciona los productos con id del parametro
	@Query("SELECT pro FROM Product pro WHERE pro.proId IN('APPL45','ACL578')")
	public List<Product> findProdcutIn();
	
	//Selecciona los productos con el id o precio segun el parametro
	public List<Product> findByProIdOrPrice(String proId, int price);
}
