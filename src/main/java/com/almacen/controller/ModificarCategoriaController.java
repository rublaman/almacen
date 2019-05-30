
import com.almacen.EJB.CategoriaFacadeLocal;
import com.almacen.EJB.PersonaFacadeLocal;
import com.almacen.modelo.Categoria;
import com.almacen.modelo.Persona;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ModificarCategoriaController implements Serializable{
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
  
    private List<Categoria> listaCategorias;
    
    @Inject
    private Categoria categoriaSeleccionada;
  
    @PostConstruct
    public void init(){
        
        listaCategorias = categoriaEJB.findAll();
        
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategoria(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public Categoria getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(Categoria categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }
    
    public void establecerCategoria(Categoria cat){
        this.categoriaSeleccionada = cat;
    }
    
    public void actualizarCategoria(){
        categoriaEJB.edit(categoriaSeleccionada);
    }
    
    public void eliminarCategoria(){
        categoriaEJB.remove(categoriaSeleccionada);
        listaCategorias.remove(categoriaSeleccionada);
    }
}