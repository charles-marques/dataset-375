package com.instapaper.ui.screen;

import com.instapaper.InstapaperResource;
import com.instapaper.google.analytics.GoogleAnalyticsTracker;
import com.instapaper.net.Network;
import com.instapaper.net.NetworkCallback;
import com.instapaper.ui.field.SpacerField;
import com.instapaper.util.Configuration;
import com.instapaper.util.Logger;
import com.instapaper.util.PostDataEncoder;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Clipboard;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.AutoTextEditField;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;

public class AddScreen extends MainScreen implements FieldChangeListener
{
    private static ResourceBundle resources = null;
    static
    {
        resources = ResourceBundle.getBundle(InstapaperResource.BUNDLE_ID, InstapaperResource.BUNDLE_NAME);
    }
    private static Logger log = Logger.getInstance();
    
    private BitmapField logoField = null;
    private LabelField urlLabelField = null;
    private AutoTextEditField urlField = null;
    private ButtonField addButton = null;
    private MenuItem settingsMenuItem = null;
    
    public AddScreen() {
        super();
        
        GoogleAnalyticsTracker.getInstance().trackPageView("/add");
        
        add(new SpacerField(10));
        
        logoField = new BitmapField(Bitmap.getBitmapResource("Instapaper Title.png"), Field.FIELD_HCENTER);
        add(logoField);
        
        add(new SpacerField(10));
        
        urlLabelField = new LabelField(resources.getString(InstapaperResource.ADD_SCREEN_URL_LABEL));
        add(urlLabelField);
        
        String url = "";
        Object clipboard = Clipboard.getClipboard().get();
        if(clipboard instanceof String)
        {
            url = (String) clipboard;
            if(! url.toLowerCase().startsWith("http"))
                url = "";
        }
        
        urlField = new AutoTextEditField("", url);
        add(urlField);
        
        add(new SpacerField(10));
        
        addButton = new ButtonField(resources.getString(InstapaperResource.ADD_SCREEN_ADD_BUTTON_LABEL), ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
        addButton.setChangeListener(this);
        add(addButton);
        
        if(url.length() > 0)
            addButton.setFocus();
        
        settingsMenuItem = new MenuItem("Settings", 100, 100)
        {            
            public void run()
            {
                UiApplication.getUiApplication().pushScreen(new SettingsScreen());                
            }
        };
        addMenuItem(settingsMenuItem);
    }

    public void fieldChanged(Field field, int context)
    {
        if(field == addButton)
        {
            String browserUrl = urlField.getText();
            if(browserUrl != null && browserUrl.length() > 0 && browserUrl.toLowerCase().startsWith("http"))
            {
                log.info("adding url: " + browserUrl);
                addButton.setLabel("Adding...");
                
                String url = "https://www.instapaper.com/api/add";
                Configuration config = Configuration.getInstance();
                
                String username = config.getInstapaperUsername();
                String password = config.getInstapaperPassword();
                
                PostDataEncoder postdata = new PostDataEncoder();
                postdata.append("username", username);
                if (password != null && password.length() > 0)
                    postdata.append("password", password);
                postdata.append("url", browserUrl.toString());
                
                Network network = new Network();
                network.doPost(url, postdata.getBytes(), new NetworkCallback()
                {
                    public void onNetworkRequestSuccessful(String reply)
                    {
                        log.info("successfully added");
                        log.debug(reply);
                        
                        GoogleAnalyticsTracker.getInstance().trackEvent("add", "success", null, -1);
                        
                        // NetworkResponse response = (NetworkResponse) reply;
                        UiApplication.getUiApplication().invokeLater(new Runnable()
                        {
                            public void run()
                            {
                                addButton.setLabel(resources.getString(InstapaperResource.ADD_SCREEN_ADD_BUTTON_LABEL));
                                Dialog.inform(resources.getString(InstapaperResource.INSTAPAPER_ADDED));
                                urlField.setText("");
                            }
                        });
                        
                    }
                    
                    public void onNetworkRequestFailed(String message, Exception e)
                    {
                        
                        log.exception("error adding url.\n\n" + message, e);
                        GoogleAnalyticsTracker.getInstance().trackEvent("add", "failure", null, -1);
                        UiApplication.getUiApplication().invokeLater(new Runnable()
                        {
                            public void run()
                            {
                                addButton.setLabel(resources.getString(InstapaperResource.ADD_SCREEN_ADD_BUTTON_LABEL));
                                int choice = Dialog.ask(resources.getString(InstapaperResource.INSTAPAPER_FAILED), new String[] { resources.getString(InstapaperResource.INSTAPAPER_FAILED_CHECK_SETTINGS),
                                        resources.getString(InstapaperResource.CANCEL) }, 0);
                                if (choice == 0)
                                {
                                    SettingsScreen screen = new SettingsScreen();
                                    UiApplication.getUiApplication().pushScreen(screen);
                                }
                            }
                        });
                    }
                });
            }
            else
            {
                Dialog.alert(resources.getString(InstapaperResource.DIALOG_INVALID_URL));
            }
        }
    }
    
    protected boolean onSavePrompt()
    {
        return true;
    }
}
