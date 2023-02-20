package com.example.progettolab;


import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettolab.Obj.Calendar;
import com.example.progettolab.Obj.Reservation;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ReservationsRecViewAdapter extends RecyclerView.Adapter<ReservationsRecViewAdapter.ViewHolder>{

    private ArrayList<Reservation> reservation =  new ArrayList<>();

    public ReservationsRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservations_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtResHour.setText(reservation.get(position).getHour());
        holder.txtResDate.setText(reservation.get(position).getDate());
        holder.txtResCourse.setText(reservation.get(position).getCourseName());
        holder.txtResTeacher.setText(Model.findTeacher(reservation.get(position).getTeachersId() , Model.getTeachersList()));

        if(reservation.get(position).getStatus().equals("active")){
            holder.materialCardLayoutReservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), ReservationInfo.class);
                    Model.setCurrentReservation(reservation.get(holder.getAdapterPosition()));
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }

        if(reservation.get(position).getStatus().equals("cancelled")){
            holder.relLayoutReservation.setBackgroundColor(Color.parseColor("#FFF44336"));
            holder.relLayoutReservation.setEnabled(false);
        }

        if(reservation.get(position).getStatus().equals("completed")){
            holder.relLayoutReservation.setBackgroundColor(Color.parseColor("#FFA3A6A3"));
            holder.relLayoutReservation.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return reservation.size();

    }

    public void setReservation(ArrayList<Reservation> reservation) {
        this.reservation = reservation;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtResHour;
        private TextView txtResDate;
        private TextView txtResCourse;
        private TextView txtResTeacher;
        private MaterialCardView materialCardLayoutReservation;
        private RelativeLayout relLayoutReservation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtResHour = itemView.findViewById(R.id.txtResHour);
            txtResDate = itemView.findViewById(R.id.txtResDate);
            txtResCourse = itemView.findViewById(R.id.txtResCourse);
            txtResTeacher = itemView.findViewById(R.id.txtResTeacher);
            materialCardLayoutReservation = itemView.findViewById(R.id.materialCardLayoutReservation);
            relLayoutReservation = itemView.findViewById(R.id.relLayoutReservation);
        }
    }

}