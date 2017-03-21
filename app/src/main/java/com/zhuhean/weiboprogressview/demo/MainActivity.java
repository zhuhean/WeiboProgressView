package com.zhuhean.weiboprogressview.demo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhuhean.weiboprogressview.WeiboProgressView;

public class MainActivity extends AppCompatActivity {

    private WeiboProgressView weiboProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weiboProgressView = (WeiboProgressView) findViewById(R.id.progress);
        weiboProgressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate();
            }
        });
    }

    private void animate() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int progress = (int) valueAnimator.getAnimatedValue();
                weiboProgressView.setProgress(progress);
            }
        });
        valueAnimator.start();
    }

}
