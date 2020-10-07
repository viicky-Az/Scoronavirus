package edu.cecar.controlador;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Clase que controla la validacion del email
 * por medio de la API email-validator
 *
 */
public class ValidarEmail {

    public Long validarEmail(String email) {
        HttpClient client = new DefaultHttpClient();
        
        String apiKey = "ev-8aaec8ad2b255228dba5750ab63119da";
        String apiUrl = "https://api.email-validator.net/api/verify";

        try {
            HttpPost request = new HttpPost(apiUrl);
            List<NameValuePair> Input = new ArrayList<NameValuePair>();
            Input.add(new BasicNameValuePair("EmailAddress", email));
            Input.add(new BasicNameValuePair("APIKey", apiKey));
            request.setEntity(new UrlEncodedFormEntity(Input));
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String Output = EntityUtils.toString(entity, "UTF-8");
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(Output);
            JSONObject jsonObject = (JSONObject) obj;
            Long result = (Long) jsonObject.get("status");
            // result 200, 207, 215 - valid
            // result 215 - can be retried to update catch-all status
            // result 114 - greylisting, wait 5min and retry
            // result 118 - api rate limit, wait 5min and retry
            // result 3xx/4xx - bad
            return result;
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            client.getConnectionManager().shutdown();
        }
        return null;
    }

}
