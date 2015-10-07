package com.easygame.w2.easygame;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.easygame.w2.utils.CriarToast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends Activity {
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(profile != null) {
            CarregarMenuPrincipal();
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        //setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               /* info.setText(
                        "Voce logou com o FaceBook: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );*/
                profile = Profile.getCurrentProfile();
                if(profile != null){

                    CriarToast.CriarToast(getApplicationContext(),"Olá " +profile.getFirstName());
                }
                CarregarMenuPrincipal();
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");

            }


            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
                e.printStackTrace();

            }
        });
    }
    public void CarregarMenuPrincipal(){

    startActivity(new Intent(getApplicationContext(), menuActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(profile != null){
            CarregarMenuPrincipal();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
