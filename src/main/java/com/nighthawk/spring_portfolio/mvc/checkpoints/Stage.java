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

    public Stage(String playerName) {
        this.playerName = playerName;
        this.levels = new ArrayList<Integer>();
        this.currentLevel = Level.ONE;
    }
    
    enum Level {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
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

    public static void main(String[] args) {
        Stage stage = new Stage("John");
        stage.getLevels().add(1); 
        stage.getLevels().add(2);
        stage.setCurrentLevel(Level.TWO);
        System.out.println(stage.toString());
        System.out.println(stage.toJson());
    }
}