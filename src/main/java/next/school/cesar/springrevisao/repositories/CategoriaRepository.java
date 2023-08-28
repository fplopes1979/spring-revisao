package next.school.cesar.springrevisao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import next.school.cesar.springrevisao.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}


