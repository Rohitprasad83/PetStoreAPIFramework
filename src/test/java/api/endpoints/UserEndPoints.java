package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class UserEndPoints {

    public static Response createUser(User payload){

        Response res = given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post(Routes.post_user_url);
        return res;
    }
    public static Response getUser(String userName){

        Response res = given()
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .when()
                .get(Routes.get_user_url);
        return res;
    }

    public static Response updateUser(User payload, String userName){

        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(Routes.update_user_url);
        return res;
    }

    public static Response deleteUser(String userName){

        Response res = given()
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .when()
                .delete(Routes.delete_user_url);
        return res;
    }

    public static Response createUserWithArray(User payload){
        User[] user = new User[1];
        user[0] = payload;
        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .when()
                .post(Routes.post_user_url_with_array);
        return res;
    }

    public static Response loginUser(String username, String password){

        Response res = given()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get(Routes.get_login);

        return res;
    }

    public static Response logoutUser(){

        Response res = given()
                .when()
                .get(Routes.get_logout);
        return res;
    }



}
