package apidez.com.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import rx.functions.Action1;

/**
 * Created by nongdenchet on 4/11/16.
 */
public class AuthenticateHelper {
    private static final int LOGIN_REQUEST = 1000;
    private AuthenticateService authenticateService;

    public AuthenticateHelper(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    public void bindLogin(final AppCompatActivity appCompatActivity,
                          final String message, final Class<?> loginActivityClass) {
        authenticateService.loginEvent()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        showLoginActivity(appCompatActivity, message, loginActivityClass);
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode) {
        if (requestCode == LOGIN_REQUEST) {
            authenticateService.setLoginResult(resultCode == Activity.RESULT_OK);
        }
    }

    private void showLoginActivity(AppCompatActivity activity, String message, Class<?> loginActivityClass) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity, loginActivityClass);
        activity.startActivityForResult(intent, LOGIN_REQUEST);
    }
}
