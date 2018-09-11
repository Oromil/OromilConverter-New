package com.kilograpp.oromilconverter.data.network;

import com.kilograpp.oromilconverter.data.network.entities.Response;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Oromil on 13.06.2017.
 */

public interface RequestCourses {

    @GET("/daily_json.js")
    Observable<Response> requestCourses();
}
