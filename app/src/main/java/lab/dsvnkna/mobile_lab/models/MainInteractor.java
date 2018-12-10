package lab.dsvnkna.mobile_lab.models;

import java.util.ArrayList;

import lab.dsvnkna.mobile_lab.entities.Product;
import lab.dsvnkna.mobile_lab.entities.ProductList;

public class MainInteractor {

    public interface ProductListInteractor {
        interface OnFinishedListener {
            void onFinishedLoad(ArrayList<Product> imageItems);

            void onFailure(Throwable t);
        }

        void getData(ProductListInteractor.OnFinishedListener
                             onFinishedListener);

    }

    public interface ProductDetailsInteractor {
        interface OnFinishedListener {
            void onAdd();

            void onRemove();

            void favouriteResult(boolean change);
        }

        void manageFavourites(Product imageItem, OnFinishedListener onFinishedListener);

        void checkFavourite(Product imageItem, MainInteractor.ProductDetailsInteractor.
                OnFinishedListener onFinishedListener);
    }
}
