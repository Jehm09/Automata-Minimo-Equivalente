package model;

import java.util.HashMap;
import java.util.Vector;

public class Moore extends Automata{
	
	private HashMap<String, HashMap<String, String>> adjw;//Conexiones (grafo)
	private HashMap<String, String> salida;//Salida de cada estado
	
	public Moore(Vector<String> S, Vector<String> R, int estados) {
		super(S, R, estados);
	}
	
	/**
	 * Metodo encargado de agregar al grafo las conexiones entre estados
	 * @param i String que representa el nombre de cada estado
	 * @param src String que representa algun caracter del alfabeto
	 * @param dst String que representa el estado De destino
	 * @param sali String que representa algunc caracter del alfabeto de salida
	 */
	public void add(String i, String src, String dst, String sali) {
		salida.put(i, sali);
		HashMap<String, String> tempHashMap = adjw.getOrDefault(i, new HashMap<>());
		tempHashMap.put(src, dst);
		adjw.put(i, tempHashMap);
	}
}
