package merenda.com.demo.modelo;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataFim;
	
	@OneToMany(mappedBy = "agenda")
	private List<Cardapio> cardapios;
	
	@ManyToOne
	private Tipo_merenda tipo_merenda;
	
	
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
	public String dataIFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
		String dataFormatada = sdf.format(this.dataInicio);
		return dataFormatada;
	}
	
}
