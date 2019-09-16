package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public abstract class Automata {
	
	protected int Num_states;
	protected String start;//Representa el estado inicial
	protected String S[];//Alfabeto
	protected String R[];//Alfabeto de salida
	protected String states[];
	

	public Automata(String S[], String R[], int Num_states, String start) {
		this.S = S;
		this.R = R;
		this.Num_states = Num_states;
		this.start = start;
		states = new String[Num_states];
	}
	
	public int getNum_states() {
		return Num_states;
	}

	public void setNum_states(int num_states) {
		Num_states = num_states;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String[] getS() {
		return S;
	}

	public void setS(String[] s) {
		S = s;
	}

	public String[] getR() {
		return R;
	}

	public void setR(String[] r) {
		R = r;
	}

	public String[] getStates() {
		return states;
	}

	public void setStates(String[] states) {
		this.states = states;
	}
	
	/**
	 * Metodo encargado de agregar al grafo las conexiones entre estados
	 * @param i String que representa el nombre de cada estado
	 * @param src String que representa algun caracter del alfabeto
	 * @param dst String que representa el estado De destino
	 * @param sali String que representa algunc caracter del alfabeto de salida
	 */
	public abstract void add(String i, String src, String dst, String sali);
	
	/**
	 * Metodo que se encarga de recorrer el grafo y eliminar los estados que no son alcanzables
	 */
	public abstract void dfs();
	
	/**
	 * Metodo que se encarga de generar el automata minimo equivalente
	 */
	public abstract void generateEquivalentMinimum();
	
	public abstract void createFirstP();

}
