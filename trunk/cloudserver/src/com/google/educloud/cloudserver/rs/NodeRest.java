package com.google.educloud.cloudserver.rs;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.educloud.api.entities.Node;
import com.google.educloud.api.entities.VirtualMachine;
import com.google.educloud.cloudserver.database.dao.NodeDao;
import com.google.educloud.cloudserver.database.dao.VirtualMachineDao;
import com.google.gson.Gson;
import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/node")
public class NodeRest {
	
	private static Gson gson = new Gson();

	private static Logger LOG = Logger.getLogger(NodeRest.class);
	
	/**
	 * this method will retrive all registered nodes
	 *
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/describeNodes")
	public Response describeNodes() {

		LOG.debug("Application will list all nodes");

		//Recupera a lista de nodos da base de dados.
		List<com.google.educloud.internal.entities.Node> listaNodos =
			NodeDao.getInstance().getAll();

		//Array para retorno
		Node[] nodes = new Node[listaNodos.size()];

		//Para controle do indice do array
		int indice = 0;

		//Coloca a lista interna no array de m�quinas externas
		for( com.google.educloud.internal.entities.Node nodoInterno : listaNodos )
		{
			Node nodoRetorno = new Node();
			nodoRetorno.setId(nodoInterno.getId());
			//nodoRetorno.setIp(nodoInterno.getIp);
			nodoRetorno.setPort(nodoInterno.getPort());
			//nodoRetorno.setTemplateDir(nodoInterno.getTemplateDir);
			nodes[indice] = nodoRetorno;

			indice++;
		}

		//Retorna o array de nodos.
		return Response.ok(gson.toJson(nodes), MediaType.APPLICATION_JSON).build();
	}
}
