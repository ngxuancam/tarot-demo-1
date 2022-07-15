package com.fptedu.soc.tarottemp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Tarot {

    @GET("/")
    static Call<List> getPosts() {
        return null;
    }
}
