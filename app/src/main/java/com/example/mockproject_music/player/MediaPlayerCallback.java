package com.example.mockproject_music.player;

import com.example.mockproject_music.player.type.UpdateType;

public interface MediaPlayerCallback {
    void updateData(UpdateType type);
}
