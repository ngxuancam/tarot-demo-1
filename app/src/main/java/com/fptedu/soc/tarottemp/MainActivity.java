package com.fptedu.soc.tarottemp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ImageView fCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        View headerView = findViewById(R.id.header);
        headerView.bringToFront();
        fCard = (ImageView) findViewById(R.id.front_card);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.start();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-tarot-daily.socit.xyz/")
                .build();
        LottieAnimationView v = (LottieAnimationView) findViewById(R.id.animation_view);
        scaleView(v, 1F , 0.9F);
        fCard.setClickable(true);
        fCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fCard.setClickable(false);
                scaleView(fCard, 1F , 0.5F);
                Picasso.get()
                        .load("https://divineapi.com/admin/uploads/daily_tarot/139_2.jpg").placeholder(R.drawable.back_card).resize(320, 550).into(fCard);
                flipFront(fCard);
                fCard.bringToFront();
                headerView.bringToFront();
            }
        });
    }

    public void scaleView(View v, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                startScale, endScale, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(1000);
        v.startAnimation(anim);
    }

    public void flipFront(ImageView v) {
        final long DELAY_ANIMATE = 300;
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        float distance = v.getCameraDistance() * (scale + (scale / 3));
        v.setCameraDistance(distance);
        v.animate().withLayer()
                .rotationY(90)
                .setDuration(DELAY_ANIMATE)
                .withEndAction(
                        new Runnable() {
                            @Override public void run() {
                                scaleView(v, 0.5F , 1.5F);
                                v.setRotationY(-90);
                                v.animate().withLayer()
                                        .rotationY(0)
                                        .setDuration(DELAY_ANIMATE)
                                        .withEndAction(
                                                new Runnable() {
                                                    @Override public void run() {

                                                        TextView tv = (TextView) findViewById(R.id.txt_desc);
                                                        tv.setText("The card indicates that your relationship is definitely going through a rough patch and there are issues between your partner and you that is preventing you to get close to your partner. You should learn from your mistakes from your past and work on them. ");
                                                        tv.animate().setDuration(DELAY_ANIMATE * 2).translationY(-1450);
                                                    }
                                                }
                                        )
                                        .start();
                            }
                        }
                ).start();
    }

}