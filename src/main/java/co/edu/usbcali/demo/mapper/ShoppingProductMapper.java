package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;

@Mapper
public interface ShoppingProductMapper {
	@Mapping(source = "product.proId", target = "productId")
	@Mapping(source = "shoppingCart.carId", target = "shoppingCartId")
	public ShoppingProductDTO toShoppingProductDTO(ShoppingProduct shoppingProduct);

	@Mapping(source = "productId", target = "product.proId")
	@Mapping(source = "shoppingCartId", target = "shoppingCart.carId")
	public ShoppingProduct toShoppingProduct(ShoppingProductDTO shoppingProductDTO);

	public List<ShoppingProductDTO> toListShoppingProductDTO(List<ShoppingProduct> shoppingProducts);

	public List<ShoppingProduct> toListShoppingProduct(List<ShoppingProductDTO> shoppingProductDTOs);
}
