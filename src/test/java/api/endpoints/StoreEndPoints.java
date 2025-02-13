package api.endpoints;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class StoreEndPoints {

    public static Response getInventory(){
        Response res = given()
                .when()
                .get(Routes.get_inventory);

        return res;
    }

    public static Response postCreateOrder(Store payload){
        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_order);

        return res;
    }

    public static Response getOrder(String orderId){
        Response res = given()
                .pathParam("orderId", orderId)
                .when()
                .get(Routes.get_order);
        return res;
    }

    public static Response deleteOrder(String orderId){
        Response res = given()
                .pathParam("orderId", orderId)
                .when()
                .delete(Routes.delete_order);
        return res;
    }
}
