package co.edu.usbcali.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Entity
@Table(name = "shopping_product", schema = "public")
public class ShoppingProduct implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer shprId;
	//objeto para crear la relacion de muchos a uno
	private Product product;
	//objeto para crear la relacion de muchos a uno
	private ShoppingCart shoppingCart;
	
	private Integer quantity;
	
	private Long total;

	public ShoppingProduct() {
	}

	public ShoppingProduct(Integer shprId, Product product, Integer quantity, ShoppingCart shoppingCart, Long total) {
		this.shprId = shprId;
		this.product = product;
		this.shoppingCart = shoppingCart;
		this.quantity = quantity;
		this.total = total;
	}
//llave primaria
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shpr_id", unique = true, nullable = false)
	public Integer getShprId() {
		return this.shprId;
	}

	public void setShprId(Integer shprId) {
		this.shprId = shprId;
	}
//llave foranea de la tabla product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pro_id")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
//llave foranea de la tabla ShoppingCart
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id")
	public ShoppingCart getShoppingCart() {
		return this.shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "total", nullable = false)
	public Long getTotal() {
		return this.total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
