package mugarria_5_7;

/**
 * Ondorengo klaseak array batean biltegiratutako zenbaki multzo
 * batekin prozesu desberdinak egiten ditu
 * @author 5.taldea
 * */

public class FuncionesADocumentar {
    /** Klaseko metodoetan aplikatuko den zenbakien multzoa du barnean
     * array batean biltegiratua, metodoen emaitzak ere erakusten ditu
     * {@link FuncionesADocumentar#suma(int[])}
     * {@link FuncionesADocumentar#promedio(int[])}
     * {@link FuncionesADocumentar#mayor(int[])}
     * {@link FuncionesADocumentar#menor(int[])}
     * {@link FuncionesADocumentar#invertir(int[])}
     * {@link FuncionesADocumentar#ordenar(int[])}
     * @return Zenbakiei aplikatu zaizkien metodoen emaitzak
     */
    public static void main(String[] args) {
        
        int a[] = {7, 14, 9, 18, 11, 6, 13, 12, 1, 5, 4, 3, 2, 8, 10};

        System.out.println("La suma de los elementos del arreglo es: " + suma(a));
        System.out.println("El promedio de los elementos del arreglo es: " + promedio(a));
        System.out.println("El mayor de los elementos del arreglo es: " + mayor(a));
        System.out.println("El menor de los elementos del arreglo es: " + menor(a));
        System.out.println("El arreglo invertido es: ");
        invertir(a);
        System.out.println("El arreglo ordenado es: ");
        ordenar(a);
    }

    /**
     * Arraytik jasotako zenbakien batura egiten du
     * @param a batuketa kalkulatuko den arrayko zenbakien multzoa
     * @return Array-ko zenbakien batura
     */

    public static int suma(int a[]) {
        int suma = 0;
        for (int i = 0; i < a.length; i++)
            suma += a[i];
        return suma;
    }

    /**
     * Array barneko zenbakien bataz bestekoa kalkulatzen du
     * Lehenagoko klaseko batuketa erabiltzen du horretarako:
     * {@link FuncionesADocumentar#suma(int[])}
     * @param a Arrayaren zenbateko luzera jasotzen du
     * @return Bataz besteko zenbakia
     */

    public static double promedio(int a[]) {
        return suma(a) / a.length;
    }

    /**
     * Zenbaki multzoko zenbakien artean handiena bilatzen du
     * @param a Handiena bilatu nahi den zenbaki multzoa
     * @return Zenbaki handiena
     */
    public static int mayor(int a[]) {
        int mayor = a[0];
        for (int i = 1; i < a.length; i++)
            if (a[i] > mayor)
                mayor = a[i];
        return mayor;
    }

    /**
     * Multzoko zenbakien artean txikiena bilatzen du
     * @param a Txikiena aurkitu  nahi den zenbaki multzoa
     * @return Zenbaki txikiena
     */
    public static int menor(int a[]) {
        int menor = a[0];
        for (int i = 1; i < a.length; i++)
            if (a[i] < menor)
                menor = a[i];
        return menor;
    }

    /**
     * Multzoko zenbakiak alderantzizko ordenean erakusten ditu
     * @param a Alderantziztu nahi diren zenbaki multzoa
     * @return Zenbaki multzoa alderantziz
     */
    public static void invertir(int a[]) {
        for (int i = a.length - 1; i >= 0; i--)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    /**
     * Aurreko metodoan zenbaki multzoa alderantziz erakutsi ondoren
     * lehenagoko ordenera itzultzen ditu
     * @param a Ordenatzeko zenbaki multzoa jasotze du
     * @return Zenbaki multzoa orden nagusian
     */
    public static void ordenar(int a[]) {
        int aux;
        for (int i = 0; i < a.length - 1; i++)
            for (int j = i; j < a.length; j++)
                if (a[i] > a[j]) {
                    aux = a[i];
                    a[i] = a[j];
                    a[j] = aux;
                }
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

}
