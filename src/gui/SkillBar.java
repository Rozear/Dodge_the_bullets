package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import logic.PlayerSkill;

public class SkillBar extends HBox {
	
	public SkillBar(){
		super(40, PlayerSkill.SKILL_1, PlayerSkill.SKILL_2, PlayerSkill.SKILL_3, PlayerSkill.SKILL_4);
		this.setAlignment(Pos.CENTER);
	}

	public void draw() {
		// TODO Auto-generated method stub
		PlayerSkill.SKILL_1.render();
		PlayerSkill.SKILL_2.render();
		PlayerSkill.SKILL_3.render();
		PlayerSkill.SKILL_4.render();
	}
	
}
