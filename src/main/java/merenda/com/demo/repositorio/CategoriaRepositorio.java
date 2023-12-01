package merenda.com.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import merenda.com.demo.modelo.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
 
	
}