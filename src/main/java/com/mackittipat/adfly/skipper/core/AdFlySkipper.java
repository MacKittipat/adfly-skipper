package com.mackittipat.adfly.skipper.core;

import com.mackittipat.adfly.skipper.core.model.TargetUrlModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class AdFlySkipper {
    
    private static final String BYPASS_AD_FLY_URL = "http://www.bypassshorturl.com/get.php";
    private static final int TIMEOUT = 2000; 
    
    public List<TargetUrlModel> skipAdFlyUrl(List<String> adFlyUrlList) {
        
        List<TargetUrlModel> targetUrlModelList = new ArrayList<>();
        
        for(String adFlyUrl : adFlyUrlList) {
            try {
                Content content = Request.Post(BYPASS_AD_FLY_URL)
                        .bodyForm(Form.form().add("url",  adFlyUrl).build())
                        .execute()
                        .returnContent();
                String targetUrl = content.asString();
                TargetUrlModel targetUrlModel = new TargetUrlModel();
                targetUrlModel.setUrl(targetUrl);
//                targetUrlModel.setIsDownloadable(retrieveRequestStatus(targetUrl));      
                targetUrlModelList.add(targetUrlModel);
            } catch (IOException ex) {
                Logger.getLogger(AdFlySkipper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return targetUrlModelList;
    }    
    
    public boolean retrieveRequestStatus(String url) {
        System.out.println("Url to check status = " + url);
        boolean result = false;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = client.execute(httpGet);
            if(httpResponse != null) {
                System.out.println("Status code = " + httpResponse.getStatusLine().getStatusCode());
            }            
            if(httpResponse != null && 
                    httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = true;
            }
        } catch (ClientProtocolException ex) {
            Logger.getLogger(AdFlySkipper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdFlySkipper.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return result;
    }
}
