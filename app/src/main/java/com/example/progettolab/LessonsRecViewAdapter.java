package com.example.progettolab;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettolab.Obj.Association;
import com.example.progettolab.Obj.Calendar;
import com.example.progettolab.Obj.Teacher;

import java.util.ArrayList;

public class LessonsRecViewAdapter extends RecyclerView.Adapter<LessonsRecViewAdapter.ViewHolder>{

    private ArrayList<Association> associations = new ArrayList<>();

    public LessonsRecViewAdapter(){

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lessons_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        holder.txtContact.setText(associations.get(position).getTeacherLast());

        if(!(Model.getUser() == null)){
            holder.btnSlot1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                    Model.setCurrentCalendar(new Calendar(Model.getCurrentDate() , holder.btnSlot1.getText().toString() , associations.get(pos).getTeachersId(), associations.get(pos).getCourseName(), Model.getUser().getId()));
                    Model.setTeacher(new Teacher(associations.get(pos).getTeacherFirst(), associations.get(pos).getTeacherLast(), associations.get(pos).getTeachersId()));
                    Intent intent = new Intent(v.getContext() , ConfirmReservation.class);
                    v.getContext().startActivity(intent);
                }
            });

            holder.btnSlot2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                    Model.setCurrentCalendar(new Calendar(Model.getCurrentDate() , holder.btnSlot2.getText().toString() , associations.get(pos).getTeachersId(), associations.get(pos).getCourseName(), Model.getUser().getId()));
                    Model.setTeacher(new Teacher(associations.get(pos).getTeacherFirst(), associations.get(pos).getTeacherLast(), associations.get(pos).getTeachersId()));
                    Intent intent = new Intent(v.getContext() , ConfirmReservation.class);
                    v.getContext().startActivity(intent);
                }
            });

            holder.btnSlot3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                    Model.setCurrentCalendar(new Calendar(Model.getCurrentDate() , holder.btnSlot3.getText().toString() , associations.get(pos).getTeachersId(), associations.get(pos).getCourseName(), Model.getUser().getId()));
                    Model.setTeacher(new Teacher(associations.get(pos).getTeacherFirst(), associations.get(pos).getTeacherLast(), associations.get(pos).getTeachersId()));
                    Intent intent = new Intent(v.getContext() , ConfirmReservation.class);
                    v.getContext().startActivity(intent);
                }
            });

            holder.btnSlot4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                    Model.setCurrentCalendar(new Calendar(Model.getCurrentDate() , holder.btnSlot4.getText().toString() , associations.get(pos).getTeachersId(), associations.get(pos).getCourseName(), Model.getUser().getId()));
                    Model.setTeacher(new Teacher(associations.get(pos).getTeacherFirst(), associations.get(pos).getTeacherLast(), associations.get(pos).getTeachersId()));
                    Intent intent = new Intent(v.getContext() , ConfirmReservation.class);
                    v.getContext().startActivity(intent);
                }
            });
        }else{
            holder.btnSlot1.setEnabled(false);
            holder.btnSlot2.setEnabled(false);
            holder.btnSlot3.setEnabled(false);
            holder.btnSlot4.setEnabled(false);
        }

        for(int i=0; i<Model.getCalendarList().size(); i++){
            if(Model.getCalendarList().get(i).getTeachersId() == associations.get(pos).getTeachersId()){
                String hour = Model.getCalendarList().get(i).getHour();
                System.out.println(hour);
                switch (hour){
                    case "15-16":
                        holder.btnSlot1.setBackgroundColor(Color.parseColor("#e1341e"));
                        holder.btnSlot1.setEnabled(false);
                        break;
                    case "16-17":
                        holder.btnSlot2.setBackgroundColor(Color.parseColor("#e1341e"));
                        holder.btnSlot2.setEnabled(false);
                        break;
                    case "17-18":
                        holder.btnSlot3.setBackgroundColor(Color.parseColor("#e1341e"));
                        holder.btnSlot3.setEnabled(false);
                        break;
                    case "18-19":
                        holder.btnSlot4.setBackgroundColor(Color.parseColor("#e1341e"));
                        holder.btnSlot4.setEnabled(false);
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if(associations == null){
            return -1;
        }else{
            return associations.size();
        }
    }

    public void setAssociations(ArrayList<Association> associations) {
        this.associations = associations;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtContact;
        private Button btnSlot1;
        private Button btnSlot2;
        private Button btnSlot3;
        private Button btnSlot4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtContact = itemView.findViewById(R.id.txtContact);
            btnSlot1 = itemView.findViewById(R.id.txtSlot1);
            btnSlot2 = itemView.findViewById(R.id.txtSlot2);
            btnSlot3 = itemView.findViewById(R.id.txtSlot3);
            btnSlot4 = itemView.findViewById(R.id.txtSlot4);
        }
    }
}

