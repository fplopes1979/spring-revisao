package next.school.cesar.springrevisao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import next.school.cesar.springrevisao.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
