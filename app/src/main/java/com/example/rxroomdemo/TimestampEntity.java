package com.example.rxroomdemo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "timestampsTable")
public class TimestampEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    public Long id;

    @ColumnInfo(name = "timestamp")
    public String timestamp;

    public TimestampEntity(@NonNull Long id, String timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    @NonNull
    @Override
    public String toString() {
        return "id = " + id + ", timestamp = " + timestamp;
    }
}
