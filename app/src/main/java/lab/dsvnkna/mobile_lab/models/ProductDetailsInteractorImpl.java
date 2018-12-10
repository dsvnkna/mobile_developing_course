package lab.dsvnkna.mobile_lab.models;

import lab.dsvnkna.mobile_lab.SharedPreferenceStorage;
import lab.dsvnkna.mobile_lab.entities.Product;

public class ProductDetailsInteractorImpl implements MainInteractor.ProductDetailsInteractor {
    SharedPreferenceStorage storageManager;

    public ProductDetailsInteractorImpl(SharedPreferenceStorage storageManager) {
        this.storageManager = storageManager;
    }

    public void manageFavourites(Product imageItem,
                                 MainInteractor.ProductDetailsInteractor.
                                         OnFinishedListener onFinishedListener) {
        if (!storageManager.ifStorageContainsItem(imageItem.getName())) {
            storageManager.addToStorage(imageItem);
            onFinishedListener.onAdd();
        } else {
            storageManager.removeFromStorage(imageItem);
            onFinishedListener.onRemove();
        }
    }

    @Override
    public void checkFavourite(Product imageItem, MainInteractor.ProductDetailsInteractor.
            OnFinishedListener onFinishedListener) {
        if (storageManager.ifStorageContainsItem(imageItem.getName())) {
            onFinishedListener.favouriteResult(true);
        } else {
            onFinishedListener.favouriteResult(false);
        }
    }
}
