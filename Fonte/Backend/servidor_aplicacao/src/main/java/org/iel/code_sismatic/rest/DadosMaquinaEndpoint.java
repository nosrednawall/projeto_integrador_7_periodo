package org.iel.code_sismatic.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.iel.code_sismatic.dao.DadosMaquinaDao;
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
	@Inject
	private DadosMaquinaDao dao;

	@POST
	@Consumes("application/json")
	public Response create(DadosMaquina entity) {
		System.out.println(entity.toString());
		dao.save(entity);
		return Response
				.created(UriBuilder.fromResource(DadosMaquinaEndpoint.class).path(String.valueOf(entity.getId())).build())
				.build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		DadosMaquina entity = dao.find(DadosMaquina.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		dao.remove(entity);
		return Response.noContent().build();
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

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, DadosMaquina entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (dao.find(DadosMaquina.class, id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			dao.update(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}
