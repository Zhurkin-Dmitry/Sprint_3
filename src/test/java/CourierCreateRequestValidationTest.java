import model.Courier;
import client.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.CourierDataGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CourierCreateRequestValidationTest {

    private final Courier courier;

    public CourierCreateRequestValidationTest(Courier courier) {
        this.courier = courier;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {CourierDataGenerator.getWithLoginOnly()},
                {CourierDataGenerator.getWithPasswordOnly()},
                {CourierDataGenerator.getWithFirstNameOnly()}
        };
    }

    @Test
    @DisplayName("Check that a courier without login and password cannot be created")
    public void invalidRequestIsNotAllowed(){
        ValidatableResponse response = new CourierClient().create(courier);
        int expectedStatus = 400;
        String expectedErrorMessage = "Недостаточно данных для создания учетной записи";

        int actualStatusCode = response.extract().statusCode();
        String actualErrorMessage = response.extract().path("message");

        assertThat("Status code is not 400", actualStatusCode, equalTo(expectedStatus));
        assertThat("Error message is incorrect", actualErrorMessage, equalTo(expectedErrorMessage));
    }
}