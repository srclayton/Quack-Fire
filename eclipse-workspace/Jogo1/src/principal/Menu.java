package principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fase.*;
import janela.Janela;

public class Menu extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JLabel jb = new JLabel("teste");
	private JButton bt = new JButton("Fase 1");
	private JButton bt2 = new JButton("Fase 2");
	private Fase faseAtual;

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
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == bt) {
			bt.removeMouseListener(this);
			removeAll();
			faseAtual = new Fase1();
			faseAtual.setBounds(0,0,Janela.getLarguraJanela(),Janela.getAlturaJanela());
			add(faseAtual);
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
}
