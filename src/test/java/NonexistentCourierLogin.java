import client.CourierClient;
import model.CourierDataGenerator;
import model.CourierLogin;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NonexistentCourierLogin {

    @Test
    @DisplayName("Check that a nonexistent courier cannot login")
    public void nonexistentCourierLogin(){
        CourierLogin courierLogin = new CourierLogin(CourierDataGenerator.getCourierLogin(), CourierDataGenerator.getCourierPassword());
        CourierClient courierClient = new CourierClient();
        ValidatableResponse response = courierClient.login(courierLogin);

        int actualStatusCode = response.extract().statusCode();
        String actualErrorMessage = response.extract().path("message");

        assertThat("Status code is not 404", actualStatusCode, equalTo(404));
        assertThat("Error message is incorrect", actualErrorMessage, equalTo("Учетная запись не найдена"));
    }
}