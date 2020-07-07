package project.client.internshipPlatform.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import project.client.internshipPlatform.DTO.InternshipOverviewDto;
import project.client.internshipPlatform.R;
import project.client.internshipPlatform.companyActivity.MyInternships;

public class CustomAdapterMyInternships extends RecyclerView.Adapter<CustomAdapterMyInternships.MyViewHolder> {

    private InternshipOverviewDto dataSet;
    private final static String TAG = CustomAdapterMyInternships.class.getSimpleName();

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView company;
        TextView applyUntil;
        TextView fav;
        TextView id;

        public MyViewHolder(final View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title1);
            this.company = itemView.findViewById(R.id.company);
            this.applyUntil = itemView.findViewById(R.id.dateUntil);
            this.id = itemView.findViewById(R.id.id);
            this.fav = itemView.findViewById(R.id.fav);

        }
    }

    public CustomAdapterMyInternships(InternshipOverviewDto data) {
        this.dataSet = data;
    }

    @Override
    public CustomAdapterMyInternships.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_my_internships, parent, false);

        view.setOnClickListener(MyInternships.myOnClickListener);

        CustomAdapterMyInternships.MyViewHolder myViewHolder = new CustomAdapterMyInternships.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomAdapterMyInternships.MyViewHolder holder, final int listPosition) {

        TextView title2 = holder.title;
        TextView company2 = holder.company;
        TextView applyUntil2 = holder.applyUntil;
        TextView id2 = holder.id;
        TextView fav2 = holder.fav;

        title2.setText(dataSet.getInternshipDtoList().get(listPosition).getTitle());
        company2.setText(dataSet.getInternshipDtoList().get(listPosition).getCompany());
        applyUntil2.setText(dataSet.getInternshipDtoList().get(listPosition).getApplyUntil());
        id2.setText(dataSet.getInternshipDtoList().get(listPosition).getId());
        fav2.setText(dataSet.getInternshipDtoList().get(listPosition).isFavourite());

    }

    @Override
    public int getItemCount() {
        return dataSet.getInternshipDtoList().size();
    }
}

