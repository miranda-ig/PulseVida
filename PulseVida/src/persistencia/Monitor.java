/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import pulsevida.FrequenciaCardiaca;
import pulsevida.Notificacao;
import pulsevida.Pessoa;
import pulsevida.Usuario;

/**
 *
 * @author 20161BSI0012
 */
public class Monitor {
    
    public Monitor(){
    }
    
    //Metodo que faz o envio da 
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
    
    //Avalia a frequencia cardiaca e envia a notificacao
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
    
    //Metodo para simulação do monitoramento
    public void simularMonitoramento(){
        //Random freq = new Random();
        ArrayList<FrequenciaCardiaca> historico = new ArrayList<>();
        Usuario usuario = new Usuario("Joaomanel","27 99969999", "teste@teste.com","joaomanel123","naoseiasenha",historico);
        String nomeContato = "Higor";        
        FrequenciaCardiaca freq = new FrequenciaCardiaca();
        
        int timer = 0;
        while(timer < 500)
        {
            freq.atualizarFrequencia(45 + (int)(Math.random() * (120 - 50)));
            this.monitoraFrequencia(freq, usuario, nomeContato);
            timer++;
        }        
    }
}