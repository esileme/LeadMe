package com.yl.leadme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.yl.leadme.R;
import com.yl.leadme.utils.MyUtils;

import java.io.IOException;
import java.util.Calendar;

public class PublishActivity extends AppCompatActivity {

    private ImageView mImageViewSelect;
    private byte[] mImageBytes = null;
    private ProgressBar mProgerss;
    private String currentTime;//获取当前时间


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_publish);

        //状态栏
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.publish));

        mImageViewSelect = (ImageView) findViewById(R.id.imageview_select_publish);
        mProgerss = (ProgressBar) findViewById(R.id.mProgess);
        final EditText mDiscriptionEdit = (EditText) findViewById(R.id.edittext_discription_publish);
        final EditText mTitleEdit = (EditText) findViewById(R.id.edittext_title_publish);
        //final EditText mPriceEdit = (EditText) findViewById(R.id.edittext_price_publish);
        final TextView mCurrentTime = (TextView) findViewById(R.id.current_time);
        //currentTime = String.valueOf(SystemClock.currentThreadTimeMillis());
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        String year= String.valueOf(calendar.get(Calendar.YEAR));
        String month= String.valueOf(calendar.get(Calendar.MONTH));
        String day= String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String hour= String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute= String.valueOf(calendar.get(Calendar.MINUTE));
        String second= String.valueOf(calendar.get(Calendar.SECOND));
        currentTime = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
        mCurrentTime.setText(currentTime);

        Button mButtonSelect = (Button) findViewById(R.id.button_select_publish);

        //选择照片按钮
        mButtonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 42);
            }
        });

        //提交按钮
        findViewById(R.id.button_submit_publish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(mTitleEdit.getText().toString())) {
                    Toast.makeText(PublishActivity.this, "请输入标题", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(mDiscriptionEdit.getText().toString())) {
                    Toast.makeText(PublishActivity.this, "请输入描述", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*if ("".equals(currentTime)) {
                    Toast.makeText(PublishActivity.this, "请输入时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mImageBytes == null) {
                    Toast.makeText(PublishActivity.this, "请选择一张照片", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                mProgerss.setVisibility(View.VISIBLE);
                AVObject product = new AVObject("Product");
                product.put("title", mTitleEdit.getText().toString());
                product.put("description", mDiscriptionEdit.getText().toString());
                product.put("time1", currentTime);
                product.put("owner", AVUser.getCurrentUser());
                product.put("image", new AVFile("productPic", mImageBytes));
                product.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            mProgerss.setVisibility(View.GONE);
                            PublishActivity.this.finish();
                        } else {
                            mProgerss.setVisibility(View.GONE);
                            Toast.makeText(PublishActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    /**
     *判断是否图片上传完成
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42 && resultCode == RESULT_OK) {
            try {
                mImageViewSelect.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData()));
                mImageBytes = MyUtils.getBytes(getContentResolver().openInputStream(data.getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
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


}
