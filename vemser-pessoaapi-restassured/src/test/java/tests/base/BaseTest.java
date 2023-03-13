package tests.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {
    @BeforeAll
    public static void setUp(){
        //configurar os dados da pessoa PAI

        //http://vemser-dbc.dbccompany.com.br:39000/vemser/dbc-pessoa-api
        RestAssured.baseURI = "http://vemser-dbc.dbccompany.com.br:39000/vemser/dbc-pessoa-api";
        //RestAssured.baseURI = "http://vemser-dbc.dbccompany.com.br";
        //RestAssured.port = 39000;
        //RestAssured.basePath = "/vemser/dbc-pessoa-api";

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
