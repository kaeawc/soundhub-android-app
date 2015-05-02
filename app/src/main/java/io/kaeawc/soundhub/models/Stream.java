package io.kaeawc.soundhub.models;

import android.support.annotation.Nullable;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

@Table("streams")
public class Stream extends Model {

    @Column("title")
    public String title;
    @Column("body")
    public String body;

    @Nullable
    public Long getId() {
        return this.id;
    }
}
