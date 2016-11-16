package com.yl.leadme.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.yl.leadme.R;
import com.yl.leadme.utils.MyUtils;

import cn.leancloud.chatkit.LCChatKit;

import static android.R.attr.id;

public class LoginActivity extends AppCompatActivity {


    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mUsernameLoginButton;
    private Button mUsernameRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //testLink();
        initUI();
        MyUtils.initAutoComplete("history",mUsernameView,this);
        initData();


    }

    private void initUI() {

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);

        mUsernameLoginButton = (Button) findViewById(R.id.username_login_button);

        mUsernameRegisterButton = (Button) findViewById(R.id.username_register_button);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);


    }

    private void initData() {

        //判断当前用户是否正确
        if (AVUser.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            LoginActivity.this.finish();
        }

        //设置菜单栏的返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.login));

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mUsernameLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.saveHistory("history",mUsernameView,LoginActivity.this);
                attemptLogin();

            }
        });

        mUsernameRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                LoginActivity.this.finish();
            }
        });

    }

    /**
     * 测试 SDK 是否正常工作的代码
     */
    private void testLink() {

        AVObject testObject = new AVObject("TestObject");
        testObject.put("words", "Hello World!");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Log.d("saved", "success!");
                }
            }
        });
    }

    /**
     * 登陆后台认证
     */
    private void attemptLogin() {
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        final String username = mUsernameView.getText().toString();
        final String password = mPasswordView.getText().toString();

        //保存用户名
        SharedPreferences sp = getSharedPreferences("userName", MODE_PRIVATE);
        sp.edit().putString("userName",username).commit();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !MyUtils.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);

            AVUser.logInInBackground(username, password, new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    if (e == null) {

                        if(username==null){
                            SharedPreferences sp1 = getSharedPreferences("userName", MODE_PRIVATE);
                            sp1.getString("userName","null");
                        }

                        LoginActivity.this.finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        //传递
                        LCChatKit.getInstance().open(username, new AVIMClientCallback() {
                            @Override
                            public void done(AVIMClient avimClient, AVIMException e) {
                                if (e == null) {
                                } else {
                                }
                            }
                        });
                    } else {
                        showProgress(false);
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    /**
     * 显示进度条
     *
     * @param show
     */
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }


    /**
     * 对话登陆的后台操作
     */
    public void onLoginClick() {
        String clientId = mUsernameView.getText().toString();
        if (TextUtils.isEmpty(clientId.trim())) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        LCChatKit.getInstance().open(clientId, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
                    //finish();
                    //Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    /*Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);*/
                } else {
                    Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
