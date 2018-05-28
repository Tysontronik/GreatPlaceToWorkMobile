package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Dell on 09/03/2018.
 */

public class PreguntasModulo  extends  RespuestasModulo  implements  Serializable {

   public int idPregunta;
    public  int peso;
    public  String pregunta;
    public RespuestasModulo respuestas;
}
