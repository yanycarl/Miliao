package com.guango.voice;

import android.annotation.SuppressLint;
import android.util.Log;

import com.guango.base.store.GlobalInfo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketSender {

    private static volatile Socket streamSocket = null;

    @SuppressLint("CI_NotAllowInvokeExecutorsMethods")
    private static ExecutorService service = Executors.newCachedThreadPool();

    public static void buildServer() {

        service.execute(new Runnable() {
            @Override
            public void run() {

                byte[] inputBuffer = new byte[7000];
                InputStream is = null;
                try {
                    is = streamSocket.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(is == null){
                    return;
                }
                BufferedInputStream bis = new BufferedInputStream(is);

                //noinspection InfiniteLoopStatement
                while (true) {
                    try {
                        int length = bis.read(inputBuffer);
                        if (length > 0) {
                            Log.d("yanyao -- in voice", String.valueOf(inputBuffer.length));
                            VoiceHelper.playVoice(inputBuffer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void buildClient(final String whom) {

        service.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    streamSocket = new Socket(InetAddress.getByName("16p23b6752.51mypc.cn").getHostAddress(), 52051);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(streamSocket == null){
                    return;
                }

                String pending = "#" + GlobalInfo.myName + "$" + whom + "%";
                byte[] inputBuffer = pending.getBytes();
                OutputStream os = null;
                try {
                    os = streamSocket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(os == null){
                    return;
                }
                BufferedOutputStream writer = new BufferedOutputStream(os);

                try {
                    writer.write(inputBuffer, 0, inputBuffer.length);
                    Log.d("yanyao -- connection", pending);
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                buildServer();
            }
        });
    }

    static void sendPcm(final byte[] pcm) {

        service.execute(new Runnable() {
            @Override
            public void run() {

                OutputStream os = null;
                try {
                    os = streamSocket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(os == null){
                    return;
                }
                BufferedOutputStream writer = new BufferedOutputStream(os);

                try {
                    writer.write(pcm, 0, pcm.length);
                    Log.d("yanyao -- sent", String.valueOf(pcm.length));
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static void clean() {
        try {
            streamSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
