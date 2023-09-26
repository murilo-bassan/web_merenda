package merenda.com.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import merenda.com.demo.modelo.Tipo_merenda;

public interface Tipo_merendaRepositorio extends JpaRepository<Tipo_merenda, Long> {

}

