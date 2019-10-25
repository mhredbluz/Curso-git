package br.com.magnasistemas.GeraFormAD.view;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ProgressBarView implements Serializable {
     
    private Integer progresso;
 
    public Integer getProgresso() {
        if(progresso == null) {
            progresso = 0;
        }
        else {
            progresso += (int)(Math.random() * 35);
             
            if(progresso > 100)
                progresso = 100;
        }
         
        return progresso;
    }
 
    public void setProgresso(Integer progresso) {
        this.progresso = progresso;
    }
     
    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Formulario Gerados com sucesso!"));
    }
     
    public void cancela() {
        progresso = null;
    }
}