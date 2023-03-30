package com.hilquiascamelo.dbqueryapi.service;

import com.hilquiascamelo.dbqueryapi.entity.Cargo;
import com.hilquiascamelo.dbqueryapi.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CargoService {
    public CargoService ( CargoRepository cargoRepository ) {
        this.cargoRepository = cargoRepository;
    }

    @Autowired CargoRepository cargoRepository;

    public List < Cargo > saveCargo ( List < Cargo > cargos ) {
        return cargoRepository.saveAll ( cargos );
    }

    public List < Cargo > getCargoList ( ) {
        return cargoRepository.getCargoList ( );

    }

    public Cargo  putCargo ( Cargo cargos ,  Integer id  ) {
        return cargoRepository.putCargo(cargos, id);
    }

    public Integer deleteCargo ( Integer id ) throws IllegalArgumentException {
      return cargoRepository.deleteCargo(id);
    }
}
