package hkust.fypta1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.loopj.android.http.*;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import android.preference.PreferenceActivity.Header;



public class EventFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ArrayList<Event> eventList = new ArrayList<>();
    public ListView evntList;
    public static HttpURLConnection conn;

    public static NetworkClientImp client;

//    private OnFragmentInteractionListener mListener;

    public EventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventFragment newInstance(String param1, String param2) {
        EventFragment fragment = new EventFragment();
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

    public class FetchEventTask extends AsyncTask<String, Void, Event> {

        @Override
        protected Event doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            /*try {
                //return downloadUrl(urls[0]);
            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
            return null;
        }


        public void downloadUrl(String serverUrl) throws IOException, JSONException {





            JSONObject obj=null;
            InputStream is = null;
            int len = 10000;
            URL url = new URL(serverUrl);

            try {

//                conn=client.connection(url);
                is = conn.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                String tempStr;
                StringBuffer stringBuffer = new StringBuffer();

                while ((tempStr=bufferedReader.readLine())!=null){
                    stringBuffer.append(tempStr);
                }

                bufferedReader.close();
                is.close();

//                String contentAsString = readIt(is, len);

                try {
                    obj = new JSONObject(stringBuffer.toString());
                } catch (Throwable t) {
                    Log.e("My App", "Could not parse malformed JSON: \"" + stringBuffer.toString() + "\"");
                }
                //return Event.parseFromJSON(obj);

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(final Event result) {

            if (result!=null) {

            }
            else
            {
                String error_msg="Error retrieving event information. Please try again.";
                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(mContext, error_msg, duration);
//                toast.show();
//                nextButton.setEnabled(true);

            }

        }


    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_event, container, false);
        evntList = (ListView) view.findViewById(R.id.listView);
        new ConnectTask().execute();

//        evntList = (ListView) view.findViewById(R.id.listView);
//
//        evntList.setAdapter( new EventListAdapter(getActivity().getApplicationContext(), eventList));
//        evntList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//                Object o = evntList.getItemAtPosition(position);
//                Event eventData = (Event) o;
//
//                Intent intent = new Intent(getActivity(), EventDetailActivity.class);
//                intent.putExtra("event",eventData);
//                startActivity(intent);
//
//            }
//        });
        return view;
    }

    class ConnectTask extends AsyncTask<String,Void,ArrayList<Event>> {


        protected ArrayList<Event> doInBackground(String... k) {

//            AsyncHttpClient client = new AsyncHttpClient();
//            client.get("http://10.89.218.32:8080/fyp.webservice/getData/getEvent", null, new HttpResponseHandler() {
//
//
//                // When the response returned by REST has Http response code '200'
//                @Override
//                public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, byte[] response) {
//
//                    try {
//                        // JSON Object
//
//
//                        String responseString = new String(response, "UTF-8");
////                    JSONObject obj= new JSONObject(responseString);
//                        JSONArray obj = new JSONArray(responseString);
//
////                    // When the JSON response has status boolean value assigned with true
////                    if(obj.get(0)){
////                        // Set Default Values for Edit View controls
//////                            setDefaultValues();
////                        // Display successfully registered message using Toast
////                        Toast.makeText(getActivity().getApplicationContext(), "You are successfully registered!", Toast.LENGTH_LONG).show();
////                    }
////                    // Else display error message
////                    else{
//////                            errorMsg.setText(obj.getString("error_msg"));
////                        Toast.makeText(getActivity().getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
////                    }
//
//                        for(int n = 0; n < obj.length(); n++)
//                        {
//                            JSONObject object = obj.getJSONObject(n);
//                            String eventID= object.getString("eventID");
//                            String eventAddress = object.getString("eventLocation");
//                            String eventDate = object.getString("eventDate");
//                            String eventTime= object.getString("eventTime");
//                            String eventDescript = object.getString("eventDescript");
//                            String eventPic = object.getString("eventPic");
//                            String eventName = object.getString("eventName");
////                        String eventCategory = object.getString("eventCategory");
//                            String eventOrganizer = object.getString("eventOrganizer");
//                            Log.d("Event",eventID);
//                            Event event = new Event(eventID,eventName,eventDate,eventAddress,eventOrganizer,eventDescript,eventPic,eventTime);
//                            eventList.add(event);
//                        }
//
//                    } catch (JSONException e) {
//                        // TODO Auto-generated catch block
//                        Toast.makeText(getActivity().getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
//                        e.printStackTrace();
//
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                // When the response returned by REST has Http response code other than '200'
//                @Override
//                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] h, byte[] b, Throwable error) {
//                    // Hide Progress Dialog
//                    // When Http response code is '404'
//                    if(statusCode == 404){
//                        Toast.makeText(getActivity().getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
//                    }
//                    // When Http response code is '500'
//                    else if(statusCode == 500){
//                        Toast.makeText(getActivity().getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
//                    }
//                    // When Http response code other than 404, 500
//                    else{
//                        Toast.makeText(getActivity().getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//            });

            try {
                URL url = new URL("http://10.89.218.32:8080/fyp.webservice/getData/getEvent");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if(urlConnection.getResponseCode()==200){
                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                 JSONArray obj = new JSONArray(builder.toString());

                for(int n = 0; n < obj.length(); n++)
                 {
                            JSONObject object = obj.getJSONObject(n);
                            String eventID= object.getString("eventID");
                            String eventAddress = object.getString("eventLocation");
                            String eventDate = object.getString("eventDate");
                            String eventTime= object.getString("eventTime");
                            String eventDescript = object.getString("eventDescript");
                            String eventPic = object.getString("eventPic");
                            String eventName = object.getString("eventName");
//                        String eventCategory = object.getString("eventCategory");
                            String eventOrganizer = object.getString("eventOrganizer");
                            Log.d("Event",eventID);
                            Event event = new Event(eventID,eventName,eventDate,eventAddress,eventOrganizer,eventDescript,eventPic,eventTime);
                            eventList.add(event);
                 }

                urlConnection.disconnect();}
                else{
                    Log.d("Connection ","failed");
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return eventList;
        }

        protected void onProgressUpdate() {

        }


        protected void onPostExecute(ArrayList<Event> event_list2) {


            evntList.setAdapter( new EventListAdapter(getActivity().getApplicationContext(), eventList));
            evntList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = evntList.getItemAtPosition(position);
                    Event eventData = (Event) o;

                    Intent intent = new Intent(getActivity(), EventDetailActivity.class);
                    intent.putExtra("event",eventData);
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
//       if (context instanceof OnFragmentInteractionListener) {
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
