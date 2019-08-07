package br.com.magnasistemas.service;

import java.util.ArrayList;
import java.util.List;
import br.com.magnasistemas.model.Endereco;
import br.com.magnasistemas.model.Fornecedores;

public class FornecedoresService {
	
	public List<Fornecedores> findAll(){
	
		List<Fornecedores> listaFornec = new ArrayList<Fornecedores>();
		
		Endereco end1 = new Endereco();
		end1.setBairro("Jardins");
		end1.setComplemento("Bloco C");
		end1.setLongradouro("Rua dos pinheiros");
		end1.setNumero("485");
		
		Fornecedores fornec1 = new Fornecedores();
		fornec1.setNome("MAC Sombreadores e toldos");
		fornec1.setCnpj("12302914660");
		fornec1.setTelefone("98462-2119");
		fornec1.setEndereco(end1);
	
		listaFornec.add(fornec1);
		
		return listaFornec;
	
	}
}
