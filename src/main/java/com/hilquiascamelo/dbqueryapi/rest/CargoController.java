package com.hilquiascamelo.dbqueryapi.rest;

import com.hilquiascamelo.dbqueryapi.entity.Cargo;
import com.hilquiascamelo.dbqueryapi.exceptions.CargoNotFoundException;
import com.hilquiascamelo.dbqueryapi.exceptions.CargoReferencedException;
import com.hilquiascamelo.dbqueryapi.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Controller
@RequestMapping ("/api")
public class CargoController {

    @Autowired private CargoService cargoService;

    @PostMapping ("/cargo")
    @ResponseBody
    public Response saveAll (@RequestBody List<Cargo> cargos) {
        List<Cargo> savedCargos = cargoService.saveCargo(cargos);
        return Response.status(Response.Status.OK).entity(savedCargos).build();
    }

    @GetMapping ("/cargo")
    @ResponseBody
    public Response getAll () {
        List<Cargo> listOfCargos = cargoService.getCargoList();
        return Response.status(Response.Status.OK).entity(listOfCargos).build();
    }

    @PutMapping ("/cargo")
    @ResponseBody
    public Response putAll (@RequestBody Cargo cargo, Integer id) {
        Cargo updatedCargo = cargoService.putCargo(cargo, id);
        return Response.status(Response.Status.OK).entity(updatedCargo).build();
    }

    @DeleteMapping ("/cargo/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteCargo(@PathVariable int id) {
        try {
            cargoService.deleteCargo(id);
            return ResponseEntity.ok("Cargo deleted successfully");
        } catch ( CargoReferencedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Não é possível excluir o cargo porque ele é referenciado em outras tabelas.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
