package com.example.rxroomdemo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
interface TimestampsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertTimestamps(TimestampEntity timestamp);

    @Query("SELECT * FROM timestampsTable")
    Single<List<TimestampEntity>> getTimestamps();
}
