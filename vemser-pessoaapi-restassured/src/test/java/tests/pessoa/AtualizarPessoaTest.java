package tests.pessoa;

import dataFactory.PessoaDataFactory;
import model.JSONFailResponse;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.PessoaService;
import tests.base.BaseTest;

public class AtualizarPessoaTest extends BaseTest {
    private static PessoaService pessoaService = new PessoaService();
    @Test
    public void testAtualizarPessoa(){
        Integer idPessoa = PessoaDataFactory.idPessoaValidoFromAPI();
        PessoaModel pessoaNova = PessoaDataFactory.pessoaValida();
        PessoaModel pessoaEncontrada = pessoaService.atualizarPessoa(idPessoa, pessoaNova)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PessoaModel.class);
        Assertions.assertEquals(pessoaNova.getNome(), pessoaEncontrada.getNome());
    }

    @Test
    public void testAtualizarPessoaFalha(){
        Integer idPessoa = PessoaDataFactory.idPessoaValidoFromAPI();
        PessoaModel pessoaNova = PessoaDataFactory.pessoaComNomeEmBranco();
        JSONFailResponse response = pessoaService.atualizarPessoa(idPessoa, pessoaNova)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailResponse.class);

        Assertions.assertEquals("nome: must not be blank", response.getErrors().get(0));
    }
}
