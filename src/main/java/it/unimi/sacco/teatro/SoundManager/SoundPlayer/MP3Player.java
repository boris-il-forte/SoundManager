package it.unimi.sacco.teatro.SoundManager.SoundPlayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.concurrent.ExecutorService;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * 
 * @author dave
 */
public class MP3Player implements Runnable
{

	/**
	 * Creates a new instance of MP3Player
	 * 
	 * @param executor
	 * 
	 * @throws FileNotFoundException
	 * @throws JavaLayerException
	 */
	MP3Player(URL url, ExecutorService executor) throws FileNotFoundException, JavaLayerException
	{
		this.path = url;
		this.openStream();
		this.player = new Player(file);
		this.soundName = this.getFilname(url);
		this.executor = executor;
	}

	public String getName()
	{
		return this.soundName;
	}

	public boolean playEnded()
	{
		return this.player.isComplete();
	}

	public void play()
	{
		this.executor.submit(this);
	}

	public void stop()
	{
		try
		{
			this.player.close();
			this.openStream();
			this.player = new Player(this.file);
		}
		catch (JavaLayerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void pausa()
	{
		try
		{
			int posizione = this.player.getPosition();
			this.stop();
			this.file.skip(posizione);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		try
		{
			this.player.play();
		}
		catch (Exception e)
		{
		}
		finally
		{
			this.stop();
		}
	}

	private void openStream()
	{
		try
		{
			this.file = this.path.openStream();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	private String getFilname(URL url)
	{

		try
		{
			String pezzi[] = URLDecoder.decode(url.toString(), "UTF-8").split("/");
			int index = pezzi.length - 1;
			String fileName = pezzi[index];
			return fileName.substring(0, fileName.lastIndexOf('.'));
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return null;
		}
		
		
		
	}

	private String			soundName;

	private Player			player;

	private InputStream		file;

	private URL			path;

	private ExecutorService	executor;

}
