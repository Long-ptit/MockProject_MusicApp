package com.example.mockproject_music.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class MusicBrocast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
           Toast.makeText(context, "Dang sac", Toast.LENGTH_SHORT).show();
          // showPopup(context);
       }

        if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "Dang khong sac", Toast.LENGTH_SHORT).show();
        }


    }

    private void showPopup(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle("Test")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
