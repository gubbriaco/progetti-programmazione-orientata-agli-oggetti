package progetto.sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AppSudoku {

	public static void main(String...strings) {
		JFrame sudoku = new FinestraSudoku();
		sudoku.setVisible(true);
	}//main
	
	
	@SuppressWarnings("serial")
	protected static class FinestraSudoku extends JFrame {
		
		protected static JFrame f;
		private static Dimension d;
		private static String titolo;
		private static JPanel p1, p2, p3;
		private static TitledBorder SUDOKU;
		private static Border border;
		private static Font numSoluzioneFont, sudokuFont, fp1, fp2, fp3, soluzioniFont;
		private static JLabel aggNuovoValore, labelGL, numSoluzione;
		private static JTextField row, col, valore;
		private static JButton aggiungi, rimuovi, risolvi, precedenteSol, successivaSol, salvaConNome, salva, carica, nuovoSudoku;
		private static Box boxAssegnamenti;
		private static List<JCheckBox> listaJCheckBox;
		private static List<Oggetto> listaOggetti;
		private static GridLayout gl;
		protected static List<int[][]> soluzioni;
		private static ArrayList<JLabel[][]> m_daMostrare;
		private static JLabel[][] daAgg;
		protected static int indiceSoluzione = 0;
		private static String REGEX = "\\d+";
		private static File fileDiSalvataggio = null;
		@SuppressWarnings("unused")
		private static String stringa;
		
		public FinestraSudoku() {
			f = this;
			titolo = "Sudoku";
			this.setTitle(titolo);
			this.setSize(600, 600);
			d = getToolkit().getScreenSize();
			this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					JFrame uscita = new Uscita();
					uscita.setVisible(true);
				}//windowClosing
			});
	
			p1 = new JPanel();
			this.add(p1, BorderLayout.NORTH);
			p1.setBackground(new Color(179, 179, 0));
			sudokuFont = new Font("Helvetica", Font.BOLD, 25);
			SUDOKU = new TitledBorder("SUDOKU " + Sudoku.N + "x" + Sudoku.N);
			SUDOKU.setTitleFont(sudokuFont);
			SUDOKU.setTitleColor(Color.BLACK);
			border = new LineBorder(Color.ORANGE, 4, true);
			SUDOKU.setBorder(border);
		    p1.setBorder(SUDOKU);
			fp1 = new Font("Helvetica", Font.BOLD, 14);
			aggNuovoValore = new JLabel("Aggiungi un nuovo valore");
			aggNuovoValore.setFont(fp1);
			aggNuovoValore.setForeground(Color.BLACK);
			aggNuovoValore.setBorder(BorderFactory.createRaisedBevelBorder());
			p1.add(aggNuovoValore, BorderLayout.SOUTH);
			row = new JTextField("", 2);
			row.setBackground(new Color(255, 195, 77));
			row.setOpaque(true);
			row.setToolTipText("riga");
			p1.add(row, BorderLayout.NORTH);
			col = new JTextField("", 2);
			col.setBackground(new Color(255, 195, 77));
			col.setOpaque(true);
			col.setToolTipText("colonna");
			p1.add(col, BorderLayout.NORTH);
			valore = new JTextField("", 2);
			valore.setBackground(new Color(255, 170, 0));
			valore.setOpaque(true);
			valore.setToolTipText("valore da inserire");
			p1.add(valore, BorderLayout.SOUTH);
			aggiungi = new JButton();
			aggiungi.setText("AGGIUNGI");
			aggiungi.setForeground(Color.BLACK);
			p1.add(aggiungi, BorderLayout.SOUTH);
			aggiungi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == aggiungi) {
						if(row.getText().equals("") || col.getText().equals("") || valore.getText().equals("")) {
							JFrame errore = new ControllaValori();
							errore.setVisible(true);
							deseleziona();
							row.setText("");
							col.setText("");
							valore.setText("");
							p1.repaint();
							p1.revalidate();
						}//else
						else {
							if(row.getText().matches(REGEX) && 
							   col.getText().matches(REGEX) && 
							   valore.getText().matches(REGEX)) {
								int riga = Integer.parseInt(row.getText());
								int colonna = Integer.parseInt(col.getText());
								int scelta = Integer.parseInt(valore.getText());
								if(indiciOK(riga, colonna) && valoreOK(scelta)) {
									Oggetto ogg = new Oggetto(riga, colonna, scelta);
									JCheckBox cb = new JCheckBox();
									cb.setText(ogg.toString());
									if(giaPresente(cb)) {
										JFrame gp = new GiaPresente();
										gp.setVisible(true);
										deseleziona();
										row.setText("");
										col.setText("");
										valore.setText("");
										p1.repaint();
										p1.revalidate();
									}//if
									else {
										cb.setForeground(Color.BLACK);
										cb.setBackground(new Color(179, 179, 0));
										cb.setOpaque(true);
										boxAssegnamenti.add(cb);
										listaJCheckBox.add(cb);
										listaOggetti.add(ogg);
										deseleziona();
										p2.repaint();
										p2.revalidate();
										row.setText("");
										col.setText("");
										valore.setText("");
										p1.repaint();
										p1.revalidate();
									}//else
								}//if
								else {
									JFrame errore = new ControllaValori();
									errore.setVisible(true);
									deseleziona();
									row.setText("");
									col.setText("");
									valore.setText("");
									p1.repaint();
									p1.revalidate();
								}//else
							}//if
							else {
								JFrame errore = new ControllaValori();
								errore.setVisible(true);
								deseleziona();
								row.setText("");
								col.setText("");
								valore.setText("");
								p1.repaint();
								p1.revalidate();
							}//else
						}//else
					}//if
				}//actionPerformed
			});
			
			p2 = new JPanel();
			this.add(p2, BorderLayout.CENTER);
			p2.setBackground(new Color(179, 179, 0));
			fp2 = new Font("Helvetica", Font.BOLD, 14);
			boxAssegnamenti = Box.createVerticalBox();
			p2.add(boxAssegnamenti, BorderLayout.WEST);
			listaJCheckBox = new ArrayList<>();
			listaOggetti = new ArrayList<>();
			soluzioni = new ArrayList<>();
			m_daMostrare = new ArrayList<>();
			
			p3 = new JPanel();
			this.add(p3, BorderLayout.SOUTH);
			p3.setBackground(new Color(179, 179, 0));
			fp3 = new Font("Helvetica", Font.BOLD, 11);
			carica = new JButton();
			carica.setText("CARICA");
			carica.setFont(fp3);
			p3.add(carica, BorderLayout.CENTER);
			carica.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == carica) {
						JFileChooser chooser = new JFileChooser();
						try {
							if( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
								if( !chooser.getSelectedFile().exists() ) {
									JOptionPane.showMessageDialog(null, "File inesistente!");
								}//if
								else {
									fileDiSalvataggio = chooser.getSelectedFile();
									AppSudoku.FinestraSudoku.f.setTitle( titolo + " - " + fileDiSalvataggio.getName() );
									try {
										ripristina( fileDiSalvataggio.getAbsolutePath() );
									}catch( IOException ioe ) {
										JOptionPane.showMessageDialog(null, "Fallimento apertura. File malformato!");
									}//catch
								}//else
							}//if
							else
								JOptionPane.showMessageDialog(null, "Nessuna apertura!");
						}catch( Exception exc ) {
							exc.printStackTrace();
						}//catch
					}//risolvi
				}//actionPerformed
			});
			salva = new JButton();
			salva.setText("SALVA");
			salva.setFont(fp3);
			p3.add(salva, BorderLayout.CENTER);
			salva.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == salva) {
						JFileChooser chooser = new JFileChooser();
						try {
							if( fileDiSalvataggio != null ) {
								int ans = JOptionPane.showConfirmDialog(null, "Sovrascrivere " + fileDiSalvataggio.getAbsolutePath() + "?");
								if( ans == 0 )
									salva( fileDiSalvataggio.getAbsolutePath() );
								else
									JOptionPane.showMessageDialog(null, "Nessun salvataggio!");
								return;
							}//if
							if( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
								fileDiSalvataggio = chooser.getSelectedFile();
								AppSudoku.FinestraSudoku.f.setTitle( titolo + " - " + fileDiSalvataggio.getName() );
								salva( fileDiSalvataggio.getAbsolutePath() );
							}//if
							if( fileDiSalvataggio != null ) {
								salva( fileDiSalvataggio.getAbsolutePath() );
								AppSudoku.FinestraSudoku.f.setTitle( titolo + " - " + fileDiSalvataggio.getName() );
							}//if
							else
								JOptionPane.showMessageDialog(null, "Nessun salvataggio!");
						}catch(Exception exc) {
							exc.printStackTrace();
						}//catch
					}//risolvi
				}//actionPerformed
			});
			salvaConNome = new JButton();
			salvaConNome.setText("SALVA CON NOME");
			salvaConNome.setFont(fp3);
			p3.add(salvaConNome, BorderLayout.CENTER);
			salvaConNome.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == salvaConNome) {
						JFileChooser chooser = new JFileChooser();
						try {
							if( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
								fileDiSalvataggio = chooser.getSelectedFile();
								AppSudoku.FinestraSudoku.f.setTitle( titolo + " - " + fileDiSalvataggio.getName() );
							}//if
							if( fileDiSalvataggio != null ) {
								salva( fileDiSalvataggio.getAbsolutePath() );
							}//if
							else
								JOptionPane.showMessageDialog(null, "Nessun salvataggio!");
						}catch(Exception exc) {
							exc.printStackTrace();
						}//catch
					}//if
				}//actionPerformed
			});
			rimuovi = new JButton();
			rimuovi.setText("RIMUOVI SCELTA");
			rimuovi.setFont(fp3);
			p3.add(rimuovi, BorderLayout.CENTER);
			rimuovi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == rimuovi) {
						elimina();
					}//rimuovi
				}//actionPerformed
			});
			risolvi = new JButton();
			risolvi.setText("RISOLVI SUDOKU");
			risolvi.setFont(fp3);
			p3.add(risolvi, BorderLayout.CENTER);
			risolvi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == risolvi) {
						p1.remove(aggNuovoValore);
						p1.remove(row);
						p1.remove(col);
						p1.remove(valore);
						p1.remove(aggiungi);
						SUDOKU.setTitleJustification(TitledBorder.CENTER);
						p1.repaint();
						p1.revalidate();
						p3.remove(risolvi);
						p3.remove(rimuovi);
						p3.remove(salva);
						p3.remove(salvaConNome);
						p3.remove(carica);
						soluzioniFont = new Font("Helvetica", Font.BOLD, 9);
						precedenteSol = new JButton();
						precedenteSol.setText("SOLUZIONE PRECEDENTE");
						precedenteSol.setFont(soluzioniFont);
						p1.add(precedenteSol, BorderLayout.CENTER);
						precedenteSol.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if(e.getSource() == precedenteSol) {
									FinestraSudoku.p2.removeAll();
									if(indiceSoluzione == 0)
										indiceSoluzione = Sudoku.SOLUZIONI_MASSIME - 1;
									else
										indiceSoluzione = indiceSoluzione - 1;
									FinestraSudoku.numSoluzione.setText("Soluzione " + (indiceSoluzione + 1));
									FinestraSudoku.numSoluzione.repaint();
									FinestraSudoku.numSoluzione.revalidate();
									daAgg = m_daMostrare.get(indiceSoluzione);
									gl = new GridLayout(Sudoku.N, Sudoku.N, 3, 3);
									p2.setLayout(gl);
									for(int i=0;i<Sudoku.N;++i) {
										for(int j=0;j<Sudoku.N;++j) {
											labelGL = new JLabel(daAgg[i][j].getText());
											labelGL.setHorizontalAlignment(JLabel.CENTER);
											labelGL.setFont(fp2);
											labelGL.setForeground(Color.BLACK);
											labelGL.setBackground(Color.ORANGE);
											labelGL.setOpaque(true);
											p2.add(labelGL);
										}//for
									}//for
									p2.repaint();
									p2.revalidate();
								}//if
							}//actionPerformed
						});
						p3.add(salva, BorderLayout.CENTER);
						p3.add(salvaConNome, BorderLayout.CENTER);
						nuovoSudoku = new JButton();
						nuovoSudoku.setText("NUOVO SUDOKU");
						p3.add(nuovoSudoku, BorderLayout.CENTER);
						nuovoSudoku.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if(e.getSource() == nuovoSudoku) {
									JFrame uscitaNS = new UscitaNS();
									uscitaNS.setVisible(true);
								}//if
							}//actionPerformed
						});
						numSoluzione = new JLabel("Soluzione " + (indiceSoluzione + 1));
						numSoluzioneFont = new Font("Helvetica", Font.BOLD, 20);
						numSoluzione.setFont(numSoluzioneFont);
						numSoluzione.setForeground(Color.BLACK);
						numSoluzione.setBorder(BorderFactory.createRaisedBevelBorder());
						p1.add(numSoluzione, BorderLayout.CENTER);
						successivaSol = new JButton();
						successivaSol.setText("SOLUZIONE SUCCESSIVA");
						successivaSol.setFont(soluzioniFont);
						p1.add(successivaSol, BorderLayout.CENTER);
						successivaSol.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if(e.getSource() == successivaSol) {
									FinestraSudoku.p2.removeAll();
									if(indiceSoluzione == Sudoku.SOLUZIONI_MASSIME - 1)
										indiceSoluzione = 0;
									else
										indiceSoluzione = indiceSoluzione + 1;
									FinestraSudoku.numSoluzione.setText("Soluzione " + (indiceSoluzione + 1));
									FinestraSudoku.numSoluzione.repaint();
									FinestraSudoku.numSoluzione.revalidate();
									daAgg = m_daMostrare.get(indiceSoluzione);
									gl = new GridLayout(Sudoku.N, Sudoku.N, 3, 3);
									p2.setLayout(gl);
									for(int i=0;i<Sudoku.N;++i) {
										for(int j=0;j<Sudoku.N;++j) {
											labelGL = new JLabel(daAgg[i][j].getText());
											labelGL.setHorizontalAlignment(JLabel.CENTER);
											labelGL.setFont(fp2);
											labelGL.setForeground(Color.BLACK);
											labelGL.setBackground(Color.ORANGE);
											labelGL.setOpaque(true);
											p2.add(labelGL);
										}//for
									}//for
									p2.repaint();
									p2.revalidate();
								}//if
							}//actionPerformed
						});
						p3.repaint();
						p3.revalidate();
						
						FinestraSudoku.p2.removeAll();
						Sudoku sudoku = new Sudoku();
						for(int i=0;i<FinestraSudoku.listaOggetti.size();++i) {
							int row = listaOggetti.get(i).getI();
							int col = listaOggetti.get(i).getJ();
							int val = listaOggetti.get(i).getValore();
							sudoku.imposta(row, col, val);
						}//for
						sudoku.risolvi();
						daAgg = m_daMostrare.get(FinestraSudoku.indiceSoluzione);
						gl = new GridLayout(Sudoku.N, Sudoku.N, 3, 3);
						p2.setLayout(gl);
						for(int i=0;i<Sudoku.N;++i) {
							for(int j=0;j<Sudoku.N;++j) {
								labelGL = new JLabel(daAgg[i][j].getText());
								labelGL.setHorizontalAlignment(JLabel.CENTER);
								labelGL.setFont(fp2);
								labelGL.setForeground(Color.BLACK);
								labelGL.setBackground(Color.ORANGE);
								labelGL.setOpaque(true);
								p2.add(labelGL);
							}//for
						}//for
						p2.repaint();
						p2.revalidate();
					}//if
				}//actionPerformed
			});
			
		}//costruttoreFinestraSudoku
		
		protected static void aggiungiSoluzione(int[][] m) {
			JLabel[][] nuova = new JLabel[m.length][m[0].length];
			for(int i=0;i<m.length;++i) {
				for(int j=0;j<m[0].length;++j) {
					JLabel l = new JLabel("" + m[i][j]);
					nuova[i][j] = l;
				}//for
			}//for
			m_daMostrare.add(nuova);
		}//aggiungiSoluzione
		
	}//FinestraSudoku
	
	
	private static void salva( String nome ) throws IOException {
		PrintWriter pw = new PrintWriter( new FileWriter(nome) );
		for(Component cb:FinestraSudoku.boxAssegnamenti.getComponents())
			pw.println(((JCheckBox) cb).getText());
			pw.close();
	}//salva
	
	private static void ripristina( String nome ) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader( new FileReader(nome) );
		FinestraSudoku.listaJCheckBox.clear();
		for( ;; ) {
			String linea = br.readLine();
			if(linea == null)
				break;
			FinestraSudoku.stringa = linea;
			aggiungiScelta(linea);
		}//for;;
	}//ripristina
	
	private static void aggiungiScelta( String s ) {
		JCheckBox cb = new JCheckBox();
		cb.setText(s);
		Oggetto ogg = creaOggetto(s);
		cb.setForeground(Color.BLACK);
		cb.setBackground(new Color(179, 179, 0));
		cb.setOpaque(true);
		FinestraSudoku.boxAssegnamenti.add(cb);
		FinestraSudoku.listaJCheckBox.add(cb);
		FinestraSudoku.listaOggetti.add(ogg);
		deseleziona();
		FinestraSudoku.p2.repaint();
		FinestraSudoku.p2.revalidate();
	}//aggiungiScelta
	
	private static Oggetto creaOggetto( String s ) {
		Oggetto ogg = new Oggetto();
		StringTokenizer st = new StringTokenizer( s, "<,> ", true);
		String aperta =  "<";
		String chiusa =  ">";
		String virgola = ",";
		String uguale =  "=";
		@SuppressWarnings("unused")
		String spazio;
		while(st.hasMoreTokens()) {
			String c = st.nextToken();
			if(c.equals(aperta)) {
				ogg.setI(Integer.parseInt(st.nextToken()));
			}//if
			if(c.equals(virgola)) {
				ogg.setJ(Integer.parseInt(st.nextToken()));
			}//if
			if(c.equals(chiusa)) {
				spazio = st.nextToken(); 
			}//if
			if(c.equals(uguale)) {
				spazio = st.nextToken();
				ogg.setValore(Integer.parseInt(st.nextToken()));
			}//if
		}//while
		return ogg;	
	}//creaOggetto
	
	private static void elimina() {
		if(nessunSelezionato()) {
			JFrame ns = new NessunSelezionato();
			ns.setVisible(true);
			return;
		}//if
		for(int i=0;i<FinestraSudoku.listaJCheckBox.size();++i)
			if(FinestraSudoku.listaJCheckBox.get(i).isSelected()) {
				FinestraSudoku.boxAssegnamenti.remove(FinestraSudoku.listaJCheckBox.get(i));
				FinestraSudoku.listaJCheckBox.get(i).setSelected(false);
				FinestraSudoku.p2.repaint();
				FinestraSudoku.p2.revalidate();
			}//if
		FinestraSudoku.p2.repaint();
		FinestraSudoku.p2.revalidate();
	}//elimina
	
	private static void deseleziona() {
		for(int i=0;i<FinestraSudoku.listaJCheckBox.size();++i)
			if(FinestraSudoku.listaJCheckBox.get(i).isSelected())
				FinestraSudoku.listaJCheckBox.get(i).setSelected(false);
	}//deseleziona
	
	private static boolean nessunSelezionato() {
		int cnt = 0;
		for(int i=0;i<FinestraSudoku.listaJCheckBox.size();++i)
			if(!FinestraSudoku.listaJCheckBox.get(i).isSelected())
				cnt++;
		if(cnt == FinestraSudoku.listaJCheckBox.size())
			return true;
		else
			return false;
	}//nessunSelezionato
	
	private static boolean giaPresente(JCheckBox cb) {
		for(int i=0;i<FinestraSudoku.listaJCheckBox.size();++i) {
			if(FinestraSudoku.listaJCheckBox.get(i).getText().equals(cb.getText()))
				return true;
		}//for
		return false;
	}//giaPresente
	
	private static boolean indiciOK(int riga, int colonna) {
		if(riga >= 0 && riga < 9 && colonna >= 0 && colonna < 9 
				&& String.valueOf(riga).matches(FinestraSudoku.REGEX)
				&& String.valueOf(colonna).matches(FinestraSudoku.REGEX))
			return true;
		return false;
	}//indiciOK
	
	private static boolean valoreOK(int scelta) {
		if(scelta > 0 && scelta < 10 
				&& String.valueOf(scelta).matches(FinestraSudoku.REGEX))
			return true;
		return false; 
	}//scelta
	
}//AppSudoku

class Oggetto {
	
	private int i, j, valore;
	
	public Oggetto() {
		this.i = 0;
		this.j = 0;
		this.valore = 0;
	}
	
	public Oggetto(int i, int j, int valore) {
		this.i = i;
		this.j = j;
		this.valore = valore;
	}
	
	public int getI() {
		return i;
	}//getI
	public int getJ() {
		return j;
	}//getJ
	public int getValore() {
		return valore;
	}//getValore
	
	public void setI(int i) {
		this.i = i;
	}//setI
	public void setJ(int j) {
		this.j = j;
	}//setJ
	public void setValore(int valore) {
		this.valore = valore;
	}//setValore
	
	@Override
	public String toString() {
		return "<" + i + "," + j + ">" + " = " + valore;
	}//toString
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof Oggetto))
			return false;
		Oggetto ogg = (Oggetto)o;
		return i == ogg.i && j == ogg.j;
	}//equals
	
	@Override
	public int hashCode() {
		final int M = 83;
		int h = 0;
		h = h*M + i+j;
		return h;
	}//hashCode
	
}//Oggetto

@SuppressWarnings("serial")
class UscitaNS extends JFrame {
	
	protected static JFrame f;
	private static Dimension d;
	private static JPanel p1, p2;
	private static JLabel l;
	private static JButton annulla, ok;
	
	public UscitaNS() {
		f = this;
		this.setTitle("ESC");
		this.setSize(350, 130);
		d = getToolkit().getScreenSize();
		this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				UscitaNS.f.setVisible(false);
			}//windowClosing
		});
		p1 = new JPanel();
		this.add(p1, BorderLayout.NORTH);
		l = new JLabel("Sei sicuro di voler uscire? Perderai le soluzioni correnti!");
		Font f = new Font("Tahoma", Font.BOLD, 11);
		l.setFont(f);
		p1.add(l, BorderLayout.CENTER);
		
		p2 = new JPanel();
		this.add(p2, BorderLayout.CENTER);
		annulla = new JButton();
		annulla.setText("annulla");
		p2.add(annulla, BorderLayout.WEST);
		annulla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == annulla)
					UscitaNS.f.setVisible(false);
			}//actionPerformed
		});
		ok = new JButton();
		ok.setText("OK");
		p2.add(ok, BorderLayout.EAST);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == ok) {
					UscitaNS.f.setVisible(false);
					AppSudoku.FinestraSudoku.f.setVisible(false);
					AppSudoku.FinestraSudoku.indiceSoluzione = 0;
					JFrame nuovo = new AppSudoku.FinestraSudoku();
					nuovo.setVisible(true);
				}//if
			}//actionPerformed
		});
	}
}//UscitaNS

@SuppressWarnings("serial")
class ControllaValori extends JFrame {

	private static JFrame f;
	private static Dimension d;
	private static JPanel p1, p2;
	private static JLabel l;
	private static JButton OK;
	
	public ControllaValori() {
		f = this;
		this.setTitle("ERRORE");
		this.setSize(365 , 130);
		d = getToolkit().getScreenSize();
		this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ControllaValori.f.setVisible(false);
			}//windowClosing
		});
		p1 = new JPanel();
		this.add(p1, BorderLayout.NORTH);
		l = new JLabel("Qualcosa è andato storto! Controlla i valori che hai inserito!");
		p1.add(l, BorderLayout.CENTER);
		p2 = new JPanel();
		this.add(p2, BorderLayout.CENTER);
		OK = new JButton();
		OK.setText("OK");
		p2.add(OK, BorderLayout.EAST);
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == OK) {
					ControllaValori.f.setVisible(false);
				}//if
			}//actionPerformed
		});
	}
	
}//ControllaValori

@SuppressWarnings("serial")
class NessunSelezionato extends JFrame {
	
	private static JFrame f;
	private static Dimension d;
	private static JPanel p1, p2;
	private static JLabel l;
	private static JButton OK;
	
	public NessunSelezionato() {
		f = this;
		this.setTitle("ERRORE");
		this.setSize(350 , 130);
		d = getToolkit().getScreenSize();
		this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				NessunSelezionato.f.setVisible(false);
			}//windowClosing
		});
		p1 = new JPanel();
		this.add(p1, BorderLayout.NORTH);
		l = new JLabel("Nessuna scelta selezionata da rimuovere!");
		p1.add(l, BorderLayout.CENTER);
		p2 = new JPanel();
		this.add(p2, BorderLayout.CENTER);
		OK = new JButton();
		OK.setText("OK");
		p2.add(OK, BorderLayout.EAST);
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == OK) {
					NessunSelezionato.f.setVisible(false);
				}//if
			}//actionPerformed
		});
	}
	
}//NessunSelezionato

@SuppressWarnings("serial")
class GiaPresente extends JFrame {
	
	private static JFrame f;
	private static Dimension d;
	private static JPanel p1, p2;
	private static JLabel l;
	private static JButton OK;
	
	public GiaPresente() {
		f = this;
		this.setTitle("ERRORE");
		this.setSize(350 , 130);
		d = getToolkit().getScreenSize();
		this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GiaPresente.f.setVisible(false);
			}//windowClosing
		});
		p1 = new JPanel();
		this.add(p1, BorderLayout.NORTH);
		l = new JLabel("Questa scelta è gia presente! Inserire una nuova scelta!");
		p1.add(l, BorderLayout.CENTER);
		p2 = new JPanel();
		this.add(p2, BorderLayout.CENTER);
		OK = new JButton();
		OK.setText("OK");
		p2.add(OK, BorderLayout.EAST);
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == OK) {
					GiaPresente.f.setVisible(false);
				}//if
			}//actionPerformed
		});
	}	
	
}//GiaPresente

@SuppressWarnings("serial")
class Uscita extends JFrame {
	
	protected static JFrame f;
	private static Dimension d;
	private static JPanel p1, p2;
	private static JLabel l;
	private static JButton annulla, ok;
	
	public Uscita() {
		f = this;
		this.setTitle("ESC");
		this.setSize(350, 130);
		d = getToolkit().getScreenSize();
		this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Uscita.f.setVisible(false);
			}//windowClosing
		});
		p1 = new JPanel();
		this.add(p1, BorderLayout.NORTH);
		l = new JLabel("Sei sicuro di voler uscire? Perderai le soluzioni correnti!");
		Font f = new Font("Tahoma", Font.BOLD, 11);
		l.setFont(f);
		p1.add(l, BorderLayout.CENTER);
		
		p2 = new JPanel();
		this.add(p2, BorderLayout.CENTER);
		annulla = new JButton();
		annulla.setText("annulla");
		p2.add(annulla, BorderLayout.WEST);
		annulla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == annulla)
					Uscita.f.setVisible(false);
			}//actionPerformed
		});
		ok = new JButton();
		ok.setText("OK");
		p2.add(ok, BorderLayout.EAST);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == ok) {
					System.exit(0);
				}//if
			}//actionPerformed
		});
	}
	
}//Uscita
