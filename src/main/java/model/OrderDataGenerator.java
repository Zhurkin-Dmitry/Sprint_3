package model;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderDataGenerator {
    public static Order getRandom(){
        int maxDays = 30;
        // метод randomAlphabetic генерирует строку, состоящую только из букв, в качестве параметра передаём длину строки
        final String orderFirstName = RandomStringUtils.randomAlphabetic(10);
        final String orderLastName = RandomStringUtils.randomAlphabetic(10);
        final String orderAddress = RandomStringUtils.randomAlphabetic(10);
        final String orderMetroStation = RandomStringUtils.randomAlphabetic(10);
        final String orderPhone = RandomStringUtils.randomAlphabetic(10);
        final int rentTime = (int) (Math.random() * ++maxDays) + 1;
        final String deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        final String comment = RandomStringUtils.randomAlphabetic(10);
        return new Order(orderFirstName, orderLastName, orderAddress,
                orderMetroStation, orderPhone, rentTime, deliveryDate, comment);
    }
}