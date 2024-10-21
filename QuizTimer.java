public class QuizTimer {
    private long remainingTime;
    private boolean isTimeUp;

    public QuizTimer(long duration) {
        this.remainingTime = duration;
        this.isTimeUp = false;
    }

    public void startTimer() {
        Thread countdownThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (!isTimeUp && remainingTime > 0) {
                try {
                    Thread.sleep(1000);
                    remainingTime = 10 * 60 * 1000 - (System.currentTimeMillis() - startTime);
                    if (remainingTime <= 0) {
                        isTimeUp = true;
                        System.out.println("\nTime is up! The quiz has ended.");
                        System.exit(0);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Countdown timer interrupted.");
                }
            }
        });
        countdownThread.start();
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public boolean isTimeUp() {
        return isTimeUp;
    }
}