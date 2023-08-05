package progetto.polinomi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import poo.polinomi.Polinomio;
import poo.polinomi.PolinomioLL;
import poo.polinomi.PolinomioSet;

@SuppressWarnings("serial")
public class PannelloOperazioni extends JPanel {

	protected static JPanel p;
	protected static JButton sommaPol, moltiplicazionePol, derivata, rimuovi, rimuoviTutto, calcolaValore;
	protected static Box box;
	protected Polinomio pol1, pol2, polris;
		
	public PannelloOperazioni() {
		
		p = this;
		
		Font f = new Font("Tahoma", Font.CENTER_BASELINE, 10);
		
		sommaPol = new JButton();
		sommaPol.setText("POLINOMIO + POLINOMIO");
		sommaPol.setFont(f);
		
		moltiplicazionePol = new JButton();
		moltiplicazionePol.setText("POLINOMIO x POLINOMIO");
		moltiplicazionePol.setFont(f);
		
		derivata = new JButton();
		derivata.setText("DERIVATA(POLINOMIO)");
		derivata.setFont(f);
		
		rimuovi = new JButton();
		rimuovi.setText("RIMUOVI");
		rimuovi.setFont(f);
		
		rimuoviTutto = new JButton();
		rimuoviTutto.setText("SVUOTA");
		rimuovi.setFont(f);
		
		calcolaValore = new JButton();
		calcolaValore.setText("CALCOLA VALORE");
		calcolaValore.setFont(f);
		
		box = Box.createVerticalBox();
		
		this.add(box);
		
		box.add(rimuoviTutto);
		box.add(Box.createRigidArea(new Dimension(0, 10)));
		box.add(rimuovi);
		
		box.add(Box.createRigidArea(new Dimension(0, 25)));
		box.add(sommaPol);
		box.add(Box.createRigidArea(new Dimension(0, 10)));
		box.add(moltiplicazionePol);
		box.add(Box.createRigidArea(new Dimension(0, 10)));
		box.add(derivata);
		
		box.add(Box.createRigidArea(new Dimension(0, 25)));
		box.add(calcolaValore);
		
		
		class ErrValore extends JFrame {
			
			private JFrame f;
			private JPanel p1, p2;
			private JLabel l;
			private JButton b;
			
			public ErrValore() {
				f = this;
				p1 = new JPanel();
				p2 = new JPanel();
				this.setTitle("Errore");
				this.setSize(400, 100);
				this.setLocation(535, 300);
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				this.add(p1, BorderLayout.NORTH);
				l = new JLabel("Seleziona un solo polinomio per calcolare il valore");
				p1.add(l, BorderLayout.CENTER);
				
				this.add(p2, BorderLayout.SOUTH);
				b = new JButton();
				b.setText("OK");
				p2.add(b, BorderLayout.CENTER);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == b) {
							f.setVisible(false);
						}//if
					}//actionPerformed
				});
			}
		}//ErrValore
		
		calcolaValore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == calcolaValore) {
					if(contaSelezionati() > 1) {
						JFrame errValore = new ErrValore();
						errValore.setVisible(true);
						deseleziona();
					}//if
					else if(nessunSelezionato()) {
						JFrame nessunSel = new NessunPolinomioSelezionato();
						nessunSel.setVisible(true);
					}//else if
					else if(contaSelezionati() == 1) {	
						int x;
						List<String> cb;
						String val = JOptionPane.showInputDialog(null, "Fornire il valore di x:");
						try {
							x = Integer.parseInt(val);
							if(contaSelezionati() == 1) {
								cb = polinomioSelezionato();
								Polinomio p = IsPolinomio.creaPolinomio(cb.get(0));
								String s = String.valueOf(p.valore(x));
								JOptionPane.showMessageDialog(null, "Valore del polinomio = "+s);
								deseleziona();
							}//if
						}catch(RuntimeException exc) {
							JOptionPane.showMessageDialog(null, "Nessun intero. Ripetere..");
						}//catch
					}//else if
				}//if
			}//actionPerformed
		});
		
		rimuovi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == rimuovi) {
					if(nessunSelezionato()) {
						JFrame err = new NessunPolinomioSelezionato();
						err.setVisible(true);
					}//if
					for(int i=0;i<PannelloPolinomi.llCB.size();++i) {
						if(PannelloPolinomi.llCB.get(i).isSelected()) {
							PannelloPolinomi.box.remove(PannelloPolinomi.llCB.get(i));
							Application.Finestra.f.validate();
							Application.Finestra.f.repaint();
						}//if
					}//for
					deseleziona();
				}//if
				else if(e.getSource() == rimuovi && nessunSelezionato()) {
					JFrame err = new NessunPolinomioSelezionato();
					err.setVisible(true);
				}//if
			}//actionPerformed
		});
		
		rimuoviTutto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == rimuoviTutto) {
					for(int i=0;i<PannelloPolinomi.llCB.size();++i) {
						PannelloPolinomi.box.remove(PannelloPolinomi.llCB.get(i));
						Application.Finestra.f.validate();
						Application.Finestra.f.repaint();
					}//for
					deseleziona();
				}//if
			}//actionPerformed
		});
		
		sommaPol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nessunSelezionato()) {
					JFrame err = new NessunPolinomioSelezionato();
					err.setVisible(true);
				}//if
				else if(contaSelezionati() == 2 && e.getSource() == sommaPol) {
					String s1 = polinomioSelezionato().get(0);
					String s2 = polinomioSelezionato().get(1);
					pol1 = IsPolinomio.creaPolinomio(s1);
					pol2 = IsPolinomio.creaPolinomio(s2);
					deseleziona();
					if(ScegliTipologia.p.equalsIgnoreCase("PolinomioLL")) {
						polris = new PolinomioLL();
						polris = pol1.add(pol2);
						String s = polris.toString();
						if(!AggiuntaPolinomio.polinomioNonPresente(s)) {
							JFrame risultato = new RisultatoADD();
							risultato.setVisible(true);
							aggiungiPolinomio(s);
						}//if
						else {
							JFrame q = new OperazioneGiaEffettuata();
							q.setVisible(true);
						}//else
					}//if
					else if(ScegliTipologia.p.equalsIgnoreCase("PolinomioSet")) {
						polris = new PolinomioSet();
						polris = pol1.add(pol2);
						String s = polris.toString();
						if(!AggiuntaPolinomio.polinomioNonPresente(s)) {
							JFrame risultato = new RisultatoADD();
							risultato.setVisible(true);
							aggiungiPolinomio(s);
						}//if
						else {
							JFrame q = new OperazioneGiaEffettuata();
							q.setVisible(true);
						}//else
					}//if
				}//if
				else if((contaSelezionati() == 1 || contaSelezionati() > 2) && e.getSource() == sommaPol) {
					JFrame err = new ErrorePolinomiSelezionati();
					err.setVisible(true);
				}//else if
			}//actionPerformed
		});
		
		moltiplicazionePol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nessunSelezionato()) {
					JFrame err = new NessunPolinomioSelezionato();
					err.setVisible(true);
				}//if
				else if(contaSelezionati() == 2 && e.getSource() == moltiplicazionePol) {
					String s1 = polinomioSelezionato().get(0);
					String s2 = polinomioSelezionato().get(1);
					pol1 = IsPolinomio.creaPolinomio(s1);
					pol2 = IsPolinomio.creaPolinomio(s2);
					deseleziona();
					if(ScegliTipologia.p.equalsIgnoreCase("PolinomioLL")) {
						polris = new PolinomioLL();
						polris = pol1.mul(pol2);
						String s = polris.toString();
						if(!AggiuntaPolinomio.polinomioNonPresente(s)) {
							JFrame risultato = new RisultatoMUL();
							risultato.setVisible(true);
							aggiungiPolinomio(s);
						}//if
						else {
							JFrame q = new OperazioneGiaEffettuata();
							q.setVisible(true);
						}//else
					}//if
					else if(ScegliTipologia.p.equalsIgnoreCase("PolinomioSet")) {
						polris = new PolinomioSet();
						polris = pol1.mul(pol2);
						String s = polris.toString();
						if(!AggiuntaPolinomio.polinomioNonPresente(s)) {
							JFrame risultato = new RisultatoMUL();
							risultato.setVisible(true);
							aggiungiPolinomio(s);
						}//if
						else {
							JFrame q = new OperazioneGiaEffettuata();
							q.setVisible(true);
						}//else
					}//if
					
				}//if
				else if((contaSelezionati() == 1 || contaSelezionati() > 2) && e.getSource() == moltiplicazionePol) {
					JFrame err = new ErrorePolinomiSelezionati();
					err.setVisible(true);
				}//else if
			}//actionPerformed
		});
		
		derivata.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nessunSelezionato()) {
					JFrame err = new NessunPolinomioSelezionato();
					err.setVisible(true);
				}//if
				else if(contaSelezionati() == 1 && e.getSource() == derivata) {
					String s1 = polinomioSelezionato().get(0);
					pol1 = IsPolinomio.creaPolinomio(s1);
					deseleziona();
					if(ScegliTipologia.p.equalsIgnoreCase("PolinomioLL")) {
						polris = new PolinomioLL();
						polris = pol1.derivata();
						String s = polris.toString();
						if(!AggiuntaPolinomio.polinomioNonPresente(s)) {
							JFrame risultato = new RisultatoDERIVATA();
							risultato.setVisible(true);
							aggiungiPolinomio(s);
						}//if
						else {
							JFrame q = new OperazioneGiaEffettuata();
							q.setVisible(true);
						}//else
					}//if
					else if(ScegliTipologia.p.equalsIgnoreCase("PolinomioSet")) {
						polris= new PolinomioSet();
						polris = pol1.derivata();
						String s = polris.toString();
						if(!AggiuntaPolinomio.polinomioNonPresente(s)) {
							JFrame risultato = new RisultatoDERIVATA();
							risultato.setVisible(true);
							aggiungiPolinomio(s);
						}//if
						else {
							JFrame q = new OperazioneGiaEffettuata();
							q.setVisible(true);
						}//else
					}//if
				}//if
				else if(contaSelezionati() > 1 && e.getSource() == derivata) {
					JFrame err = new ErroreDerivata();
					err.setVisible(true);
				}//else if
			}//actionPerformed
		});
		
	}
	
	
	class OperazioneGiaEffettuata extends JFrame {
		
		protected JFrame f;
		protected JPanel p;
		protected JLabel l;
		protected JButton b;
		
		public OperazioneGiaEffettuata() {
			f = this;
			p = new JPanel();
			this.setTitle("Errore");
			this.setSize(425, 100);
			this.setLocation(535, 300);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			this.add(p, BorderLayout.CENTER);
			l = new JLabel("Questo polinomio-risultato è già presente. Seleziona polinomi diversi!");
			p.add(l, BorderLayout.NORTH);
			
			b = new JButton();
			b.setText("OK");
			p.add(b, BorderLayout.SOUTH);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b) {
						f.setVisible(false);
					}//if
				}
			});
		}
	}//OperazioneGiaEffettuata
	
	
	class ErroreDerivata extends JFrame {
		
		protected JFrame f;
		protected JPanel p1, p2;
		protected JLabel l;
		protected JButton b;
		
		public ErroreDerivata() {
			f = this;
			p1 = new JPanel();
			p2 = new JPanel();
			this.setTitle("Errore");
			this.setSize(400, 100);
			this.setLocation(535, 300);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			this.add(p1, BorderLayout.NORTH);
			l = new JLabel("Seleziona un solo polinomio per effettuare la derivata!");
			p1.add(l, BorderLayout.CENTER);
			
			this.add(p2, BorderLayout.SOUTH);
			b = new JButton();
			b.setText("OK");
			p2.add(b, BorderLayout.CENTER);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b) {
						f.setVisible(false);
					}//if
				}//actionPerformed
			});
		}
	}//ErroreDerivata
	
	class ErrorePolinomiSelezionati extends JFrame {
		
		protected JFrame f;
		protected JPanel p1, p2;
		protected JLabel l;
		protected JButton b;
		
		public ErrorePolinomiSelezionati() {
			f = this;
			p1 = new JPanel();
			p2 = new JPanel();
			this.setTitle("Errore");
			this.setSize(400, 100);
			this.setLocation(535, 300);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			this.add(p1, BorderLayout.NORTH);
			l = new JLabel("Seleziona 2 polinomi per effettuare l'operazione!");
			p1.add(l, BorderLayout.NORTH);
			
			this.add(p2, BorderLayout.SOUTH);
			b = new JButton();
			b.setText("OK");
			p2.add(b, BorderLayout.CENTER);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b) {
						f.setVisible(false);
					}//if
				}//actionPerformed
			});
		}
		
	}//ErrorePolinomiSelezionati
	
	class RisultatoADD extends JFrame {
		
		protected JFrame f;
		protected JPanel p1, p2, p3;
		protected JLabel l1, l2;
		protected JButton b;
		
		public RisultatoADD() {
			f = this;
			p1 = new JPanel();
			p2 = new JPanel();
			p3 = new JPanel();
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setTitle("Risultato operazione");
			this.setSize(400, 135);
			this.setLocation(535, 300);
			this.add(p1,BorderLayout.NORTH);
			String s1 = pol1.toString();
			String s2 = pol2.toString();
			l1 = new JLabel(s1 + " + " + s2 +" = ");
			p1.add(l1, BorderLayout.CENTER);
			
			this.add(p2,BorderLayout.CENTER);
			l2 = new JLabel(polris.toString());
			p2.add(l2, BorderLayout.CENTER);
			
			this.add(p3, BorderLayout.SOUTH);
			b = new JButton();
			b.setText("OK");
			p3.add(b, BorderLayout.CENTER);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b) {
						f.setVisible(false);
					}//if
				}//actionPerformed
			});
		}
	}//RisultatoADD
	
	
	class RisultatoMUL extends JFrame {
		protected JFrame f;
		protected JPanel p1, p2, p3;
		protected JLabel l1, l2;
		protected JButton b;
		
		public RisultatoMUL() {
			f = this;
			p1 = new JPanel();
			p2 = new JPanel();
			p3 = new JPanel();
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setTitle("Risultato operazione");
			this.setSize(400, 135);
			this.setLocation(535, 300);
			this.add(p1,BorderLayout.NORTH);
			l1 = new JLabel(pol1 + " * " + pol2 +" = ");
			p1.add(l1, BorderLayout.CENTER);
			
			this.add(p2,BorderLayout.CENTER);
			l2 = new JLabel(polris.toString());
			p2.add(l2, BorderLayout.CENTER);
			
			this.add(p3, BorderLayout.SOUTH);
			b = new JButton();
			b.setText("OK");
			p3.add(b, BorderLayout.CENTER);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b) {
						f.setVisible(false);
					}//if
				}//actionPerformed
			});
		}
	}//RisultatoMUL
	
	
	class RisultatoDERIVATA extends JFrame {
		protected JFrame f;
		protected JPanel p1, p2, p3;
		protected JLabel l1, l2;
		protected JButton b;
		
		public RisultatoDERIVATA() {
			f = this;
			p1 = new JPanel();
			p2 = new JPanel();
			p3 = new JPanel();
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setTitle("Risultato operazione");
			this.setSize(400, 135);
			this.setLocation(535, 300);
			this.add(p1,BorderLayout.NORTH);
			String s1 = pol1.toString();
			l1 = new JLabel("D(" + s1 +") = ");
			p1.add(l1, BorderLayout.CENTER);
			
			this.add(p2,BorderLayout.CENTER);
			l2 = new JLabel(polris.toString());
			p2.add(l2, BorderLayout.CENTER);
			
			this.add(p3, BorderLayout.SOUTH);
			b = new JButton();
			b.setText("OK");
			p3.add(b, BorderLayout.CENTER);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == b) {
						f.setVisible(false);
					}//if
				}//actionPerformed
			});
		}
	}//RisultatoDERIVATA
	
	private void deseleziona() {
		for(int w=0;w<PannelloPolinomi.llCB.size();++w) {
			if(PannelloPolinomi.llCB.get(w).isSelected())
				PannelloPolinomi.llCB.get(w).setSelected(false);
		}//for
		PannelloPolinomi.p.revalidate();
	}//deseleziona
	
	private void aggiungiPolinomio(String s) {
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
		else if(AggiuntaPolinomio.polinomioNonPresente(s)) {
			JFrame t = new AggiuntaPolinomio.PolinomioGiaEsistente();
			t.setVisible(true);
		}//else if
		else if(!IsPolinomio.riconosciPolinomio(s)){
			JFrame q = new AggiuntaPolinomio.ErrorePattern();
			q.setVisible(true);
		}//else if
	}//aggiungiPolinomio
	
	private List<String> polinomioSelezionato() {
		List<String> ret = new ArrayList<>();
		for(int i=0;i<PannelloPolinomi.llCB.size();++i) {
			if(PannelloPolinomi.llCB.get(i).isSelected())
				ret.add(PannelloPolinomi.llCB.get(i).getText());
		}//for
		return ret;
	}//polinomioSelezionato
	
	private int contaSelezionati() {
		int c = 0;
		for(int i=0;i<PannelloPolinomi.llCB.size();++i) {
			if(PannelloPolinomi.llCB.get(i).isSelected())
				c++;
		}//for
		return c;
	}//contaSelezionati
	
	private boolean nessunSelezionato() {
		int cnt = 0;
		for(int i=0;i<PannelloPolinomi.llCB.size();++i) {
			if(!PannelloPolinomi.llCB.get(i).isSelected())
				cnt++;
		}//for
		if(cnt == PannelloPolinomi.llCB.size())
			return true;
		return false;
	}//nessunSelezionato
	
	
	class NessunPolinomioSelezionato extends JFrame {
		
		protected JFrame s;
		protected JPanel q1, q2;
		protected JLabel testo;
		protected JButton ok;
		
		public NessunPolinomioSelezionato() {
			s = this;
			this.setTitle("Errore");
			this.setSize(400, 100);
			this.setLocation(535, 300);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			q1 = new JPanel();
			q2 = new JPanel();
			this.add(q1, BorderLayout.NORTH);
			this.add(q2, BorderLayout.SOUTH);
			testo = new JLabel("Nessun polinomio selezionato");
			q1.add(testo, BorderLayout.CENTER);
			ok = new JButton();
			ok.setText("OK");
			q2.add(ok, BorderLayout.CENTER);
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == ok)
						s.setVisible(false);
				}//actionPerformed
			});
		}
	}//NessunPolinomioSelezionato
	
}//PannelloOperazioni
