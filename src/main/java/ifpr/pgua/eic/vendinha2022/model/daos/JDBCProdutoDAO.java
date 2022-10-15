package ifpr.pgua.eic.vendinha2022.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.pgua.eic.vendinha2022.model.FabricaConexoes;
import ifpr.pgua.eic.vendinha2022.model.entities.Produto;
import ifpr.pgua.eic.vendinha2022.model.results.Result;

public class JDBCProdutoDAO implements ProdutoDAO{

    private FabricaConexoes fabricaConexoes;

    public JDBCProdutoDAO(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }


    @Override
    public Result create(Produto produto) {
        try{
            //criando uma conexão
            Connection con = fabricaConexoes.getConnection();

            //preparando o comando sql
            PreparedStatement pstm = con.prepareStatement("INSERT INTO produtos(nome,descricao,valor,quantidadeEstoque) VALUES (?,?,?,?)");
            
            //ajustando os parâmetros do comando
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setDouble(3, produto.getValor());
            pstm.setDouble(4, produto.getQuantidadeEstoque());

            pstm.execute();

            pstm.close();
            con.close();
            return Result.success("Produto criado com sucesso!");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return Result.fail(e.getMessage());
        }

    }

    @Override
    public Result update(int id, Produto produto) {
        return null;
    }

    @Override
    public List<Produto> listAll() {
        ArrayList<Produto> produtos = new ArrayList<>();
        try{
            //criando uma conexão
            Connection con = fabricaConexoes.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM produtos");

            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                double quantidadeEstoque = rs.getDouble("quantidadeEstoque");

                Produto c = new Produto(id,nome, descricao, valor, quantidadeEstoque);
                produtos.add(c);
            }
            return produtos;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Produto getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result delete(int id) {
        // TODO Auto-generated method stub
        return null;
    }
    



}
