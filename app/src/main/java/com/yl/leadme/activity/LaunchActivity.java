package com.yl.leadme.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;

import com.yl.leadme.R;
import com.yl.leadme.view.PageWidget;

import static com.yl.leadme.view.PageWidget.view;

public class LaunchActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        PageWidget pageWidget = new PageWidget(this,this,view);//怎样在activity中获取Page Widget对象？或者怎样在PageWidget中获取view对象？

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        pageWidget.SetScreen(width, height);

        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_launch);
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.mipmap.launch);

        Bitmap foreImage = Bitmap.createScaledBitmap(bm1, width, height, false);
        Bitmap bgImage = Bitmap.createScaledBitmap(bm2, width, height, false);

        pageWidget.setBgImage(bgImage);
        pageWidget.setForeImage(foreImage);

        setContentView(pageWidget);

        super.onCreate(savedInstanceState);
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchPt.x = event.getX();
            touchPt.y = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            lastTouchX = (int) touchPt.x;
            touchPt.x = event.getX();
            touchPt.y = event.getY();

            view.postInvalidate();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            int dx, dy;

            dy = 0;

            //向右滑动
            if (lastTouchX < touchPt.x) {
                dx = foreImage.getWidth() - (int) touchPt.x + 30;
            } else {
                //向左滑动
                dx = -(int) touchPt.x - foreImage.getWidth();
            }

            mScroller.startScroll((int) touchPt.x, (int) touchPt.y, dx, dy, 1000);
            view.postInvalidate();
        }

        //必须为true，否则无法获取ACTION_MOVE及ACTION_UP事件
        Toast.makeText(mactivity, "點擊翻頁", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,LoginActivity.class));

        return true;
    }
*/

}
