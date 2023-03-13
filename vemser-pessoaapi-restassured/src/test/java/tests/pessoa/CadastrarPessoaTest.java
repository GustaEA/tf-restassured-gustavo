package tests.pessoa;

import dataFactory.PessoaDataFactory;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import model.JSONFailResponse;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.PessoaService;
import tests.base.BaseTest;

public class CadastrarPessoaTest extends BaseTest {
    private static PessoaService pessoaService = new PessoaService();

    @Test
    @DisplayName("Deve cadastrar usuário com sucesso")
    public void testCadastrarUserSucesso(){
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        PessoaModel pessoaCadastrada = pessoaService.cadastrarPessoa(pessoaValida)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PessoaModel.class);

        Assertions.assertNotNull(pessoaCadastrada.getIdPessoa());
        Assertions.assertEquals(pessoaValida.getNome(), pessoaCadastrada.getNome());
        Assertions.assertEquals(pessoaValida.getDataNascimento(), pessoaCadastrada.getDataNascimento());
        Assertions.assertEquals(pessoaValida.getCpf(), pessoaCadastrada.getCpf());
    }

    @Test
    public void testCadastrarUserComNomeEmBranco(){
        PessoaModel pessoaComNomeEmBranco = PessoaDataFactory.pessoaComNomeEmBranco();

        JSONFailResponse response = pessoaService.cadastrarPessoa(pessoaComNomeEmBranco)
                .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                                    .as(JSONFailResponse.class);

        Assertions.assertEquals("nome: must not be blank", response.getErrors().get(0));
    }
}
