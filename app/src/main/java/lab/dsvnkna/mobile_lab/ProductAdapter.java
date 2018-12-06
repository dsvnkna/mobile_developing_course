package lab.dsvnkna.mobile_lab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

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
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView tagsText;
        private TextView producerText;
        private TextView servingSuggestionText;

        ProductViewHolder(View itemView) {
            super(itemView);
            tagsText =  itemView.findViewById(R.id.tags_text);
            producerText =  itemView.findViewById(R.id.producer_text);
            servingSuggestionText =  itemView.findViewById(R.id.serving_suggestion_text);
        }
    }
}