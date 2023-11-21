package merenda.com.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import merenda.com.demo.modelo.Cardapio;


public interface CardapioRepositorio extends JpaRepository<Cardapio, Long> {

}
