package com.raghulrider.androidworkshop;

        import android.content.Intent;
        import android.os.Handler;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ProgressBar;

public class Welcome extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    ProgressBar pb;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        pb = findViewById(R.id.progressbar);
        pb.setVisibility(View.VISIBLE);
        actionBar = getSupportActionBar();
        actionBar.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(Welcome.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
