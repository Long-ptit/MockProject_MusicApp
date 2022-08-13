package com.example.mockproject_music.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.mockproject_music.player.MyMediaPlayerController;

public class MusicBrocast extends BroadcastReceiver {

    private MyMediaPlayerController mMediaController;

    @Override
    public void onReceive(Context context, Intent intent) {
       if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
           mMediaController = MyMediaPlayerController.getInstance(context);

           if (mMediaController.isPlaying()) {
               showPopup(context);
           }
       }

    }

    private void showPopup(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle("Warning")
                .setMessage("Your phone is charging, let's stop music to protect battery!")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        mMediaController.deleteSong();
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
