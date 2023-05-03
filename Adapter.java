
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    List<imageItem> items;
    ViewPager2 viewPager2;

    public ImageAdapter(List<imageItem> items,ViewPager2 viewPager2){
        this.items = items;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.roundedImageView.setImageResource(items.get(position).getImage());
        // looping end to head
        if(position == items.size()-1){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView roundedImageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.imageViewContainer);
        }
    }

    // code of looping end to head
    private  Runnable runnable = new Runnable() {
        @Override
        public void run() {
            items.addAll(items);
            notifyDataSetChanged();
        }
    };
}
