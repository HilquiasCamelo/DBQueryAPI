package com.hilquiascamelo.dbqueryapi.service;

import com.hilquiascamelo.dbqueryapi.dto.EscolaridadeDto;
import com.hilquiascamelo.dbqueryapi.entity.Escolaridade;
import com.hilquiascamelo.dbqueryapi.exceptions.NotFoundException;
import com.hilquiascamelo.dbqueryapi.repository.EscolaridadeRepository;
import com.hilquiascamelo.dbqueryapi.service.util.JpaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EscolaridadeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EscolaridadeService.class);
    private final EscolaridadeRepository escolaridadeRepository;
    private final JpaUtils jpaUtils;

    public EscolaridadeService(EscolaridadeRepository escolaridadeRepository, JpaUtils jpaUtils) {
        this.escolaridadeRepository = escolaridadeRepository;
        this.jpaUtils = jpaUtils;
    }

    public List < Escolaridade > saveAll( List<EscolaridadeDto> escolaridadeDTOList) {
        try {
            return escolaridadeRepository.saveAll(escolaridadeDTOList);
        } catch (Exception e) {
            LOGGER.error("Error saving escolaridade list: {}", e.getMessage());
            throw new RuntimeException("Error saving escolaridade list");
        }
    }

    public Optional<Page<Escolaridade>> getAll(Pageable pageable) {
        try {
            return Optional.of(escolaridadeRepository.getAll(pageable));
        } catch (Exception e) {
            LOGGER.error("Error getting all escolaridade: {}", e.getMessage());
            throw new RuntimeException("Error getting all escolaridade");
        }
    }

    public Optional<Escolaridade> findByIdEscolaridade(Long id) throws NotFoundException {
        try {
            jpaUtils.isReferencedOrExistsById(Collections.singletonList("Escolaridade"), "id_escolaridade", id.intValue());
            return Optional.ofNullable( escolaridadeRepository.findByIdEscolaridade( id ) );
        } catch (Exception e) {
            LOGGER.error("Error finding escolaridade by id {}: {}", id, e.getMessage());
            throw new NotFoundException("Error finding escolaridade by id ", id.intValue());
        }
    }

    public Optional<EscolaridadeDto> updateEscolaridade(EscolaridadeDto escolaridadeDto, Long id) throws NotFoundException {
        try {
            jpaUtils.isReferencedOrExistsById(Collections.singletonList("Escolaridade"), "id_escolaridade", id.intValue());
            return Optional.ofNullable( escolaridadeRepository.updateEscolaridade( escolaridadeDto , id ) );
        } catch (Exception e) {
            LOGGER.error("Error updating escolaridade by id {}: {}", id, e.getMessage());
            throw new NotFoundException("Error updating escolaridade by id ", id.intValue());
        }
    }

    public Optional<Integer> delete(Long id) throws NotFoundException {
        try {
            jpaUtils.isReferencedOrExistsById(Collections.singletonList("Escolaridade"), "id_escolaridade", id.intValue());
            return Optional.of(escolaridadeRepository.deleteById(id));
        } catch (Exception e) {
            LOGGER.error("Error deleting escolaridade by id {}: {}", id, e.getMessage());
            throw new NotFoundException("Error deleting escolaridade by id ", id.intValue());
        }
    }

    private EscolaridadeDto mapEntityToDTO( EscolaridadeDto escolaridade) {
        return new EscolaridadeDto(escolaridade.getId(), escolaridade.getDescricao(), escolaridade.getNivel() , escolaridade.getAtivo());
    }
}
