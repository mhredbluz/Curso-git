package br.com.magnasistemas.soap;

import java.util.List;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.magnasistemas.model.Client;
import br.com.magnasistemas.service.ClienteService;

@WebService
public class ClientResourceSOAPService {
	//instanciamos a classe ClienteService para usar seu metodo
	@Inject
	ClienteService clientService;
	//E criamos o método obtemTodosClientes para puxar nosso serviço
	
	@WebMethod
	public List<Client> obtemTodosClientes(){
		return clientService.findAll();
	}

}