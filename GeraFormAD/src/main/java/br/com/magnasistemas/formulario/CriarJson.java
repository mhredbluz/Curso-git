package br.com.magnasistemas.formulario;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import br.com.magnasistemas.model.Entrydata;
import br.com.magnasistemas.model.Funcionario;
import br.com.magnasistemas.model.Principal;
import br.com.magnasistemas.model.Viewentry;

public class CriarJson {
	
	public List<Funcionario> consomeJson (BufferedReader br){
		
			Gson gson = new Gson();

			Principal principal = gson.fromJson(br, Principal.class);
			
			Set<Viewentry> funcionarios = principal.getViewentry();
			List<Funcionario> listaFuncionarios = new ArrayList<>();
			
		
		for (Viewentry funcionario : funcionarios) {
			List<Entrydata> entrydatas = funcionario.getEntrydata();
			
			String apelido = entrydatas.get(0).getText().getDado();
			String nome = entrydatas.get(1).getText().getDado();
			String celular = entrydatas.get(2).getText().getDado();
			String ramal = entrydatas.get(3).getText().getDado();
			String superior = entrydatas.get(4).getText().getDado();
			String email = entrydatas.get(5).getText().getDado();
			String cargo = entrydatas.get(6).getText().getDado();
			String matricula = entrydatas.get(7).getText().getDado();
			String departamento = entrydatas.get(8).getText().getDado();
			
			Funcionario func = new Funcionario();
			
			func.setApelido(apelido);
			func.setNome(nome);
			func.setCelular(celular);
			func.setRamal(ramal);
			func.setSuperior(superior);
			func.setEmail(email);
			func.setCargo(cargo);
			func.setMatricula(matricula);
			func.setDepartamento(departamento);
			
			listaFuncionarios.add(func);
			}
		
		return listaFuncionarios;
	}
}
