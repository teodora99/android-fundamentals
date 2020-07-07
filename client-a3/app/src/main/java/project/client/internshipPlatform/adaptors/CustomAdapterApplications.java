package project.client.internshipPlatform.adaptors;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import project.client.internshipPlatform.DTO.InternshipOverviewDto;
import project.client.internshipPlatform.R;
import project.client.internshipPlatform.userActivity.ApplicationActivity;

public class CustomAdapterApplications extends RecyclerView.Adapter<CustomAdapterApplications.MyViewHolder> {

    private InternshipOverviewDto dataSet;
    private final static String TAG = CustomAdapter.class.getSimpleName();
    private String idUser;
    private ApplicationActivity applicationActivity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView company;
        TextView applyUntil;
        TextView fav;
        TextView id;
        TextView sorry;

        private final String URL_NOT_FAVORITE = "http://10.0.2.2:8080/api/internship/deleteFavorite/";

        public MyViewHolder(final View itemView, final String idUser, final ApplicationActivity applicationActivity) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title1);
            this.company = itemView.findViewById(R.id.company);
            this.id = itemView.findViewById(R.id.id);
            this.fav = itemView.findViewById(R.id.fav);
            this.applyUntil = itemView.findViewById(R.id.dateUntil);

        }
    }

    public CustomAdapterApplications(InternshipOverviewDto data, String idUser, ApplicationActivity applicationActivity) {
        this.dataSet = data;
        this.idUser = idUser;
        this.applicationActivity = applicationActivity;
    }

    @Override
    public CustomAdapterApplications.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.internship_overview_layout, parent, false);

        view.setOnClickListener(ApplicationActivity.myOnClickListener);

        CustomAdapterApplications.MyViewHolder myViewHolder = new CustomAdapterApplications.MyViewHolder(view, idUser, applicationActivity);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final CustomAdapterApplications.MyViewHolder holder, final int listPosition) {

        TextView title2 = holder.title;
        TextView description2 = holder.company;
        TextView applyUntilDate = holder.applyUntil;
        TextView id2 = holder.id;
        TextView fav2 = holder.fav;

        if(!dataSet.getInternshipDtoList().isEmpty()){
            Log.e(TAG, "All internships!");
            title2.setText(dataSet.getInternshipDtoList().get(listPosition).getTitle());
            description2.setText(dataSet.getInternshipDtoList().get(listPosition).getCompany());
            applyUntilDate.setText(dataSet.getInternshipDtoList().get(listPosition).getApplyUntil());
            id2.setText(dataSet.getInternshipDtoList().get(listPosition).getId());
            fav2.setText(dataSet.getInternshipDtoList().get(listPosition).isFavourite());

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

