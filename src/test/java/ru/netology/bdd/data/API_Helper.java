package ru.netology.bdd.data;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API_Helper {
    private String uri = "http://localhost:9999";
    private String bearerToken = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbiI6InZhc3lhIn0.JmhHh8NXwfqktXSFbzkPohUb90gnc3yZ9tiXa0uUpRY";
    private List<Map<String, Object>> cardsList;
    private Map<String, Object> firstCard;
    private Map<String, Object> secondCard;
    private int firstCardBalance;
    private int secondCardBalance;
    private int target;
    private String firstCardNumber;
    private String secondCardNumber;
    private String firstCardID;
    private String secondCardID;

    public API_Helper() {
        getCardsList();
        firstCard = cardsList.get(0);
        secondCard = cardsList.get(1);
        firstCardBalance = (int) firstCard.get("balance");
        secondCardBalance = (int) secondCard.get("balance");
        target = (firstCardBalance + secondCardBalance) / 2;
        firstCardNumber = "5559 0000 0000 0001";
        secondCardNumber = "5559 0000 0000 0002";
        firstCardID = (String) firstCard.get("id");
        secondCardID = (String) secondCard.get("id");
    }

    private void getCardsList() {
        Response response = given()
                .baseUri(uri)
                .header("Authorization", bearerToken)
                .contentType("application/json")
                .when()
                .get("/api/cards")
                .then()
                .statusCode(200)
                .extract().response();

        cardsList = response.as(List.class);
    }

    public void resetCardsBalances() {
        Map<String, Object> requestBody = new HashMap<>();

        if (firstCardBalance > secondCardBalance) {
            requestBody.put("from", firstCardNumber);
            requestBody.put("to", secondCardID);
            requestBody.put("amount", firstCardBalance - target);
            given()
                    .baseUri(uri)
                    .header("Authorization", bearerToken)
                    .contentType("application/json")
                    .body(requestBody)
                    .when()
                    .post("/api/transfer")
                    .then()
                    .statusCode(200);

        } else if (firstCardBalance < secondCardBalance) {
            requestBody.put("from", secondCardNumber);
            requestBody.put("to", firstCardID);
            requestBody.put("amount", secondCardBalance - target);
            given()
                    .baseUri(uri)
                    .header("Authorization", bearerToken)
                    .contentType("application/json")
                    .body(requestBody)
                    .when()
                    .post("/api/transfer")
                    .then()
                    .statusCode(200);
        }
    }
}
