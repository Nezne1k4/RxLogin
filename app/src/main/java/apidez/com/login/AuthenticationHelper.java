package apidez.com.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import rx.functions.Action1;

/**
 * Created by nongdenchet on 4/11/16.
 */
public class AuthenticationHelper {
    private static final int LOGIN_REQUEST = 1000;
    private AuthenticateService authenticateService;

    public AuthenticationHelper(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    public void bindLogin(final AppCompatActivity appCompatActivity) {
        authenticateService.loginEvent()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        showLoginActivity(appCompatActivity);
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode) {
        if (requestCode == LOGIN_REQUEST) {
            authenticateService.setLoginResult(resultCode == Activity.RESULT_OK);
        }
    }

    private void showLoginActivity(AppCompatActivity activity) {
        Toast.makeText(activity, "Login required!!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent, LOGIN_REQUEST);
    }
}
