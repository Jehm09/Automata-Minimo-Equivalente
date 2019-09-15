package model;

import java.util.HashMap;
import java.util.Vector;

public class Mealy extends Automata{
	
	private HashMap<String, HashMap<String, String[]>> adjw;//Conexiones (grafo)
	
	public Mealy(Vector<String> S, Vector<String> R, int estados) {
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
		String arr[] = {dst, sali};
		HashMap<String, String[]> tempHashMap = adjw.getOrDefault(i, new HashMap<>());
		tempHashMap.put(src, arr);
		adjw.put(i, tempHashMap);
	}
	

}
