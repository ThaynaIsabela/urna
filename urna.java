// Desenvolva uma aplicação usando java de uma Urna Eleitoral. 
// Ela deverá receber um voto apenas de cada eleitor. 
// Esse voto deverá ter três tipos de candidato: presidente, senador e deputado. 

// A urna deverá emitir as seguintes informações quando solicitada:
// - quantidade de votos computados,
// - lista do total de votos por candidato
// - total de votos de um candidato específico.

// Para desenvolver é obrigatório utilizar o que foi visto em aula como 
// interfaces e herança. 

// Desenvolva uma classe de teste. A entrega deverá ser feita através de 
// um link do github postado aqui na tarefa.

abstract class Pessoa {
  protected String cpf;
  protected String nome;
  private String tituloEleitoral;
  private String zonaEleitoral;

  public String getCPF() {
    return this.cpf;
  }

  public String getNome() {
    return this.nome;
  }
}

class Eleitor extends Pessoa {

  public Eleitor(String cpf, String nome) {
    this.cpf = cpf;
    this.nome = nome;
  }

}

interface Candidatura {
  public void habilitar();
  public void receberVoto();
}


class Candidato extends Eleitor implements Candidatura {
  private int numero;
  private String cargo;
  private boolean habilitado = false;
  private int totalDeVotos = 0;

  public Candidato(int numero, String cargo, String nome, String cpf) {
    super(cpf, nome);
    this.cargo = cargo;
    this.numero = numero;
  }

  public void habilitar() {
    this.habilitado = true;
  }

  public void receberVoto() {
    this.totalDeVotos += 1;
  }

  public int getTotalDeVotos() {
    return this.totalDeVotos;
  }

  public int getNumero() {
    return this.numero;
  }

  public String getCargo() {
    return this.cargo;
  }
}

class Voto {
  private Eleitor eleitor;
  private Candidato presidente;
  private Candidato senador;
  private Candidato deputado;

  public Voto(Eleitor eleitor, Candidato presidente, Candidato senador, Candidato deputado) {
    this.eleitor = eleitor;
    this.presidente = presidente;
    this.senador = senador;
    this.deputado = deputado;

    this.presidente.receberVoto();
    this.senador.receberVoto();
    this.deputado.receberVoto();
  }

}

class Urna {
  private java.util.ArrayList<Voto> votos = new java.util.ArrayList<Voto>();
  private java.util.ArrayList<Candidato> candidatos = new java.util.ArrayList<Candidato>();
  private java.util.ArrayList<Eleitor> eleitores = new java.util.ArrayList<Eleitor>();  

  public void adicionarCandidato(Candidato candidato) {
    this.candidatos.add(candidato);
  }

  public boolean eleitorVotou(Eleitor eleitor) {
    for (int x = 0; x < this.eleitores.size(); x+=1) {
      if (this.eleitores.get(x).getCPF() == eleitor.getCPF()) {
        return true;
      }
    }
    return false;
  }

  public void votar(Eleitor eleitor, int presidente, int senador, int deputado) {
    if (!this.eleitorVotou(eleitor)) {
      Candidato cpresidente = this.localizarCandidato(presidente);
      Candidato csenador = this.localizarCandidato(senador);
      Candidato cdeputado = this.localizarCandidato(deputado);
      eleitores.add(eleitor);
      this.votos.add(new Voto(eleitor, cpresidente, csenador, cdeputado));
      System.out.println("Voto computado!");
    } else {
      System.out.println("Este eleitor já votou!");
    }
  }

  public Candidato localizarCandidato(int numero) {
    Candidato encontrado = this.candidatos.get(0);
    for (int x = 0; x < this.candidatos.size(); x+=1) {
      if (this.candidatos.get(x).getNumero() == numero) {
        encontrado = this.candidatos.get(x);
        break;
      }
    }
    return encontrado;
  }

  public void quantidadeComputado() {
    System.out.println("Total de votos computados:" + String.format("%5d", votos.size()));
  }

  public void totalDeVotosPorCandidato() {
    System.out.println("\nTotal de Votos por Candidato:\n");
    for (int x = 0; x < this.candidatos.size(); x+=1) {
      Candidato candidato = this.candidatos.get(x);
      System.out.println("Candidato: " + candidato.getNome() + " - Cargo: " + candidato.getCargo() + ": " + String.format("%d", candidato.getTotalDeVotos()));
    }
  }

  public void totalDeVotosDoCandiato(int numero) {
    Candidato candidato = this.localizarCandidato(numero);
    this.totalDeVotosDoCandiato(candidato);
  }

  public void totalDeVotosDoCandiato(Candidato candidato) {
    System.out.println("\nTotal de Votos para um candidato:\n");
    System.out.println("Candidato: " + candidato.getNome() + " - Cargo: " + candidato.getCargo() + ": " + String.format("%d", candidato.getTotalDeVotos()));
  }
}

class UrnaTest {
  public static void run() {
    Urna urna = new Urna();
    urna.adicionarCandidato(new Candidato(200, "Presidente", "Flash", "156.432.666-00"));
    urna.adicionarCandidato(new Candidato(300, "Presidente", "Superman", "789.432.123-00"));
    urna.adicionarCandidato(new Candidato(5000, "Senador", "Ciclope", "541.432.123-00"));
    urna.adicionarCandidato(new Candidato(6000, "Senador", "Wolverine", "639.432.123-00"));
    urna.adicionarCandidato(new Candidato(7000, "Senador", "Tempestade", "613.432.123-00"));
    urna.adicionarCandidato(new Candidato(30000, "Deputado", "Margie", "279.589.123-00"));
    urna.adicionarCandidato(new Candidato(40000, "Deputado", "Homer", "634.432.123-00"));
    urna.adicionarCandidato(new Candidato(50000, "Deputado", "Barth", "333.432.123-00"));
    urna.adicionarCandidato(new Candidato(60000, "Deputado", "Lisa", "582.589.123-00"));
    urna.adicionarCandidato(new Candidato(70000, "Deputado", "Meg", "589.432.258-00"));

    urna.votar(new Eleitor("123.456.789-0", "Thor"), 200,  6000, 30000);

    urna.quantidadeComputado();
    urna.totalDeVotosPorCandidato();
    urna.totalDeVotosDoCandiato(30000);
  }
}

class Main {
  public static void main(String[] args) {
    UrnaTest.run();
  }
}