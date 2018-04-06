package com.example.genji.am104_gson;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by genji on 4/2/17. MODEL created using
 * http://www.jsonschema2pojo.org/
 * settings
 * - Source type: JSON
 * - Annotation style: GSON
 */

// il campionato complessivo

public class Pojo {
    @SerializedName("name")
    @Expose
    private String name; // es.  "Serie A 2016/17"
    @SerializedName("rounds")
    @Expose
    private List<Round> rounds = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
}

// la singola partita

class Match {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("team1")
    @Expose
    private Team team1;
    @SerializedName("team2")
    @Expose
    private Team team2;
    @SerializedName("score1")
    @Expose
    private Integer score1;
    @SerializedName("score2")
    @Expose
    private Integer score2;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

}

// la giornata

class Round {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("matches")
    @Expose
    private List<Match> matches = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

}


class Team {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}

