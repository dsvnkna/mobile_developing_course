package lab.dsvnkna.mobile_lab;

import android.app.Application;

import lab.dsvnkna.mobile_lab.models.MainInteractor;
import lab.dsvnkna.mobile_lab.models.ProductDetailsInteractorImpl;
import lab.dsvnkna.mobile_lab.models.ProductListInteractorImpl;
import lab.dsvnkna.mobile_lab.retrofit.GetProductsData;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {
    private static final String BASE_URL = "http://lcboapi.com/";
    private static ApplicationEx instance;
    private FragmentNavigation fragmentNavigation;
    private MainInteractor.ProductListInteractor listInteractor;
    private MainInteractor.ProductDetailsInteractor detailsInteractor;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setupItems();
    }

    public MainInteractor.ProductDetailsInteractor getDetailsInteractor() {
        return detailsInteractor;
    }

    private void setupItems() {
        final GetProductsData apiClient = createApiClient();
        SharedPreferenceStorage storageManager = new SharedPreferenceStorage(getApplicationContext());
        listInteractor = new ProductListInteractorImpl(apiClient);
        detailsInteractor = new ProductDetailsInteractorImpl(storageManager);
    }

    public static ApplicationEx getInstance() {
        return instance;
    }

    private GetProductsData createApiClient() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GetProductsData.class);
    }

    public void setFragmentManager(FragmentNavigation fragmentNavigation) {
        this.fragmentNavigation = fragmentNavigation;
    }

    public MainInteractor.ProductListInteractor getListInteractor() {
        return listInteractor;
    }

    public FragmentNavigation getFragmentManager() {
        return fragmentNavigation;
    }
}