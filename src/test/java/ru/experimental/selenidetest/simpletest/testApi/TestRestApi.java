package ru.experimental.selenidetest.simpletest.testApi;
import io.restassured.RestAssured;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.experimental.selenidetest.simpletest.TestBase;
import unirest.shaded.com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;


public class TestRestApi extends TestBase {

    private static final String URL_KEY = "https://numbersapi.p.rapidapi.com"; //
    private static final String x_rapidapi_key = "6cad337ed5msh7b87fd9d62c0ddbp1e0d2fjsn9aebde718718";
    private static final String x_rapidapi_host = "numbersapi.p.rapidapi.com";


    @Test
    @Tag("skipBeforeEach")
    @Tag("api")
    public void test1() {

        String type = "trivia";
        int min = 10;
        int max = 100;

        RestAssured.baseURI = URL_KEY;
        // игнорировать проверку SSL сертификата
        RestAssured.useRelaxedHTTPSValidation();

        given()
                .header("x-rapidapi-host", x_rapidapi_host)
                .header("x-rapidapi-key", x_rapidapi_key);

        when()
                .get(EndpointURL.RANDOM.addPath(String.format("/%s?json=true&fragment=true&max=%s&min=%s",type, max, min)))
                .then()
                    .statusCode(401);
                    //.body("type", hasItem("trivia"));

    }

    @Test
    @Tag("skipBeforeEach")
    @Tag("api")
    public void test2() {
        // простой запрос с Unirest и проверкой кода ответа
        HttpResponse<String> response = Unirest.get("https://numbersapi.p.rapidapi.com/random/trivia?json=true&fragment=false&max=20&min=10")
                .header("x-rapidapi-host", "numbersapi.p.rapidapi.com")
                .header("x-rapidapi-key", "6cad337ed5msh7b87fd9d62c0ddbp1e0d2fjsn9aebde718718")
                .header("cache-control", "no-cache")
                .asString();
        System.out.println("HttpResponse: " + response.getStatusText() + " " + response.getBody());
        assertEquals("OK", response.getStatusText() );
    }

    @Test
    @Tag("skipBeforeEach")
    @Tag("api")
    void testGetPetById(){
        Unirest.config().defaultBaseUrl("https://petstore.swagger.io/v2").verifySsl(false);
        String petId = "9223372036854057000";

        Map r = Unirest.get("/pet/" + petId ).asObject(i -> new Gson().fromJson(i.getContentReader(), HashMap.class))
                .getBody();

        // из json ответа получаем map и выводим его
        System.out.println(" ***** TEST ***** \n");
        Set<String> keys = r.keySet();
        System.out.println("Ключи: " + keys);

        ArrayList<String> values = new ArrayList<>(r.values());
        System.out.println("Значения: " + values);
        System.out.println(" ***** TEST ***** \n");

        //assertEquals(r.get("id").toString(), petId, "Compare id value responce and request");

        HttpResponse<String> response = Unirest.get("/pet/" + petId).asString();

        System.out.println("TEST: " + response.getStatus() + " " + response.getStatusText());
        System.out.println("TEST: " + response.getBody() );

    }

}
