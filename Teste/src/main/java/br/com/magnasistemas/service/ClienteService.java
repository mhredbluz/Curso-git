package br.com.magnasistemas.service;

import java.util.ArrayList;
import java.util.List;
import br.com.magnasistemas.model.Client;

public class ClienteService {
	
	//Criamos o método para encontrar todos os clientes. 
	public List<Client> findAll(){
		
		List<Client> listaClient = new ArrayList<Client>();
		
		//Criamos um cliente novo.
		Client cliente1 = new Client();
		cliente1.setNome("Marcos Henrique");
		cliente1.setCpf("000111222-66");
		cliente1.setIdade("20");
		cliente1.setTelefone("1199999-9999");
		cliente1.setEmail("Marcos@cliente.com.br");
		
		//Implementamos a Lista. 
		listaClient.add(cliente1);
		
		return listaClient;
	}
}
