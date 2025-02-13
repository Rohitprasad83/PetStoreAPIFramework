package api.endpoints;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetEndPoints {

    public static Response getPet(int petId){
        Response res = given()
                .contentType(ContentType.JSON)
                .pathParam("petId", petId)
                .when()
                .get(Routes.get_pet);
        return res;
    }

    public static Response postCreatePet(Pet payload){
        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_pet);

        return res;
    }

    public static Response deletePet(int petId){
        Response res = given()
                .contentType(ContentType.JSON)
                .pathParam("petId", petId)
                .when()
                .delete(Routes.delete_pet);
        return res;
    }

    public static Response updatePet(Pet payload){
        Response res = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.put_pet);

        return res;
    }

    public static Response uploadImageToPet(int petId, File file){

        Response res = given()
                .contentType(ContentType.MULTIPART)
                .pathParam("petId", petId)
                .multiPart(file)
                .formParam("additionalMetadata", "dog")
                .when()
                .post(Routes.post_image_to_pet);
        return res;
    }

    public static Response getPetByStatus(String status){
        Response res = given()
                .contentType(ContentType.JSON)
                .queryParam("status", status)
                .when()
                .get(Routes.get_pet_by_status);
        return res;
    }

}
