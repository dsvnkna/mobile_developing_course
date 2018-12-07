package lab.dsvnkna.mobile_lab;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    protected @BindView(R.id.toolbar)
    Toolbar toolbar;

    protected @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    protected @BindView(android.R.id.content)
    View currentAppView;

    protected @BindView(R.id.no_data_image)
    ImageView noDataImage;

    protected @BindView(R.id.no_data_text_info)
    TextView noDataTextInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        noDataImage.setVisibility(View.INVISIBLE);
        noDataTextInfo.setVisibility(View.INVISIBLE);

        swipeToRefreshContent();
    }

    @Override
    protected void onResume(){
        super.onResume();
        apiCall();
    }

    private void swipeToRefreshContent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiCall();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
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
                    noDataTextInfo.setVisibility(View.INVISIBLE);
                    noDataImage.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ProductResultList> call, Throwable throwable) {
                noDataImage.setVisibility(View.VISIBLE);
                noDataTextInfo.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, R.string.error,
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