package com.nighthawk.spring_portfolio.mvc.checkpoints;
import java.util.ArrayList;
import javax.persistence.Entity;

import lombok.*;

@Data
@Entity

public class Stage extends DataObject implements DataObject.InterfaceToJson {
    private ArrayList<Integer> levels;
    private String playerName;
    private Level currentLevel;

    // class for stages
    public Stage(String playerName) {
        super("Stage");
        this.playerName = playerName;
        this.levels = new ArrayList<Integer>();
        this.currentLevel = Level.ONE;
    }
    // levels
    enum Level {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    }

    // arraylist of levels
    public ArrayList<Integer> getLevels() {
        return this.levels;
    }

    // setter for levels
    public void setLevels(ArrayList<Integer> levels) {
        this.levels = levels;
    }
    
    // getter for playername
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

    // to string to json
	@Override
	public String toString()
	{
		return "Name: " + playerName + ", Level: " + currentLevel;
	}

	/* 'Generics' requires toJson override
	 * toJson provides data based off of Static Key setting
	 */
	@Override
	public String toJson()
	{
		// return all data in json format
		return "{ \"Name\": \"" + playerName + "\", \"Level\": \"" + currentLevel + "\" }";
	}

    public static void main(String[] args) {
        Stage stage = new Stage("John");
        stage.getLevels().add(1); 
        stage.getLevels().add(2);
        stage.setCurrentLevel(Level.TWO);
        System.out.println(stage.toString());
        System.out.println(stage.toJson());
    }
}