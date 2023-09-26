package merenda.com.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import merenda.com.demo.modelo.Item;

public interface ItemRepositorio extends JpaRepository<Item, Long> {

}
