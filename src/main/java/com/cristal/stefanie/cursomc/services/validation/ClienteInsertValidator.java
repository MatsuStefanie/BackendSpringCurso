package com.cristal.stefanie.cursomc.services.validation;


import com.cristal.stefanie.cursomc.domain.Cliente;
import com.cristal.stefanie.cursomc.domain.enuns.TipoCliente;
import com.cristal.stefanie.cursomc.dto.ClienteNewDTO;
import com.cristal.stefanie.cursomc.repositores.ClienteRepository;
import com.cristal.stefanie.cursomc.resources.exception.FieldMessage;
import com.cristal.stefanie.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objNewDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

       if(objNewDto.getTipo()== TipoCliente.PESSOAFISICA.getCod() && !BR.isValidCPF(objNewDto.getCpfOuCnpj())){
           list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
       }if(objNewDto.getTipo()== TipoCliente.PESSOAJURIDICA.getCod() && !BR.isValidCNPJ(objNewDto.getCpfOuCnpj())){
           list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
       }
       Cliente aux =  clienteRepository.findByEmail(objNewDto.getEmail());
       if(aux!=null){
           list.add(new FieldMessage("email", "Email já existente"));
       }
       aux = clienteRepository.findByCpfOuCnpj(objNewDto.getCpfOuCnpj());
       if(aux!=null){
           list.add(new FieldMessage("cpfOuCnpj","CPF ou CNPJ já cadastrado"));
       }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}