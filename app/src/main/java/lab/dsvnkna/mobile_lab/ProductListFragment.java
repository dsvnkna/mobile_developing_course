package lab.dsvnkna.mobile_lab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListFragment extends Fragment {

    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_view_product_list)
    protected RecyclerView recyclerView;

    @BindView(R.id.no_data_image)
    protected ImageView noDataImage;

    @BindView(R.id.no_data_text_info)
    protected TextView noDataTextInfo;

    @BindView(R.id.button_favourites)
    protected Button buttonFavourites;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, view);

        noDataImage.setVisibility(View.INVISIBLE);
        noDataTextInfo.setVisibility(View.INVISIBLE);
        swipeToRefreshContent();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        apiCall();
        buttonFavourites.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.button_favourites)
    protected void setButtonFavourites(View view) {
        ((MainActivity) Objects.requireNonNull(getActivity()))
                .setFragmentToContainer(new ProductFavouritesFragment());
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

        Call<ProductList> call = data.getProduct();

        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call,
                                   Response<ProductList> response) {
                if (response.body() != null) {
                    if (response.body() != null) {
                        generateRecordsList(response.body().getProducts());
                    }
                    noDataTextInfo.setVisibility(View.INVISIBLE);
                    noDataImage.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable throwable) {
                noDataImage.setVisibility(View.VISIBLE);
                noDataTextInfo.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), R.string.error,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void generateRecordsList(ArrayList<Product> productList) {
        ProductAdapter adapter = new ProductAdapter(productList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}