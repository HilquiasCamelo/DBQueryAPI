package com.hilquiascamelo.dbqueryapi.controller;

import com.hilquiascamelo.dbqueryapi.dto.EscolaridadeDto;
import com.hilquiascamelo.dbqueryapi.entity.Cargo;
import com.hilquiascamelo.dbqueryapi.entity.Escolaridade;
import com.hilquiascamelo.dbqueryapi.exceptions.NotFoundException;
import com.hilquiascamelo.dbqueryapi.exceptions.ResourceNotFoundException;
import com.hilquiascamelo.dbqueryapi.service.EscolaridadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/escolaridade")
public class EscolaridadeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EscolaridadeController.class);

    private final EscolaridadeService escolaridadeService;

    @Autowired
    public EscolaridadeController(EscolaridadeService escolaridadeService) {
        this.escolaridadeService = escolaridadeService;
    }

    @PostMapping ("/")
    @ResponseBody
    public ResponseEntity < List < Escolaridade > > saveAll (
            @RequestBody List < EscolaridadeDto > escolaridadeDtoList
    ) {
        List < Escolaridade > savedEscolaridade = escolaridadeService.saveAll( escolaridadeDtoList );
        return ResponseEntity.ok( savedEscolaridade );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escolaridade> findById(@PathVariable("id") Long id) throws NotFoundException {
        Optional<Escolaridade> escolaridadeOptional = escolaridadeService.findByIdEscolaridade(id);
        if (escolaridadeOptional.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(escolaridadeOptional.get());
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<Page<EscolaridadeDto>> getAll(Pageable pageable) {
        Page<Escolaridade> pageOfEscolaridade = escolaridadeService.getAll(pageable).orElse(Page.empty());
            Page<EscolaridadeDto> pageOfEscolaridadeDto = pageOfEscolaridade.map(EscolaridadeDto::fromEntity);
        return ResponseEntity.ok().body(pageOfEscolaridadeDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) throws NotFoundException {
        escolaridadeService.delete(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEscolaridade(@Validated @RequestBody EscolaridadeDto escolaridadeDto,
                                       @PathVariable("id") Long id) throws NotFoundException {
        escolaridadeService.updateEscolaridade(escolaridadeDto, id);
        return ResponseEntity.ok().build();
    }
}