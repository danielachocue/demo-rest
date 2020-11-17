package co.edu.usbcali.demo.service;




import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingProductService extends GenericService<ShoppingProduct, Integer> {
	
	public Long totalShoppingProductByShoppingCart(Integer carId);
	
	public ShoppingProduct findShoppinProductProId(Integer carId, String proId);

}
