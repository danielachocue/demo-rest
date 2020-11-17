package co.edu.usbcali.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingProductRepository extends JpaRepository<ShoppingProduct, Integer> {

	@Query("SELECT SUM(shpr.total) FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public Long totalShoppingProductByShoppingCart(Integer carId);
	
	@Query("SELECT shoppingPro FROM ShoppingProduct shoppingPro WHERE shoppingPro.shoppingCart.carId=:carId AND shoppingPro.product.proId=:proId ")
	public ShoppingProduct findShoppinProductAndProId(Integer carId, String proId);
}
