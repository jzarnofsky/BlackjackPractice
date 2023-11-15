package catapi;

import io.restassured.response.ExtractableResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.RestAssured.given;

public class GetKitties {

    public static void main(String[] asdf) {
        ExtractableResponse response = given()
                .headers("x-api-key", "live_8TWILufDs3XtNNPsMTKYnlaXFyvWvQoCSWm4wxdsb4XiYmzuZnsJT0fZeucU8Dkf")
                .get("https://api.thecatapi.com/v1/images/search?limit=10")
                .then()
                .extract();

        System.out.println(response.body().asString());

        //turn the response into a JSON (Array)
        JSONArray jsonArray = new JSONArray(response.body().asString());

        //Find some method to select which picture we want
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        //From the JSON object with the picture, get the URL
        String getPicUrl = jsonObject.getString("url");

        System.out.println(getPicUrl);

        ExtractableResponse response2 = given()
                .get(getPicUrl)
                .then()
                .extract();
        System.out.println(response2.body().asString());

        File file = new File("/Users/JDoty/Desktop/catPicture.png");
        file.createNewFile();

        //Use the URL in a network call to get the picture
        //Save picture to desktop
    }

}
