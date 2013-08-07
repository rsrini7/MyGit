package com.ecomputercoach.file;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;


public class PlayerFieldSetMapper implements FieldSetMapper<RealPlayer> {

	public RealPlayer mapFieldSet(FieldSet fs) {
		
		if(fs == null){
			return null;
		}
		
		RealPlayer realPlayer = new RealPlayer();
		
		
		realPlayer.setId(fs.readString("ID"));
		realPlayer.setLastName(fs.readString("lastName"));
		realPlayer.setFirstName(fs.readString("firstName"));
		realPlayer.setPosition(fs.readString("position"));
		
		return realPlayer;
	}
	
}