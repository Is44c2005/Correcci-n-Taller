
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ColaCompra {


    private Queue<Compra> compras;

    public ColaCompra(){
        compras=new LinkedList<Compra>();
    }

    public void encolar(Compra compra){
        compras.add(compra);
    }

    public List <Compra> listaCompras(){
        return new ArrayList<>(compras);
    }

    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder();
        for (Compra c: compras){
            sb.append(c);
        }
        return sb.toString();
    }
}

