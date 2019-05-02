package org.iel.code_sismatic.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.iel.code_sismatic.model.QtdaVezesMaquinaParou;

import org.iel.code_sismatic.util.Util;

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
	public List<QtdaVezesMaquinaParou> listaDadosMaquinaParou(
			@QueryParam("data_inicial") String dataInicial, 
			@QueryParam("data_limite") String dataLimite) {
		
		//instancio a coleção
		final List<QtdaVezesMaquinaParou> results = new ArrayList<QtdaVezesMaquinaParou>();
		
		//verifico se as queryparam não estão nulas
		if(!Util.isNullOrBlank(dataInicial) && !Util.isNullOrBlank(dataLimite)) {
			
			//aplico o patter as datas
			dataInicial = Util.adicionaPattermData(dataInicial);
			dataLimite = Util.adicionaPattermData(dataLimite);
			
			//faço a solicitação dos dadoss
			results.addAll(daoQtdaVezesMaquinaParou.listarDadosComDataInicialELimite(dataInicial, dataLimite));
					
			//caso só tenha a data inicial
		}else if(!Util.isNullOrBlank(dataInicial)) {
			
			dataInicial = Util.adicionaPattermData(dataInicial);
			results.addAll(daoQtdaVezesMaquinaParou.listarDadosApartirDataInicial(dataInicial));
		}
		return results;
	}
	

}
