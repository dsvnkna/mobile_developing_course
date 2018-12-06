package lab.dsvnkna.mobile_lab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Product> dataList;

    public ProductAdapter(ArrayList<Product> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_view, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.txtNoticeTitle.setText(dataList.get(position).getName());
        holder.txtNoticeBrief.setText(dataList.get(position).getOrigin());
        holder.txtNoticeFilePath.setText(dataList.get(position).getAlcoholContent());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNoticeTitle;
        private TextView txtNoticeBrief;
        private TextView txtNoticeFilePath;

        ProductViewHolder(View itemView) {
            super(itemView);
            txtNoticeTitle =  itemView.findViewById(R.id.txt_notice_title);
            txtNoticeBrief =  itemView.findViewById(R.id.txt_notice_brief);
            txtNoticeFilePath =  itemView.findViewById(R.id.txt_notice_file_path);
        }
    }
}