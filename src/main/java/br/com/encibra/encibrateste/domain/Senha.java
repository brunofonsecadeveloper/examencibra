package br.com.encibra.encibrateste.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "senha")
public class Senha implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private String tags;
	private String valor;
	
	@NotNull(message = "A senha precisa estar diretamente vinculada a um usuário cadastrado (usuario não pode ser nulo).")
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	public Senha() {
	}

	public Senha(Integer id, String descricao, String tags, String valor, Usuario usuario) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.tags = tags;
		this.valor = valor;
		this.usuario = usuario;
	}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Senha other = (Senha) obj;
		return Objects.equals(id, other.id);
	}

}
