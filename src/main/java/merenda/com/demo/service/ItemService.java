package merenda.com.demo.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import merenda.com.demo.excecao.CategoriaNotFoundException;
import merenda.com.demo.modelo.Item;
import merenda.com.demo.repositorio.ItemRepositorio;


@Service
public class ItemService {
	
	@Autowired
	ItemRepositorio itemRepositorio;
	
	public Item buscarItemPorId(long id) throws CategoriaNotFoundException {
		Optional<Item> caixa = itemRepositorio.findById(id);
		if (caixa.isPresent()) {
			return caixa.get();
		} else {
			throw new CategoriaNotFoundException("Categoria" + "com id" + id + "nao existe");
		}
	}
}
