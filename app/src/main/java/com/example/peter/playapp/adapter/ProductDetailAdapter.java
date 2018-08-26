package com.example.peter.playapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.peter.playapp.R;
import com.example.peter.playapp.bean.ProductInfo;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ViewHolder>{
    private Context mContext;
    private List<ProductInfo> mData = new ArrayList<>();

    public ProductDetailAdapter(Context context, List<ProductInfo> data) {
        this.mContext = context;
        this.mData = data;
    }

    public void setData(List<ProductInfo> list){
        this.mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product_detail, null);
        return new ViewHolder(view, true);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mData != null && position < getItemCount()) {
            ProductInfo productInfo = mData.get(position);
            String image_url = productInfo.getImage_url();
            String productName = productInfo.getName();
            double productPrice = productInfo.getPrice();

            Glide.with(mContext).load(image_url).into(holder.iv_product);
            holder.tv_product_name.setText(productName);
            holder.tv_product_price.setText(String.valueOf(productPrice));
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_product;
        private TextView tv_product_name;
        private TextView tv_product_price;

        public ViewHolder(View itemView, boolean isItem){
            super(itemView);
            if (isItem){
                iv_product = itemView.findViewById(R.id.item_product_detail_iv_product);
                tv_product_name = itemView.findViewById(R.id.item_product_detail_tv_product_name);
                tv_product_price = itemView.findViewById(R.id.item_product_detail_tv_product_price);
            }
        }
    }
}
