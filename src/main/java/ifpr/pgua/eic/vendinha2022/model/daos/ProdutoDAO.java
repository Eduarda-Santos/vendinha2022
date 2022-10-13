package ifpr.pgua.eic.vendinha2022.model.daos;

import java.util.List;

import ifpr.pgua.eic.vendinha2022.model.entities.Produto;
import ifpr.pgua.eic.vendinha2022.model.results.Result;


public class ProdutoDAO {

    public Result create(Produto produto);
    Result update(int id,Produto produto);
    public List<Produto> listAll();
    Produto getById(int id);
    Result delete(int id);

}
