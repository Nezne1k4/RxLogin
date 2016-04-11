package apidez.com.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by nongdenchet on 3/7/16.
 */
public class BaseActivity extends AppCompatActivity {
    private BehaviorSubject<BaseActivity> mDestroyEvent = BehaviorSubject.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected Observable<BaseActivity> destroyEvent() {
        return mDestroyEvent.asObservable();
    }

    protected void showToast(String value) {
        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        mDestroyEvent.onNext(this);
        super.onDestroy();
    }
}
