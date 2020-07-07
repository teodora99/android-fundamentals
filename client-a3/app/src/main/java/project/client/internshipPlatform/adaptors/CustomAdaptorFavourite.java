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
import project.client.internshipPlatform.threads.ThreadRequest;
import project.client.internshipPlatform.userActivity.FavouriteInternship;

public class CustomAdaptorFavourite extends RecyclerView.Adapter<CustomAdaptorFavourite.MyViewHolder> {

        private InternshipOverviewDto dataSet;
        private final static String TAG = CustomAdapter.class.getSimpleName();
        private String idUser;
        private FavouriteInternship favouriteInternship;

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView title;
            TextView company;
            TextView applyUntil;
            TextView fav;
            TextView id;
            ImageButton favButton;
            TextView sorry;

            private final String URL_NOT_FAVORITE = "http://10.0.2.2:8080/api/internship/deleteFavorite/";

            public MyViewHolder(final View itemView, final String idUser, final FavouriteInternship favouriteInternship) {
                super(itemView);
                this.title = itemView.findViewById(R.id.title1);
                this.company = itemView.findViewById(R.id.company);
                this.id = itemView.findViewById(R.id.id);
                this.fav = itemView.findViewById(R.id.fav);
                this.applyUntil = itemView.findViewById(R.id.dateUntil);
                this.favButton = itemView.findViewById(R.id.favBtn);

                favButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "I have added an item to favorite " + fav.getText().toString());
                        if(fav.getText().toString().equals("true")) {
                            fav.setText("false");
                            InternshipDto internshipDto = new InternshipDto(id.getText().toString(), title.getText().toString(),
                                    company.getText().toString(),
                                    applyUntil.getText().toString(),
                                    Boolean.getBoolean(fav.getText().toString()));
                            favButton.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
                            ThreadRequest threadRequest = new ThreadRequest(internshipDto, URL_NOT_FAVORITE + idUser);
                            Thread t = new Thread(threadRequest);
                            t.start();
                            favouriteInternship.startNewActivity();
                        }
                    }
                });
            }
        }

    public CustomAdaptorFavourite(InternshipOverviewDto data, String idUser, FavouriteInternship favouriteInternship) {
            this.dataSet = data;
            this.idUser = idUser;
            this.favouriteInternship = favouriteInternship;
        }

        @Override
        public CustomAdaptorFavourite.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.internship_overview_layout, parent, false);

            view.setOnClickListener(FavouriteInternship.myOnClickListener);

            CustomAdaptorFavourite.MyViewHolder myViewHolder = new CustomAdaptorFavourite.MyViewHolder(view, idUser, favouriteInternship);
            return myViewHolder;
        }


    @Override
        public void onBindViewHolder(final CustomAdaptorFavourite.MyViewHolder holder, final int listPosition) {

            TextView title2 = holder.title;
            TextView description2 = holder.company;
            TextView applyUntilDate = holder.applyUntil;
            TextView id2 = holder.id;
            TextView fav2 = holder.fav;
            ImageButton favButton = holder.favButton;

            if(!dataSet.getInternshipDtoList().isEmpty()){
                Log.e(TAG, "All internships!");
                title2.setText(dataSet.getInternshipDtoList().get(listPosition).getTitle());
                description2.setText(dataSet.getInternshipDtoList().get(listPosition).getCompany());
                applyUntilDate.setText(dataSet.getInternshipDtoList().get(listPosition).getApplyUntil());
                id2.setText(dataSet.getInternshipDtoList().get(listPosition).getId());
                fav2.setText(dataSet.getInternshipDtoList().get(listPosition).isFavourite());
                favButton.setBackgroundResource(R.drawable.ic_favorite_red);

            }else {
                Log.e(TAG, "No internships!");
                holder.sorry.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return dataSet.getInternshipDtoList().size();
        }
    }

