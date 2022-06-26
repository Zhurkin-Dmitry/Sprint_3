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
import static org.junit.Assert.assertTrue;

public class CourierCreationTest {

    private CourierClient courierClient;
    private Courier courier;
    private int courierId;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
    }

    @After
    public void tearDown(){
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Check that a courier can be created")
    public void checkCourierCanBeCreated(){
        courier = CourierDataGenerator.getRandom();
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");
        courierId = courierClient.login(CourierLogin.from(courier)).extract().path("id");

        assertThat("Status code is not 201", statusCode, equalTo(201));
        assertTrue("Courier is not created", isCourierCreated);
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }

    @Test
    @DisplayName("Check that a courier can be created only with the obligatory fields")
    public void checkCourierWithObligatoryFieldsCanBeCreated(){
        courier = CourierDataGenerator.getRandomObligatoryFields();
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");
        courierId = courierClient.login(CourierLogin.from(courier)).extract().path("id");

        assertThat("Status code is not 201", statusCode, equalTo(201));
        assertTrue("Courier is not created", isCourierCreated);
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }

    @Test
    @DisplayName("Check that a duplicated courier cannot be created")
    public void duplicatedCourierCannotBeCreated(){
        courier = CourierDataGenerator.getRandom();
        courierClient.create(courier); //first courier creation
        courierId = courierClient.login(CourierLogin.from(courier)).extract().path("id"); //login to get ID to clean up after the test
        ValidatableResponse response = courierClient.create(courier); //second courier creation

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertThat("Status code is not 409", statusCode, equalTo(409));
        assertThat("Error message is incorrect", errorMessage, equalTo("Этот логин уже используется"));
    }
}