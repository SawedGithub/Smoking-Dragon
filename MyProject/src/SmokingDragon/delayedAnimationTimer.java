package SmokingDragon;

import javafx.animation.AnimationTimer;

public abstract class delayedAnimationTimer extends AnimationTimer {

    private long sleepNs = 0;

    long prevTime = 0;

    public delayedAnimationTimer( long sleepMs) {
        this.sleepNs = sleepMs * 1_000_000;
    }

    public void handle(long now) {

         
        if ((now - prevTime) < sleepNs) {
            return;
        }

        prevTime = now;
        
        handle();
    }

    public abstract void handle();
    
}