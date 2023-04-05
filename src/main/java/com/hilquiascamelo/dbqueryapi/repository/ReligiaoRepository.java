package com.hilquiascamelo.dbqueryapi.repository;

import com.hilquiascamelo.dbqueryapi.entity.Religiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReligiaoRepository extends JpaRepository < Religiao, Long >, JpaSpecificationExecutor < Religiao > {
}