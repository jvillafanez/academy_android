package es.academy.solidgear.surveyx.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AppEventsLogger;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;

import es.academy.solidgear.surveyx.R;

public class SocialNetworkActivity extends BaseActivity implements View.OnClickListener {

    private Button mFinishButton;
    private Button mFacebookButton;
    private UiLifecycleHelper uiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_network);
        initToolbar();

        mFinishButton = (Button) findViewById(R.id.finishButton);
        mFinishButton.setOnClickListener(this);

        mFacebookButton =  (Button) findViewById(R.id.facebookButton);
        mFacebookButton.setOnClickListener(this);

        uiHelper = new UiLifecycleHelper(this, null);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        if (view == mFinishButton) {
            finish();
        }

        if (view == mFacebookButton) {
            if (FacebookDialog.canPresentShareDialog(getApplicationContext(),
                    FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
                // Publish the post using the Share Dialog
                FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
                        .setLink("http://solidgear.es/")
                        .setName(getResources().getString(R.string.app_name))
                        .setDescription(getResources().getString(R.string.social_networks_facebook_post))
                        .setApplicationName(getResources().getString(R.string.app_name))
                        .build();
                uiHelper.trackPendingDialogCall(shareDialog.present());

            } else {
                Toast.makeText(this, R.string.social_networks_facebook_error, Toast.LENGTH_SHORT).show();
                // Fallback. For example, publish the post using the Feed Dialog
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
            @Override
            public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
                Log.e("Activity", String.format("Error: %s", error.toString()));
            }

            @Override
            public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
                Log.i("Activity", "Success!");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        uiHelper.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }
}
