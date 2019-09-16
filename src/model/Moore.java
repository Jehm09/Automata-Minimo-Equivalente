package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

public class Moore extends Automata{
	
	private HashMap<String, HashMap<String, String>> adjw;//Conexiones (grafo)
	private HashMap<String, String> salida;//Salida de cada estado
	
	public Moore(String S[], String R[], int Num_estados, String start) {
		super(S, R, Num_estados, start);
		adjw = new HashMap<>();
		salida = new HashMap<>();
	}
	
	/**
	 * Metodo encargado de agregar al grafo las conexiones entre estados
	 * @param i String que representa el nombre de cada estado
	 * @param src String que representa algun caracter del alfabeto
	 * @param dst String que representa el estado De destino
	 * @param sali String que representa algunc caracter del alfabeto de salida
	 */
	@Override
	public void add(String i, String src, String dst, String sali) {
		salida.put(i, sali);
		HashMap<String, String> tempHashMap = adjw.getOrDefault(i, new HashMap<>());
		tempHashMap.put(src, dst);
		adjw.put(i, tempHashMap);
	}

	@Override
	public void dfs() {
		HashMap<String, Boolean> visitados = new HashMap<>();
		for (int i = 0; i < Num_states; i++) {
			visitados.put(states[i], false);
		}

		Stack<String> stack = new Stack<>();
		String s;
		stack.add(start);

		while (!stack.isEmpty()) {
			s = stack.pop();

			if (!visitados.get(s)) {
				visitados.put(s, true);
			}
			
			for (Map.Entry<String, String> entry : adjw.get(s).entrySet()) {
				String v = entry.getValue();
				if (!visitados.get(v))
					stack.push(v);
			}
			
		}
		
		for (int i = 0; i < Num_states; i++) {
			if(!visitados.get(states[i])) {
				adjw.remove(states[i]);
			}
		}
	}

	@Override
	public void generateEquivalentMinimum() {
		
	}
}
