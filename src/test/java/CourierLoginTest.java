import client.CourierClient;
import model.Courier;
import model.CourierDataGenerator;
import model.CourierLogin;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CourierLoginTest {

    private CourierClient courierClient;
    private Courier courier;
    int courierId;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = CourierDataGenerator.getRandom();
        courierClient.create(courier);
    }

    @After
    public void tearDown(){
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Check that a courier can login with valid password and login")
    public void courierCanLogin(){
        ValidatableResponse response = courierClient.login(CourierLogin.from(courier));

        int statusCode = response.extract().statusCode();
        courierId = response.extract().path("id");

        assertThat("", statusCode, equalTo(200));
    }

    @Test
    @DisplayName("Check that courier login request returns courier ID")
    public void courierLoginReturnsValidId(){
        ValidatableResponse response = courierClient.login(CourierLogin.from(courier));

        courierId = response.extract().path("id");

        assertThat("", courierId, is(not(0)));
    }
}