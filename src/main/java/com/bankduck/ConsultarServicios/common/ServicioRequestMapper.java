package com.bankduck.ConsultarServicios.common;

import com.bankduck.ConsultarServicios.dto.ServicioRequest;
import com.bankduck.ConsultarServicios.entities.Servicio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicioRequestMapper {

    Servicio ServicioRequestToServicio(ServicioRequest source);

    List<Servicio> ServicioRequestListToServicioList(List<ServicioRequest> source);
}
