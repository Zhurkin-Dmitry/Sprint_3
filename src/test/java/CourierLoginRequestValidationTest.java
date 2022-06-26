import client.CourierClient;
import model.CourierDataGenerator;
import model.CourierLogin;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CourierLoginRequestValidationTest {

    private final String login;
    private final String password;


    public CourierLoginRequestValidationTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {CourierDataGenerator.getCourierLogin(), ""},
                {"", CourierDataGenerator.getCourierPassword()},
                {"", ""},
                {CourierDataGenerator.getCourierLogin(), null},
                {null, CourierDataGenerator.getCourierPassword()},
                {null, null}
        };
    }

    @Test
    @DisplayName("Check that a courier cannot login without login and password")
    public void invalidLoginRequestIsNotAllowed(){

        final int expectedStatus = 400;
        final String expectedErrorMessage = "Недостаточно данных для входа";

        CourierLogin courierLogin = new CourierLogin(login, password);
        CourierClient courierClient = new CourierClient();
        ValidatableResponse response = courierClient.login(courierLogin);

        int actualStatusCode = response.extract().statusCode();
        assertThat("Status code is not 400", actualStatusCode, equalTo(expectedStatus));

        String actualErrorMessage = response.extract().path("message");
        assertThat("Error message is incorrect", actualErrorMessage, equalTo(expectedErrorMessage));
    }
}