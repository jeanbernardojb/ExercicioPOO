package Sistema_Amigo;

import java.util.List;

public class TestaSistemaAmigo {

    public static void main(String[] args) throws Exception {

		SistemaAmigo sistema = new SistemaAmigo();
		try {
			sistema.cadastraAmigo("José", "jose@dcx.ufpb.br");
			sistema.cadastraAmigo("Maria", "maria@dcx.ufpb.br");
			System.out.println("José e Maria cadastrado com sucesso!");
		} catch (AmigoJaExisteException e) {
			System.out.println(e.getMessage());
		}

		try {
			sistema.configuraAmigoSecretoDe("jose@dcx.ufpb.br", "maria@dcx.ufpb.br");
			sistema.configuraAmigoSecretoDe("maria@dcx.ufpb.br", "jose@dcx.ufpb.br");
			System.out.println("Amigos configurados com sucesso!");
		} catch (AmigoInexistenteException e) {
			System.out.println(e.getMessage());
		}

		sistema.enviarMensagemParaAlguem("Bom dia amiga!", "maria@dcx.ufpb.br", "jose@dcx.ufpb.br", true);
		sistema.enviarMensagemParaTodos("Bom dia meus amigos!", "maria@dcx.ufpb.br", true);

		List<Mensagem> msgAnonima = sistema.pesquisaMensagensAnonimas();

		for (Mensagem m : msgAnonima) {
			System.out.println(m.getTextoCompletoAExibir());
		}

		try {
			String emailDoAmigoDeJose = sistema.pesquisaAmigoSecretoDe("jose@dcx.ufpb.br");
			if (emailDoAmigoDeJose.equals("maria@dcx.ufpb.br")){
				System.out.println("OK!");
			} else {
				System.out.println("Erro no amigo de José!");
			}
		} catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
			System.out.println("problema ao ver amigo" + e.getMessage());
		}
	}
    
}
