package com.instapaper.ui.screen;

import java.io.IOException;

import com.instapaper.InstapaperResource;
import com.instapaper.google.analytics.GoogleAnalyticsTracker;
import com.instapaper.net.Network;
import com.instapaper.net.NetworkCallback;
import com.instapaper.net.NetworkConfig;
import com.instapaper.ui.field.SpacerField;
import com.instapaper.util.CheckUpdateThread;
import com.instapaper.util.Configuration;
import com.instapaper.util.Logger;
import com.instapaper.util.PostDataEncoder;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.AutoTextEditField;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;

public class SettingsScreen extends MainScreen implements FieldChangeListener
{   
    private static ResourceBundle resources = null;
    static
    {
        resources = ResourceBundle.getBundle(InstapaperResource.BUNDLE_ID, InstapaperResource.BUNDLE_NAME);
    }
  
    private static Configuration config = Configuration.getInstance();
    private static Logger log = Logger.getInstance();
    
    private BitmapField logoField = null;
    private LabelField usernameLabelField = null;
    private LabelField passwordLabelField = null;
    private AutoTextEditField usernameEditField = null;
    private PasswordEditField passwordEditField = null;
    private ButtonField loginButton = null;
    
    private ObjectChoiceField networkField = null;
    
    private MenuItem checkUpdateMenuItem = null;
    
    public SettingsScreen()
    {
        super(DEFAULT_CLOSE);
        
        GoogleAnalyticsTracker tracker = GoogleAnalyticsTracker.getInstance();
        tracker.trackPageView("/settings");
        
        add(new SpacerField(10));
        
        logoField = new BitmapField(Bitmap.getBitmapResource("Instapaper Title.png"), Field.FIELD_HCENTER);
        add(logoField);
        
        add(new SpacerField(10));
        
        
        
        usernameLabelField = new LabelField(resources.getString(InstapaperResource.SETTINGS_LABEL_USERNAME));
        add(usernameLabelField);
        
        usernameEditField = new AutoTextEditField("", config.getInstapaperUsername());
        add(usernameEditField);
        
        add(new SpacerField(10));
        
        passwordLabelField = new LabelField(resources.getString(InstapaperResource.SETTINGS_LABEL_PASSWORD));
        add(passwordLabelField);
        
        passwordEditField = new PasswordEditField("", config.getInstapaperPassword());
        add(passwordEditField);   
        
        add(new SpacerField(10));
        
        loginButton = new ButtonField(resources.getString(InstapaperResource.SETTINGS_LABEL_LOGIN_BUTTON), ButtonField.CONSUME_CLICK |FIELD_HCENTER);
        loginButton.setCookie("login");
        loginButton.setChangeListener(this);
        add(loginButton);
        
        add(new SpacerField(10));
        
        add(new SeparatorField());
        
        add(new SpacerField(10));
        
        networkField = new ObjectChoiceField(resources.getString(InstapaperResource.SETTINGS_LABEL_NETWORK), NetworkConfig.choices, config.getConnectionMode());
        add(networkField);
        
        checkUpdateMenuItem = new MenuItem(resources.getString(InstapaperResource.MENUITEM_CHECK_UPDATES), 500000, 100)
        {            
            public void run()
            {
                CheckUpdateThread checkUpdateThread = new CheckUpdateThread(true);
                checkUpdateThread.start();
            }
        };
        
        addMenuItem(checkUpdateMenuItem);
    }
    
    public void save()
    {
        config.setInstapaperPassword(passwordEditField.getText());
        config.setInstapaperUsername(usernameEditField.getText());
        config.setConnectionMode(networkField.getSelectedIndex());
        Configuration.save();
    }

    public void fieldChanged(Field field, int context)
    {
        String cookie = (String) field.getCookie();
        if (cookie.equals("login"))
        {
            loginButton.setLabel("Logging in...");
            String url = "https://www.instapaper.com/api/authenticate";
            
            PostDataEncoder postdata = new PostDataEncoder();
            postdata.append("username", usernameEditField.getText());
            String password = passwordEditField.getText();
            if (password != null && password.length() > 0)
                postdata.append("password", password);
            
            Network network = new Network();
            network.doPost(url, postdata.getBytes(), new NetworkCallback()
            {
                public void onNetworkRequestSuccessful(String reply)
                {           
                    log.debug("login successful");
                    GoogleAnalyticsTracker.getInstance().trackEvent("login", "success", null, -1);
                    
                    UiApplication.getUiApplication().invokeLater(new Runnable()
                    {
                        public void run()
                        {
                            save();
                            loginButton.setLabel(resources.getString(InstapaperResource.SETTINGS_LABEL_LOGIN_BUTTON));
                            Dialog.alert(resources.getString(InstapaperResource.DIALOG_LOGIN_SUCCESS));
                            UiApplication.getUiApplication().popScreen(SettingsScreen.this);
                            UiApplication.getUiApplication().pushScreen(new AddScreen());
                        }
                    });
                    
                }
                
                public void onNetworkRequestFailed(String message, Exception e)
                {
                    log.exception("error logging in", e);
                    GoogleAnalyticsTracker.getInstance().trackEvent("login", "failure", null, -1);
                    UiApplication.getUiApplication().invokeLater(new Runnable()
                    {
                        public void run()
                        {
                            loginButton.setLabel(resources.getString(InstapaperResource.SETTINGS_LABEL_LOGIN_BUTTON));
                            Dialog.alert(resources.getString(InstapaperResource.DIALOG_LOGIN_FAILURE));
                        }
                    });
                }
            });
        }
        
    }
    
    /*protected void paint(Graphics graphics)
    {
        graphics.setBackgroundColor(0xF4F4F4);
        
        graphics.clear();
        
        super.paint(graphics); 
    }*/
}
