package merenda.com.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import merenda.com.demo.modelo.Agenda;

public interface AgendaRepositorio extends JpaRepository<Agenda, Long> {
    
}
