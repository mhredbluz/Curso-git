package br.com.magnasistemas.soap;

import java.util.List;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.magnasistemas.model.Fornecedores;
import br.com.magnasistemas.service.FornecedoresService;;

@WebService
public class FornecedoresResourceSOAPService {
	
	@Inject
	FornecedoresService fornecedoresService;
	@WebMethod
	public List<Fornecedores> obtemTodosFornecedores(){
		return fornecedoresService.findAll();
	}

	
}
