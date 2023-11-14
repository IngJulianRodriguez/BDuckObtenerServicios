package com.bankduck.ConsultarServicios.controller;

import com.bankduck.ConsultarServicios.common.RequisitosServicioResponseMapper;
import com.bankduck.ConsultarServicios.common.ServicioItemResponseMapper;
import com.bankduck.ConsultarServicios.common.ServicioRequestMapper;
import com.bankduck.ConsultarServicios.common.ServicioResponseMapper;
import com.bankduck.ConsultarServicios.dto.RequisitosServicioResponse;
import com.bankduck.ConsultarServicios.dto.ServicioItemResponse;
import com.bankduck.ConsultarServicios.dto.ServicioRequest;
import com.bankduck.ConsultarServicios.dto.ServicioResponse;
import com.bankduck.ConsultarServicios.entities.RequisitosServicio;
import com.bankduck.ConsultarServicios.entities.Servicio;
import com.bankduck.ConsultarServicios.repository.RequisitosServicioRepository;
import com.bankduck.ConsultarServicios.repository.ServicioRepository;
import com.bankduck.ConsultarServicios.services.ServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.server.ResponseStatusException;

@Api(tags = "Servicios")
@RestController
@RequestMapping("/servicios")
public class ServicioRestController {

    @Autowired
    ServicioRepository servicioRepository;

    @Autowired
    RequisitosServicioRepository requisitosServicioRepository;

    @Autowired
    ServicioResponseMapper servicioResponseMapper;

    @Autowired
    ServicioRequestMapper servicioRequestMapper;

    @Autowired
    RequisitosServicioResponseMapper requisitosServicioResponseMapper;

    @Autowired
    ServicioItemResponseMapper servicioItemResponseMapper;

    @Autowired
    ServicioService servicioService;

    @ApiOperation(value = "Retorna todos los servicios disponibles", notes = "Retorna 204 sino hay datos")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping()
    public List<ServicioItemResponse> list() {
        List<Servicio> findAll = servicioRepository.findAll();
        List<ServicioItemResponse> servicioListToServicioItemResponseList = servicioItemResponseMapper.ServicioListToServicioItemResponseList(findAll);
        return servicioListToServicioItemResponseList;
    }

    @GetMapping("/requisitos/{id}")
    public List<RequisitosServicioResponse> get(@PathVariable String id) {
        Long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido: " + id);
        }
        List<RequisitosServicioResponse> requisitosServicioResponses = servicioService.listarRequisitosServicio(longId);
        if(requisitosServicioResponses.isEmpty() ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado con ID: " + id);
        }else{
            return requisitosServicioResponses;
        }

    }


    @PostMapping
    public ResponseEntity<?> post(@RequestBody ServicioRequest input) {
        ServicioResponse servicioResponse = servicioService.GuardarServicio(input);
        return ResponseEntity.ok(servicioResponse);

    }

}
