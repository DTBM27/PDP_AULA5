import java.util.Random;
import java.util.Scanner;

class Login {
    private static Login instance;
    private String usuario;
    private String senha;

    private Login() {} 

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    public boolean autenticar(String usuario, String senha) {
        return this.usuario.equals(usuario) && this.senha.equals(senha);
    }

    public void cadastrar(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String gerarCaptcha() {
        String caracteres = "1234567890!@#$%^&*";
        StringBuilder captcha = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            captcha.append(caracteres.charAt(rand.nextInt(caracteres.length())));
        }
        return captcha.toString();
    }
}

public class SistemaLogin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = Login.getInstance();

        System.out.println("=== Cadastro ===");
        System.out.print("Digite o nome de usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();
        login.cadastrar(usuario, senha);

        System.out.println("\n=== Login ===");
        System.out.print("Usuário: ");
        String usuarioLogin = scanner.nextLine();
        System.out.print("Senha: ");
        String senhaLogin = scanner.nextLine();

    
        String codigo = login.gerarCaptcha();
        System.out.println("Digite o código para verificar que você não é um bot: " + codigo);
        System.out.print("Código: ");
        String respostaCaptcha = scanner.nextLine();

        if (!codigo.equals(respostaCaptcha)) {
            System.out.println("Erro: código incorreto. Acesso negado.");
        } else if (login.autenticar(usuarioLogin, senhaLogin)) {
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }

        scanner.close();
    }
}