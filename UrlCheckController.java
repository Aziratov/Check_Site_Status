package spring_tutorial.is_site_running;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

  private final String SITE_UP = "Website is up and running";
  private final String SITE_DOWN = "Website can not be reached. Not running.";
  private final String INCORRECT_URL = "URL does not EXIST or is INCORRECT.";

  @GetMapping("/check")
  public String getUrlStatus(@RequestParam String url) {

    String returnMessage = "";
    try {

      URL urlObj = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
      connection.setRequestMethod("GET");
      connection.connect();

      int responseCodeCat = connection.getResponseCode() / 100;

      if (responseCodeCat != 2) {
        returnMessage = SITE_DOWN;
      } else {
        returnMessage = SITE_UP;
      }


    } catch (MalformedURLException e) {
      returnMessage = INCORRECT_URL;
    } catch (IOException e) {
      returnMessage = SITE_DOWN;
    }
    return returnMessage;
  }
}
