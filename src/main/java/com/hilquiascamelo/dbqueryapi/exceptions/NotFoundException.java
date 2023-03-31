package com.hilquiascamelo.dbqueryapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends Exception {
    public NotFoundException ( String s , Integer id ) {
        super("ID do cargo não encontrado: " + id);
        throw new ResponseStatusException( HttpStatus.INTERNAL_SERVER_ERROR,  s + id);
    }

    public NotFoundException ( String erro,String email  ) {
        super("E-MAIL do cargo não encontrado: " + email);
        throw new ResponseStatusException( HttpStatus.INTERNAL_SERVER_ERROR, erro + email);
    }
}