package com.luoye.bzmedia;

import android.content.Context;
import android.os.Build;

import com.bzcommon.utils.BZLogUtil;
import com.bzcommon.utils.BZSpUtils;
import com.luoye.bzmedia.bean.VideoRecordParams;
import com.luoye.bzmedia.utils.FFmpegCMDUtil;


/**
 * Created by zhandalin on 2020-05-25 16:27.
 * description:
 */
public class BZMedia {
    static {
        System.loadLibrary("bzffmpeg");
        System.loadLibrary("bzffmpegcmd");
        System.loadLibrary("bzmedia");
    }

    public static int init(Context context, boolean isDebug) {
        BZSpUtils.init(context);
        BZLogUtil.setShowLog(isDebug);
        FFmpegCMDUtil.showLog(isDebug);
        return initNative(context, isDebug, Build.VERSION.SDK_INT);
    }

    private native static int initNative(Context context, boolean isDebug, int sdkInt);

    /**
     * @param videoRecordParams 录制参数
     * @return 录制的时间, 单位毫秒, 小于0启动录制失败
     */
    public native static long startRecord(VideoRecordParams videoRecordParams);

    public native static long addAudioData(long nativeHandle, byte[] audioData, int length);

    public native static int setStopRecordFlag(long nativeHandle);

    public static native int stopRecord(long nativeHandle);

    public static native long updateVideoRecorderTexture(long nativeHandle, int textureId);

    public static native long addYUV420Data(long nativeHandle, byte[] data, long pts);

    public static native long addVideoPacketData(long nativeHandle, byte[] videoPacket, long size, long pts);

    public static native int adjustVideoSpeed(String srcVideoPath, String outputPath, float speed);

    public native static int test();

    public static enum PixelFormat {
        YUVI420, YV12, TEXTURE
    }
}
