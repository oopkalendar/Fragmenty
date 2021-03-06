package net.useobjects.fragmenty;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ControlFragment.MainFragmentListener} interface
 * to handle interaction events.
 * Use the {@link ControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment {

    private Selection selection;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String STATE_SELECTION = "state_selection";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MainFragmentListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ControlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControlFragment newInstance(String param1, String param2) {
        Log.d("Aplikacia", "ControlFragment.newInstance");
        ControlFragment fragment = new ControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ControlFragment() {
        // Required empty public constructor
        Log.d("Aplikacia", "ControlFragment konstruktor " + Integer.toHexString(System.identityHashCode(this)));
        selection = Selection.NOTHING;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Aplikacia", "ControlFragment.onCreate zaciatok " + Integer.toHexString(System.identityHashCode(this)) + " " + savedInstanceState);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if( savedInstanceState != null ) {
            Log.d("Aplikacia", "ControlFragment.onCreate savedInstanceState.getString(STATE_SELECTION) = " + savedInstanceState.getString(STATE_SELECTION));
            selection = Selection.valueOf(savedInstanceState.getString(STATE_SELECTION));
            Log.d("Applikacia", "ControlFragment.onCreate selection = " +  selection.toInfoString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Aplikacia", "ControlFragment.onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_control, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("Aplikacia", "ControlFragment.onActivityCreated");

        ((TextView)(getActivity().findViewById(R.id.selection))).setText(selection.toInfoString());

        Button buttonA = (Button) getActivity().findViewById(R.id.buttonA);
        buttonA.setOnClickListener(new ButtonAListener());

        Button buttonB = (Button) getActivity().findViewById(R.id.buttonB);
        buttonB.setOnClickListener(new ButtonBListener());

        //mListener.onChange(selection);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (MainFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement MainFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_SELECTION, selection.toString());
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
    public interface MainFragmentListener {
        //public void onInit(Selection selection);
        public void onChange(Selection selection);
    }

    public Selection getSelection() {
        return selection;
    }

    private class ButtonAListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d("Aplikacia", "kliknute tlacidlo A");
            selection = Selection.A;
            ((TextView)(getActivity().findViewById(R.id.selection))).setText(selection.toInfoString());
            if (mListener != null) {
                mListener.onChange(selection);
            }
        }
    }

    private class ButtonBListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d("Aplikacia", "kliknute tlacidlo B");
            selection = Selection.B;
            ((TextView)(getActivity().findViewById(R.id.selection))).setText(selection.toInfoString());
            if (mListener != null) {
                mListener.onChange(selection);
            }
        }
    }

}
