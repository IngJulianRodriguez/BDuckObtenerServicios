package com.bankduck.ConsultarServicios;

import com.bankduck.ConsultarServicios.common.RequisitosServicioResponseMapper;
import com.bankduck.ConsultarServicios.controller.ServicioRestController;
import com.bankduck.ConsultarServicios.dto.RequisitosServicioResponse;
import com.bankduck.ConsultarServicios.entities.RequisitosServicio;
import com.bankduck.ConsultarServicios.entities.Servicio;
import com.bankduck.ConsultarServicios.repository.RequisitosServicioRepository;
import com.bankduck.ConsultarServicios.repository.ServicioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@WebMvcTest(ServicioRestController.class)
@AutoConfigureMockMvc
class ConsultarServiciosApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ServicioRepository servicioRepository;

	@MockBean
	private RequisitosServicioRepository requisitosServicioRepository;

	@MockBean
	private RequisitosServicioResponseMapper requisitosServicioResponseMapper;

	private static final String PASSWORD = "admin";
	private static final String USER = "admin";


	@Test
	public void testListServiciosVacia() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String encoding = encoder.encodeToString((USER + ":" + PASSWORD).getBytes());
		mockMvc.perform(MockMvcRequestBuilders.get("/servicios")
						.header("Authorization", "Basic " + encoding))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
	}
	@Test
	public void testListServiciosNoVacia() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String encoding = encoder.encodeToString((USER + ":" + PASSWORD).getBytes());
		Servicio servicio1 = new Servicio();
		Servicio servicio2 = new Servicio();
		List<Servicio> mockServicios=Arrays.asList(servicio1,servicio2);
		Mockito.when(servicioRepository.findAll()).thenReturn(mockServicios);

		mockMvc.perform(MockMvcRequestBuilders.get("/servicios")
						.header("Authorization", "Basic " + encoding))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
	}
	@Test
	public void testListContenidoServicios() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String encoding = encoder.encodeToString((USER + ":" + PASSWORD).getBytes());
		Servicio servicio = new Servicio();
		servicio.setNombre("Cuenta de ahorros");
		servicio.setId(2548975);
		servicio.setDescripcion("Cuenta de ahorros");
		List<Servicio> mockServicios=Arrays.asList(servicio);
		Mockito.when(servicioRepository.findAll()).thenReturn(mockServicios);

		mockMvc.perform(MockMvcRequestBuilders.get("/servicios")
				.header("Authorization", "Basic " + encoding))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].descripcion").value("Cuenta de ahorros"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2548975))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Cuenta de ahorros"));

	}
	@Test
	public void testRequisitosServicioEncontrados() throws Exception {

		Base64.Encoder encoder = Base64.getEncoder();
		String encoding = encoder.encodeToString((USER + ":" + PASSWORD).getBytes());

		String servicioId = "1";
		Servicio servicio = new Servicio();
		RequisitosServicio requisito1 = new RequisitosServicio();
		RequisitosServicio requisito2 = new RequisitosServicio();

		Mockito.when(servicioRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(servicio));
		Mockito.when(requisitosServicioRepository.findByServicioId(Mockito.anyLong())).thenReturn(Arrays.asList(requisito1, requisito2));
		Mockito.when(requisitosServicioResponseMapper.RequisitosServicioListToRequisitosServicioResponseList(Mockito.anyList())).thenReturn(Arrays.asList(new RequisitosServicioResponse(), new RequisitosServicioResponse()));

		mockMvc.perform(MockMvcRequestBuilders.get("/servicios/requisitos/{id}", servicioId)
						.header("Authorization", "Basic " + encoding)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
	}

	@Test
	public void testServicioNoEncontrado() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String encoding = encoder.encodeToString((USER + ":" + PASSWORD).getBytes());
		mockMvc.perform(MockMvcRequestBuilders.get("/servicios/requisitos/999")
						.header("Authorization", "Basic " + encoding)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testIdInvalido() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String encoding = encoder.encodeToString((USER + ":" + PASSWORD).getBytes());
		mockMvc.perform(MockMvcRequestBuilders.get("/servicios/requisitos/invalido")
						.header("Authorization", "Basic " + encoding)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testSinAutorizacion() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/servicios/requisitos/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	public void testAccesoNoAutorizado() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String encodingParaUsuarioSinPermiso = encoder.encodeToString(("usuario" + ":" + "password").getBytes());
		mockMvc.perform(MockMvcRequestBuilders.get("/servicios/requisitos/1")
						.header("Authorization", "Basic " + encodingParaUsuarioSinPermiso)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
}
