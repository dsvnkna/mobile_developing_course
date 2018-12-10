package lab.dsvnkna.mobile_lab.presenters;

import java.util.ArrayList;

import lab.dsvnkna.mobile_lab.ApplicationEx;
import lab.dsvnkna.mobile_lab.entities.Product;
import lab.dsvnkna.mobile_lab.entities.ProductList;
import lab.dsvnkna.mobile_lab.models.MainInteractor;
import lab.dsvnkna.mobile_lab.views.MainView;

public class ProductListPresenterImpl implements MainPresenter.ProductListPresenter,
        MainInteractor.ProductListInteractor.OnFinishedListener {

    private MainView.ListImagesView view;
    private MainInteractor.ProductListInteractor interactor;

    public ProductListPresenterImpl(MainView.ListImagesView view) {
        this.view = view;
        interactor = ApplicationEx.getInstance().getListInteractor();
    }


    @Override
    public void getData() {
        interactor.getData(this);
    }

    @Override
    public void onFinishedLoad(ArrayList<Product> imageItemList) {
        if (view != null) {
            view.setData(imageItemList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (view != null) {
            view.onResponseFailure(t);
        }
    }
}