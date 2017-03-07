package hkust.fypta1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FollowerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FollowerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public String currentUserName;
    public ListView lv1;

    ArrayList<Follower> follower_list = new ArrayList<>();

//    private OnFragmentInteractionListener mListener;

    public FollowerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowerFragment newInstance(String param1, String param2) {
        FollowerFragment fragment = new FollowerFragment();
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

        currentUserName="admin";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_follower, container, false);

        lv1=(ListView) view.findViewById(R.id.custom_list);

        new ConnectTask().execute("following");

        Button following = (Button) view.findViewById(R.id.followingButton);
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ConnectTask().execute("following");
            }
        });
        Button follower =(Button) view.findViewById(R.id.followerButton);
        follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ConnectTask().execute("follower");
            }
        });
        return view;
    }

    class ConnectTask extends AsyncTask<String,Void,ArrayList<Follower>> {


        protected ArrayList<Follower> doInBackground(String... k) {


            try {
                URL url = null;

                if (k[0].equals("following")) {
                    url = new URL("http://10.89.218.32:8080/fyp.webservice/getData/getFollower/?user=" + currentUserName);
                } else if (k[0].equals("follower")) {
                    url = new URL("http://10.89.218.32:8080/fyp.webservice/getData/getFollowing/?follower=" + currentUserName);
                }

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                JSONArray obj = new JSONArray(builder.toString());

                for (int n = 0; n < obj.length(); n++) {
                    JSONObject object = obj.getJSONObject(n);
                    String user_name = object.getString("userName");
                    String follower_name = object.getString("followerName");
                    String follower_icon = object.getString("Icon");

                    Log.d("Event", follower_name);
                    Follower following = new Follower(user_name, follower_name, follower_icon);
                    follower_list.add(following);
                }

                urlConnection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


            return follower_list;
        }

        protected void onProgressUpdate() {

        }


        protected void onPostExecute(ArrayList<Follower> follower_list) {


            lv1.setAdapter(new CustomListAdapter(getActivity(), follower_list));
            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = lv1.getItemAtPosition(position);
                    Follower followerData = (Follower) o;

                    Intent intent = new Intent(getActivity(), SharingActivity.class);
                    intent.putExtra("follower", followerData);
                    startActivity(intent);

                }
            });


        }

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
//        ((MainActivity) getActivity()).setActionBarTitle("Friends");
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
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
//        void onFragmentInteraction(Uri uri);
//    }
}
