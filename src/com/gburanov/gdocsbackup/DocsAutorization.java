package com.gburanov.gdocsbackup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

public class DocsAutorization {
  private static String CLIENT_ID = "458334002012.apps.googleusercontent.com";
  private static String CLIENT_SECRET = "KRpdJiDxptYprnkiBFJCFPbo";

  private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
  
  private HttpTransport httpTransport = new NetHttpTransport();
  private JsonFactory jsonFactory = new JacksonFactory();
  GoogleCredential credential;
  
  
  public DocsAutorization()  throws IOException {
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
        .setAccessType("online")
        .setApprovalPrompt("auto").build();
    
    String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
    System.out.println("Please open the following URL in your browser then type the authorization code:");
    System.out.println("  " + url);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String code = br.readLine();
    
    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
    credential = new GoogleCredential().setFromTokenResponse(response);
  }
  
  public Drive getDrive() {
    Drive drive = new Drive.Builder(httpTransport, jsonFactory, credential).build();
    return drive;
  }

}
