package service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.PessoaModel;
import utils.Autenticacao;

import static io.restassured.RestAssured.*;

public class PessoaService {
    public Response cadastrarPessoa(PessoaModel pessoaModel){
        Response response =
        given().header("Authorization", Autenticacao.token())
                .contentType(ContentType.JSON).body(pessoaModel)
        .when()
                .post("/pessoa");
        return response;
    }

    public Response buscarTodasPessoas(){
        Response response =
        given()
                .header("Authorization", Autenticacao.token())
        .when()
                .get("/pessoa");
        return response;
    }

    public Response buscarPessoaCpf(String cpf){
        Response response =
        given()
                .header("Authorization", Autenticacao.token())
                .pathParam("cpf", cpf)
        .when()
                .get("/pessoa/{cpf}/cpf");
        return response;
    }

    public Response buscarPessoaNome(String name){
        Response reponse =
        given()
                .header("Authorization", Autenticacao.token())
                .queryParam("nome", name)
        .when().get("/pessoa/byname");
        return reponse;
    }

    public Response atualizarPessoa(Integer idPessoa ,PessoaModel pessoaModel){
        Response response =
        given()
                .header("Authorization", Autenticacao.token())
                .contentType(ContentType.JSON)
                .pathParam("idPessoa", idPessoa)
                .body(pessoaModel)
        .when()
                .put("/pessoa/{idPessoa}");
        return response;
    }

    public Response deletarPessoa(Integer idPessoa) {

        Response response =
        given()
                .header("Authorization", Autenticacao.token())
                .pathParam("idPessoa", idPessoa)
        .when()
                .delete("/pessoa/{idPessoa}");

        return response;
    }
}
