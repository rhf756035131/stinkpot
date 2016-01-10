package com.example.renhaifeng.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by renhaifeng on 2016/1/10.
 */
public class Socket_STA_Setting extends AppCompatActivity {
    private static final String TAG = "socket PA Setting";
    private TextView mTvApSsid;
    private EditText mEdtoldApPassword,mEdtnewApPassword,mEdtnewaginApPassword;
    private Button mBtnConfirm;
    private Switch mSwitchIsSsidHidden;
    private EspWifiAdminSimple mWifiAdmin;
    private Spinner mSpinnerTaskCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket_sta_setting);
        mWifiAdmin = new EspWifiAdminSimple(this);
        mTvApSsid = (TextView) findViewById(R.id.tvApSssidConnected);
        mEdtoldApPassword = (EditText) findViewById(R.id.edtoldApPassword);
        mEdtnewApPassword = (EditText) findViewById(R.id.edtnewApPassword);
        mEdtnewaginApPassword = (EditText) findViewById(R.id.edtnewaginApPassword);
        mBtnConfirm = (Button) findViewById(R.id.btnConfirm);
        mSwitchIsSsidHidden = (Switch) findViewById(R.id.switchIsSsidHidden);
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String apSsid = mTvApSsid.getText().toString();
                String oldapPassword = mEdtoldApPassword.getText().toString();
                String newapPassword = mEdtnewApPassword.getText().toString();
                String newaginapPassword = mEdtnewaginApPassword.getText().toString();
                String apBssid = mWifiAdmin.getWifiConnectedBssid();
                Boolean isSsidHidden = mSwitchIsSsidHidden.isChecked();
                String isSsidHiddenStr = "NO";
                String taskResultCountStr = Integer.toString(mSpinnerTaskCount
                        .getSelectedItemPosition());
                if (isSsidHidden) {
                    isSsidHiddenStr = "YES";
                }
                Log.d(TAG, "mBtnConfirm is clicked, mEdtApSsid = " + apSsid
                        + ", " + " mEdtoldApPassword = " + oldapPassword);
                new StatusTask(Socket_STA_Setting.this, "Query Status").execute();
            }

        });
        initSpinner();
    }
    private void initSpinner()
    {
        mSpinnerTaskCount = (Spinner) findViewById(R.id.spinnerTaskResultCount);
        int[] spinnerItemsInt = getResources().getIntArray(R.array.taskResultCount);
        int length = spinnerItemsInt.length;
        Integer[] spinnerItemsInteger = new Integer[length];
        for(int i=0;i<length;i++)
        {
            spinnerItemsInteger[i] = spinnerItemsInt[i];
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_list_item_1, spinnerItemsInteger);
        mSpinnerTaskCount.setAdapter(adapter);
        mSpinnerTaskCount.setSelection(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // display the connected ap's ssid
        String apSsid = mWifiAdmin.getWifiConnectedSsid();
        if (apSsid != null) {
            mTvApSsid.setText(apSsid);
        } else {
            mTvApSsid.setText("");
        }
        // check whether the wifi is connected
        boolean isApSsidEmpty = TextUtils.isEmpty(apSsid);
        mBtnConfirm.setEnabled(!isApSsidEmpty);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*
        if (id == R.id.action_settings) {
            return true;
        }
*/

        return super.onOptionsItemSelected(item);
    }
    private class StatusTask extends BaseTask {

        public StatusTask(Context context, String message) {
            super(context, message);
        }
        private String intToIp(int i) {

            return (i & 0xFF ) + "." +
                    ((i >> 8 ) & 0xFF) + "." +
                    ((i >> 16 ) & 0xFF) + "." +
                    ( i >> 24 & 0xFF) ;
        }
        @Override
        protected String doInBackground(String... params) {
            Socket socket = null;
            Scanner scanner = null;
            PrintWriter out = null;
            try {
                socket = new Socket(intToIp(mWifiAdmin.getWifiIpAddress()), 8002);
                scanner = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream());
                out.println("status");
                out.flush();
                String line = scanner.nextLine();
                if (!TextUtils.isEmpty(line)) {
                    String[] tokens = line.split(",");
                    if (tokens.length == 6) {
                    }
                }

            } catch (IOException e) {
                return e.getMessage();
            } finally {
                if (out != null)
                    out.close();
                if (scanner != null)
                    scanner.close();
                if (socket != null)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        return e.getMessage();
                    }
            }
            return "ok";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result.equals("ok")){

            }
        }
    }
}
