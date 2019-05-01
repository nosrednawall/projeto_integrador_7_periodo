package org.iel.code_sismatic.rest;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.iel.code_sismatic.control.SalvaDadosBI;
import org.iel.code_sismatic.dao.DadosMaquinaDao;
import org.iel.code_sismatic.dao.QtdaVezesMaquinaParouDao;
import org.iel.code_sismatic.model.DadosMaquina;

/**
 * 
 */
@Stateless
@Path("/dados-maquina")
public class DadosMaquinaEndpoint {

	/**
	 * Injetado pelo CDI
	 */
	//dao da tabela fato
	@Inject
	private DadosMaquinaDao dao;
	
	@Inject
	private QtdaVezesMaquinaParouDao daoQtdaVezesMaquinaParou;
	
	//dao das tabelas de dimensão
	@Inject
	private SalvaDadosBI daoBi;

	@POST
	@Consumes("application/json")
	public Response create(DadosMaquina entity) {
		//seto a data que chegou a informação
		entity.setDateTime(LocalDateTime.now());
		
		//salvo os dados na tabela fato
		dao.save(entity);
		
		//salvos os dados nas tabelas de dimensão
		daoBi.salvaDadosMaquinaEmTabelasBI(entity);
		
		return Response
				.created(UriBuilder.fromResource(DadosMaquinaEndpoint.class).path(String.valueOf(entity.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		DadosMaquina entity = dao.find(DadosMaquina.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.ok(entity).build();
		}
	}

	@GET
	@Produces("application/json")
	public List<DadosMaquina> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult) {
		final List<DadosMaquina> results = dao.listAll(startPosition, maxResult);
		return results;
	}
	
	@GET
	@Path("/maquina-parou")
	@Produces("application/json")
	public List<DadosMaquina> listaDadosMaquinaParou(
			@QueryParam("data_inicio") String dataInicio, 
			@QueryParam("data_fim") String dataFim) {
		final List<DadosMaquina> results = daoQtdaVezesMaquinaParou.listAll(dataInicio, dataFim);
		return results;
	}
}
