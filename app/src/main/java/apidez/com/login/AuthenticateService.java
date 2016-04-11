package apidez.com.login;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by nongdenchet on 4/11/16.
 */
public class AuthenticateService {
    private SessionService mSessionService = new SessionService();
    private PublishSubject<Boolean> mLoginEvent = PublishSubject.create();
    private PublishSubject<Boolean> mLoginEventResult = PublishSubject.create();

    public void setLoginResult(boolean result) {
        mLoginEventResult.onNext(result);
    }

    public Observable<Boolean> loginEvent() {
        return mLoginEvent.asObservable();
    }

    protected <T> Observable.Transformer<T, T> checkLogin() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(final Observable<T> observable) {
                if (!mSessionService.isLogin()) {
                    mLoginEvent.onNext(true);
                    return waitForLogin(observable);
                }
                return observable;
            }
        };
    }

    private <T> Observable<T> waitForLogin(final Observable<T> observable) {
        return mLoginEventResult.asObservable()
                .take(1)
                .flatMap(new Func1<Boolean, Observable<T>>() {
                    @Override
                    public Observable<T> call(Boolean success) {
                        mSessionService.setLogin(success);
                        return success ? observable : Observable.<T>empty();
                    }
                });
    }
}
