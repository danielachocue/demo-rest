package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingProductRepository extends JpaRepository<ShoppingProduct, Integer> {
	
	@Query("SELECT SUM(shpr.total) FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public Long totalShoppingProductByShoppingCart(Integer carId);
	
	@Query("SELECT SUM(shpr.quantity) FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public Integer totalItems(Integer carId);
	
	@Query("SELECT shpr FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId and product.proId=:proId")
	public ShoppingProduct selectShprId(Integer carId,String proId);
	
	@Query("SELECT shpr.shprId FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public List<Integer> selectShprIdByCardId(Integer carId);
	
	@Query("SELECT shpr FROM ShoppingProduct shpr WHERE shpr.shoppingCart.carId=:carId")
	public List<ShoppingProduct> findByCarId(Integer carId);
	
	@Query("SELECT shpr FROM ShoppingProduct shpr WHERE shpr.shoppingCart.customer.email=:email")
	public List<ShoppingProduct> selectPurchase(String email);
}
