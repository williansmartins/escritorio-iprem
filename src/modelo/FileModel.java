package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Willians Martins
 */
@Table(name = "file")
@SuppressWarnings("serial")
@Entity
public class FileModel implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	private String titulo;
	private String descricao;
	private String url;
	
	public FileModel() {
		super();
	}
	
	public FileModel(Long id, String titulo, String descricao, String url) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
