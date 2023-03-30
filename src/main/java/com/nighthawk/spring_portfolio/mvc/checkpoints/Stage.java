package com.nighthawk.spring_portfolio.mvc.checkpoints;
import java.util.ArrayList;
import javax.persistence.Entity;

import lombok.*;

@Data
@Entity

public class Stage extends DataObject implements DataObject.InterfaceToJson {

    // Stage data
    private ArrayList<Integer> levels;
    private String playerName;
    private Level currentLevel;
    private Object stage;

    public Stage(String playerName) {
        this.playerName = playerName;
        this.levels = new ArrayList<Integer>();
        this.stage = Stage.LEVEL_ONE;
    }

    public Stage(String playerName) {
        this.playerName = playerName;
        this.levels = new ArrayList<Integer>();
        this.currentLevel = Level.ONE;
    }

    public ArrayList<Integer> getLevels() {
        return this.levels;
    }

    public void setLevels(ArrayList<Integer> levels) {
        this.levels = levels;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    /* 'Generics' requires toString override
    * toString provides data based off of Static Key setting
    */

    // to string to json
	@Override
	public String toString()
	{
		return "Name: " + playerName + ", Stage: " + stage + ", Level: " + currentLevel;
	}

	/* 'Generics' requires toJson override
	 * toJson provides data based off of Static Key setting
	 */
	@Override
	public String toJson()
	{
		// return all data in json format
		return "{ \"Name\": \"" + playerName + "\", \"Stage\": \"" + stage + "\", \"Level\": \"" + currentLevel + "\" }";
	}

    public static Stage[] stages() {
        return new Stage[] {
            new Stage("Data Structures"),
            new Stage("Sorting"),
            new Stage ("Arrays")
        };
    }

    enum Level {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    }

    public static void main(String[] args)
	{
        Stage testStage = new Stage("Data Structures");
        System.out.println("Stage Class Test");
		System.out.println(testStage.toString());
		System.out.println(testStage.toJson());
	}
}
