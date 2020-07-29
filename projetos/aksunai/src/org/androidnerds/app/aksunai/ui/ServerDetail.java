/*
 * Copyright (C) 2009  AndroidNerds.org
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.androidnerds.app.aksunai.ui;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.androidnerds.app.aksunai.R;
import org.androidnerds.app.aksunai.data.ServerDbAdapter;

public class ServerDetail extends Activity {

    private String mMode;
    private Long mId;
    private EditText mTitle;
    private EditText mAddress;
    private EditText mUsername;
    private EditText mNickname;
    private EditText mPassword;
    private EditText mPort;
    private EditText mRealName;
    private EditText mAutojoin;
    private CheckBox mAutoconnect;
    
    @Override
    public void onCreate(Bundle appState) {
        super.onCreate(appState);

        setContentView(R.layout.server_detail);
        setTitle(R.string.server_detail_title);

        Bundle extras = getIntent().getExtras();

        //the mode tells us if we are creating a new server or editing an existing.
        mMode = extras.getString("mode");

        Button confirm = (Button) findViewById(R.id.new_confirm);
        confirm.setOnClickListener(mConfirmListener);

        Button cancel = (Button) findViewById(R.id.btn_discard);
        cancel.setOnClickListener(mCancelListener);

        //if the mode is edit we should expect a server id.
        if (mMode.equals("edit")) {
            loadServerData(mId = extras.getLong("id"));
        }
    }

    private OnClickListener mConfirmListener = new OnClickListener() {
        public void onClick(View v) {
            //determine the mode type.
            if (mMode.equals("create")) {
                addServer();
            } else if (mMode.equals("edit")) {
                editServer();
            }
        }
    };

    private OnClickListener mCancelListener = new OnClickListener() {
        public void onClick(View v) {
            //user canceled just leave.
            finish();
        }
    };

    private void loadServerData(Long id) {
        mTitle = (EditText) findViewById(R.id.detail_title);
        mAddress = (EditText) findViewById(R.id.detail_address);
        mUsername = (EditText) findViewById(R.id.detail_username);
        mNickname = (EditText) findViewById(R.id.detail_nick);
        mPassword = (EditText) findViewById(R.id.detail_password);
        mPort = (EditText) findViewById(R.id.detail_port);
        mRealName = (EditText) findViewById(R.id.detail_real_name);
        mAutojoin = (EditText) findViewById(R.id.detail_autojoin);
        mAutoconnect = (CheckBox) findViewById(R.id.detail_autoconnect);
        
        ServerDbAdapter db = new ServerDbAdapter(this);
        Cursor c = db.getItem(id.longValue());

        while (c.moveToNext()) {
            mTitle.setText(c.getString(1));
            mAddress.setText(c.getString(2));
            mUsername.setText(c.getString(3));
            mNickname.setText(c.getString(4));
            mPassword.setText(c.getString(5));
            mPort.setText(c.getString(6));
            mRealName.setText(c.getString(7));
            if (c.getString(8) != null) {
            	mAutojoin.setText(c.getString(8));
            }
            
            if (c.getInt(9) == 1) {
            	mAutoconnect.setChecked(true);
            } else {
            	mAutoconnect.setChecked(false);
            }
        }

        db.release();
    }

    private boolean verifyUi() {
        mTitle = (EditText) findViewById(R.id.detail_title);
        mAddress = (EditText) findViewById(R.id.detail_address);
        mUsername = (EditText) findViewById(R.id.detail_username);
        mNickname = (EditText) findViewById(R.id.detail_nick);
        mPassword = (EditText) findViewById(R.id.detail_password);
        mPort = (EditText) findViewById(R.id.detail_port);
        mRealName = (EditText) findViewById(R.id.detail_real_name);
        mAutojoin = (EditText) findViewById(R.id.detail_autojoin);
        mAutoconnect = (CheckBox) findViewById(R.id.detail_autoconnect);
        
        if (mTitle.getText().toString().equals("") || mAddress.getText().toString().equals("") || mUsername.getText().toString().equals("") || mNickname.getText().toString().equals("")) {
            Toast.makeText(this, getString(R.string.required_fields), Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void addServer() {
        if (!verifyUi()) {
            return;
        }

        int connect = 0;
        
        if (mAutoconnect.isChecked()) {
        	connect = 1;
        }
        
        ServerDbAdapter db = new ServerDbAdapter(this);

        long id = db.addServer(mTitle.getText().toString(), mAddress.getText().toString(), mUsername.getText().toString(), mNickname.getText().toString(), mPassword.getText().toString(), mPort.getText().toString(), mRealName.getText().toString(), mAutojoin.getText().toString(), connect);

        db.close();

        if (id != -1) {
            finish();
        } else {
            Toast.makeText(this, "Error saving server.", Toast.LENGTH_LONG).show();
        }
    }

    private void editServer() {
        if (!verifyUi()) {
            return;
        }

        int connect = 0;
        
        if (mAutoconnect.isChecked()) {
        	connect = 1;
        }
        
        ServerDbAdapter db = new ServerDbAdapter(this);

        int result = db.updateServer(mId.intValue(), mTitle.getText().toString(), mAddress.getText().toString(), mUsername.getText().toString(), mNickname.getText().toString(), mPassword.getText().toString(), mPort.getText().toString(), mRealName.getText().toString(), mAutojoin.getText().toString(), connect);

        db.close();

        if (result != -1) {
            finish();
        } else {
            Toast.makeText(this, "Error saving server.", Toast.LENGTH_LONG).show();
        }
    }
}
