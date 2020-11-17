package gestaofarmacia;

public class Cliente extends Pessoa{
	@Override
	public String toString() {
		return "Cliente [Codigo=" + id  + ", nome=" + nome + ", morada=" + morada + "]";
	}
}
