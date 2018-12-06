package lab.dsvnkna.mobile_lab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume(){
        super.onResume();
        apiCall();
    }

    private void apiCall() {
        GetProductsData data = RetrofitInstance
                .getRetrofitInstance().create(GetProductsData.class);

        Call<ProductResultList> call = data.getProduct();

        call.enqueue(new Callback<ProductResultList>() {
            @Override
            public void onResponse(Call<ProductResultList> call,
                                   Response<ProductResultList> response) {
                if (response.body() != null) {
                    generateRecordsList(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductResultList> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Error",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void generateRecordsList(ArrayList<Product> productList) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_product_list);
        ProductAdapter adapter = new ProductAdapter(productList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}