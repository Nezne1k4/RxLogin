package apidez.com.login;

import rx.Observable;

/**
 * Created by nongdenchet on 4/11/16.
 */
public class MyViewModel {
    public AuthenticateService authenticateService;

    public MyViewModel() {
        this.authenticateService = new AuthenticateService(new LoginRuleImpl());
    }

    public Observable<String> sample() {
        return Observable.just("Hello World")
                .compose(authenticateService.<String>checkLogin());
    }
}
