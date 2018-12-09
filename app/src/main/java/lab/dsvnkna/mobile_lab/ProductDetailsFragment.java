package lab.dsvnkna.mobile_lab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static lab.dsvnkna.mobile_lab.MainActivity.DETAILS;
import static lab.dsvnkna.mobile_lab.MainActivity.FAVOURITES;

public class ProductDetailsFragment extends Fragment {
    private static final int IMAGE_HEIGHT = 1800;
    private SharedPreferences sharedPrefFavourite;
    private boolean isImageFullScreen;
    private Product product;
    private Bundle bundle;

    @BindView(R.id.image_details)
    protected ImageView imageView;

    @BindView(R.id.name)
    protected TextView name;

    @BindView(R.id.primary_category)
    protected TextView primaryCategory;

    @BindView(R.id.tags)
    protected TextView tags;

    @BindView(R.id.serving_suggestion)
    protected TextView servingSuggestion;

    @BindView(R.id.add_favourite_button)
    protected FloatingActionButton addToFavouritesButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detalies, container, false);
        ButterKnife.bind(this, view);

        bundle = this.getArguments();
        getFragmentData();
        setProductData();
        checkFavourite();
        return view;
    }

    @OnClick(R.id.add_favourite_button)
    protected void onAddFavouriteButtonClick(View view) {
        editFavourites();
    }

    @OnClick(R.id.image_details)
    protected void onImageClick(View view) {
        resizeImage();
    }

    private void getFragmentData() {
        if (bundle != null) {
            product = new Gson().fromJson(bundle.getString(DETAILS), Product.class);
        }
    }

    private void setProductData() {
        Picasso.with(getContext()).load(product.getImageUrl()).into(imageView);
        name.setText(product.getName());
        primaryCategory.setText(product.getPrimaryCategory());
        tags.setText(String.valueOf(product.getTags()));
        servingSuggestion.setText(product.getServingSuggestion());
        sharedPrefFavourite = Objects.requireNonNull(getActivity())
                .getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
    }

    private void checkFavourite() {
        if (sharedPrefFavourite.contains(product.getName()))
            addToFavouritesButton.setImageResource(R.drawable.ic_favorite_white_24dp);
    }

    private void editFavourites() {
        if (!sharedPrefFavourite.contains(product.getName())) {
            addToFavouritesButton.setImageResource(R.drawable.ic_favorite_white_24dp);
            sharedPrefFavourite.edit().putString(product.getName(),
                    new Gson().toJson(product)).apply();
        } else {
            addToFavouritesButton.setImageResource(R.drawable.ic_favorite_border_white_24dp);
            sharedPrefFavourite.edit().remove(product.getName()).apply();
        }
    }

    private void resizeImage() {
        if (isImageFullScreen) {
            isImageFullScreen = false;
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, IMAGE_HEIGHT));
        } else {
            isImageFullScreen = true;
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams
                    .MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
}