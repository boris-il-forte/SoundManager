/**
 * 
 */
package it.unimi.sacco.teatro.SoundManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unimi.sacco.teatro.SoundManager.GUI.FactoryPulsanti;
import it.unimi.sacco.teatro.SoundManager.GUI.Gui;
import it.unimi.sacco.teatro.SoundManager.SoundPlayer.SoundFactory;

import javax.swing.SwingUtilities;

/**
 * Java Audio Player
 * 
 * @author dave
 * 
 */
public class Soundmanager
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		SoundFactory musicFactory = new SoundFactory(executor, "musica");
		SoundFactory noiseFactory = new SoundFactory(executor, "rumori");
		FactoryPulsanti factory = new FactoryPulsanti();
		factory.addPlayer(musicFactory.getListaPlayer(), musicFactory.getNomeLista());
		factory.addPlayer(noiseFactory.getListaPlayer(), noiseFactory.getNomeLista());
		
		Gui gui = new Gui(factory);
		SwingUtilities.invokeLater(gui);
	}
}
