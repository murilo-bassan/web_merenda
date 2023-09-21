package merenda.com.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NoticiaCreate {

	@NotNull
	@Size(min = 3, message = "O título da notícia deve ter no mínimo 3 caracteres")
	private String titulo;
	@NotNull
	@Size(min = 3, message = "O subtítulo da notícia deve ter no mínimo 3 caracteres")
	private String subtitulo;
	@Size(min = 3, message = "O nome do autor deve ter no mínimo 3 caracteres")
	private String autor;
	@Size(min = 50, message = "O corpo da noticia deve ter no mínimo 50 caracteres")
	@Column(length = 10000)
	private String corpo;

	private MultipartFile[] fotos;

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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public MultipartFile[] getFotos() {
		return fotos;
	}

	public void setFotos(MultipartFile[] fotos) {
		this.fotos = fotos;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
}
