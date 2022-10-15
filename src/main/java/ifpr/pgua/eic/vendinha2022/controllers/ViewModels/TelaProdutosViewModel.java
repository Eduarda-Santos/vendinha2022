package ifpr.pgua.eic.vendinha2022.controllers.ViewModels;

import com.mysql.cj.conf.BooleanProperty;

import ifpr.pgua.eic.vendinha2022.model.entities.Produto;
import ifpr.pgua.eic.vendinha2022.model.repositories.GerenciadorLoja;
import ifpr.pgua.eic.vendinha2022.model.repositories.ProdutosRepository;
import ifpr.pgua.eic.vendinha2022.model.results.Result;
import ifpr.pgua.eic.vendinha2022.model.results.SuccessResult;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaProdutosViewModel {
    
    private StringProperty nomeProperty = new SimpleStringProperty();
    private StringProperty descricaoProperty = new SimpleStringProperty();
    private StringProperty valorProperty = new SimpleStringProperty("0.0");
    private StringProperty quantidadeEstoqueProperty = new SimpleStringProperty("0.0");

    
    private StringProperty operacao = new SimpleStringProperty("Cadastrar");
    private BooleanProperty podeEditar = new SimpleBooleanProperty(true);
    private boolean atualizar = false;

    private ObservableList<ProdutoRow> obsProdutos = FXCollections.observableArrayList();

    private ObjectProperty<ProdutoRow> selecionado = new SimpleObjectProperty<>();

    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

    private ProdutosRepository repository;

    public TelaProdutosViewModel(ProdutosRepository repository){
        this.repository = repository;

        updateList();
    }

    private void updateList(){
        obsProdutos.clear();
        for (Produto c : repository.getprodutos()) {
            obsProdutos.add(new ProdutoRow(c));
        }
    }

    public ObservableList<ProdutoRow> getProdutos() {
        return this.obsProdutos;
    }

    public ObjectProperty<Result> alertProperty() {
        return alertProperty;
    }

    public StringProperty operacaoProperty() {
        return operacao;
    }

    public BooleanProperty podeEditarProperty() {
        return podeEditar;
    }

    public StringProperty nomeProperty() {
        return this.spNome;
    }

    public StringProperty descricaoProperty() {
        return this.spDescricao;
    }

    public StringProperty valorProperty() {
        //var double, funcao String
        return this.spValor;
    }

    public StringProperty quantidadeEstoqueProperty() {
        return this.spQuantidade;
    }

    public ObjectProperty<ProdutoRow> selecionadoProperty() {
        return selecionado;
    }

    
    public void cadastrar() {

        // acessa os valores das propriedades, que por consequência
        // contém os valores digitados nos textfields.
        String nome = spNome.getValue();
        String descricao = spDescricao.getValue();
        Double valor = spValor.getValue();
        Double quantidadeEstoque = spQuantidade.getValue();

        if (atualizar) {
            repository.atualizarProduto(nome, descricao, valor, quantidadeEstoque);
        } else {
            repository.adicionarProduto(nome, descricao, valor, quantidadeEstoque);
        }

        updateList();
        limpar();
    }

    public void atualizar() {
        operacao.setValue("Atualizar");
        podeEditar.setValue(false);
        atualizar = true;
        Produto produto = selecionado.get().getProduto();
        spNome.setValue(produto.getNome());
        spDescricao.setValue(produto.getDescricao());
        spValor.setValue(produto.getValor()());
        spQuantidade.setValue(produto.getQuantidadeEstoque());

        alertProperty.setValue(Result.fail("Teste"));

    }

    public void limpar(){
        spNome.setValue("");
        spDescricao.setValue("");
        spValor.setValue("0.0");
        spQuantidade.setValue("0.0");
        podeEditar.setValue(true);
        atualizar = false;
        operacao.setValue("Cadastrar");
    }


    


}
