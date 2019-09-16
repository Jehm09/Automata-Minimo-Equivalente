package model;

import java.util.*;

public class Moore extends Automata {

	private HashMap<String, HashMap<String, String>> adjw;// Conexiones (grafo)
	private HashMap<String, String> salida;// Salida de cada estado
	private HashMap<String, HashSet<String>> firstP;
	private Vector<HashSet<String>> sets;// vectores de hashset que representa la particion final

	public Moore(String S[], String R[], int Num_estados, String start) {
		super(S, R, Num_estados, start);
		adjw = new HashMap<>();
		salida = new HashMap<>();
		firstP = new HashMap<>();
		sets = new Vector<>();
	}

	/**
	 * Metodo encargado de agregar al grafo las conexiones entre estados
	 * 
	 * @param i    String que representa el nombre de cada estado
	 * @param src  String que representa algun caracter del alfabeto
	 * @param dst  String que representa el estado De destino
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
			if (!visitados.get(states[i])) {
				adjw.remove(states[i]);
			}
		}
	}

	@Override
	public void generateEquivalentMinimum() {
		dfs();
		createFirstP();

		for (Map.Entry<String, HashSet<String>> entry : firstP.entrySet()) {
			sets.add(entry.getValue());
		}

		boolean st = true;

		while (st) {
			int tam = sets.size();
			for (int s = 0; s < sets.size(); s++) {
				check(s);
			}
			if (tam == sets.size()) {
				st = false;
			} else {
				tam = sets.size();
			}
		}

	}

	public void check(int s) {
		int val = sets.size();
		boolean create = false;
		String anterior = "";

		for (String strings : sets.get(s)) {
			if (!anterior.isEmpty()) {
				boolean exist = true;
				for (HashSet<String> c : sets) {
					for (int i = 0; i < S.length && exist; i++) {
						if (!c.contains(adjw.get(anterior).get(S[i])) && c.contains(adjw.get(strings).get(S[i]))) {
							exist = false;
						}
						if (c.contains(adjw.get(anterior).get(S[i])) && !c.contains(adjw.get(strings).get(S[i]))) {
							exist = false;
						}
					}
				}
				if (!exist && !create) {
					sets.add(new HashSet<>());
					create = true;
				}
				if (!exist && create) {
					sets.get(val).add(strings);
					sets.get(s).remove(strings);
				}
			}
			if (!create) {
				anterior = strings;
			}
		}
		if (create) {
			check(val);
		}
	}

	@Override
	public void createFirstP() {
		HashMap<String, HashSet<String>> map = new HashMap<>();

		for (Map.Entry<String, String> entry : salida.entrySet()) {
			HashSet<String> set = map.getOrDefault(entry.getValue(), new HashSet<>());
			set.add(entry.getKey());
			map.put(entry.getValue(), set);
		}

		firstP = map;
	}

}
