package lab.dsvnkna.mobile_lab.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import lab.dsvnkna.mobile_lab.entities.Product;
import lab.dsvnkna.mobile_lab.adapters.ProductAdapter;
import lab.dsvnkna.mobile_lab.R;

import static lab.dsvnkna.mobile_lab.activities.MainActivity.FAVOURITES;

public class ProductFavouritesFragment extends Fragment {
    private ArrayList<Product> mItemsList = new ArrayList<>();

    @BindView(R.id.recycler_view_product_list)
    protected RecyclerView recyclerView;

    @BindView(R.id.no_data_image)
    protected ImageView noDataImage;

    @BindView(R.id.no_data_text_info)
    protected TextView noDataTextInfo;

    @BindView(R.id.button_favourites)
    protected Button buttonFavourites;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, view);
        getFavouriteItems();
        setRecyclerAdapter();

        noDataImage.setVisibility(View.INVISIBLE);
        noDataTextInfo.setVisibility(View.INVISIBLE);
        buttonFavourites.setVisibility(View.INVISIBLE);
        return view;
    }

    private void getFavouriteItems() {
        SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity())
                .getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        Map<String, ?> map = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            Product product = new Gson().fromJson(entry.getValue().toString(), Product.class);
            mItemsList.add(product);
        }
    }

    private void setRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ProductAdapter productAdapter = new ProductAdapter(mItemsList);
        recyclerView.setAdapter(productAdapter);
    }
}
