package project.client.internshipPlatform.adaptors;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import project.client.internshipPlatform.DTO.InternshipDto;
import project.client.internshipPlatform.DTO.InternshipOverviewDto;
import project.client.internshipPlatform.R;
import project.client.internshipPlatform.RecycleViewInternshipActivity;
import project.client.internshipPlatform.threads.ThreadRequest;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private InternshipOverviewDto dataSet;
    private final static String TAG = CustomAdapter.class.getSimpleName();
    String idUser;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView company;
        TextView applyUntil;
        TextView fav;
        TextView id;
        ImageButton favButton;



        private final String URL_FAVORITE = "http://10.0.2.2:8080/api/internship/addFavorite/";
        private final String URL_NOT_FAVORITE = "http://10.0.2.2:8080/api/internship/deleteFavorite/";

        public MyViewHolder(final View itemView, final String idUser) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title1);
            this.company = itemView.findViewById(R.id.company);
            this.applyUntil = itemView.findViewById(R.id.dateUntil);
            this.id = itemView.findViewById(R.id.id);
            this.fav = itemView.findViewById(R.id.fav);
            this.favButton = itemView.findViewById(R.id.favBtn);

            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "I have added an item to favorite " + fav.getText().toString());

                    if(fav.getText().toString().equals("false")) {
                        fav.setText("true");
                        InternshipDto internshipDto = new InternshipDto(id.getText().toString(), title.getText().toString(),
                                company.getText().toString(),
                                applyUntil.getText().toString(),
                                Boolean.getBoolean(fav.getText().toString()));
                        favButton.setBackgroundResource(R.drawable.ic_favorite_red);
                        ThreadRequest threadRequest = new ThreadRequest(internshipDto, URL_FAVORITE + idUser);
                        Thread t = new Thread(threadRequest);
                        t.start();
                    }else{
                        fav.setText("false");
                        InternshipDto internshipDto = new InternshipDto(id.getText().toString(), title.getText().toString(),
                                company.getText().toString(),
                                applyUntil.getText().toString(),
                                Boolean.getBoolean(fav.getText().toString()));
                        favButton.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                        ThreadRequest threadRequest = new ThreadRequest(internshipDto, URL_NOT_FAVORITE + idUser);
                        Thread t = new Thread(threadRequest);
                        t.start();
                    }
                }
            });
              }
    }

    public CustomAdapter(InternshipOverviewDto data, String id) {
        this.dataSet = data;
        this.idUser = id;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.internship_overview_layout, parent, false);

        view.setOnClickListener(RecycleViewInternshipActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view, idUser);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView title2 = holder.title;
        TextView company2 = holder.company;
        TextView applyUntil2 = holder.applyUntil;
        TextView id2 = holder.id;
        TextView fav2 = holder.fav;
        ImageButton favButton = holder.favButton;

                 title2.setText(dataSet.getInternshipDtoList().get(listPosition).getTitle());
                 company2.setText(dataSet.getInternshipDtoList().get(listPosition).getCompany());
                 applyUntil2.setText(dataSet.getInternshipDtoList().get(listPosition).getApplyUntil());
                 id2.setText(dataSet.getInternshipDtoList().get(listPosition).getId());
                    fav2.setText(dataSet.getInternshipDtoList().get(listPosition).isFavourite());
                    if(fav2.getText().toString().equals("true")){
                             favButton.setBackgroundResource(R.drawable.ic_favorite_red);
                    }else
                             favButton.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
    }

    @Override
    public int getItemCount() {
        return dataSet.getInternshipDtoList().size();
    }
}
