package it.unimi.sacco.teatro.SoundManager.SoundPlayer;

import it.unimi.sacco.teatro.SoundManager.Exceptions.NoMorePlayersException;
import it.unimi.sacco.teatro.SoundManager.utils.ListFolder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javazoom.jl.decoder.JavaLayerException;

public class SoundFactory
{
	public SoundFactory(ExecutorService executor, String subfolder)
	{
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
		try
		{
			String[] entries = ListFolder.listAlphabetically(this.subFolder);
			this.resources.addAll(Arrays.asList(entries));
		}
		catch (URISyntaxException | IOException e)
		{
			e.printStackTrace();
		}

	}

	private void creaPlayers()
	{
		this.players = new ArrayList<MP3Player>();
		for (String resource : this.resources)
		{
			if (resource.contains(".mp3"))
			{
				URL resourcePath = this.getClass().getResource(
						"/" + this.subFolder + "/" + resource);
				System.out.println(resourcePath);
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
}
