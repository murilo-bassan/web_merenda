package merenda.com.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O feedback deve ser informado")
	@Size(min = 3, message ="O feedback deve conter pelo menos 3 caracteres")
	private String descricaoFeedback;
	
	@NotBlank(message = "O nome do aluno deve ser informado")
	@Size(min = 3, message ="O nome do aluno deve conter pelo menos 3 caracteres")
	private String nomeAluno;
	
	@NotBlank(message = "A quantidade de estrelas deve ser avaliada")
	@Size(max = 5, message ="O nome do aluno deve conter pelo menos 3 caracteres")
	private int estrelas;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricaoFeedback() {
		return descricaoFeedback;
	}

	public void setDescricaoFeedback(String descricaoFeedback) {
		this.descricaoFeedback = descricaoFeedback;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

}
