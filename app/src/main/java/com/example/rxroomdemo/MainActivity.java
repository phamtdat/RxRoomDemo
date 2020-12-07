package com.example.rxroomdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "APP_DATABASE").build();

        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long now = System.currentTimeMillis();
                final TimestampEntity entity = new TimestampEntity(now, now.toString());
                db.coursesDao().insertTimestamps(entity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                Log.d("INSERT", "inserted " + entity.toString());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });

        findViewById(R.id.btn_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.coursesDao().getTimestamps()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new SingleObserver<List<TimestampEntity>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<TimestampEntity> timestampEntities) {
                                for (int i = 0; i < timestampEntities.size(); i++) {
                                    Log.d("Timestamp_"+i, timestampEntities.get(i).toString());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });
    }
}