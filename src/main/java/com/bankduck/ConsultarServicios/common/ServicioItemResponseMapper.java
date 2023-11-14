package com.bankduck.ConsultarServicios.common;

import com.bankduck.ConsultarServicios.dto.ServicioItemResponse;
import com.bankduck.ConsultarServicios.dto.ServicioResponse;
import com.bankduck.ConsultarServicios.entities.Servicio;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ServicioItemResponseMapper {

    ServicioItemResponse ServicioToServicioItemResponse(Servicio source);

    List<ServicioItemResponse> ServicioListToServicioItemResponseList(List<Servicio> source);
}
