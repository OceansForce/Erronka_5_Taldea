package mugarria_5_7;

/**
 * Ikasle bat bere identifikatzaile, izen eta kurtsoarekin irudikatzen du
 * @author 5.taldea
 */

public class Alumno {
    private String identificador;
    private String nombre;
    private String curso;

    // Constructor

    /**
     * Alumno objetua sortzeko eraikitzailea
     * @param identificador Ikaslearen identifikatzailea
     * @param nombre Ikaslearen izena
     * @param curso Ikaslea matrikulatutako kurtsoa
     */
    public Alumno(String identificador, String nombre, String curso) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.curso = curso;
    }

    /**
     * Ikaslea kurtso berri batean matrikulatzen du
     * @param curso ikaslea matrikulatuko den kurtso berria
     */
    public void matricular(String curso){
        this.curso = curso + " 1";
    }

    /**
     * Ikaslea kurtsoz pasarazten du
     * Lehen kurtsoan badago, bigarrengora pasatuko du
     * Bestela, titulua jasoko du
     */
    public void pasarDeCurso(){
        String[] curso = this.curso.split(" ");
        int numeroCurso = Integer.parseInt(curso[1]);
        if(numeroCurso == 1)
        {
            this.curso = curso[0] + " " + (numeroCurso + 1);
        }
        else{
            this.curso = "Titulado en " + curso[0];
        }

    }

    // Getters y Setters

    /**
     * Ikaslearen identifikatzailea eskuratzen du
     * @return Ikaslearen identifikatzailea
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Ikasleari identifikatzailea ezartzen dio
     * @param identificador identifikatzaile berria
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * Ikaslearen izena eskuratzen du
     * @return ikaslearen izena
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Ikasleari izena ezartzen dio
     * @param nombre ikaslearen izena
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Ikaslearen kurtsoa eskuratzen du
     * @return Kurtsoaren izena
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Ikasleari matrikulatuko den kurtsoa ezartzen dio
     * @param curso kurtso berria
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }
}
