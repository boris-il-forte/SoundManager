/**
 * 
 */
package it.unimi.sacco.teatro.SoundManager;

import it.unimi.sacco.teatro.SoundManager.GUI.FactoryPulsanti;
import it.unimi.sacco.teatro.SoundManager.GUI.Gui;
import it.unimi.sacco.teatro.SoundManager.SoundPlayer.SoundFactory;

import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args)
	{
		
		Preferencemanager preferenceManager = new Preferencemanager(needPreferenceClean(args));
		
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		SoundFactory musicFactory;
		try
		{
			String soundDir = preferenceManager.getPreference();
			musicFactory = new SoundFactory(executor, soundDir, "musica");
			SoundFactory noiseFactory = new SoundFactory(executor, soundDir, "rumori");
			FactoryPulsanti factory = new FactoryPulsanti();
			factory.addPlayer(musicFactory.getListaPlayer(), musicFactory.getNomeLista());
			factory.addPlayer(noiseFactory.getListaPlayer(), noiseFactory.getNomeLista());
			
			Gui gui = new Gui(factory);
			SwingUtilities.invokeLater(gui);
		}
		catch (IOException e)
		{
			System.out.println("An error occurred...");
			e.printStackTrace();
		}
		
	}
	
	
	static private boolean needPreferenceClean(String[] args)
	{
		List<String> arguments = Arrays.asList(args);
		return arguments.contains("-clean");
	}
}
