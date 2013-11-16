package it.unimi.sacco.teatro.SoundManager.GUI;

import it.unimi.sacco.teatro.SoundManager.SoundPlayer.MP3Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FactoryPulsanti
{
	public FactoryPulsanti()
	{
		this.mappaPulsanti = new HashMap<String, List<JPulsanteSuono>>();
		
	}
	public void addPlayer(List<MP3Player> listaPlayer, String nomeLista)
	{
		List<JPulsanteSuono> listaPulsanti = this.creaPulsanti(listaPlayer);
		this.mappaPulsanti.put(nomeLista, listaPulsanti);
	}

	public Set<Entry<String, List<JPulsanteSuono>>> getPulsanti()
	{
		return this.mappaPulsanti.entrySet();
	}

	private List<JPulsanteSuono> creaPulsanti(List<MP3Player> lista)
	{
		List<JPulsanteSuono> listaPulsanti = new ArrayList<JPulsanteSuono>();
		for (MP3Player player : lista)
		{
			String nome = player.getName();
			JPulsanteSuono pulsante = new JPulsanteSuono(player, nome);
			listaPulsanti.add(pulsante);
		}

		return listaPulsanti;
	}

	private Map<String, List<JPulsanteSuono>> mappaPulsanti;

}
