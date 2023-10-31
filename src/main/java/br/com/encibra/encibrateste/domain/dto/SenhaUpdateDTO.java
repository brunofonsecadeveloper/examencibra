package br.com.encibra.encibrateste.domain.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SenhaUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@NotNull(message = "O campo 'id' precisa ser incluído.")
	private Integer id;
	
	@NotNull(message = "O campo 'descricao' precisa ser incluído.")
	@NotBlank(message = "O campo 'descricao' precisa ser preenchido.")
	private String descricao;
	
	@NotNull(message = "O campo 'tags' precisa ser incluído.")
	@NotBlank(message = "O campo 'tags' precisa ser preenchido.")
	private String tags;
	
	@NotNull(message = "O campo 'valor' precisa ser incluído.")
	@NotBlank(message = "O campo 'valor' precisa ser preenchido.")
	private String valor;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}

