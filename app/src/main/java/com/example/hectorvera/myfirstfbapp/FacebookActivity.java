package com.example.hectorvera.myfirstfbapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class FacebookActivity extends AppCompatActivity {
    MainActivity main = new MainActivity();
    private TextView fbUsername;
    private TextView fbuiUser;
    private ProfilePictureView fbimage;
    private Button bFbDialog;
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_facebook);

        if (Profile.getCurrentProfile() != null) {
            fbUsername = (TextView) findViewById(R.id.detailFB);
            fbuiUser = (TextView) findViewById(R.id.fbuiUser);
            fbimage = (ProfilePictureView) findViewById(R.id.fbImageView);
            fbUsername.setText(Profile.getCurrentProfile().getName());
            fbimage.setProfileId(Profile.getCurrentProfile().getId());
//            LoginButton loginButton = (LoginButton) findViewById(R.id.facebook_button);
//            loginButton.setReadPermissions("email", "public_profile");
            fbuiUser.setText(Profile.getCurrentProfile().getId());
            bFbDialog = (Button) findViewById(R.id.bFbDialog);
            shareDialog = new ShareDialog(this);
            bFbDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ShareDialog .canShow(ShareLinkContent.class)) {
                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle("Hello Facebook")
                                .setContentDescription(
                                        "The 'Hello Facebook' sample  showcases simple Facebook integration")
                                .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                                .build();

                        shareDialog.show(linkContent);
                    }
                }
            });
        }
    }

}
