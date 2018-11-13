/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pulsevida;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20161BSI0012
 */
public class Monitor {
    
    public Monitor(){
    }
    
    public boolean disparaNotificacao(int frequencia, String nomeUsuario,String nomeContato, String celular) throws Exception {
        if (frequencia < 0) {
            throw new Exception("Frequencia invalida. Verificar sensor de frequencia.");
        }
        
        //Exemplo de notificação que será enviada via sms e/ou e-mail
        String mensagem = "O paciente " + nomeUsuario + " registrou uma frequencia cardiaca de " + frequencia + " bpm.";
        java.util.Date data = new Date();
        String dataF = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(data);
        
        try{
            Notificacao notificacao = new Notificacao(nomeUsuario,nomeContato, mensagem, dataF);
            
            System.out.println("Atenção! Notificacão enviada pois o paciente " + nomeUsuario + " registrou uma frequência de " + frequencia + "bpm.");
            
            return true;
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            
            return false;
        }
    }
    
    public void monitoraFrequencia(FrequenciaCardiaca frequenciaCardiaca, Usuario usuario,String nomeContato){        
        if ((frequenciaCardiaca.getFrequencia() < 60) || (frequenciaCardiaca.getFrequencia() > 100)){
            try {
                this.disparaNotificacao(frequenciaCardiaca.getFrequencia(), usuario.getNome(),nomeContato, usuario.getCelular());
                
                
            } catch (Exception ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
    public void adicionaHistorico(FrequenciaCardiaca frequenciaCardiaca, Usuario usuario){
        usuario.getHistorico().add(frequenciaCardiaca); 
    }
    
    public void simularFrequencia(){
        //Random freq = new Random();
        //ePssoa pessoa = new Pessoa(11, "Joaomanel","27 99969999");
//        String nomeContato = "Higor";
//        int timer = 0;
//        FrequenciaCardiaca freq = new FrequenciaCardiaca();
//        while(timer < 500)
//        {
//            freq.atualizarFrequencia(45 + (int)(Math.random() * (120 - 50)));
//            this.monitoraFrequencia(freq.getFrequencia(),pessoa, nomeContato);
//            timer++;
//        }        
    }
}
