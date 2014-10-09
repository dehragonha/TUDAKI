package br.com.agilles.tudaki.views;

import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Classe que irá validar se o usuário digitou um valor correto para todos os
 * campos que contenham formato de moeda
 *
 * @author Agilles
 */
@FacesValidator("MoedaValidator")
public class MoedaValidator implements Validator {

    /**
     * Método responsável por validar os campos com moeda. Caso ocorra algum
     * erro lança uma ValidatorException.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        BigDecimal valor = (BigDecimal) value;

        if (value == null) {
            return;
        }
        if (valor.intValue() == 0) {
            FacesMessage message = new FacesMessage("Preencha com um valor válido maior que zero");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

    }

}
