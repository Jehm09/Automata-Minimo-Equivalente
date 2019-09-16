package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

public class Mealy extends Automata {

	private HashMap<String, HashMap<String, String[]>> adjw;// Conexiones (grafo)
	private Vector<HashSet<String>> sets;// Vectors de hashset que representa la particion final

	public Mealy(String S[], String R[], int Num_estados, String start) {
		super(S, R, Num_estados, start);
		adjw = new HashMap<>();
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
		String arr[] = { dst, sali };
		HashMap<String, String[]> tempHashMap = adjw.getOrDefault(i, new HashMap<>());
		tempHashMap.put(src, arr);
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

			for (Map.Entry<String, String[]> entry : adjw.get(s).entrySet()) {
				String v = entry.getValue()[0];
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
						if (!c.contains(adjw.get(anterior).get(S[i])[0])
								&& c.contains(adjw.get(strings).get(S[i])[0])) {
							exist = false;
						}
						if (c.contains(adjw.get(anterior).get(S[i])[0])
								&& !c.contains(adjw.get(strings).get(S[i])[0])) {
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

		for (Map.Entry<String, HashMap<String, String[]>> entry : adjw.entrySet()) {
			String keyString = "";
			for (Map.Entry<String, String[]> entry2 : entry.getValue().entrySet()) {
				keyString += entry2.getValue()[1] + " ";
			}
			keyString = keyString.trim();
			HashSet<String> set = map.getOrDefault(keyString, new HashSet<>());
			set.add(entry.getKey());
			map.put(keyString, set);
		}

		for (Map.Entry<String, HashSet<String>> entry : map.entrySet()) {
			sets.add(entry.getValue());
		}
	}

	@Override
	public String[] column() {
		String rst[] = new String[S.length + 1];
		
		for (int i = 0; i < rst.length; i++) {
			rst[i] = "";
		}
		
		for (int i = 1; i < S.length + 1; i++) {
			rst[i] = S[i - 1];
		}

		return rst;
	}

	@Override
	public String[][] data() {
		String rst[][] = new String[sets.size()][S.length + 1];
		HashMap<String, String> rename = new HashMap<>();
		
		for (int i = 0; i < rst.length; i++) {
			for (int j = 0; j < rst[0].length; j++) {
				rst[i][j] = "";
			}
		}
		
		int conta = 0;
		for (HashSet<String> s : sets) {
			for (String st : s) {
				rename.put(st, "P" + conta);
			}
			conta++;
		}
		

		for (int i = 0; i < rst.length; i++) {
			rst[i][0] = "P" + (i);
			for (int j = 1; j < rst[0].length; j++) {
				for (String s : sets.get(i)) {
					rst[i][j] = rename.get(adjw.get(s).get(S[j - 1])[0]) + " " + adjw.get(s).get(S[j - 1])[1];
					break;
				}
			}
		}

		return rst;
	}

}
