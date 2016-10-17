package com.amanda.bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import com.amanda.dao.ServicoDAO;
import com.amanda.domain.Servico;
import com.amanda.domain.ServicoFilter;




@SuppressWarnings("serial")
@Named
@ViewScoped
public class ServicoBean implements Serializable {
	
	private Servico servico;
	private List<Servico> servicos;
	private ServicoFilter filtro;
	private BigDecimal somarTotal;
	

	@Inject
	private ServicoDAO servicoDAO;
	
	
	@PostConstruct
	public void init(){
		
		servico = new Servico();
		
	}
	
	
	public void salvar(){
		
		try{			
			servicoDAO.salvar(servico);
						
			Messages.addGlobalInfo("Salvo com sucesso!");
		}catch(RuntimeException e){
			Messages.addGlobalError("Erro ao salvar este cadastro");
			e.printStackTrace();
		}
		
		
	}
	
	public void listar(){
		
		servicos = servicoDAO.filtrar(filtro);
		somarTotal = servicoDAO.somarTotal(filtro);
		
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}


	public List<Servico> getServicos() {
		return servicos;
	}


	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}


	public ServicoFilter getFiltro() {if(filtro == null){
		filtro = new ServicoFilter();
	}
		return filtro;
	}


	public void setFiltro(ServicoFilter filtro) {
		this.filtro = filtro;
	}


	public BigDecimal getSomarTotal() {
		return somarTotal;
	}


	public void setSomarTotal(BigDecimal somarTotal) {
		this.somarTotal = somarTotal;
	}
	
	
	
	
	
	

}
