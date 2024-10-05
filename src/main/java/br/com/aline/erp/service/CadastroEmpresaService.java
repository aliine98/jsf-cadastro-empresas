package br.com.aline.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.aline.erp.model.Empresa;
import br.com.aline.erp.repository.Empresas;
import br.com.aline.erp.util.Transacional;

public class CadastroEmpresaService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Empresas empresas;
	
	@Transacional
	public void salvar(Empresa e) {
		this.empresas.guardar(e);
	}
	
	@Transacional
	public void excluir(Empresa e) {
		this.empresas.remover(e);
	}
}
