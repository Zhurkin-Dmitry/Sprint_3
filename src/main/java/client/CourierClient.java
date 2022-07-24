package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.Courier;
import model.CourierLogin;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient {

    private static final String COURIER_PATH = "/api/v1/courier";

    @Step("Send POST request to /api/v1/courier")
    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    @Step("Send POST request to /api/v1/courier/login")
    public ValidatableResponse login(CourierLogin courierLogin){
        return given()
                .spec(getBaseSpec())
                .body(courierLogin)
                .when()
                .post(COURIER_PATH + "/login")
                .then();
    }

    @Step("Send DELETE request to /api/v1/courier")
    public boolean delete(int courierId){
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + "/" + courierId)
                .then()
                .extract()
                .path("ok");
    }
}