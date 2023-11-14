package com.bankduck.ConsultarServicios.common;


import com.bankduck.ConsultarServicios.dto.ServicioResponse;
import com.bankduck.ConsultarServicios.entities.Servicio;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ServicioResponseMapper {

    ServicioResponse ServicioToServicioResponse(Servicio source);

    List<ServicioResponse> ServicioListToServicioResponseList(List<Servicio> source);
}
