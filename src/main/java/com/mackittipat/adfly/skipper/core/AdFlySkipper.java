package com.mackittipat.adfly.skipper.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class AdFlySkipper {
    
    private static final String BYPASS_AD_FLY_URL = "http://www.bypassshorturl.com/get.php";
    
    public List<String> skipAdFlyUrl(List<String> adFlyUrlList) {
        List<String> targetUrlList = new ArrayList<>();
        
        for(String adFlyUrl : adFlyUrlList) {
            try {
                Content content = Request.Post(BYPASS_AD_FLY_URL)
                        .bodyForm(Form.form().add("url",  adFlyUrl).build())
                        .execute().returnContent();
                targetUrlList.add(content.asString());
            } catch (IOException ex) {
                Logger.getLogger(AdFlySkipper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return targetUrlList;
    }    
}
