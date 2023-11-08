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
	
	private float estrelasBebida;
	
	private float estrelasCarboidrato;
	
	private float estrelasFruta;
	
	private float mediaEstrelas;

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

	public float getEstrelasBebida() {
		return estrelasBebida;
	}

	public void setEstrelasBebida(float estrelasBebida) {
		this.estrelasBebida = estrelasBebida;
	}

	public float getEstrelasCarboidrato() {
		return estrelasCarboidrato;
	}

	public void setEstrelasCarboidrato(float estrelasCarboidrato) {
		this.estrelasCarboidrato = estrelasCarboidrato;
	}

	public float getEstrelasFruta() {
		return estrelasFruta;
	}

	public void setEstrelasFruta(float estrelaFruta) {
		this.estrelasFruta = estrelaFruta;
	}

	public float getMediaEstrelas() {
		int divisor = 0;
		float dividendo = 0;
		
		
		if(this.getEstrelasBebida() > 0) {
			divisor++;
		}
		
		if(this.getEstrelasCarboidrato() > 0) {
			divisor++;		
		}
		
		if(this.getEstrelasFruta() > 0) {
			divisor++;
		}
		
		
		dividendo = dividendo + (this.getEstrelasBebida() + this.getEstrelasCarboidrato() + this.getEstrelasFruta());
		
		if(divisor > 0) {
			mediaEstrelas = dividendo/divisor;
		}
		
		else {
			mediaEstrelas = 0;
		}
		
		return mediaEstrelas;
	}

	public void setMediaEstrelas(float mediaEstrelas) {
		this.mediaEstrelas = mediaEstrelas;
	}

}
