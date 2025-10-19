public class Compra {
    private int entradas;
    private String nombrep;
    private float valor;
    private int cedula;


    public Compra(int entradas, String nombrep, float valor, int cedula) {
        this.entradas = entradas;
        this.nombrep = nombrep;
        this.valor = valor;
        this.cedula = cedula;
    }

    public int getEntradas() {
        return entradas;
    }

    public int getCedula() {
        return cedula;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public String getNombrep() {
        return nombrep;
    }

    public void setNombrep(String nombrep) {
        this.nombrep = nombrep;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "\nPelícula: " + nombrep + "\n"+
                "Entradas: " + entradas + '\n' +
                "Valor: $" + valor +"\n";
    }
}