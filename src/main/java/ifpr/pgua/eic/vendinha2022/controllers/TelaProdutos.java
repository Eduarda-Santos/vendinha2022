package ifpr.pgua.eic.vendinha2022.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.vendinha2022.controllers.ViewModels.ProdutoRow;
import ifpr.pgua.eic.vendinha2022.controllers.ViewModels.TelaProdutosViewModel;
import ifpr.pgua.eic.vendinha2022.model.entities.Produto;
import ifpr.pgua.eic.vendinha2022.model.results.Result;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TelaProdutos extends BaseController implements Initializable {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TableColumn<ProdutoRow, String> tbcDescricao;

    @FXML
    private TableColumn<ProdutoRow, String> tbcNome;

    @FXML
    private TableColumn<ProdutoRow, String> tbcId;

    @FXML
    private TableColumn<ProdutoRow, Double> tbcValor;

    @FXML
    private TableColumn<ProdutoRow, Double> tbcQuantidade;

    @FXML
    private TableView<ProdutoRow> tbProdutos;

    @FXML
    private TextField tfValor;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfQuantidade;

    @FXML
    private Button btCadastrar;
    
    @FXML
    private Button btLimpar;


    private TelaProdutosViewModel viewModel;


    public TelaProdutos(TelaProdutosViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        

        //define quais serão as propriedades que servirão para preencher
        //o valor da coluna. Note que o nome da propriedade deve possuir
        //um get equivalente no modelo que representa a linha da tabela.
        tbcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tbcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));

        //liga o conjunto de itens da tabela com a lista de clientes do viewmodel
        tbProdutos.setItems(viewModel.getProdutos());

        //liga a propriedade selecionado do viewmodel com a tabela
        viewModel.selecionadoProperty().bind(tbProdutos.getSelectionModel().selectedItemProperty());

        viewModel.alertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            // TODO Auto-generated method stub
            showMessage(newVal);
        });

        //liga a propriedade texto do textfield nome com a propriedade do viewmodel
        tfNome.textProperty().bindBidirectional(viewModel.nomeProperty());
        //liga a propriedade editavel do textfield com a propriedade do viewmodel
        tfNome.editableProperty().bind(viewModel.podeEditarProperty());
        
        
        tfValor.textProperty().bindBidirectional(viewModel.valorProperty());
        tfValor.editableProperty().bind(viewModel.podeEditarProperty());

        tfDescricao.textProperty().bindBidirectional(viewModel.descricaoProperty());
        tfQuantidade.textProperty().bindBidirectional(viewModel.quantidadeEstoqueProperty());

        btCadastrar.textProperty().bind(viewModel.operacaoProperty());
    }

    @FXML
    private void cadastrar(){
        viewModel.cadastrar();
    }

    @FXML
    private void limpar(){
        viewModel.limpar();
    }

    @FXML
    private void atualizar(MouseEvent event){
        if(event.getClickCount() == 2){
            viewModel.atualizar();
        }
        
    }

}
