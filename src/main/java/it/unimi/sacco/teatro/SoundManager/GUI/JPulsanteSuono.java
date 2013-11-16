package it.unimi.sacco.teatro.SoundManager.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import it.unimi.sacco.teatro.SoundManager.SoundPlayer.MP3Player;

public class JPulsanteSuono extends JButton implements ActionListener
{
	public JPulsanteSuono(MP3Player player, String nome)
	{
		super(nome);
		this.player = player;
		this.addActionListener(this);
	}
	
	public MP3Player getPlayer()
	{
		return this.player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this)
		{
			this.player.play();
		}
		else if(!this.player.playEnded())
		{
			this.player.stop();
		}
	}

	private MP3Player	player;

	private static final long	serialVersionUID	= -2600969788128472723L;

}
