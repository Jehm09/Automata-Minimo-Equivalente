package model;

import java.util.Vector;

public abstract class Automata {
	
	protected int Num_estados;
	protected String start;//Representa el estado inicial
	protected Vector<String> S;//Alfabeto
	protected Vector<String> R;//Alfabeto de salida
	protected String estados[];
	

	public Automata(Vector<String> S, Vector<String> R, int Num_estados, String start) {
		this.S = S;
		this.R = R;
		this.Num_estados = Num_estados;
		this.start = start;
		estados = new String[Num_estados];
	}
	
	public String getStartString() {
		return start;
	}
	
	public void setStartString(String start) {
		this.start = start;
	}
	
	public int getEstados() {
		return Num_estados;
	}

	public void setEstados(int Num_estados) {
		this.Num_estados = Num_estados;
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

}
