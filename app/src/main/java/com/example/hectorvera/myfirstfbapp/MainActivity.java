package com.example.hectorvera.myfirstfbapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "yeAijiIeusF3V9hKRMngJIxFJ";
    private static final String TWITTER_SECRET = "JKEvztPeMjDwJxZUCAWhZdZcm3fsM626Y0jr6V3F3UDuLZZAds";

    CallbackManager callbackManager;
    private TwitterLoginButton twbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);

        FacebookCallBack();

        //TwitterCallBack();


    }
    /*
    private void TwitterCallBack() {
        twbutton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        twbutton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Toast.makeText(getApplicationContext(), "Twitter Loggin Succes with ", Toast.LENGTH_LONG).show();
                Intent intTw = new Intent(getApplicationContext(), TwitterActivity.class);
                startActivity(intTw);
                twbutton.setText("Log out");

            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(), "Twitter Loggin Fail", Toast.LENGTH_LONG).show();

            }
        });
    }*/

    private void FacebookCallBack() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Intent intentFB = new Intent(getApplicationContext(),
                                com.example.hectorvera.myfirstfbapp.FacebookActivity.class);

                        startActivity(intentFB);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //twbutton.onActivityResult(requestCode, resultCode, data);
    }
}
