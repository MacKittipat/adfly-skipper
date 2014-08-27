package com.mackittipat.adfly.skipper.core;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdFlySkipper {
    
    private static final String BYPASS_AD_FLY_URL = "http://www.bypassshorturl.com/get.php";

    /**
     * Transform adfly url to target url.
     * @param adFlyUrl
     * @return target url
     */
    public String skipAdFlyUrl(String adFlyUrl) {
        try {
            Content content = Request.Post(BYPASS_AD_FLY_URL)
                    .bodyForm(Form.form().add("url",  adFlyUrl).build())
                    .execute().returnContent();
            return content.asString();
        } catch (IOException ex) {
            Logger.getLogger(AdFlySkipper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
