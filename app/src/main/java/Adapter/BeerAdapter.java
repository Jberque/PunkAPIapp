package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.justin.punk_apiapp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import Beer.Beer;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<Beer> beerList;
    private Context context;

    public BeerAdapter(List<Beer> beerList, Context context) {
        this.context = context;
        this.beerList = beerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_beer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Beer beer = beerList.get(position);


        Picasso.with(context)
                .load(new File(beer.getImageUrl()))
                .resize(50, 100)
                .centerCrop()
                .into(holder.beerCover);

        holder.name.setText(beer.getName());
        holder.tagline.setText(beer.getTagline());
        holder.brewed.setText(beer.getFirstBrewed());
        holder.abv.setText(String.valueOf(beer.getAbv()));
        holder.ibu.setText(String.valueOf(beer.getIbu()));
        holder.ebc.setText(String.valueOf(beer.getEbc()));
//        holder.itemView.setOnClickListener(clickListener(position));
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView beerCover;
        TextView name;
        TextView tagline;
        TextView brewed;
        TextView abv;
        TextView ibu;
        TextView ebc;

        ViewHolder(View itemView) {
            super(itemView);
            beerCover = itemView.findViewById(R.id.beer_cover);
            name = itemView.findViewById(R.id.name);
            tagline = itemView.findViewById(R.id.tagline);
            brewed = itemView.findViewById(R.id.first_brewed);
            abv = itemView.findViewById(R.id.abv);
            ibu = itemView.findViewById(R.id.ibu);
            ebc = itemView.findViewById(R.id.ebc);
        }
    }

//    View.OnClickListener clickListener(final int position) {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(
//                        new Intent(context, BeerActivity.class)
//                                .putExtra("id", beerList.get(position).getId())
//                );
//            }
//        };
//    }
}
