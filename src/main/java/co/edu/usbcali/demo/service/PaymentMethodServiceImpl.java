package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.repository.PaymentMethodRepository;

@Service
@Scope("singleton")
public class PaymentMethodServiceImpl implements PaymentMethodService {
	
	@Autowired
	PaymentMethodRepository paymentmethodRespository;

	@Override
	@Transactional(readOnly = true)
	public List<PaymentMethod> findAll() {
		return paymentmethodRespository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PaymentMethod> findById(Integer id) throws Exception {
		return paymentmethodRespository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return paymentmethodRespository.count();
	}

	@Override
	public void validate(PaymentMethod entity) throws Exception {
		
		//Validaciones
		if(entity==null) {
			throw new Exception("El PaymentMethod es nulo");
		}
		
		if(entity.getEnable()==null || entity.getEnable().isBlank()==true) {
			throw new Exception("el enable es nulo");
		}
		
		if(entity.getName()==null || entity.getName().isBlank()==true) {
			throw new Exception("el Name es nulo");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PaymentMethod save(PaymentMethod entity) throws Exception {
		
		validate(entity);
		
		if(entity==null) {
			throw new Exception("El PaymentMethod es nulo");
		}
		
		
		return paymentmethodRespository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PaymentMethod update(PaymentMethod entity) throws Exception {
		
		validate(entity);
		
		if(entity==null) {
			throw new Exception("El PaymentMethod es nulo");
		}
		
		return paymentmethodRespository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(PaymentMethod entity) throws Exception {
		
		if(entity==null) {
			throw new Exception("El paymentmethod es nulo");
		}
		
		if(entity.getPayId()==null) {
			throw new Exception("El paymentmethod es obligatorio");
		}
		
		//Si no existe lanza error
		if(paymentmethodRespository.existsById(entity.getPayId())==false) {
			throw new Exception("el PymenMethod con PayId: "+entity.getPayId()+" No existe, no se puede borrar");
		}
		
		paymentmethodRespository.deleteById(entity.getPayId());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		
		if(id==null || id <0) {
			throw new Exception("El PayId es obligatorio");
		}
		
		if(paymentmethodRespository.existsById(id)) {
			delete(paymentmethodRespository.findById(id).get());
		}
	}

}
