package com.nighthawk.spring_portfolio.mvc.checkpoints;
import java.util.ArrayList;
import javax.persistence.Entity;

import lombok.*;

@Data
@Entity

public class Stage extends DataObject implements DataObject.InterfaceToJson {

    // Stage data
    private String name;
	private ArrayList<String> stages = new ArrayList<String>();

    public Stage(String stages)
	{
		super("Stage");
        this.stages = stages; 
	}

    /* 'Generics' requires toString override
    * toString provides data based off of Static Key setting
    */

    // to string to json
	@Override
	public String toString()
	{
		return "Name: " + name + ", Stage: " + stages;
	}

	/* 'Generics' requires toJson override
	 * toJson provides data based off of Static Key setting
	 */
	@Override
	public String toJson()
	{
		// return all data in json format
		return "{ \"Name\": \"" + name + "\", \"Stage\": \"" + stages + "\"}";
	}

    public static Stage[] stages() {
        return new Stage[] {
            new Stage("Data Structures"),
            new Stage("Sorting"),
            new Stage ("Arrays")
        };
    }

    public static void main(String[] args)
	{
        Stage testStage = new Stage("Data Structures");
        System.out.println("Stage Class Test");
		System.out.println(testStage.toString());
		System.out.println(testStage.toJson());
	}
}
