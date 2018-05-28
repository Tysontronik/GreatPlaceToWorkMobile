package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 30/01/2018.
 */

public class ModuloInicio  implements Serializable{

    public int getIdGrupoModulo() {
        return idGrupoModulo;
    }

    public void setIdGrupoModulo(int idGrupoModulo) {
        this.idGrupoModulo = idGrupoModulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public	 int idGrupoModulo	;

   public  String modulo;



}
