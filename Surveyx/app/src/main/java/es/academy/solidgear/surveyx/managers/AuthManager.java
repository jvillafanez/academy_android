package es.academy.solidgear.surveyx.managers;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**

 * Created by Saul on 2/3/15.
 */
public class AuthManager {

    private static AuthManager authManager = new AuthManager( );
    private static Activity mActivity;
    public void AuthManager(){

    }
    public static AuthManager getInstance(Activity activity ) {

        mActivity = activity;
        return authManager;
    }

    public void setToken(Context ctx, String token){
        SharedPreferences sp = ctx.getSharedPreferences("Preferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.putString("Token",token);
        edit.commit();
        }
    public String getToken(){
        SharedPreferences prefs = mActivity.getSharedPreferences("preferences", Context.MODE_PRIVATE);

        String token = prefs.getString("token", null);
        return token;
    }

}
