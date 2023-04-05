package com.hilquiascamelo.dbqueryapi.repository;

import com.hilquiascamelo.dbqueryapi.entity.Quadro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuadroRepository extends JpaRepository < Quadro, Long >, JpaSpecificationExecutor < Quadro > {
}