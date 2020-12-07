package com.example.rxroomdemo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {TimestampEntity.class},
        version = 1,
        exportSchema = false
)
abstract class AppDatabase extends RoomDatabase {
    abstract TimestampsDao coursesDao();
}
