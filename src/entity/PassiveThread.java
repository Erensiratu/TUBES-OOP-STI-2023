package entity;

import javax.sound.midi.VoiceStatus;

public abstract class PassiveThread extends Thread implements TickListener {
    private long duration ;

    public synchronized void durationDecrement(){
        duration = duration -  1000;
    }

    public synchronized long getDuration(){
        return duration;
    }

    public void setDuration(long duration){
        this.duration = duration;
    }

}