package com.luismiguelcortinez.apilocal;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("planetary/apod")
    Call <ResponseApi> getApod();
}
