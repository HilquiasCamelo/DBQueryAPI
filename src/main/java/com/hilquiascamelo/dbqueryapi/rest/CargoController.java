package com.hilquiascamelo.dbqueryapi.rest;

import com.hilquiascamelo.dbqueryapi.entity.Cargo;
import com.hilquiascamelo.dbqueryapi.exceptions.CargoNotFoundException;
import com.hilquiascamelo.dbqueryapi.exceptions.CargoReferencedException;
import com.hilquiascamelo.dbqueryapi.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping ("/api")
public class CargoController {

    @Autowired private CargoService cargoService;

    @PostMapping ("/cargo") @ResponseBody public ResponseEntity < List < Cargo > > saveAll (
            @RequestBody List < Cargo > cargos ) {
        List < Cargo > savedCargos = cargoService.saveCargo( cargos );
        return ResponseEntity.ok( savedCargos );
    }

    @GetMapping ("/cargo")
    @ResponseBody
    public ResponseEntity<Page<Cargo>> getAll(Pageable pageable) {
        Page <Cargo> pageOfCargos = cargoService.getCargoList(pageable );
        return ResponseEntity.ok().body(pageOfCargos);
    }

    @GetMapping("/cargo/{id}")
    @ResponseBody
    public ResponseEntity<Object> getById(@PathVariable int id) {
        try {
            Cargo cargo = cargoService.getCargo(id);
            return ResponseEntity.ok().body(cargo);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping ("/cargo/{id}") @ResponseBody public ResponseEntity < Cargo > putAll (
            @RequestBody Cargo cargo , @PathVariable Integer id ) throws CargoNotFoundException {
        Cargo updatedCargo = cargoService.putCargo( cargo , id );
        return ResponseEntity.ok( )
                .body( updatedCargo );

    }

    @DeleteMapping ("/cargo/{id}") @ResponseBody public ResponseEntity < String > deleteCargo ( @PathVariable int id ) {
        try {
            cargoService.deleteCargo( id );
            return ResponseEntity.ok( "Cargo deleted successfully" );
        } catch( CargoReferencedException e ) {
            return ResponseEntity.status( HttpStatus.CONFLICT )
                    .body( "Não é possível excluir o cargo porque ele é referenciado em outras tabelas." );
        } catch( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body( e.getMessage( ) );
        }
    }

}
