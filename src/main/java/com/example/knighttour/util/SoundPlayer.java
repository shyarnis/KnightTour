package com.example.knighttour.util;

import javafx.scene.media.AudioClip;

import java.net.URL;

/**
 * Utility class for playing sounds in the application.
 */
public class SoundPlayer {
    // Constants for volume levels
    private static final double DEFAULT_VOLUME = 0.7;

    // Sound resources
    private static AudioClip moveSound;

    /**
     * Initializes the sound resources.
     * This method should be called once when the application starts.
     */
    public static void initialize() {
        try {
            URL moveSoundUrl = SoundPlayer.class.getResource("/move.mp3");

            if (moveSoundUrl != null) {
                moveSound = new AudioClip(moveSoundUrl.toExternalForm());
                moveSound.setVolume(DEFAULT_VOLUME);
            } else {
                System.err.println("Could not find move sound resource");
            }
        } catch (Exception e) {
            System.err.println("Error loading sound resources: " + e.getMessage());
        }
    }

    /**
     * Plays the knight move sound.
     */
    public static void playMoveSound() {
        if (moveSound != null) {
            moveSound.play();
        }
    }

    /**
     * Sets the volume for all sound effects.
     *
     * @param volume The volume level (0.0 to 1.0)
     */
    public static void setVolume(double volume) {
        if (moveSound != null) {
            moveSound.setVolume(volume);
        }
    }
}