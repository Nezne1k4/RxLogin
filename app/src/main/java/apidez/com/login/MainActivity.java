package apidez.com.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    private AuthenticationHelper authenticatehelper;
    private MyViewModel myViewModel = new MyViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        bindViewModel();
    }

    private void bindViewModel() {
        authenticatehelper = new AuthenticationHelper(myViewModel.authenticateService);
        authenticatehelper.bindLogin(this);
    }

    private void setUpView() {
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribeTask();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        authenticatehelper.onActivityResult(requestCode, resultCode);
    }

    private void subscribeTask() {
        myViewModel.sample()
                .takeUntil(destroyEvent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String response) {
                        showToast(response);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showToast(throwable.getMessage());
                    }
                });
    }
}
