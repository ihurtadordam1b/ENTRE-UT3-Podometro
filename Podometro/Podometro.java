/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @author    - Itxaso Hurtado - 
 */
public class Podometro {
    //CONSTANTES
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    
    //ATRIBUTOS O VARIABLES DE INSTANCIA
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        sexo = 'M';
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
         return marca;   
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        
        if(sexo=='H') {
            longitudZancada = Math.ceil(altura*ZANCADA_HOMBRE);
        }
        else {
            longitudZancada = Math.floor(altura*ZANCADA_MUJER);
        }
        
    }

     /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFin – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,int horaFin) {
        int horaIniciada;
        int horaFinal;
        horaIniciada = horaInicio;
        horaFinal = horaFin;
        
        tiempo = horaFinal-horaIniciada;
        if(horaIniciada>=2100)  {
            caminatasNoche ++; 
        }
        switch(dia) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                totalPasosLaborables += pasos;
                totalDistanciaSemana += pasos*longitudZancada;
                break;
            case 6:
                totalPasosSabado += pasos;
                totalDistanciaFinSemana += pasos*longitudZancada;
                break;
            case 7:
                totalPasosDomingo += pasos;
                totalDistanciaFinSemana += pasos*longitudZancada;
                break;
        }   
       
    }
    
     /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuración del podómetro"); 
        System.out.println("***************************");
        System.out.println("");
        System.out.println("Altura: "+altura/100+" metros");
        //No funciona
        if(sexo=='H') {
            System.out.println("Sexo: HOMBRE");
        }
        else {
            System.out.println("Sexo: MUJER");
        }
        System.out.println("Sexo: "+sexo);
        System.out.println("Longitud zancada: "+longitudZancada/100+" metros");

    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadísticas");
        System.out.println("***************************");
        System.out.println("");
        System.out.println("Distancia recorrida toda la semana: "+totalDistanciaSemana/100000+"Km");
        System.out.println("Distancia recorrida fin de semana: "+totalDistanciaFinSemana/100000+"Km");
        System.out.println("");
        System.out.println("Nº pasos días laborables: "+totalPasosLaborables);
        System.out.println("Nº pasos SADADO: "+totalPasosSabado);
        System.out.println("Nº pasos DOMINGO: "+totalPasosDomingo);
        System.out.println("Nº caminatas realizadas a partir de las 21h: "+caminatasNoche);
        
        double hora;
        double minutos;
        
        hora = Math.floor(tiempo/100);
        minutos= (tiempo/100)-hora;
        if(minutos>=60) {
            minutos -= 60 ;
            hora ++;
        }

        System.out.println("Tiempo total caminado en la semana: "+hora+"h. y "+minutos+"m.");
        System.out.println("Día/s con más pasos caminados: "+diaMayorNumeroPasos());
        
    }

   

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String diaMayorNumeroPasos = "";
        
        if(totalPasosLaborables>totalPasosSabado){
            diaMayorNumeroPasos= "laboral";
        }
        if(totalPasosLaborables>totalPasosDomingo){
            diaMayorNumeroPasos= "laboral";
        }
        if(totalPasosSabado>totalPasosDomingo){
            diaMayorNumeroPasos= "sabado";
        }
        if(totalPasosLaborables<totalPasosSabado){
            diaMayorNumeroPasos= "sabado";
        }
        if(totalPasosDomingo>totalPasosLaborables){
            diaMayorNumeroPasos= "domingo";
        }
        if(totalPasosDomingo>totalPasosSabado){
            diaMayorNumeroPasos= "domingo";
        }
         
        return diaMayorNumeroPasos;
    }
    
    
    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
    altura = 0;
    sexo = 'M';
    longitudZancada = 0;
    totalPasosLaborables = 0;
    totalPasosSabado = 0;
    totalPasosDomingo = 0;
    totalDistanciaSemana = 0;
    totalDistanciaFinSemana = 0;
    tiempo = 0;
    caminatasNoche = 0;
        

    }

}
