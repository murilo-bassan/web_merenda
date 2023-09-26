package merenda.com.demo.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, message = "O nome da categoria deve ter no mínimo 3 caracteres")
	private String nome;
	
	@NotNull
	@ManyToOne
	private Tipo_merenda tipo_merenda;
	
	@OneToMany (mappedBy = "categoria")
	private List<Item> itens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Tipo_merenda getTipo_merenda() {
		return tipo_merenda;
	}

	public void setTipo_merenda(Tipo_merenda tipo_merenda) {
		this.tipo_merenda = tipo_merenda;
	}	
	
	
}

