package it.unimi.sacco.teatro.SoundManager.GUI;

import it.unimi.sacco.teatro.SoundManager.SoundPlayer.MP3Player;

import javax.swing.JButton;

public class JPulsanteStop extends JButton
{
	public JPulsanteStop()
	{
		super("Stop");
	}
	
	public MP3Player getPlayer()
	{
		return this.player;
	}
	
	private MP3Player	player;

	private static final long	serialVersionUID	= -2600969788128472723L;

}
