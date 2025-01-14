package utils;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class Autenticacao {

    static String baseUri = "http://vemser-dbc.dbccompany.com.br:39000/vemser/dbc-pessoa-api/auth";
    public static String token(){

        String token =
        given()
                .contentType(ContentType.JSON).body("""
                        {
                          "login": "admin",
                          "senha": "123"
                        }
                        """)
        .when()
                .post(baseUri)
        .then().extract().asString();
        return token;
    }
}
