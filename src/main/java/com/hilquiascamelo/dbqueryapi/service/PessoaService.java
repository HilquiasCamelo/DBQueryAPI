package com.hilquiascamelo.dbqueryapi.service;

import com.hilquiascamelo.dbqueryapi.entity.Pessoa;
import com.hilquiascamelo.dbqueryapi.entity.dto.PessoaFromDTO;
import com.hilquiascamelo.dbqueryapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaService ( PessoaRepository pessoaRepository ) {
        this.pessoaRepository = pessoaRepository;
    }
    public List < Pessoa > findAllPessoas ( ) {
        return pessoaRepository.findAllByOrderByNomeCompletoAsc ( );
    }


    public List<Pessoa> addPessoa ( PessoaFromDTO pessoaFromDTO ) {
        List<PessoaFromDTO> pessoaList = new ArrayList <> ();
        pessoaList.add(pessoaFromDTO);
        return pessoaRepository.saveAllAndOrderByNomeCompletoAsc(pessoaList);
    }
}
