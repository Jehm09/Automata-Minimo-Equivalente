package model;

import java.util.Vector;

public abstract class Automata {
	
	private int estados;
	private Vector<String> S;//Alfabeto
	private Vector<String> R;//Alfabeto de salida
	
	public Automata(Vector<String> S, Vector<String> R, int estados) {
		this.S = S;
		this.R = R;
		this.estados = estados;
	}
	
	/**
	 * Metodo encargado de agregar al grafo las conexiones entre estados
	 * @param i String que representa el nombre de cada estado
	 * @param src String que representa algun caracter del alfabeto
	 * @param dst String que representa el estado De destino
	 * @param sali String que representa algunc caracter del alfabeto de salida
	 */
	public abstract void add(String i, String src, String dst, String sali);

}
