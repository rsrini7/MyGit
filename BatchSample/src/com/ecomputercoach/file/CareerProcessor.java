package com.ecomputercoach.file;

import org.springframework.batch.item.ItemProcessor;

public class CareerProcessor implements ItemProcessor<Player, Player> {
	public Player process(Player player) throws Exception {
		if (player == null)
			return null;

		player.setCareerLength(player.getCareerPeriod().getFinalYear() - player.getCareerPeriod().getDebutYear());
		player.setFullName(player.getFirstName() + " " + player.getLastName());
		return player;
	}
}