package br.ufpb.dcx.amigosecreto;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaAmigoMap {

    private List<Mensagem> mensagens;
    private Map<String,Amigo> amigos;

    public SistemaAmigoMap() {
        this.mensagens = new ArrayList<>();
        this.amigos = new HashMap<>();
    }

    public List<Mensagem> getMensagem() {
        return this.mensagens;
    }

    public Map<String,Amigo> getAmigo() {
        return this.amigos;
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> msgAnonima = new ArrayList<>();

        for (Mensagem msg : this.mensagens) {
            if (msg.ehAnonima()) {
                msgAnonima.add(msg);
            }
        }
        return msgAnonima;
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {
        for (Amigo a : this.amigos.values()) {
            if(a.getEmail().equals(emailAmigo)){
                throw new AmigoJaExisteException("Amigo já existe no sistema!");
            }
        }
        Amigo amigoNovo = new Amigo(nomeAmigo, emailAmigo);
        this.amigos.put(emailAmigo,amigoNovo);
    }

    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
        for (Amigo a : this.amigos.values()) {
            if (a.getEmail().equals(emailAmigo)) {
                return a;
            } 
        }
        throw new AmigoInexistenteException("Amigo pesquisado não existe!");

    }

    public void enviarMensagemParaTodos(String texto, String remetente, boolean ehAnonima) {
        MensagemParaTodos msg = new MensagemParaTodos(texto, remetente, ehAnonima);
        this.mensagens.add(msg);
    }

    public void enviarMensagemParaAlguem(String texto, String remetente, String destinatario,
            boolean ehAnonima) {
        MensagemParaAlguem msg = new MensagemParaAlguem(texto, remetente, destinatario, ehAnonima);
        this.mensagens.add(msg);
    }

    public List<Mensagem> pesquisaTodasAsMensagens() {
        return this.mensagens;
    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException {
        for (Amigo a : this.amigos.values()) {
            if (a.getEmail().equals(emailDaPessoa)) {
                a.setEmailAmigoSorteado(emailAmigoSorteado);
                return;
            }             
        }
        throw new AmigoInexistenteException("Amigo não registrado no sistema!");
        
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) 
    throws AmigoInexistenteException, AmigoNaoSorteadoException {

        for (Amigo a : this.amigos.values()) {
            if (a.getEmail().equals(emailDaPessoa)) {
                String emailAmigoSorteado = a.getEmailAmigoSorteado();
                if (emailAmigoSorteado == null){
                    throw new AmigoNaoSorteadoException("Amigo não foi sorteado!");
                }else {
                    return emailAmigoSorteado; 
                }  
            }
        }
        throw new AmigoInexistenteException("Amigo não está registrado no sistema!");

    }

    public Map<String,Amigo> todosParticipantes() throws AmigoInexistenteException {
        Map<String,Amigo> amigo = new HashMap<>();

        for (Amigo a : amigo.values()) {
            System.out.println(a.getNome());
        }
        throw new AmigoInexistenteException("Não exsite amigos!");
    }

    public Map<String, Amigo> pesquisarTodosParticipantes() {
        Map<String, Amigo> participantes = amigos;
        participantes.putAll(amigos);
        return participantes;
    }

    public void cadastraResultado(String emailDapessoa, String emailDoAmigoSecreto) {
    }

}