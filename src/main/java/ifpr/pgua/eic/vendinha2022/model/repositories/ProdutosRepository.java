package ifpr.pgua.eic.vendinha2022.model.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ifpr.pgua.eic.vendinha2022.model.daos.ProdutoDAO;
import ifpr.pgua.eic.vendinha2022.model.entities.Produto;
import ifpr.pgua.eic.vendinha2022.model.results.Result;

public class ProdutosRepository {

    private List<Produto> produtos;
    private ProdutoDAO dao;

    public ProdutosRepository(ProdutoDAO dao){
        this.dao = dao;
    }

    public Result adicionarProduto(String nome, String descricao, double valor, double quantidadeEstoque){

        Optional<Produto> busca = produtos.stream().filter((cli)->cli.getNome().equals(nome)).findFirst();
        
        if(busca.isPresent()){
            return Result.fail("Produto já cadastrado!");
        }

        Produto Produto = new Produto(nome,descricao,valor,quantidadeEstoque);
        
        return dao.create(Produto);
            
    }

    public Result atualizarProduto(String nome, String novadescricao, double novovalor, double novaquantidadeEstoque){
        Optional<Produto> busca = produtos.stream().filter((cli)->cli.getNome().equals(nome)).findFirst();
        
        if(busca.isPresent()){
            Produto Produto = busca.get();
            Produto.setDescricao(novadescricao);
            Produto.setValor(novovalor);
            Produto.setQuantidadeEstoque(novaquantidadeEstoque);

            return Result.success("Produto atualizado com sucesso!");
        }
        return Result.fail("Produto não encontrado!");
    }

    public List<Produto> getprodutos(){
        produtos = dao.listAll();
        return Collections.unmodifiableList(produtos);
    }


}
