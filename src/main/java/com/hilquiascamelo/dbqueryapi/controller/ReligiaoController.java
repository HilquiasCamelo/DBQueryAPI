package com.hilquiascamelo.dbqueryapi.controller;

import com.hilquiascamelo.dbqueryapi.dto.ReligiaoDto;
import com.hilquiascamelo.dbqueryapi.exceptions.ResourceNotFoundException;
import com.hilquiascamelo.dbqueryapi.service.ReligiaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/religiao")

public class ReligiaoController {

    private static final Logger LOGGER = LoggerFactory.getLogger( ReligiaoController.class );
    private final ReligiaoService religiaoService;

    public ReligiaoController(ReligiaoService religiaoService) {
        this.religiaoService = religiaoService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated ReligiaoDto religiaoDto) {
        religiaoService.save(religiaoDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReligiaoDto> findById(@PathVariable("id") Long id) {
        ReligiaoDto religiao = religiaoService.findById(id);
        return ResponseEntity.ok(religiao);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
//        religiaoService.findById(id)
//                .orElseThrow(() -> {
//                    LOGGER.error("Unable to delete non-existent data!");
//                    return new ResourceNotFoundException();
//                });
//        religiaoService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }


    @GetMapping("/page-query")
    public ResponseEntity<Page<ReligiaoDto>> pageQuery(ReligiaoDto religiaoDto,
                                                       @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC)
                                                       Pageable pageable) {
        Page<ReligiaoDto> religiaoPage = religiaoService.findByCondition(religiaoDto, pageable);
        return ResponseEntity.ok(religiaoPage);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> update(@RequestBody @Validated ReligiaoDto religiaoDto,
//                                       @PathVariable("id") Long id) {
//        religiaoService.update(religiaoDto, id);
//        return ResponseEntity.ok().build();
//    }
}
