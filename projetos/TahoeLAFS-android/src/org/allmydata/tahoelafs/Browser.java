package org.allmydata.tahoelafs;

import org.allmydata.tahoelafs.TahoeDirectory.types;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Browser extends ListActivity implements Runnable {
    private static final String TAG = "DirectoryList";

    String node;
    String rootcap;

    String current_cap;

    private TahoeClient tahoe;
    private TahoeDirectory dir = null;

    private ProgressDialog pd;

    // Menu item ids
    public static final int MENU_UPLOAD = Menu.FIRST;
    public static final int MENU_HOME = Menu.FIRST + 1;
    public static final int MENU_REFRESH = Menu.FIRST + 2;
    public static final int MENU_SETTINGS = Menu.FIRST + 3;
    public static final int MENU_ABOUT = Menu.FIRST + 4;
    public static final int MENU_MKDIR = Menu.FIRST + 5;

    // Sub activities
    public static final int ACT_DONTCARE = 0;
    public static final int ACT_UPLOAD = 1;

    /* Creates the menu items */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_UPLOAD, 0, "Upload");
        menu.add(0, MENU_MKDIR, 0, "Mkdir");
        menu.add(0, MENU_HOME, 0, "Home");
        menu.add(0, MENU_REFRESH, 0, "Refresh");
        menu.add(0, MENU_SETTINGS, 0, "Settings");
        menu.add(0, MENU_ABOUT, 0, "About");
        return true;
    }

    /* Handles item selections */
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case MENU_UPLOAD:
                intent = new Intent("org.openintents.action.PICK_FILE");

                try {
                    startActivityForResult(intent, 1);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "Please install OI File Manager.", Toast.LENGTH_LONG)
                            .show();

                    intent = new Intent(Intent.ACTION_VIEW);
                    intent
                            .setData(Uri
                                    .parse("market://search?q=pname:org.openintents.filemanager"));
                    startActivity(intent);

                    return false;
                }

                refresh();
                return true;

            case MENU_MKDIR:

                refresh();
                return true;

            case MENU_HOME:
                intent = new Intent();
                intent.setClassName("org.allmydata.tahoelafs", "org.allmydata.tahoelafs.Browser");
                intent.setData(Uri.fromParts("lafs", "", rootcap));
                startActivity(intent);
                return true;

            case MENU_REFRESH:
                // Force a refresh
                refresh();
                return true;

            case MENU_SETTINGS:
                startActivity(new Intent(this, Prefs.class));
                refresh();

                return true;
            case MENU_ABOUT:
                intent = new Intent("org.openintents.action.SHOW_ABOUT_DIALOG");

                try {
                    startActivityForResult(intent, 0);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "Please install OI About.", Toast.LENGTH_LONG).show();

                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://search?q=pname:org.openintents.about"));
                    startActivity(intent);

                    return false;
                }

                return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String test_node = getString(R.string.test_node);
        String test_rootcap = getString(R.string.test_rootcap);

        setDefaultKeyMode(DEFAULT_KEYS_SHORTCUT);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Inform the list we provide context menus for items
        getListView().setOnCreateContextMenuListener(this);

        // Get preferences
        node = prefs.getString(Prefs.KEY_NODE, test_node);
        rootcap = prefs.getString(Prefs.KEY_ROOTCAP, test_rootcap);

        if (node.equals(test_node) && rootcap.equals(test_rootcap)) {
            Toast.makeText(this,
                    "Warning: You are using the TESTING grid\nyou can change that in the settings",
                    Toast.LENGTH_LONG).show();
        }

        // Load Tahoe client
        tahoe = new TahoeClient(node);

        Intent intent = getIntent();
        if (intent.getData() == null) {
            intent.setData(Uri.fromParts("lafs", "", rootcap));
        }

        String cap = getIntent().getData().getEncodedFragment();
        loadDirectory(cap);
    }

    // Need handler for callbacks to the UI thread
    final Handler mHandler = new Handler();

    // Create runnable for posting
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            updateResultsInUi();
        }
    };

    protected void loadDirectory(String cap) {
        current_cap = cap;

        if (dir == null) {
            pd = ProgressDialog.show(this, "Please wait..", "Opening directory", true, false);
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    protected void refresh() {
        dir = null;
        loadDirectory(current_cap);
    }

    public void run() {
        try {
            dir = tahoe.getDirectory(current_cap);
        } catch (Exception e) {
            // Toast.makeText(this, "Cannot access the directory referenced by "
            // + current_cap, Toast.LENGTH_LONG).show();
            Log.d(TAG, "Cannot access the directory referenced by " + current_cap);
        }

        mHandler.post(mUpdateResults);
    }

    private void updateResultsInUi() {
        if (dir != null) {
            this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    dir.toStringArray()));
        } else {
            Toast.makeText(this, "Cannot access the directory referenced by " + current_cap,
                    Toast.LENGTH_LONG).show();
        }

        pd.dismiss();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Log.d(TAG, "Item number at position " + position + " with id " + id + " was selected");

        try {
            Log.i(TAG, "Getting type");
            types type = dir.getType(position);
            Log.i(TAG, "Type: " + type);

            switch (type) {
                case dirnode:
                    Log.i(TAG, "A directory was selected");

                    Intent intent = new Intent();
                    intent.setClassName("org.allmydata.tahoelafs",
                            "org.allmydata.tahoelafs.Browser");
                    intent.setData(Uri.fromParts("lafs", "", dir.getReadCap(position)));
                    startActivity(intent);

                    return;

                case filenode:
                    Log.i(TAG, "A file was selected");
                    openFile(dir.getReadCap(position), dir.getFilename(position));
            }
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
            Toast.makeText(this, "Cannot access this element", Toast.LENGTH_LONG).show();
        }
    }

    protected void OnItemLongClickListener(ListView l, View v, int position, long id) {
        Log.d(TAG, "Item " + position + "was clicked for a long time");
    }

    /*
     * Download the file using Android Web Browser
     */
    private void openFile(String cap, String filename) {
        Uri data = Uri.parse(node + "/file/" + cap + "/@@named=/" + filename);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setData(data);
        startActivity(intent);
    }

    /*
     * Download the file using internal downloader
     */
    // private void downloadFile(String cap, String filename) {
    // Log.i(TAG, "Getting file " + filename + "whose cap is " + cap);
    // String dst = "/sdcard/" + filename;
    //
    // try {
    // tahoe.downloadFile(cap, dst);
    // } catch (IOException e) {
    // Log.e(TAG, "Download failed");
    // }
    //
    // // Display the downloaded file
    // try {
    // Uri data = Uri.parse(dst);
    // Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
    // intent.setData(data);
    // // intent.setType("image/png");
    // startActivity(intent);
    // } catch (Exception e) {
    // Log.e(TAG, "I cannot open this file");
    // Log.e(TAG, e.getMessage());
    // }
    // }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "Back from startActivityForResult");

        if (data != null && data.hasFileDescriptors()) {
            switch (requestCode) {
                case ACT_DONTCARE:
                    Log.i(TAG, "I don't care about this activity's result");

                case ACT_UPLOAD:
                    Log.i(TAG, "File upload");
                    String src = data.getData().getEncodedPath();
                    String filename = data.getData().getLastPathSegment();

                    Log.i(TAG, "Source file: " + src);

                    try {
                        tahoe.uploadFile(current_cap, filename, src);
                    } catch (Exception e) {
                        Log.e(TAG, "File upload failed");
                        Log.d(TAG, e.getStackTrace().toString());
                    }

                    // Refresh the current view
                    loadDirectory(current_cap);
            }
        }
    }
}
