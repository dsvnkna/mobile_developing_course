package lab.dsvnkna.mobile_lab.models;

import java.util.Objects;

import lab.dsvnkna.mobile_lab.entities.ProductList;
import lab.dsvnkna.mobile_lab.retrofit.GetProductsData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListInteractorImpl implements MainInteractor.ProductListInteractor {

    private GetProductsData service;

    public ProductListInteractorImpl(GetProductsData service) {
        this.service = service;
    }

    public void getData(final MainInteractor.ProductListInteractor.OnFinishedListener onFinishedListener) {
        Call<ProductList> call = service.getProduct();
        call.clone().enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                if (response.body() != null) {
                    onFinishedListener
                            .onFinishedLoad(Objects.requireNonNull(response.body()).getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }
}
