package project.client.internshipPlatform.adaptors;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import project.client.internshipPlatform.DTO.InternshipDto;
import project.client.internshipPlatform.DTO.InternshipOverviewDto;
import project.client.internshipPlatform.R;
import project.client.internshipPlatform.adminActivity.UnapprovedInternships;
import project.client.internshipPlatform.threads.ThreadRequest;

public class CustomAdapterUnapproved extends RecyclerView.Adapter<CustomAdapterUnapproved.MyViewHolder> {

    private InternshipOverviewDto dataSet;
    private final static String TAG = CustomAdapterUnapproved.class.getSimpleName();
    String idAdmin;
    private UnapprovedInternships unapprovedInternships;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView company;
        TextView applyUntil;
        TextView fav;
        TextView id;
        MaterialButton approveButton;


        private final String URL_APPROVED = "http://10.0.2.2:8080/api/admin/approve/";

        public MyViewHolder(final View itemView, final String idAdmin, final UnapprovedInternships unapprovedInternships) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title1);
            this.company = itemView.findViewById(R.id.company);
            this.applyUntil = itemView.findViewById(R.id.dateUntil);
            this.id = itemView.findViewById(R.id.id);
            this.fav = itemView.findViewById(R.id.fav);
            this.approveButton = itemView.findViewById(R.id.approveButtonAdmin);

            approveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Ai apasat pe buton  " + idAdmin);

                    InternshipDto internshipDto = new InternshipDto(id.getText().toString(), title.getText().toString(),
                                company.getText().toString(),
                                applyUntil.getText().toString(),
                                Boolean.getBoolean(fav.getText().toString()));

                        ThreadRequest threadRequest = new ThreadRequest(internshipDto, URL_APPROVED + idAdmin);
                        Thread t = new Thread(threadRequest);
                        t.start();

                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    unapprovedInternships.startNewActivity();

                }
            });
        }
    }

    public CustomAdapterUnapproved(InternshipOverviewDto data, String idAdmin, UnapprovedInternships unapprovedInternships) {
        this.dataSet = data;
        this.idAdmin = idAdmin;
        this.unapprovedInternships = unapprovedInternships;
    }

    @Override
    public CustomAdapterUnapproved.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_internship_unapproved, parent, false);

        view.setOnClickListener(UnapprovedInternships.myOnClickListener);

        CustomAdapterUnapproved.MyViewHolder myViewHolder = new CustomAdapterUnapproved.MyViewHolder(view, idAdmin, unapprovedInternships);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomAdapterUnapproved.MyViewHolder holder, final int listPosition) {

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
