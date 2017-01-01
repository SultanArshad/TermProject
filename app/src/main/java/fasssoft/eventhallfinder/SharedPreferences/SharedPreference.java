package fasssoft.eventhallfinder.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sultan on 1/2/2017.
 */

public class SharedPreference {

boolean check;
    SharedPreferences pref;
    private  String email="not set",pass="not set";
    SharedPreferences.Editor editor;
    Context context;

    public SharedPreference(Context context) {
        this.context=context;
        pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
         editor = pref.edit();

    }

    public void setCheck(boolean check) {
        this.check = check;
        editor.putBoolean("key_check", check);
        editor.commit();
    }

    public boolean isCheck() {
        Boolean ch;
        ch= pref.getBoolean("key_check",true);
        return ch;
    }

    public void setPass(String pass) {
        this.pass = pass;
        editor.putString("key_pass", pass);
        editor.commit();
    }

    public void setEmail(String email) {
        this.email = email;
        editor.putString("key_email", email);
        editor.commit();
    }

    public String getEmail() {
        String em;
       em= pref.getString("key_email",null);
        return em;
    }


    public String getPass() {
        String pa;
        pa= pref.getString("key_pass",null);
        return pa;

    }

public void destroyPref(){
    editor.clear();
    editor.commit();
}


}
