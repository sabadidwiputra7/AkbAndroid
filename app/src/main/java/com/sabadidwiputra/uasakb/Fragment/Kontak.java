package com.sabadidwiputra.uasakb.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sabadidwiputra.uasakb.R;
/**5-08-2019
10116314
Sabadi Dwiputra
AKB7*/
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Kontak#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Kontak extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageView imgCall, imgSm, imgEmail, imgFb;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Kontak() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Kontak.
     */
    // TODO: Rename and change types and number of parameters
    public static Kontak newInstance(String param1, String param2) {
        Kontak fragment = new Kontak();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kontak, container, false);
        imgCall = (ImageView) view.findViewById(R.id.gmbrTelp);
        imgEmail = (ImageView) view.findViewById(R.id.gmbrEmail);
        imgSm = (ImageView) view.findViewById(R.id.gmbrIg);
        imgFb = (ImageView) view.findViewById(R.id.gmbrFb);

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String NomerHp = "08986055178";
                Intent intentpanggil = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+NomerHp));
                startActivity(intentpanggil);
            }
        });
        imgEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"sabadidwiputra@mahasiswa.unikom.ac.id"});
                try {
                    startActivity(Intent.createChooser(intent, "Ingin Mengirim Email ?"));
                } catch (android.content.ActivityNotFoundException ex) {
                    //do something else
                }
            }
        });
        imgSm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String Instagram = "https://www.instagram.com/sabadidwiputra_/";
                Intent bukaIg = new Intent(Intent.ACTION_VIEW);
                bukaIg.setData(Uri.parse(Instagram));
                startActivity(bukaIg);
            }
        });
        imgFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String facebook = "https://www.facebook.com/sabadi";
                Intent bukaFb = new Intent(Intent.ACTION_VIEW);
                bukaFb.setData(Uri.parse(facebook));
                startActivity(bukaFb);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
