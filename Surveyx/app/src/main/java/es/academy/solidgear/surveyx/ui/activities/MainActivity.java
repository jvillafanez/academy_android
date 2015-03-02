package es.academy.solidgear.surveyx.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import es.academy.solidgear.surveyx.R;
import es.academy.solidgear.surveyx.managers.AuthManager;


public class MainActivity extends Activity {

    AuthManager authManager= new AuthManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AuthManager auth= AuthManager.getInstance(this);
        String mToken = auth.getToken();

        if (mToken== null ) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SurveyListActivity.class);
            startActivity(intent);
        }

        finish();
    }

}
