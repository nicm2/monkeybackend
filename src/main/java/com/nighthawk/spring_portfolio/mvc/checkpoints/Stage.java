import javax.persistence.Entity;

import lombok.*;

@Data
@Entity

public class Stage {

    // Stage data
	private String name;
	private int stage;
	private ArrayList<String> listLevels; 
    // name, what number stage you are on, use list of levels, make main methods
    // to string to json
}
