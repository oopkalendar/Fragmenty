package net.useobjects.fragmenty;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    private String info;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param infoString Parameter 1.
          * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(Selection selection) {
        Log.d("Aplikacia", "DetailFragment.newInstance");
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("info", selection.toInfoString());
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
        Log.d("Aplikacia", "DetailFragment konstruktor " + Integer.toHexString(System.identityHashCode(this)));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            info = getArguments().getString("info");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Aplikacia", "DetailFragment.onCreateView (container = " + Integer.toHexString(System.identityHashCode(container)) + ")");
        // Inflate the layout for this fragment
        if(container == null) {
            return null;
        }
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("Aplikacia", "DetailFragment.onViewCreated zaciatok " + Integer.toHexString(System.identityHashCode(this)));
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView) getActivity().findViewById(R.id.detailText);
        textView.setText(info);
        Log.d("Aplikacia", "DetailFragment.onViewCreated koniec");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("Aplikacia", "DetailFragment.onActivityCreated zaciatok " + Integer.toHexString(System.identityHashCode(this)));
        super.onActivityCreated(savedInstanceState);
//        if( savedInstanceState == null) {
//            TextView textView = (TextView) getActivity().findViewById(R.id.detailText);
//            textView.setText(info);
//        }
        Log.d("Aplikacia", "DetailFragment.onActivityCreated (info = " + info + "), savedInstanceState = " + savedInstanceState);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.d("Aplikacia", "DetailFragment.onDetach " + Integer.toHexString(System.identityHashCode(this)));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
