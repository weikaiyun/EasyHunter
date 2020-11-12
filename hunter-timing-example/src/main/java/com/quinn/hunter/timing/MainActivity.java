package com.quinn.hunter.timing;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.quinn.hunter.timing.black.NetWorkManager;

/**
 * Created by Quinn on 24/03/2017.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataSource.getInstance().getUserName();
        DataSource.getInstance().saveHugeFileToDisk();
        DataSource.getInstance().modifyAndMoveFile();
        NetWorkManager.getInstance().uploadHugeFile();
        NativeService.getInstance().loadSoFile();
        findViewById(R.id.dump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App)getApplication()).getCustomBlockManager().dump();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
