package br.com.magnasistemas.GeraFormAD.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.magnasistemas.formulario.Poi;

@ManagedBean
@ViewScoped
public class FormBean implements Serializable{
	
	
	private Integer processo;
	//valor entre 0 e 100 no processo
	private String mensagem;
	//Mensagem de formularios sendo processados
	private Integer quantidadeForms;
	//Quantidade de formularios sendo processados
	private List<String> formularios;
	//Temporario - até implementar o código
	
	
	//Valor padrão de quantidade de formularios
	@PostConstruct
	public void init() {
		formularios = new ArrayList<>();
		quantidadeForms = 100;
	}
	
	//Esse metodo cria uma mensagem que será exibida atraves do p:messages
	private void criarMensagem(String texto) {
		FacesMessage msg = new FacesMessage(texto);
		FacesContext.getCurrentInstance().addMessage("", msg);
	}
	
	public void gerarForms() throws IOException {
		System.out.println("Teste");
		new Poi().gerarPoi();
		
	}
	
//	//Esse metodo reseta a mensagem e o processo
//	private void resetarProgresso() {
//		processo = 0;
//		mensagem = "";
//	}
//	
//	// Para atualizar todos os processos
//	private void atualizarProcesso(int i) {
//		processo = (i * 100) / quantidadeForms;
//		try {
//			Thread.sleep(200);
//		} catch(Exception ex){
//			criarMensagem("Erro ");
//		}
//	}
	

	public Integer getProcesso() {
		if(processo == null) {
			processo = 0;
		}
		return processo;
	}
	public void setProcesso(Integer processo) {
		this.processo = processo;
	}
	public String getMensagem() {
		if(mensagem == null) {
			mensagem = "";
		}
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Integer getQuantidadeForms() {
		return quantidadeForms;
	}
	public void setQuantidadeForms(Integer quantidadeForms) {
		this.quantidadeForms = quantidadeForms;
	}
	public List<String> getFormularios(){
		return formularios;
	}
	public void setFormularios(List<String>formularios) {
		this.formularios = formularios;
	}
}
