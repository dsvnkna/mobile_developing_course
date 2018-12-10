package lab.dsvnkna.mobile_lab.presenters;

public class MainPresenter {

    public interface ProductListPresenter {
        void getData();
    }

    public interface ProductDetailsPresenter {
        void checkFavourite();

        void getData();
    }
}