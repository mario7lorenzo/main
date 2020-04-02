package seedu.address.model.hirelah;

public class IntervieweeToScore {
    private Interviewee interviewee;
    private double score;

    public IntervieweeToScore(Interviewee interviewee, double score) {
        this.interviewee = interviewee;
        this.score = score;
    }

    public Interviewee getInterviewee() {
        return this.interviewee;
    }

    public double getScore() {
        return this.score;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof IntervieweeToScore
                && interviewee.equals(((IntervieweeToScore)other).interviewee)
                && score == ((IntervieweeToScore)other).score);
    }
}
