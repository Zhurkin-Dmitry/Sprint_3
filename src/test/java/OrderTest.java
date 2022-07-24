import model.Order;
import client.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.OrderDataGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class OrderTest {

    OrderClient orderClient;
    Order order;

    @Before
    public void setUp(){
        orderClient = new OrderClient();
        order = OrderDataGenerator.getRandom();
    }

    @Test
    @DisplayName("Check that an order can be created")
    public void orderCanBeCreated(){
        ValidatableResponse response = orderClient.create(order);

        int orderStatusCode = response.extract().statusCode();
        assertThat("", orderStatusCode, equalTo(201));

        int orderId = response.extract().path("track");
        assertThat("Track is incorrect", orderId, is(not(0)));

        //extra check that the track actually exists
        int getOrderStatusCode = orderClient.get(orderId).extract().statusCode();
        assertThat("", getOrderStatusCode, equalTo(200));
    }

    @Test
    @DisplayName("Check that a list of all orders can be got")
    public void getOrdersReturnsTest(){
        ValidatableResponse response = orderClient.getAll();

        List<Object> orders = response.extract().jsonPath().getList("orders");
        assertFalse(orders.isEmpty());
    }
}