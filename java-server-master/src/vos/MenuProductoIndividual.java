package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class MenuProductoIndividual {
    @JsonProperty(value="id_menu")
    private Long id_menu;
    
    @JsonProperty(value="id_prod_individual")
    private Long id_prod_individual;

    public MenuProductoIndividual(@JsonProperty(value="id_menu") Long id_menu, @JsonProperty(value="id_prod_individual") Long id_prod_individual) {
        this.id_menu = id_menu;
        this.id_prod_individual = id_prod_individual;
    }

    public Long getId_menu() {
        return id_menu;
    }

    public void setId_menu(Long id_menu) {
        this.id_menu = id_menu;
    }
    
    public Long getId_prod_individual() {
        return id_prod_individual;
    }

    public void setId_prod_individual(Long id_prod_individual) {
        this.id_prod_individual = id_prod_individual;
    }
}
