package ws_pousada.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Validador {
    
    public static void main(String[] args) {
        
        
    }
    
    
    public String dateTimeSqlToJava(String localDateTimeToString){
        String data;
        try{
            data = localDateTimeToString.replace("T", " ");
            String [] a = data.split(" ");
            String [] b = a[0].split("-");
            data = b[2]+"/"+b[1]+"/"+b[0]+" "+a[1];
        }catch(Exception e){
            data = ""; 
        }
        return data;
    }
    
    public String localDateToString(String localDateToString){
        // usar para bind de sql para javabeans
        String data;
        try{
            String [] a = localDateToString.split("-");
            data = a[2]+"/"+a[1]+"/"+a[0];
        }catch(Exception e){
            data = "";
        }
        return data;
    }
    
    public LocalDate stringToLocalDate(String stringToLocalDate){
        //recebe 18/10/1992 e retorna 1992-10-18 em LocalDate
        //usar para inserir SQL
        LocalDate data;
        try{
            String [] a = stringToLocalDate.split("/");
            String dataString = a[2]+"-"+a[1]+"-"+a[0];
            data = LocalDate.parse(dataString);
        }catch(Exception e){
            data = null;
        }
        return data;
    }
    
    public Integer obterDiasEntreDatas(String de, String ate){
        Integer resultado = null;
        LocalDate lde = this.stringToLocalDate(de);
        LocalDate late = this.stringToLocalDate(ate);
        
        Duration diferenca = Duration.between(lde.atStartOfDay(),late.atStartOfDay());
        Long r = diferenca.toDays();
        resultado = Integer.parseInt(r.toString());
        
        return resultado;
    }
    
    public Boolean isInicioAntesDaSaida(String de, String ate){
        LocalDate dateDe = stringToLocalDate(de);
        LocalDate dateAte = stringToLocalDate(ate);
        if(dateAte.isBefore(dateDe)){
            return false;
        }
        return true;
    }
    
    public LocalDate getDataFinalReserva(LocalDate de, Integer diasReservados){
        LocalDate ate = de.plusDays(diasReservados.longValue()-1);
        return ate;
    }
    
    public LocalDateTime getLocalDateTimeInUTC(){
        ZonedDateTime nowUTC = ZonedDateTime.now(ZoneOffset.systemDefault());
        return nowUTC.toLocalDateTime();
    }
    
    public LocalDate getLocalDateInUTC(){
        LocalDate now = LocalDate.now(ZoneOffset.systemDefault());
        return now;
    }
    
    public Integer getIdadeFromDtNascimento(LocalDate dtNascimento){
        Integer idade = LocalDate.now().getYear()-dtNascimento.getYear();
        return idade;
    }
}
