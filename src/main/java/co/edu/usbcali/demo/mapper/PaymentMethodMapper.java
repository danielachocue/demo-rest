package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.dto.PaymentMethodDTO;

@Mapper
public interface PaymentMethodMapper {
	
	public PaymentMethodDTO toPaymenMethodDTO(PaymentMethod paymenMethod);
	
	public PaymentMethod toPaymenMethod(PaymentMethodDTO paymenMethodDTO);
	
	//Trae una lista de paymentMethods y me devuelve una lista de paymentMethodsDTO
	public List<PaymentMethodDTO> toPaymenMethodsDTO(List<PaymentMethod> paymenMethods);
	
	//Trae una lista de paymentMethodsDTO y me devuelve una lista de paymentMethod
	public List<PaymentMethod> toPaymentMethods(List<PaymentMethodDTO> paymentMethodsDTO);

}
