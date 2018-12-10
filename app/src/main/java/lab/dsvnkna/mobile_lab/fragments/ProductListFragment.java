package lab.dsvnkna.mobile_lab.fragments;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lab.dsvnkna.mobile_lab.ApplicationEx;
import lab.dsvnkna.mobile_lab.FragmentNavigation;
import lab.dsvnkna.mobile_lab.R;
import lab.dsvnkna.mobile_lab.activities.MainActivity;
import lab.dsvnkna.mobile_lab.adapters.ProductAdapter;
import lab.dsvnkna.mobile_lab.entities.Product;
import lab.dsvnkna.mobile_lab.entities.ProductList;
import lab.dsvnkna.mobile_lab.presenters.MainPresenter;
import lab.dsvnkna.mobile_lab.presenters.ProductListPresenterImpl;
import lab.dsvnkna.mobile_lab.views.MainView;

public class ProductListFragment extends Fragment implements MainView.ListImagesView {
    private MainPresenter.ProductListPresenter listPresenter;
    private ProductAdapter productAdapter;

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
        listPresenter = new ProductListPresenterImpl(this);
        initializeAdapter();
//        swipeToRefreshContent();
        return view;
    }

    private void initializeAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setData(ArrayList<Product> imageItemsList) {
        productAdapter = new ProductAdapter(imageItemsList);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), R.string.error
                + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        buttonFavourites.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.button_favourites)
    protected void setButtonFavourites(View view) {
        FragmentNavigation manageFragments = new FragmentNavigation(((MainActivity) getActivity()));
        ApplicationEx.getInstance().setFragmentManager(manageFragments);
        ApplicationEx.getInstance().getFragmentManager().setFragment(new ProductFavouritesFragment());
    }
}