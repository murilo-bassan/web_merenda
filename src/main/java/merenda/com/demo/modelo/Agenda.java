package merenda.com.demo.modelo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Agenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date dataInicio;
	
	private Date dataFim;
	
	@OneToMany(mappedBy = "agenda")
	private List<Cardapio> cardapios;
	
	@ManyToOne
	private Tipo_merenda tipo_merenda;
	
	
	public Date getData() {
		return dataInicio;
	}
	public void setData(Date data) {
		this.dataInicio = data;
	}
	public List<Cardapio> getCardapios() {
		return cardapios;
	}
	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}
	public Tipo_merenda getTipo_merenda() {
		return tipo_merenda;
	}
	public void setTipo_merenda(Tipo_merenda tipo_merenda) {
		this.tipo_merenda = tipo_merenda;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	 
	
	
}
