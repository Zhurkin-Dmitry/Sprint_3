package model;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierDataGenerator {
    // метод randomAlphabetic генерирует строку, состоящую только из букв, в качестве параметра передаём длину строки

    public static String getCourierLogin(){
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String getCourierPassword(){
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String getCourierFirstName(){
        return RandomStringUtils.randomAlphabetic(10);
    }


    public static Courier getWithLoginOnly(){
        return new Courier().setLogin(RandomStringUtils.randomAlphabetic(10));
    }

    public static Courier getWithPasswordOnly() {
        return new Courier().setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    public static Courier getWithFirstNameOnly() {
        return new Courier().setFirstName(RandomStringUtils.randomAlphabetic(10));
    }


    public static Courier getRandom(){
        // метод randomAlphabetic генерирует строку, состоящую только из букв, в качестве параметра передаём длину строки
        final String courierLogin = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем пароль
        final String courierPassword = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем имя курьера
        final String courierFirstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(courierLogin, courierPassword, courierFirstName);
    }

    public static Courier getRandomObligatoryFields(){
        // метод randomAlphabetic генерирует строку, состоящую только из букв, в качестве параметра передаём длину строки
        final String courierLogin = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем пароль
        final String courierPassword = RandomStringUtils.randomAlphabetic(10);
        return new Courier(courierLogin, courierPassword);
    }
}