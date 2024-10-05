package br.com.aline.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.aline.erp.model.Empresa;
import br.com.aline.erp.model.RamoAtividade;
import br.com.aline.erp.model.TipoEmpresa;
import br.com.aline.erp.repository.Empresas;
import br.com.aline.erp.repository.RamoAtividades;
import br.com.aline.erp.service.CadastroEmpresaService;
import br.com.aline.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	private List<Empresa> listaEmpresas;
	@Inject
	private FacesMessages messages;
	@Inject
	private RamoAtividades ramoAtividades;
	private String termoPesquisa;
	private Converter ramoAtividadesConverter;
	private Empresa empresa;
	@Inject
	private CadastroEmpresaService cadastro;
	
	public void preparaNovaEmpresa() {
		empresa = new Empresa();
	}
	
	public void preparaEdicao() {
		ramoAtividadesConverter = new RamoAtividadesConverter(Arrays.asList(empresa.getRamoAtividade()));
	}
	
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}
	
	public void todasEmpresas() {
		listaEmpresas = empresas.todas();
	}
	
	public void pesquisar() {
		listaEmpresas = empresas.pesquisar(termoPesquisa);
		
		if(listaEmpresas.isEmpty()) {
			messages.info("A sua consulta não retornou registros");
		}
	}
	
	public void salvar() {
		cadastro.salvar(empresa);
		
		atualizarRegistros();
		
		messages.info("Empresa salva com sucesso!");
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:messages","frm:empresasDataTable"));
	}
	
	public void excluir() {
		cadastro.excluir(empresa);
		
		empresa = null;
		
		atualizarRegistros();
		
		messages.info("Empresa excluída com sucesso!");
	}

	private void atualizarRegistros() {
		if(jaHouvePesquisa()) {
			pesquisar();
		} else {
			todasEmpresas();
		}
	}

	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public List<RamoAtividade> completarRamoAtividade(String termo) {
		List<RamoAtividade> list = ramoAtividades.pesquisar(termo);
		ramoAtividadesConverter = new RamoAtividadesConverter(list);
		return list;
	}

	public Converter getRamoAtividadesConverter() {
		return ramoAtividadesConverter;
	}
	
	public boolean isEmpresaSelecionada() {
		return empresa != null;
	}
}
