package tests.pessoa;

import dataFactory.PessoaDataFactory;
import io.restassured.internal.common.assertion.Assertion;
import model.JSONFailResponse;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.PessoaService;
import tests.base.BaseTest;

public class BuscarPessoaTest extends BaseTest {
    private static PessoaService pessoaService = new PessoaService();
    @Test
    @DisplayName("busca pessoa pelo cpf - sucesso")
    public void testBuscarPessoaPeloCpf(){
        String cpfPessoaFromAPI = PessoaDataFactory.cpfValidaFromAPI();
        PessoaModel pessoaEncontrada = pessoaService.buscarPessoaCpf(cpfPessoaFromAPI)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                                .as(PessoaModel.class);
        Assertions.assertEquals(cpfPessoaFromAPI, pessoaEncontrada.getCpf());
    }

    @Test
    @DisplayName("busca pessoa pelo cpf - falha")
    public void testBuscarPessoaPeloCpfFalha(){
        String cpfInvalido = PessoaDataFactory.cpfInvalido();
        JSONFailResponse response = pessoaService.buscarPessoaCpf(cpfInvalido)
                .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(JSONFailResponse.class);
    }

    @Test
    @DisplayName("busca pessoa pelo nome - sucesso")
    public void testBuscarPessoaPeloNome(){
        String nomePessoaFromAPI = PessoaDataFactory.nomeValidoFromAPI();
        PessoaModel[] pessoaEncontrada = pessoaService.buscarPessoaNome(nomePessoaFromAPI)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PessoaModel[].class);

        Assertions.assertEquals(nomePessoaFromAPI, pessoaEncontrada[0].getNome());
    }

    @Test
    @DisplayName("busca pessoa pelo nome - falha")
    public void testBuscarPessoaPeloNomeFalha(){
        String nome = PessoaDataFactory.nomeInvalido();
        JSONFailResponse response = pessoaService.buscarPessoaNome(nome)
                .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(JSONFailResponse.class);
    }
}
