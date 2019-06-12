package kruskal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Kruskal {

	private int cantidadNodos;
	private int aristasUsadas;
	private int aristasTotales;
	private Arista[] aristas;
	private int costo;
	private ArrayList<Arista> aristasDelGrafo = new ArrayList<Arista>();
	private static int padre[];
	
	public Kruskal(Scanner entrada) {
		int nodo1, nodo2, costo;
		this.costo = 0;
		this.aristasUsadas = 0;
		this.cantidadNodos = entrada.nextInt();
		this.aristasTotales = entrada.nextInt();
		this.aristas = new Arista[this.aristasTotales];
		for(int i=0; i<this.aristasTotales; i++) {
			nodo1 = entrada.nextInt();
			nodo2 = entrada.nextInt();
			costo = entrada.nextInt();
			this.aristas[i] = new Arista(nodo1, nodo2, costo);
		}
		Arrays.sort(this.aristas);
		this.padre = new int[this.cantidadNodos+1];
		for(int i=1; i<=this.cantidadNodos; i++) 
			this.padre[i] = i; 
	}
	
	public static int find(int x) {
		if(x == padre[x])
			return x;
		else 
			return find(padre[x]);
	}
	
	public static boolean mismoComponente(int x, int y) {
		if(find(x) == find(y))
			return true;
		return false;
	}
	
	public static void union(int x, int y) {
		padre[find(x)] = find(y);
	}
	
	public void calcularCosto() {
		int x, y, i=0;
		while(i<this.aristasTotales && this.aristasUsadas != (this.cantidadNodos-1)) {
			x = this.aristas[i].getNodo1();
			y = this.aristas[i].getNodo2();
			if(mismoComponente(x,y) == false) {
				this.costo += this.aristas[i].getCosto(); 
				this.aristasDelGrafo.add(this.aristas[i]);
				this.aristasUsadas++;
				union(x,y);
			}
			i++;
		}
		
	}
	
	public void mostrarCosto() {
		System.out.println("El costo del arbol abarcador minimo es: " + this.costo);
	}
	
	public void mostrarArbol() {
		System.out.println("Aristas del grafo: ");
		for(int i=0; i<this.aristasDelGrafo.size(); i++) {
			System.out.println(this.aristasDelGrafo.get(i).toString());
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner entrada = new Scanner(new FileReader("kruskal.in"));
		Kruskal prim = new Kruskal(entrada);
		entrada.close();
		prim.calcularCosto();
		prim.mostrarCosto();
		prim.mostrarArbol();
	}
}
