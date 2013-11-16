package it.unimi.sacco.teatro.SoundManager.GUI;

import java.awt.FlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Gui implements Runnable
{
	public Gui(FactoryPulsanti factory)
	{
		this.factory = factory;
		this.creaFinestra();
		this.creaPulsanti();
	}

	@Override
	public void run()
	{
		this.showGui();
	}

	private void creaPulsanti()
	{
		this.stop = new JPulsanteStop();		
		this.listaPulsanti = new ArrayList<JPulsanteSuono>();
		for (Entry<String, List<JPulsanteSuono>> categoria : this.factory
				.getPulsanti())
		{
			Box box = new Box(BoxLayout.Y_AXIS);
			JLabel label = new JLabel(categoria.getKey());
			box.add(label);
			
			List<JPulsanteSuono> pulsanti = categoria.getValue();
			
			for (JPulsanteSuono pulsante : pulsanti)
			{
				box.add(pulsante);
				this.listaPulsanti.add(pulsante);
				this.stop.addActionListener(pulsante);
			}
			
			this.finestra.add(box);
		}
		
		this.finestra.add(this.stop);
	}

	private void creaFinestra()
	{
		this.finestra = new JFrame("SoundManager 1.0");
		this.finestra.setLayout(new FlowLayout());
		this.finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.finestra.setResizable(false);
	}

	private void showGui()
	{
		this.finestra.pack();
		this.finestra.setVisible(true);
	}

	private JPulsanteStop stop;

	private JFrame finestra;

	private FactoryPulsanti factory;

	private List<JPulsanteSuono> listaPulsanti;

}
