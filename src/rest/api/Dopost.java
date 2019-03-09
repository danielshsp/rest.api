/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.api;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.*;
import okio.*;
public class Dopost {

   OkHttpClient client ;
 
public Dopost(){ 
    this.client = new OkHttpClient();
}   

 public void postCorrect() throws IOException {
        
        MediaType mediaType = MediaType.parse("application/json");
         RequestBody body;
         body = RequestBody.create(mediaType, "{\r\n \"account_id\":\"selectivedorb\",\r\n \"user_name\":\"sysaid\",\r\n \"password\":\"changeit\"\r\n}");
    
        Request request = new Request.Builder()
        .url("http://localhost:8080/api/v1/login")
        .post(body)
        .addHeader("Content-Type", "application/json")
        .addHeader("Cache-Control", "no-cache")
        .build();
        Call call = client.newCall(request);
         Response response = call.execute();
         if(response.code()==200){
             String jsessionid =response.headers().get("Set-Cookie");
             String [] arr =jsessionid.split(" ");
             String jsession = arr[0];
             String s = jsession.substring(11);
             System.out.println(s);
             createSR(s);
         }
            
    }
 
 public void createSR(String theSession) throws IOException{
     MediaType mediaType = MediaType.parse("application/json");
         RequestBody body;
        body = RequestBody.create(mediaType, "{\r\n\t\"info\":\r\n\t[\r\n\t\t{\"key\":\"status\",\"value\":\"2\"},\r\n    \t  {\"key\":\"request_user\",\"value\":\"1\"},\r\n    \t{\"key\":\"description\",\"value\":\"SDDQQ\"}\r\n    \t\r\n\r\n\r\n]\r\n}");
        Request request;
       request = new Request.Builder()
               .url("http://localhost:8080/api/v1/sr;jsessionid="+theSession+"")
               .post(body)
               .addHeader("Content-Type", "application/json")
               .addHeader("Cache-Control", "no-cache")
               .build();
        Call call = client.newCall(request);
        Response response;
        response = call.execute();
         if(response.code()==200){
             System.out.println("okkkk2"+response.body().string());
         }else{
              System.out.print("bad"+response);
         }
         
 }
 
 
}
