package it.unimi.sacco.teatro.SoundManager.SoundPlayer;

import it.unimi.sacco.teatro.SoundManager.Exceptions.NoMorePlayersException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javazoom.jl.decoder.JavaLayerException;

public class SoundFactory
{
	public SoundFactory(ExecutorService executor, String soundDir,
			String subfolder) throws MalformedURLException
	{
		this.soundDir = soundDir;
		this.subFolder = subfolder;
		this.resources = new ArrayList<String>();
		this.executor = executor;
		this.aggiungiPlayer();
		this.creaPlayers();
	}

	public String getNomeLista()
	{
		return this.subFolder;
	}

	public List<MP3Player> getListaPlayer()
	{
		return this.players;
	}

	public MP3Player getPlayer() throws NoMorePlayersException
	{
		if (this.players.isEmpty())
		{
			throw new NoMorePlayersException("Non ci sono più players");
		}
		else
			return this.players.remove(0);
	}

	private void aggiungiPlayer()
	{
		File subFolderDir = new File(this.soundDir + "/" + this.subFolder);
		String[] entries = subFolderDir.list();
		this.resources.addAll(Arrays.asList(entries));
		Collections.sort(this.resources);
	}

	private void creaPlayers() throws MalformedURLException
	{
		this.players = new ArrayList<MP3Player>();
		for (String resource : this.resources)
		{
			if (resource != null && resource.contains(".mp3"))
			{
				URL resourcePath = new File(this.soundDir + "/"
						+ this.subFolder + "/" + resource).toURI().toURL();
				MP3Player player = this.creaPlayer(resourcePath, this.executor);
				if (player != null)
					this.players.add(player);
			}
		}
	}

	private MP3Player creaPlayer(URL resourcePath, ExecutorService executor)
	{
		MP3Player player = null;
		try
		{
			player = new MP3Player(resourcePath, executor);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File " + resourcePath + " non trovato");
		}
		catch (JavaLayerException e)
		{
			System.out.println("Errore libreria per il player...");
		}
		return player;
	}

	private List<MP3Player> players;

	private List<String> resources;

	private ExecutorService executor;

	private String subFolder;

	private String soundDir;
}
