package sample;

public class Person extends StatsController{
    private String name;
    private String score;
    private String time;


    public Person(String name, String score, String time) {
        this.name = name;
        this.score=score;
        this.time = time;
    }
    public String toString(){return name+" "+score+" "+time;}

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }
    public String getTime() {
        return time;
    }

    public void setName(String n) {
        name=n;
    }

    public void setScore(String s) {
        score=s;
    }
    public void setTime(String t) {
        time=t;
    }

}