package it.unimi.sacco.teatro.SoundManager.GUI;

import java.awt.Component;
import java.awt.Container;
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
		this.finestra.add(Box.createHorizontalStrut(10));
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
				box.add(Box.createVerticalStrut(5));
				box.add(pulsante);
				this.listaPulsanti.add(pulsante);
				this.stop.addActionListener(pulsante);
			}

			box.add(Box.createVerticalStrut(10));
			box.setAlignmentY(Component.TOP_ALIGNMENT);

			this.finestra.add(box);
			this.finestra.add(Box.createHorizontalStrut(25));
		}

		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(Box.createVerticalGlue());
		box.add(this.stop);
		box.add(Box.createVerticalGlue());
		this.finestra.add(box);
		this.finestra.add(Box.createHorizontalStrut(10));
	}

	private void creaFinestra()
	{
		this.finestra = new JFrame("SoundManager 2.0");
		Container panel = this.finestra.getContentPane();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
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
