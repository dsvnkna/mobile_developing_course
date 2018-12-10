package lab.dsvnkna.mobile_lab.retrofit;


import lab.dsvnkna.mobile_lab.entities.ProductList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetProductsData {
    @GET("products?access_key=MDpmMThmYjVmYS1mOTMwLTExZTgtYmEzNC1hYjM2NDk4NGM2OGI6am9BU0pQZ3B2SG1uQXBlNUVzZVMwZWNsS3I1bnhSdVdmR2ox")
    Call<ProductList> getProduct();
}
