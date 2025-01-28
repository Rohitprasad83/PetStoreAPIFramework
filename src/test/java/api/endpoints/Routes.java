package api.endpoints;

public class Routes {

    //Base Url
    public static String baseUrl = "https://petstore.swagger.io/v2";

    //User Routes
    public static String post_user_url = baseUrl+"/user";
    public static String get_user_url = baseUrl + "/user/{username}";
    public static String update_user_url = baseUrl + "/user/{username}";
    public static String delete_user_url = baseUrl +"/user/{username}";

    //Store Routes
}
