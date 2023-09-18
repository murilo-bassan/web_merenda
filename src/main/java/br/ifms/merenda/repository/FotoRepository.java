package br.ifms.merenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifms.merenda.model.Foto;

public interface FotoRepository extends JpaRepository<Foto, Long> {

}
