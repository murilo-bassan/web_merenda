package merenda.com.demo.modelo;

import java.util.Date;
import java.util.List;

public class Agenda {
	
	private Date data;
	
	
	private List<Cardapio> cardapios;
	
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public List<Cardapio> getCardapios() {
		return cardapios;
	}
	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}
	
}
