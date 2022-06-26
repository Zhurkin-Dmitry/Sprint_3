import model.Order;
import client.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.OrderDataGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class OrderWithColorsTest {
    private final String[] color;

    public OrderWithColorsTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{""}}
        };
    }

    @Test
    @DisplayName("Check that orders with different colors can be created")
    public void differentColorsInOrderAreAllowed(){
        final int expectedStatus = 201;
        Order order = OrderDataGenerator.getRandom().setColor(color);
        OrderClient orderClient = new OrderClient();
        ValidatableResponse response = orderClient.create(order);

        int actualStatusCode = response.extract().statusCode();
        assertThat("Status code is not 201", actualStatusCode, equalTo(expectedStatus));

    }
}