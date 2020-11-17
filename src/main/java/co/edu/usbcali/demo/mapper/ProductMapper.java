package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.dto.ProductDTO;

@Mapper
public interface ProductMapper {
	
	public ProductDTO toProductDTO(Product product);
	
	public Product toProduct(ProductDTO porductDTO);
	
	//Trae una lista de productos y me devuelve una lista de ProductosDTO
	public List<ProductDTO> toProductsDTO(List<Product> products);
	
	//Trae una lista de ProductDTO y me devuleve una lista de productos
	public List<Product> toProducts(List<ProductDTO> productsDTO); 
}
