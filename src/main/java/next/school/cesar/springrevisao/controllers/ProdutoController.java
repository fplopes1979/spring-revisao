package next.school.cesar.springrevisao.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import next.school.cesar.springrevisao.entities.Produto;
import next.school.cesar.springrevisao.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getProdutos() {
        return new ResponseEntity<List<Produto>>(produtoRepository.findAll(), HttpStatus.OK);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {

        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            
            return new ResponseEntity<Produto>(produtoOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Produto> addProduto(@RequestBody Produto produto) {
        produtoRepository.save(produto);
        return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);

    }

     @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto updatedProduto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            Produto existingProduto = produtoOptional.get();
            existingProduto.setNome(updatedProduto.getNome());
            existingProduto.setPreco(updatedProduto.getPreco());
            existingProduto.setQuantidade(existingProduto.getQuantidade());
            existingProduto.setCategoria(existingProduto.getCategoria());
            produtoRepository.save(existingProduto);
            return new ResponseEntity<>(existingProduto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            produtoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
