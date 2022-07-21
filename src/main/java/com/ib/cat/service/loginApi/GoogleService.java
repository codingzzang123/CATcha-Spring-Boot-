package com.ib.cat.service.loginApi;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@Service
public class GoogleService {

    public String  getToken(String code) {
        String clientId = "1022596664094-r26vimfhq0ooql9516nctpombjqv3ilg.apps.googleusercontent.com";
        String clientSecret = "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";
        String redirectUri = "http://localhost:8080/googlecallback";
        String reqUrl = "https://oauth2.googleapis.com/token";
        String access_Token = null;
        String id_token = null;

        URL url;
        try {
            url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("code=" + code);
            sb.append("&client_id="+clientId);
            sb.append("&client_secret="+clientSecret); //본인이 발급받은 key
            sb.append("&redirect_uri="+redirectUri); // 본인이 설정한 주소
            sb.append("&grant_type=authorization_code");
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

//            System.out.println("response body : " + result);

            JsonElement element = JsonParser.parseString(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            id_token = element.getAsJsonObject().get("id_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("id_token : " + id_token);

            br.close();
            bw.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_Token;
    }

    public HashMap<String, Object> getUserInfo (String access_Token) {
        String googleApiKey = "AIzaSyBPOuLbcPAI6WVldLXNjzaspeuF1nf3Kb4";

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://www.googleapis.com/drive/v2/files?access_token="+access_Token;
        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            //    요청에 필요한 Header에 포함될 내용
            conn.addRequestProperty("Authorization", "Bearer " + access_Token);
            System.out.println(conn.getURL());
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonElement element = JsonParser.parseString(result);
            System.out.println(element);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userInfo;
    }
}
