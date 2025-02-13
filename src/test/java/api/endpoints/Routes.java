package api.endpoints;

public class Routes {

    //Base Url
    public static String baseUrl = "https://petstore.swagger.io/v2";

    //User Routes
    public static String post_user_url = baseUrl+"/user";
    public static String get_user_url = baseUrl + "/user/{username}";
    public static String update_user_url = baseUrl + "/user/{username}";
    public static String delete_user_url = baseUrl +"/user/{username}";
    public static String post_user_url_with_array = baseUrl + "/user/createWithArray";
    public static String get_login = baseUrl + "/user/login";
    public static String get_logout = baseUrl + "/user/logout";

    //Store Routes
    public static String get_inventory = baseUrl + "/store/inventory";
    public static String post_order = baseUrl + "/store/order";
    public static String get_order = baseUrl + "/store/order/{orderId}";
    public static String delete_order = baseUrl + "/store/order/{orderId}";

    //Pet Routes
    public static String post_pet = baseUrl + "/pet";
    public static String post_image_to_pet = baseUrl + "/pet/{petId}/uploadImage";
    public static String get_pet = baseUrl + "/pet/{petId}";
    public static String put_pet = baseUrl + "/pet";
    public static String get_pet_by_status = baseUrl + "/pet/findByStatus";
    public static String delete_pet = baseUrl+"/pet/{petId}";
}
