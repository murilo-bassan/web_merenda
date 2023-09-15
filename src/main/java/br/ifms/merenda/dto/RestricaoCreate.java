package br.ifms.merenda.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RestricaoCreate {
	
	@NotBlank(message = "A restricao deve ser informada")
	@Size(min = 3, message ="A restricao deve conter pelo menos 3 caracteres")
	private String descricaoRestricao;
	
	@NotBlank(message = "O nome do aluno deve ser informado")
	@Size(min = 3, message ="O nome do aluno deve conter pelo menos 3 caracteres")
	private String nomeAluno;

	private MultipartFile[] pdf;

	public String getDescricaoRestricao() {
		return descricaoRestricao;
	}

	public void setDescricaoRestricao(String descricaoRestricao) {
		this.descricaoRestricao = descricaoRestricao;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public MultipartFile[] getPdf() {
		return pdf;
	}

	public void setPdf(MultipartFile[] pdf) {
		this.pdf = pdf;
	}

}
