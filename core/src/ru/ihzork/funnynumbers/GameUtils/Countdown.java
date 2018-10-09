package ru.ihzork.funnynumbers.GameUtils;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Countdown  {

    private int seconds;
    private boolean cancelled;
    Label infos;

    class TickTask extends Task {



        private final Runnable doneCallback;

        public TickTask(final Runnable doneCallback) {
            this.doneCallback = doneCallback;
        }

        @Override
        public void run() {

            if (seconds-- == 0 || cancelled) {
                this.cancel();
                doneCallback.run();
            }
        }


    }
    public Countdown(Label _infos) {
        this.infos = _infos;
    }
    public void start(int seconds, final Runnable doneCallback) {
        this.cancelled = false;
        this.seconds = seconds;

        Timer.schedule(new TickTask(doneCallback), 0, 1);
    }

    public void update() {
        if (isCounting()) {

            infos.setText(String.valueOf(seconds));
            //FontHelper.draw(seconds, position);
        }
    }
    public void cancel() {
        cancelled = true;
    }

    public boolean isCounting() {
        return seconds > 0 && !cancelled;
    }
}