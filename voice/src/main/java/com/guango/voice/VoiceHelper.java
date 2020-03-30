package com.guango.voice;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.util.Log;


public class VoiceHelper {

    public static IView view;

    public interface IView{
        void requestPermissions();
    }


    static boolean isRecording = false; //true表示正在录音

    static AudioRecord audioRecord=null;

    static AudioTrack audioTrack = null;

    private static int part = 0;

    private static int bufferSize=3000;//最小缓冲区大小

    private static int sampleRateInHz = 16500;//采样率

    private static int channelConfig = AudioFormat.CHANNEL_IN_STEREO;

    private static int audioFormat = AudioFormat.ENCODING_PCM_16BIT;

    public static void applyPermission(){
        view.requestPermissions();
    }

    public static void getVoice(){
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,sampleRateInHz,channelConfig, audioFormat, bufferSize);

        new Thread(new Runnable() {
            @Override
            public void run() {
                isRecording = true;
                try {
                    byte[] buffer = new byte[bufferSize];
                    audioRecord.startRecording();
                    while (isRecording) {
                        audioRecord.read(buffer,0,bufferSize);
                        SocketSender.sendPcm(buffer);
                        Log.d("yanyao", String.valueOf(buffer.length));
                    }
                    audioRecord.stop();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }).start();
    }

    public static void playVoice(byte[] pcm){
        part++;
        if(audioTrack == null){
            audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRateInHz, AudioFormat.CHANNEL_OUT_STEREO, audioFormat, bufferSize, AudioTrack.MODE_STREAM);
        }
        audioTrack.write(pcm, 0, pcm.length);
        if(part == 100){
            part = 0;
            audioTrack.play();
        }
    }

    public static void stopVoice(){
        isRecording = false;
        if(audioRecord != null){
            audioRecord.release();
        }
        if(audioTrack != null){
            audioTrack.stop();
            audioTrack.release();
            audioTrack = null;
        }
    }
}
