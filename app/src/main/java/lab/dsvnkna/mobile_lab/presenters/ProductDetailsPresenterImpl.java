package lab.dsvnkna.mobile_lab.presenters;

import java.util.ArrayList;

import lab.dsvnkna.mobile_lab.ApplicationEx;
import lab.dsvnkna.mobile_lab.entities.Product;
import lab.dsvnkna.mobile_lab.entities.ProductList;
import lab.dsvnkna.mobile_lab.models.MainInteractor;
import lab.dsvnkna.mobile_lab.views.MainView;

public class ProductDetailsPresenterImpl implements MainPresenter.ProductDetailsPresenter,
        MainInteractor.ProductDetailsInteractor.OnFinishedListener {

    private MainInteractor.ProductDetailsInteractor interactor;
    private MainView.DetailsView view;
    public Product imageItem;

    public ProductDetailsPresenterImpl(MainView.DetailsView view) {
        this.view = view;
        interactor = ApplicationEx.getInstance().getDetailsInteractor();
    }

    public void getData() {
        imageItem = ApplicationEx.getInstance().getFragmentManager().getArguments();
        view.initializeItems(imageItem);
        interactor.checkFavourite(imageItem, this);
    }

    public void checkFavourite() {
        interactor.manageFavourites(imageItem, this);
    }

    public void onAdd() {
        view.addToFavourites();
    }

    public void onRemove() {
        view.removeFromFavourites();
    }

    public void favouriteResult(boolean favourite) {
        view.editFavourite(favourite);
    }

}