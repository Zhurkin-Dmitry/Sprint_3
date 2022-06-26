package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.Order;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {

    private static final String ORDER_PATH = "/api/v1/orders";

    @Step("Send POST request to /api/v1/orders")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step("Send GET request to /api/v1/orders to get one order")
    public ValidatableResponse get(int orderNumber) {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH + "/track?t=" + orderNumber)
                .then();
    }

    @Step("Send GET request to /api/v1/orders to get all oreders")
    public ValidatableResponse getAll() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }
}