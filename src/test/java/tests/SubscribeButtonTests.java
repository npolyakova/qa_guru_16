package tests;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class SubscribeButtonTests {

    private static final String BASE_URI = "http://demowebshop.tricentis.com";

    @Test
    public void SubscribeWithoutEmail(){

        given()
                .baseUri(BASE_URI)
                .when()
                .post("/subscribenewsletter")
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("Success", is(false))
                .body("Result", is("Enter valid email"));
    }

    @Test
    public void SubscribeWithInvalidEmail(){

        given()
                .baseUri(BASE_URI)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("email","123")
                .when()
                .post("/subscribenewsletter")
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("Success", is(false))
                .body("Result", is("Enter valid email"));
    }

    @Test
    public void SubscribeWithValidEmail(){

        given()
                .baseUri(BASE_URI)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("email","123@12.12")
                .log().body()
                .when()
                .post("/subscribenewsletter")
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("Success", is(true))
                .body("Result", is("Thank you for signing up! A verification email has been sent. We appreciate your interest."));
    }
}
