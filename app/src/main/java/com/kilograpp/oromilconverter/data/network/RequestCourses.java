package com.kilograpp.oromilconverter.data.network;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Oromil on 13.06.2017.
 */

public interface RequestCourses {

    @GET("/latest?base=RUB")
    Observable<Courses> requestCourses();

    @GET("http://api.fixer.io/{date}??base=RUB")
    Observable<Courses> requestLastCourses(@Path("date") String lastDate);
}
