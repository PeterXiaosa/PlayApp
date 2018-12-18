package com.example.peter.playapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.peter.playapp.R;
import com.example.peter.playapp.bean.ProductTypeBean;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ViewHolder>{
    private Context mContext;
    private List<ProductTypeBean> mData = new ArrayList<>();
    private onClickType listener;

    public interface onClickType{
        void clickTypeItem(String typeName);
    }

    public ProductTypeAdapter(Context context, List<ProductTypeBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    public void setListener(onClickType listener){
        this.listener = listener;
    }

    public void setData(List<ProductTypeBean> data){
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product_item, null);
        return new ViewHolder(view, true);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mData != null && position < getItemCount()) {
            ProductTypeBean productTypeBean = mData.get(position);
            final String typeName = productTypeBean.getType_name();

            holder.tv_type_name.setText(typeName);

            holder.tv_type_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clickTypeItem(typeName);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_type_name;

        public ViewHolder(View itemView, boolean isItem){
            super(itemView);
            if (isItem){
                tv_type_name = itemView.findViewById(R.id.item_product_tv_type_name);
            }
        }
    }
}
