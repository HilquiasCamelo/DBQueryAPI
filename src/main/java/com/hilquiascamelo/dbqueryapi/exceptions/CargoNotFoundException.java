package com.hilquiascamelo.dbqueryapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CargoNotFoundException extends Exception {
    public CargoNotFoundException ( String s , Integer id ) {
        super("ID do cargo não encontrado: " + id);
        throw new ResponseStatusException( HttpStatus.INTERNAL_SERVER_ERROR,  s + id);
    }
}