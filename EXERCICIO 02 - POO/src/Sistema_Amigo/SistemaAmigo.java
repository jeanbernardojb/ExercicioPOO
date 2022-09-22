package Sistema_Amigo;

import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo {

    private List<Mensagem> mensagens;
    private List<Amigo> amigos;

    public SistemaAmigo() {
        this.mensagens = new ArrayList<>();
        this.amigos = new ArrayList<>();
    }

    public List<Mensagem> getMensagem() {
        return this.mensagens;
    }

    public List<Amigo> getAmigo() {
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
        for (Amigo a : this.amigos) {
            if(a.getEmail().equals(emailAmigo)){
                throw new AmigoJaExisteException("Amigo já existe no sistema!");
            }
        }
        Amigo amigoNovo = new Amigo(nomeAmigo, emailAmigo);
        this.amigos.add(amigoNovo);
    }

    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
        for (Amigo a : this.amigos) {
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
        for (Amigo a : this.amigos) {
            if (a.getEmail().equals(emailDaPessoa)) {
                a.setEmailAmigoSorteado(emailAmigoSorteado);
                return;
            }             
        }
        throw new AmigoInexistenteException("Amigo não registrado no sistema!");
        
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) 
    throws AmigoInexistenteException, AmigoNaoSorteadoException {

        for (Amigo a : this.amigos) {
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

    public List<Amigo> todosParticipantes() throws AmigoInexistenteException {
        List<Amigo> amigo = new ArrayList<Amigo>();

        for (Amigo a : amigo) {
            System.out.println(a.getNome());
        }
        throw new AmigoInexistenteException("Não exsite amigos!");
    }

    public List<Amigo> pesquisarTodosParticipantes() {
        List<Amigo> participantes = amigos;
        participantes.addAll(amigos);
        return participantes;
    }

    public void cadastraResultado(String emailDapessoa, String emailDoAmigoSecreto) {
    }

}
