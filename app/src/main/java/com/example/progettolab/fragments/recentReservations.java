package com.example.progettolab.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.progettolab.Model;
import com.example.progettolab.Obj.Reservation;
import com.example.progettolab.R;
import com.example.progettolab.Obj.Calendar;
import com.example.progettolab.ReservationsRecViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link recentReservations#newInstance} factory method to
 * create an instance of this fragment.
 */
public class recentReservations extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public recentReservations() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment recentReservations.
     */
    // TODO: Rename and change types and number of parameters
    public static recentReservations newInstance(String param1, String param2) {
        recentReservations fragment = new recentReservations();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView recentReservations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recent_reservations, container, false);

        recentReservations = (RecyclerView) rootView.findViewById(R.id.recentReservations);

        recentReservations.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Reservation> reservations = Model.getDataBaseHelper().getActiveReservations(Model.getUser().getId());

        ReservationsRecViewAdapter adapter = new ReservationsRecViewAdapter();

        adapter.setReservation(reservations);

        recentReservations.setAdapter(adapter);

        return rootView;
        // Inflate the layout for this fragment
    }
}