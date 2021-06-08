package com.example.Project_1171503_1171214;
import android.app.Activity;
import android.os.AsyncTask;

import java.util.List;


public class ConnectionAsyncTask extends AsyncTask<String, String, String> {

    Activity activity;

    public ConnectionAsyncTask(Activity activity) {

        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {

        ((MainConnect) activity).setButtonText("connecting");
        super.onPreExecute();
        ((MainConnect) activity).setProgress(true);
    }

    @Override
    protected String doInBackground(String... params) {

        String data = HttpManager.getData(params[0]);

        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ((MainConnect) activity).setProgress(false);
        ((MainConnect) activity).setButtonText("connected");
        try {
            List<Car> cars = carJsonParser.getObjectFromJason(s);
            ((MainConnect) activity).fillCars(cars);
        }

        catch(Exception e){
            ((MainConnect) activity).setButtonText("Try Again!");
        }

    }
}
