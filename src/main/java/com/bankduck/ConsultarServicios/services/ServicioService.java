package com.bankduck.ConsultarServicios.services;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    ServicioRepository servicioRepository;
    @Autowired
    ServicioResponseMapper servicioResponseMapper;

    @Autowired
    RequisitosServicioRepository requisitosServicioRepository;
    @Autowired
    ServicioRequestMapper servicioRequestMapper;
    @Autowired
    RequisitosServicioResponseMapper requisitosServicioResponseMapper;

    @Autowired
    ServicioItemResponseMapper servicioItemResponseMapper;

    public List<ServicioItemResponse> ObtenerTodos (){
        List<Servicio> findAll = servicioRepository.findAll();
        List<ServicioItemResponse> servicioListToServicioItemResponseList = servicioItemResponseMapper.ServicioListToServicioItemResponseList(findAll);
        return servicioListToServicioItemResponseList;
    }
    public List<RequisitosServicioResponse> listarRequisitosServicio(Long id){
        Optional<Servicio> findById = servicioRepository.findById(id);
        List<RequisitosServicioResponse> requisitosServicioResponses = new ArrayList<>();
        if (findById.isPresent()) {
            List<RequisitosServicio> findRequisitosServicioById
                    = requisitosServicioRepository.findByServicioId(Long.valueOf(id));
            requisitosServicioResponses
                    = requisitosServicioResponseMapper
                    .RequisitosServicioListToRequisitosServicioResponseList(findRequisitosServicioById);
        }
        return requisitosServicioResponses;
    }
    public ServicioResponse GuardarServicio(ServicioRequest servicioRequest){
        Servicio ServicioRequestToServicio = servicioRequestMapper.ServicioRequestToServicio(servicioRequest);
        Servicio save = servicioRepository.save(ServicioRequestToServicio);
        ServicioResponse ServicioToServicioResponse = servicioResponseMapper.ServicioToServicioResponse(save);
        return ServicioToServicioResponse;
    }
}
