package lab.dsvnkna.mobile_lab.views;

import java.util.ArrayList;

import lab.dsvnkna.mobile_lab.entities.Product;

public interface MainView {

    interface ListImagesView {
        void setData(ArrayList<Product> imageItems);

        void onResponseFailure(Throwable throwable);
    }

    interface DetailsView {
        void addToFavourites();

        void removeFromFavourites();

        void editFavourite(boolean favourite);

        void initializeItems(Product imageItem);
    }
}