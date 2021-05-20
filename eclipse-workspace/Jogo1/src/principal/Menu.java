package principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import fase.*;
import janela.Janela;
import javax.swing.Timer;

public class Menu extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JLabel jb = new JLabel("teste");
	private JButton bt = new JButton("Fase 1");
	private JButton bt2 = new JButton("Fase 2");
	private static Fase faseAtual;	
	private static Menu newMenu;
	private Timer tempoDeFase;
	public Menu() {
		//setLayout(new BorderLayout());
			setLayout(null);
			jb.setBounds(Janela.getLarguraJanela()/2 - jb.getText().length(),100,100,100);
			bt.setBounds(100,200,100,100);
			bt.addMouseListener(this);
			bt2.setBounds(100,400,100,100);
			bt2.addMouseListener(this);
			add(jb);
			add(bt);
			add(bt2);

//			tempoDeFase = new Timer(5000,(ActionListener) this);
//			tempoDeFase.start();

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == bt) {
			bt.removeMouseListener(this);
			//removeAll();
			faseAtual = new Fase1();
			faseAtual.setBounds(0,0,Janela.getLarguraJanela(),Janela.getAlturaJanela());
			add(faseAtual);
			validate();
			repaint();
			
			//removeAll();

			
			validate();
			repaint();
			

            
		}
		if(e.getSource() == bt2) {
			bt2.removeMouseListener(this);
			removeAll();
			faseAtual = new Fase2();
			faseAtual.setBounds(0,0,Janela.getLarguraJanela(),Janela.getAlturaJanela());
			add(faseAtual);
			validate();
			repaint();
		}
	}
//	public void actionPerformed(ActionEvent e) {
//		System.out.println("BBBBBBB");
//	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
//	public static void setFase(Fase f) {	
//		this.faseAtual = f;
//		newMenu = new Menu();
//		newMenu.setBounds(0,0,Janela.getLarguraJanela(), Janela.getAlturaJanela());
//		add(newMenu);
//	}
}