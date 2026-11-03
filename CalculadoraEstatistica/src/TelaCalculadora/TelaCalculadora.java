package TelaCalculadora;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCalculadora extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// Transformados em atributos da classe para podermos ler os valores no botão
	private JTextField txtQuantidade;
	private JTextArea txtAreaNumeros;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCalculadora frame = new TelaCalculadora();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCalculadora() {
		setTitle("Calculadora Estatística");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CALCULADORA ESTATÍSTICA");
		lblNewLabel.setBounds(133, 11, 181, 29);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quantos números deseja analisar?");
		lblNewLabel_1.setBounds(10, 35, 210, 38);
		getContentPane().add(lblNewLabel_1);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(211, 44, 86, 20);
		getContentPane().add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Digite os números (separados por vírgula e espaço):");
		lblNewLabel_2.setBounds(10, 100, 260, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtAreaNumeros = new JTextArea();
		txtAreaNumeros.setBounds(10, 125, 380, 64);
		getContentPane().add(txtAreaNumeros);
		
		JButton btnAvancar = new JButton("Avançar");
		btnAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lógica executada ao clicar no botão
				try {
					String qtdTexto = txtQuantidade.getText().trim();
					String numerosTexto = txtAreaNumeros.getText().trim();
					
					// Validação básica para ver se não está vazio
					if (qtdTexto.isEmpty() || numerosTexto.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					int quantidade = Integer.parseInt(qtdTexto);
					
					// Teste temporário para verificar se capturou corretamente
					JOptionPane.showMessageDialog(null, "Quantidade: " + quantidade + "\nNúmeros digitados:\n" + numerosTexto);
					
					// Aqui depois você chamará a próxima tela passando esses dados!
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "A quantidade deve ser um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAvancar.setBounds(335, 227, 89, 23);
		getContentPane().add(btnAvancar);
	}
}
