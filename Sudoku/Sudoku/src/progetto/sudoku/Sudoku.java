package progetto.sudoku;

import poo.backtracking.Backtracking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Sudoku extends Backtracking<Cella, Integer> {
	
	protected static int N = 9, INIT = 0;
	protected static int NUMERO_SOLUZIONI;
	protected static int SOLUZIONI_MASSIME = 11;
	protected List<Cella> listaPuntiScelta = new ArrayList<>();
	protected  int m[][];
	
	public Sudoku() {
		m = new int[N][N];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				m[i][j] = INIT;
				listaPuntiScelta.add(new Cella(i, j));
			}//for
		}//for
		NUMERO_SOLUZIONI = 0;
	}
	

	@Override
	public List<Cella> puntiDiScelta() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				listaPuntiScelta.add(new Cella(i, j));
			}//for
		}//for
		return listaPuntiScelta;
	}//puntiDiScelta

	@Override
	public Collection<Integer> scelte(Cella ps) {
		ArrayList<Integer> scelte = new ArrayList<>();
		for(int i=1; i<10; ++i) {
			scelte.add(i);
		}//for
		return scelte;
	}//scelte
	
	@Override
	protected void scriviSoluzione(Cella ps) {
		NUMERO_SOLUZIONI++;
		AppSudoku.FinestraSudoku.aggiungiSoluzione(m);
	}//scriviSoluzione
	
	@Override
	protected boolean esisteSoluzione(Cella ps) {
		if(listaPuntiScelta.get(listaPuntiScelta.size() - 1).equals(ps))
			return true;
		return false;
	}//esisteSoluzione
	
	@Override
	protected boolean ultimaSoluzione(Cella ps) {
		return NUMERO_SOLUZIONI == SOLUZIONI_MASSIME;
	}//ultimaSoluzione
	
	protected void imposta(int row, int col, int valore) {
		if(valore <= 0)
			throw new IllegalArgumentException();
		Cella ps = new Cella(row, col);
		if(assegnabile(ps, valore)) {
			listaPuntiScelta.remove(ps);
			m[row][col] = valore;
		}//if
	}//imposta
	
	@Override
	protected void assegna(Cella ps, Integer scelta) {
		m[ps.getI()][ps.getJ()] = scelta;
	}//assegna

	@Override
	protected void deassegna(Cella ps, Integer scelta) {
		m[ps.getI()][ps.getJ()] = 0;
	}//deassegna
	
	@Override
	protected boolean assegnabile(Cella ps, Integer scelta) {
		if(!listaPuntiScelta.contains(ps)) {
			int i = listaPuntiScelta.indexOf(ps);
			tentativo(listaPuntiScelta, listaPuntiScelta.get(i+1));
		}//if
		if( stessaRiga(ps, scelta) || 
		    stessaColonna(ps, scelta) || 
		    stessaSottoGriglia(ps, scelta))
				return false;
		return true;
	}//assegnabile

	private boolean stessaRiga(Cella ps, Integer scelta) {
		for(int j = 0; j<N; ++j) {
			if(m[ps.getI()][j] == scelta)
				return true;
		}//for
		return false;
	}//stessaRiga
	
	private boolean stessaColonna(Cella ps, Integer scelta) {
		for(int i = 0; i<N; ++i) {
			if(m[i][ps.getJ()] == scelta)
				return true;
		}//for
		return false;
	}//stessaColonna
	
	private boolean stessaSottoGriglia(Cella ps, Integer scelta) { 
		int i = ps.getI();   
		int j = ps.getJ();  
		int row = i - ( i  %  3 );
		int col = j - ( j  %  3 );
		for(i=row; i<row+3; i++)
			for(j=col; j<col+3; j++)
				if(m[i][j] == scelta)
					return true;
		return false;
	}//stessaSottoGriglia

	@Override
	public void risolvi() {
		tentativo(listaPuntiScelta, new Cella(0, 0));
	}//risolvi
	
}//Sudoku