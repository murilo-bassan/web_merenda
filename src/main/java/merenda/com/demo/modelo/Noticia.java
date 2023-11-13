package merenda.com.demo.modelo;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Noticia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 3, message = "O título da notícia deve ter no mínimo 3 caracteres")
	private String titulo;
	@NotNull
	@Size(min = 3, message = "O subtítulo da notícia deve ter no mínimo 3 caracteres")
	private String subtitulo;
	@Size(min = 50, message = "O corpo da noticia deve ter no mínimo 50 caracteres")
	@Column(length = 10000)
	private String corpo;
	@Size(min = 3, message = "O nome do autor deve ter no mínimo 3 caracteres")
	private String autor;
	@OneToMany (mappedBy = "noticia", cascade = CascadeType.ALL)
	private List<Foto> fotos;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public List<Foto> getFotos() {
		return fotos;
	}
	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
}

