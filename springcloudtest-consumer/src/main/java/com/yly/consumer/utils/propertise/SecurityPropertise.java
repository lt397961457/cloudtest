package com.yly.consumer.utils.propertise;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "myconf.security")
public class SecurityPropertise {
    private BrowserPropertise browser = new BrowserPropertise();

    private MySocialProperties social = new MySocialProperties();

    public BrowserPropertise getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserPropertise browser) {
        this.browser = browser;
    }

    public MySocialProperties getSocial() {
        return social;
    }

    public void setSocial(MySocialProperties social) {
        this.social = social;
    }
}
