package br.com.aline.erp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name="empresa")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name="nome_fantasia", nullable=false, length=80)
	private String nomeFantasia;
	
	@NotEmpty
	@Column(name="razao_social",nullable=false,length=120 )
	private String razaoSocial;
	
	@CNPJ
	@NotNull
	@Column(nullable=false,length=18)
	private String cnpj;
	
	@Past
	@NotNull
	@Column(name="data_fundacao", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataFundacao;
	
	@NotNull
	@Column(nullable=false, length=30)
	@Enumerated(EnumType.STRING)
	private TipoEmpresa tipo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="ramo_atividade_id", nullable=false) //se não colocar essa anotação o hibernate cria a coluna com o nome do atributo + _id
	private RamoAtividade ramoAtividade;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Date getDataFundacao() {
		return dataFundacao;
	}
	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	public TipoEmpresa getTipo() {
		return tipo;
	}
	public void setTipo(TipoEmpresa tipo) {
		this.tipo = tipo;
	}
	public RamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Empresa [id=" + id + "]";
	}
	
}
