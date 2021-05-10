package ru.experimental.selenidetest.simpletest.testApi;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import ru.experimental.selenidetest.simpletest.TestBase;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;


public class TestRestApi extends TestBase {

    private static final String URL_KEY = "https://numbersapi.p.rapidapi.com"; //
    private static final String x_rapidapi_key = "6cad337ed5msh7b87fd9d62c0ddbp1e0d2fjsn9aebde718718";
    private static final String x_rapidapi_host = "numbersapi.p.rapidapi.com";


    @Test
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
                .get(EndpointURL.RANDOM.addPath(String.format("/%s?json=true&fragment=true&max=%s&min=5s",type, max, min)))
                .then()
                    .statusCode(200)
                    .body("type", hasItem("trivia"));

    }

}
