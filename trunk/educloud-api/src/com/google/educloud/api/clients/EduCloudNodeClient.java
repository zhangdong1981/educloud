package com.google.educloud.api.clients;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.google.educloud.api.entities.Node;
import com.google.educloud.api.entities.exceptions.EduCloudServerException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class EduCloudNodeClient extends AbstractClient{
	
	//Recupera todos os nodos registrados.
	public List<Node> decribeNodes() throws EduCloudServerException{		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());

		ClientResponse response = service.path("node").path("describeNodes").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		int status = response.getStatus();
		String entity = response.getEntity(String.class);

		handleError(status, entity);

		response.close();
		
		//Recupera o array de retorno.
		Node[] cloudNodes =
			gson.fromJson(entity, Node[].class);		
		
		//Gera a lista de retorno. 
		List<Node> listaCloudNodes = 
			new ArrayList<Node>();
		
		for( Node vm : cloudNodes )
		{
			listaCloudNodes.add(vm);			
		}

		return listaCloudNodes;
	}

}
