package ro.pub.cs.systems.eim.practicaltest02.network;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ro.pub.cs.systems.eim.practicaltest02.general.Constants;
import ro.pub.cs.systems.eim.practicaltest02.general.Utilities;

public class ClientThread extends Thread {

    private String address;
    private int port;
    private String hour;
    private String min;
    private TextView alarmInfoTextView;

    private Socket socket;

    public ClientThread(String address, int port, String hour, String min , TextView alarmInfoTextView) {
        this.address = address;
        this.port = port;
        this.hour = hour;
        this.min = min;
        this.alarmInfoTextView = alarmInfoTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            if (socket == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Could not create socket!");
                return;
            }
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            if (bufferedReader == null || printWriter == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Buffered Reader / Print Writer are null!");
                return;
            }
            printWriter.println(hour);
            printWriter.flush();
            printWriter.println(min);
            printWriter.flush();
            String alarmInformation;

            while ((alarmInformation = bufferedReader.readLine()) != null) {
                final String finalizedAlarmInformation = alarmInformation;
                Log.i(Constants.TAG, finalizedAlarmInformation);
                alarmInfoTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        alarmInfoTextView.setText(finalizedAlarmInformation);
                    }
                });
            }
        } catch (IOException ioException) {
            Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                    if (Constants.DEBUG) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }

}
