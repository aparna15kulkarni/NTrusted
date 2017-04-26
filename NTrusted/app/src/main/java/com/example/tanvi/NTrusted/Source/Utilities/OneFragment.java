package com.example.tanvi.NTrusted.Source.Utilities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.tanvi.NTrusted.R;
public class OneFragment extends ListFragment {

    String[] AndroidOS = new String[] { "Camera","Table","Camera","Books"};


    public OneFragment() {
        // Required empty public constructor
    }

    public OneFragment(int categoryId) {
        System.out.println("Category is : "+categoryId);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_one, container, false);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, AndroidOS);
        setListAdapter(adapter);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Toast.makeText(getContext(),"Clicked item "+position,Toast.LENGTH_SHORT).show();
    }
}
