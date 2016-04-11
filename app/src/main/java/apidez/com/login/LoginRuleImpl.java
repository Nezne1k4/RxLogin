package apidez.com.login;

/**
 * Created by nongdenchet on 3/7/16.
 */
public class LoginRuleImpl implements LoginRule {
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
