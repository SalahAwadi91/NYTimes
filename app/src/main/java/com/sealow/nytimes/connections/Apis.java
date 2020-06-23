package com.sealow.nytimes.connections;


import com.sealow.nytimes.generic.MConstants;
import com.sealow.nytimes.models.HomeModel;
import com.sealow.nytimes.models.MainModel;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Apis {



    @GET("svc/mostpopular/v2/emailed/{daysBefore}"+ MConstants.API_KEY)
    Observable<MainModel> getMostPopular(@Path("daysBefore") String daysBefore);


}
