package com.hilquiascamelo.dbqueryapi.rest;

import com.hilquiascamelo.dbqueryapi.entity.Pessoa;
import com.hilquiascamelo.dbqueryapi.entity.dto.PessoaFromDTO;
import com.hilquiascamelo.dbqueryapi.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (value="/api")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/pessoas")
    public List<Pessoa> getPessoas() {
        return pessoaService.findAllPessoas();
    }

    @PostMapping("/pessoas")
    public List<Pessoa> insertPessoa(PessoaFromDTO pessoaFromDTO) {
        return pessoaService.addPessoa(pessoaFromDTO);
    }
}