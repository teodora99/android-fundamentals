package project.client.internshipPlatform.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import project.client.internshipPlatform.DTO.InternshipOverviewDto;
import project.client.internshipPlatform.R;
import project.client.internshipPlatform.adminActivity.AllInternshipsOverview;

public class CustomAdapterAllInternship extends RecyclerView.Adapter<CustomAdapterAllInternship.MyViewHolder> {

    private InternshipOverviewDto dataSet;
    private final static String TAG = CustomAdapterAllInternship.class.getSimpleName();
    String idAdmin;
    private AllInternshipsOverview allInternshipsOverview;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView company;
        TextView applyUntil;
        TextView fav;
        TextView id;
        MaterialButton approveButton;


        private final String URL_APPROVED = "http://10.0.2.2:8080/api/admin/approve";

        public MyViewHolder(final View itemView, final String idAdmin, final AllInternshipsOverview allInternshipsOverview) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title1);
            this.company = itemView.findViewById(R.id.company);
            this.applyUntil = itemView.findViewById(R.id.dateUntil);
            this.id = itemView.findViewById(R.id.id);
            this.fav = itemView.findViewById(R.id.fav);
            this.approveButton = itemView.findViewById(R.id.approveButtonAdmin);
            approveButton.setVisibility(View.INVISIBLE);
        }
    }

    public CustomAdapterAllInternship(InternshipOverviewDto data, String idAdmin, AllInternshipsOverview allInternshipsOverview) {
        this.dataSet = data;
        this.idAdmin = idAdmin;
        this.allInternshipsOverview = allInternshipsOverview;
    }

    @Override
    public CustomAdapterAllInternship.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_internship_unapproved, parent, false);

        view.setOnClickListener(AllInternshipsOverview.myOnClickListener);

        CustomAdapterAllInternship.MyViewHolder myViewHolder = new CustomAdapterAllInternship.MyViewHolder(view, idAdmin, allInternshipsOverview);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomAdapterAllInternship.MyViewHolder holder, final int listPosition) {

        TextView title2 = holder.title;
        TextView company2 = holder.company;
        TextView applyUntil2 = holder.applyUntil;
        TextView id2 = holder.id;
        TextView fav2 = holder.fav;
        MaterialButton approveButton2 = holder.approveButton;

        approveButton2.setVisibility(View.INVISIBLE);
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
