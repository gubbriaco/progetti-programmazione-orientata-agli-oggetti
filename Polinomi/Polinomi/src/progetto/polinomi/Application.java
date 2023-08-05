package progetto.polinomi;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import javax.swing.JOptionPane;

public class Application {
	
	public static void main(String[] args) {
		
		JFrame f = new Finestra();
		f.setVisible(true);
		
	}//main
	
	
	@SuppressWarnings("serial")
	static class Finestra extends JFrame{
		protected static JFrame f;
		protected static String titolo;
		protected static String titolob;
		private File fileDiSalvataggio = null;
		protected static String stringa;
		protected static JPanel princ, operazioni;
		protected JPanel p;
		protected static JMenuBar mb;
		protected static JMenu fileMenu, comandiMenu, helpMenu;
		protected static JMenuItem nuova, apri, salva, salvaConNome, esci, aggiungiPolinomio, about;
		
		
		private boolean consensoUscita() {
			   int option = JOptionPane.showConfirmDialog(null, "Continuare ?", "Uscendo si perderanno tutti i dati!", JOptionPane.YES_NO_OPTION);
			   return option == JOptionPane.YES_OPTION;
		}//consensoUscita
		
		public Finestra() {
			f = this;
			titolo = "Polinomi";
			titolob = "Polinomi";
			setTitle(titolo);
			setSize(800, 600);
			setLocation(350, 150);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					if(consensoUscita())
						System.exit(0);
				}//windowClosing
			});
			this.add(p = new JPanel(), BorderLayout.NORTH);
			p.setVisible(true);
			
			mb = new JMenuBar();
			this.setJMenuBar(mb);
			
			fileMenu = new JMenu("File");
			comandiMenu = new JMenu("Comandi");
			helpMenu = new JMenu("Help");
			
			mb.add(fileMenu);
			mb.add(comandiMenu);
			mb.add(helpMenu);
			
			nuova = new JMenuItem("Nuovo");
			fileMenu.add(nuova);
			nuova.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame f = new ScegliTipologia();
					f.setVisible(true);
				}//actionPerformed
			});
			
			
			class SalvaApriListener implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					if( e.getSource() == salva ){
			  			   JFileChooser chooser = new JFileChooser();
			  			   try{
			  				   if( fileDiSalvataggio != null ){
			  					   int ans=JOptionPane.showConfirmDialog(null, "Sovrascrivere " + fileDiSalvataggio.getAbsolutePath() + " ?");
								   if( ans==0 )
									   salva(fileDiSalvataggio.getAbsolutePath());
								   else
									   JOptionPane.showMessageDialog(null, "Nessun salvataggio!");
								   return;
							   }//if
			  				   if( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ){
			  					   fileDiSalvataggio = chooser.getSelectedFile();
			  					   Application.Finestra.f.setTitle(Application.Finestra.titolo + " - " + fileDiSalvataggio.getName());
			  					   salva(fileDiSalvataggio.getAbsolutePath());
			  				   }//if
			  				   if( fileDiSalvataggio != null ){
			  					   salva(fileDiSalvataggio.getAbsolutePath());
			  					   Application.Finestra.f.setTitle(Application.Finestra.titolo + " - " + fileDiSalvataggio.getName());
			  				   }//if
			  				   else
			  					   JOptionPane.showMessageDialog(null, "Nessun Salvataggio!");
			  			   }catch( Exception exc ){
			  				   exc.printStackTrace();
			  			   }//catch
			  		   }//if
			  		   else if( e.getSource() == salvaConNome ){
			  			   JFileChooser chooser = new JFileChooser();
			  			   try{
			  				   if( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ){
			  						   fileDiSalvataggio=chooser.getSelectedFile();
			  						   Application.Finestra.f.setTitle(Application.Finestra.titolo + " - " + fileDiSalvataggio.getName());
			  					   }//if
			  				   if( fileDiSalvataggio != null ){
			  					 salva(fileDiSalvataggio.getAbsolutePath());
			  				   }//if
			  				   else
			  					   JOptionPane.showMessageDialog(null, "Nessun Salvataggio!");
			  			   }catch( Exception exc ){
			  				   exc.printStackTrace();
			  			   }//catch  			   
			  		   }//else if
			  		   else if( e.getSource() == apri ){
			  			   JFileChooser chooser = new JFileChooser();
			  			   try{
			  				   if( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
			  					   if( !chooser.getSelectedFile().exists() ){
			  						   JOptionPane.showMessageDialog(null, "File inesistente!"); 
			  					   }//if
			  					   else{	
			  						   fileDiSalvataggio=chooser.getSelectedFile();
			  						   Application.Finestra.f.setTitle(Application.Finestra.titolob + " - " + fileDiSalvataggio.getName());
			  						   try{
			  							 ripristina(fileDiSalvataggio.getAbsolutePath());
			  						   }catch(IOException ioe){
			  							   JOptionPane.showMessageDialog(null, "Fallimento apertura. File malformato!");
			  						   }//catch
			  					   }//else
			  				   }//if
			  				   else
			  					   JOptionPane.showMessageDialog(null, "Nessuna apertura!");
			  			   }catch( Exception exc ){
			  				   exc.printStackTrace();
			  			   }//catch
			  		   }//else if
				}//actionPerformed
			}//Listener
			
			apri = new JMenuItem("Apri");
			fileMenu.add(apri);
			apri.addActionListener(new SalvaApriListener());
			
			fileMenu.addSeparator();
			
			salva = new JMenuItem("Salva");
			fileMenu.add(salva);
			salva.addActionListener(new SalvaApriListener());
			
			salvaConNome = new JMenuItem("Salva con nome");
			fileMenu.add(salvaConNome);
			salvaConNome.addActionListener(new SalvaApriListener());
			
			fileMenu.addSeparator();
			
			esci = new JMenuItem("Esci");
			fileMenu.add(esci);
			esci.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == esci) {
						if(consensoUscita())
							System.exit(0);
					}//if
				}//actionPerformed
			});
			
			aggiungiPolinomio = new JMenuItem("Aggiungi polinomio");
			comandiMenu.add(aggiungiPolinomio);
			
			class AggPolListener implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == aggiungiPolinomio) {
						JFrame aggPol = new AggiungiPolinomio();
						aggPol.setVisible(true);
					}//if
				}//actionPerformed
			}//ascAggPol
			aggiungiPolinomio.addActionListener(new AggPolListener());
			
			about = new JMenuItem("About");
			helpMenu.add(about);
			class AboutListener implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == about) {
						JFrame af = new AboutFinestra();
						af.setVisible(true);
					}//if
				}//actionPerformed
			}//AboutListener
			about.addActionListener(new AboutListener());
			
			menuIniziale();
			
			princ = new PannelloPolinomi();
			this.add(princ, BorderLayout.WEST);
			this.add(PannelloPolinomi.scroll);
			
			operazioni = new PannelloOperazioni();
			this.add(operazioni, BorderLayout.EAST);
			operazioni.setVisible(false);
			
		}//cFinestra
		
		private void salva(String nome) throws IOException {
			PrintWriter pw = new PrintWriter(new FileWriter(nome));
			for(Component cb:PannelloPolinomi.box.getComponents())
				pw.println(((JCheckBox) cb).getText());
			pw.close();
		}//salva
		
		private void ripristina(String nome) throws IOException {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(nome));
			PannelloPolinomi.llCB.clear();
			for(;;) {
				String linea = br.readLine();
				if(linea == null)
					break;
				stringa = linea;
				aggiungiPol(linea);
			}//for;;
		}//ripristina
		
		protected static void aggiungiPol(String s) {
			if(IsPolinomio.riconosciPolinomio(s) && !AggiuntaPolinomio.polinomioNonPresente(s)) {
				PannelloPolinomi.llP.add(IsPolinomio.creaPolinomio(s));
				JCheckBox cb = new JCheckBox();
				Font f = new Font("Helvetica", Font.ITALIC, 55);
				cb.setText(s);
				cb.setFont(f);
				PannelloPolinomi.llCB.add(cb);
				PannelloPolinomi.box.add(cb);
				cb.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == cb) {
							PannelloOperazioni.p.setVisible(true);
						}//if
					}//actionPerformed
				});
				PannelloPolinomi.p.revalidate();
			}//if
		}//aggiungiPol
		
		private void menuIniziale() {
			apri.setEnabled(false);
			salva.setEnabled(false);
			salvaConNome.setEnabled(false);
			aggiungiPolinomio.setEnabled(false);
		}//menuIniziale
		
		
		class erroreNessunPolinomioPresente extends JFrame {
			protected JFrame s;
			protected JPanel q1, q2;
			protected JLabel testo;
			protected JButton ok;
			
			public erroreNessunPolinomioPresente() {
				s = this;
				this.setTitle("Errore");
				this.setSize(400, 100);
				this.setLocation(535, 300);
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				q1 = new JPanel();
				q2 = new JPanel();
				this.add(q1, BorderLayout.NORTH);
				this.add(q2, BorderLayout.SOUTH);
				testo = new JLabel("Nessuno presente. Aggiungi un nuovo polinomio!");
				q1.add(testo, BorderLayout.CENTER);
				ok = new JButton();
				ok.setText("OK");
				q2.add(ok, BorderLayout.CENTER);
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						s.setVisible(false);
					}//actionPerformed
				});
			}
		}//nessunPolinomioPresente
		
	}//Finestra
	
}//Application
