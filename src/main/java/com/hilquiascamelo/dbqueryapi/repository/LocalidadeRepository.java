package com.hilquiascamelo.dbqueryapi.repository;

import com.hilquiascamelo.dbqueryapi.entity.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadeRepository extends JpaRepository < Localidade, Long >, JpaSpecificationExecutor < Localidade > {
}