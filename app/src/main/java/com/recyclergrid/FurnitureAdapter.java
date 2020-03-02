package com.recyclergrid;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FurnitureAdapter extends RecyclerView.Adapter<FurnitureAdapter.FurnitureView> implements Filterable{


    List<HashMap<String,Object>> furnitureList = new ArrayList<>();
    List<HashMap<String,Object>> filteredList = new ArrayList<>();
    CustomFilter customFilter;

    public FurnitureAdapter(List<HashMap<String, Object>> fList) {
        this.furnitureList = fList;
        filteredList = furnitureList;
        customFilter = new CustomFilter();
    }

    @NonNull
    @Override
    public FurnitureView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_furniture,viewGroup,false);

        return new FurnitureView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureView furnitureView, int position) {

        HashMap<String,Object> map = filteredList.get(position);

        furnitureView.imageFurniture.setImageResource((Integer) map.get("Image"));
        furnitureView.textTitle.setText(String.valueOf(map.get("Title")));
        furnitureView.textPrice.setText(String.valueOf(map.get("Price")));

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return customFilter;
    }


    public class FurnitureView extends RecyclerView.ViewHolder{


        ImageView imageFurniture;
        TextView textTitle,textPrice;
        public FurnitureView(@NonNull View itemView) {
            super(itemView);

            imageFurniture = (ImageView)itemView.findViewById(R.id.image_furniture);
            textTitle = (TextView)itemView.findViewById(R.id.text_title);
            textPrice = (TextView)itemView.findViewById(R.id.text_price);


        }
    }

    public class CustomFilter extends Filter{


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults filterResults = new FilterResults();

            List<HashMap<String,Object>> newList = furnitureList;
            List<HashMap<String,Object>> resultList = new ArrayList<>();

            String searchValue = constraint.toString().toLowerCase();

            for(int i=0;i<newList.size();i++){

                HashMap<String,Object> map = newList.get(i);
                String title = String.valueOf(map.get("Title"));

                if(title.toLowerCase().contains(searchValue)){
                    resultList.add(map);
                }

            }


            filterResults.count = resultList.size();
            filterResults.values = resultList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filteredList = (List<HashMap<String, Object>>) results.values;
            notifyDataSetChanged();

        }
    }

}
