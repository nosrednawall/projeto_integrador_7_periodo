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

import org.iel.code_sismatic.control.RelatorioFuncionamentoMaquina;
import org.iel.code_sismatic.control.SalvaDadosBI;
import org.iel.code_sismatic.dao.DadosMaquinaDao;
import org.iel.code_sismatic.dao.FuncionamentoMaquinaDao;
import org.iel.code_sismatic.dao.StatusMaquinaDao;
import org.iel.code_sismatic.model.entidades_dimensao.FuncionamentoMaquina;
import org.iel.code_sismatic.model.entidades_dimensao.StatusMaquina;
import org.iel.code_sismatic.model.entidades_fato.DadosMaquina;
import org.iel.code_sismatic.rest.objetos_envio.RelatorioFuncionamentoMaquinaEnvio;
import org.iel.code_sismatic.rest.objetos_envio.StatusMaquinaEnvio;
import org.iel.code_sismatic.util.Util;

/**
 * @author Anderson
 */
@Stateless
@Path("/dados-maquina")
@Consumes("application/json")
@Produces("application/json")
public class DadosMaquinaEndpoint {

	/**
	 * Injetado pelo CDI
	 */
	// dao da tabela fato
	@Inject
	private DadosMaquinaDao dao;

	@Inject
	private StatusMaquinaDao daoStatusMaquina;

	// dao das tabelas de dimensão
	@Inject
	private SalvaDadosBI daoBi;

	@Inject
	private RelatorioFuncionamentoMaquina relatorioFuncionamento;

	@Inject
	private FuncionamentoMaquinaDao daoFuncionamentoMaquina;

	@POST
	public Response create(DadosMaquina entity) {
		// seto a data que chegou a informação
		entity.setData(LocalDateTime.now());

		// salvo os dados na tabela fato
		dao.save(entity);

		// salvos os dados nas tabelas de dimensão
		daoBi.salvaDadosMaquinaEmTabelasBI(entity);

		return Response.created(
				UriBuilder.fromResource(DadosMaquinaEndpoint.class).path(String.valueOf(entity.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") Long id) {
		DadosMaquina entity = dao.find(DadosMaquina.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.ok(entity).build();
		}
	}

	@GET
	public List<DadosMaquina> listAll(@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		final List<DadosMaquina> results = dao.listAll(startPosition, maxResult);
		return results;
	}

	@GET
	@Path("/status")
	public StatusMaquinaEnvio listaDadosMaquinaParou(@QueryParam("data_inicial") String dataInicial,
			@QueryParam("data_limite") String dataLimite) {

		// instancio a coleção
		final List<StatusMaquina> results = new ArrayList<StatusMaquina>();

		// aplico o patter as datas
		dataInicial = Util.adicionaPattermDataInicial(dataInicial);
		dataLimite = Util.adicionaPattermDataFinal(dataLimite);

		// verifico se as queryparam não estão nulas
		if (!Util.isNullOrBlank(dataInicial) && !Util.isNullOrBlank(dataLimite)) {

			// faço a solicitação dos dadoss
			results.addAll(daoStatusMaquina.listarDadosComDataInicialELimite(dataInicial, dataLimite));

			// caso só tenha a data inicial
		} else if (!Util.isNullOrBlank(dataInicial)) {

			results.addAll(daoStatusMaquina.listarDadosApartirDataInicial(dataInicial));

		}
		StatusMaquinaEnvio retorno = new StatusMaquinaEnvio(Util.converteStringEmData(dataInicial),
				Util.converteStringEmData(dataLimite), results);

		return retorno;
	}

	/**
	 * Método retorna a porcentagem de vezes que a maquina ficou ligada no
	 * automático e no manual dentro do escopo de tempo soliciatado, caso não
	 * forneça as datas será efetuado o relatório com a data do dia
	 * 
	 * @param dataInicial
	 * @param dataLimite
	 * @return
	 */
	@GET
	@Path("/funcionamento/porcentagem")
	public RelatorioFuncionamentoMaquinaEnvio retornaRelatorioFuncionamentoMaquina(
			@QueryParam("data_inicial") String dataInicial, @QueryParam("data_limite") String dataLimite) {
//		para fazer um teste rápido
//		http://localhost:8080/code-simatic/rest/dados-maquina/funcionamento/porcentagem?data_inicial=2019-01-01&&data_limite=2019-12-31

		// instancio a coleção
		final RelatorioFuncionamentoMaquinaEnvio results;

		// verifico se as queryparam não estão nulas
		if (Util.isNullOrBlank(dataInicial) && Util.isNullOrBlank(dataLimite)) {
			dataInicial = Util.dataHojeFormatoAmericano();
			dataLimite = Util.dataHojeFormatoAmericano();
		}
		results = relatorioFuncionamento.getRelatorio(dataInicial, dataLimite);
		return results;
	}

	@GET
	@Path("/funcionamento")
	public List<FuncionamentoMaquina> listAllFuncionamento(@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		final List<FuncionamentoMaquina> results = daoFuncionamentoMaquina.listAll(startPosition, maxResult);
		return results;
	}
}
