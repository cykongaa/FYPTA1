package hkust.fypta1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private PopupWindow popup;
    private Typeface face;
    private int year;
    private int month;
    private int weekDay;
    private int dayOfMonth;
    private TextView yearText;
    private TextView monthText;
    private GridView gv_cal;
    public ViewGroup layoutBar;
    public Button close;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
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

    public void switchOnClick(View view) {
        java.util.Calendar newMonth = java.util.Calendar.getInstance();

        switch (view.getId()) {
            case R.id.forward:
                if (month + 1 > 12) {
                    yearText.setText(Integer.toString(year + 1));
                    month = 1;
                    year += 1;
                } else {
                    month += 1;
                }
                break;
            case R.id.backward:
                if (month - 1 < 1) {
                    yearText.setText(Integer.toString(year - 1));
                    year -= 1;
                    month = 12;
                } else {
                    month -= 1;
                }
                break;
        }

        newMonth.set(year, month - 1, 1);
        monthText.setText(new SimpleDateFormat("MMM").format(newMonth.getTime()));
        weekDay = newMonth.get(java.util.Calendar.DAY_OF_WEEK);
        dayOfMonth = newMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        gv_cal.setAdapter(new TextAdapter(getActivity().getApplicationContext(), year, month, weekDay, 0, dayOfMonth, face));
    }

    private void showPopUp() {
        try {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_calendar_detail, (ViewGroup) getActivity().findViewById(R.id.popupCalendar));
//            TextView weekday = (TextView) findViewById(R.id.weekdayTV);
//            weekday.setText(weekDay);
            popup = new PopupWindow(layout, 1000, 1200, true);
            popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

            close = (Button) layout.findViewById(R.id.closeBtn);
            close.setOnClickListener(cancel_button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button = new View.OnClickListener() {
        public void onClick(View v) {
            popup.dismiss();
        }
    };

    private void setAllAttr(TextView txtview, String text, Typeface face, int color) {
        txtview.setTextColor(color);
        txtview.setText(text);
        txtview.setTypeface(face);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        if (mListener != null) {
//            mListener.onFragmentInteraction("Calendar");
//        }


        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

                //Get Current Year and Month When Creating
        Context con = getActivity().getApplicationContext();
        java.util.Calendar c = java.util.Calendar.getInstance();
        year = c.get(java.util.Calendar.YEAR);
        month = c.get(java.util.Calendar.MONTH) + 1;
        c.set(java.util.Calendar.DATE, 1);
        face = Typeface.createFromAsset(con.getAssets(), "fonts/KaushanScript-Regular.ttf");
        weekDay = c.get(java.util.Calendar.DAY_OF_WEEK);
        dayOfMonth = c.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);



        //Set The Text View by current year and month
        yearText = (TextView) view.findViewById(R.id.Year);
        monthText = (TextView) view.findViewById(R.id.Month);
        TextView txtyr = (TextView) view.findViewById(R.id.textForYear);


        setAllAttr(yearText, Integer.toString(year), face, Color.BLACK);
        setAllAttr(monthText, new SimpleDateFormat("MMM").format(c.getTime()), face, Color.BLACK);
        setAllAttr(txtyr, "Year", face, Color.BLACK);

        //Binding the grid view by day images and set the theme image
        gv_cal = (GridView) view.findViewById(R.id.CalendarGrid);
        //gv_cal.setBackgroundResource(R.mipmap.ic_launcher);
        gv_cal.setAdapter(new TextAdapter(getActivity().getApplicationContext(), year, month, weekDay, 0, dayOfMonth, face));

        gv_cal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                //Method for output parameter and debugging
                if (index - weekDay >= -1 && index - weekDay < dayOfMonth - 1) {
                    showPopUp();
                }
                Toast.makeText(getActivity(), "" + index + " " + year + " " + month + " " + weekDay,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        try {
//            mListener = (OnFragmentInteractionListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//
//        public void onFragmentInteraction(String title);
//    }
}
