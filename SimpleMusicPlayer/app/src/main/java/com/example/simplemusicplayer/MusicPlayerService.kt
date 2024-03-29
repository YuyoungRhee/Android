package com.example.simplemusicplayer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.Build.VERSION_CODES
import android.widget.Toast


class MusicPlayerService : Service() {


    var mMediaPlayer: MediaPlayer? = null //미디어플레이어 객체 초기화
    var mBinder: MusicPlayerBinder = MusicPlayerBinder()

    inner class MusicPlayerBinder : Binder() {
        fun getService(): MusicPlayerService {
            return this@MusicPlayerService
        }
    }

    //서비스가 생성될 때 딱 한 번만 실행
    override fun onCreate() {
        super.onCreate()
        startForegroundService()
    }

    //바인드
    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    //시작된 상태 & 백그라운드에서 존재할 수 있게 됨
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY //시스템이 서비스를 중단하면 서비스를 다시 실행하고 onStartCommand() 함수를 호출
    }

    //알림 채널을 만들고 startForeground() 함수를 실행함
    fun startForegroundService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NOTIFICATION_SERVICE)
                    as NotificationManager

            val mChannel = NotificationChannel(
                "CHANNEL_ID",
                "CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(mChannel)
        }

        //알림 생성
        val notification: Notification = Notification.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_play)
            .setContentTitle("뮤직 플레이어 앱")
            .setContentText("앱이 실행 중입니다.")
            .build()

        startForeground(1, notification)
    }

    //서비스 종료 (알림을 해제
    override fun onDestroy() {
        super.onDestroy()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true);
        }
    }
    fun isPlaying() : Boolean {
        return (mMediaPlayer !=null && mMediaPlayer?.isPlaying?:false)
    }

    fun play() {
        if (mMediaPlayer == null) {
            //음악 파일의 리소스를 가져와 미디어 플레이어 객체를 할당해줍니다.
            mMediaPlayer = MediaPlayer.create(this, R.raw.chocolate)

            mMediaPlayer?.setVolume(1.0f, 1.0f);
            mMediaPlayer?.isLooping = true
            mMediaPlayer?.start()
        } else { //음악이 재생 중인 경우
            if (mMediaPlayer!!.isPlaying) {
                Toast.makeText(
                    this, "이미 음악이 실행중입니다.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                mMediaPlayer?.start()
            }
        }
    }

    fun pause() {
        mMediaPlayer?.let {
            if(it.isPlaying) {
                it.pause()
            }
        }
    }

    fun stop() {
        mMediaPlayer?.let {
            if(it.isPlaying) {
                it.stop()
                it.release()
                mMediaPlayer = null
            }
        }
    }


}