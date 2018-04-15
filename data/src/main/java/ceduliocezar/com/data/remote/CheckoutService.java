package ceduliocezar.com.data.remote;


import ceduliocezar.com.data.repository.cart.CartTO;
import io.reactivex.Completable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CheckoutService {

    @POST("post")
    Completable checkout(@Body CartTO cartTO);
}
