package model;

import java.util.Vector;

public abstract class Automata {
	
	protected int Num_states;
	protected String start;//Representa el estado inicial
	protected Vector<String> S;//Alfabeto
	protected Vector<String> R;//Alfabeto de salida
	protected String states[];
	

	public Automata(Vector<String> S, Vector<String> R, int Num_states, String start) {
		this.S = S;
		this.R = R;
		this.Num_states = Num_states;
		this.start = start;
		states = new String[Num_states];
	}
	
	public String getStartString() {
		return start;
	}
	
	public void setStartString(String start) {
		this.start = start;
	}
	
	public int getEstados() {
		return Num_states;
	}

	public void setEstados(int Num_states) {
		this.Num_states = Num_states;
	}

	public Vector<String> getS() {
		return S;
	}

	public void setS(Vector<String> s) {
		S = s;
	}

	public Vector<String> getR() {
		return R;
	}

	public void setR(Vector<String> r) {
		R = r;
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

}
