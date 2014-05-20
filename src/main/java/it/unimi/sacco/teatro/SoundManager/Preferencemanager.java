package it.unimi.sacco.teatro.SoundManager;

import java.io.File;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;

public class Preferencemanager
{
	Preferencemanager(boolean cleanPreferences)
	{
		preferences = Preferences.userRoot().node(Soundmanager.class.getName());

		try
		{
			if (cleanPreferences)
				preferences.clear();
		}
		catch (BackingStoreException e)
		{
			System.out.println("Errore dello storage delle java preferences..");
			e.printStackTrace();
		}
	}

	public String getPreference() throws IOException
	{

		String soundDir = preferences.get(DIR_PREFERENCE, "");

		if (soundDir.isEmpty())
		{
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.showOpenDialog(null);
			File dir = fc.getSelectedFile();
			soundDir = dir.getCanonicalPath();
			preferences.put(DIR_PREFERENCE, soundDir);
		}

		return soundDir;
	}

	private Preferences preferences;

	private static final String DIR_PREFERENCE = "soundDir";
}
