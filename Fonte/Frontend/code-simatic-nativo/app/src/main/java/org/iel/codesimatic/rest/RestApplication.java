package org.iel.codesimatic.rest;

import retrofit2.Retrofit;

public class RestApplication {

   private void init(){
      Retrofit retrofit = new Retrofit.Builder()
               .baseUrl("http://192.168.0.119:8080/")
               .build();
    }

}
