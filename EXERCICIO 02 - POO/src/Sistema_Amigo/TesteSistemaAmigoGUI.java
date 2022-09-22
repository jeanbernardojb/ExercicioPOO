package Sistema_Amigo;

import javax.swing.JOptionPane;

public class TesteSistemaAmigoGUI {

    public static void main(String[] args) throws AmigoNaoSorteadoException {
        int maxMsg = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade maxíma de mensagens no sistema ?"));
        SistemaAmigo sistema = new SistemaAmigo();

        int quatTotalDeParticipantes = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de participamtes ?"));

        for(int i = 0; i < quatTotalDeParticipantes; i++) {
            String nomeAmigo = JOptionPane.showInputDialog("Qual o nome da pessoa ?");
            String emailAmigo = JOptionPane.showInputDialog("Digite o email da pessoa ?");
            try{
                sistema.cadastraAmigo(nomeAmigo, emailAmigo);
                JOptionPane.showMessageDialog(null,"Pessoa cadastrada com sucesso na brincadeira");
            } catch (AmigoJaExisteException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                e.printStackTrace();
            }
        }

        for(int i = 0; i < quatTotalDeParticipantes; i++) {
            String emailDapessoa = JOptionPane.showInputDialog("Digite o email da pessoa que vai brincar de amigo secreto ?");
            String emailDoAmigoSecreto = JOptionPane.showInputDialog("Digite o email do amigo secreto da pessoa ?");
            try {
                sistema.configuraAmigoSecretoDe(emailDapessoa, emailDoAmigoSecreto);
                JOptionPane.showMessageDialog(null, "Amigos cofigurados com sucesso!");
                sistema.cadastraResultado(emailDapessoa, emailDoAmigoSecreto);
                JOptionPane.showMessageDialog(null, " Amigo: " + emailDapessoa + " Pegou: "+ emailDoAmigoSecreto + " No amigo secreto ");
            } catch (AmigoInexistenteException e) {
                JOptionPane.showMessageDialog(null, "Esse amigo não existe no sistema!");
                throw new AmigoNaoSorteadoException("Problema no sorteio!");
                
            }
        }
        String texto = JOptionPane.showInputDialog("Digite o texto da mensagem");
        String emailRemetente = JOptionPane.showInputDialog("Informe o email do remetente");
        String msgEhanonima = JOptionPane.showInputDialog("A mensagem é anônima ? Digite SIM ou NÃO");
        boolean ehAnonima;

        if (msgEhanonima.equalsIgnoreCase("SIM")) {
            ehAnonima = true;
            JOptionPane.showMessageDialog(null," Mensagem anônima para todos: " + texto);
        } else {
            ehAnonima = false;
            JOptionPane.showMessageDialog(null," Texto de: " + emailRemetente +  " Para todos: " + texto);
        }
        sistema.enviarMensagemParaTodos(texto, emailRemetente, ehAnonima);
    } 
}
