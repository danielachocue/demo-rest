package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.repository.ShoppingCartRepository;

@Service
@Scope("singleton")
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	ShoppingCartRepository shoppingcartRepository;

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingCart> findAll(){
		return shoppingcartRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ShoppingCart> findById(Integer id) throws Exception {
		return shoppingcartRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return shoppingcartRepository.count();
	}

	@Override
	public void validate(ShoppingCart entity) throws Exception {
		
		//validaciones
		if(entity==null) {
			throw new Exception("El shoppingcart es nulo");
		}
		
		if(entity.getCustomer()==null || entity.getCustomer() == null) {
			throw new Exception("El customer es nulo");
		}
	
		if(entity.getEnable()==null || entity.getEnable().isBlank()==true) {
			throw new Exception("El enable es nulo");
		}
		
		if(entity.getItems()==null || entity.getItems() < 0) {
			throw new Exception("El item es vacio");
		}
		
		if(entity.getPaymentMethod()==null || entity.getPaymentMethod()==null) {
			throw new Exception("El payMethod es vacio");
		}
		
		if(entity.getTotal()==null || entity.getTotal() < 0) {
			throw new Exception("El total es nulo");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingCart save(ShoppingCart entity) throws Exception {
		validate(entity);
		
		if(entity==null) {
			throw new Exception("El shoppingcart es nulo");
		}
		
		return shoppingcartRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingCart update(ShoppingCart entity) throws Exception {
		
		validate(entity);
		
		if(entity==null) {
			throw new Exception("El shoppingcart es nulo");
		}
		
		return shoppingcartRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(ShoppingCart entity) throws Exception {
		
		if(entity==null) {
			throw new Exception("El shoppingcart es nulo");
		}
		
		if(entity.getCarId()==null) {
			throw new Exception("El shoppingcart es obligatorio");
		}
		//si no existe lanza error
		if(shoppingcartRepository.existsById(entity.getCarId())==false) {
			throw new Exception("El shoppingCart con CarId: "+entity.getCarId()+" NO existe, no se puede borrar");
		}

		
		shoppingcartRepository.deleteById(entity.getCarId());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		
		//valida CarId
		if(id==null || id<0) {
			throw new Exception("El carid es obligatorio");
		}
		
		if(shoppingcartRepository.existsById(id)) {
			delete(shoppingcartRepository.findById(id).get());
		}
	}

}
