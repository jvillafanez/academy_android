package es.academy.solidgear.surveyx.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Saul on 2/3/15.
 */
public class AuthManager {

    public void AuthManager(){

    }
    public void setToken(Context ctx, String token){
        SharedPreferences sp = ctx.getSharedPreferences("Preferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.putString("Token",token);
        edit.commit();
    }
}
