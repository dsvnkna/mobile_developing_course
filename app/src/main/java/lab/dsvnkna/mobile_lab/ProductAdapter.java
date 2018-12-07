package lab.dsvnkna.mobile_lab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Product> productArrayList;

    public ProductAdapter(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_view, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.tagsText.setText(productArrayList.get(position).getTags());
        holder.producerText.setText(productArrayList.get(position).getProducerName());
        holder.servingSuggestionText.setText(productArrayList.get(position).getServingSuggestion());
        Picasso.with(holder.itemView.getContext())
                .load(productArrayList.get(position).getImageUrl())
                .fit().centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tags_text)
        protected TextView tagsText;

        @BindView(R.id.producer_text)
        protected TextView producerText;

        @BindView(R.id.serving_suggestion_text)
        protected TextView servingSuggestionText;

        @BindView(R.id.image)
        protected ImageView imageView;

        ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}