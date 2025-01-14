package tests.pessoa;

import dataFactory.PessoaDataFactory;
import model.JSONFailResponseWithoutArray;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.PessoaService;
import tests.base.BaseTest;

public class DeletarPessoaTest extends BaseTest {
    private static PessoaService pessoaService = new PessoaService();
    @Test
    public void testDeletarPessoa(){
        PessoaModel novaPessoa = PessoaDataFactory.pessoaValida();
        PessoaModel pessoaCadastrada = pessoaService.cadastrarPessoa(novaPessoa)
        .then()
                .extract()
                            .as(PessoaModel.class);

        pessoaService.deletarPessoa(pessoaCadastrada.getIdPessoa())
        .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testDeletarPessoaFalha(){
        Integer idPessoa = PessoaDataFactory.idPessoaInvalido();
        JSONFailResponseWithoutArray response =
        pessoaService.deletarPessoa(idPessoa)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailResponseWithoutArray.class);

        Assertions.assertEquals("ID da pessoa nao encontrada", response.getMessage());
    }
}
