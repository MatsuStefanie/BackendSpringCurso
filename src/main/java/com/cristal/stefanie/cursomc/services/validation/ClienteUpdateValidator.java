package com.cristal.stefanie.cursomc.services.validation;


import com.cristal.stefanie.cursomc.domain.Cliente;
import com.cristal.stefanie.cursomc.dto.ClienteDTO;
import com.cristal.stefanie.cursomc.repositores.ClienteRepository;
import com.cristal.stefanie.cursomc.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.remote.server.HandlerMapper;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objNewDto, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = clienteRepository.findByEmail(objNewDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existente em outro cliente"));
        }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}